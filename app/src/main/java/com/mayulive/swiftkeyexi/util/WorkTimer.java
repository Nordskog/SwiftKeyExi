package com.mayulive.swiftkeyexi.util;

import android.util.Log;

public class WorkTimer
{
	public WorkTimer()
	{

	}

	private long time = System.currentTimeMillis();

	public void start()
	{
		time = System.currentTimeMillis();
	}

	public long getElapsedAndReset()
	{
		long elapsed = System.currentTimeMillis() - time;
		time = System.currentTimeMillis();
		return elapsed;
	}
}
