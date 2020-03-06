package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.util.AttributeSet;

public class EmojiGarbageView extends FloatingActionButton
{
	public EmojiGarbageView(Context context)
	{
		super(context);
	}

	public EmojiGarbageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public EmojiGarbageView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	@Override
	public void setActivated(boolean activated)
	{
		super.setActivated(activated);

		if (activated)
		{
			this.animate().setDuration(100).rotation(-25);
		}
		else
		{
			this.animate().setDuration(100).rotation(0);
		}
	}

	public void setVisible( boolean visible )
	{
		// Actual error if you set visibility directly on this guy.
		// With this we also get animations so meh

		if (visible)
			show();
		else
			hide();
	}
}
