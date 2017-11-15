package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.settings.SoundFragment;

/**
 * Created by Roughy on 1/4/2017.
 */

public class PreferenceShowSummary extends Preference
{

	private static String LOGTAG = ExiModule.getLogTag(PreferenceShowSummary.class);

	public PreferenceShowSummary(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public PreferenceShowSummary(Context context)
	{
		super(context);
		init();
	}

	private void init()
	{
		setOnPreferenceChangeListener(new OnPreferenceChangeListener()
		{

			@Override
			public boolean onPreferenceChange(Preference arg0, Object arg1)
			{
				arg0.setSummary(arg1.toString());
				return true;
			}
		});
	}

	@Override
	public CharSequence getSummary()
	{
		return this.getPersistedString("...");
	}

}
