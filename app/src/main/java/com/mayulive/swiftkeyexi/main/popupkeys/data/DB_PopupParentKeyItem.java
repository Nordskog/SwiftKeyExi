package com.mayulive.swiftkeyexi.main.popupkeys.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.mayulive.swiftkeyexi.database.DatabaseItem;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.database.TableInfo;


public class DB_PopupParentKeyItem extends PopupParentKeyItem implements DatabaseItem
{
	private int _id = -1;

	public DB_PopupParentKeyItem(){ super(); };

	public DB_PopupParentKeyItem(int id, String key)
	{
		super(key);
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
    public static class DictionaryShortcutEntry implements BaseColumns
    {
        public static final String KEY_COLUMN = "parent_key";
		public static final String ITEMS_TABLE_COLUMN = "items";
    }
	//}
	
	public static final String[] PROJECTION =
	{
			DictionaryShortcutEntry._ID,
			DictionaryShortcutEntry.KEY_COLUMN,
			DictionaryShortcutEntry.ITEMS_TABLE_COLUMN
	};

	public static final String DEFINITION =
			"("+
				DictionaryShortcutEntry._ID +" INTEGER PRIMARY KEY, " +
				DictionaryShortcutEntry.KEY_COLUMN +" TEXT, " +
				DictionaryShortcutEntry.ITEMS_TABLE_COLUMN +" TEXT " +
			");";
	
	//private static TableInfo tableInfo = new TableInfo(new DictionaryShortcutItem(), projection, DictionaryShortcutEntry.TABLE_NAME, DictionaryShortcutEntry.CONTENT_URI, definition);
	

	@Override
	public ContentValues getValues(boolean includeId)
	{
		ContentValues values = new ContentValues();
		if(includeId)
			values.put(BaseColumns._ID, get_id());
		values.put(DictionaryShortcutEntry.KEY_COLUMN, get_parentKey());
		values.put(DictionaryShortcutEntry.ITEMS_TABLE_COLUMN, get_items().getTableName());

		return values;
	}



	@Override
	public void createChildTables(DatabaseWrapper dbWrap, String parentTable)
	{

		if (_items.getTableInfo() != null)
		{
			throw new IllegalStateException("Attempted to create child tables when tables already exist. Existing: "+_items.getTableInfo().tableName+", new: "+(parentTable+"_items_"+get_id()));
		}

		TableInfo itemsTableInfo = new TableInfo(new DB_PopupKeyItem(), DB_PopupKeyItem.PROJECTION, DB_PopupKeyItem.DEFINITION, parentTable+"_items_"+get_id());
		_items.addToDb(dbWrap, itemsTableInfo);

	}

	@Override
	public void deleteChildTables()
	{

		if (_items.getTableInfo() != null)
		{
			//throw new IllegalStateException("Attempted to delete tables when none exist");

			//May be null if item not added to database yet
			_items.removeFromDb();
		}

	}

	@Override
	public boolean hasChildTables()
	{
		return true;
	}


	@Override
	public void populate(DatabaseWrapper dbWrap, Cursor c)
	{
		//Surely these are static for all rows
		int idColumnIndex = c.getColumnIndex(BaseColumns._ID);
		int keyColumnIndex = c.getColumnIndex(DictionaryShortcutEntry.KEY_COLUMN);

		int items_table_ColumnIndex = c.getColumnIndex(DictionaryShortcutEntry.ITEMS_TABLE_COLUMN);

		set_id( c.getInt(idColumnIndex) );
		set_parentKey( c.getString(keyColumnIndex) );

		TableInfo itemsTableInfo = new TableInfo(new DB_PopupKeyItem(), DB_PopupKeyItem.PROJECTION, DB_PopupKeyItem.DEFINITION, c.getString(items_table_ColumnIndex));
		_items.populateFromDb(dbWrap, itemsTableInfo);
	}

	@Override
	public DatabaseItem getNewInstance() 
	{

		return new DB_PopupParentKeyItem();
	}

}
