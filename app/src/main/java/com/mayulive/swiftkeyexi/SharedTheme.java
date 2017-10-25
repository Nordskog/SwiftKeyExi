package com.mayulive.swiftkeyexi;


import android.content.Context;
import android.os.Build;

import com.mayulive.swiftkeyexi.EmojiCache.ImageEmojiItem;

//For theme stuff that needs to be accessed in both the config app and swiftkey.
//Each of them is responsible for setting the value, and certain bits and pieces
//refer to this to get the current value.
public class SharedTheme
{
	public static final int DARK_THEME_IDENTIFIER = 1;
	public static final int LIGHT_THEME_IDENTIFIER = 0;

	private static final int DARK_THEME_ACCENT_COLOR = 0xFF1a1a1c;
	private static final int LIGHT_THEME_ACCENT_COLOR = 0xFFe4e7e8;

	private static final int DARK_THEME_ACCENT_COLOR_NOUGAT = 0xFF393941;
	private static final int LIGHT_THEME_ACCENT_COLOR_NOUGAT = 0xFFffffff;

	private static int mCurrentThemeIdentifier = 1;

	public static void setCurrenThemeType(Context context, int theme)
	{
		mCurrentThemeIdentifier = theme;
		ImageEmojiItem.setThemeType(context, theme);
	}

	public static int getCurrentThemeType()
	{
		return mCurrentThemeIdentifier;
	}

	public static int getSwiftkeyThemeAccentColor()
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
		{
			if (mCurrentThemeIdentifier == DARK_THEME_IDENTIFIER)
				return DARK_THEME_ACCENT_COLOR_NOUGAT;
			else
				return LIGHT_THEME_ACCENT_COLOR_NOUGAT;
		}
		else
		{
			if (mCurrentThemeIdentifier == DARK_THEME_IDENTIFIER)
				return DARK_THEME_ACCENT_COLOR;
			else
				return LIGHT_THEME_ACCENT_COLOR;
		}


	}

}
