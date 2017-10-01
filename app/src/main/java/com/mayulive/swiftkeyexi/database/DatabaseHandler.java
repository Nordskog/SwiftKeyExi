package com.mayulive.swiftkeyexi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;

public class DatabaseHandler extends SQLiteOpenHelper
{

	public static final int DATABASE_VERSION = 2;
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

	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		int presentVersion = oldVersion;
		while(presentVersion < newVersion)
		{
			switch(presentVersion)
			{
				case 1:
				{
					String query = "CREATE TABLE " + TableInfoTemplates.HOTKEY_MENU_ITEMS_TABLE_INFO.tableName + TableInfoTemplates.HOTKEY_MENU_ITEMS_TABLE_INFO.tableDefinition;
					db.execSQL(query);

					ExiModule.loadDefaults(mContext, new WrappedDatabase(db), ExiModule.ModuleDatabaseType.HOTKEY_MENU_ITEM);
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
