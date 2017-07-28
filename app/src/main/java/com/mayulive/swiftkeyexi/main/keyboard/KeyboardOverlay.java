package com.mayulive.swiftkeyexi.main.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;

import java.util.List;

//For debugging key hitboxes
public class KeyboardOverlay extends View
{
	List<KeyDefinition> mKeys = null;

	public KeyboardOverlay(Context context)
	{
		super(context);
	}

	public KeyboardOverlay(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public KeyboardOverlay(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	public void setDisplayKeys(List<KeyDefinition> keys)
	{
		this.mKeys = keys;
		this.setWillNotDraw( mKeys == null );
		this.invalidate();
	}

	private RectF toPixels(RectF ratios)
	{
		return new RectF(
							ratios.left*this.getMeasuredWidth(),
							ratios.top*this.getMeasuredHeight(),
							ratios.right*this.getMeasuredWidth(),
							ratios.bottom*this.getMeasuredHeight()
				);


	}


	@Override
	protected void dispatchDraw(Canvas canvas)
	{
		super.dispatchDraw(canvas);

		//Draw after drawing children
		drawOverlay(canvas);
	}

	private void drawOverlay(Canvas canvas)
	{
		if (mKeys != null)
		{
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(Color.RED);

			paint.setAlpha(50);
			paint.setTypeface(Typeface.create( Typeface.DEFAULT, Typeface.NORMAL));
			paint.setTextSize( 30);

			paint.setStrokeWidth(2f);

			paint.setTextAlign(Paint.Align.CENTER);

			for (KeyDefinition def : mKeys)
			{
				RectF rect = toPixels(def.hitbox);

				paint.setColor(Color.RED);
				paint.setAlpha(100);
				canvas.drawRect(rect,paint);
				canvas.drawLine(rect.left, rect.bottom, rect.right, rect.top, paint);
				canvas.drawLine(rect.left, rect.top, rect.right, rect.bottom, paint);

				int length = def.getContent().length();
				if (length > 5)
					length = 5;
				String string = def.getContent().substring(0,length);

				paint.setColor(Color.WHITE);
				paint.setAlpha(255);
				canvas.drawText(string, rect.centerX(),rect.centerY(), paint);
			}
		}
	}

}
