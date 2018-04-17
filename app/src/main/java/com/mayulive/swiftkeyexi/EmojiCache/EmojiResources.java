package com.mayulive.swiftkeyexi.EmojiCache;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.TypedValue;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.MainActivity;

/**
 * Created by Roughy on 12/13/2016.
 */

//Some of these components run in both the config app and the hooked app
	//Access the necessary resources via this class
public class EmojiResources
{
	private static String LOGTAG = ExiModule.getLogTag(MainActivity.class);

	//Will be accessed both from config app and hook context,
	//so all sizes are defined here instead of in xml.
	private static final int SINGLE_EMOJI_ITEM_WIDTH_DP = 48;
	private static final int EMOJI_TEXT_SIZE_DP = 12;	//Same as swiftkey, also look best at this res (no scaling?)
	private static final int EMOJI_HORIZONTAL_PADDING_DP = 10;
	private static final int EMOJI_VERTICAL_PADDING_DP = 10;

	private static String DEFAULT_DIVERSE_MODIFIER = "";

	public static int EMOJI_COLOR = 0xFF9c9c9c;

	private static EmojiPixelDimensions mDimensions = null;


	public static class EmojiPixelDimensions
	{
		public float default_singleEmojiWidth = 26;
		public float configured_singleEmojiWidth = 26;
		public float default_emojiTextSize = 50;
		public float configured_emojiTextSize = 50;
		public float emojiHorizontalPadding = 25;
		public float emojiVerticalPadding = 25;
	}

	private static float calculatePixelFromDp(Context context, int dpSize)
	{
		return TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, dpSize, context.getResources().getDisplayMetrics() );
	}

	public static boolean setDefaultDiverseModifier(String def)
	{
		if (!DEFAULT_DIVERSE_MODIFIER.equals(def) )
		{
			DEFAULT_DIVERSE_MODIFIER = def;
			return true;
		}

		DEFAULT_DIVERSE_MODIFIER = def;
		return false;
	}

	public static String getDefaultDiverseModifier()
	{
		return DEFAULT_DIVERSE_MODIFIER;
	}

	public static boolean setEmojiTextSize(Context context, int dpSize)
	{
		//We want our emoji items to be a specific height, thus why it is defined in dp.
		//Why the desired pixel height has to be multiplied by 2 for the font to
		//occupy the expected space I do not know.

		EmojiPixelDimensions dimens = getDimensions(context);

		float newValue = calculatePixelFromDp(context, dpSize) * 2f;
		boolean changed = newValue != dimens.configured_emojiTextSize;

		dimens.configured_emojiTextSize = newValue;
		dimens.configured_singleEmojiWidth =  dimens.configured_emojiTextSize * 2; //(int)calculatePixelFromDp(context, SINGLE_EMOJI_ITEM_WIDTH_DP);

		ImageEmojiItem.updateDiverseIndicator(context);

		return changed;
	}

	//This is a singleton. Beware that changes will affect other classes accessing it.
	//Please don't do that.
	//Context is nullable, but will return null if not already initialized
	public static EmojiPixelDimensions getDimensions(@Nullable Context context)
	{
		if (mDimensions == null)
		{
			initDimensions(context);
		}

		return mDimensions;
	}



	private static void initDimensions(Context context)
	{
		mDimensions = new EmojiPixelDimensions();


		mDimensions.configured_emojiTextSize = (int)calculatePixelFromDp(context, EMOJI_TEXT_SIZE_DP) *2f;
		mDimensions.default_singleEmojiWidth = (int)calculatePixelFromDp(context, SINGLE_EMOJI_ITEM_WIDTH_DP);
		mDimensions.configured_singleEmojiWidth =  mDimensions.configured_emojiTextSize * 2;
		mDimensions.default_emojiTextSize = mDimensions.configured_emojiTextSize;
		mDimensions.emojiHorizontalPadding = (int)calculatePixelFromDp(context, EMOJI_HORIZONTAL_PADDING_DP);
		mDimensions.emojiVerticalPadding = (int)calculatePixelFromDp(context, EMOJI_VERTICAL_PADDING_DP);
	}

	public static int calculateColCount(Context context, int viewWidth, int itemWidth)
	{

		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(context);

		//Get actual pixel width of a single item
		itemWidth *= dimens.configured_singleEmojiWidth;

		if (itemWidth <= 0)
			return 1;

		int colCount = viewWidth / itemWidth;
		if (colCount < 1)
			colCount = 1;

		//Width is in swiftkey-emoji-default-width units

		return colCount;

	}
}
