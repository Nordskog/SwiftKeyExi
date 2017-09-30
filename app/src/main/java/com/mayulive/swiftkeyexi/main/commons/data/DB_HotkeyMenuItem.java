package com.mayulive.swiftkeyexi.main.commons.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.mayulive.swiftkeyexi.database.DatabaseItem;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.main.keyboard.HotkeyPanel;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

/**
 * Created by Roughy on 9/26/2017.
 */

public class DB_HotkeyMenuItem extends HotkeyPanel.HotkeyMenuItem implements DatabaseItem
{

	private int _id = -1;

	public DB_HotkeyMenuItem()
	{
		super(null, KeyboardInteraction.TextAction.COPY, 1, 0);
	}

	public DB_HotkeyMenuItem(String text, KeyboardInteraction.TextAction action, float calculatedWidth, int pos)
	{
		super(text, action, calculatedWidth, pos);
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
		values.put(HotKeyMenuItemEntry.ACTION_COLUMN, action.toString());
		values.put(HotKeyMenuItemEntry.DISPLAY_TEXT_COLUMN, text);
		values.put(HotKeyMenuItemEntry.POSITION_COLUMN, position);
		values.put(HotKeyMenuItemEntry.WIDTH_COLUMN, calculatedWidth);

		return values;
	}

	@Override
	public void populate(DatabaseWrapper dbWrap, Cursor c)
	{
		//Surely these are static for all rows
		int idColumnIndex = c.getColumnIndex(BaseColumns._ID);
		int actionColumnIndex = c.getColumnIndex(HotKeyMenuItemEntry.ACTION_COLUMN);
		int displayTextColumnIndex = c.getColumnIndex(HotKeyMenuItemEntry.DISPLAY_TEXT_COLUMN);
		int positionColumnIndex = c.getColumnIndex(HotKeyMenuItemEntry.POSITION_COLUMN);
		int widthColumnIndex = c.getColumnIndex(HotKeyMenuItemEntry.WIDTH_COLUMN);

		set_id( c.getInt(idColumnIndex) );
		action = KeyboardInteraction.TextAction.valueOf(c.getString(actionColumnIndex) );
		text = c.getString(displayTextColumnIndex);
		position = c.getInt(positionColumnIndex);
		calculatedWidth = c.getFloat(widthColumnIndex);
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
		return new DB_HotkeyMenuItem();
	}

	//////////////////

	/* Inner class that defines the table contents */
	private static class HotKeyMenuItemEntry implements BaseColumns
	{
		public static final String ACTION_COLUMN = "action";
		public static final String DISPLAY_TEXT_COLUMN = "text";
		public static final String POSITION_COLUMN = "position";
		public static final String WIDTH_COLUMN = "width";
	}
	//}

	public static final String[] PROJECTION =
			{
					HotKeyMenuItemEntry._ID,
					HotKeyMenuItemEntry.ACTION_COLUMN,
					HotKeyMenuItemEntry.DISPLAY_TEXT_COLUMN,
					HotKeyMenuItemEntry.POSITION_COLUMN,
					HotKeyMenuItemEntry.WIDTH_COLUMN,
			};

	public static final String DEFINITION =
			"("+
					HotKeyMenuItemEntry._ID +" INTEGER PRIMARY KEY, " +
					HotKeyMenuItemEntry.ACTION_COLUMN +" TEXT, " +
					HotKeyMenuItemEntry.DISPLAY_TEXT_COLUMN +" TEXT, " +
					HotKeyMenuItemEntry.POSITION_COLUMN +" NUMBER, " +
					HotKeyMenuItemEntry.WIDTH_COLUMN +" NUMBER " +
					");";
}
