package com.mayulive.swiftkeyexi.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.mayulive.swiftkeyexi.SharedTheme;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;

/**
 * Created by Roughy on 6/20/2017.
 */

public class Theme
{
	private static int mCurrentTheme = R.style.AppTheme;


	public static int getTheme(String themeString)
	{
		if (themeString.equalsIgnoreCase("LIGHT"))
			return  R.style.AppThemeLight;
		else
			return  R.style.AppTheme;
	}


	//Just sets the theme
	public static void setTheme( ThemeApplicable activity, int theme)
	{
		//On the config app side, this is responsbile for setting the
		//SharedTheme values
		if (theme == R.style.AppTheme)
			SharedTheme.setCurrenThemeType( activity.getContext(), SharedTheme.DARK_THEME_IDENTIFIER);
		else
			SharedTheme.setCurrenThemeType(activity.getContext(), SharedTheme.LIGHT_THEME_IDENTIFIER);

		mCurrentTheme = theme;
		activity.setAppliedTheme(theme);
		activity.setTheme(theme);
	}

	//Check if assigned theme differs from current configured theme,
	//modifies and re-creates activity as necessary. Call from onResume.
	//Returns true if activity recreated
	public static boolean applyThemeIfNecessary(ThemeApplicable activity)
	{
		if (  activity.getAppliedTheme() != mCurrentTheme)
		{
			setTheme(activity, mCurrentTheme);
			activity.recreate();
			return true;
		}

		return false;
	}

	public static int getThemeResId(Activity activity, String SharedPref)
	{
		SharedPreferences prefs = SettingsCommons.getSharedPreferences(activity, SharedPref);
		return Theme.getTheme(  prefs.getString(PreferenceConstants.pref_app_theme_key, "DARK") );
	}

	public interface ThemeApplicable
	{
		//Set resid ref since we can't get it from activity or context
		int getAppliedTheme();
		void setAppliedTheme(int themeResId);

		//Exist already
		void setTheme(int themeResId);
		void recreate();
		Context getContext();
	}

}
