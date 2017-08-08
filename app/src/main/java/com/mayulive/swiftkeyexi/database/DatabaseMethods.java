package com.mayulive.swiftkeyexi.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Roughy on 2/5/2017.
 */

public class DatabaseMethods
{

	//When deleting by ID
	public static boolean clearTable(DatabaseWrapper db, TableInfo tableInfo)
	{
		//ensureOpen();

		int updatedRows = 0;

		if (tableInfo.item.hasChildTables())
		{
			//If the items have child tables, we need to delete all the child tables too
			ArrayList<? extends DatabaseItem> items = getAllItems(db, tableInfo);

			for (DatabaseItem item : items)
			{
				item.deleteChildTables();
			}
		}

		updatedRows = db.delete(tableInfo.tableName,null,null);

		if (updatedRows < 1)
			return false;

		return true;
	}


	//////////////////////////////////
	//Dictionary shortcut stuff
	//////////////////////////////////



	public static void addItem(DatabaseWrapper db, DatabaseItem item, TableInfo tableInfo)
	{
		//ensureOpen();

		//Log.e("###", "Added item to "+tableInfo.tableName);

		ContentValues values = item.getValues(false);

		long id = db.insert(tableInfo.tableName, null, values);

		//Dictionary entries are given a auto-incremented ID. Update input item.
		item.set_id((int)id);

		if (item.hasChildTables())
		{
			//Item was added, but with a null for the child table names
			item.createChildTables(db, tableInfo.tableName);

			//TODO only need to update table name column(s)
			if (!updateItem(db, item, tableInfo, false))
			{
				throw new IllegalStateException("Failed to update child table name of item just added to database");
			}

		}


	}

	//When deleting by ID
	public static boolean deleteItem(DatabaseWrapper db, DatabaseItem item, TableInfo tableInfo)
	{
		//ensureOpen();

		item.deleteChildTables();

		String selection = BaseColumns._ID + " = ?";
		String[] selectionArgs = {Integer.toString(item.get_id())};

		int updatedRows = db.delete(tableInfo.tableName,selection,selectionArgs);

		if (updatedRows < 1)
			return false;

		return true;
	}


	public static DatabaseItem getItemFromCursor(DatabaseWrapper dbWrap, Cursor c, DatabaseItem item)
	{
		/*
		if (c.getCount() > 1)
		{
			Log.e("###", "Expected single shortcut for key lookup, found multiple.");
		}
		*/

		if (c.getCount() > 0)
		{
			c.moveToFirst();

			item.populate(dbWrap, c);
		}

		return item;
	}



	public static Cursor getItemCursorById(DatabaseWrapper db, int id, TableInfo tableInfo)
	{
		//ensureOpen();

		String selection = BaseColumns._ID + " = ?";
		String[] selectionArgs = {Integer.toString(id)};


		Cursor c = db.query(
				tableInfo.tableName,
				tableInfo.projection,
				selection,
				selectionArgs,
				null,
				null,
				null
		);

		return c;
	}

	public static <T> boolean updateItem(DatabaseWrapper db, DatabaseItem item, TableInfo tableInfo, boolean createIfNotFound)
	{
		//ensureOpen();
		ContentValues values = item.getValues(true);

		String selection = BaseColumns._ID + " = ?";
		String[] selectionArgs = {Integer.toString(item.get_id())};

		int updatedRows = db.update(tableInfo.tableName, values, selection,selectionArgs);

		if (updatedRows < 1 && createIfNotFound)
		{

			if (createIfNotFound)
			{
				//Had valid ID but is not in database. Strip ID and add.
				addItem(db, item,tableInfo);
				return true;
			}
			else
			{
				//Attempted to update item that doesn't exist
				return false;
			}

		}

		return true;
	}

	public static <T> boolean updateItem(DatabaseWrapper db, DatabaseItem item, TableInfo tableInfo,  String... columns)
	{
		//ensureOpen();
		ContentValues values = item.getValues(true);

		//Filter the values we want, rather convoluted.
		Set<String> selectedCols = new HashSet<String>();
		selectedCols.addAll( values.keySet());
		selectedCols.removeAll(Arrays.asList(columns) );
		for (String key : selectedCols)
		{
			values.remove(key);
		}

		String selection = BaseColumns._ID + " = ?";
		String[] selectionArgs = {Integer.toString(item.get_id())};

		int updatedRows = db.update(tableInfo.tableName, values, selection,selectionArgs);

		if (updatedRows < 1)
		{
			return false;
		}

		return true;
	}

	public static Cursor getCursorToAllItems(DatabaseWrapper db, TableInfo tableInfo)
	{

		//ensureOpen();


		Cursor c = db.query(
				tableInfo.tableName,
				tableInfo.projection,
				null,
				null,
				null,
				null,
				null
		);

		/*
		if (!c.moveToFirst())
		{
			Log.e("###", "shortcut Cursor was empty!");
		}
		else
		{
			Log.e("###", "shortcut Cursor was NOT empty!");
		}
		*/



		return c;
	}

	public static ArrayList<? extends DatabaseItem> getItemsFromCursor(DatabaseWrapper dbWrap, TableInfo tableInfo, Cursor c)
	{
		ArrayList<DatabaseItem> returnArray = new ArrayList<DatabaseItem>();

		//Log.e("###", "shortcut get row count: "+c.getCount());

		//Log.e("###","Table: "+tableInfo.tableName);

		if (c == null)
		{
			return returnArray;
		}

		c.moveToFirst();
		c.moveToPrevious();


		if (c.isAfterLast() || c.isClosed() || c.getCount()<1)
		{
			/*
			if (c.isAfterLast())
				Log.e("###", "Cursor was after last");
			if (c.isClosed())
				Log.e("###", "Cursor was closed");
			if (c.getCount() < 1)
				Log.e("###", "Cursor count was below 1");
			*/

			if (!c.isClosed())
				c.close();
			return returnArray;
		}


		//That explains why the default position is -1, so you can make a simple loop like this
		//Makes sense.
		while(c.moveToNext())
		{

			DatabaseItem newEntry = tableInfo.item.getNewInstance();

			newEntry.populate(dbWrap,c);

			returnArray.add(newEntry);
		}

		return returnArray;
	}


	public static ArrayList<? extends DatabaseItem> getAllItems(DatabaseWrapper db, TableInfo tableInfo)
	{
		Cursor c = getCursorToAllItems(db, tableInfo);
		ArrayList<? extends DatabaseItem> items =  getItemsFromCursor(db, tableInfo, c);
		c.close();
		return items;
	}



	public static void addAllItems(DatabaseWrapper db, TableInfo tableInfo, ArrayList<? extends DatabaseItem> items)
	{
		db.beginTransaction();

		for (DatabaseItem item : items)
		{
			addItem(db, item, tableInfo);
		}

		db.setTransactionSuccessful();
		db.endTransaction();
	}

	//Updates if ID is != -1, otherwise adds as new item
	public static void updateAllItems(DatabaseWrapper db, ArrayList<? extends DatabaseItem> items, TableInfo tableInfo, boolean createIfNotFound)
	{
		db.beginTransaction();

		for (DatabaseItem currentItem : items)
		{
			//if (currentItem.get_needsUpdate() )
			{
				updateItem(db, currentItem, tableInfo, createIfNotFound);
			}
		}

		db.setTransactionSuccessful();
		db.endTransaction();
	}

}
