package com.mayulive.swiftkeyexi.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

import com.mayulive.swiftkeyexi.R;

public class FloatDualNumberPickerPreference extends DialogPreference implements DualNumberPickerPreferenceFragment.DualNumberPreference
{
	protected float mMin, mMax, mDefault, mCurrentValue_A, mCurrentValue_B = 1;

	protected String mKey_A;
	protected String mKey_B;

	protected String mTitle_A;
	protected String mTitle_B;


	public FloatDualNumberPickerPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray dialogType = context.obtainStyledAttributes(attrs,
				R.styleable.DialogPreference, 0, 0);
		TypedArray numberPickerType = context.obtainStyledAttributes(attrs,
				R.styleable.FloatNumberPickerPreference, 0, 0);
		TypedArray dualNumberPickerType = context.obtainStyledAttributes(attrs,
				R.styleable.DualNumberPickerPreference, 0, 0);

		mMin = numberPickerType.getFloat(R.styleable.FloatNumberPickerPreference_minFloat, 0.5f);
		mMax = numberPickerType.getFloat(R.styleable.FloatNumberPickerPreference_maxFloat, 3);
		mCurrentValue_A = numberPickerType.getFloat(R.styleable.FloatNumberPickerPreference_appDefaultValueFloat, 1);
		mCurrentValue_B = mCurrentValue_A;
		mDefault = mCurrentValue_A;

		mKey_A = dualNumberPickerType.getString(R.styleable.DualNumberPickerPreference_keyA);
		mKey_B = dualNumberPickerType.getString(R.styleable.DualNumberPickerPreference_keyB);

		mTitle_A = dualNumberPickerType.getString(R.styleable.DualNumberPickerPreference_titleA);
		mTitle_B = dualNumberPickerType.getString(R.styleable.DualNumberPickerPreference_titleB);

		dialogType.recycle();
		numberPickerType.recycle();
		dualNumberPickerType.recycle();
	}

	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue)
	{
		if (restoreValue)
		{
			SharedPreferences prefs = SettingsCommons.getSharedPreferences(this.getContext());

			mCurrentValue_A = prefs.getFloat(mKey_A,  mDefault);
			mCurrentValue_B = prefs.getFloat(mKey_B,  mDefault);
		}
	}

	@Override
	public String getKeyA()
	{
		return mKey_A;
	}

	@Override
	public String getKeyB()
	{
		return mKey_B;
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
	public int getValueA()
	{
		return (int)(mCurrentValue_A * 10);
	}

	@Override
	public int getValueB()
	{
		return (int)(mCurrentValue_B * 10);
	}

	@Override
	public String getTitleA()
	{
		return mTitle_A;
	}

	@Override
	public String getTitleB()
	{
		return mTitle_B;
	}


	@Override
	public void persistValue(int valA, int valB)
	{
		mCurrentValue_A = ((float)valA) * 0.1f;
		mCurrentValue_B = ((float)valB) * 0.1f;

		SharedPreferences.Editor prefs = SettingsCommons.getSharedPreferencesEditor(this.getContext());

		prefs.putFloat( mKey_A, mCurrentValue_A );
		prefs.putFloat( mKey_B, mCurrentValue_B );

		prefs.apply();
	}

	@Override
	public String format(int val)
	{
		String returnString =  String.format("%.1f",  ((float)val * 0.1f)  );
		return returnString;
	}
}