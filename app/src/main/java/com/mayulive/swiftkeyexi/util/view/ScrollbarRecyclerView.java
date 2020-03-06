package com.mayulive.swiftkeyexi.util.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

import com.mayulive.swiftkeyexi.util.DimenUtils;

//A recyclerview with a scrollbar (vertical only) that can be enabled programatically
//because it is literally impossible to do so properly.
public class ScrollbarRecyclerView extends RecyclerView
{

	private boolean mScrollbarEnabled = false;
	private int mScrollbarColor = Color.RED;

	private Paint mPaint = new Paint();

	//Android default size set in init()
	private float mScrollbarWidth = 10;


	public ScrollbarRecyclerView(Context context)
	{
		super(context);
		init(context);
	}

	public ScrollbarRecyclerView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}

	public ScrollbarRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context)
	{
		mScrollbarWidth =  DimenUtils.calculatePixelFromDp(context, 3);
		mPaint.setColor(mScrollbarColor);
	}

	public void setScrollbarColor(int color)
	{
		mScrollbarColor = color;
		mPaint.setColor(mScrollbarColor);
	}

	public void setScrollbarEnabled(boolean toggle)
	{
		mScrollbarEnabled = toggle;
	}

	@Override
	public final void dispatchDraw(Canvas canvas)
	{
		super.dispatchDraw(canvas);

		if (mScrollbarEnabled)
		{
			int height = this.getMeasuredHeight();
			int width = this.getMeasuredWidth();

			int scrollRange = this.computeVerticalScrollRange();
			float scrollbarExtent = this.computeVerticalScrollExtent();

			if (scrollRange >= 0 && scrollbarExtent < scrollRange)
			{

				float scrollY = this.computeVerticalScrollOffset();

				//So top position of scrollbar is...
				float yScrollbarTop = (scrollY / scrollRange);	//Ratio
				yScrollbarTop *= height;

				//and height
				float scrollbarHeight = scrollbarExtent / (float)scrollRange;
				scrollbarHeight *= height;

				canvas.drawRect(
						width - mScrollbarWidth,
						yScrollbarTop,
						width,
						yScrollbarTop + scrollbarHeight,
						mPaint
				);

				//canvas.drawCircle(width, height/2, 50, paint);
			}

		}
	}



}
