package com.mayulive.swiftkeyexi.xposed.selection.selectionstuff;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.selection.SelectionMethods;


//Placed over keyboard to intercept touches
public class SwipeOverlay extends FrameLayout
{
	public SwipeOverlay(Context context)
	{
		super(context);

		this.setOnTouchListener( OnTouchListenerStatic );
	}

	@Override
	public void requestDisallowInterceptTouchEvent(boolean disallow)
	{
		//Ignored!
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event)
	{
		return onInterceptTouchEventStatic(this, event);
	}

	public static boolean onInterceptTouchEventStatic(View view,MotionEvent event )
	{
		try
		{
			if (Hooks.selectionHooks_base.isRequirementsMet())
				return SelectionMethods.handleMotionEvent(view, event);
		}
		catch (Throwable ex)
		{
			Hooks.selectionHooks_base.invalidate(ex, "Something went wrong while intercepting touch event");
		}

		return false;
	}

	public static OnTouchListener OnTouchListenerStatic = new OnTouchListener()
	{
		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			try
			{
				return SelectionMethods.handleMotionEvent(v, event);
			}
			catch (Throwable ex)
			{
				Hooks.selectionHooks_base.invalidate(ex, "Something went wrong while intercepting touch event");
				return false;
			}
		}
	};
}
