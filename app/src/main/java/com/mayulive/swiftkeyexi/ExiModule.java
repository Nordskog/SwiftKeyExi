package com.mayulive.swiftkeyexi;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.main.commons.data.DB_HotkeyMenuItem;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.commons.data.DB_KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.main.emoji.data.FancyEmojiPanelTemplates;
import com.mayulive.swiftkeyexi.main.keyboard.HotkeyPanel;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelTemplates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.ADDITIONAL_DELETE_KEYS_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.ADDITIONAL_SHIFT_KEYS_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.ADDITIONAL_SYMBOL_KEYS_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.DICTIONARY_SHORTCUT_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO;
import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.HOTKEY_MENU_ITEMS_TABLE_INFO;
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
		KEYS,
		HOTKEY_MENU_ITEM
	}

	public static String getLogTag(Class thiz)
	{
		return "Exi/"+thiz.getSimpleName();
	}

	public static void initialize(Context context, WrappedDatabase db)
	{
		clear(db);
		loadDefaults(context, db);
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
			case HOTKEY_MENU_ITEM:
			{
				DatabaseMethods.clearTable( db, HOTKEY_MENU_ITEMS_TABLE_INFO);
				break;
			}
		}
	}

	public static boolean needsEmojiUpdate(int currentEmoji)
	{
		int currentSDK = Build.VERSION.SDK_INT;

		//If emoji are below N, and we are currently above M, nuke things.
		if (currentEmoji < Build.VERSION_CODES.N && currentSDK > Build.VERSION_CODES.M)
		{
			return true;
		}

		return false;
	}

	//Deletes and re-created the emoji panels, including new emoji and panels
	//Returns the version of the new emoji
	public static int update(Context context, WrappedDatabase db)
	{
		removeEmoji(db);
		loadDefaults(context, db, ModuleDatabaseType.EMOJI_KEYBOARD);
		loadDefaults(context, db, ModuleDatabaseType.EMOJI_DICTIONARY);

		return getEmojiVersionForSDK();
	}

	public static int getEmojiVersionForSDK()
	{
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M)
			return Build.VERSION_CODES.M;
		else
			return Build.VERSION_CODES.N;
	}

	public static ArrayList<DB_EmojiPanelItem> getEmojiTemplatesForSDK(int sdk)
	{
		switch(sdk)
		{
			case Build.VERSION_CODES.N:
				return FancyEmojiPanelTemplates.getEmojiPanelTemplates();
			default:
				return EmojiPanelTemplates.getEmojiPanelTemplates();
		}
	}

	public static void removeEmoji(WrappedDatabase db)
	{
		//The template panels can't be modified, and even have a key set, so they'll be fine.
		//The editable panels are a bit more of a headache, and we have to be careful.
		TableList<DB_EmojiPanelItem> dictionaryPanels = new TableList<>(db, TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO);
		TableList<DB_EmojiPanelItem> keyboardPanels = new TableList<>(db, TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO);

		int templateCount = 0;
		int deleteCount = 0;
		//For each existing template panel whose source is app, find any similar looking panels and remove them.
		ListIterator<DB_EmojiPanelItem> templateIterator = dictionaryPanels.listIterator();
		while(templateIterator.hasNext())
		{
			DB_EmojiPanelItem templatePanel  =templateIterator.next();
			if (templatePanel.get_source() == EmojiPanelItem.PANEL_SOURCE.TEMPLATE)
			{
				templateCount++;

				ListIterator<DB_EmojiPanelItem> keyboardIterator = keyboardPanels.listIterator();

				while(keyboardIterator.hasNext())
				{
					DB_EmojiPanelItem keyboardPanel = keyboardIterator.next();
					int countDiff = Math.abs( templatePanel.get_items().size() - keyboardPanel.get_items().size() );
					if (countDiff <= 5)
					{
						//User may have added a few extra emoji at the bottom while testing stuff.
						//Removing these is acceptable
						//Compare all but the last... 10 characters?
						int diffCount = 0;
						for (int i = 0; i < keyboardPanel.get_items().size() - 10; i++)
						{
							if ( !keyboardPanel.get_items().get(i).get_text().equals( templatePanel.get_items().get(i) .get_text() ) )
							{
								diffCount++;
								if (diffCount > 5)
									break;
							}
						}

						if (diffCount < 5)
						{
							keyboardIterator.remove();
							deleteCount++;
						}
					}
				}

				//Finally delete the template
				templateIterator.remove();

			}

		}

		//To make life easier, reset recents too
		ListIterator<DB_EmojiPanelItem> keyboardIterator = keyboardPanels.listIterator();
		while(keyboardIterator.hasNext())
		{
			DB_EmojiPanelItem keyboardPanel = keyboardIterator.next();
			if(keyboardPanel.get_source() == EmojiPanelItem.PANEL_SOURCE.RECENTS)
			{
				keyboardIterator.remove();
				break;
			}
		}

		//If we didn't delete any existing panels, then user has either modified everything or
		//deleted all the panels. Probably shouldn't add anytihng. Let them do it themselves.
		int panelCountDiff = Math.abs( templateCount -deleteCount);
		if (panelCountDiff < 2)
		{

		}
	}

	public static void clear(WrappedDatabase db)
	{
		for (ModuleDatabaseType type : ModuleDatabaseType.values())
		{
			clear(db,type);
		}
	}

	public static void loadDefaults(Context context, WrappedDatabase db)
	{
		for (ModuleDatabaseType type : ModuleDatabaseType.values())
		{
			loadDefaults(context, db,type);
		}
	}


	public static void loadDefaults(final Context context, WrappedDatabase db, ModuleDatabaseType type )
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
				TableList<DB_EmojiPanelItem> dictionaryPanels = new TableList<>(db, TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO);



				ArrayList<DB_EmojiPanelItem> keyboardTemplates = getEmojiTemplatesForSDK(getEmojiVersionForSDK());
				Collections.reverse(keyboardTemplates);
				for (DB_EmojiPanelItem item : keyboardTemplates)
					dictionaryPanels.add(0,item);

				//Recents is added as the first item, but position is configurable. Update indices here.
				{
					int iterator = 0;
					for (DB_EmojiPanelItem item : dictionaryPanels)
					{
						item.set_index(iterator);
						iterator++;
					}
				}

				DatabaseMethods.updateAllItems( db, dictionaryPanels, EMOJI_DICTIONARY_PANEL_TABLE_INFO,true);

				break;
			}
			case EMOJI_KEYBOARD:
			{
				TableList<DB_EmojiPanelItem> keyboardPanels = new TableList<>(db, TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO);
				ArrayList<DB_EmojiPanelItem> keyboardTemplates =  getEmojiTemplatesForSDK(getEmojiVersionForSDK());
				Collections.reverse(keyboardTemplates);
				for (DB_EmojiPanelItem item : keyboardTemplates)
					keyboardPanels.add(0,item);

				//Once added to the keyboard the user should be free to do whatever, so mark them as user.
				for (DB_EmojiPanelItem item : keyboardPanels)
					item.set_source(EmojiPanelItem.PANEL_SOURCE.USER);

				//Additionally, the keyboard items should contain a special recents panel
				//This panel will not be editable by the user. Should probably have some indicator.
				keyboardPanels.add(0, EmojiPanelTemplates.getRecentsPanelItem());


				//Recents is added as the first item, but position is configurable. Update indices here.
				{
					int iterator = 0;
					for (DB_EmojiPanelItem item : keyboardPanels)
					{
						item.set_index(iterator);
						iterator++;
					}
				}

				DatabaseMethods.updateAllItems( db, keyboardPanels, EMOJI_KEYBOARD_PANEL_TABLE_INFO,true);

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
					keys.add( new DB_KeyDefinition("", KeyType.SWITCH_LAYOUT));
					DatabaseMethods.updateAllItems( db, keys, ADDITIONAL_SYMBOL_KEYS_TABLE_INFO,true);
				}

				{
					keys.clear();
					keys.add( new DB_KeyDefinition("", KeyType.DELETE));
					DatabaseMethods.updateAllItems( db, keys, ADDITIONAL_DELETE_KEYS_TABLE_INFO,true);
				}

				{
					keys.clear();
					keys.add( new DB_KeyDefinition("", KeyType.SHIFT));
					DatabaseMethods.updateAllItems( db, keys, ADDITIONAL_SHIFT_KEYS_TABLE_INFO,true);
				}

				break;
			}
			case HOTKEY_MENU_ITEM:
			{
				List<DB_HotkeyMenuItem> items = new ArrayList<DB_HotkeyMenuItem>()
				{{
					//new HotkeyMenuItem ("↑", 	0.5f),
					add(new DB_HotkeyMenuItem (KeyboardInteraction.TextAction.getShortTextRepresentation(context, KeyboardInteraction.TextAction.SELECT_ALL) , KeyboardInteraction.TextAction.SELECT_ALL, 	1f, 0));
					add(new DB_HotkeyMenuItem (KeyboardInteraction.TextAction.getShortTextRepresentation(context, KeyboardInteraction.TextAction.COPY), KeyboardInteraction.TextAction.COPY, 	1f, 1));
					add(new DB_HotkeyMenuItem (KeyboardInteraction.TextAction.getShortTextRepresentation(context, KeyboardInteraction.TextAction.PASTE), KeyboardInteraction.TextAction.PASTE, 	1f, 2));
					add(new DB_HotkeyMenuItem (KeyboardInteraction.TextAction.getShortTextRepresentation(context, KeyboardInteraction.TextAction.GO_TO_END), 	KeyboardInteraction.TextAction.GO_TO_END, 	0.5f, 3));
				}};

				DatabaseMethods.updateAllItems( db, items, HOTKEY_MENU_ITEMS_TABLE_INFO,true);

				break;
			}
		}
	}





}
