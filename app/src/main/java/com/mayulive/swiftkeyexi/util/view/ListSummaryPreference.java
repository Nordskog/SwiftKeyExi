package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.R;

import java.nio.charset.Charset;
import java.security.acl.LastOwnerException;
import java.util.Arrays;


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
		}
	}


}
