package com.mayulive.swiftkeyexi.util.view;

import android.view.View;

/**
 * Created by Roughy on 6/26/2017.
 */

public class ViewTools
{
	public static int getPositionInWindowX(View view)
	{
		int[] pos = new int[2];
		view.getLocationInWindow(pos);
		return pos[0];
	}

	public static int getPositionInWindowY(View view)
	{
		int[] pos = new int[2];
		view.getLocationInWindow(pos);
		return pos[1];
	}

	public static String measureSpecToString(int measurespec)
	{
		int size = View.MeasureSpec.getSize(measurespec);
		int mode = View.MeasureSpec.getMode(measurespec);

		String returnString = "Mode : ";

		if (mode ==  -2147483648)
			returnString += "AT_MOST, ";
		else if (mode ==  1073741824)
			returnString += "EXACTLY, ";
		else if (mode ==  0)
			returnString += "UNSPECIFIED, ";

		returnString +="Size: "+size;

		return returnString;

	}


}
