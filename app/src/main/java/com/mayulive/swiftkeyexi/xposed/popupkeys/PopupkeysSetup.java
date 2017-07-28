package com.mayulive.swiftkeyexi.xposed.popupkeys;

import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupKeyItem;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.database.WrappedProvider;
import com.mayulive.swiftkeyexi.providers.Provider;

import java.util.ArrayList;
import java.util.Collections;

import static com.mayulive.swiftkeyexi.util.ContextUtils.getHookContext;

/**
 * Created by Roughy on 6/8/2017.
 */

public class PopupkeysSetup
{
	protected static void loadPopupKeys()
	{
		WrappedProvider dbWrap = Provider.getWrapped(getHookContext());
		ArrayList<DB_PopupParentKeyItem> popupParents =  (ArrayList<DB_PopupParentKeyItem>) DatabaseMethods.getAllItems(dbWrap,  TableInfoTemplates.POPUP_KEY_TABLE_INFO);

		PopupkeysCommons.mPopupKeyActions.clear();
		PopupkeysCommons.mPopupKeys.clear();

		for (DB_PopupParentKeyItem parentKeyItem : popupParents)
		{
			//Sort ascending by insert index
			Collections.sort(parentKeyItem.get_items(), new DB_PopupKeyItem.DictionaryShortcutKeyIndexComparator());

			//Put in hashmap for easy lookup
			PopupkeysCommons.mPopupKeys.put(parentKeyItem.get_parentKey().toLowerCase(), parentKeyItem);

			/*
			//keys with special actions. Dropped because a major pain, and space-modifier is superior.
			for (DB_PopupKeyItem currentKey : parentKeyItem.get_items())
			{
				//Default actions just insert whatever text is there,
				//don't need to register any actions for them.
				if (currentKey.get_textAction() != KeyboardInteraction.TextAction.DEFAULT)
				{
					mPopupKeyActions.put(currentKey.get_popupLower().toLowerCase(), currentKey.get_textAction());

					//While the action is assigned to a single key, for some stupid reason I still let the user
					//assign a different string for upper/lower case. If these keys differ, we need to add a second
					//key - action mapping.
					if (!currentKey.get_popupLower().equals(currentKey.get_popupUpper()))
					{
						mPopupKeyActions.put(currentKey.get_popupUpper().toLowerCase(), currentKey.get_textAction());
					}
				}
			}
			*/
		}

	}
}
