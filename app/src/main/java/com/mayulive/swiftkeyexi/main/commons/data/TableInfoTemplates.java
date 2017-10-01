package com.mayulive.swiftkeyexi.main.commons.data;

import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryShortcutItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.commons.data.DB_KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;
import com.mayulive.swiftkeyexi.database.TableInfo;

/**
 * Created by Roughy on 12/12/2016.
 */

public class TableInfoTemplates
{
	protected static final String EMOJI_KEYBOARD_PANEL_TABLE_NAME = "emoji_keyboard_panel";
	protected static final String EMOJI_DICTIONARY_PANEL_TABLE_NAME = "emoji_dictionary_panel";
	protected static final String DICTIONARY_SHORTCUT_TABLE_NAME = "dictionary_shortcut";

	protected static final String MODIFIER_KEY_TABLE_NAME = "modifier_key";

	protected static final String RECENT_EMOJI_TABLE_NAME = "recent_emoji";

	protected static final String POPUP_KEY_TABLE_NAME = "popup_key";


	protected static final String ADDITIONAL_SHIFT_KEYS_TABLE_NAME = "shift_keys";
	protected static final String ADDITIONAL_DELETE_KEYS_TABLE_NAME = "delete_keys";
	protected static final String ADDITIONAL_SYMBOL_KEYS_TABLE_NAME = "symbol_keys";

	protected static final String HOTKEY_MENU_ITEMS_TABLE_NAME = "hotkey_menu_items";


	public static TableInfo MODIFIER_KEY_TABLE_INFO = new TableInfo(
			new DB_ModifierKeyItem(),
			DB_ModifierKeyItem.PROJECTION,
			DB_ModifierKeyItem.DEFINITION,
			MODIFIER_KEY_TABLE_NAME
	);

	public static TableInfo EMOJI_KEYBOARD_PANEL_TABLE_INFO = new TableInfo(
			new DB_EmojiPanelItem(),
			DB_EmojiPanelItem.PROJECTION,
			DB_EmojiPanelItem.DEFINITION,
			EMOJI_KEYBOARD_PANEL_TABLE_NAME
	);

	public static TableInfo EMOJI_DICTIONARY_PANEL_TABLE_INFO = new TableInfo(
			new DB_EmojiPanelItem(),
			DB_EmojiPanelItem.PROJECTION,
			DB_EmojiPanelItem.DEFINITION,
			EMOJI_DICTIONARY_PANEL_TABLE_NAME
	);

	public static TableInfo DICTIONARY_SHORTCUT_TABLE_INFO = new TableInfo(
			new DB_DictionaryShortcutItem(),
			DB_DictionaryShortcutItem.PROJECTION,
			DB_DictionaryShortcutItem.DEFINITION,
			DICTIONARY_SHORTCUT_TABLE_NAME
	);

	public static TableInfo RECENT_EMOJI_TABLE_INFO = new TableInfo(
			new DB_EmojiItem(),
			DB_EmojiItem.PROJECTION,
			DB_EmojiItem.DEFINITION,
			RECENT_EMOJI_TABLE_NAME
	);

	public static TableInfo POPUP_KEY_TABLE_INFO = new TableInfo(
			new DB_PopupParentKeyItem(),
			DB_PopupParentKeyItem.PROJECTION,
			DB_PopupParentKeyItem.DEFINITION,
			POPUP_KEY_TABLE_NAME
	);

	public static TableInfo ADDITIONAL_SHIFT_KEYS_TABLE_INFO = new TableInfo(
			new DB_KeyDefinition(),
			DB_KeyDefinition.PROJECTION,
			DB_KeyDefinition.DEFINITION,
			ADDITIONAL_SHIFT_KEYS_TABLE_NAME
	);

	public static TableInfo ADDITIONAL_DELETE_KEYS_TABLE_INFO = new TableInfo(
			new DB_KeyDefinition(),
			DB_KeyDefinition.PROJECTION,
			DB_KeyDefinition.DEFINITION,
			ADDITIONAL_DELETE_KEYS_TABLE_NAME
	);

	public static TableInfo ADDITIONAL_SYMBOL_KEYS_TABLE_INFO = new TableInfo(
			new DB_KeyDefinition(),
			DB_KeyDefinition.PROJECTION,
			DB_KeyDefinition.DEFINITION,
			ADDITIONAL_SYMBOL_KEYS_TABLE_NAME
	);

	public static TableInfo HOTKEY_MENU_ITEMS_TABLE_INFO = new TableInfo(
			new DB_HotkeyMenuItem(),
			DB_HotkeyMenuItem.PROJECTION,
			DB_HotkeyMenuItem.DEFINITION,
			HOTKEY_MENU_ITEMS_TABLE_NAME
	);

}
