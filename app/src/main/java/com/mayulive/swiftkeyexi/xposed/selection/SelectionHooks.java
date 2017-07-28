package com.mayulive.swiftkeyexi.xposed.selection;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysCommons;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SwipeOverlay;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class SelectionHooks
{
	private static String LOGTAG = ExiModule.getLogTag(SelectionHooks.class);


	public static XC_MethodHook.Unhook hookViewCreated( )
	{
		{
			return XposedBridge.hookMethod(SelectionClassManager.frameHolderFactoryClass_frameHolderInflaterMethod, new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{

					try
					{
						View thiz = (View)param.args[0];

						//int targetParentId = thiz.getResources().getIdentifier("keyboard_view", "id", ExiXposed.HOOK_PACKAGE_NAME);
						int targetId = thiz.getResources().getIdentifier("keyboard_frame_holder", "id", ExiXposed.HOOK_PACKAGE_NAME);

						//We used to refer to the parent by its ID, but someone forgot to assign the ID to the view
						//when creatin the layout xml for the compact layout. Luckily the frame holder one is still there.
						//ViewGroup targetParent = (ViewGroup)thiz.findViewById(targetParentId);
						View target = thiz.findViewById(targetId);
						ViewGroup targetParent = (ViewGroup)target.getParent();	//Linear layout


						if (targetParent == null || target == null)
						{
							Log.e(LOGTAG, "Selection overlay failed to get views. Target parent null?: "+(targetParent==null)+"Target null?: "+(target==null) );
						}
						else
						{
							int currentPosition = CodeUtils.findViewPosition(targetParent, target);

							ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
							SwipeOverlay overlay = new SwipeOverlay(thiz.getContext());
							overlay.setLayoutParams(params);

							targetParent.removeView(target);
							overlay.addView(target);
							targetParent.addView(overlay,currentPosition);
						}
					}
					catch (Throwable ex)
					{
						Hooks.selectionHooks_base.invalidate(ex, "Unexpected problem in Selection View Created hook");
					}

				}
			});
		}
	}


	public static Set<XC_MethodHook.Unhook> hookFlowHandler( )
	{
		//This class also handles swipe requests, but .. they aren't actually passed to it.
		//Probabily both implementing the same interface

		Set<XC_MethodHook.Unhook> returnHooks = new HashSet<>();

		for (Method method : SelectionClassManager.FlowDelegate_flowDetectedMethods)
		{

			Object tempEnum = null;
			if (method.getParameterTypes().length > 0)
			{
				Class firstParamClass = method.getParameterTypes().getClass();
				if (firstParamClass.isEnum())
				{
					tempEnum = CodeUtils.findEnumByName((Enum[])firstParamClass.getEnumConstants(), "DRAG");
				}
			}

			final Object targetEnum = tempEnum;

			if (targetEnum != null)
			{
				returnHooks.add( XposedBridge.hookMethod(method, new XC_MethodHook()
				{
					@Override
					protected void beforeHookedMethod(MethodHookParam param) throws Throwable
					{
						try
						{
							if (param.args[0].getClass().isEnum())
							{
								if (param.args[0] == targetEnum)
								{
									if (SelectionState.isSwipeAllowed())
									{
										param.setResult(true);
									}
								}
							}
						}
						catch (Throwable ex)
						{
							Hooks.selectionHooks_base.invalidate(ex, "Unexpected problem in Flow Handler hook");
							SelectionState.clearState(0);
						}
					}
				}));
			}
		}

		return returnHooks;
	}



	public static XC_MethodHook.Unhook hookSwipeHandler( )
	{
		//for (Method method : swipeDelegate_flowDetectedMethods)
		{
			{
				return XposedBridge.hookMethod(SelectionClassManager.swipeDelegate_flowDetectedMethod, new XC_MethodHook()
				{
					@Override
					protected void beforeHookedMethod(MethodHookParam param) throws Throwable
					{

						if (SelectionState.isSwipeAllowed())
						{
							param.setResult(false);
						}

					}
				});
			}
		}
	}

	public static XC_MethodHook.Unhook hookSelectionChangedEvent( )
	{

		//When the cursor is moved to a different word, swiftkey will always move it to the end of the word once selection has end.
		//Actually does this /while/ you're selecting too, which can be very jarring. This obviously requires the user to first
		//move the cursor to the word they want it in, then release and start selecting again to move it somewhere within that word.
		//Silly.

		//We already perform a second setSelection() after exiting batch mode when swiping, but without this hook there's a split second
		//flicker as we first get the predictions from the cursor being at the end of the word, then again after we move it back to
		//where the user actually wanted it.

		//This methof is not called anywhere else.

		{
			return XposedBridge.hookMethod(SelectionClassManager.SelectionChangedInputEventClass_hasMovedAbruptlyMethod, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					param.setResult(false);
				}
			});
		}
	}







	public static void hookAll(final PackageTree param)
	{
		try
		{

			if ( Hooks.selectionHooks_base.isRequirementsMet() )
			{
				SelectionClassManager.doAllTheThings(param);

				if ( Hooks.selectionHooks_base.isRequirementsMet() )
				{
					//Disable flow and gestures
					Hooks.selectionHooks_base.addAll( hookFlowHandler() );
					Hooks.selectionHooks_base.add( hookSwipeHandler() );

					//Selection
					Hooks.selectionHooks_base.add( hookViewCreated() );

					//Prevent swiftkey from moving cursor to end of word
					//Not a requirement, and may be null
					if (SelectionClassManager.SelectionChangedInputEventClass_hasMovedAbruptlyMethod != null)
					{
						hookSelectionChangedEvent();
					}

					PopupkeysCommons.addOnLongPressListener(new PopupkeysCommons.OnLongPressTriggeredListener()
					{
						@Override
						public boolean onLongPressTriggered()
						{
							return SelectionMethods.handleLongPress();
						}
					});

					KeyboardMethods.addKeyboardEventListener(new KeyboardMethods.KeyboardEventListener()
					{
						@Override public void beforeKeyboardClosed() {}

						@Override
						public void keyboardInvalidated()
						{

						}

						@Override public void afterKeyboardConfigurationChanged() {}

						@Override
						public void beforeKeyboardOpened()
						{
							SelectionSetup.populateActions();
							SelectionSetup.populateKeys();
						}

					});


					KeyCommons.addOnKeyDownListener(new KeyCommons.OnKeyDownListener()
					{
						@Override
						public void onKeyDown(KeyDefinition key)
						{
							SelectionMethods.handleFirstKeyDown(key);
						}
					});
				}
			}
		} 
		catch ( Exception ex )// | InstantiationException | IllegalAccessException | InvocationTargetException e)
		{
			Hooks.selectionHooks_base.invalidate(ex, "Failed to hook");
		}

	}
	
}





