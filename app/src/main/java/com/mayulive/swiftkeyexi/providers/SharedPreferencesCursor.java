package com.mayulive.swiftkeyexi.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

//Responsible for formatting certain bits of a data so the provider can ... provide it
class SharedPreferencesCursor implements SharedPreferences
{
	private static final String SHARED_PREF_NAME_FIELD = "shared_pref_name";
	private static final String SHARED_PREF_VALUE_FIELD = "shared_pref_value";

	Map<String,String>  mPreferenceMap = null;

	protected SharedPreferencesCursor(Cursor cursor)
	{
		mPreferenceMap = new HashMap<String,String>();

		int nameFieldIndex = cursor.getColumnIndex(SHARED_PREF_NAME_FIELD);
		int valueFieldIndex = cursor.getColumnIndex(SHARED_PREF_VALUE_FIELD);

		cursor.moveToFirst();
		cursor.moveToPrevious();
		while (cursor.moveToNext())
		{
			mPreferenceMap.put(cursor.getString(nameFieldIndex), cursor.getString(valueFieldIndex));
		}

	}

	//Call from
	protected static Cursor getPreferencesCursor(Context context, String preferenceName)
	{
		SharedPreferences prefs = context.getSharedPreferences(preferenceName, MODE_PRIVATE);

		MatrixCursor matrixCursor = new MatrixCursor(new String[]
				{
						SHARED_PREF_NAME_FIELD,
						SHARED_PREF_VALUE_FIELD,
				});

		Map<String,?> allPrefs =  prefs.getAll();
		for (Map.Entry<String,?> currentPref : allPrefs.entrySet())
		{
			if (currentPref != null && currentPref.getValue() != null)
			{
				matrixCursor.addRow(new Object[]{currentPref.getKey(), currentPref.getValue().toString()});
			}
		}

		return matrixCursor;
	}



	//The majority of the sharedpref implementation consists of stubs.
	//Basically only need the get methods

	@Override
	public Map<String, ?> getAll()
	{
		return null;
	}

	@Nullable
	@Override
	public String getString(String key, String defValue)
	{
		String returnValue = mPreferenceMap.get(key);
		if (returnValue == null)
			return defValue;
		return returnValue;
	}

	@Nullable
	@Override
	public Set<String> getStringSet(String key, Set<String> defValues)
	{
		//Not supported
		return null;
	}

	@Override
	public int getInt(String key, int defValue)
	{
		String returnString = mPreferenceMap.get(key);
		if (returnString == null)
			return defValue;
		return Integer.parseInt(returnString);
	}

	@Override
	public long getLong(String key, long defValue)
	{
		String returnString = mPreferenceMap.get(key);
		if (returnString == null)
			return defValue;
		return Long.parseLong(returnString);
	}

	@Override
	public float getFloat(String key, float defValue)
	{
		String returnString = mPreferenceMap.get(key);
		if (returnString == null)
			return defValue;
		return Float.parseFloat(returnString);
	}

	@Override
	public boolean getBoolean(String key, boolean defValue)
	{
		String returnString = mPreferenceMap.get(key);
		if (returnString == null)
			return defValue;
		if (returnString.equalsIgnoreCase("true"))
			return true;
		else if (returnString.equalsIgnoreCase("false"))
			return false;
		else
			return defValue;
	}

	@Override
	public boolean contains(String key)
	{
		String returnValue = mPreferenceMap.get(key);

		return (returnValue != null);
	}

	@Override
	public Editor edit()
	{
		return null;
	}

	@Override
	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener)
	{

	}

	@Override
	public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener)
	{

	}



}
