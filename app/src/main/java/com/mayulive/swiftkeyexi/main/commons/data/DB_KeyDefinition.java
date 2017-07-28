package com.mayulive.swiftkeyexi.main.commons.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.mayulive.swiftkeyexi.database.DatabaseItem;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;

/**
 * Created by Roughy on 3/8/2017.
 */

public class DB_KeyDefinition extends KeyDefinition implements DatabaseItem
{

	private int _id = -1;


	public DB_KeyDefinition()
	{
		super();
	}

	public DB_KeyDefinition(int id, String content, KeyCommons.KeyType type)
	{
		super(content,type);
		_id = id;
	}

	public DB_KeyDefinition(String content, KeyCommons.KeyType type)
	{
		super(content,type);
	}

	public DB_KeyDefinition(KeyDefinition existingItem)
	{
		super(existingItem.content, existingItem.type);
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
		values.put(KeyDefinitionEntry.CONTENT_COLUMN, getContent());
		values.put(KeyDefinitionEntry.TYPE_COLUMN, getType().toString());

		return values;
	}

	@Override
	public void populate(DatabaseWrapper dbWrap, Cursor c)
	{

		//Surely these are static for all rows
		int idColumnIndex = c.getColumnIndex(BaseColumns._ID);
		int contentColumnIndex = c.getColumnIndex(KeyDefinitionEntry.CONTENT_COLUMN);
		int typeColumnIndex = c.getColumnIndex(KeyDefinitionEntry.TYPE_COLUMN);

		set_id( c.getInt(idColumnIndex) );
		setContent( c.getString(contentColumnIndex));
		setType( KeyCommons.KeyType.valueOf(c.getString(typeColumnIndex) ) );
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
		return new DB_KeyDefinition();
	}

	//////////////////

	/* Inner class that defines the table contents */
	private static class KeyDefinitionEntry implements BaseColumns
	{
		public static final String CONTENT_COLUMN = "priority";
		public static final String TYPE_COLUMN = "text";
	}
	//}

	public static final String[] PROJECTION =
			{
					KeyDefinitionEntry._ID,
					KeyDefinitionEntry.CONTENT_COLUMN,
					KeyDefinitionEntry.TYPE_COLUMN,
			};

	public static final String DEFINITION =
			"("+
					KeyDefinitionEntry._ID +" INTEGER PRIMARY KEY, " +
					KeyDefinitionEntry.CONTENT_COLUMN +" TEXT, " +
					KeyDefinitionEntry.TYPE_COLUMN +" TEXT " +
					");";


}
