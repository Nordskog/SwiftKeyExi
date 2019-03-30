package com.mayulive.swiftkeyexi.database;

import android.content.Context;

/**
 * Created by Roughy on 5/3/2017.
 */

public class DatabaseHolder
{
	private static DatabaseHandler mDbHandler = null;
	private static WrappedDatabase mDbWrap = null;

	public static DatabaseHandler getHolder(Context context)
	{
		if (mDbHandler == null)
		{
			initHandler(context);
		}

		return mDbHandler;
	}

	public static WrappedDatabase getWrapped(Context context)
	{
		if (mDbWrap == null)
		{
			initHandler(context);
		}

		return mDbWrap;
	}

	public static void close(Context context)
	{
		if (mDbHandler == null)
			return;

		DatabaseHandler handler = getHolder(context);
		handler.close();

		mDbHandler = null;
		mDbWrap = null;
	}

	private static void initHandler(Context context)
	{
		mDbHandler = new DatabaseHandler(context);
		mDbHandler.ensureOpen();
		mDbWrap = new WrappedDatabase(mDbHandler.getDatabaseInstance());
	}

}
