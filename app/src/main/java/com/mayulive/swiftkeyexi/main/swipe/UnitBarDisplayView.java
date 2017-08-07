package com.mayulive.swiftkeyexi.main.swipe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.mayulive.swiftkeyexi.R;

import static com.mayulive.swiftkeyexi.main.swipe.UnitBarDisplayView.ChartUnitDisplayMode.CENTER;
import static com.mayulive.swiftkeyexi.main.swipe.UnitBarDisplayView.ChartUnitDisplayMode.TOP;

/**
 * Created by Roughy on 3/15/2017.
 */

public class UnitBarDisplayView extends View
{

	float mPixelCount = 100;		//Current value
	float mMaxPixelWidth = 1000;	//Max value

	float mHorzWidth = 2;
	float mVertWidth = 2;
	float mTopOffset = 1;

	Paint mPaint;

	ChartUnitDisplayMode mDisplayMode = CENTER;

	public enum ChartUnitDisplayMode
	{
		TOP, CENTER
	}

	public void setDisplayMode(ChartUnitDisplayMode mode)
	{
		mDisplayMode = mode;
	}

	public void setPixelCount(float newPixelCount)
	{
		mPixelCount = newPixelCount;
		this.invalidate();
	}

	public void setMaxPixelCount(float newMaxPixelCount)
	{
		mMaxPixelWidth = newMaxPixelCount;
		this.invalidate();
	}

	public UnitBarDisplayView(Context context)
	{
		super(context);
		init();
	}

	public UnitBarDisplayView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public UnitBarDisplayView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init()
	{

		final TypedValue value = new TypedValue ();
		getContext().getTheme ().resolveAttribute (R.attr.colorAccent, value, true);

		mHorzWidth = getContext().getResources().getDimension(R.dimen.swipe_speed_horizontal_line_width);
		mVertWidth = getContext().getResources().getDimension(R.dimen.swipe_speed_vertical_line_width);

		mTopOffset = mHorzWidth * 0.5f;

		mPaint = new Paint();
		mPaint.setColor( value.data );
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		float lineWidth =  this.getMeasuredWidth() < mMaxPixelWidth ? ( mPixelCount / mMaxPixelWidth ) * this.getMeasuredWidth() : mPixelCount;
		float leftPos =  (this.getMeasuredWidth() * 0.5f) - (lineWidth * 0.5f);

		mPaint.setStrokeWidth(mHorzWidth);

		if (mDisplayMode == TOP)
		{
			canvas.drawLine(leftPos, mTopOffset, leftPos+lineWidth, mTopOffset, mPaint);
		}
		else
		{	float verticalCenter = this.getMeasuredHeight() * 0.5f;
			canvas.drawLine(leftPos, verticalCenter, leftPos+lineWidth, verticalCenter, mPaint);
		}


		mPaint.setStrokeWidth(mVertWidth);
		canvas.drawLine(leftPos, 0, leftPos, this.getMeasuredHeight(), mPaint);
		canvas.drawLine(leftPos+lineWidth, 0, leftPos+lineWidth, this.getMeasuredHeight(), mPaint);
	}
}
