package com.mayulive.swiftkeyexi.xposed.selection.selectionstuff;

/**
 * Created by Roughy on 6/7/2017.
 */
public enum PointerState
{
	DEFAULT,
	SHIFT,
	DELETE,
	SWIPE,
	HOLD,
	ACTION,
	MODIFIER,
	SPACE_MODIFIER,
	LEFT_SWIPE,
	RIGHT_SWIPE;

	public static boolean isSwipe(PointerState state)
	{
		switch (state)
		{
			case SWIPE:
				return true;
			case LEFT_SWIPE:
				return true;
			case RIGHT_SWIPE:
				return true;
			default:
				return false;
		}
	}

	public static boolean isSelect(PointerState state)
	{
		switch (state)
		{
			case LEFT_SWIPE:
				return true;
			case RIGHT_SWIPE:
				return true;
			default:
				return false;
		}
	}

	public static boolean isHold(PointerState state)
	{
		switch (state)
		{
			case SHIFT:
				return true;
			case DELETE:
				return true;
			case HOLD:
				return true;
			default:
				return false;
		}
	}
}
