package com.mayulive.swiftkeyexi.database;

import android.content.ContentValues;
import android.database.Cursor;

public interface DatabaseItem
{
	//*sigh* It amazes me that default implementations and statics
	//were not a thing until java8. Can't use them of course.

	int get_id();
	void set_id(int id);
	
	ContentValues getValues(boolean includeId);

	void populate(DatabaseWrapper dbWrap, Cursor c);

	void createChildTables(DatabaseWrapper dbWrap, String parentTable);
	void deleteChildTables();

	boolean hasChildTables();

	DatabaseItem getNewInstance();
	
}
