package com.mayulive.swiftkeyexi.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;


public class SharedPreferencesProvider extends ContentProvider
{

	//
	private static final String SHARED_PREFERENCES_PATH = "shared_prefs";
	private static final Uri SHARED_PREFERENCES_PROVIDER_URI = Uri.parse("content://"+ ExiModule.PACKAGE+".sharedprefs/"+SHARED_PREFERENCES_PATH);

    // Creates a UriMatcher object.
    private static final UriMatcher mSharedPreferencesUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static 
    {
		mSharedPreferencesUriMatcher.addURI(ExiModule.PACKAGE+".sharedprefs", SHARED_PREFERENCES_PATH, 0);	//Shared prefs, does not support database operations
																											//and should only be accesssed by this provider
    }

	@Override
	public boolean onCreate()
	{
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) 
	{

		switch  (mSharedPreferencesUriMatcher.match(uri) )
		{
			case 0:	//Returns all shared prefs
			{
				return SharedPreferencesCursor.getPreferencesCursor(getContext(), SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);
			}

			default:
			{
				//Nothing
			}
		}

		return null;
    }


	@Override
	public String getType(Uri uri)
	{
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values)
	{
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs)
	{
		return -1;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
	{
		return -1;
	}

	public static SharedPreferences getSharedPreferences(Context context)
	{
		Cursor cursor = context.getContentResolver().query(
				SHARED_PREFERENCES_PROVIDER_URI,     // The content URI of the words table
				null,           // The columns to return for each row
				null,                    		 // Selection criteria
				null,                    		 // Selection criteria
				null);                        	 // The sort order for the returned rows

		return new SharedPreferencesCursor(cursor);


	}
}
