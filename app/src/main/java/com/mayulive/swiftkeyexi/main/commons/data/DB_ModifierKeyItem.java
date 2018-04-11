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
		public static final String HOTKEY_TYPE = "hotkey_type";
		public static final String HARD_MODIFIER_COLUMN = "hard_modifier";
		public static final String HARD_KEY_COLUMN = "hard_key";
		public static final String HARD_MODIFIER_TYPE_COLUMN = "hard_modifier_type";
		public static final String HARD_KEY_TYPE_COLUMN = "hard_key_type";
	}

	public static final String DEFINITION =
			"("+
					ModifierKeyEntry._ID +" INTEGER PRIMARY KEY, " +
					ModifierKeyEntry.KEY_COLUMN +" TEXT, " +
					ModifierKeyEntry.ACTION_COLUMN +" TEXT, " +
					ModifierKeyEntry.HOTKEY_TYPE +" TEXT, " +
					ModifierKeyEntry.HARD_MODIFIER_COLUMN +" INTEGER, " +
					ModifierKeyEntry.HARD_KEY_COLUMN +" INTEGER, " +
					ModifierKeyEntry.HARD_MODIFIER_TYPE_COLUMN +" TEXT, " +
					ModifierKeyEntry.HARD_KEY_TYPE_COLUMN +" TEXT " +
					");";

	public static final String[] PROJECTION =
			{
					ModifierKeyEntry._ID,
					ModifierKeyEntry.KEY_COLUMN,
					ModifierKeyEntry.ACTION_COLUMN,
					ModifierKeyEntry.HOTKEY_TYPE,
					ModifierKeyEntry.HARD_MODIFIER_COLUMN,
					ModifierKeyEntry.HARD_KEY_COLUMN,
					ModifierKeyEntry.HARD_MODIFIER_TYPE_COLUMN,
					ModifierKeyEntry.HARD_KEY_TYPE_COLUMN,
			};

	@Override
	public ContentValues getValues(boolean includeId)
	{
		ContentValues values = new ContentValues();
		if(includeId)
			values.put(BaseColumns._ID, get_id());
		values.put(ModifierKeyEntry.KEY_COLUMN, get_key());
		values.put(ModifierKeyEntry.ACTION_COLUMN, get_textAction().toString());

		values.put(ModifierKeyEntry.HOTKEY_TYPE, get_hotkey_type().toString());

		HardKey hard_modifier = get_hard_modifier_key();
		HardKey hard_key = get_hard_key();

		values.put(ModifierKeyEntry.HARD_MODIFIER_COLUMN, hard_modifier.getCodeForType());
		values.put(ModifierKeyEntry.HARD_KEY_COLUMN, hard_key.getCodeForType());

		values.put(ModifierKeyEntry.HARD_MODIFIER_TYPE_COLUMN, hard_modifier.getType().toString());
		values.put(ModifierKeyEntry.HARD_KEY_TYPE_COLUMN, hard_key.getType().toString());

		return values;
	}

	@Override
	public void populate(DatabaseWrapper dbWrap, Cursor c)
	{
		//Surely these are static for all rows
		int idColumnIndex = c.getColumnIndex(BaseColumns._ID);
		int keyColumnIndex = c.getColumnIndex(ModifierKeyEntry.KEY_COLUMN);
		int actionColumnIndex = c.getColumnIndex(ModifierKeyEntry.ACTION_COLUMN);

		int hotkeyTypeColumn = c.getColumnIndex(ModifierKeyEntry.HOTKEY_TYPE);
		int hardModifierColumn = c.getColumnIndex(ModifierKeyEntry.HARD_MODIFIER_COLUMN);
		int hardKeyColumn = c.getColumnIndex(ModifierKeyEntry.HARD_KEY_COLUMN);

		int hardModifierTypeColumn = c.getColumnIndex(ModifierKeyEntry.HARD_MODIFIER_TYPE_COLUMN);
		int hardKeyTypeColumn = c.getColumnIndex(ModifierKeyEntry.HARD_KEY_TYPE_COLUMN);

		set_id( c.getInt(idColumnIndex) );
		set_key( c.getString(keyColumnIndex) );
		set_textAction( KeyboardInteraction.TextAction.valueOf( c.getString(actionColumnIndex) ) );
		set_hotkey_type(ModifierKeyItem.HotkeyKeyType.valueOf( c.getString(hotkeyTypeColumn) ) );

		HardKey hard_modifier = get_hard_modifier_key();
		HardKey hard_key = get_hard_key();

		hard_modifier.setForType( 	c.getInt(hardModifierColumn), HardKeyType.valueOf( c.getString(hardModifierTypeColumn)  ) );
		hard_key.setForType( 		c.getInt(hardKeyColumn), HardKeyType.valueOf( c.getString(hardKeyTypeColumn)  ) );
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
