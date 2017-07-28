package com.mayulive.swiftkeyexi.xposed.selection.selectionstuff;

/**
 * Created by Roughy on 6/7/2017.
 */
public enum SpaceModifierBehavior
{
	KEY,
	MENU,
	DISABLED;

	public boolean isEnabled()
	{
		return (this == KEY || this == MENU);
	}
}
