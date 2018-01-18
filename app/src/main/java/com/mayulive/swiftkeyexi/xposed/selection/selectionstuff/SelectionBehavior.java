package com.mayulive.swiftkeyexi.xposed.selection.selectionstuff;

/**
 * Created by Roughy on 6/7/2017.
 */
public enum SelectionBehavior
{
	DISABLED,
	SHIFT_DELETE_DRAG_SWIPE,
	HOLD_AND_DRAG_SWIPE,
	HYBRID;

	public boolean triggersFromShiftAndDelete()
	{
		return this == HYBRID || this == SHIFT_DELETE_DRAG_SWIPE;
	}

	public boolean isGesture()
	{
		return (this == SelectionBehavior.HOLD_AND_DRAG_SWIPE || this == SelectionBehavior.HYBRID);
	}

}
