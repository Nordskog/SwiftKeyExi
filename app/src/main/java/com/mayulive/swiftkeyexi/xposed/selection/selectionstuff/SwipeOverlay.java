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

		this.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				try
				{
					return SelectionMethods.handleMotionEvent(SwipeOverlay.this, event);
				}
				catch (Throwable ex)
				{
						Hooks.selectionHooks_base.invalidate(ex, "Something went wrong while intercepting touch event");
						return false;
				}
			}
		});
	}

	@Override
	public void requestDisallowInterceptTouchEvent(boolean disallow)
	{
		//Ignored!
	}

	private float positionDownX = 0;
	private float positionDownY = 0;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event)
	{
		try
		{
			if (Hooks.selectionHooks_base.isRequirementsMet())
				return SelectionMethods.handleMotionEvent(this, event);
		}
		catch (Throwable ex)
		{
			Hooks.selectionHooks_base.invalidate(ex, "Something went wrong while intercepting touch event");
		}

		return false;
	}
}
