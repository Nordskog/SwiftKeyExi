package com.mayulive.swiftkeyexi.database;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Roughy on 2/3/2017.
 */

//Wraps a database or provider
public interface DatabaseWrapper
{

	long insert(String table, String nullColumnHack, ContentValues values);

	int update(String table, ContentValues values, String whereClause, String[] whereArgs);

	Cursor query(String table, String[] columns, String selection,
				 String[] selectionArgs, String groupBy, String having,
				 String orderBy);

	int delete(String table, String whereClause, String[] whereArgs);

	//return true on success
	boolean deleteTable(String tableName);

	//Return true on success
	boolean createTable(String tableName, String tableDefinition);

	void beginTransaction();
	void endTransaction();
	void setTransactionSuccessful();
}
