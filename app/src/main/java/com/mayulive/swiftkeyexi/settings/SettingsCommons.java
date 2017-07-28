package com.mayulive.swiftkeyexi.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.mayulive.swiftkeyexi.ExiModule;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Roughy on 1/5/2017.
 */

public class SettingsCommons
{
	public static final String MODULE_SHARED_PREFERENCES_KEY = ExiModule.PACKAGE+"_modulesettings";

	public static SharedPreferences.Editor getSharedPreferencesEditor(Context context, String preference)
	{
		SharedPreferences prefs = context.getSharedPreferences(preference, MODE_PRIVATE);
		return prefs.edit();
	}

	public static SharedPreferences getSharedPreferences(Context context, String preference)
	{
		return context.getSharedPreferences(preference, MODE_PRIVATE);
	}
}
