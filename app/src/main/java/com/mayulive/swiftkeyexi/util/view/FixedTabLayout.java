package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;

import com.mayulive.swiftkeyexi.R;

import java.lang.reflect.Field;


/**
 * Created by Roughy on 12/19/2016.
 */

public class FixedTabLayout extends TabLayout
{

	private OnTabAddedListener mOnTabAddedListener = null;
	private OnTabLongPressedListener mOnTabLongPressedListener = null;

	private static Field mParentField = null;

	static
	{
		try
		{
			mParentField = View.class.getDeclaredField("mParent");
			mParentField.setAccessible(true);
		} catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
	}

	private ViewParent getParentFieldValue()
	{
		if (mParentField != null)
		{
			try
			{
				return (ViewParent)mParentField.get(this);
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
				return null;
			}
		}

		return null;

	}

	private void setParentFieldValue(ViewParent parent)
	{
		if (mParentField != null)
		{
			try
			{
				mParentField.set(this, parent);
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
	}

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

	//Tablayout will request requestDisallowInterceptTouchEvent on its parent, thus breaking viewpagers.
	//It checks mParent before calling it. Simplest workaround is to override, null the field, call super, re-set field.
	//I can't think of any scenario where you would want the original behavior in a tablayout.
	//Note that a fix for canScroll() is also required in ViewPager to make tablayouts place nice.
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		ViewParent parent = getParentFieldValue();
		setParentFieldValue(null);
		boolean retValue = super.onInterceptTouchEvent(ev);
		setParentFieldValue(parent);

		return retValue;
	}

	//Also in touch, but here it uses getParent().
	//Clearing the field works all the same though
	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		ViewParent parent = getParentFieldValue();
		setParentFieldValue(null);
		boolean retValue = super.onTouchEvent(ev);
		setParentFieldValue(parent);

		return retValue;
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
