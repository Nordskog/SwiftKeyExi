package com.mayulive.swiftkeyexi.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.LoadPackageHook;
import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;
import com.mayulive.swiftkeyexi.util.CursorUtils;

import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper
{

	private static String LOGTAG = ExiModule.getLogTag(DatabaseHandler.class);

	public static final int DATABASE_VERSION = 5;
	public static final String DATABASE_NAME = "exi_main.db";

	private SQLiteDatabase mDb = null;
	private static Context mContext = null;
	
	//////////////////////////////////////////////////////////////////


	public SQLiteDatabase getDatabaseInstance()
	{
		ensureOpen();
		return mDb;

	}

	public  void openDatabase()
	{
		mDb = getWritableDatabase();
	}
	
	public void closeDataBase()
	{
		mDb.close();
		mDb = null;
	}
	
	public void ensureOpen()
	{
		if (mDb == null)
			openDatabase();
	}
	
	
	public DatabaseHandler(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		String query = "CREATE TABLE " + TableInfoTemplates.DICTIONARY_SHORTCUT_TABLE_INFO.tableName + TableInfoTemplates.DICTIONARY_SHORTCUT_TABLE_INFO.tableDefinition;
		db.execSQL(query);
		
		query = "CREATE TABLE " + TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO.tableName + TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO.tableDefinition;
		db.execSQL(query);

		query = "CREATE TABLE " + TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO.tableName + TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO.tableDefinition;
		db.execSQL(query);

		query = "CREATE TABLE " + TableInfoTemplates.RECENT_EMOJI_TABLE_INFO.tableName + TableInfoTemplates.RECENT_EMOJI_TABLE_INFO.tableDefinition;
		db.execSQL(query);

		query = "CREATE TABLE " + TableInfoTemplates.MODIFIER_KEY_TABLE_INFO.tableName + TableInfoTemplates.MODIFIER_KEY_TABLE_INFO.tableDefinition;
		db.execSQL(query);

		query = "CREATE TABLE " + TableInfoTemplates.POPUP_KEY_TABLE_INFO.tableName + TableInfoTemplates.POPUP_KEY_TABLE_INFO.tableDefinition;
		db.execSQL(query);

		query = "CREATE TABLE " + TableInfoTemplates.ADDITIONAL_SHIFT_KEYS_TABLE_INFO.tableName + TableInfoTemplates.ADDITIONAL_SHIFT_KEYS_TABLE_INFO.tableDefinition;
		db.execSQL(query);

		query = "CREATE TABLE " + TableInfoTemplates.ADDITIONAL_DELETE_KEYS_TABLE_INFO.tableName + TableInfoTemplates.ADDITIONAL_DELETE_KEYS_TABLE_INFO.tableDefinition;
		db.execSQL(query);

		query = "CREATE TABLE " + TableInfoTemplates.ADDITIONAL_SYMBOL_KEYS_TABLE_INFO.tableName + TableInfoTemplates.ADDITIONAL_SYMBOL_KEYS_TABLE_INFO.tableDefinition;
		db.execSQL(query);

		query = "CREATE TABLE " + TableInfoTemplates.HOTKEY_MENU_ITEMS_TABLE_INFO.tableName + TableInfoTemplates.HOTKEY_MENU_ITEMS_TABLE_INFO.tableDefinition;
		db.execSQL(query);

		query = "CREATE TABLE " + TableInfoTemplates.REMAPPED_HARDWAREKEYS_TABLE_INFO.tableName + TableInfoTemplates.REMAPPED_HARDWAREKEYS_TABLE_INFO.tableDefinition;
		db.execSQL(query);

	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		Log.i(LOGTAG, "DB version: "+oldVersion+", upgrading to: "+newVersion);

		int presentVersion = oldVersion;
		while(presentVersion < newVersion)
		{
			switch(presentVersion)
			{
				case 1:
				{
					Log.i(LOGTAG, "upgrading to: "+(presentVersion+1));
					String query = "CREATE TABLE " + TableInfoTemplates.HOTKEY_MENU_ITEMS_TABLE_INFO.tableName + TableInfoTemplates.HOTKEY_MENU_ITEMS_TABLE_INFO.tableDefinition;
					db.execSQL(query);

					ExiModule.loadDefaults(mContext, new WrappedDatabase(db), ExiModule.ModuleDatabaseType.HOTKEY_MENU_ITEM, Build.VERSION.SDK_INT);
					break;
				}

				case 2:
				{
					Log.i(LOGTAG, "upgrading to: "+presentVersion+1);

					//Add identifier column to template and keyboard panels
					String query = "ALTER TABLE "+TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO.tableName+" ADD COLUMN "+ DB_EmojiPanelItem.EmojiPanelContract.IDENTIFIER_TABLE_COLUMN+" INTEGER DEFAULT -1";
					db.execSQL(query);
					query = "ALTER TABLE "+TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO.tableName+" ADD COLUMN "+ DB_EmojiPanelItem.EmojiPanelContract.IDENTIFIER_TABLE_COLUMN+" INTEGER DEFAULT -1";
					db.execSQL(query);

					//Add modifiers supported column to emoji lists.
					//Since the projection has changed we can't use the usual simplified databaseitem interface
					Cursor c = db.query(
							TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO.tableName,
							new String[]{DB_EmojiPanelItem.EmojiPanelContract.ITEMS_TABLE_COLUMN},
							null,
							null,
							null,
							null,
							null
					);

					List<String> tableNames = CursorUtils.getStringsFromCursor(c);
					c.close();
					for (String item : tableNames)
					{
						query = "ALTER TABLE "+item+" ADD COLUMN "+ DB_EmojiItem.EmojiEntry.MODIFIERS_SUPPORTED_COLUMN+" INTEGER DEFAULT 0";
						db.execSQL(query);
					}

					c = db.query(
							TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO.tableName,
							new String[]{DB_EmojiPanelItem.EmojiPanelContract.ITEMS_TABLE_COLUMN},
							null,
							null,
							null,
							null,
							null
					);
					tableNames = CursorUtils.getStringsFromCursor(c);
					c.close();
					for (String item : tableNames)
					{
						query = "ALTER TABLE "+item+" ADD COLUMN "+ DB_EmojiItem.EmojiEntry.MODIFIERS_SUPPORTED_COLUMN+" INTEGER DEFAULT 0";
						db.execSQL(query);
					}

					break;
				}

				case 3:
				{
					Log.i(LOGTAG, "upgrading to: "+presentVersion+1);

					String query = "ALTER TABLE "+TableInfoTemplates.POPUP_KEY_TABLE_INFO.tableName+" ADD COLUMN "+ DB_PopupParentKeyItem.PopupParentKeyEntry.DELETE_EXISTING_TABLE_COLUMN+" BOOLEAN DEFAULT 0";
					db.execSQL(query);

					break;
				}

				case 4:
				{
					Log.i(LOGTAG, "upgrading to: "+presentVersion+1);

					//////////////////////
					// Hardware hotkeys
					//////////////////////

					String query = "ALTER TABLE "+TableInfoTemplates.MODIFIER_KEY_TABLE_INFO.tableName+" ADD COLUMN "+ DB_ModifierKeyItem.ModifierKeyEntry.HOTKEY_TYPE+" TEXT DEFAULT 'SOFT'";
					db.execSQL(query);

					query = "ALTER TABLE "+TableInfoTemplates.MODIFIER_KEY_TABLE_INFO.tableName+" ADD COLUMN "+ DB_ModifierKeyItem.ModifierKeyEntry.HARD_MODIFIER_COLUMN+" INTEGER DEFAULT -1";
					db.execSQL(query);

					query = "ALTER TABLE "+TableInfoTemplates.MODIFIER_KEY_TABLE_INFO.tableName+" ADD COLUMN "+ DB_ModifierKeyItem.ModifierKeyEntry.HARD_KEY_COLUMN+" INTEGER DEFAULT -1";
					db.execSQL(query);

					query = "ALTER TABLE "+TableInfoTemplates.MODIFIER_KEY_TABLE_INFO.tableName+" ADD COLUMN "+ DB_ModifierKeyItem.ModifierKeyEntry.HARD_MODIFIER_TYPE_COLUMN+" TEXT DEFAULT 'KEY_CODE'";
					db.execSQL(query);

					query = "ALTER TABLE "+TableInfoTemplates.MODIFIER_KEY_TABLE_INFO.tableName+" ADD COLUMN "+ DB_ModifierKeyItem.ModifierKeyEntry.HARD_KEY_TYPE_COLUMN+" TEXT DEFAULT 'KEY_CODE'";
					db.execSQL(query);

					//////////////////////////
					// Hardware key remapping
					//////////////////////////
					query = "CREATE TABLE " + TableInfoTemplates.REMAPPED_HARDWAREKEYS_TABLE_INFO.tableName + TableInfoTemplates.REMAPPED_HARDWAREKEYS_TABLE_INFO.tableDefinition;
					db.execSQL(query);

					break;
				}
			}

			presentVersion++;
		}
	}
	
	public static void DeleteDatabase(Context context)
	{
		context.deleteDatabase(DATABASE_NAME);
	}


	
}
