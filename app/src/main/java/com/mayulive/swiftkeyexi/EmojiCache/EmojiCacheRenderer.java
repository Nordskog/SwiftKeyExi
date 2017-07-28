package com.mayulive.swiftkeyexi.EmojiCache;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static android.R.attr.width;

/**
 * Created by Roughy on 1/25/2017.
 */

public class EmojiCacheRenderer
{
	//Especially when rendering in the background we can reuse the same view for massive gains
	private static final ArrayList<NormalEmojiItem> mViewPool = new ArrayList<>();

	public static void clearViewPool()
	{
		synchronized(mViewPool)
		{
			mViewPool.clear();
		}

	}

	private static NormalEmojiItem createView(Context context)
	{
		NormalEmojiItem textView = new NormalEmojiItem(context);
		textView.setPadding(0,0,0,0);
		textView.setMarked(false);

		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
		textView.setLayoutParams(params);
		textView.setIncludeFontPadding(true);

		textView.setDrawingCacheEnabled(true);

		return textView;
	}

	private static NormalEmojiItem getView(Context context)
	{
		synchronized (mViewPool)
		{
			if (!mViewPool.isEmpty())
			{
				return mViewPool.remove( mViewPool.size()-1 );
			}
		}

		return createView(context);

	}

	private static void returnView(NormalEmojiItem view)
	{
		synchronized (mViewPool)
		{
			mViewPool.add(view);
		}
	}

	protected static EmojiCacheItem populateEmojiCacheItem(Context context, EmojiCacheItem item, String text, float textSize, int styleId, int itemWidth, boolean singleLine)
	{

		NormalEmojiItem textView = getView(context);

		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(context);

		int width = (int) (dimens.singleEmojiWidth * (float)itemWidth);

		int paddingLeft = textView.getPaddingLeft();
		int paddingRight = textView.getPaddingRight();
		int paddingTop = textView.getPaddingTop();
		int paddingBottom = textView.getPaddingBottom();


		//Once a textview has been through a layout pass, it actually won't
		//grow smaller if you change the text and re-measure. It may grow bigger,
		//but will otherwise want to retain its previous width.
		//A layout pass with all 0s alone is not enough to reset it it, you also
		//have to set the text to nothing.
		//Apparently you /also/- have to measure it at 0,0,0,0, otherwise it stops updating properly.
		//Would measure it with unspecified and set it to the requested size, yet the resulting
		//text would be 3 lines ... very strange.
		textView.setEmojiText("",textSize,null,styleId);
		textView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY));
		textView.layout(0,0,0,0);

		textView.setEmojiText(text, textSize, null, styleId);


		if (singleLine)
		{
			textView.measure(View.MeasureSpec.makeMeasureSpec(width - paddingLeft - paddingRight, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.UNSPECIFIED);
		}
		else
		{

			textView.measure(View.MeasureSpec.makeMeasureSpec(width - paddingLeft - paddingRight, View.MeasureSpec.AT_MOST), View.MeasureSpec.UNSPECIFIED);
		}


		textView.layout(
				0,
				0,
				textView.getMeasuredWidth(),
				textView.getMeasuredHeight());





		//So if you create a bitmap from a bitmap normally, it /may/ just end up being a referece to the source bitmap.
		//So have have to call createScaledBitmap() to ensure that a copy is made. Okay.
		Bitmap drawCache = textView.getDrawingCache();


		//Number of emoji item width units required, multiplied for each line.
		//Note that getLineCount() on the textView will return 0 until it has been drawn (getDrawingCache()).
		int widthUnitWidth = (int) ( Math.ceil ( (float)textView.getMeasuredWidth() / (float)dimens.singleEmojiWidth) ) ;
		widthUnitWidth *= textView.getLineCount();
		boolean singleWidth = textView.getLineCount() <= 1 && textView.getMeasuredWidth() <= dimens.singleEmojiWidth;


		//Sometimes the drawCache will be null, presumably when there are no characters to render?
		if (drawCache != null)
		{
			item.set( Bitmap.createScaledBitmap(drawCache, drawCache.getWidth(), drawCache.getHeight(), false), textView.getLineCount(), singleWidth, widthUnitWidth, styleId);
		}
		else
		{
			item.set( Bitmap.createBitmap(1,1, Bitmap.Config.ALPHA_8), textView.getLineCount(), singleWidth, widthUnitWidth, styleId);
		}


		returnView(textView);

		return item;
	}



}
