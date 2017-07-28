package com.mayulive.swiftkeyexi.util;

import android.content.Context;
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

}
