package com.mayulive.swiftkeyexi.EmojiCache;

import android.graphics.Bitmap;

import java.util.ArrayList;



/**
 * Created by Roughy on 5/20/2017.
 */
public class EmojiCacheItem
{
	public Bitmap bitmap;
	public int linecount;
	public boolean isSingleWidth = true;
	public int widthUnits = 1;
	public int style = 0;
	public int memorySize = 0;

	public CacheItemStatus status = CacheItemStatus.UNDEFINED;

	//Synchronize any calls to this
	ArrayList<EmojiCache.OnRenderCompleteListener> mRenderListeners = new ArrayList<>();

	public enum CacheItemStatus
	{
		UNDEFINED, READY, RENDERING
	}

	EmojiCacheItem()
	{

	}

	EmojiCacheItem(Bitmap bitmap, int lineCount, boolean singleWidth, int widthUnits, int style)
	{
		this.bitmap = bitmap;
		this.widthUnits = widthUnits;
		this.linecount = lineCount;
		this.isSingleWidth = singleWidth;
		this.style = style;
		this.memorySize = bitmap.getRowBytes() * bitmap.getHeight();
	}

	public void set(Bitmap bitmap, int lineCount, boolean singleWidth, int widthUnits, int style)
	{
		this.bitmap = bitmap;
		this.widthUnits = widthUnits;    //How many width units the item actually requires
		//This is only the width, but it will be doubled for
		//each additional line
		linecount = lineCount;
		isSingleWidth = singleWidth;
		this.style = style;
		this.memorySize = bitmap.getRowBytes() * bitmap.getHeight();
	}

	public int getStyle()
	{
		return style;
	}


	protected void onRenderComplete(EmojiCacheItem item)
	{
		//synchronized (mRenderListeners)
		{

			for (EmojiCache.OnRenderCompleteListener listener : mRenderListeners)
			{
				listener.onRenderComplete(item);
			}

			mRenderListeners.clear();

		}
	}

	public void addOnRenderCompleteListener(EmojiCache.OnRenderCompleteListener listener)
	{
		synchronized (mRenderListeners)
		{
			mRenderListeners.add(listener);
		}
	}

	public void removeOnRenderCompleteListener(EmojiCache.OnRenderCompleteListener listener)
	{
		synchronized (mRenderListeners)
		{
			mRenderListeners.remove(listener);
		}
	}

	public void setStatus(CacheItemStatus status)
	{
		this.status = status;
	}

	public CacheItemStatus getStatus()
	{
		return status;
	}

	public Bitmap getBitmap()
	{
		return bitmap;
	}


	public boolean needsUpdate(int newWidthUnits)
	{
		if (bitmap == null)
			return true;

		if (isSingleWidth)
			return false;

		if (widthUnits <= newWidthUnits)
			return false;

		return true;
	}
}
