package com.mayulive.swiftkeyexi.xposed.popupkeys;

import android.os.Debug;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupKeyItem;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;
import com.mayulive.swiftkeyexi.main.popupkeys.data.PopupKeyItem;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
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


import static com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysClassManager.btSubClass_aField;
import static com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysClassManager.btSubClass_bField;
import static com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysClassManager.btSubClass_getProperOrderMethod;


/**
 * Created by Roughy on 2/15/2017.
 */

public class PopupkeysHooks
{

	private static String LOGTAG = ExiModule.getLogTag(PopupkeysHooks.class);


	public static XC_MethodHook.Unhook hookSymbolsA( ) throws NoSuchFieldException
	{

		return XposedBridge.hookMethod(PopupkeysClassManager.addLongPressCharacters_A_Method, new XC_MethodHook()
		{

			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				if ( KeyCommons.mLastTemplateKey == null )
				{
					if (DebugSettings.DEBUG_POPUPS)
					{
						Log.e(LOGTAG, "template key null, skipping popups.");
					}

					return;
				}


				List<String> listObject = (List<String>)param.getResult();

				////////////////////////////////////////////////////////////
				// Skip if same list twice. Happens with period and such
				///////////////////////////////////////////////////////////

				if (PopupkeysCommons.mLastInitialPopupkeyList == listObject)
				{
					//When a key starts off with no popups, or
					//with the same popups for upper and lower case,
					//they share the same list. I currently cannot change this.
					//We do want to avoid adding the same popups twice though.
					if (DebugSettings.DEBUG_POPUPS)
					{
						Log.i(LOGTAG, "Duplicate list, skipping");
					}
					return;
				}

				if (listObject!= null)
				{

					DB_PopupParentKeyItem newParentKey = PopupkeysCommons.mPopupKeys.get( KeyCommons.mLastTemplateKey.content );

					boolean isLowerCase = true;
					if (PopupkeysCommons.mLastPopupParentKey != null && PopupkeysCommons.mLastPopupParentKey == newParentKey)
					{
						isLowerCase = false;
					}

					PopupkeysCommons.mLastPopupParentKey = newParentKey;


					//Some keys have multiple popup keys, but they're added later.
					//This should only applay to the period key
					boolean assumeMulti = false;

					if (DebugSettings.DEBUG_POPUPS)
					{
						Log.i(LOGTAG, "Initial popups: "+listObject.toString()+", assuming multi?: "+assumeMulti+", lower case?: "+isLowerCase);
					}


					//////////////////////////////////
					// Remove from multiple popups
					//////////////////////////////////

					if (KeyCommons.mLastTemplateKey.content != null)
					{
						//Remove any entry from list of keys with multiple popups.
						//If there are any it will be re-added later
						PopupkeysCommons.mMultipleKeyPopups.remove( KeyCommons.mLastTemplateKey.content );

						if (DebugSettings.DEBUG_POPUPS)
						{
							Log.i(LOGTAG, "Removing Multi-popup symbol: "+KeyCommons.mLastTemplateKey.content +", hash: "+Integer.toHexString(KeyCommons.mLastTemplateKey.content.hashCode() ));
						}
					}

					////////////////////
					//Insert shortcuts
					////////////////////

					//If we need to remove existing popup keys, perform the whole shazzam a second time here.
					if (PopupkeysCommons.mLastPopupParentKey != null && PopupkeysCommons.mLastPopupParentKey.get_delete_existing())
					{
						ArrayList<String> replacementStrings = new ArrayList<>();
						PopupkeysMethods.handlePopupkeyInitialInsert(replacementStrings, isLowerCase, assumeMulti);
						listObject = replacementStrings;
						param.setResult(listObject);
					}
					else if (PopupkeysCommons.mLastPopupParentKey != null)
					{
						ArrayList<String> replacementStrings = new ArrayList<>();
						replacementStrings.addAll(listObject);
						PopupkeysMethods.handlePopupkeyInitialInsert(replacementStrings, isLowerCase, assumeMulti);
						listObject = replacementStrings;
						param.setResult(listObject);
					}
					else
					{
						if (DebugSettings.DEBUG_POPUPS)
						{
							Log.i(LOGTAG, "Skipping insert because last popup parent key null");
						}
					}

					if (DebugSettings.DEBUG_POPUPS)
					{
						Log.i(LOGTAG, "Initial insert: "+listObject.toString());
					}

					/////////////////////////
					//Add to multi-popups
					/////////////////////////

					if (listObject.size() > 1 || assumeMulti)
					{
						if (DebugSettings.DEBUG_POPUPS)
						{
							Log.i(LOGTAG, "Adding to multi-popup keys: "+ KeyCommons.mLastTemplateKey.content );

							Log.i(LOGTAG, "Hash is: "+Integer.toHexString(KeyCommons.mLastTemplateKey.content.hashCode()) ) ;
						}

						//Log.i(LOGTAG, "Adding to multi-popup: "+sLastSymbolDefined);
						PopupkeysCommons.mMultipleKeyPopups.add( KeyCommons.mLastTemplateKey.content );
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

				PopupkeysCommons.mLastInitialPopupkeyList = listObject;



				//PopupkeysMethods.handlePopupkeyInitialInsert((List<String>)param.getResult(), sLastSymbolDefined, false);
			}
		});
	}

	public static XC_MethodHook.Unhook hookButtonOrder() throws NoSuchFieldException
	{
		{
			return XposedBridge.hookMethod(btSubClass_getProperOrderMethod, new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{


					if (DebugSettings.DEBUG_POPUPS)
					{

						if ( KeyCommons.mLastTemplateKey != null)
						{
							Log.i(LOGTAG, "Popup order key: "+KeyCommons.mLastTemplateKey.content );
						}


					}

					try
					{
						if (PopupkeysCommons.mLastPopupParentKey != null)
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


							boolean isUpperCase = !KeyCommons.mLastTemplateKey.content.equalsIgnoreCase(PopupkeysCommons.mLastOrderSymbol);
							PopupkeysCommons.mLastOrderSymbol = KeyCommons.mLastTemplateKey.content;

							//Grab a copy of the primary popup, if we need it.
							String primaryPopup = outputKeys.get(leftCount);
							DB_PopupKeyItem primaryPopupItem = null;

							boolean replacePrimary = false;
							for (DB_PopupKeyItem item : PopupkeysCommons.mLastPopupParentKey.get_items())
							{
								if (item.get_insertIndex() == 0)
								{
									primaryPopupItem =  item;
									replacePrimary = true;

									break;
								}
							}
							if (!replacePrimary)
								outputKeys.remove(leftCount);


							//Remove all our dummy keys
							int removedCount = 0;
							for (int i = 0; i < outputKeys.size(); i++)
							{
								//Log.i(LOGTAG, "Checking dummy: "+outputKeys.get(i));

								if (outputKeys.get(i).toUpperCase().startsWith(PopupkeysCommons.LOWER_CASE_KEY))
								{
									isUpperCase = false;
									outputKeys.remove(i);
									i--;
									removedCount++;
								}
								else if (outputKeys.get(i).toUpperCase().startsWith(PopupkeysCommons.UPPER_CASE_KEY))
								{
									isUpperCase = true;
									outputKeys.remove(i);
									i--;
									removedCount++;
								}
							}

							if (removedCount == 0)
							{
								//Must have been a key that had popups added later
								//Will cause problems if the user adds a duplicate of an existing key

								//Should only happen with period key, doesn't have case
								isUpperCase = false;

								for (int i = 0; i < outputKeys.size(); i++)
								{
									String outputKey = outputKeys.get(i).toLowerCase();

									for (PopupKeyItem item : PopupkeysCommons.mLastPopupParentKey.get_items())
									{
										if ( item.get_popupLower().toLowerCase().equals(outputKey) )
										{
											outputKeys.remove(i);
											i--;
										}
									}
								}

							}

							if (DebugSettings.DEBUG_POPUPS)
							{
								Log.i(LOGTAG, "Dummy removed: "+outputKeys);
							}


							if (replacePrimary && primaryPopupItem != null)
							{
								primaryPopup = isUpperCase ? PopupkeysCommons.validatePopupString(primaryPopupItem.get_popupUpper(), primaryPopupItem.get_popupLower().toUpperCase())
															:PopupkeysCommons.validatePopupString(primaryPopupItem.get_popupLower(), "x");
							}

							//If there is no existing primary, and we are not adding one,
							//we should not re-add it below
							boolean primaryNotPresent = primaryPopup.toUpperCase().startsWith(PopupkeysCommons.EITHER_CASE_KEY);


							//Insert all our keys, starting from the lowest
							//They was order this way when loaded from database.
							for (DB_PopupKeyItem item : PopupkeysCommons.mLastPopupParentKey.get_items())
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
								insertString = isUpperCase ? PopupkeysCommons.validatePopupString(item.get_popupUpper(), item.get_popupLower().toUpperCase())
										: PopupkeysCommons.validatePopupString(item.get_popupLower(), "x");

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


				if ( Hooks.popupHooks_cancel.isRequirementsMet() )
				{
					Hooks.popupHooks_cancel.add( hookExtrasKeysPopupRunnable() );
				}

				if ( Hooks.popupHooks_read.isRequirementsMet() )
				{
					//Hooks.popupHooks_read.addAll( hookPopupsInsertion() );


					if ( Hooks.popupHooks_modify.isRequirementsMet() )
					{
						Hooks.popupHooks_modify.add( hookSymbolsA() );
						Hooks.popupHooks_modify.add( hookButtonOrder() );

						Settings.addOnSettingsUpdatedListener(new Settings.OnSettingsUpdatedListener()
						{
							@Override
							public void OnSettingsUpdated()
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
		catch ( Throwable ex)
		{
			Hooks.popupHooks_read.invalidate(ex, "Failed to hook");
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
