package com.mayulive.swiftkeyexi.EmojiCache;

import android.content.Context;

/**
 * Created by Roughy on 5/20/2017.
 */
public class EmojiRenderInterface implements Runnable
{
	public Context context;
	public EmojiCacheItem item;
	public String text;
	public float textSize;
	public int styleId;
	public int itemWidth;
	public boolean singleLine;
	public Object panelKey;

	public EmojiRenderInterface(Context context, EmojiCacheItem item, String text, float textSize, int styleId, int itemWidth, boolean singleLine, Object panelKey)
	{
		this.context = context;
		this.item = item;
		this.text = text;
		this.textSize = textSize;
		this.styleId = styleId;
		this.itemWidth = itemWidth;
		this.singleLine = singleLine;
		this.panelKey = panelKey;
	}

	@Override
	public void run()
	{
		EmojiCacheRenderer.populateEmojiCacheItem(
				context,
				item,
				text,
				textSize,
				styleId,
				itemWidth,
				singleLine
		);

		EmojiCache.onRenderComplete(this);
	}

}
