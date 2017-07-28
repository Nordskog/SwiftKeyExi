package com.mayulive.swiftkeyexi.settings;

import android.support.v7.preference.DialogPreference;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;

import com.mayulive.swiftkeyexi.R;

public class NumberPickerPreference extends DialogPreference
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


}