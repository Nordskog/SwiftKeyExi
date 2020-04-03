package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.mayulive.swiftkeyexi.util.MathUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

public class SimpleGestureLinearLayout extends LinearLayout implements GestureDetector.OnGestureListener
{
	private GestureDetectorCompat mDetector;

	onFlingListener mListener = null;


	public SimpleGestureLinearLayout(@NonNull Context context)
	{
		super(context);
		init(context);
	}

	public SimpleGestureLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}

	public SimpleGestureLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public SimpleGestureLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context);
	}

	private void init(Context context)
	{
		mDetector = new GestureDetectorCompat(context,this);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		if (this.mDetector.onTouchEvent(ev))
		{
			return true;
		}

		return super.onInterceptTouchEvent(ev);

	}

	/////////////////////
	// Gesture listnener
	/////////////////////

	@Override
	public boolean onDown(MotionEvent e)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{

	}

	public void setOnFlingListener( onFlingListener listener)
	{
		mListener = listener;
	}




	private boolean eventInBounds(MotionEvent event )
	{
		int height = this.getMeasuredHeight();	// Also padding event must end outside
		int width = this.getMeasuredWidth();


		boolean withinX =  (MathUtils.isWithinRange( event.getX(), 0 - height, width +height ));
		boolean witinY  =  (MathUtils.isWithinRange( event.getY(), 0 - height, height + height ));

		return withinX && witinY;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{

		if (mListener != null)
		{
			// Only used for predictions vertical swipe.
			// Disregard if up is not outside + height away.
			if ( eventInBounds(e2) )
				return false;

			if ( Math.abs(velocityY) > Math.abs( velocityX ))
			{
				if (velocityY > 0)
				{
					return mListener.onFling(FlingDirection.UP);
				}
				else
				{
					return mListener.onFling(FlingDirection.DOWN);
				}
			}
			else
			{
				if (velocityX > 0)
				{
					return mListener.onFling(FlingDirection.RIGHT);
				}
				else
				{
					return mListener.onFling(FlingDirection.LEFT);
				}
			}

		}


		return false;
	}

	public interface onFlingListener
	{
		boolean onFling(FlingDirection direction);
	}

	public enum FlingDirection
	{
		UP, DOWN, LEFT, RIGHT
	}
}
