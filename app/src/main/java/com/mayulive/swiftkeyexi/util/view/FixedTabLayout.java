package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;

import com.mayulive.swiftkeyexi.R;

import java.lang.reflect.Field;


/**
 * Created by Roughy on 12/19/2016.
 */

public class FixedTabLayout extends TabLayout
{

	private OnTabAddedListener mOnTabAddedListener = null;
	private OnTabLongPressedListener mOnTabLongPressedListener = null;

	public FixedTabLayout(Context context)
	{
		super(context);
	}

	public FixedTabLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public FixedTabLayout(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);

	}

	//Cannot set min width programtically otherwise
	public void setTabMinWidth(int value)
	{
		Field field;
		try {
			field = TabLayout.class.getDeclaredField("mRequestedTabMinWidth");
			field.setAccessible(true);
			field.set(this, value);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addTab(@NonNull final Tab tab, int position, boolean setSelected)
	{
		if (mOnTabAddedListener != null)
		{
			mOnTabAddedListener.onTabAdded(this.getContext(), tab, position);
		}

		if (tab.getCustomView() != null)
		{
			View customView = tab.getCustomView();

			//No click listener with custom views? weird.
			customView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					tab.select();
				}
			});


			customView.setOnLongClickListener(new OnLongClickListener()
			{
				@Override
				public boolean onLongClick(View v)
				{
					if (mOnTabLongPressedListener != null)
					{
						mOnTabLongPressedListener.OnTabLongPressed(tab.getPosition(), tab);
						return true;
					}
					return false;
				}
			});

		}

		super.addTab(tab, position, setSelected);

	}

	//So we can set the icon
	public interface OnTabAddedListener
	{
		void onTabAdded(Context context, Tab tab, int position);
	}

	public interface OnTabLongPressedListener
	{
		void OnTabLongPressed(int position, Tab tab);

	}

	public void setOnTabLongPressedListener(OnTabLongPressedListener listener)
	{
		mOnTabLongPressedListener = listener;
	}

	public void setOnTabAddedListener(OnTabAddedListener listener)
	{
		mOnTabAddedListener = listener;
	}

}
