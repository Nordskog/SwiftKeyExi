package com.mayulive.swiftkeyexi.settings;

import androidx.preference.DialogPreference;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;

import com.mayulive.swiftkeyexi.R;

public class NumberPickerPreference extends DialogPreference implements NumberPickerPreferenceFragment.NumberPreference
{
	protected int mMin, mMax, mCurrentValue;

	public NumberPickerPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray dialogType = context.obtainStyledAttributes(attrs,
				R.styleable.DialogPreference, 0, 0);
		TypedArray numberPickerType = context.obtainStyledAttributes(attrs,
				R.styleable.NumberPickerPreference, 0, 0);

		mMax = numberPickerType.getInt(R.styleable.NumberPickerPreference_max, 5);
		mMin = numberPickerType.getInt(R.styleable.NumberPickerPreference_min, 0);
		int defaultValue = numberPickerType.getInt(R.styleable.NumberPickerPreference_appDefaultValue, 0);

		mCurrentValue = dialogType.getInt(R.styleable.Preference_defaultValue, defaultValue);

		dialogType.recycle();
		numberPickerType.recycle();
	}

	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue)
	{
		if (restoreValue)
		{
			mCurrentValue = getPersistedInt(mCurrentValue);
		}
	}

	public int getPersisted()
	{
		return getPersistedInt(mCurrentValue);
	}

	public void persistIntValue(int value)
	{
		persistInt(value);
	}


	@Override
	public int getMin()
	{
		return mMin;
	}

	@Override
	public int getMax()
	{
		return mMax;
	}

	@Override
	public int getValue()
	{
		return getPersisted();
	}

	@Override
	public void persistValue(int val)
	{
		persistIntValue(val);
	}

	@Override
	public String format(int val)
	{
		return String.valueOf(val);
	}
}