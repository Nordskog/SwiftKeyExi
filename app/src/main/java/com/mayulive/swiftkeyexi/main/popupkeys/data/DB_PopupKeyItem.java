package com.mayulive.swiftkeyexi.main.popupkeys.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.mayulive.swiftkeyexi.database.DatabaseItem;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

import java.util.Comparator;

public class DB_PopupKeyItem extends PopupKeyItem implements DatabaseItem
{
	
	private int _id = -1;

	public DB_PopupKeyItem()
	{
		super();
	}

	public DB_PopupKeyItem(int id, int insertIndex, String popupKeyLower, String popupKeyUpper, KeyboardInteraction.TextAction action, boolean deleteExisting)
	{
		super(insertIndex,popupKeyLower,popupKeyUpper,action,deleteExisting);
		set_id(id);
	}


	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public static class DictionaryShortcutKeyIndexComparator implements Comparator<DB_PopupKeyItem> {
	    @Override
	    public int compare(DB_PopupKeyItem a, DB_PopupKeyItem b)
	    {
	    		if (a.get_insertIndex() == b.get_insertIndex())
	    			return 0;
	    		else if (a.get_insertIndex() < b.get_insertIndex())
	    			return -1;
	    		else
	    			return 1;
	    }
	}


	//Database stuff

	/* Inner class that defines the table contents */
	public static class PopupkeyEntry implements BaseColumns
	{
		public static final String INSERT_INDEX_COLUMN = "insert_index";

		public static final String LOWER_KEY_COLUMN = "lower";
		public static final String UPPER_KEY_COLUMN = "upper";

		public static final String ACTION_COLUMN = "action";
		public static final String DELETE_EXISTING_COLUMN = "delete_existing";


	}
	//}

	public static final String[] PROJECTION =
			{
					PopupkeyEntry._ID,
					PopupkeyEntry.INSERT_INDEX_COLUMN,
					PopupkeyEntry.LOWER_KEY_COLUMN,
					PopupkeyEntry.UPPER_KEY_COLUMN,
					PopupkeyEntry.ACTION_COLUMN,
					PopupkeyEntry.DELETE_EXISTING_COLUMN,
			};

	public static final String DEFINITION =
			"("+
					PopupkeyEntry._ID +" INTEGER PRIMARY KEY, " +
					PopupkeyEntry.INSERT_INDEX_COLUMN +" NUMBER, " +
					PopupkeyEntry.LOWER_KEY_COLUMN +" TEXT, " +
					PopupkeyEntry.UPPER_KEY_COLUMN +" TEXT, " +
					PopupkeyEntry.ACTION_COLUMN +" TEXT, " +
					PopupkeyEntry.DELETE_EXISTING_COLUMN +" BOOLEAN" +
					");";


	@Override
	public ContentValues getValues(boolean includeId)
	{
		ContentValues values = new ContentValues();
		if(includeId)
			values.put(BaseColumns._ID, get_id());
		values.put(PopupkeyEntry.INSERT_INDEX_COLUMN, get_insertIndex());
		values.put(PopupkeyEntry.LOWER_KEY_COLUMN, get_popupLower());
		values.put(PopupkeyEntry.UPPER_KEY_COLUMN, get_popupUpper());
		values.put(PopupkeyEntry.ACTION_COLUMN, get_textAction().toString());
		values.put(PopupkeyEntry.DELETE_EXISTING_COLUMN, is_deleteExisting());


		return values;
	}

	@Override
	public void populate(DatabaseWrapper dbWrap, Cursor c)
	{

		//Surely these are static for all rows
		int idColumnIndex = c.getColumnIndex(BaseColumns._ID);
		int insertIndexColumnIndex = c.getColumnIndex(PopupkeyEntry.INSERT_INDEX_COLUMN);
		int lowerColumnIndex = c.getColumnIndex(PopupkeyEntry.LOWER_KEY_COLUMN);
		int upperColumnIndex = c.getColumnIndex(PopupkeyEntry.UPPER_KEY_COLUMN);
		int actionColumnIndex = c.getColumnIndex(PopupkeyEntry.ACTION_COLUMN);
		int deleteExistingColumnIndex = c.getColumnIndex(PopupkeyEntry.DELETE_EXISTING_COLUMN);

		set_id( c.getInt(idColumnIndex) );

		set_insertIndex( c.getInt(insertIndexColumnIndex));
		set_popupLower( c.getString(lowerColumnIndex) );
		set_popupUpper( c.getString(upperColumnIndex) );
		set_textAction( KeyboardInteraction.TextAction.valueOf( c.getString(actionColumnIndex) ) );
		set_deleteExisting( c.getInt(deleteExistingColumnIndex) == 1 );

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
		return new DB_PopupKeyItem();
	}
	
}
