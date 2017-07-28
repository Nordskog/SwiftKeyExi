package com.mayulive.swiftkeyexi.util;

import android.os.Build;

/**
 * Created by Roughy on 12/9/2016.
 */

public class VersionTools
{
	public static boolean isLollipopOrGreater()
	{
		return(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
	}

	public static boolean isNougatOrGreater()
	{
		return(Build.VERSION.SDK_INT >= 24);
	}

}
