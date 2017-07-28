package com.mayulive.swiftkeyexi.main.dictionary.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.database.DatabaseItem;


public class DB_DictionaryWordItem extends DictionaryWordItem implements DatabaseItem
{
	private int _id = -1;

	public DB_DictionaryWordItem(){ super(); };

	public DB_DictionaryWordItem(int id, String text)
	{
		super(text);
		set_id(id);
	}

	public DB_DictionaryWordItem(int id, String text, long priority)
	{
		super(text, priority);
		set_id(id);
	}


	@Override
	public int get_id() {
		return _id;
	}

	@Override
	public void set_id(int _id) {
		this._id = _id;
	}



	//public final static class Contract 
	//{
	    // To prevent someone from accidentally instantiating the contract class,
	    // make the constructor private.
	   // private Contract() {}

	    /* Inner class that defines the table contents */
    public static class DictionaryWordEntry implements BaseColumns
    {
        public static final String PRIORITY_COLUMN = "priority";
        public static final String TEXT_COLUMN = "text";
    }
	//}
	
	public static final String[] PROJECTION =
	{
			DictionaryWordEntry._ID,
			DictionaryWordEntry.PRIORITY_COLUMN,
			DictionaryWordEntry.TEXT_COLUMN,
	};

	public static final String DEFINITION =
			"("+
					DictionaryWordEntry._ID +" INTEGER PRIMARY KEY, " +
					DictionaryWordEntry.PRIORITY_COLUMN +" NUMBER, " +
					DictionaryWordEntry.TEXT_COLUMN +" TEXT " +
			");";
	
	//private static TableInfo tableInfo = new TableInfo(new DictionaryShortcutItem(), projection, DictionaryShortcutEntry.TABLE_NAME, DictionaryShortcutEntry.CONTENT_URI, definition);
	

	@Override
	public ContentValues getValues(boolean includeId)
	{
		ContentValues values = new ContentValues();
		if(includeId)
			values.put(BaseColumns._ID, get_id());
		values.put(DictionaryWordEntry.PRIORITY_COLUMN, get_priority());
		values.put(DictionaryWordEntry.TEXT_COLUMN, get_text());
		
		return values;
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
	public void populate(DatabaseWrapper dbWrap, Cursor c)
	{
		//Surely these are static for all rows
		int idColumnIndex = c.getColumnIndex(BaseColumns._ID);
		int priorityColumnIndex = c.getColumnIndex(DictionaryWordEntry.PRIORITY_COLUMN);
		int textColumnIndex = c.getColumnIndex(DictionaryWordEntry.TEXT_COLUMN);
		
		set_id( c.getInt(idColumnIndex) );
		set_priority( c.getLong(priorityColumnIndex));
		set_text( c.getString(textColumnIndex) );
	}

	@Override
	public DatabaseItem getNewInstance() 
	{
		return new DB_DictionaryWordItem();
	}

}
