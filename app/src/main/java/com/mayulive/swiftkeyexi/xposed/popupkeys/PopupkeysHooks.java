package com.mayulive.swiftkeyexi.xposed.popupkeys;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupKeyItem;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

import static com.mayulive.swiftkeyexi.xposed.key.KeyClassManager.keyRawDefinitionClass;
import static com.mayulive.swiftkeyexi.xposed.key.KeyCommons.sLastSymbolDefined;
import static com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysClassManager.btSubClass_aField;
import static com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysClassManager.btSubClass_bField;
import static com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysClassManager.btSubClass_getProperOrderMethod;
import static com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysClassManager.keyRawDefinitionInputClass;
import static com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysClassManager.keyRawDefinitionInputClass_listFields;


/**
 * Created by Roughy on 2/15/2017.
 */

public class PopupkeysHooks
{

	private static String LOGTAG = ExiModule.getLogTag(PopupkeysHooks.class);

	public static XC_MethodHook.Unhook hookPopupScheduler() throws NoSuchMethodException, NoSuchFieldException
	{
		return XposedBridge.hookMethod(PopupkeysClassManager.popupScheduler_schedulePopupMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					if (PopupkeysCommons.mDelayNextPopup)
					{
						PopupkeysCommons.mDelayNextPopup = false;
						param.args[1] = ( (long)param.args[1] )* 2;

						Method superMethod = (Method)param.method;
						param.setResult( superMethod.invoke(param.thisObject, param.args) );
					}
				}
				catch (Throwable ex)
				{
					Hooks.popupHooks_delay.invalidate(ex, "Unexpected problem in Popup Delay hook");
				}


			}
		});
	}

	public static void hookSymbolsA( ) throws NoSuchFieldException
	{

		XposedBridge.hookMethod(PopupkeysClassManager.addLongPressCharacters_A_Method, new XC_MethodHook()
		{
			/*
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				Log.i(LOGTAG, "A input: "+param.args[0].toString());
			}
			*/

			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				if (DebugSettings.DEBUG_POPUPS)
				{
					Log.i(LOGTAG, "A output: "+param.getResult().toString());
				}


				List<String> listObject = (List<String>)param.getResult();

				if (listObject!= null)
				{
					if (listObject.size() > 1)
					{
						if (DebugSettings.DEBUG_POPUPS)
						{
							Log.i(LOGTAG, "Adding to multi-popup keys: "+ sLastSymbolDefined);

							Log.i(LOGTAG, "Hash is: "+Integer.toHexString(sLastSymbolDefined.hashCode()) ) ;
						}


						//Log.i(LOGTAG, "Adding to multi-popup: "+sLastSymbolDefined);
						PopupkeysCommons.mMultipleKeyPopups.add(sLastSymbolDefined);
					}
					else
					{
						if (DebugSettings.DEBUG_POPUPS)
							Log.i(LOGTAG, "Not adding to multi-popup keys");
					}
				}
				else
				{
					if (DebugSettings.DEBUG_POPUPS)
						Log.i(LOGTAG, "List null");
				}




				//PopupkeysMethods.handlePopupkeyInitialInsert((List<String>)param.getResult(), sLastSymbolDefined, false);
			}
		});
	}


	public static Set<XC_MethodHook.Unhook> hookPopupsInsertion( ) throws NoSuchFieldException
	{

		{
			return XposedBridge.hookAllConstructors(keyRawDefinitionClass, new XC_MethodHook()
			{

				private int mLastListFieldIndex = -1;

				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						//While this is normally called on keyboard creation, it is also called every time a Pinyin
						//keyboard key is hit. This is because it creates a side menu where you can select from additional keys.
						//B and later methods are not called though, and adding anything here doesn't have any effect, good or bad.

						if (param.args.length > 0 && keyRawDefinitionInputClass.isAssignableFrom( param.args[0].getClass() ))
						{
							Object thiz = param.args[0];

							List<String> listObject = null;

							int currentListFieldIndex = -1;
							//if (mLastListFieldIndex == -1)	//Changes betwene lanagues
							{

								int index = 0;
								for (Field field : keyRawDefinitionInputClass_listFields)
								{
									//There should only be a single field that is not null or empty
									List value = (List)field.get(thiz);
									if (value != null)
									{
										if (value.size() > 0)
										{
											currentListFieldIndex = index;
											break;
										}
									}
									index++;
								}
							}

							if (currentListFieldIndex == -1)
								currentListFieldIndex = mLastListFieldIndex;
							mLastListFieldIndex = currentListFieldIndex;


							if (currentListFieldIndex != -1)
							{

								listObject = (List)keyRawDefinitionInputClass_listFields.get( currentListFieldIndex ).get(thiz);
								if (DebugSettings.DEBUG_POPUPS)
								{
									if (listObject != null)
									{
										Log.e(LOGTAG, "Popup values: "+listObject.toString());
									}

								}

								//I don't see why this would be needed
								//keyRawDefinitionInputClass_listFields.get(mLastListFieldIndex).set(thiz, listObject);
								if (listObject != null && sLastSymbolDefined != null)
								{
									PopupkeysMethods.handlePopupkeyInitialInsert(listObject, sLastSymbolDefined, true);
								}


							}
							else if (DebugSettings.DEBUG_POPUPS)
							{
								Log.e(LOGTAG, "Could not find list field");
							}
						}
						else
						{
							if (DebugSettings.DEBUG_POPUPS)
							Log.i(LOGTAG, "Wrong constructor: "+param.method.toString());
						}
					}
					catch (Throwable ex)
					{
						Hooks.popupHooks_read.invalidate(ex, "Unexpected problem in Alternate Symbols A hook");
					}
				}
			});

		}
	}


	public static XC_MethodHook.Unhook hookButtonOrder() throws NoSuchFieldException
	{
		{
			return XposedBridge.hookMethod(btSubClass_getProperOrderMethod, new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{

					//Log.e("###", "Button order");
					try
					{
						if (PopupkeysCommons.mLastPopupKeyList != null)
						{
							ArrayList<String> outputKeys = new ArrayList<String>();

							Object btSubClassInstance = param.getResult();
							outputKeys.addAll( (List<String>) btSubClass_aField.get(btSubClassInstance) );
							int leftCount = (int) btSubClass_bField.get(btSubClassInstance);

							if (DebugSettings.DEBUG_POPUPS)
							{

								Log.i(LOGTAG, "Order input: "+outputKeys);

							}

							//Log.e("###", "leftcount: "+leftCount);
							//Log.e("###", "Input: "+outputKeys.toString());

							//Unlike the earlier lists, the order of this list
							//matches what will actually appeaer on screen, in
							//left-to-right order.

							//leftCount is the number of keys that appear on the
							//left before the primary center key (the key directly above
							//the key we long pressed.

							//e.g. if leftCount is 2, the primary popup key is at index 2.

							//The primary popup should always be whatever key we have inserted
							//at index 0. The rest are inserted as-is, but leaving whatever key
							//is the primary key at its original location.


							boolean isLowerCase = !sLastSymbolDefined.equalsIgnoreCase(PopupkeysCommons.mLastOrderSymbol);
							PopupkeysCommons.mLastOrderSymbol = sLastSymbolDefined;

							//Grab a copy of the primary popup, if we need it.
							String primaryPopup = outputKeys.get(leftCount);

							boolean replacePrimary = false;
							for (DB_PopupKeyItem item : PopupkeysCommons.mLastPopupKeyList)
							{
								if (item.get_insertIndex() == 0)
								{
									//We're replacing it! In this case we will not remove it from the array below.
									//Regardless of the outcome, all we have to do afterwards is add primaryPopup back in at
									//position leftCount;
									//primaryPopup = isLowerCase ? item.get_popupLower() : item.get_popupUpper();
									//primaryPopup = isLowerCase ? item.get_popupLower() : item.get_popupLower().toUpperCase();
									primaryPopup =  item.get_popupLower();

									replacePrimary = true;

									break;
								}
							}
							if (!replacePrimary)
								outputKeys.remove(leftCount);


							//Remove all our dummy keys
							for (int i = 0; i < outputKeys.size(); i++)
							{
								//Log.i(LOGTAG, "Checking dummy: "+outputKeys.get(i));

								if (outputKeys.get(i).startsWith(PopupkeysCommons.NULLCHAR))
								{
									//Log.i(LOGTAG, "Removing");
									outputKeys.remove(i);
									i--;
								}
							}

							//If there is no existing primary, and we are not adding one,
							//we should not re-add it below
							boolean primaryNotPresent = primaryPopup.startsWith(PopupkeysCommons.NULLCHAR);


							//Insert all our keys, starting from the lowest
							//They was order this way when loaded from database.

							//Log.i(LOGTAG, "Adding items: "+PopupkeysCommons.mLastPopupKeyList.size());


							for (DB_PopupKeyItem item : PopupkeysCommons.mLastPopupKeyList)
							{
								//Primary popup items will be inserted manually afterwards
								//We make sure there is only a single index-0 item when loading from database
								if (item.get_insertIndex() == 0)
									continue;

								//Because we treat 0 as the primary popup,
								//actual insert positions should be shifted by 1
								int insertLocation = item.get_insertIndex();
								insertLocation--;

								String insertString;
								//if ( isLowerCase)
								insertString = item.get_popupLower();
								//else
								//	insertString = item.get_popupUpper();

								//TODO lowercase check fails work words but works with single chars?
								//Might be preferrable for them to be inserted as-is anyway.
								//if (isLowerCase)
								//	insertString = insertString.toUpperCase();

								if (insertLocation > outputKeys.size() )
								{
									//Add to end if index too large
									outputKeys.add(insertString);
								}
								else
								{
									//Otherwise just insert at index
									outputKeys.add(insertLocation, insertString);
								}
							}

							//Finally place primaryPopup back in.
							//This may be the original primary, or our inserted primary.
							if (!primaryNotPresent)
								outputKeys.add(leftCount, primaryPopup);

							///////////////////////

							if (DebugSettings.DEBUG_POPUPS)
								Log.i(LOGTAG, "Output: "+outputKeys.toString());

							btSubClass_aField.set(btSubClassInstance, outputKeys);
						}
					}
					catch (Throwable ex)
					{
						Hooks.popupHooks_modify.invalidate(ex, "Unexpected problem in Popups Button Order hook");
					}
				}
			});
		}
	}



	public static XC_MethodHook.Unhook hookExtrasKeysPopupRunnable() throws NoSuchMethodException, NoSuchFieldException
	{
		return XposedBridge.hookMethod(PopupkeysClassManager.extraKeyPopupRunnableRunMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				if (PopupkeysCommons.callLongPressListeners())
				{
					//Log.e("###", "Blocking popup");
					param.setResult(null);
				}
				else
				{
					//Log.e("###", "Allowing popup");
				}
			}
		});
	}



	public static void hookAll(final PackageTree param)
	{

		try
		{
			//Check dependencies
			{
				PopupkeysClassManager.doAllTheThings(param);

				hookSymbolsA();


				if ( Hooks.popupHooks_delay.isRequirementsMet() )
				{
					Hooks.popupHooks_delay.add( hookPopupScheduler() );
				}

				if ( Hooks.popupHooks_cancel.isRequirementsMet() )
				{
					Hooks.popupHooks_cancel.add( hookExtrasKeysPopupRunnable() );
				}

				if ( Hooks.popupHooks_read.isRequirementsMet() )
				{
					Hooks.popupHooks_read.addAll( hookPopupsInsertion() );

					if ( Hooks.popupHooks_modify.isRequirementsMet() )
					{
						Hooks.popupHooks_modify.add( hookButtonOrder() );

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
								if (PopupkeysCommons.mLastUpdateTime < Settings.LAST_POPUP_UPDATE)
								{
									PopupkeysSetup.loadPopupKeys();
									PopupkeysCommons.mLastUpdateTime = Settings.LAST_POPUP_UPDATE;
								}
							}

						});
					}
				}
			}


		}
		catch ( Exception ex)
		{
			Hooks.popupHooks_read.invalidate(ex, "Failed to hook");
			Hooks.popupHooks_delay.invalidate(ex, "Failed to hook");
			Hooks.popupHooks_cancel.invalidate(ex, "Failed to hook");
		}

		/*
		KeyCommons.addOnKeyUpListener(new KeyCommons.OnPopupKeyUpListener()
		{
			@Override
			public boolean shouldKeyBeCancelled(String key)
			{
				return PopupkeysCommons.handlePopupKeyAction(key);
			}
		});
		*/


	}

}
