package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by Roughy on 9/28/2017.
 */

public class DisableableLinearLayoutManager extends LinearLayoutManager
{
	private boolean isScrollEnabled = true;

	public DisableableLinearLayoutManager(Context context)
	{
		super(context);
	}

	public void setScrollEnabled(boolean flag)
	{
		this.isScrollEnabled = flag;
	}

	@Override
	public boolean canScrollVertically()
	{
		return isScrollEnabled && super.canScrollVertically();
	}
}