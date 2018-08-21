package com.mayulive.swiftkeyexi.util;

import android.util.Log;

public class WorkTimer
{

	private long totalElapsed = 0;
	private long time = System.currentTimeMillis();

	public WorkTimer()
	{

	}



	public void start()
	{
		time = System.currentTimeMillis();
	}

	public long getTotal()
	{
		return totalElapsed;
	}

	public long getElapsedAndReset()
	{
		long elapsed = System.currentTimeMillis() - time;
		time = System.currentTimeMillis();
		totalElapsed += elapsed;
		return elapsed;
	}
}
