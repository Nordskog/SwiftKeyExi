package com.mayulive.swiftkeyexi.main.settings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.MainActivity;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.backup.Backup;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.util.FileUtils;

import java.io.File;

/**
 * Created by Roughy on 1/4/2017.
 */

public class BackupAndRestoreFragment extends PreferenceFragmentCompat
{
	private static final int SELECT_OUTPUT_FOLDER = 100;
	private static final int SELECT_RESTORE_ARCHIVE = 200;

	private static String LOGTAG = ExiModule.getLogTag(BackupAndRestoreFragment.class);


	private enum BackupType
	{
		SHARE, FILE
	}

	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
	{
		PreferenceManager prefMgr = getPreferenceManager();
		prefMgr.setSharedPreferencesName(SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);

		addPreferencesFromResource(R.xml.preferences_backup_restore);

		Preference resetPref = findPreference( this.getContext().getResources().getString( R.string.pref_reset_key ) );
		resetPref.setOnPreferenceClickListener(preference ->
		{
			SettingsActivity.displayFragment( BackupAndRestoreFragment.this.getFragmentManager(), new ResetFragment() );
			return true;
		});

		Preference backupPref = findPreference( this.getContext().getResources().getString( R.string.pref_backup_to_zip_key ) );
		backupPref.setOnPreferenceClickListener(preference ->
		{
			displayBackupDialog();
			return true;
		});

		Preference restorePref = findPreference( this.getContext().getResources().getString( R.string.pref_restore_from_zip_key ) );
		restorePref.setOnPreferenceClickListener(preference ->
		{
			handleRestoreSelect();
			return true;
		});


	}


	@Override
	public void onResume()
	{
		super.onResume();
		getActivity().setTitle(R.string.pref_backup_and_restore_title);
	}


	////////////////////////
	// Backup
	////////////////////////

	// ZIP


	private void handleBackupSelect( BackupType type )
	{
		// Zip file generated in same location regardless
		File zipFile = Backup.backupToZip(this.getContext());

		if (zipFile == null)
		{
			displayBackupErrorToast();
			Log.i(LOGTAG, "Failed to generate zip");
			return;

		}

		if ( type == BackupType.FILE )
		{
			Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
			startActivityForResult(intent, SELECT_OUTPUT_FOLDER);

			// Calls handleZipBackupToFile() on result
		}
		else
		{
			// TODO share flow
			shareFile(zipFile);
		}




	}

	private void handleZipBackupToFile(DocumentFile outputDir )
	{
		try
		{
			File zipFile = Backup.getLastGeneratedBackup(this.getContext());

			if (zipFile == null)
			{
				displayBackupErrorToast();
				Log.i(LOGTAG, "Could not locate zip file");
				return;
			}

			if ( !FileUtils.copyToDir(this.getContext(), outputDir, DocumentFile.fromFile(zipFile)) )
			{
				displayBackupErrorToast();
				return;
			}

		}
		catch (Exception ex )
		{
			displayBackupErrorToast();
			ex.printStackTrace();
			return;
		}


		displayBackupSuccessToast();
		Log.i(LOGTAG, "Finished exporting");
	}

	//////////////////////
	// Restore
	/////////////////////

	private void handleRestoreSelect()
	{
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

		intent.addCategory(Intent.CATEGORY_OPENABLE);

		//audio/* does not include ogg/oga.
		intent.setType("application/zip");

		startActivityForResult(intent, SELECT_RESTORE_ARCHIVE);

	}

