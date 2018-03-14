package com.mayulive.swiftkeyexi.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

import com.mayulive.swiftkeyexi.R;

public class OpacityPreference extends DialogPreference implements OpacityPreferenceFragment.SeekbarPreference
{
	protected int mMax, mCurrentValue;

	public OpacityPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray dialogType = context.obtainStyledAttributes(attrs,
				R.styleable.DialogPreference, 0, 0);
		TypedArray SeekbarDialogPreferenceType = context.obtainStyledAttributes(attrs,
				R.styleable.OpacityPreference, 0, 0);

		mMax = SeekbarDialogPreferenceType.getInt(R.styleable.OpacityPreference_opacityPreference_max, 100);
		int defaultValue = SeekbarDialogPreferenceType.getInt(R.styleable.OpacityPreference_opacityPreference_default, 0);

		mCurrentValue = dialogType.getInt(R.styleable.Preference_defaultValue, defaultValue);

		dialogType.recycle();
		SeekbarDialogPreferenceType.recycle();
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

}