package com.mayulive.swiftkeyexi.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.database.WrappedProvider;


public class Provider extends ContentProvider
{
	public static final String PROVIDER_DATABASE_PATH = "database";
	public static final Uri PROVIDER_DATABASE_URI = Uri.parse("content://"+ ExiModule.PACKAGE+".database/"+PROVIDER_DATABASE_PATH);

    // Creates a UriMatcher object.
    private static final UriMatcher mDatabaseUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static 
    {
        //For fetching anything that accesses a database
        mDatabaseUriMatcher.addURI(ExiModule.PACKAGE+".database", PROVIDER_DATABASE_PATH+"/*", 0);
    }
	
	@Override
	public boolean onCreate() 
	{
		//Turns out these aren't necessary. Without any permissions defined pretty much anyone can access it, Apparently.
		//Guess you need to handle access permissions yourself somehow.
		//this.getContext().grantUriPermission("com.touchtype.swiftkey.beta", EmojiPanelEntry.CONTENT_URI, Intent.FLAG_GRANT_READ_URI_PERMISSION);
		//this.getContext().grantUriPermission("com.touchtype.swiftkey.beta", EmojiPanelEntry.CONTENT_URI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) 
	{
		if ( !ProviderSecurity.isAllowed( this.getContext(), Binder.getCallingUid() ) )
			return null;

		switch  (mDatabaseUriMatcher.match(uri) )
		{
			case 0:
			{
				DatabaseWrapper dbWrap = DatabaseHolder.getWrapped(this.getContext());

				return dbWrap.query(uri.getLastPathSegment(),
						null,
						selection,
						selectionArgs,
						null,
						null,
						sortOrder
				);
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

		DatabaseWrapper dbWrap = DatabaseHolder.getWrapped(this.getContext());

		long newId = dbWrap.insert(uri.getLastPathSegment(), null, values);
		return uri.buildUpon().appendPath(String.valueOf( newId ) ).build();
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs)
	{
		if ( !ProviderSecurity.isAllowed( this.getContext(), Binder.getCallingUid() ) )
			return -1;

		DatabaseWrapper dbWrap = DatabaseHolder.getWrapped(this.getContext());

		return dbWrap.delete(uri.getLastPathSegment(),selection,selectionArgs);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
	{
		if ( !ProviderSecurity.isAllowed( this.getContext(), Binder.getCallingUid() ) )
			return -1;

		DatabaseWrapper dbWrap = DatabaseHolder.getWrapped(this.getContext());

		return dbWrap.update(uri.getLastPathSegment(), values, selection,selectionArgs);
	}

	public static WrappedProvider getWrapped(Context context)
	{
		return new WrappedProvider(context, PROVIDER_DATABASE_URI);
	}


}