	private void handleRestore( Uri uri )
	{
		if ( !Backup.extractZipToBackupFolder( this.getContext(), uri) )
		{
			displayRestoreErrorToast();
			Log.i(LOGTAG, "Failed to extract zip");
			return;
		}

		// Database will be closed after the next step.
		// restartApp() MUST be called regardless of failure or success.

		displayRestoreWarning( () ->
		{

			// Remove pref listener from activity so it doesn't start doing things while we're restoring things.
			// I should have left it a separate activity.
			SettingsCommons.getSharedPreferences(this.getContext(), SettingsCommons.MODULE_SHARED_PREFERENCES_KEY).unregisterOnSharedPreferenceChangeListener( ( SettingsActivity )getActivity() );

			if ( !Backup.restoreFromBackupFolder(this.getContext()))
			{
				displayRestoreErrorToast();
				Log.i(LOGTAG, "Failed to restore from backup folder");

				restartApp(); // Always restart.
				return;
			}

			displayRestoreSuccessToast();

			// Nukes app
			restartApp();

		} );


	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == Activity.RESULT_OK && requestCode == SELECT_OUTPUT_FOLDER)
		{
			Uri treeUri = data.getData();

			if (treeUri == null)
			{
				Log.i(LOGTAG, "Backup got user-selected directory but it was null");
				return;
			}

			// Special uri.. to special file.. to path.. to real file directory
			DocumentFile uri = DocumentFile.fromTreeUri(this.getContext(), treeUri);

			handleZipBackupToFile(uri);
		}
		else if ( requestCode == SELECT_RESTORE_ARCHIVE && resultCode == Activity.RESULT_OK )
		{

			Uri uri = data.getData();
			if (uri != null)
			{
				handleRestore(uri);
			}

		}
		else
		{
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private void displayBackupErrorToast()
	{
		Toast.makeText(this.getContext(), R.string.toast_backup_fail, Toast.LENGTH_LONG).show();
	}

	private void displayBackupSuccessToast()
	{
		Toast.makeText(this.getContext(), R.string.toast_backup_success, Toast.LENGTH_LONG).show();
	}

	private void displayRestoreErrorToast()
	{
		Toast.makeText(this.getContext(), R.string.toast_restore_fail, Toast.LENGTH_LONG).show();
	}

	private void displayRestoreSuccessToast()
	{
		Toast.makeText(this.getContext(), R.string.toast_restore_success, Toast.LENGTH_LONG).show();
	}

	private void restartApp()
	{
		// Make sure everything is nuked so nothing is feeding off the old database handle
		// This does not cover the providers, but anything that accesses the db there does not hold on to it.
		Intent intent = new Intent(this.getContext(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK );
		startActivity(intent);
	}

	void displayRestoreWarning( Runnable onOkCallback)
	{
		new AlertDialog.Builder(this.getContext())

				.setTitle(R.string.restore)
				.setMessage(R.string.restore_backup_warning)
				.setPositiveButton(R.string.button_ok, (dialogInterface, i) -> onOkCallback.run())
				.setNegativeButton(R.string.button_cancel, (dialogInterface, i) ->
				{
					// Do nothing
				})
				.show();
	}

	void displayBackupDialog()
	{
		LayoutInflater inflater = LayoutInflater.from(this.getContext());

		View dialogView = inflater.inflate(R.layout.backup_selection_dialog, null,false);

		AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext())

				.setView(dialogView)
				.setTitle(R.string.backup)
				.setNegativeButton(R.string.button_cancel, (dialogInterface, i) ->
				{
					// Do nothing
				});

		final AlertDialog dialog = builder.show();

		dialogView.findViewById(R.id.backup_to_file_button).setOnClickListener(v ->
		{
			dialog.dismiss();
			handleBackupSelect( BackupType.FILE );
		} );
		dialogView.findViewById(R.id.backup_share_button).setOnClickListener(v ->
		{
			dialog.dismiss();
			handleBackupSelect( BackupType.SHARE );
		});

	}


	private void shareFile(File file) {

		Uri uri = FileProvider.getUriForFile(this.getContext(), this.getContext().getPackageName()+".backup", file);

		Intent intent = ShareCompat.IntentBuilder.from(this.getActivity())
				.setStream(uri) // uri from FileProvider
				.setType("application/zip")
				.getIntent()
				.setAction(Intent.ACTION_SEND) //Change if needed
				.setDataAndType(uri, "application/zip")
				.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

		startActivity(intent);
	}
}
