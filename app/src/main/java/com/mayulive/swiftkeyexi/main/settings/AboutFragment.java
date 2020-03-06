package com.mayulive.swiftkeyexi.main.settings;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;

/**
 * Created by Roughy on 1/4/2017.
 */

public class AboutFragment extends PreferenceFragmentCompat
{

	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
	{
		PreferenceManager prefMgr = getPreferenceManager();
		prefMgr.setSharedPreferencesName(SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);

		addPreferencesFromResource(R.xml.preferences_about);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		getActivity().setTitle(R.string.pref_about_title);
	}
}
