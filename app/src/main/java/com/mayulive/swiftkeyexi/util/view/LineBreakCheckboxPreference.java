package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceViewHolder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Roughy on 11/1/2017.
 */

//Long translations are very very long.
	//Will talk to translators to make them shorter, but for now preferences
	//need to to display the entirety of the text

public class LineBreakCheckboxPreference extends CheckBoxPreference
{

	//Note that we call all the supers instad of just the top then nesting the rest into that.
	//THings break otherwise.
	public LineBreakCheckboxPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public LineBreakCheckboxPreference(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}


	public LineBreakCheckboxPreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public LineBreakCheckboxPreference(Context context)
	{
		super(context);
	}


	@Override
	public void onBindViewHolder(PreferenceViewHolder holder)
	{
		super.onBindViewHolder(holder);

		final TextView titleView = (TextView) holder.findViewById(android.R.id.title);
		if (titleView != null)
		{
			titleView.setEllipsize(TextUtils.TruncateAt.END);
			titleView.setSingleLine(false);
		}


		final TextView summaryView = (TextView) holder.findViewById(android.R.id.summary);
		if (summaryView != null)
		{
			summaryView.setEllipsize(TextUtils.TruncateAt.END);
			summaryView.setSingleLine(false);
			summaryView.setMaxLines(999);	// No summary is too long, also it does nested scrolls if you let it.
		}
	}


}
