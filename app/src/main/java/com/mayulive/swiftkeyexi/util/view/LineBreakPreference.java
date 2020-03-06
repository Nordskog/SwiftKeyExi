package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
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

public class LineBreakPreference extends Preference
{

	//Note that we call all the supers instad of just the top then nesting the rest into that.
	//THings break otherwise.
	public LineBreakPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public LineBreakPreference(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}


	public LineBreakPreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public LineBreakPreference(Context context)
	{
		super(context);
	}


	@Override
	public void onBindViewHolder(PreferenceViewHolder holder)
	{
		super.onBindViewHolder(holder);

		final TextView summaryView = (TextView) holder.findViewById(android.R.id.title);

		//if (summaryView != null)
		{
			summaryView.setEllipsize(TextUtils.TruncateAt.END);
			summaryView.setSingleLine(false);
		}
	}


}
