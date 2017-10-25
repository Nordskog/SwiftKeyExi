package com.mayulive.swiftkeyexi.util;

import android.graphics.Color;

/**
 * Created by Roughy on 10/25/2017.
 */

public class ColorUtils
{

	/**
	 *
	 * @param brightness Brightness between 0 and 1
	 * @return
	 */
	public static int setColorBrightness(int color, float brightness)
	{
		float[] hsv = new float[3];
		Color.colorToHSV(color, hsv);
		hsv[2] = brightness;
		color = Color.HSVToColor(hsv);

		return color;
	}

	/**
	 *
	 * @param brightness Brightness between 0 and 1
	 * @return
	 */
	public static int multiplyColorBrightness(int color, float brightness)
	{
		float[] hsv = new float[3];
		Color.colorToHSV(color, hsv);
		hsv[2] *= brightness; // value component
		color = Color.HSVToColor(hsv);

		return color;
	}


}
