package com.mayulive.swiftkeyexi.main.swipe;

import android.content.Context;
import androidx.appcompat.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;


//Seekbar that can't be intercepted, making seeking actually possible
public class UninterceptableSeekBar extends AppCompatSeekBar
{
	public UninterceptableSeekBar(Context context)
	{
		super(context);
	}

	public UninterceptableSeekBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public UninterceptableSeekBar(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		this.getParent().requestDisallowInterceptTouchEvent(true);
		return super.onTouchEvent(event);
	}


}
