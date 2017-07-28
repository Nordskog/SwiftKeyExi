package com.mayulive.swiftkeyexi.main.dictionary;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;

import java.lang.reflect.Field;

public class SlowRecyclerView extends RecyclerView
{
	private static String LOGTAG = ExiModule.getLogTag(SlowRecyclerView.class);

	public SlowRecyclerView(Context context)
	{
		super(context);
		init();
	}

	public SlowRecyclerView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public SlowRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init();

	}

	private void init()
	{
		//decrease scroll-start threshold
		try
		{
			Field field = RecyclerView.class.getDeclaredField("mTouchSlop");
			field.setAccessible(true);

			int slop = field.getInt(this);
			slop *= 10;
			field.set(this, slop);
		}
		catch (Exception e)
		{
			Log.e(LOGTAG," Failed to set recycler sensitivity");
			e.printStackTrace();
		}
	}


}
