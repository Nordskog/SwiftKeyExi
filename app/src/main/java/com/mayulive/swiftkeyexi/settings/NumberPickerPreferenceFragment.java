package com.mayulive.swiftkeyexi.settings;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.mayulive.swiftkeyexi.R;

import java.lang.reflect.Field;

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

		mNumberPicker.setFormatter(new NumberPicker.Formatter()
		{
			@Override
			public String format(int i)
			{
				return NumberPickerPreferenceFragment.this.formatString(i);
			}
		});

		// No keyboard popup
		mNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		//First value ignores formatter, and google never fixes anything.
		//https://stackoverflow.com/a/26797732/2312367
		try
		{

			Field f = NumberPicker.class.getDeclaredField("mInputText");
			f.setAccessible(true);
			EditText inputText = (EditText)f.get(mNumberPicker);
			inputText.setFilters(new InputFilter[0]);
		}
		catch (Exception ex)
		{
			//Not the end of the world.
		}

		return view;
	}

	@Override
	protected void onBindDialogView(View v)
	{
		super.onBindDialogView(v);

		NumberPreference pref = (NumberPreference) getPreference();
		mNumberPicker.setMaxValue(pref.getMax());
		mNumberPicker.setMinValue(pref.getMin());
		mNumberPicker.setValue(pref.getValue());
		mNumberPicker.setWrapSelectorWheel(false);

	}


	@Override
	public void onDialogClosed(boolean positiveResult) {
		if (positiveResult)
		{
			NumberPreference pref = (NumberPreference) getPreference();
			pref.persistValue(mNumberPicker.getValue());
		}
	}


	public static NumberPickerPreferenceFragment newInstance(Preference preference) {
		NumberPickerPreferenceFragment fragment = new NumberPickerPreferenceFragment();
		Bundle bundle = new Bundle(1);
		bundle.putString("key", preference.getKey());
		fragment.setArguments(bundle);
		return fragment;
	}

	private String formatString(int val)
	{
		NumberPreference pref = (NumberPreference)getPreference();
		if (pref != null)
		{
			return pref.format(val);
		}
		else
		{
			return String.valueOf(val);
		}
	}

	public interface NumberPreference
	{
		int getMin();
		int getMax();
		int getValue();
		void persistValue(int val);
		String format(int val);
	}


}
