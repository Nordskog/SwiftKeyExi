package com.mayulive.swiftkeyexi.xposed.selection;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysCommons;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SwipeOverlay;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class SelectionHooks
{
	private static String LOGTAG = ExiModule.getLogTag(SelectionHooks.class);


	public static XC_MethodHook.Unhook hookFrameHolderTouchIntercept( )
	{

		// Keeping this guy here because there's platform classes/methods and won't ever change.
		Method interceptTouchEventMethod = ProfileHelpers.firstMethodByName( ViewGroup.class.getDeclaredMethods(), "onInterceptTouchEvent" );

		return XposedBridge.hookMethod(interceptTouchEventMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{

				try
				{
					if ( SelectionClassManager.keyboardFrameHolderFrameHolderClass == param.thisObject.getClass() )
					{
						boolean result = SwipeOverlay.onInterceptTouchEventStatic( (View)param.thisObject, (MotionEvent)param.args[0] );

						if (result)
						{
							ViewGroup thiz = (ViewGroup) param.thisObject;
							thiz.setOnTouchListener( SwipeOverlay.OnTouchListenerStatic );
						}

						param.setResult(result);
					}

				}
				catch (Throwable ex)
				{
					Hooks.selectionHooks_base.invalidate(ex, "Unexpected problem in frame holder touch intercept hook");
					SelectionState.clearState(0);
				}
			}
		});
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

		//This used to be its own separate method, but they inlined it at some point.
		//The new method does a bunch of stuff, and this check is just... in there.
		//It will not move the cursor if a certain value is -1, so we set that at the beginning of
		//the method and restore it at the end. I don't think this should affect anything else.

		{
			return XposedBridge.hookMethod(SelectionClassManager.SelectionChangedInputEventClass_hasMovedAbruptlyMethod, new XC_MethodHook()
			{
				int originalValue = 0;
				boolean valueModified = false;

				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						originalValue = (int) SelectionClassManager.selectionChangedInfoClass_aFirstIntField.get( param.args[1] );

						//Call if pointer down on overlay, or <100ms since end of swipe selection event
						if ( Settings.DISABLE_CURSOR_JUMPING ||
								(SelectionState.mValidFirstDown || (System.currentTimeMillis() - SelectionState.mSwipeEndTime) < 100 ) )
						{
							//Will force the bit of code we're after to execute in a certain way, without affecting the rest.
							//Rest in after hook.
							SelectionClassManager.selectionChangedInfoClass_aFirstIntField.set( param.args[1], -1 );
							valueModified = true;
						}
						else
						{
							valueModified = false;
						}
					}
					catch ( Throwable ex)
					{
						Hooks.selectionHooks_movedAbruptly.invalidate(ex, "Something went wrong intercepting cursor moved abruptly event");
					}


				}

				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						//Call if pointer down on overlay, or <100ms since end of swipe selection event
						if (valueModified)
						{
							SelectionClassManager.selectionChangedInfoClass_aFirstIntField.set( param.args[1], originalValue );
							valueModified = false;
						}
					}
					catch  (Throwable ex)
					{
						Hooks.selectionHooks_movedAbruptly.invalidate(ex, "Something went wrong intercepting cursor moved abruptly event, the sequel.");
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

					// Hook intercept in all viewgroups, do stuff if frameholder.
					Hooks.selectionHooks_base.add( hookFrameHolderTouchIntercept() );

					//Disable flow and gestures
					Hooks.selectionHooks_base.add( hookFlowHandler() );
					Hooks.selectionHooks_base.add( hookSwipeHandler() );

					//Prevent swiftkey from moving cursor to end of word
					//Not a requirement, and may be null
					if ( Hooks.selectionHooks_movedAbruptly.isRequirementsMet() )
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
							if (Settings.SWIPE_CURSOR_BEHAVIOR.isMultiKey() || Settings.SWIPE_SELECTION_BEHAVIOR.isGesture() )
							{
								//Only apply if shift is down in this mode, or selection behavior is gesture
								if ( (Settings.SWIPE_CURSOR_BEHAVIOR == CursorBehavior.HOLD_SHIFT_SWIPE && !SelectionState.mShiftDown)
										&& !Settings.SWIPE_SELECTION_BEHAVIOR.isGesture() )
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
		catch ( Throwable ex )// | InstantiationException | IllegalAccessException | InvocationTargetException e)
		{
			Hooks.selectionHooks_base.invalidate(ex, "Failed to hook");
		}

	}
	
}





