package com.mayulive.swiftkeyexi.main.commons;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.mayulive.swiftkeyexi.util.DimenUtils;
import com.mayulive.swiftkeyexi.util.VersionTools;
import com.mayulive.swiftkeyexi.R;


/**
 * Created by Roughy on 12/8/2016.
 */

public class PopupLinearLayout extends PopupWindow
{
	LinearLayout mMenuLayout = null;
	Context mContext = null;

	int mXoffset = 0;
	int mYoffset = 0;

	private static final int WINDOW_ELEVATION_DP = 5;

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public PopupLinearLayout(Context context)
	{
		super();

		mContext = context;

		mMenuLayout = new LinearLayout(context);

		mMenuLayout.setOrientation( LinearLayout.VERTICAL);
		this.setContentView(mMenuLayout);
		this.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		this.setHeight(1);
		this.setWidth(1);



		this.setBackgroundDrawable(new ColorDrawable(Color.RED));


		if (VersionTools.isLollipopOrGreater())
		{
			this.setElevation(DimenUtils.calculatePixelFromDp(context, WINDOW_ELEVATION_DP)) ;
		}


		this.setOutsideTouchable(true);
		this.setFocusable(true);
		this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

		mMenuLayout.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dismiss();
			}
		});
	}

	public void setOffset(int xOffset, int yOffset)
	{
		mXoffset = xOffset;
		mYoffset = yOffset;
	}

	public LinearLayout getLayoutView()
	{
		return mMenuLayout;
	}

	public void addItem(View view)
	{
		mMenuLayout.addView(view);
	}

	public void addItem(View view, ViewGroup.LayoutParams params)
	{
		mMenuLayout.addView(view, params);
	}

	@Override
	public void showAsDropDown(View anchor, int xoff, int yoff, int gravity)
	{
		//Overriden to apply offset
		super.showAsDropDown(anchor, xoff+mXoffset, yoff+mYoffset, gravity);
	}

	public void showAboveLeft(View anchor)
	{
		mMenuLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		this.showAsDropDown(anchor,-anchor.getMeasuredHeight(),-(anchor.getMeasuredHeight() + mMenuLayout.getMeasuredHeight()), Gravity.CENTER);
	}

	//Center on view contained in this popup
	public void showCenteredOn(View anchor, View centerChild)
	{
		mMenuLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		mMenuLayout.layout(0,0,mMenuLayout.getMeasuredWidth(), mMenuLayout.getMeasuredHeight());

		boolean isContained = false;
		for (int i = 0 ; i < mMenuLayout.getChildCount() && !isContained; i++)
		{
			isContained = centerChild == mMenuLayout.getChildAt(i);
		}

		if (isContained)
		{
			int pos = centerChild.getTop() + (centerChild.getMeasuredHeight() / 2);
			pos += anchor.getMeasuredHeight() / 2;


			this.showAsDropDown(anchor,0, -pos, Gravity.CENTER);

		}
	}

	public void showOntop(View anchor)
	{
		mMenuLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		this.showAsDropDown(anchor,0, -(mMenuLayout.getMeasuredHeight() / 2), Gravity.CENTER);
	}

	public void showAbove(View anchor)
	{
		mMenuLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		this.showAsDropDown(anchor,0,-(anchor.getMeasuredHeight() + mMenuLayout.getMeasuredHeight()), Gravity.CENTER);
	}

	//PopupWindow does not seem to work correctly when given a viewholder view anchor. The position is offset
	//by a certain percentage of the view of the anchor view.
	public void showAboveViewHolder(View anchor)
	{
		mMenuLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

		int xOffset = (anchor.getMeasuredWidth() / 2) - (mMenuLayout.getMeasuredWidth() / 2);

		//The offset appears to be exactly width or height * 0.2, so I'm going to assume it's a constant weirdo thing.
		int viewHolderOffsetX = (int) ((float)anchor.getMeasuredWidth() * 0.2f);
		int viewHolderOffsetY = (int) ((float)anchor.getMeasuredHeight() * 0.2f);

		this.showAsDropDown(
				anchor,
				xOffset+viewHolderOffsetX,
				(-(anchor.getMeasuredHeight() + mMenuLayout.getMeasuredHeight()))+viewHolderOffsetY,
				Gravity.CENTER);
	}


	public void showBelow(View anchor)
	{
		this.showAsDropDown(anchor,0, 0, Gravity.BOTTOM);
	}

}
