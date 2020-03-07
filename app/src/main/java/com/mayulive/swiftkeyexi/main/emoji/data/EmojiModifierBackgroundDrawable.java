package com.mayulive.swiftkeyexi.main.emoji.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mayulive.swiftkeyexi.util.DimenUtils;

/**
 * Created by Roughy on 10/25/2017.
 */

public class EmojiModifierBackgroundDrawable extends Drawable
{
	Paint mPaint = new Paint();
	RectF mRect = new RectF();

	int mBackgroundColor = 0;
	int mOutlineColor = 0;
	float mOvalRadius = 0;
	float mOutlineWidth = 0;

	public EmojiModifierBackgroundDrawable(Context context, int backgroundColor, int outlineColor)
	{
		mBackgroundColor = backgroundColor;
		mOutlineColor = outlineColor;
		mOvalRadius = DimenUtils.calculatePixelFromDp(context, 5);
		mOutlineWidth = DimenUtils.calculatePixelFromDp(context, 2);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.FILL);


	}


	@Override
	public void draw(@NonNull Canvas canvas)
	{



		mRect.left = 0;
		mRect.top = 0;
		mRect.right = this.getBounds().right;
		mRect.bottom = this.getBounds().bottom;

		mPaint.setColor(mOutlineColor);
		canvas.drawRoundRect(
				mRect,
				mOvalRadius,
				mOvalRadius,
				mPaint
		);


		mRect.left = 0+mOutlineWidth;
		mRect.top = 0+mOutlineWidth;
		mRect.right = this.getBounds().right-mOutlineWidth;
		mRect.bottom = this.getBounds().bottom-mOutlineWidth;

		mPaint.setColor(mBackgroundColor);

		canvas.drawRoundRect(
				mRect,
				mOvalRadius,
				mOvalRadius,
				mPaint
		);




	}

	@Override
	public void setAlpha(@IntRange(from = 0, to = 255) int alpha)
	{

	}

	@Override
	public void setColorFilter(@Nullable ColorFilter colorFilter)
	{

	}

	@Override
	public int getOpacity()
	{
		return PixelFormat.OPAQUE;
	}
}
