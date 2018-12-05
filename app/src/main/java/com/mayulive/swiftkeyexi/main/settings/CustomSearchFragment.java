package com.mayulive.swiftkeyexi.main.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.providers.SoundProvider;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.util.ArrayUtils;
import com.mayulive.swiftkeyexi.util.view.FilePickerPreference;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Roughy on 1/4/2017.
 */

public class CustomSearchFragment extends PreferenceFragmentCompat
{
	private static String LOGTAG = ExiModule.getLogTag(CustomSearchFragment.class);


	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
	{
		PreferenceManager prefMgr = getPreferenceManager();
		prefMgr.setSharedPreferencesName(SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);

		// Default value crap isn't working. I don't care anymore this will do.
		SharedPreferences prefs = SettingsCommons.getSharedPreferences(this.getContext());
		String prefVal = prefs.getString( PreferenceConstants.pref_custom_search_string_key, CustomSearchStringInputDialog.GOOGLE_SEARCH_STRING);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString( PreferenceConstants.pref_custom_search_string_key, prefVal );
		editor.apply();


		addPreferencesFromResource(R.xml.preferences_search);

		Preference searchStringPreference = findPreference(this.getContext().getResources().getString( R.string.pref_custom_search_string_key ));
		searchStringPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				Context context = CustomSearchFragment.this.getContext();

				SharedPreferences prefs = SettingsCommons.getSharedPreferences(context);
				SharedPreferences.Editor editor = prefs.edit();

				new CustomSearchStringInputDialog(context, prefs.getString( PreferenceConstants.pref_custom_search_string_key, CustomSearchStringInputDialog.GOOGLE_SEARCH_STRING))
				{
					@Override
					public void onEntrySaved(String newString)
					{
						editor.putString( PreferenceConstants.pref_custom_search_string_key, newString );
						editor.apply();
						searchStringPreference.setSummary(newString);
					}
				}.show();


				return true;
			}
		});


	}


	@Override
	public void onResume()
	{
		super.onResume();
		getActivity().setTitle(R.string.pref_section_custom_search_title);
	}


}
