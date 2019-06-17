package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;

import com.mayulive.swiftkeyexi.main.emoji.NormalEmojiPanelView;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
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

	public static int[] getScrenSize()
	{
		int[] result = new int[2];
		DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
		result[0] = dm.widthPixels;
		result[1] = dm.heightPixels;

		return result;
	}

	public static boolean isInsideViewGlobal( View sourceView, float sourceX, float sourceY, View targetView )
	{
		// Get relative location in drop target
		int[] sourceInWindow = ViewTools.getPositionInWindow( sourceView );
		int[] targetInWindow = ViewTools.getPositionInWindow( targetView );

		// Actual screen position
		sourceX = sourceInWindow[0] + sourceX;
		sourceY = sourceInWindow[1] + sourceY;

		if (  sourceX > targetInWindow[0] && sourceX < targetInWindow[0] + targetView.getMeasuredWidth() )
		{
			if (  sourceY > targetInWindow[1] && sourceY < targetInWindow[1] + targetView.getMeasuredHeight() )
			{
				return true;
			}
		}

		return false;
	}



}
