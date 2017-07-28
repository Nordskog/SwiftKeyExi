package com.mayulive.swiftkeyexi.util;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

import com.mayulive.swiftkeyexi.R;

/**
 * Created by Roughy on 5/19/2017.
 */

public class ThemeUtils
{
	public static int getThemeAccentColor (final Context context)
	{
		final TypedValue value = new TypedValue ();
		context.getTheme ().resolveAttribute (R.attr.colorAccent, value, true);
		return value.data;
	}

	public static int getThemeAttribute (final Context context, int attribute)
	{
		final TypedValue value = new TypedValue ();
		context.getTheme ().resolveAttribute (attribute, value, true);
		return value.data;
	}



	public static int getThemeAttribute (final Context context, String attribute)
	{
		int res = context.getResources().getIdentifier(attribute, "attr", context.getPackageName());
		return getThemeAttribute(context, res);

	}

}