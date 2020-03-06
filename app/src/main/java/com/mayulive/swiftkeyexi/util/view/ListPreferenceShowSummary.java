package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import android.util.AttributeSet;

/**
 * Created by Roughy on 1/4/2017.
 */

public class ListPreferenceShowSummary extends ListPreference
{

	private final static String TAG = ListPreferenceShowSummary.class.getName();

	public ListPreferenceShowSummary(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ListPreferenceShowSummary(Context context) {
		super(context);
		init();
	}

	private void init() {

		setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference arg0, Object arg1) {
				arg0.setSummary(getEntry());
				return true;
			}
		});
	}

	@Override
	public CharSequence getSummary() {
		return super.getEntry();
	}
}