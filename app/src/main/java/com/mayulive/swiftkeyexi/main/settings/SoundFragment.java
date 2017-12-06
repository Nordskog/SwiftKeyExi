package com.mayulive.swiftkeyexi.main.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.providers.SoundProvider;
import com.mayulive.swiftkeyexi.settings.NumberPickerPreference;
import com.mayulive.swiftkeyexi.settings.NumberPickerPreferenceFragment;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.util.ArrayUtils;
import com.mayulive.swiftkeyexi.util.view.FilePickerPreference;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Roughy on 1/4/2017.
 */

public class SoundFragment extends PreferenceFragmentCompat
{
	private static String LOGTAG = ExiModule.getLogTag(SoundFragment.class);


	private static final int KEYPRESS_SOUND_REQUEST_CODE = 51;
	private static final int SPACEBAR_SOUND_REQUEST_CODE = 52;
	private static final int BACKSPACE_SOUND_REQUEST_CODE = 53;

	private static final int[] REQUEST_CODES = new int[3];
	private static final String[] PREFERENCE_RESOURCES = new String[3];
	private static final FilePickerPreference[] mPreferences = new FilePickerPreference[3];

	static
	{
		REQUEST_CODES[0] = KEYPRESS_SOUND_REQUEST_CODE;
		REQUEST_CODES[1] = SPACEBAR_SOUND_REQUEST_CODE;
		REQUEST_CODES[2] = BACKSPACE_SOUND_REQUEST_CODE;

		PREFERENCE_RESOURCES[0] = PreferenceConstants.pref_sound_custom_keypress_path_key;
		PREFERENCE_RESOURCES[1] = PreferenceConstants.pref_sound_custom_spacebar_path_key;
		PREFERENCE_RESOURCES[2] = PreferenceConstants.pref_sound_custom_backspace_path_key;

	}

	//Preference mKeypressPathPref = null;
	//Preference mSpacebarPathPref = null;
	//Preference mBackspacePathPref = null;

	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
	{
		PreferenceManager prefMgr = getPreferenceManager();
		prefMgr.setSharedPreferencesName(SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);

		addPreferencesFromResource(R.xml.preferences_sound);

		for (int i = 0; i < PREFERENCE_RESOURCES.length; i++)
		{
			final int index = i;

			mPreferences[i] = (FilePickerPreference) this.findPreference(PREFERENCE_RESOURCES[i]);
			mPreferences[i] .setOnPreferenceClickListener(preference ->
			{
				showSelectSoundFileDialog(REQUEST_CODES[index]);
				return true;
			});

			mPreferences[i].addOnPreferenceWidgetClickedListener(pref -> clearPref(REQUEST_CODES[index]));
		}
	}

	//Needed for custom prefs to work... ? I don't even
	@Override
	public void onDisplayPreferenceDialog(Preference preference) {
		DialogFragment fragment;
		if (preference instanceof NumberPickerPreference) {
			fragment = NumberPickerPreferenceFragment.newInstance(preference);
			fragment.setTargetFragment(this, 0);
			fragment.show(getFragmentManager(),
					"android.support.v7.preference.PreferenceFragment.DIALOG");
		} else super.onDisplayPreferenceDialog(preference);
	}

	void showSelectSoundFileDialog(int requestCode)
	{
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

		intent.addCategory(Intent.CATEGORY_OPENABLE);

		//audio/* does not include ogg/oga.
		intent.setType("*/*");
		String[] mimetypes = {"audio/*", "application/ogg", "application/oga"};
		intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);

