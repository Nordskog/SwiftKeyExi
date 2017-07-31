package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mayulive.swiftkeyexi.util.CodeUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Roughy on 12/12/2016.
 */

//A view pager that lets you get the current visible view instead of just its index
	//The only other way to keep track of views is with tags, which is nasty.
	//You can always store the primaryitem submitted to the adapter, but it is called
	//AFTER this pager calls its onpagechanged listener. I think a bit of reflection is justified.
public class FixedViewPager extends ViewPager
{

	static private Field mItemsField = null;
	static private Class mItemInfoClass = null;

	static private Field mItemInfo_PositionField = null;
	static private Field mItemInfo_ViewField = null;


	static
	{
		try
		{
			mItemsField = ViewPager.class.getDeclaredField("mItems");
			mItemsField.setAccessible(true);


			Class[] classes = ViewPager.class.getDeclaredClasses();
			for (int i = 0; i < classes.length; i++)
			{


				if (classes[i].getSimpleName().equals("ItemInfo"))
				{
					mItemInfoClass = classes[i];
					break;
				}
			}


			mItemInfo_PositionField = mItemInfoClass.getDeclaredField("position");

			mItemInfo_ViewField = mItemInfoClass.getDeclaredField("object");


			mItemInfo_PositionField.setAccessible(true);
			mItemInfo_ViewField.setAccessible(true);


		}
		catch (NoSuchFieldException | ExceptionInInitializerError e)
		{

			e.printStackTrace();
		}

	}




	public FixedViewPager(Context context)
	{
		super(context);
	}

	public FixedViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public View getCurrentPage()
	{
		return getPageAtPosition(this.getCurrentItem());
	}

	//If it exists
	public View getPageAtPosition(int position)
	{
		try
		{
			ArrayList<Object> items = (ArrayList<Object>) mItemsField.get(this);

			for (int i = 0; i < items.size(); i++)
			{
				Object item = items.get(i);
				if (item != null)
				{
					int itemPosition = (int)mItemInfo_PositionField.get(item);
					View itemView = (View) mItemInfo_ViewField.get(item);

					if (itemPosition == position)
						return itemView;
				}
			}

		}
		catch (IllegalAccessException e)
		{

			e.printStackTrace();
		}

		return null;
	}


	@Override
	public void requestDisallowInterceptTouchEvent(boolean disallowIntercept)
	{
		//Ignore
		//Log.i("Exi/Test", "Intercept request detected!");
		//CodeUtils.printStackTrace();
	}


	//Tablayouts will requestDisallowInterceptTouchEvent, which is fixed on the tablayout's side.
	//In addition, the standard canScroll implementation doesn't work correctly. the viewCompat one called
	//on return does though.
	@Override
	protected boolean canScroll(View v, boolean checkV, int dx, int x, int y)
	{

		if (TabLayout.class.isAssignableFrom( v.getClass() ))
		{
			//ViewCompat.canScrollHorizontally below takes care of it
		}
		else if (v instanceof ViewGroup) {
			final ViewGroup group = (ViewGroup) v;
			final int scrollX = v.getScrollX();
			final int scrollY = v.getScrollY();
			final int count = group.getChildCount();
			// Count backwards - let topmost views consume scroll distance first.
			for (int i = count - 1; i >= 0; i--) {
				// TODO: Add versioned support here for transformed views.
				// This will not work for transformed views in Honeycomb+
				final View child = group.getChildAt(i);
				if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight()
						&& y + scrollY >= child.getTop() && y + scrollY < child.getBottom()
						&& canScroll(child, true, dx, x + scrollX - child.getLeft(),
						y + scrollY - child.getTop())) {
					return true;
				}
			}
		}

		return checkV && ViewCompat.canScrollHorizontally(v, -dx);
	}



}
