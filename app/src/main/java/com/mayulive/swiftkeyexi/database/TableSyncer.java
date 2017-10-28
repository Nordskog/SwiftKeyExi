package com.mayulive.swiftkeyexi.database;

import java.util.HashMap;

/**
 * Created by Roughy on 10/28/2017.
 */

public class TableSyncer
{

	private static HashMap<String,Long> mTableUpdateTime = new HashMap<>();

	public static boolean needsSync(String tableName, long time)
	{
		if (tableName == null)
			return false;

		Long storedTime = mTableUpdateTime.get(tableName);
		if (storedTime != null && storedTime > time)
		{
			return true;
		}

		return false;
	}

	public static void setTime(String tableName, long time)
	{
		if (tableName == null)
			return;

		mTableUpdateTime.put(tableName,time);
	}

	public static long getTime(String tableName)
	{
		if (tableName == null)
			return 0;

		Long val = mTableUpdateTime.get(tableName);
		return val != null ? val : 0;
	}

	public static void removeTime(String tableName)
	{
		if (tableName == null)
			return;

		mTableUpdateTime.remove(tableName);

	}

}
