package com.mayulive.swiftkeyexi.util;

import android.database.Cursor;

import com.mayulive.swiftkeyexi.database.DatabaseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roughy on 10/22/2017.
 */

public class CursorUtils
{
	//Given a cursor with only a single column, return the strings in the column as a list
	public static List<String> getStringsFromCursor(Cursor c)
	{
		ArrayList<String> returnArray = new ArrayList<>();

		if (c == null)
		{
			return returnArray;
		}

		c.moveToFirst();
		c.moveToPrevious();


		if (c.isAfterLast() || c.isClosed() || c.getCount()<1)
		{
			if (!c.isClosed())
				c.close();
			return returnArray;
		}

		//That explains why the default position is -1, so you can make a simple loop like this
		//Makes sense.
		while(c.moveToNext())
		{
			returnArray.add(c.getString(0) );
		}

		return returnArray;
	}
}
