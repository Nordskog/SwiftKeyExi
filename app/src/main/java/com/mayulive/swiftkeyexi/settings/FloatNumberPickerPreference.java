package com.mayulive.swiftkeyexi.settings;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.preference.DialogPreference;
import android.util.AttributeSet;

import com.mayulive.swiftkeyexi.R;

public class FloatNumberPickerPreference extends DialogPreference implements NumberPickerPreferenceFragment.NumberPreference
{
	protected float mMin, mMax, mCurrentValue = 1;

	public FloatNumberPickerPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray dialogType = context.obtainStyledAttributes(attrs,
				R.styleable.DialogPreference, 0, 0);
		TypedArray numberPickerType = context.obtainStyledAttributes(attrs,
				R.styleable.FloatNumberPickerPreference, 0, 0);

		mMin = numberPickerType.getFloat(R.styleable.FloatNumberPickerPreference_minFloat, 0.5f);
		mMax = numberPickerType.getFloat(R.styleable.FloatNumberPickerPreference_maxFloat, 3);
		mCurrentValue = numberPickerType.getFloat(R.styleable.FloatNumberPickerPreference_appDefaultValueFloat, 1);

		dialogType.recycle();
		numberPickerType.recycle();
	}

	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue)
	{
		if (restoreValue)
		{
			mCurrentValue = getPersistedFloat((int)mCurrentValue);
		}
	}

	public float getPersisted()
	{
		return getPersistedFloat(mCurrentValue);
	}

	public void persisFloatValue(float value)
	{
		persistFloat(value);
	}

	@Override
	public int getMin()
	{
		return (int) (mMin * 10);
	}

	@Override
	public int getMax()
	{
		return (int) (mMax * 10);
	}

	@Override
	public int getValue()
	{
		return (int)(getPersisted() * 10);
	}

	@Override
	public void persistValue(int val)
	{
		mCurrentValue = ((float)val) * 0.1f;
		persisFloatValue( mCurrentValue );
	}

	@Override
	public String format(int val)
	{
		String returnString =  String.format("%.1f",  ((float)val * 0.1f)  );
		return returnString;
	}
}