package com.mayulive.swiftkeyexi.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;

import java.util.Map;


public class SharedPreferencesProvider extends ContentProvider
{
	private static String LOGTAG = ExiModule.getLogTag(SharedPreferencesProvider.class);

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

		if ( !ProviderSecurity.isAllowed( this.getContext(), Binder.getCallingUid() ) )
			return null;

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
		if ( !ProviderSecurity.isAllowed( this.getContext(), Binder.getCallingUid() ) )
			return null;

		switch  (mSharedPreferencesUriMatcher.match(uri) )
		{
			case 0:	//Returns all shared prefs
			{
				try
				{
					SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(getContext());

					for (Map.Entry<String, Object> entry : values.valueSet())
					{
						Object obj = entry.getValue();
						if ( obj instanceof Long )
						{
							editor.putLong(entry.getKey(), (Long) obj);
						}
						else if ( obj instanceof Float )
						{
							editor.putFloat(entry.getKey(), (Float) obj);
						}
						else if ( obj instanceof Integer )
						{
							editor.putInt(entry.getKey(), (Integer) obj);
						}
						else if ( obj instanceof Boolean )
						{
							editor.putBoolean(entry.getKey(), (Boolean) obj);
						}
						else if ( obj instanceof String )
						{
							editor.putString(entry.getKey(), (String) obj);
						}
						else
						{
							Log.e(LOGTAG, "Unsupported type in SharedPref provider insert");
						}
					}

					editor.apply();
				}
				catch ( Exception ex )
				{
					Log.e(LOGTAG, "Problem inserting shared pref");
					ex.printStackTrace();
				}


			}

			default:
			{
				//Nothing
			}
		}

		// Always returns null
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


	public static <T> void setPreference( Context context, String key, T value )
	{
		ContentValues values = new ContentValues();

		if ( value instanceof Long )
		{
			values.put(key, (Long) value);
		}
		else if ( value instanceof Float )
		{
			values.put(key, (Float) value);
		}
		else if ( value instanceof Integer )
		{
			values.put(key, (Integer) value);
		}
		else if ( value instanceof Boolean )
		{
			values.put(key, (Boolean) value);
		}
		else if ( value instanceof String )
		{
			values.put(key, (String) value);
		}
		else
		{
			Log.e(LOGTAG, "Unsupported type in SharedPref provider setPreference");
		}

		context.getContentResolver().insert(SHARED_PREFERENCES_PROVIDER_URI, values );
	}

	public static SharedPreferences getSharedPreferences(Context context)
	{
		Cursor cursor = context.getContentResolver().query(
				SHARED_PREFERENCES_PROVIDER_URI,     // The content URI of the words table
				null,           // The columns to return for each row
				null,                    		 // Selection criteria
				null,                    		 // Selection criteria
				null);                        	 // The sort order for the returned rows

		SharedPreferencesCursor prefCursor = new SharedPreferencesCursor(cursor);
		cursor.close();
		return prefCursor;
	}
}
