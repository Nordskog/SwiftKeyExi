package com.mayulive.swiftkeyexi;

import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.commons.data.DB_KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelTemplates;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;

import java.util.ArrayList;

import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.ADDITIONAL_DELETE_KEYS_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.ADDITIONAL_SHIFT_KEYS_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.ADDITIONAL_SYMBOL_KEYS_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.DICTIONARY_SHORTCUT_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.MODIFIER_KEY_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.POPUP_KEY_TABLE_INFO;

/**
 * Created by Roughy on 3/29/2017.
 */

public class ExiModule
{
	//Anything that references the package name should use this constant
	public static String PACKAGE = BuildConfig.APPLICATION_ID;

	public static String SWIFTKEY_PACKAGE_NAME = "com.touchtype.swiftkey";
	public static String SWIFTKEY_BETA_PACKAGE_NAME = "com.touchtype.swiftkey.beta";

	public enum ModuleDatabaseType
	{
		DICTIONARY,
		EMOJI_DICTIONARY,
		EMOJI_KEYBOARD,
		HOTKEY,
		POPUP,
		KEYS
	}

	public static String getLogTag(Class thiz)
	{
		return "Exi/"+thiz.getSimpleName();
	}

	public static void initialize(WrappedDatabase db)
	{
		clear(db);
		loadDefaults(db);
	}

	public static void clear(WrappedDatabase db, ModuleDatabaseType type)
	{
		switch(type)
		{
			case DICTIONARY:
			{
				DatabaseMethods.clearTable( db, DICTIONARY_SHORTCUT_TABLE_INFO);
				break;
			}
			case EMOJI_DICTIONARY:
			{
				DatabaseMethods.clearTable( db, EMOJI_DICTIONARY_PANEL_TABLE_INFO);
				break;
			}
			case EMOJI_KEYBOARD:
			{
				DatabaseMethods.clearTable( db, EMOJI_KEYBOARD_PANEL_TABLE_INFO);
				break;
			}
			case HOTKEY:
			{
				DatabaseMethods.clearTable( db, MODIFIER_KEY_TABLE_INFO);
				break;
			}
			case POPUP:
			{
				DatabaseMethods.clearTable( db, POPUP_KEY_TABLE_INFO);
				break;
			}
			case KEYS:
			{
				DatabaseMethods.clearTable( db, ADDITIONAL_SYMBOL_KEYS_TABLE_INFO);
				DatabaseMethods.clearTable( db, ADDITIONAL_DELETE_KEYS_TABLE_INFO);
				DatabaseMethods.clearTable( db, ADDITIONAL_SHIFT_KEYS_TABLE_INFO);

				break;
			}
		}
	}

	public static void clear(WrappedDatabase db)
	{
		for (ModuleDatabaseType type : ModuleDatabaseType.values())
		{
			clear(db,type);
		}
	}

	public static void loadDefaults(WrappedDatabase db)
	{
		for (ModuleDatabaseType type : ModuleDatabaseType.values())
		{
			loadDefaults(db,type);
		}
	}


	public static void loadDefaults(WrappedDatabase db, ModuleDatabaseType type )
	{
		switch(type)
		{
			case DICTIONARY:
			{
				//Nothing
				break;
			}
			case EMOJI_DICTIONARY:
			{
				ArrayList<DB_EmojiPanelItem> dictionaryTemplates = EmojiPanelTemplates.getEmojiPanelTemplates();
				DatabaseMethods.updateAllItems( db, dictionaryTemplates, EMOJI_DICTIONARY_PANEL_TABLE_INFO,true);
				break;
			}
			case EMOJI_KEYBOARD:
			{
				//Once added to the keyboard the user should be free to do whatever, so mark them as user.
				ArrayList<DB_EmojiPanelItem> keyboardTemplates = EmojiPanelTemplates.getEmojiPanelTemplates();
				for (DB_EmojiPanelItem item : keyboardTemplates)
					item.set_source(EmojiPanelItem.PANEL_SOURCE.USER);

				//Additionally, the keyboard items should contain a special recents panel
				//This panel will not be editable by the user. Should probably have some indicator.
				keyboardTemplates.add(0, EmojiPanelTemplates.getRecentsPanelItem());

				//Recents is added as the first item, but position is configurable. Update indices here.
				{
					int iterator = 0;
					for (DB_EmojiPanelItem item : keyboardTemplates)
					{
						item.set_index(iterator);
						iterator++;
					}
				}

				DatabaseMethods.updateAllItems( db, keyboardTemplates, EMOJI_KEYBOARD_PANEL_TABLE_INFO,true);
				break;
			}
			case HOTKEY:
			{
				ArrayList<DB_ModifierKeyItem> modifierKeys = new ArrayList<DB_ModifierKeyItem>();
				modifierKeys.add( new DB_ModifierKeyItem(-1, "a", KeyboardInteraction.TextAction.SELECT_ALL));
				modifierKeys.add( new DB_ModifierKeyItem(-1, "x", KeyboardInteraction.TextAction.CUT));
				modifierKeys.add( new DB_ModifierKeyItem(-1, "c", KeyboardInteraction.TextAction.COPY));
				modifierKeys.add( new DB_ModifierKeyItem(-1, "v", KeyboardInteraction.TextAction.PASTE));
				DatabaseMethods.updateAllItems( db, modifierKeys, MODIFIER_KEY_TABLE_INFO,true);

				break;
			}
			case POPUP:
			{
				//None
				break;
			}
			case KEYS:
			{
				ArrayList<DB_KeyDefinition> keys = new ArrayList<DB_KeyDefinition>();
				{
					keys.add( new DB_KeyDefinition("", KeyCommons.KeyType.SWITCH_LAYOUT));
					DatabaseMethods.updateAllItems( db, keys, ADDITIONAL_SYMBOL_KEYS_TABLE_INFO,true);
				}

				{
					keys.clear();
					keys.add( new DB_KeyDefinition("", KeyCommons.KeyType.DELETE));
					DatabaseMethods.updateAllItems( db, keys, ADDITIONAL_DELETE_KEYS_TABLE_INFO,true);
				}

				{
					keys.clear();
					keys.add( new DB_KeyDefinition("", KeyCommons.KeyType.SHIFT));
					DatabaseMethods.updateAllItems( db, keys, ADDITIONAL_SHIFT_KEYS_TABLE_INFO,true);
				}

				break;
			}
		}
	}





}
