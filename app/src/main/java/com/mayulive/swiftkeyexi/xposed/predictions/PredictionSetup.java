package com.mayulive.swiftkeyexi.xposed.predictions;

import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryShortcutItem;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.database.WrappedProvider;
import com.mayulive.swiftkeyexi.providers.Provider;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryWordItem;

import java.util.ArrayList;
import java.util.Collections;

import static com.mayulive.swiftkeyexi.util.ContextUtils.getHookContext;

/**
 * Created by Roughy on 6/7/2017.
 */

public class PredictionSetup
{
	public static void loadShortcuts()
	{
		WrappedProvider dbWrap = Provider.getWrapped(getHookContext());
		ArrayList<DB_DictionaryShortcutItem> shortcuts =  (ArrayList<DB_DictionaryShortcutItem>) DatabaseMethods.getAllItems(dbWrap,  TableInfoTemplates.DICTIONARY_SHORTCUT_TABLE_INFO);

		PredictionCommons.mShortcutMap.clear();

		for (DB_DictionaryShortcutItem currentShortcut : shortcuts)
		{
			//For hash-matching purposes we want our keys to always be lower case
			String processedKey = currentShortcut.get_key().toLowerCase();

			Collections.sort(currentShortcut.get_items(), new DB_DictionaryWordItem.DictionaryWordComparatorInverse());
			PredictionCommons.mShortcutMap.put(processedKey, currentShortcut);
		}
	}
}
