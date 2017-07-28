package com.mayulive.swiftkeyexi.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Roughy on 2/5/2017.
 */

public class WrappedDatabase implements DatabaseWrapper
{

	private SQLiteDatabase mDb = null;

	//Database is assumed to have been opened
	public WrappedDatabase(SQLiteDatabase db)
	{
		mDb = db;
	}

	@Override
	public long insert(String table, String nullColumnHack, ContentValues values)
	{
		return mDb.insert(table,nullColumnHack,values);
	}

	@Override
	public int update(String table, ContentValues values, String whereClause, String[] whereArgs)
	{
		return mDb.update(table, values, whereClause, whereArgs);
	}

	@Override
	public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
	{
		return mDb.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
	}

	@Override
	public int delete(String table, String whereClause, String[] whereArgs)
	{
		return mDb.delete(table,whereClause,whereArgs);
	}

	@Override
	public boolean deleteTable(String tableName)
	{
		mDb.execSQL("DROP TABLE IF EXISTS " + tableName);

		return true;
	}

	@Override
	public boolean createTable(String tableName, String tableDefinition)
	{
		mDb.execSQL("CREATE TABLE " + tableName + tableDefinition);

		return true;
	}

	@Override
	public void beginTransaction()
	{
		mDb.beginTransaction();
	}

	@Override
	public void endTransaction()
	{
		mDb.endTransaction();
	}

	@Override
	public void setTransactionSuccessful()
	{
		mDb.setTransactionSuccessful();
	}

}
