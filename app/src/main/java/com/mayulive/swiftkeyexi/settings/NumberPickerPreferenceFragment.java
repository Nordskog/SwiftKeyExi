package com.mayulive.swiftkeyexi.settings;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.mayulive.swiftkeyexi.R;

/**
 * Created by Roughy on 6/17/2017.
 */

public class NumberPickerPreferenceFragment extends PreferenceDialogFragmentCompat
{

	private NumberPicker mNumberPicker;

	@Override
	protected View onCreateDialogView(Context context) {


		LayoutInflater inflater =
				(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.number_picker_dialog, null);

		mNumberPicker = (NumberPicker) view.findViewById(R.id.number_picker);

		// No keyboard popup
		mNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		return view;
	}

	@Override
	protected void onBindDialogView(View v)
	{
		super.onBindDialogView(v);

		NumberPickerPreference pref = (NumberPickerPreference) getPreference();
		mNumberPicker.setMaxValue(pref.mMax);
		mNumberPicker.setMinValue(pref.mMin);
		mNumberPicker.setValue(pref.getPersisted());
		mNumberPicker.setWrapSelectorWheel(false);

	}


	@Override
	public void onDialogClosed(boolean positiveResult) {
		if (positiveResult)
		{
			NumberPickerPreference pref = (NumberPickerPreference) getPreference();

			pref.mCurrentValue = mNumberPicker.getValue();
			pref.persistIntValue(pref.mCurrentValue);
		}
	}


	public static NumberPickerPreferenceFragment newInstance(Preference preference) {
		NumberPickerPreferenceFragment fragment = new NumberPickerPreferenceFragment();
		Bundle bundle = new Bundle(1);
		bundle.putString("key", preference.getKey());
		fragment.setArguments(bundle);
		return fragment;
	}


}
