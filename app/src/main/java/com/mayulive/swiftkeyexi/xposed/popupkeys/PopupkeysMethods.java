package com.mayulive.swiftkeyexi.xposed.popupkeys;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;

import java.util.List;

/**
 * Created by Roughy on 7/3/2017.
 */

public class PopupkeysMethods
{

	private static String LOGTAG = ExiModule.getLogTag(PopupkeysMethods.class);

	public static void handlePopupkeyInitialInsert(List<String> listObject, String lastSymbol, boolean ignoreCase)
	{
		if (PopupkeysCommons.mLastInitialPopupkeyList == listObject)
		{
			//When a key starts off with no popups, or
			//with the same popups for upper and lower case,
			//they share the same list. I currently cannot change this.
			//We do want to avoid adding the same popups twice though.
			return;

		}
		PopupkeysCommons.mLastInitialPopupkeyList = listObject;

		if (KeyCommons.sLastSymbolDefined != null)
		{
			//Remove any entry from list of keys with multiple popups.
			//If there are any it will be re-added later
			PopupkeysCommons.mMultipleKeyPopups.remove( lastSymbol );

			if (DebugSettings.DEBUG_POPUPS)
			{
				Log.i(LOGTAG, "Removing Multi-popup symbol: "+KeyCommons.sLastSymbolDefined +", hash: "+Integer.toHexString( KeyCommons.sLastSymbolDefined.hashCode() ));
			}

		}
		else if (DebugSettings.DEBUG_POPUPS)
		{
			Log.i(LOGTAG, "sLastSymbolDefined was null");
		}

		//Do not add here if we won't be able to re-order them later
		if ( Hooks.popupHooks_modify.isRequirementsMet() )
		{

			boolean isLowerCase = lastSymbol.equalsIgnoreCase(PopupkeysCommons.mLastASymbol);
			if (!ignoreCase)
			{
				PopupkeysCommons.mLastASymbol = KeyCommons.sLastSymbolDefined;
			}
			else
			{
				isLowerCase = true;
			}


			//Made some changes that broke stuff, quick fix.
			{
				PopupkeysCommons.mLastPopupParentKey = PopupkeysCommons.mPopupKeys.get( KeyCommons.sLastSymbolDefined);
			}



			if (PopupkeysCommons.mLastPopupParentKey != null)
			{
				if (listObject != null)
				{
					//If there are no existing popups, and we only add a single popup,
					//the hookButtonOrder hook never gets called, so we should add
					//the actual text of the shortcut instead of our dummy items
					if (listObject.size() < 1 && PopupkeysCommons.mLastPopupParentKey.get_items().size() < 2)
					{
						if (isLowerCase)
						{
							for (int i = 0; i < PopupkeysCommons.mLastPopupParentKey.get_items().size(); i++)
							{
								//Random string
								listObject.add(PopupkeysCommons.mLastPopupParentKey.get_items().get(i).get_popupLower());
								//listObject.add(PopupkeysCommons.NULLCHAR+"2");
							}
						}
					}
					else
					{
						//Unless we supply different lists for upper and lower cases,
						//it will only get called once later on.
						//TODO fix upper / lower case popups.
						if (isLowerCase)
						{
							for (int i = 0; i < PopupkeysCommons.mLastPopupParentKey.get_items().size(); i++)
							{
								//Random string
								listObject.add(PopupkeysCommons.NULLCHAR + i);
								//listObject.add(PopupkeysCommons.NULLCHAR+"2");
							}
						}
										/*else
										{
											for (int i = 0 ; i < PopupkeysCommons.mLastPopupParentKey.size(); i++)
											{
												//Random string
												listObject.add(PopupkeysCommons.NULLCHAR+(100+i));
												//listObject.add(PopupkeysCommons.NULLCHAR+"4");
											}
										}*/

					}
				}
				else
				{
					//Log.e("###", "Tried to add popups but couldn't identify field.");
				}
			}
		}
		else if (DebugSettings.DEBUG_POPUPS)
		{
			Log.i(LOGTAG, "Popup modify requirements not met, or not last symbol");
		}


	}



}
