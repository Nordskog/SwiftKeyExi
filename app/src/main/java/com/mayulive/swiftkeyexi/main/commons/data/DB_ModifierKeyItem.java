package com.mayulive.swiftkeyexi.main.commons.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.database.DatabaseItem;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction.TextAction;

public class DB_ModifierKeyItem extends ModifierKeyItem implements DatabaseItem
{
	private int _id = -1;

	public DB_ModifierKeyItem(){super();};

	public DB_ModifierKeyItem(int id, String key, TextAction action)
	{
		super(key,action);
		set_id(id);
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}


	public static class ModifierKeyEntry implements BaseColumns
	{
		public static final String KEY_COLUMN = "key";
		public static final String ACTION_COLUMN = "action";
	}

	public static final String DEFINITION =
			"("+
					ModifierKeyEntry._ID +" INTEGER PRIMARY KEY, " +
					ModifierKeyEntry.KEY_COLUMN +" TEXT, " +
					ModifierKeyEntry.ACTION_COLUMN +" TEXT " +
					");";

	public static final String[] PROJECTION =
			{
					ModifierKeyEntry._ID,
					ModifierKeyEntry.KEY_COLUMN,
					ModifierKeyEntry.ACTION_COLUMN,
			};

	@Override
	public ContentValues getValues(boolean includeId)
	{
		ContentValues values = new ContentValues();
		if(includeId)
			values.put(BaseColumns._ID, get_id());
		values.put(ModifierKeyEntry.KEY_COLUMN, get_key());
		values.put(ModifierKeyEntry.ACTION_COLUMN, get_textAction().toString());

		return values;
	}

	@Override
	public void populate(DatabaseWrapper dbWrap, Cursor c)
	{
		//Surely these are static for all rows
		int idColumnIndex = c.getColumnIndex(BaseColumns._ID);
		int keyColumnIndex = c.getColumnIndex(ModifierKeyEntry.KEY_COLUMN);
		int actionColumnIndex = c.getColumnIndex(ModifierKeyEntry.ACTION_COLUMN);

		set_id( c.getInt(idColumnIndex) );
		set_key( c.getString(keyColumnIndex) );
		set_textAction( KeyboardInteraction.TextAction.valueOf( c.getString(actionColumnIndex) ) );
	}


	@Override
	public void createChildTables(DatabaseWrapper dbWrap, String parentTable)
	{

	}

	@Override
	public void deleteChildTables()
	{

	}

	@Override
	public boolean hasChildTables()
	{
		return false;
	}

	@Override
	public DatabaseItem getNewInstance()
	{
		return new DB_ModifierKeyItem();
	}
}
