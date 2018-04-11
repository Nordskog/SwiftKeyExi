package com.mayulive.swiftkeyexi.main.commons.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.mayulive.swiftkeyexi.database.DatabaseItem;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;

/**
 * Created by Roughy on 3/8/2017.
 */

public class DB_RemappedKey extends RemappedKey implements DatabaseItem
{

	private int _id = -1;


	public DB_RemappedKey()
	{
		super();
	}

	public DB_RemappedKey(int id, HardKey from, HardKey to)
	{
		super(from,to);
		_id = id;
	}

	@Override
	public int get_id()
	{
		return _id;
	}

	@Override
	public void set_id(int id)
	{
		_id = id;
	}

	@Override
	public ContentValues getValues(boolean includeId)
	{
		ContentValues values = new ContentValues();
		if(includeId)
			values.put(BaseColumns._ID, get_id());

		HardKey from = this.getFrom_key();
		HardKey to = this.getTo_key();


		values.put(RemappedKeyEntry.FROM_KEY_KEYCODE_COLUMN, from.keycode);
		values.put(RemappedKeyEntry.FROM_KEY_SCANCODE_COLUMN, from.scancode);

		values.put(RemappedKeyEntry.TO_KEY_KEYCODE_COLUMN, to.keycode);
		values.put(RemappedKeyEntry.TO_KEY_SCANCODE_COLUMN, to.scancode);

		return values;
	}

	@Override
	public void populate(DatabaseWrapper dbWrap, Cursor c)
	{

		//Surely these are static for all rows
		int idColumnIndex = c.getColumnIndex(BaseColumns._ID);

		int from_key = c.getColumnIndex(RemappedKeyEntry.FROM_KEY_KEYCODE_COLUMN);
		int from_scan = c.getColumnIndex(RemappedKeyEntry.FROM_KEY_SCANCODE_COLUMN);

		int to_key = c.getColumnIndex(RemappedKeyEntry.TO_KEY_KEYCODE_COLUMN);
		int to_scan = c.getColumnIndex(RemappedKeyEntry.TO_KEY_SCANCODE_COLUMN);

		set_id( c.getInt(idColumnIndex) );

		setFrom_key	( new HardKey( c.getInt(from_key), c.getInt(from_scan) )	);
		setTo_key	( new HardKey( c.getInt(to_key), c.getInt(to_scan) )	);
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
		return new DB_RemappedKey();
	}

	//////////////////

	/* Inner class that defines the table contents */
	private static class RemappedKeyEntry implements BaseColumns
	{
		public static final String FROM_KEY_KEYCODE_COLUMN 		= "from_key_keycode";
		public static final String FROM_KEY_SCANCODE_COLUMN 	= "from_key_scancode";
		public static final String TO_KEY_KEYCODE_COLUMN 		= "to_key_keycode";
		public static final String TO_KEY_SCANCODE_COLUMN 		= "to_key_scancode";
	}
	//}

	public static final String[] PROJECTION =
			{
					RemappedKeyEntry._ID,
					RemappedKeyEntry.FROM_KEY_KEYCODE_COLUMN,
					RemappedKeyEntry.FROM_KEY_SCANCODE_COLUMN,
					RemappedKeyEntry.TO_KEY_KEYCODE_COLUMN,
					RemappedKeyEntry.TO_KEY_SCANCODE_COLUMN,
			};

	public static final String DEFINITION =
			"("+
					RemappedKeyEntry._ID +" INTEGER PRIMARY KEY, " +
					RemappedKeyEntry. FROM_KEY_KEYCODE_COLUMN  +" INTEGER, " +
					RemappedKeyEntry. FROM_KEY_SCANCODE_COLUMN +" INTEGER, " +
					RemappedKeyEntry. TO_KEY_KEYCODE_COLUMN  +" INTEGER, " +
					RemappedKeyEntry. TO_KEY_SCANCODE_COLUMN  +" INTEGER " +

					");";

}
