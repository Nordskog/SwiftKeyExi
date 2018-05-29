package com.mayulive.swiftkeyexi.xposed.key;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.util.TextUtils;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.Modifiers;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

	public static XC_MethodHook.Unhook hookOnKeyDown() throws NoSuchFieldException
	{
		return XposedBridge.hookMethod(KeyClassManager.keyboardSingleKeyDownMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{

				try
				{
					Object thiz = param.thisObject;
					KeyDefinition key = KeyCommons.getKeyDefinition(thiz);

					if (DebugSettings.DEBUG_KEYS)
					{
						Log.i(LOGTAG, "Key down: "+(key != null ? key.toString() : "NULL" ));
					}

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
				catch (Throwable ex)
				{
					Hooks.keyHooks_keyDefinition.invalidate(ex, "Unexpected problem in onKeyDown hook");
				}
			}

		});
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


	public static Set<XC_MethodHook.Unhook> hookKeyFields(PackageTree param)
	{

		Set<XC_MethodHook.Unhook> returnSet = new HashSet<>();




		returnSet.add( XposedBridge.hookMethod(KeyClassManager.keyFieldsClass_setIntegerMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				//We should only pass through this once for every key.
				//The Symbol is not actually defined for all keys, and that method is thus not always run.
				//Null here if symbol has not changed
				if (KeyCommons.sSymboledDefinedOnLastKeyLoop == KeyCommons.sLastSymbolDefined)
				{
					if (DebugSettings.DEBUG_KEYS)
						Log.i(LOGTAG, "Symbol unchanged, nulling");
					KeyCommons.sLastSymbolDefined = null;
				}
				KeyCommons.sSymboledDefinedOnLastKeyLoop = KeyCommons.sLastSymbolDefined;


				if (DebugSettings.DEBUG_KEYS)
					Log.i(LOGTAG, "Param int set");
				KeyCommons.mKeyFieldsSetIntCalled = true;
			}
		}) );

		for (Method method : KeyClassManager.keyFieldsClass_setStringMethods)
		{
			returnSet.add( XposedBridge.hookMethod(method, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					if (KeyCommons.mKeyFieldsSetIntCalled)
					{

						if (DebugSettings.DEBUG_KEYS)
							Log.i(LOGTAG, "Got param tag: "+ KeyCommons.mLastTag);
						KeyCommons.mLastTag = (String)param.args[0];
						KeyCommons.mKeyFieldsSetIntCalled = false;
					}
				}
			}) );
		}

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
					if (returnKey != null && KeyCommons.mLastTag != null)
					{
						//Note that this is actually called multiple times for each key,
						//Once for the parent, and again for all its popups.
						//This means all the children will have the same content as their parent,
						//but this shouldn't be a problem.
						KeyHandlers.handleKeyConstructed(returnKey, KeyCommons.mLastTag);
					}
					else if (DebugSettings.DEBUG_KEYS)
					{
						Log.i(LOGTAG, "Key?: "+(returnKey!=null)+", tag?: "+(KeyCommons.mLastTag!=null) );
					}
				}
				catch (Throwable ex)
				{
					Hooks.keyHooks_keyDefinition.invalidate(ex, "Unexpected problem in KeyConstructor hook");
				}

			}
		});
	}

	public static XC_MethodHook.Unhook newKeyHook( ) throws NoSuchFieldException
	{
		return XposedBridge.hookMethod(KeyClassManager.keyRawDefinitionClass_newKeyMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					if (param.getResult() != null)
					{
						KeyCommons.sLastSymbolDefined = (String)param.getResult();
						KeyCommons.sLastSymbolDefined = KeyCommons.sLastSymbolDefined.split(" ")[0];
						KeyCommons.sLastSymbolDefined = TextUtils.stripZeroWidthJoiner(KeyCommons.sLastSymbolDefined);

						if (DebugSettings.DEBUG_KEYS)
						{
							Log.i(LOGTAG, "Key defined. Symbol: "+KeyCommons.sLastSymbolDefined);
						}
					}
				}
				catch (Throwable ex)
				{
					Hooks.keyHooks_keyDefinition.invalidate(ex, "Unexpected problem in newKeyHook");
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
				Hooks.keyHooks_keyDefinition.add( newKeyHook() );
				Hooks.keyHooks_keyDefinition.add( hookOnKeyDown() );

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
				@Override public void beforeKeyboardClosed() {}

				@Override
				public void keyboardInvalidated()
				{
					KeyCommons.clearKeys();
				}

				@Override public void afterKeyboardConfigurationChanged() {}

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
