package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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

}
