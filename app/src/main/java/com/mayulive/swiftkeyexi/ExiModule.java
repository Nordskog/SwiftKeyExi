package com.mayulive.swiftkeyexi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.main.commons.data.DB_HotkeyMenuItem;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.commons.data.DB_KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.main.emoji.data.FancyEmojiPanelTemplates;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

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
	private static String LOGTAG = ExiModule.getLogTag(ExiModule.class);

	//Anything that references the package name should use this constant
	public static String PACKAGE = BuildConfig.APPLICATION_ID;

	public static String SWIFTKEY_PACKAGE_NAME = "com.touchtype.swiftkey";
	public static String SWIFTKEY_BETA_PACKAGE_NAME = "com.touchtype.swiftkey.beta";

	public static final int DISPLAY_EMOJI_VARIANT_FIX_VERSION = 8;
	public static final int OREO_EMOJI_FIX_VERSION = 12;


	public enum ModuleDatabaseType
	{
		DICTIONARY(PreferenceConstants.pref_dictionary_last_update_key),
		EMOJI(PreferenceConstants.pref_emoji_last_update_key),
		HOTKEY(PreferenceConstants.pref_hotkeys_last_update_key),
		POPUP(PreferenceConstants.pref_popup_last_update_key),
		KEYS(PreferenceConstants.pref_additional_keys_last_update_key),
		HOTKEY_MENU_ITEM(PreferenceConstants.pref_hotkeys_last_update_key);

		private String mLastUpdateTimePref;

		ModuleDatabaseType(String lastUpdateTimePref)
		{
			mLastUpdateTimePref = lastUpdateTimePref;
		}

		//Update last update time pref and invaliate at the same time
		public void setUpdateItem(Context context)
		{
			SettingsCommons.updateTimePreference(context, mLastUpdateTimePref);
		}

		public String getTimeUpdatePref()
		{
			return mLastUpdateTimePref;
		}
	}

	public static String getLogTag(Class thiz)
	{
		return "Exi/"+thiz.getSimpleName();
	}

	public static void initialize(Context context, WrappedDatabase db)
	{
		clear(context, db);
		loadDefaults(context, db, Build.VERSION.SDK_INT);
	}

	public static void clear(Context context, WrappedDatabase db, ModuleDatabaseType type)
	{
		switch(type)
		{
			case DICTIONARY:
			{
				DatabaseMethods.clearTable( db, DICTIONARY_SHORTCUT_TABLE_INFO);
				break;
			}
			case EMOJI:
			{
				DatabaseMethods.clearTable( db, EMOJI_KEYBOARD_PANEL_TABLE_INFO);
				DatabaseMethods.clearTable( db, EMOJI_DICTIONARY_PANEL_TABLE_INFO);
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

		type.setUpdateItem(context);
	}

	public static boolean needsEmojiUpdate(Context context, int previousSDK, int previousExiVersionCode)
	{
		SharedPreferences prefs = SettingsCommons.getSharedPreferences(context);

		int currentEmoji = prefs.getInt(PreferenceConstants.status_api_version_emoji,  Build.VERSION_CODES.M );

		FancyEmojiPanelTemplates.EmojiPanelVersion newVersion = FancyEmojiPanelTemplates.EmojiPanelVersion.getFromPref(
			prefs.getString(PreferenceConstants.pref_emoji_force_version_key, "AUTO")
	);


		Log.i(LOGTAG, "Exi version: "+BuildConfig.VERSION_CODE+", previousVersion: "+previousExiVersionCode);
		Log.i(LOGTAG, "Current emoji: "+currentEmoji+", for SDK: "+newVersion.getSdkVersion());
		Log.i(LOGTAG, "Build version: "+Build.VERSION.SDK_INT+", previousSDK: "+previousSDK);

		//If current emoji version differs from new version
		if (currentEmoji != newVersion.getSdkVersion())
			return true;

		//If different version of android
		if ( Build.VERSION.SDK_INT != previousSDK)
			return true;

		//In Exi v8 (Version code) the display-as-emoji variant selector was added to all emoji
		//Update panels need to be refreshed.
		//Android oreo made some changes to fonts.xml that murdered our parser, must be reloaded there.
		if (previousExiVersionCode < DISPLAY_EMOJI_VARIANT_FIX_VERSION
				|| (previousExiVersionCode < OREO_EMOJI_FIX_VERSION && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
			)
			return true;

		//If we haven't added identifiers to the panels yet, better to unify this stuff.
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M)
		{
			boolean hasIdentifiers = prefs.getBoolean(PreferenceConstants.status_emoji_panels_have_identifiers_key, false);
			if (hasIdentifiers)
				return true;
		}

		return false;
	}

	//Deletes and re-created the emoji panels, including new emoji and panels
	//Returns the version of the new emoji
	public static int update(Context context, WrappedDatabase db)
	{
		SharedPreferences prefs = SettingsCommons.getSharedPreferences(context);

		int currentVersion = prefs.getInt(PreferenceConstants.status_api_version_emoji,  Build.VERSION_CODES.M );

		FancyEmojiPanelTemplates.EmojiPanelVersion newVersion = FancyEmojiPanelTemplates.EmojiPanelVersion.getFromPref(
				prefs.getString(PreferenceConstants.pref_emoji_force_version_key, "AUTO")
		);

		//Older versions did not provide identifiers for panels
		//Nougat panels were added afterwards, so they will always have identifiers.
		boolean hasIdentifiers = true;
		if (currentVersion <= Build.VERSION_CODES.M)
		{
			hasIdentifiers = prefs.getBoolean(PreferenceConstants.status_emoji_panels_have_identifiers_key, false);
		}

		//If we are moving from pre_nougat to post_nougat or vice-versa, all existing panels must be removed and defaults restored
		if (currentVersion != newVersion.getSdkVersion())
		{
			Log.i(LOGTAG, "Removing stock panels and replacing with "+newVersion.name());
			removeAllStockPanels(context, db);
			loadDefaults(context, db, ModuleDatabaseType.EMOJI, newVersion.getSdkVersion());
		}
		else
		{
			//Otherwise we're good to just refresh the panels, unless they don't have identifiers yet

			//Refresh with identifiers, otherwise guess existing panels and restore defaults.
			if (hasIdentifiers)
			{
				Log.i(LOGTAG, "Updating from identifiable panels");
				refreshAllPanels(context, db, newVersion);
			}
			else
			{
				Log.i(LOGTAG, "Updating from Marshmallow emoji");
				removeStockMarshmallowEmojiPanels(db,true);
				loadDefaults(context, db, ModuleDatabaseType.EMOJI, newVersion.getSdkVersion());
			}
		}

		return newVersion.getSdkVersion();
	}

	public static void removeStockMarshmallowEmojiPanels(WrappedDatabase db, boolean removeKeyboardPanels)
	{
		//The template panels can't be modified, and even have a key set, so they'll be fine.
		//The editable panels are a bit more of a headache, and we have to be careful.
		TableList<DB_EmojiPanelItem> dictionaryPanels = new TableList<>(db, TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO);
		TableList<DB_EmojiPanelItem> keyboardPanels = new TableList<>(db, TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO);

		//For each existing template panel whose source is app, find any similar looking panels and remove them.
		ListIterator<DB_EmojiPanelItem> templateIterator = dictionaryPanels.listIterator();
		while(templateIterator.hasNext())
		{
			DB_EmojiPanelItem templatePanel  =templateIterator.next();
			if (templatePanel.get_source() == EmojiPanelItem.PANEL_SOURCE.TEMPLATE)
			{

				Set<String> templateEmoji = new HashSet<>();
				for (DB_EmojiItem item : templatePanel.get_items())
				{
					templateEmoji.add(item.get_text());
				}

				//Find all keybaord panels that match this template.
				if (removeKeyboardPanels)
				{
					ListIterator<DB_EmojiPanelItem> keyboardIterator = keyboardPanels.listIterator();

					while(keyboardIterator.hasNext())
					{
						DB_EmojiPanelItem keyboardPanel = keyboardIterator.next();
						int countDiff = Math.abs( templatePanel.get_items().size() - keyboardPanel.get_items().size() );

						//Use a quick count diff to weed out most of the false hits
						if (countDiff <= 15)
						{
							//Use hashmap to determine similarity

							//User may have added a few extra emoji at the bottom while testing stuff.
							//Removing these is acceptable
							//Compare all but the last... 10 characters?
							int containedCount = 0;
							for (DB_EmojiItem item : keyboardPanel.get_items())
							{
								if (templateEmoji.contains(item.get_text()))
									containedCount++;

							}

							//Consider match if keyboard contains at least 80% of emoji in template
							if (containedCount >= templateEmoji.size() * 0.8)
							{
								keyboardIterator.remove();
							}
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
	}

	private static void removeAllStockPanels(Context context, WrappedDatabase db)
	{
		SharedPreferences prefs = SettingsCommons.getSharedPreferences(context);
		int emojiVersion = prefs.getInt(PreferenceConstants.status_api_version_emoji,  Build.VERSION_CODES.M );
		boolean hasIdentifiers = true;
		if (emojiVersion <= Build.VERSION_CODES.M)
		{
			hasIdentifiers = prefs.getBoolean(PreferenceConstants.status_emoji_panels_have_identifiers_key, false);
		}

		if (hasIdentifiers)
		{
			TableList<DB_EmojiPanelItem> dictionaryPanels = new TableList<>(db, TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO);
			TableList<DB_EmojiPanelItem> keyboardPanels = new TableList<>(db, TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO);

			ListIterator<DB_EmojiPanelItem> templateIterator = dictionaryPanels.listIterator();
			while(templateIterator.hasNext())
			{
				DB_EmojiPanelItem item = templateIterator.next();
				if (item.get_panel_identifier() != -1)
				{
					templateIterator.remove();
				}
			}

			ListIterator<DB_EmojiPanelItem> keyboardIterator = keyboardPanels.listIterator();
			while(keyboardIterator.hasNext())
			{
				DB_EmojiPanelItem item = keyboardIterator.next();
				if (item.get_panel_identifier() != -1 || item.get_source() == EmojiPanelItem.PANEL_SOURCE.RECENTS)
				{
					keyboardIterator.remove();
				}
			}

		}
		else
		{
			removeStockMarshmallowEmojiPanels(db, true);
		}

	}

	private static ArrayList<DB_EmojiPanelItem> getPanelsForIdentifier(List<DB_EmojiPanelItem> items, int identifier)
	{
		ArrayList<DB_EmojiPanelItem> returnItems = new ArrayList<>();

		if (identifier == -1)
			return returnItems;

		for (DB_EmojiPanelItem item : items)
		{
			if (item.get_panel_identifier() == identifier)
				returnItems.add(item);
		}

		return returnItems;
	}

	private static DB_EmojiPanelItem getPanelForIdentifier(List<DB_EmojiPanelItem> items, int identifier)
	{
		ArrayList<DB_EmojiPanelItem> returnItems = getPanelsForIdentifier(items,identifier);
		if (!returnItems.isEmpty())
			return returnItems.get(0);
		return null;
	}

	//Refresh emoji. Before marshmallow panels are just removed instead.
	public static void refreshAllPanels(Context context, WrappedDatabase db, FancyEmojiPanelTemplates.EmojiPanelVersion version)
	{
		//The template panels can't be modified, and even have a key set, so they'll be fine.
		//The editable panels are a bit more of a headache, and we have to be careful.
		TableList<DB_EmojiPanelItem> dictionaryPanels = new TableList<>(db, TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO);
		TableList<DB_EmojiPanelItem> keyboardPanels = new TableList<>(db, TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO);

		//New emoji
		List<DB_EmojiPanelItem> replacementPanels = FancyEmojiPanelTemplates.getForEmojiPanelVersion(version);

		//Starting from Android N, we refresh panels instead of readding them completely.
		//On creation, or when a user hits add-all for a new empty keyboard panel, we mark
		//the panel with an identifier that matches the template.
		//When we want to update the emoji we do a quick content check, then remove
		//the the emoji up to the last existing item, following by feeding in all the new replacements.

		//Keep track of panel identifiers we've covered so we don't do weird things
		Set<Integer> coveredIdentifiers = new HashSet<>();
		coveredIdentifiers.add(-1);	//-1 is unset, thus skip

		ListIterator<DB_EmojiPanelItem> templateIterator = dictionaryPanels.listIterator();
		while(templateIterator.hasNext())
		{
			DB_EmojiPanelItem template  = templateIterator.next();

			if (!coveredIdentifiers.contains(template.get_panel_identifier()))
			{
				coveredIdentifiers.add(template.get_panel_identifier());;

				ArrayList<DB_EmojiPanelItem> panels = getPanelsForIdentifier(keyboardPanels, template.get_panel_identifier());

				//Remove all emoji from panels
				for (DB_EmojiPanelItem item : panels)
				{
					//We'll be completely replacing the contents once we're done
					item.get_items().setDatabaseMode(TableList.DatabaseMode.MANUAL);
					item.get_items().clear();
				}

				//Add emoji from new template
				DB_EmojiPanelItem newTemplate = getPanelForIdentifier(replacementPanels, template.get_panel_identifier());
				if (newTemplate != null)
				{
					//Add new emoji to beginning
					for (DB_EmojiPanelItem item : panels)
					{
						item.get_items().addAll(newTemplate.get_items());

						//Push to database
						item.get_items().replaceTableWithContents();
					}

					//Replace emoji in template
					template.get_items().setDatabaseMode(TableList.DatabaseMode.MANUAL);
					template.get_items().clear();
					template.get_items().addAll( newTemplate.get_items() );
					template.get_items().replaceTableWithContents();
				}
				else
				{
					Log.d(LOGTAG, "Failed to locate template corresponding to "+template.get_panel_identifier());
					Log.d(LOGTAG, "Candidates were: ");
					for (DB_EmojiPanelItem item : replacementPanels)
					{
						Log.d(LOGTAG, item.get_icon()+", identifier: "+item.get_panel_identifier());
					}
				}
			}
		}

		//Do a check for emoji modifier support in all user panels
		for (DB_EmojiPanelItem item : dictionaryPanels)
		{
			if (item.get_source().isEditable())
			{
				item.updateModifierSupport();
				item.get_items().updateAll();
			}
		}
		for (DB_EmojiPanelItem item : keyboardPanels)
		{
			if (item.get_source().isEditable())
			{
				item.updateModifierSupport();
				item.get_items().updateAll();
			}
		}

		//Let hook know of update
		ModuleDatabaseType.EMOJI.setUpdateItem(context);
	}

	public static void clear(Context context, WrappedDatabase db)
	{
		for (ModuleDatabaseType type : ModuleDatabaseType.values())
		{
			clear(context, db,type);
		}
	}

	public static void loadDefaults(Context context, WrappedDatabase db, int SDKVersion)
	{
		for (ModuleDatabaseType type : ModuleDatabaseType.values())
		{
			loadDefaults(context, db,type, SDKVersion);
		}
	}


	public static void loadDefaults(final Context context, WrappedDatabase db, ModuleDatabaseType type, int SDKVersion )
	{
		switch(type)
		{
			case DICTIONARY:
			{
				//Nothing
				break;
			}

			case EMOJI:
			{

				FancyEmojiPanelTemplates.EmojiPanelVersion emojiVersion = FancyEmojiPanelTemplates.EmojiPanelVersion.getFromSdkVersion(SDKVersion);

				ArrayList<DB_EmojiPanelItem> keyboardTemplates =  FancyEmojiPanelTemplates.getForEmojiPanelVersion(emojiVersion);
				Collections.reverse(keyboardTemplates);

				//////////////
				//Templates
				//////////////

				TableList<DB_EmojiPanelItem> dictionaryPanels = new TableList<>(db, TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO);


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

				/////////////
				//Keyboard
				/////////////

				//Templates are modified when adding, so grab a fresh copy
				keyboardTemplates =  FancyEmojiPanelTemplates.getForEmojiPanelVersion(emojiVersion);
				Collections.reverse(keyboardTemplates);

				TableList<DB_EmojiPanelItem> keyboardPanels = new TableList<>(db, TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO);

				for (DB_EmojiPanelItem item : keyboardTemplates)
					keyboardPanels.add(0,item);

				//Additionally, the keyboard items should contain a special recents panel
				//This panel will not be editable by the user. Should probably have some indicator.
				keyboardPanels.add(0, FancyEmojiPanelTemplates.getRecentsPanelItem());


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

				//Save a pref so we'll know that the current set of emoji panels all have identifers set
				//this way we can just refresh them instead of guessing which are which and deleting.
				SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(context);
				editor.putBoolean(PreferenceConstants.status_emoji_panels_have_identifiers_key, true);
				editor.apply();

				//And set the current emoji version pref
				editor.putInt(PreferenceConstants.status_api_version_emoji, emojiVersion.getSdkVersion());
				editor.apply();

				break;
			}
			case HOTKEY:
			{
				TableList<DB_ModifierKeyItem> modifierKeys = new TableList<DB_ModifierKeyItem>( db, TableInfoTemplates.MODIFIER_KEY_TABLE_INFO);
				modifierKeys.startBatchEdit();
				modifierKeys.add( new DB_ModifierKeyItem(-1, "a", KeyboardInteraction.TextAction.SELECT_ALL));
				modifierKeys.add( new DB_ModifierKeyItem(-1, "x", KeyboardInteraction.TextAction.CUT));
				modifierKeys.add( new DB_ModifierKeyItem(-1, "c", KeyboardInteraction.TextAction.COPY));
				modifierKeys.add( new DB_ModifierKeyItem(-1, "v", KeyboardInteraction.TextAction.PASTE));
				modifierKeys.endBatchEdit();

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

		//Let hook know of update
		type.setUpdateItem(context);
	}





}