		startActivityForResult(intent, requestCode);
	}

	private void setLastUpdateTime()
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext());
		editor.putLong(PreferenceConstants.pref_sound_keypress_last_update_key, System.currentTimeMillis());
		editor.apply();
	}

	private void clearPref(int requestCode)
	{
		Preference prefWidget = null;
		String prefKey = null;
		String fileName = null;

		switch(requestCode)
		{
			case KEYPRESS_SOUND_REQUEST_CODE  :
			{
				prefWidget = mPreferences[0];
				prefKey = PreferenceConstants.pref_sound_custom_keypress_path_key;
				fileName = SoundProvider.KEYPRESS_SOUND_FILENAME;
				break;
			}
			case SPACEBAR_SOUND_REQUEST_CODE  :
			{
				prefWidget = mPreferences[1];
				prefKey = PreferenceConstants.pref_sound_custom_spacebar_path_key;
				fileName = SoundProvider.SPACEBAR_SOUND_FILENAME;
				break;
			}
			case BACKSPACE_SOUND_REQUEST_CODE :
			{
				prefWidget = mPreferences[2];
				prefKey = PreferenceConstants.pref_sound_custom_backspace_path_key;
				fileName = SoundProvider.BACKSPACE_SOUND_FILENAME;
				break;
			}
		}

		deleteAudioFile(fileName);

		{
			String displayValue = "...";

			SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext());
			editor.putString(prefKey, displayValue);
			editor.commit();

			prefWidget.setSummary(displayValue);

			setLastUpdateTime();
		}
	}

	private void updatePref(int requestCode, Intent data)
	{
		Preference prefWidget = null;
		String prefKey = null;
		String fileName = null;

		switch(requestCode)
		{
			case KEYPRESS_SOUND_REQUEST_CODE  :
			{
				prefWidget = mPreferences[0];
				prefKey = PreferenceConstants.pref_sound_custom_keypress_path_key;
				fileName = SoundProvider.KEYPRESS_SOUND_FILENAME;
				break;
			}
			case SPACEBAR_SOUND_REQUEST_CODE  :
			{
				prefWidget = mPreferences[1];
				prefKey = PreferenceConstants.pref_sound_custom_spacebar_path_key;
				fileName = SoundProvider.SPACEBAR_SOUND_FILENAME;
				break;
			}
			case BACKSPACE_SOUND_REQUEST_CODE :
			{
				prefWidget = mPreferences[2];
				prefKey = PreferenceConstants.pref_sound_custom_backspace_path_key;
				fileName = SoundProvider.BACKSPACE_SOUND_FILENAME;
				break;
			}
		}

		Uri uri = data.getData();

		if (copyAudioFile(uri, fileName))
		{
			String displayValue = uri.getLastPathSegment();
			//Bugger includes primary: on newer devices
			if (displayValue.contains(":"))
			{
				displayValue = displayValue.split(":")[1];
			}

			SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext());
			editor.putString(prefKey, displayValue);
			editor.commit();

			prefWidget.setSummary(displayValue);

			setLastUpdateTime();
		}
		else
		{
			//TODO display error
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		//Resultcode is always -1 ...
		if ( ArrayUtils.containsInt( REQUEST_CODES, requestCode ) ) // && resultCode == Activity.RESULT_OK)
		{
			if (data != null)
			{
				updatePref(requestCode, data);
			}
			else
			{
				Log.e(LOGTAG, "Could not select audio file beause result was null");
			}
		}
		else
		{
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private void deleteAudioFile(String fileName)
	{
		File soundsDir = new File(getContext().getFilesDir(), SoundProvider.SOUND_PATH );
		soundsDir.mkdirs();
		File soundFile = new File(soundsDir, fileName);

		//Delete file if it exists
		soundFile.delete();
	}

	private boolean copyAudioFile(Uri uri, String toFilename)
	{
			try
			{
				BufferedInputStream bufferStream = new BufferedInputStream( getContext().getContentResolver().openInputStream(uri));

				File soundsDir = new File(getContext().getFilesDir(), SoundProvider.SOUND_PATH );
				soundsDir.mkdirs();

				BufferedOutputStream outputStream = new BufferedOutputStream( new FileOutputStream( new File(soundsDir, toFilename) ) );
				byte[] buffer = new byte[32000];

				int read = -1;
				while ( (read = bufferStream.read(buffer) ) != -1)
				{
					outputStream.write(buffer,0,read);
				}

				outputStream.close();
				bufferStream.close();
			}
			catch (Exception e)
			{
				Log.e(LOGTAG, "Failed to copy custom keypress file");
				e.printStackTrace();

				return false;
			}

			return true;
	}

	@Override
	public void onResume()
	{
		super.onResume();
		getActivity().setTitle(R.string.preferences_key_sound_title);
	}


}
