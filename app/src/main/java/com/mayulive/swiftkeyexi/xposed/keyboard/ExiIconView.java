package com.mayulive.swiftkeyexi.xposed.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ExiIconView extends View
{

	Path[] mPaths = new Path[6];
	Paint mPaint;
	int mColor = Color.WHITE;

	public ExiIconView(Context context)
	{
		super(context);
		init();
	}

	public ExiIconView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public ExiIconView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init()
	{
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL );
		mPaint.setColor( mColor );
		mPaint.setAntiAlias(true);
	}

	public void setIconColor( int color)
	{
		mColor = color;
		mPaint.setColor(mColor);
		this.invalidate();
	}

	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w,h,oldw,oldh);
		generatePath();
	}


	//Convers 0-1 coordinates to pixel coordinates
	private float x( float x )
	{
		return ((float)this.getMeasuredWidth()) * x;
	}

	private float y( float y )
	{
		return  ((float) this.getMeasuredHeight()) * y;
	}

	//Draw one of the shapes of the logo.
	//Starting point, two control points,
	//end point, two control points.
	private Path generateSingle( float[] points )
	{
		Path path = new Path();

		path.moveTo(
				x(points[0]), y(points[1])
		);

		path.cubicTo(
				x(points[2]), y(points[3]),
				x(points[4]), y(points[5]),
				x(points[6]), y(points[7])
		);


		path.cubicTo(
				x(points[8]), y(points[9]),
				x(points[10]), y(points[11]),
				x(points[0]), y(points[1])
		);

		path.close();

		return path;
	}


	private void generatePath()
	{

		///////////////////////////
		// Vertical, left to right
		///////////////////////////

		mPaths[0] = generateSingle( new float[]
		{
				0.015f, 0.315f,	//Start

				0.192f, 0.341f,
				0.740f, 0.485f,

				0.815f, 0.855f,	//End

				0.874f, 0.524f,
				0.470f, 0.329f,
		});

		mPaths[1] = generateSingle( new float[]
		{
				0.116f, 0.280f,	//Start

				0.176f, 0.277f,
				0.893f, 0.346f,

				0.894f, 0.782f,	//End

				1.008f, 0.393f,
				0.542f, 0.265f,
		});

		mPaths[2] = generateSingle( new float[]
		{
				0.255f, 0.247f,	//Start

				0.380f, 0.229f,
				0.948f, 0.250f,

				0.966f, 0.657f,	//End

				1.054f, 0.250f,
				0.563f, 0.189f,
		});


		///////////////////////////
		// Horizontal, Top to bottom
		///////////////////////////

		mPaths[3] = generateSingle( new float[]
		{
				0.120f, 0.422f,	//Start

				0.427f, 0.455f,
				0.835f, 0.312f,

				0.711f, 0.268f,	//End

				0.654f, 0.260f,
				0.480f, 0.397f,
		});

		mPaths[4] = generateSingle( new float[]
		{
				0.240f, 0.587f,	//Start

				0.634f, 0.583f,
				0.995f, 0.375f,

				0.886f, 0.362f,	//End

				0.831f, 0.279f,
				0.828f, 0.455f,
		});

		mPaths[5] = generateSingle( new float[]
		{
				0.467f, 0.694f,	//Start

				0.726f, 0.682f,
				1.043f, 0.545f,

				0.965f, 0.525f,	//End

				0.958f, 0.445f,
				0.843f, 0.613f,
		});

	}


	protected void onDraw(Canvas canvas)
	{
		for (Path path : mPaths)
		{
			if ( path != null)
			{
				canvas.drawPath(path, mPaint);
			}
		}


	}

}
