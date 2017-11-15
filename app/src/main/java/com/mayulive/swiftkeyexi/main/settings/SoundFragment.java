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

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.providers.SoundProvider;
import com.mayulive.swiftkeyexi.settings.NumberPickerPreference;
import com.mayulive.swiftkeyexi.settings.NumberPickerPreferenceFragment;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

	private static final HashSet<Integer> REQUEST_CODES = new HashSet();

	static
	{
		REQUEST_CODES.add(KEYPRESS_SOUND_REQUEST_CODE);
		REQUEST_CODES.add(SPACEBAR_SOUND_REQUEST_CODE);
		REQUEST_CODES.add(BACKSPACE_SOUND_REQUEST_CODE);
	}

	Preference mKeypressPathPref = null;
	Preference mSpacebarPathPref = null;
	Preference mBackspacePathPref = null;

	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
	{
		PreferenceManager prefMgr = getPreferenceManager();
		prefMgr.setSharedPreferencesName(SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);

		addPreferencesFromResource(R.xml.preferences_sound);

		mKeypressPathPref = this.findPreference(PreferenceConstants.pref_sound_custom_keypress_path_key);
		mKeypressPathPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				showSelectSoundFileDialog(KEYPRESS_SOUND_REQUEST_CODE);
				return true;
			}
		});

		mSpacebarPathPref = this.findPreference(PreferenceConstants.pref_sound_custom_spacebar_path_key);
		mSpacebarPathPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				showSelectSoundFileDialog(SPACEBAR_SOUND_REQUEST_CODE);
				return true;
			}
		});

		mBackspacePathPref = this.findPreference(PreferenceConstants.pref_sound_custom_backspace_path_key);
		mBackspacePathPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				showSelectSoundFileDialog(BACKSPACE_SOUND_REQUEST_CODE);
				return true;
			}
		});

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
		intent.setType("audio/mpeg");

		startActivityForResult(intent, requestCode);
	}

	private void setLastUpdateTime()
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext());
		editor.putLong(PreferenceConstants.pref_sound_keypress_last_update_key, System.currentTimeMillis());
		editor.apply();
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
				prefWidget = mKeypressPathPref;
				prefKey = PreferenceConstants.pref_sound_custom_keypress_path_key;
				fileName = SoundProvider.KEYPRESS_SOUND_FILENAME;
				break;
			}
			case SPACEBAR_SOUND_REQUEST_CODE  :
			{
				prefWidget = mSpacebarPathPref;
				prefKey = PreferenceConstants.pref_sound_custom_spacebar_path_key;
				fileName = SoundProvider.SPACEBAR_SOUND_FILENAME;
				break;
			}
			case BACKSPACE_SOUND_REQUEST_CODE :
			{
				prefWidget = mBackspacePathPref;
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
		if (REQUEST_CODES.contains(requestCode) ) // && resultCode == Activity.RESULT_OK)
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
