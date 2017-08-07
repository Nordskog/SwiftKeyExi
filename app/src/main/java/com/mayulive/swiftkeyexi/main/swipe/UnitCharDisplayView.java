package com.mayulive.swiftkeyexi.main.swipe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.util.TextUtils;

/**
 * Created by Roughy on 8/4/2017.
 */

public class UnitCharDisplayView extends View
{

	float mPixelCount = 100;		//Current value
	float mComparisonBarLength = 100;
	float mMaxFontSize = 18;

	Paint mPaint;

	private Rect mMeasureRect = new Rect();


	public UnitCharDisplayView(Context context)
	{
		super(context);
	 	init(null);
	}

	public UnitCharDisplayView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		init(attrs);
	}

	public UnitCharDisplayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	private void init(AttributeSet attrs)
	{
		if (attrs != null)
		{
			TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.BoundedView);
			setComparisonBarLength( a.getDimensionPixelSize(R.styleable.UnitCharDisplayView_comparison_bar_length, 500) );
			a.recycle();
		}

		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		mPaint.setTextAlign(Paint.Align.CENTER );
		mMaxFontSize = getContext().getResources().getDimension(R.dimen.unichardisplayview_textsize);
		mPaint.setTextSize( mMaxFontSize );	//Doesn't say what units it expects. Probably pixels?
	}

	public void setPixelCount(float newPixelCount)
	{
		if (newPixelCount <= 0)
			newPixelCount = 1;

		mPixelCount = newPixelCount;
		this.invalidate();
	}

	public void setComparisonBarLength(float length)
	{
		if (length <= 0)
			length = 1;
		mComparisonBarLength = length;
	}

	public float getComparisonBarLenght()
	{
		return mComparisonBarLength;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		float charCount = mComparisonBarLength / mPixelCount;

		String text = TextUtils.fillString("a",(int)charCount);

		//Figure out text size
		mPaint.setTextSize(mMaxFontSize);
		mPaint.getTextBounds(text, 0, text.length(), mMeasureRect);

		float textHeight = mMeasureRect.height();

		if ((mMeasureRect.width() > this.getMeasuredWidth()) )
		{
			//Include margin of .... 4 char widths?
			float charPadding = ( mMeasureRect.width() / text.length()  )  *  4f;

			//Needs to be smaller.
			float sizeRatio = (float) (this.getMeasuredWidth() - charPadding) / ( (float)mMeasureRect.width() ) ;
			mPaint.setTextSize( mMaxFontSize * sizeRatio );

			textHeight = textHeight * sizeRatio;

		}

		canvas.drawText(text, getMeasuredWidth() / 2, (  getMeasuredHeight() / 2 ) + ( textHeight * 0.5f  )  , mPaint);
	}
}
