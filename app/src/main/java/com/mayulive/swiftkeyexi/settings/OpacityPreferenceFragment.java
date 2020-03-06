package com.mayulive.swiftkeyexi.settings;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceDialogFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.mayulive.swiftkeyexi.R;

/**
 * Created by Roughy on 6/17/2017.
 */

public class OpacityPreferenceFragment extends PreferenceDialogFragmentCompat
{

	private SeekBar mSeekBar;
	private FrameLayout mPreview;

	@Override
	protected View onCreateDialogView(Context context) {


		LayoutInflater inflater =
				(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.opacity_preference_layout, null);

		mSeekBar = view.findViewById(R.id.seek_bar);
		mPreview = view.findViewById(R.id.transparency_preview);

		return view;
	}


	private void setPreview(int progress, int max)
	{
		mPreview.setAlpha( ((float)progress / (float)max) );
	}

	@Override
	protected void onBindDialogView(View v)
	{
		super.onBindDialogView(v);

		SeekbarPreference pref = (SeekbarPreference) getPreference();
		mSeekBar.setMax(pref.getMax());
		mSeekBar.setProgress(pref.getValue());

		setPreview(pref.getValue(), pref.getMax());

		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				setPreview(progress, seekBar.getMax());
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{

			}
		});

	}


	@Override
	public void onDialogClosed(boolean positiveResult) {
		if (positiveResult)
		{
			SeekbarPreference pref = (SeekbarPreference) getPreference();
			pref.persistValue(mSeekBar.getProgress());
		}
	}


	public static OpacityPreferenceFragment newInstance(Preference preference) {
		OpacityPreferenceFragment fragment = new OpacityPreferenceFragment();
		Bundle bundle = new Bundle(1);
		bundle.putString("key", preference.getKey());
		fragment.setArguments(bundle);
		return fragment;
	}

	public interface SeekbarPreference
	{
		int getMax();
		int getValue();
		void persistValue(int val);
	}


}
