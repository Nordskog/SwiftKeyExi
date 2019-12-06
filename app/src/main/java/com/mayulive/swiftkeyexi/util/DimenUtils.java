package com.mayulive.swiftkeyexi.util;

import android.content.Context;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Roughy on 6/28/2017.
 */

public class DimenUtils
{
	public static float calculatePixelFromDp(Context context, int dpSize)
	{
		return TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, dpSize, context.getResources().getDisplayMetrics() );
	}

	public static float getRawDpResource( Context context, int resId )
	{

		DisplayMetrics metrics =  context.getResources().getDisplayMetrics();
		float logicalDensity = metrics.density;

		float px = context.getResources().getDimension( resId );

		if (logicalDensity != 0)
		{
			return Math.round( px / logicalDensity);
		}

		return 1;
	}

}
