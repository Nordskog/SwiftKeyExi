package com.mayulive.swiftkeyexi.xposed.selection.selectionstuff;

/**
 * Created by Roughy on 6/7/2017.
 */ //When setting selection, the view will
//move to follow the end, so end should
//always be the cursor position actively
//moving.
public class CursorSelection
{
	public int start = 0;
	public int end = 0;

	public CursorSelection()
	{
	}

	;

	public CursorSelection(int start, int end)
	{
		this.start = start;
		this.end = end;
	}

	;
}
