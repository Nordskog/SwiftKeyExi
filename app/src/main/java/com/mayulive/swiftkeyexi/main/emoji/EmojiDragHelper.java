package com.mayulive.swiftkeyexi.main.emoji;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;

import java.lang.reflect.Field;

public class EmojiDragHelper extends ItemTouchHelper
{
	public EmojiDragHelper(Callback callback)
	{
		super(callback);
	}


}