package com.mayulive.swiftkeyexi.main.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.main.Theme;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;

/**
 * Created by Roughy on 3/9/2017.
 */

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, Theme.ThemeApplicable
{

	WrappedDatabase mDbWrap = null;

	private void setupReferences()
	{
		mDbWrap = DatabaseHolder.getWrapped(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		Theme.setTheme(this, Theme.getThemeResId(this, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY) );

		setContentView(R.layout.settings_activity_layout);

		setupReferences();

		super.onCreate(savedInstanceState);

		if (savedInstanceState != null)
		{
			return;
		}

		SettingsFragment settingsFragment = new SettingsFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.settings_activity_fragment_container, settingsFragment).commit();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		if (key.equals(PreferenceConstants.pref_app_theme_key))
		{
			this.recreate();
		}

		if (key.equals(PreferenceConstants.pref_emoji_force_version_key))
		{
			ExiModule.update(getContext(), mDbWrap);
		}
	}

	@Override
	public void onPause()
	{
		super.onPause();
		SettingsCommons.getSharedPreferences(this, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY).unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		SettingsCommons.getSharedPreferences(this, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY).registerOnSharedPreferenceChangeListener(this);
	}


	@Override
	public int getAppliedTheme()
	{
		//Only changed here, not necessary
		return 0;
	}

	@Override
	public void setAppliedTheme(int themeResId)
	{
		//Only changed here, not necessary
	}

	@Override
	public Context getContext()
	{
		return this;
	}
}
