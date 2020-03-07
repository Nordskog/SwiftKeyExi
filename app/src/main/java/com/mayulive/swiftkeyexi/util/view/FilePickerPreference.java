package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.R;

import java.util.ArrayList;

/**
 * Created by Roughy on 1/4/2017.
 */

public class FilePickerPreference extends PreferenceShowSummary
{

	private static String LOGTAG = ExiModule.getLogTag(FilePickerPreference.class);

	ArrayList<OnPreferenceWidgetClickedListener> mWidgetListeners = new ArrayList<>();


	public FilePickerPreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public FilePickerPreference(Context context)
	{
		super(context);


		CheckBoxPreference pref = null;

		init();
	}

	private void init()
	{
		this.setWidgetLayoutResource(R.layout.file_picker_preference_widget);
	}

	@Override
	public void onBindViewHolder(PreferenceViewHolder holder)
	{
		super.onBindViewHolder(holder);

		final LinearLayout widgetHolder = (LinearLayout) holder.findViewById(android.R.id.widget_frame);

		widgetHolder.setOnClickListener(view ->
		{
			for (OnPreferenceWidgetClickedListener listener : mWidgetListeners)
			{
				listener.onClick(FilePickerPreference.this);
			}
		});

	}

	public interface OnPreferenceWidgetClickedListener
	{
		void onClick(Preference pref);
	}

	public void addOnPreferenceWidgetClickedListener(OnPreferenceWidgetClickedListener listener)
	{
		mWidgetListeners.add(listener);
	}

	public void removeOnPreferenceWidgetClickedListener(OnPreferenceWidgetClickedListener listener)
	{
		mWidgetListeners.remove(listener);
	}
}
