package com.mayulive.swiftkeyexi.util;

/**
 * Created by Roughy on 11/30/2017.
 */

public class ArrayUtils
{
	public static boolean containsInt( int[] array, int pattern)
	{
		for (int item : array)
		{
			if ( item == pattern)
				return true;
		}

		return false;
	}

}
