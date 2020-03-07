package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.R;


//If that's what it fucking takes to get a line break in a summary!
public class ListSummaryPreference extends Preference
{
	CharSequence[] mSummaries = null;

	public ListSummaryPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		handleAttrs(attrs);
	}

	public ListSummaryPreference(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		handleAttrs(attrs);
	}

	public ListSummaryPreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		handleAttrs(attrs);
	}

	public ListSummaryPreference(Context context)
	{
		super(context);
	}

	private void handleAttrs(AttributeSet attrs)
	{
		TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.ListSummaryPreference);

		mSummaries =  a.getTextArray( R.styleable.ListSummaryPreference_summary_list);

		if (mSummaries == null)
			mSummaries = new CharSequence[0];

		a.recycle();
	}


	@Override
	public void onBindViewHolder(PreferenceViewHolder holder)
	{
		super.onBindViewHolder(holder);

		final TextView summaryView = (TextView) holder.findViewById(android.R.id.summary);

		if (mSummaries != null)
		{
			StringBuilder builder = new StringBuilder();
			for (CharSequence string : mSummaries)
			{
				builder.append(string+"\n");
			}

			summaryView.setText(builder.toString());
			summaryView.setVisibility(View.VISIBLE);

			//Wonder if you can just disable it somewhere.
			//0 removes all lines, -1 crashes
			summaryView.setMaxLines(99);
		}
	}


}
