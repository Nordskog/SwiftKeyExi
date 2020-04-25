package com.mayulive.swiftkeyexi.xposed.key;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.settings.Settings;

import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;

import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysCommons;
import com.mayulive.swiftkeyexi.xposed.selection.SelectionMethods;
import com.mayulive.swiftkeyexi.xposed.selection.SelectionState;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.UpDownMotionEvent;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import java.lang.reflect.Method;


import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;


/**
 * Created by Roughy on 2/14/2017.
 */


//Key up, key down, cancel key hooks.
public class KeyHooks
{
	private static String LOGTAG = ExiModule.getLogTag(KeyHooks.class);

	private static void handleDonwHookAfter()
	{
		// Back in the day enabling it directly in the before hook would allow the current event but cancel the next one.
		// For some reason it will now also cancel the current one, so we set it on a delay.
		if ( KeyCommons.mCancelAllKeysAfterKeyDown )
		{
			KeyCommons.mCancelAllKeys = true;
			KeyCommons.mCancelAllKeysAfterKeyDown = false;
		}
	}

	private static void handleUpHookBefore( XC_MethodHook.MethodHookParam param )
	{

		if (DebugTools.DEBUG_KEYS)
		{
			Object thiz = param.thisObject;
			KeyDefinition key = KeyCommons.getKeyDefinition(thiz);

			Log.d(LOGTAG, "Up   method: "+param.toString());
			Log.d(LOGTAG, "Key up: "+(key != null ? key.toString() : "NULL" )+", pointer: "+System.identityHashCode(thiz));
		}

		if ( KeyCommons.mLongPressTriggeredSinceLastDownEvent)
		{
			KeyCommons.mLongPressTriggeredSinceLastDownEvent = false;

			try
			{
				Object thiz = param.thisObject;
				KeyDefinition key = KeyCommons.getKeyDefinition(thiz);

				// This is probably a popup
				if (key != null && key.type == KeyType.POPUP && key.hasCustomPopups && key.content != null && key.content.length() > 1 )
				{
					// With extremely few exceptions, popups only contain a single character.
					// Our inserted popups, however, may contain more characters.
					// For some reason Swiftkey will sort or cut off long popup strings.
					// A notable example is password fields, where for some bizarre reason the string will be sorted
					// so that all numbers come first, followed by all letters.
					// If the popup is greater than 1 character we should inser it ourselves instead.
					// We need to call the keyCancel event on the popup key to make it disappeaer/de-highlight.

					if (KeyClassManager.keyboardSingleKeyCancelMethod != null)
					{
						if (DebugTools.DEBUG_KEYS)
						{
							Log.d(LOGTAG, "Multi-char popup. Will cancel and insert manually: "+key.content);
						}
						param.setResult(null);
						KeyClassManager.keyboardSingleKeyCancelMethod.invoke(thiz, param.args[0]);

						KeyboardMethods.inputText( key.content );
					}
				}
			}
			catch (Throwable ex)
			{
				Hooks.keyHooks_keyDefinition.invalidate(ex, "Unexpected problem in onKeyUp hook");
			}
		}
	}

	private static void handleDonwhookBefore(XC_MethodHook.MethodHookParam param, boolean isDefinitelyDown )
	{

		if (DebugTools.DEBUG_KEYS)
		{
			Object thiz = param.thisObject;
			KeyDefinition key = KeyCommons.getKeyDefinition(thiz);

			Log.d(LOGTAG, "Key down: "+(key != null ? key.toString() : "NULL" )+", pointer: "+System.identityHashCode(thiz));
			Log.d(LOGTAG, "Down method: "+param.toString());
		}

		if ( isDefinitelyDown || SelectionState.getLastUpDownEvent() == UpDownMotionEvent.DOWN)
		{
			try
			{
				Object thiz = param.thisObject;
				KeyDefinition key = KeyCommons.getKeyDefinition(thiz);

				//Maybe keys are defined somewhere else too?
				//I used to pass a generic key in these cases, but recently swiftkey have
				//changed how long-presses work. When a popup... pops-up, it triggers
				//a null key (key that did not pass through the definition methods we are hooking).
				//This causes some features to think another key was pressed, even though it was just
				//a key poppping up. We shouldn't be doing anything with these null keys anyway,
				//so let's just not pass them along.
				if (key != null)
				{
					KeyCommons.callKeyDownListeners(key);

					// Normally when we invoke the original method, the hook is not called.
					// with EdXposed, it is, for some reason. Probably only because we call it from outside of the hook.
					// Check time since last processed and skip delaying key if it was very very recent.
					if ( System.currentTimeMillis() - KeyCommons.mDelayedKeysLastProcessed > 25 )
					{
						//If we will ever be swiping for from the shift key, we have to delay it triggering
						//until pointer_up and we're sure we're not going to enter swipe.
						//This is also technically necessary when swiping from anywhere, but it is unlikely the user
						//will swipe from shift.
						if ( key.is(KeyType.SHIFT) && ( Settings.SWIPE_CURSOR_BEHAVIOR.isMultiKey() || Settings.SWIPE_SELECTION_BEHAVIOR.triggersFromShiftAndDelete() ) )
						{
							KeyCommons.DelayedKey delayedKey = new KeyCommons.DelayedKey( param.args, thiz, (Method)param.method, 2000 );
							KeyCommons.addDelayedKey(delayedKey);
							param.setResult(null);
						}
					}

				}
			}
			catch (Throwable ex)
			{
				Hooks.keyHooks_keyDefinition.invalidate(ex, "Unexpected problem in onKeyDown hook");
			}
		}

	}


