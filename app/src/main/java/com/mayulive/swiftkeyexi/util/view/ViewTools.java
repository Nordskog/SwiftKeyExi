package com.mayulive.swiftkeyexi.util.view;

import android.graphics.Point;
import android.view.View;

import com.mayulive.swiftkeyexi.util.MathUtils;

/**
 * Created by Roughy on 6/26/2017.
 */

public class ViewTools
{
	//Get the position of view in parent, even if view is not a direct child.
	public static Point getPositionInParent(View parent, View view)
	{
		int[] parentPos = getPositionInWindow(parent);
		int[] viewPos = getPositionInWindow(view);

		return new Point(viewPos[0] - parentPos[0], viewPos[1] - parentPos[1]);
	}

	/**
	 *
	 * @param view
	 * @return array containing x,y coordinates of window
	 */
	public static int[] getPositionInWindow(View view)
	{
		int[] pos = new int[2];
		view.getLocationInWindow(pos);
		return pos;
	}

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
