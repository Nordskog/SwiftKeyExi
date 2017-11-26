package com.mayulive.swiftkeyexi.xposed.popupkeys;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;
import com.mayulive.swiftkeyexi.main.popupkeys.data.PopupKeyItem;
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

	public static void handlePopupkeyInitialInsert(List<String> listObject, boolean isLowerCase, boolean assumeMulti)
	{
		//Do not add here if we won't be able to re-order them later
		if ( Hooks.popupHooks_modify.isRequirementsMet() )
		{
			if (PopupkeysCommons.mLastPopupParentKey != null)
			{
				if (listObject != null)
				{
					//If there are no existing popups, and we only add a single popup,
					//the hookButtonOrder hook never gets called, so we should add
					//the actual text of the shortcut instead of our dummy items
					//Some keys only have a single popup at this point, but get more later, such as period.
					if (listObject.size() < 1 && PopupkeysCommons.mLastPopupParentKey.get_items().size() < 2 && !assumeMulti)
					{
						for (int i = 0; i < PopupkeysCommons.mLastPopupParentKey.get_items().size(); i++)
						{
							PopupKeyItem item = PopupkeysCommons.mLastPopupParentKey.get_items().get(i);

							if (isLowerCase)
							{
								if (DebugSettings.DEBUG_POPUPS)
									Log.i(LOGTAG, "Adding popup: "+  PopupkeysCommons.validatePopupString(item.get_popupLower(), "x") );

								listObject.add( PopupkeysCommons.validatePopupString(item.get_popupLower(), "x") );
							}
							else
							{
								if (DebugSettings.DEBUG_POPUPS)
									Log.i(LOGTAG, "Adding popup: "+ PopupkeysCommons.validatePopupString(item.get_popupUpper(), item.get_popupLower().toUpperCase()));

								listObject.add(  PopupkeysCommons.validatePopupString(item.get_popupUpper(), item.get_popupLower().toUpperCase())  );
							}
						}
					}
					else
					{
						for (int i = 0; i < PopupkeysCommons.mLastPopupParentKey.get_items().size(); i++)
						{
							if (DebugSettings.DEBUG_POPUPS)
							{
								Log.i(LOGTAG, "Adding popup: "+  (isLowerCase ? PopupkeysCommons.LOWER_CASE_KEY : PopupkeysCommons.UPPER_CASE_KEY ) + i );
							}

							if (isLowerCase)
								listObject.add(PopupkeysCommons.LOWER_CASE_KEY + i);
							else
								listObject.add(PopupkeysCommons.UPPER_CASE_KEY + i);
						}
					}
				}
			}
		}
		else if (DebugSettings.DEBUG_POPUPS)
		{
			Log.i(LOGTAG, "Popup modify requirements not met, or not last symbol");
		}


	}



}
