package com.mayulive.swiftkeyexi.xposed.selection;

import com.mayulive.swiftkeyexi.main.commons.data.DB_HotkeyMenuItem;
import com.mayulive.swiftkeyexi.main.commons.data.DB_KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.database.WrappedProvider;
import com.mayulive.swiftkeyexi.main.keyboard.HotkeyPanel;
import com.mayulive.swiftkeyexi.providers.Provider;
import com.mayulive.swiftkeyexi.settings.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.ADDITIONAL_DELETE_KEYS_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.ADDITIONAL_SHIFT_KEYS_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.ADDITIONAL_SYMBOL_KEYS_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.HOTKEY_MENU_ITEMS_TABLE_INFO;
import static com.mayulive.swiftkeyexi.util.ContextUtils.getHookContext;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mModifierKeyActions;

/**
 * Created by Roughy on 6/7/2017.
 */

public class SelectionSetup
{

	public static void populateQuickMenu()
	{
		if (SelectionState.mLastQuickmenuUpdateTime == -1 || SelectionState.mLastQuickmenuUpdateTime < Settings.LAST_QUICKMENU_UPDATE)
		{
			SelectionState.mLastQuickmenuUpdateTime = Settings.LAST_QUICKMENU_UPDATE;
			WrappedProvider dbWrap = Provider.getWrapped(getHookContext());

			SelectionState.mHotkeyMenuItems = new TableList<>(dbWrap, HOTKEY_MENU_ITEMS_TABLE_INFO);
			Collections.sort(SelectionState.mHotkeyMenuItems, new HotkeyPanel.HotkeyMenuItem.HotkeyMenuItemComparator() );
		}

	}

	public static void populateKeys()
	{
		if (SelectionState.mLastAdditionalKeysUpdateTime == -1 || SelectionState.mLastAdditionalKeysUpdateTime < Settings.LAST_ADDITIONAL_KEYS_UPDATE)
		{
			SelectionState.mLastAdditionalKeysUpdateTime = Settings.LAST_ADDITIONAL_KEYS_UPDATE;

			//SHould presist somewhere instead
			WrappedProvider dbWrap = Provider.getWrapped(getHookContext());

			TableList<DB_KeyDefinition> mShiftItems = new TableList<DB_KeyDefinition>(dbWrap, ADDITIONAL_SHIFT_KEYS_TABLE_INFO);
			TableList<DB_KeyDefinition> mDeleteItems = new TableList<DB_KeyDefinition>(dbWrap, ADDITIONAL_DELETE_KEYS_TABLE_INFO);
			TableList<DB_KeyDefinition> mSymbolItems = new TableList<DB_KeyDefinition>(dbWrap, ADDITIONAL_SYMBOL_KEYS_TABLE_INFO);

			{
				SelectionState.mShiftKeyEnums.clear();
				SelectionState.mShiftKeys.clear();

				for (DB_KeyDefinition item : mShiftItems)
				{
					if (item.is(KeyType.SYMBOL))
					{
						SelectionState.mShiftKeys.add(item.getContent());
					}
					else
					{
						SelectionState.mShiftKeyEnums.add(item.getType());
					}

				}
			}

			{
				SelectionState.mDeleteKeyEnums.clear();
				SelectionState.mDeleteKeys.clear();

				for (DB_KeyDefinition item : mDeleteItems)
				{
					if (item.is(KeyType.SYMBOL))
					{
						SelectionState.mDeleteKeys.add(item.getContent());
					}
					else
					{
						SelectionState.mDeleteKeyEnums.add(item.getType());
					}
				}

			}

			{

				SelectionState.mSymbolKeyEnums.clear();
				SelectionState.mSymbolKeys.clear();

				for (DB_KeyDefinition item : mSymbolItems)
				{
					if (item.is(KeyType.SYMBOL))
					{
						SelectionState.mSymbolKeys.add(item.getContent());
					}
					else
					{

						SelectionState.mSymbolKeyEnums.add(item.getType());
					}
				}

			}
		}

	}

	public static void populateActions()
	{
		if (Settings.LAST_HOTKEYS_UPDATE > SelectionState.mLastHotkeyUpdateTime)
		{
			WrappedProvider dbWrap = Provider.getWrapped(getHookContext());
			ArrayList<DB_ModifierKeyItem> modifierKeys =  (ArrayList<DB_ModifierKeyItem>) DatabaseMethods.getAllItems(dbWrap,  TableInfoTemplates.MODIFIER_KEY_TABLE_INFO);

			mModifierKeyActions.clear();

			for (DB_ModifierKeyItem currentKey : modifierKeys)
			{
				mModifierKeyActions.put(currentKey.get_key().toLowerCase(), currentKey.get_textAction());
			}
		}

		SelectionState.mLastHotkeyUpdateTime = Settings.LAST_HOTKEYS_UPDATE;
	}

}
