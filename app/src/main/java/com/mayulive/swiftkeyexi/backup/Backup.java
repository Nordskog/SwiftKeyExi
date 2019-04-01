package com.mayulive.swiftkeyexi.backup;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.database.DatabaseHandler;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.providers.SoundProvider;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.util.FileUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Backup
{
	private static String LOGTAG = ExiModule.getLogTag(Backup.class);

	private static final String BACKUP_FOLDER_PATH = "backup_work";

	private static final String BACKUP_ZIP_FILE_NAME = "swiftkeyexi_backup.zip";

	public static final String TEMPORARY_SHARED_PREF = ExiModule.PACKAGE+"_backup_restore_prefs";

	private static final String[] soundFilenames = new String[]{
			SoundProvider.BACKSPACE_SOUND_FILENAME,
			SoundProvider.KEYPRESS_SOUND_FILENAME,
			SoundProvider.SPACEBAR_SOUND_FILENAME,
	};

	public static File backupToZip(Context context )
	{
		// Create work directory
		File backupDir = new File(context.getFilesDir(), BACKUP_FOLDER_PATH );
		backupDir.mkdirs();

		// Delete existing files
		for (File file : backupDir.listFiles())
		{
			file.delete();
		}

		///////////////////////
		// Database
		///////////////////////

		// Assumption is made that any writes to the db will be complete at this point.
		// User would have to move super fast for this not to be the case.

		File dbFile = context.getDatabasePath( DatabaseHandler.DATABASE_NAME );
		FileUtils.copyToDir( backupDir, dbFile );

		Log.i(LOGTAG, "Finished backing up DB");

		////////////////////////
		// Prefs
		////////////////////////

		File sharedPrefsFolder = new File( context.getFilesDir().getParentFile(), "shared_prefs"  );
		File prefFile = new File (sharedPrefsFolder, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY+".xml" );

		FileUtils.copyToDir(backupDir, prefFile );

		Log.i(LOGTAG, "Finished backing up Prefs");

		///////////////////
		// Sounds
		//////////////////

		copySounds(context, backupDir);

		Log.i(LOGTAG, "Finished backing up Sounds");

		/////////////////
		// Zio
		////////////////

		File zip = FileUtils.generateZip(backupDir.listFiles(), backupDir, BACKUP_ZIP_FILE_NAME);

		Log.i(LOGTAG, "Finished generating zip");

		return zip;
	}

	private static boolean copySounds( Context context, File outputDir)
	{
		try
		{
			File soundDir = new File(context.getFilesDir(), SoundProvider.SOUND_PATH );

			if (!soundDir.exists())
			{
				Log.i(LOGTAG, "Sounds dir does not exist, will not backup");
				return true;
			}

			for (String filename : soundFilenames)
			{
				File soundFile = new File( soundDir, filename );
				if ( soundFile.exists())
				{
					FileUtils.copyToDir(outputDir, soundFile );
				}
			}


		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean extractZipToBackupFolder(Context context, Uri backupZip)
	{
		boolean ret = false;

		try
		{
			ret = extractZipToBackupFolder(context, context.getContentResolver().openInputStream( backupZip ));
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}


		return ret;
	}

	public static boolean extractZipToBackupFolder(Context context, InputStream backupZip)
	{

		// Create work directory
		File backupDir = new File(context.getFilesDir(), BACKUP_FOLDER_PATH );
		backupDir.mkdirs();

		// Delete existing files
		for (File file : backupDir.listFiles())
		{
			file.delete();
		}

		if ( !FileUtils.Unzip(backupDir, backupZip) )
			return false;

		return true;
	}


	public static boolean restoreFromBackupFolder(Context context)
	{
		File backupDir = new File(context.getFilesDir(), BACKUP_FOLDER_PATH );
		backupDir.mkdirs();

		if ( !restorePrefs(context, backupDir) )
		{
			Log.e(LOGTAG, "Problem restoring prefs, aborting");
			return false;
		}

		if ( !restoreDatabase(context, backupDir) )
		{
			Log.e(LOGTAG, "Problem restoring database, aborting");
			return false;
		}

		// Don't really care about this
		restoreSounds(context, backupDir);

		return true;
	}

	private static boolean restoreSounds( Context context, File backupDir )
	{
		File soundDir = new File(context.getFilesDir(), SoundProvider.SOUND_PATH );
		soundDir.mkdirs();

		// Remove any existing files
		for (File file : soundDir.listFiles())
		{
			file.delete();
		}

		for (File file : backupDir.listFiles())
		{
			// I rename the selected audio file and rename it to something.mp3
			if (file.getName().contains(".mp3"))
			{
				// Ignore failure
				FileUtils.copyToDir(soundDir, file);
			}
		}

		return true;

	}

	private static boolean restoreDatabase( Context context, File backupDir )
	{
		Log.i(LOGTAG, "Restoring database");

		DatabaseHolder.close(context);

		File backupDatabase = new File( backupDir, DatabaseHandler.DATABASE_NAME );
		if (!backupDatabase.exists())
		{
			Log.e(LOGTAG, "Tried to restore database from backup folder but database was not present");
			return false;
		}

		File databaseDir = new File( context.getFilesDir().getParentFile() , "databases"  );
		return FileUtils.copyToDir(databaseDir, backupDatabase);
	}

	private static boolean restorePrefs( Context context, File backupDir )
	{
		Log.i(LOGTAG, "Restoring shared preferences");

		long time = System.currentTimeMillis();
		String tempPrefName = TEMPORARY_SHARED_PREF+"_"+time;	// Excludes extension

		File backupPrefs = new File (backupDir, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY+".xml" );

		File sharedPrefsFolder = new File( context.getFilesDir().getParentFile(), "shared_prefs"  );
		FileUtils.copyToDir(sharedPrefsFolder, backupPrefs, tempPrefName+".xml" );


		SharedPreferences realPrefs = SettingsCommons.getSharedPreferences(context);
		SharedPreferences.Editor realPrefsEditor = realPrefs.edit();
		SharedPreferences tempPrefs = context.getSharedPreferences(tempPrefName, Context.MODE_PRIVATE);

		Map<String, ?> map =  tempPrefs.getAll();


		if (map.isEmpty())
		{
			Log.i(LOGTAG, "Imported pref file was empty, not clearing existing");
			return false;
		}
		else
		{
			Log.i(LOGTAG, "Imported prefs entry count: "+map.size());
		}

		// Do not clear earlier than here
		realPrefsEditor.clear();

		for (Map.Entry<String, ?> entry : map.entrySet())
		{
			Object obj = entry.getValue();

			if ( obj instanceof Long )
			{
				realPrefsEditor.putLong(entry.getKey(), (Long) obj);
			}
			else if ( obj instanceof Float )
			{
				realPrefsEditor.putFloat(entry.getKey(), (Float) obj);
			}
			else if ( obj instanceof Integer )
			{
				realPrefsEditor.putInt(entry.getKey(), (Integer) obj);
			}
			else if ( obj instanceof Boolean )
			{
				realPrefsEditor.putBoolean(entry.getKey(), (Boolean) obj);
			}
			else if ( obj instanceof String )
			{
				realPrefsEditor.putString(entry.getKey(), (String) obj);
			}
			else
			{
				Log.e(LOGTAG, "Unsupported pref type in backup restore");
			}

		}

		// Set any thing_has_changed prefs to current time so the keyboard will update

		long currentTime = System.currentTimeMillis();

		realPrefsEditor.putLong(PreferenceConstants.pref_popup_last_update_key, currentTime );
		realPrefsEditor.putLong(PreferenceConstants.pref_emoji_last_update_key, currentTime );
		realPrefsEditor.putLong(PreferenceConstants.pref_additional_keys_last_update_key, currentTime );
		realPrefsEditor.putLong(PreferenceConstants.pref_sound_keypress_last_update_key, currentTime );
		realPrefsEditor.putLong(PreferenceConstants.pref_quickmenu_last_update_key, currentTime );
		realPrefsEditor.putLong(PreferenceConstants.pref_dictionary_last_update_key, currentTime );
		realPrefsEditor.putLong(PreferenceConstants.pref_hotkeys_last_update_key, currentTime );

		// Do not use apply
		realPrefsEditor.commit();


		File tempPrefFile = new File (sharedPrefsFolder, tempPrefName+".xml" );
		if (tempPrefFile.exists())
		{
			tempPrefFile.delete();
		}
		else
		{
			Log.i(LOGTAG, "Cannot delete temp pref file "+tempPrefFile.getName()+", does not exist");
		}


		return true;
	}

	public static File getLastGeneratedBackup(Context context)
	{
		File backupDir = new File(context.getFilesDir(), BACKUP_FOLDER_PATH );
		File backupZip = new File(backupDir, BACKUP_ZIP_FILE_NAME);

		if (!backupZip.exists())
			return null;

		return backupZip;
	}
}
