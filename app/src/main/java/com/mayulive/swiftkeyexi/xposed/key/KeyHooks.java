package com.mayulive.swiftkeyexi.xposed.key;

import android.graphics.RectF;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.util.TextUtils;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
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
						//key = new KeyDefinition("", KeyCommons.KeyType.DEFAULT);
						KeyCommons.callKeyDownListeners(key);
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
								//Log.e("###", "Cancelling normal button tap");
								param.setResult(false);
								KeyCommons.mCancelNextKey = false;
							}
							else if (!KeyCommons.mCancelAllKeys)
								KeyCommons.mCancelNextKey = false;
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

	//Called after key definition
	public static XC_MethodHook.Unhook hookKeyDefinition( ) throws NoSuchFieldException
	{
			return XposedBridge.hookMethod(KeyClassManager.keyFactory_DownKeyDefinitionMethod, new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						//This is the LAST  method that is called during the creation of a key.
						//Shared variables will be cleared at the end of it.
						if (DebugSettings.DEBUG_KEYS)
						{
							if (KeyCommons.sLastSymbolDefined != null)
							{
								Log.i(LOGTAG, "Definition, Last symbols is: "+KeyCommons.sLastSymbolDefined);
							}
							else
							{
								Log.i(LOGTAG, "Definition, Last symbols is: NULL");
							}
						}

						String tag = (String)param.args[KeyClassManager.keyFactory_DownKeyDefinitionMethod_STRING_PARAM_POSITION];
						Object returnKey = param.getResult();

						RectF hitbox = KeyCommons.getKeyArea(returnKey);

						KeyType type = KeyType.getType(tag);

						if (DebugSettings.DEBUG_KEYS)
						{
							Log.i(LOGTAG, "Key defined. Tag: "+tag);
						}

						if (KeyType.SPACE == type)
						{

							if (KeyboardMethods.isLayoutWeird())
							{
								// Figure out the real positions of the keys in weird layouts
								// would be entirely too much work, so they will not support
								// swipe-from-space-to-key hotkeys. They should still support
								// the hotkey menu though, meaning we at the very least need to
								// figure out where the spacebar is.

								// In weird layouts, the bottom row of keys is in its own window,
								// and hitbox coordinates are for positions inside it. If arrow
								// keys are enabled they go in the same box below this row,
								// so spacebar will take up half the vertical space, rather than all of it).
								// In horizontal mode the arrows go on the right side instead.
								// The height of the spacebar is the same regardless, only being squished
								// if the user also enabled the number row. Maybe take that into consideration later.



								//If it fills its box it's at the bottom, if not there are two rows.
								boolean spacebarAtBottom = hitbox.height() > 0.75f;

								if (spacebarAtBottom)
								{
									//Space key at the very bottom in a normal layout, without number row
									//Area: RectF(0.25250053, 0.75625, 0.74750054, 0.99375) }

									hitbox = new RectF(hitbox.left, 0.75625f, hitbox.right, 0.99375f);


								}
								else
								{	//With arrow keys row
									//Hitbox: RectF(0.25250053, 0.6368421, 0.74750054, 0.8368421)
									hitbox = new RectF(hitbox.left, 0.6368421f, hitbox.right, 0.8368421f);
								}
							}
						}

						if (KeyCommons.sLastSymbolDefined == null)
						{
							KeyCommons.sLastSymbolDefined = "";
						}

						{
							KeyDefinition newKey = new KeyDefinition(KeyCommons.sLastSymbolDefined, type, hitbox);
							KeyCommons.addKeyDefinition(returnKey, newKey);
							KeyCommons.mLastKeyDefined = newKey;

							//If we can't track layout changes, we shouldn't bother with this.
							if (Hooks.baseHooks_layoutChange.isRequirementsMet())
							{
								KeyCommons.HitboxMap map = KeyCommons.getHitboxMap();

								//Don't bother adding anything but space for weird layouts
								if (map != null && ( !KeyboardMethods.isLayoutWeird() || type == KeyType.SPACE ) )
								{
									String key = "";
									if (KeyCommons.sLastSymbolDefined != null)
										key = KeyCommons.sLastSymbolDefined;
									if (key.isEmpty())
									{
										key = tag+System.identityHashCode(returnKey);
									}

									map.put(key, newKey);
								}
							}
						}

						//They changed the call order. May cause problems if things break, but do not invalidate last symbol anymore.
						/*
						if (DebugSettings.DEBUG_KEYS)
						{
							Log.i(LOGTAG, "Key define finished, resetting last symbol");
						}

						KeyCommons.sLastSymbolDefined = null;
						*/

						//With the key created, clear recycled variables
						KeyCommons.sLastSymbolDefined = null;

					}
					catch (Throwable ex)
					{
						Hooks.keyHooks_keyDefinition.invalidate(ex, "Unexpected problem in keyDefinition hook");
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

						if (KeyCommons.mLastKeyDefined != null)
						{

						}

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
				Hooks.keyHooks_keyDefinition.add( hookKeyDefinition() );
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
		catch(Exception ex)
		{
			Hooks.keyHooks_keyDefinition.invalidate(ex, "Failed to hook");
			return false;
		}

		return true;
	}








}
