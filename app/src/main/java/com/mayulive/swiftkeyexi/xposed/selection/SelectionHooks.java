package com.mayulive.swiftkeyexi.xposed.selection;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysCommons;
import com.mayulive.swiftkeyexi.xposed.predictions.PredictionCommons;
import com.mayulive.swiftkeyexi.xposed.predictions.PredictionSetup;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorBehavior;
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
							SelectionState.mSwipeOverlay = new SwipeOverlay(thiz.getContext());
							SelectionState.mSwipeOverlay.setLayoutParams(params);

							targetParent.removeView(target);
							SelectionState.mSwipeOverlay.addView(target);
							targetParent.addView(SelectionState.mSwipeOverlay,currentPosition);
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


	public static XC_MethodHook.Unhook hookFlowHandler( )
	{
		//This class also handles swipe requests, but .. they aren't actually passed to it.
		//Probabily both implementing the same interface
		return XposedBridge.hookMethod(SelectionClassManager.FlowDelegate_flowDetectedMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					if (param.args[0] == SelectionClassManager.FlowDelegate_DRAG_ENUM)
					{
						//We block swipe when some popups appear, including the spacebar change-language one.
						//this results in swipeAllowed() returning false, thus the extra space check
						if (SelectionState.isSwipeAllowed() || SelectionState.mSpaceDown)
						{
							param.setResult(true);
						}
					}

				}
				catch (Throwable ex)
				{
					Hooks.selectionHooks_base.invalidate(ex, "Unexpected problem in Flow Handler hook");
					SelectionState.clearState(0);
				}
			}
		});
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
						//We block swipe when some popups appear, including the spacebar change-language one.
						//this results in swipeAllowed() returning false, thus the extra space check
						if (SelectionState.isSwipeAllowed() || SelectionState.mSpaceDown)
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
					//Call if pointer down on overlay, or <100ms since end of swipe selection event
					if ( Settings.DISABLE_CURSOR_JUMPING ||
							(SelectionState.mValidFirstDown || (System.currentTimeMillis() - SelectionState.mSwipeEndTime) < 100 ) )
					{
						param.setResult(false);
					}
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
					Hooks.selectionHooks_base.add( hookFlowHandler() );
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

					Settings.addOnSettingsUpdatedListener(new Settings.OnSettingsUpdatedListener()
					{
						@Override
						public void OnSettingsUpdated()
						{
							SelectionSetup.populateActions();
							SelectionSetup.populateKeys();
							SelectionSetup.populateQuickMenu();
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

					KeyCommons.addOnInputEventListener(new KeyCommons.OnInputEventListener()
					{
						@Override
						public boolean onInputEvent()
						{

							//If you have two fingers on the kyeboard and swipe wit hthe second,
							//swiftkey will insert all the keys it swipes across.
							//This interfers with either of our hold-and-swipe modes.
							//Solution: No input events without corresponding up events
							if (Settings.SWIPE_CURSOR_BEHAVIOR.isMultiKey())
							{
								//Only apply if shift is down in this mode
								if (Settings.SWIPE_CURSOR_BEHAVIOR == CursorBehavior.HOLD_SHIFT_SWIPE && !SelectionState.mShiftDown)
								{
									return false;
								}

								//Otherwise check last pointer up event if pointer count is above 2 and the last up event was
								//more than 20ms ago, disallow input
								if (SelectionState.mLastPointerCount > 1
										&& (System.currentTimeMillis() - SelectionState.mLastPointerUpTime) > 20 )
								{
									return true;
								}
							}

							return false;
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





