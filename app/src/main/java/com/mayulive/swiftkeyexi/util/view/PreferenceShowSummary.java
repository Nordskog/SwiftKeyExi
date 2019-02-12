package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;

import com.mayulive.swiftkeyexi.ExiModule;

/**
 * Created by Roughy on 1/4/2017.
 */

public class PreferenceShowSummary extends Preference
{

	private static String LOGTAG = ExiModule.getLogTag(PreferenceShowSummary.class);

	private String mDefault;

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

	/**
	 *
	 * Don't forget to override me with the correct object type
	 */
	@Override
	protected Object onGetDefaultValue(TypedArray a, int index)
	{
		mDefault = a.getString(index);
		return mDefault;
	}

	@Override
	public CharSequence getSummary()
	{
		return this.getPersistedString(mDefault != null ? mDefault : "..." );
	}

}
