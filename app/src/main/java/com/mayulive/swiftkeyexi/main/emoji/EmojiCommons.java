package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiCache;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roughy on 1/25/2017.
 */

public class EmojiCommons
{
	public static void preRenderPanels(Context context, ArrayList<DB_EmojiPanelItem> items)
	{
		for (DB_EmojiPanelItem item : items)
		{
			preRenderPanel(context, item);
		}
	}

	//User always starts on panel 1 both for the dictionary and the keyboard,
	//so we should render panel 1 for both, panel 2 for both and so on
	public static void preRenderPanels(Context context, List<DB_EmojiPanelItem> items, List<DB_EmojiPanelItem> items2)
	{

		int maxSize = items.size();
		if (items2.size() > maxSize)
			maxSize = items2.size();

		for (int i = 0; i < maxSize; i++)
		{
			if (i < items.size())
				preRenderPanel(context, items.get(i));

			if (i < items2.size())
				preRenderPanel(context, items2.get(i));
		}

	}

	public static void preRenderPanelIcon(Context context, DB_EmojiPanelItem item)
	{
		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(context);
		EmojiCache.singleQueue(context, item.get_icon(), dimens.configured_emojiTextSize, null, item.get_icon_style(), 1, true);
		EmojiCache.processQueue();
	}

	public static void preRenderPanel(Context context, DB_EmojiPanelItem item)
	{
		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(context);
		EmojiCache.batchQueue(context, item.get_items(), dimens.configured_emojiTextSize, item, item.get_style(), item.get_column_width(), false);
		EmojiCache.processQueue();
	}

}
