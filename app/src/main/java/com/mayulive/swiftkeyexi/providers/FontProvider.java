package com.mayulive.swiftkeyexi.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.FontLoader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Roughy on 2/5/2017.
 */

public class FontProvider extends ContentProvider
{
	private static String LOGTAG = ExiModule.getLogTag(FontProvider.class);

	private static final String FONTS_PATH = "fonts";

	private static final Uri FONT_PROVIDER_URI = Uri.parse("content://"+ExiModule.PACKAGE+".fonts/"+FONTS_PATH);

	// Creates a UriMatcher object.
	private static final UriMatcher mAssetUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

	static
	{
		mAssetUriMatcher.addURI(ExiModule.PACKAGE+".fonts", FONTS_PATH+"/*", 0);
	}


	@Override
	public boolean onCreate()
	{
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
	{
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
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
	{
		return 0;
	}


	@Override
	public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException
	{
		AssetManager am = getContext().getAssets();
		String file_name = uri.getLastPathSegment();

		switch(mAssetUriMatcher.match(uri))
		{
			case 0:
			{
				file_name = "fonts/"+file_name;
				break;
			}

			default:
			{


			}
		}

		if(file_name == null)
			throw new FileNotFoundException();
		AssetFileDescriptor afd = null;
		try {
			afd = am.openFd(file_name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return afd;
	}

	public static Typeface getFont(Context context, String fontName)
	{
		try
		{
			AssetFileDescriptor asset = context.getContentResolver().openAssetFileDescriptor(FONT_PROVIDER_URI.buildUpon().appendPath(fontName).build(), "r");

			return FontLoader.loadFont(context, asset.createInputStream(), fontName);
		}
		catch ( Exception e)
		{
			Log.e(LOGTAG, "Provider failed to provide requested font: "+fontName);
			e.printStackTrace();

			return null;
		}
	}

}
