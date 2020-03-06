package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.R;


//If that's what it fucking takes to get a line break in a summary!
public class MultilineSummaryPreference extends Preference
{

	String mTopSummary = null;
	String mBottomSummary = null;

	public MultilineSummaryPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		handleAttrs(attrs);
	}

	public MultilineSummaryPreference(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		handleAttrs(attrs);
	}

	public MultilineSummaryPreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		handleAttrs(attrs);
	}

	public MultilineSummaryPreference(Context context)
	{
		super(context);
	}

	private void handleAttrs(AttributeSet attrs)
	{
		TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.MultilineSummaryPreference);
		mTopSummary = a.getString(R.styleable.MultilineSummaryPreference_top_summary);
		mBottomSummary = a.getString(R.styleable.MultilineSummaryPreference_bottom_summary);
		a.recycle();
	}


	@Override
	public void onBindViewHolder(PreferenceViewHolder holder)
	{
		super.onBindViewHolder(holder);

		final TextView summaryView = (TextView) holder.findViewById(android.R.id.summary);

		if (mTopSummary != null && mBottomSummary != null)
		{
			summaryView.setText(mTopSummary +"\n" + mBottomSummary);
			summaryView.setVisibility(View.VISIBLE);
		}
	}


}
