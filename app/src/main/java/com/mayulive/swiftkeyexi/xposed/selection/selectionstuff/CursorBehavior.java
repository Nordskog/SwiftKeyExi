package com.mayulive.swiftkeyexi.xposed.selection.selectionstuff;

/**
 * Created by Roughy on 6/7/2017.
 */
public enum CursorBehavior
{
	DISABLED,
	SWIPE,
	HOLD_SHIFT_SWIPE,
	HOLD_ANY_SWIPE,
	SPACE_SWIPE,
	NUMBER_ROW_SWIPE;

	public boolean isMultiKey()
	{
		return this == HOLD_SHIFT_SWIPE ||  this == HOLD_ANY_SWIPE;
	}
}