	public static Set<XC_MethodHook.Unhook> hookOnKeyDown() throws NoSuchFieldException
	{
		HashSet<XC_MethodHook.Unhook> hooks = new HashSet<>();

		if ( KeyClassManager.keyboardSingleKeyDownMethod != null )
		{
			hooks.add( XposedBridge.hookMethod(KeyClassManager.keyboardSingleKeyDownMethod , new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					handleDonwHookAfter();
				}

				// This used to be a before hook, and we immediately asked to cancel all keys sometimes.
				// This prevents the key from working at all after 7.3.7.18.
				// Fixed by changing to After. Not sure what changed here.
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					handleDonwhookBefore(param, true);
				}

			}));

			hooks.add( XposedBridge.hookMethod(KeyClassManager.keyboardSingleKeyUpMethod , new XC_MethodHook()
			{
				// This used to be a before hook, and we immediately asked to cancel all keys sometimes.
				// This prevents the key from working at all after 7.3.7.18.
				// Fixed by changing to After. Not sure what changed here.
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					handleUpHookBefore(param);
				}

			}));

		}
		else
		{
			Log.e(LOGTAG, "keyboardSingleKeyDownMethod not defined, falling back to hooking all key event methods");

			for (Method method : KeyClassManager.keyboardSingleKeyActionMethods)
			{
				hooks.add( XposedBridge.hookMethod(method, new XC_MethodHook()
				{
					@Override
					protected void afterHookedMethod(MethodHookParam param) throws Throwable
					{
						handleDonwHookAfter();
					}

					// This used to be a before hook, and we immediately asked to cancel all keys sometimes.
					// This prevents the key from working at all after 7.3.7.18.
					// Fixed by changing to After. Not sure what changed here.
					@Override
					protected void beforeHookedMethod(MethodHookParam param) throws Throwable
					{
						handleDonwhookBefore(param, false);
					}

				}));
			}
		}



		return hooks;

	}

	public static Set<XC_MethodHook.Unhook> hookPreventNormalButtonInput() throws NoSuchFieldException
	{
		Set<XC_MethodHook.Unhook> returnSet = new HashSet<>();

		for (Method currentMethod : KeyClassManager.normalButtonClickItemMethods)
		{
			returnSet.add(XposedBridge.hookMethod(currentMethod, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{



					try
					{
						if (KeyCommons.mCancelNextKey || KeyCommons.mCancelAllKeys)
						{
							if ( SelectionState.mFirstDown != null && SelectionState.mFirstDown.type.isArrowKey() )
							{
								// Modifier + arrow key allows for selection now, but we request cancel all keys while the modifier is down.
								// solution ... just exclude arrow keys from the whole shebang
								return;
							}

							//Log.e("###", "maybe cancel normal button tap");
							//We set this bool to true on ACTION_UP of the primary pointer.
							//In theory we only set it when we know it's going to trigger a button tap,
							//but just to be sure, ignore the bool if it was set more than ... 100ms? ago.
							long currentTime = System.currentTimeMillis();

							if ( (currentTime - KeyCommons.mCancelNextKey_RequestTime) < 100 || KeyCommons.mCancelAllKeys)
							{
								param.setResult(false);
								KeyCommons.mCancelNextKey = false;
							}
							else
							{
								if (!KeyCommons.mCancelAllKeys)
									KeyCommons.mCancelNextKey = false;

								if (KeyCommons.callInputEventListeners())
								{
									param.setResult(false);
								}
							}
						}
						else if( KeyCommons.callInputEventListeners())
						{
							param.setResult(false);
						}
					}
					catch (Throwable ex)
					{
						Hooks.keyHooks_keyCancel.invalidate(ex, "Unexpected problem in preventNormalButtonInput hook");
					}
				}
			}));
		}

		return returnSet;
	}


	public static XC_MethodHook.Unhook hookKeyTemplateConstructor()
	{
		return XposedBridge.hookMethod(KeyClassManager.newKeyInfoClass_constructor, new XC_MethodHook()
		{

			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				int typeInt = (int) param.args[0];

				String content = (String) param.args[ KeyClassManager.newKeyInfoClass_constructorContentArgumentPosition ];

				KeyType Type = KeyType.getType(typeInt, content);

				KeyCommons.TemplateKey template = new KeyCommons.TemplateKey( Type, content );
				template.tag = Integer.toHexString(typeInt);


				KeyCommons.addTemplateKey( System.identityHashCode( param.thisObject ), template );

			}
		});

	}


	public static Set<XC_MethodHook.Unhook> hookKeyFields(PackageTree param)
	{

		Set<XC_MethodHook.Unhook> returnSet = new HashSet<>();

		returnSet.add( XposedBridge.hookMethod(KeyClassManager.keyRawDefinitionClass_newKeyMethod, new XC_MethodHook()
		{

			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				// Called on the creation of any new key.
				// This will not be called again until the key and all of its popups have been constructed.

				PopupkeysCommons.mLastOrderedLowerCasepopups = null;
				PopupkeysCommons.mLastOrderedUpperasepopups = null;
				KeyCommons.mLastKeyPopupKeyIndexCounter = 0;
				KeyCommons.mLastKeyPopupLowerCaseKeyDefinitionsProcessed = false;
				KeyCommons.mLastKeyHasCustomPopups = false;
				Object templateInstance =  param.args[0];
				KeyCommons.mLastTemplateKey = KeyCommons.getTemplateKey( System.identityHashCode(templateInstance) );
			}
		}) );

		return returnSet;
	}

	public static Set<XC_MethodHook.Unhook> hookKeyConstructor(PackageTree param)
	{

		return XposedBridge.hookAllConstructors(KeyClassManager.keyDefinitionKeyClass, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					Object returnKey = param.thisObject;
					if (KeyCommons.mLastTemplateKey != null)
					{
						//Note that this is actually called multiple times for each key,
						//Once for the parent, and again for all its popups.
						//This means all the children will have the same content as their parent,
						//but this shouldn't be a problem.
						KeyHandlers.handleKeyConstructed(returnKey, KeyCommons.mLastTemplateKey);
					}
				}
				catch (Throwable ex)
				{
					Hooks.keyHooks_keyDefinition.invalidate(ex, "Unexpected problem in KeyConstructor hook");
				}

			}
		});
	}


	public static boolean HookAll(final PackageTree param)
	{
		try
		{
			KeyClassManager.doAllTheThings(param);


			if (Hooks.keyHooks_keyDefinition.isRequirementsMet())
			{
				Hooks.keyHooks_keyDefinition.add( hookKeyTemplateConstructor() );

				Hooks.keyHooks_keyDefinition.addAll( hookOnKeyDown() );

				Hooks.keyHooks_keyDefinition.addAll( hookKeyFields(param));
				Hooks.keyHooks_keyDefinition.addAll( hookKeyConstructor(param));
			}

			if (Hooks.keyHooks_keyCancel.isRequirementsMet())
			{
				Hooks.keyHooks_keyCancel.addAll( hookPreventNormalButtonInput() );
			}

			//Run regardless I guess
			KeyboardMethods.addKeyboardEventListener(new KeyboardMethods.KeyboardEventListener()
			{
				@Override public void beforeKeyboardOpened() {}

				@Override
				public void afterKeyboardOpened()
				{

				}

				@Override public void beforeKeyboardClosed() {}


				@Override public void afterKeyboardConfigurationChanged() {}

			});

			PopupkeysCommons.addOnLongPressListener(new PopupkeysCommons.OnLongPressTriggeredListener()
			{
				@Override
				public boolean onLongPressTriggered()
				{
					KeyCommons.mLongPressTriggeredSinceLastDownEvent = true;
					return false;
				}
			});

		}
		catch(Throwable ex)
		{
			Hooks.keyHooks_keyDefinition.invalidate(ex, "Failed to hook");
			return false;
		}

		return true;
	}








}
