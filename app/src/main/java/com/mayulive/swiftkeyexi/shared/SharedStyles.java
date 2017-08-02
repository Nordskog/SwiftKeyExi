package com.mayulive.swiftkeyexi.shared;

import android.content.Context;
import android.graphics.Color;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.util.DimenUtils;
import com.mayulive.swiftkeyexi.util.ThemeUtils;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;


public class SharedStyles
{
	public enum StyleContext
	{
		APP, HOOK
	}

	private static StyleContext mSelectedStyle = StyleContext.APP;

	//The accent color. Not available in hookland, so we load hook resources here if we are there.
	private static boolean mAccentLoaded = false;
	private static int mAccentColor = Color.RED;

	//As good as place as any
	private static boolean mTabHeightLoaded = false;
	private static final int TAB_HEIGHT_DP_VALUE = 2;
	private static int mTabHeight = 5;

	public static void setStyleContext(StyleContext style)
	{
		mSelectedStyle = style;
	}

	public static int getAccentColor(Context context )
	{
		if (!mAccentLoaded)
		{
			switch(mSelectedStyle)
			{
				case APP:
				{
					mAccentColor = ThemeUtils.getThemeAccentColor(context);
					break;
				}

				case HOOK:
				{
					//Loaded from swiftkey resources
					//mAccentColor = ContextUtils.getHookContext().getResources().getIdentifier("emoji_highlight_color", "color", ExiXposed.HOOK_PACKAGE_NAME);
					//Screw it, something broke and it's constant anyway
					mAccentColor = 0xFF28b8ce;
				}
			}

			mAccentLoaded = true;
		}

		return mAccentColor;
	}

	public static int getTabHeight(Context context)
	{
		if (!mTabHeightLoaded)
		{
			mTabHeight = (int)DimenUtils.calculatePixelFromDp(context, TAB_HEIGHT_DP_VALUE);
			mTabHeightLoaded = true;
		}

		return mTabHeight;
	}


}
