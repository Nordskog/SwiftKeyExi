package com.mayulive.swiftkeyexi.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;

import java.io.File;


public class SoundProvider extends ContentProvider
{

	private static String LOGTAG = ExiModule.getLogTag(SoundProvider.class);

	public static final String SOUND_PATH = "sounds";
	public static final String KEYPRESS_SOUND_FILENAME = "custom_keypress_sound.mp3";
	public static final String SPACEBAR_SOUND_FILENAME = "custom_special_sound.mp3";
	public static final String BACKSPACE_SOUND_FILENAME = "custom_backspace_sound.mp3";

	private static final Uri SOUND_PROVIDER_URI = Uri.parse("content://"+ExiModule.PACKAGE+".sounds/");

	// Creates a UriMatcher object.
	private static final UriMatcher mAssetUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

	static
	{
		mAssetUriMatcher.addURI(ExiModule.PACKAGE+".sounds", "*", 0);
	}


	@Override
	public @Nullable ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode)
	{
		String filename = uri.getLastPathSegment();

		ParcelFileDescriptor returnFile = null;

		try
		{
			String filePath = getContext().getFilesDir()+"/"+SOUND_PATH+"/"+filename;
			File audioFile = new File(filePath);
			returnFile = ParcelFileDescriptor.open(audioFile,ParcelFileDescriptor.MODE_READ_ONLY);
		}
		catch (Exception ex)
		{
			Log.e(LOGTAG, "Could not find requested sound file "+filename);
			ex.printStackTrace();
		}

		return returnFile;
	}



	public static ParcelFileDescriptor getSoundUri(Context context, String name)
	{
		Uri queryUri = Uri.withAppendedPath(SOUND_PROVIDER_URI, name);

		ParcelFileDescriptor returnFile = null;
		try
		{
			returnFile = context.getContentResolver().openFileDescriptor(
					queryUri,     // The content URI of the words table
					"r");

		}
		catch (Exception ex)
		{
			Log.e(LOGTAG, "Failed to get sound file "+name);
		}

		return returnFile;
	}

	////////////
	//Stubs
	/////////////

	@Override
	public boolean onCreate()
	{
		return true;
	}

	@Nullable
	@Override
	public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder)
	{
		return null;
	}

	@Nullable
	@Override
	public String getType(@NonNull Uri uri)
	{
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values)
	{
		return null;
	}

	@Override
	public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs)
	{
		return 0;
	}

	@Override
	public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs)
	{
		return 0;
	}


}
