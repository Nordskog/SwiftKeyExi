package com.mayulive.swiftkeyexi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Roughy on 2/6/2017.
 */

public class WrappedProvider implements DatabaseWrapper
{
	private Uri mBaseUri = null;
	private Context mContext = null;

	public WrappedProvider(Context context, Uri baseUri)
	{
		mBaseUri = baseUri;
		mContext = context;
	}

	public long insert(String table, String nullColumnHack, ContentValues values)
	{
		return Long.parseLong( mContext.getContentResolver().insert( mBaseUri.buildUpon().appendPath(table).build(),values ).getLastPathSegment() );
	}

	@Override
	public int update(String table, ContentValues values, String whereClause, String[] whereArgs)
	{
		return mContext.getContentResolver().update(mBaseUri.buildUpon().appendPath(table).build(), values, whereClause, whereArgs);
	}

	@Override
	public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
	{
		return mContext.getContentResolver().query( mBaseUri.buildUpon().appendPath(table).build(),null, selection,selectionArgs,orderBy );
	}

	@Override
	public int delete(String table, String whereClause, String[] whereArgs)
	{
		return mContext.getContentResolver().delete(mBaseUri.buildUpon().appendPath(table).build(),whereClause,whereArgs);
	}

	@Override
	public boolean deleteTable(String tableName)
	{
		throw new UnsupportedOperationException("Provider does not support deleting tables");
	}

	@Override
	public boolean createTable(String tableName, String tableDefinition)
	{
		throw new UnsupportedOperationException("Provider does not support adding tables");
	}

	@Override
	public void beginTransaction()
	{

	}

	@Override
	public void endTransaction()
	{

	}

	@Override
	public void setTransactionSuccessful()
	{

	}
}
