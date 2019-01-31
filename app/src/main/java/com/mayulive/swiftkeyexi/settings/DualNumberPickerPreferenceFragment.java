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
import android.widget.TextView;

import com.mayulive.swiftkeyexi.R;

import java.lang.reflect.Field;

/**
 * Created by Roughy on 6/17/2017.
 */

public class DualNumberPickerPreferenceFragment extends PreferenceDialogFragmentCompat
{

	private NumberPicker mNumberPickerA;
	private NumberPicker mNumberPickerB;

	private TextView mNumnberPickerA_title;
	private TextView mNumnberPickerB_title;

	@Override
	protected View onCreateDialogView(Context context) {


		LayoutInflater inflater =
				(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dual_number_picker_dialog, null);

		mNumberPickerA = (NumberPicker) view.findViewById(R.id.number_picker_A);
		mNumberPickerB = (NumberPicker) view.findViewById(R.id.number_picker_B);

		mNumnberPickerA_title = view.findViewById(R.id.number_picker_A_title);
		mNumnberPickerB_title = view.findViewById(R.id.number_picker_B_title);

		setupPicker(mNumberPickerA);
		setupPicker(mNumberPickerB);

		return view;
	}

	private void setupPicker( NumberPicker picker )
	{
		picker.setFormatter(new NumberPicker.Formatter()
		{
			@Override
			public String format(int i)
			{
				return DualNumberPickerPreferenceFragment.this.formatString(i);
			}
		});

		// No keyboard popup
		picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		//First value ignores formatter, and google never fixes anything.
		//https://stackoverflow.com/a/26797732/2312367
		try
		{

			Field f = NumberPicker.class.getDeclaredField("mInputText");
			f.setAccessible(true);
			EditText inputText = (EditText)f.get(picker);
			inputText.setFilters(new InputFilter[0]);
		}
		catch (Exception ex)
		{
			//Not the end of the world.
		}
	}


	@Override
	protected void onBindDialogView(View v)
	{
		super.onBindDialogView(v);

		DualNumberPreference pref = (DualNumberPreference) getPreference();
		mNumberPickerA.setMaxValue(pref.getMax());
		mNumberPickerB.setMaxValue(pref.getMax());

		mNumberPickerA.setMinValue(pref.getMin());
		mNumberPickerB.setMinValue(pref.getMin());

		mNumberPickerA.setValue(pref.getValueA());
		mNumberPickerB.setValue(pref.getValueB());

		mNumberPickerA.setWrapSelectorWheel(false);
		mNumberPickerB.setWrapSelectorWheel(false);

		mNumnberPickerA_title.setText( pref.getTitleA() );
		mNumnberPickerB_title.setText( pref.getTitleB() );

	}


	@Override
	public void onDialogClosed(boolean positiveResult) {
		if (positiveResult)
		{
			DualNumberPreference pref = (DualNumberPreference) getPreference();
			pref.persistValue(mNumberPickerA.getValue(), mNumberPickerB.getValue());
		}
	}


	public static DualNumberPickerPreferenceFragment newInstance(DualNumberPreference preference) {
		DualNumberPickerPreferenceFragment fragment = new DualNumberPickerPreferenceFragment();
		Bundle bundle = new Bundle(1);
		bundle.putString("key", preference.getKeyA());	// Just to keep it happy
		fragment.setArguments(bundle);
		return fragment;
	}

	private String formatString(int val)
	{
		DualNumberPreference pref = (DualNumberPreference) getPreference();
		if (pref != null)
		{
			return pref.format(val);
		}
		else
		{
			return String.valueOf(val);
		}
	}

	public interface DualNumberPreference
	{
		String getKeyA();
		String getKeyB();
		int getMin();
		int getMax();
		int getValueA();
		int getValueB();
		String getTitleA();
		String getTitleB();
		void persistValue(int valA, int valB);
		String format(int val);
	}


}
