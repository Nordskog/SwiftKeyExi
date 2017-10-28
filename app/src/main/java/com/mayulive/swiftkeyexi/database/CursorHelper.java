package com.mayulive.swiftkeyexi.database;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roughy on 10/19/2017.
 */

public class CursorHelper
{
	//Given a cursor with a single column, specifically a string, return the list of strings as a ... list
	public static List<String> cursorSingleColumnToString(Cursor c)
	{
		ArrayList<String> strings = new ArrayList<>();

		if (c == null)
		{
			return strings;
		}

		c.moveToFirst();
		c.moveToPrevious();


		if (c.isAfterLast() || c.isClosed() || c.getCount()<1)
		{
			if (!c.isClosed())
				c.close();
			return strings;
		}


		{
			//That explains why the default position is -1, so you can make a simple loop like this
			//Makes sense.
			while(c.moveToNext())
			{
				strings.add( c.getString(0) );
			}
		}

		return strings;
	}


}
