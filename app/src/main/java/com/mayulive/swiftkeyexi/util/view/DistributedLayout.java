package com.mayulive.swiftkeyexi.util.view;


import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.MathUtils;

import java.util.ArrayList;

/**
 * Created by Roughy on 7/10/2017.
 */

public class DistributedLayout extends FrameLayout
{
	private static String LOGTAG = ExiModule.getLogTag(DistributedLayout.class);

	ArrayList<ChildViewInfo> mChildInfo = new ArrayList<>();
	ArrayList<RowInfo> mRowInfo = new ArrayList<>();
	int UNSPECIFIED_MEASURE_SPEC = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);

	private int mTargetItemWidth = 0;
	private float mFittingWeight = 0.5f;

	private int mPrevWidth = 0;

	public void setFittingWeight(float weight)
	{
		mFittingWeight = weight;
	}

	public DistributedLayout(@NonNull Context context)
	{
		super(context);
	}

	public DistributedLayout(@NonNull Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
	}

	public DistributedLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	public void setTargetItemWidth(int size)
	{
		mTargetItemWidth = size;
	}

	private static class ChildViewInfo
	{
		boolean consumed = false;
		int viewIndex = -1;
		int width = 0;
		int height = 0;
	}

	private static class RowInfo
	{
		ArrayList< ChildViewInfo > rowChildren = new ArrayList<>();
		int consumedWidth = 0;
		int height = 0;

		public void addChild(ChildViewInfo child)
		{
			child.consumed = true;
			rowChildren.add(child);
			consumedWidth += child.width;
		}

		public boolean canFit(ChildViewInfo child, int maxWidth)
		{
			return child.width <= getRemainingWidth( maxWidth );
		}

		public int getRemainingWidth(int maxWidth)
		{
			return maxWidth - consumedWidth;
		}

	}

	private void resizeChildArray()
	{
		while(mChildInfo.size() > getChildCount())
		{
			mChildInfo.remove( mChildInfo.size()-1 );
		}

		while(mChildInfo.size() < getChildCount())
		{
			mChildInfo.add(new ChildViewInfo() );
		}
	}

	private void resetChildConsumptionState()
	{
		for (ChildViewInfo info : mChildInfo)
		{
			info.consumed = false;
		}
	}

	private void resetRows()
	{
		for (RowInfo info : mRowInfo)
		{
			info.consumedWidth = 0;
			info.rowChildren.clear();
		}
	}




	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{

		{
			//Log.i(LOGTAG, "Height "+ViewTools.measureSpecToString(heightMeasureSpec));
			//Log.i(LOGTAG, "Width "+ViewTools.measureSpecToString(widthMeasureSpec));
		}


		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		//if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)
		{
			//Log.i(LOGTAG, "Width: "+widthSize);
			//Log.i(LOGTAG, "Mode: "+widthMode);

			if (mPrevWidth != widthSize)
			{
				//Height is effectively ignore, as we need to layout everything anyway.
				resizeChildArray();

				//Measure all childviews. only care about width.
				for (int i = 0; i < this.getChildCount(); i++)
				{
					ChildViewInfo info = mChildInfo.get(i);
					View child = getChildAt(i);
					child.measure(MeasureSpec.makeMeasureSpec( widthSize, MeasureSpec.AT_MOST ) ,UNSPECIFIED_MEASURE_SPEC);

					info.consumed = false;
					info.viewIndex = i;
					info.width = child.getMeasuredWidth();
					info.height = child.getMeasuredHeight();


					if (mTargetItemWidth != 0)
					{

						//Log.i(LOGTAG, "Existing width: "+info.width);

						//If target width is != 0 we want the width to round to nearest multiple
						//Weight set so that we round up unless we are /very/ close to fitting anyway
						info.width = (int) MathUtils.roundToNearestMultiple(info.width, mTargetItemWidth, mFittingWeight);

						//Log.i(LOGTAG, "Multiple: "+mTargetItemWidth+", Closest: "+info.width);
					}

				}

				calculateLayout( widthSize);
			}

			int calculatedHeight = 0;
			for (RowInfo row : mRowInfo)
			{
				calculatedHeight += row.height;
			}

			if ( heightMode == MeasureSpec.UNSPECIFIED )
			{
				setMeasuredDimension(widthSize,calculatedHeight);
			}
			else
			{
				setMeasuredDimension(widthSize,heightSize);
			}
		}
	}

	private @Nullable ChildViewInfo findViewForSpace(int space)
	{
		for (ChildViewInfo info : mChildInfo)
		{
			if (!info.consumed)
			{
				if (info.width <= space)
				{
					info.consumed = true;
					return info;
				}
			}
		}

		return null;
	}

	private RowInfo getRowInfo(int rowNumber)
	{
		while(mRowInfo.size() <= rowNumber)
		{
			mRowInfo.add(new RowInfo());
		}

		return mRowInfo.get(rowNumber);
	}

	private void calculateLayout(int maxWidth)
	{
		resetRows();
		resetChildConsumptionState();

		{
			int rowCounter = 0;

			RowInfo currentRow = getRowInfo(rowCounter);
			ChildViewInfo fillerChild = null;

			for (int i = 0; i < mChildInfo.size(); i++)
			{
				ChildViewInfo child = mChildInfo.get(i);
				if (!child.consumed)
				{
					int remainingWidth = currentRow.getRemainingWidth( maxWidth );

					if ( currentRow.consumedWidth <= 0 || currentRow.canFit(child, maxWidth) )
					{
						//Add child if it fits into current row
						currentRow.addChild(child);
					}
					//Try to find an item to fit in the gap
					else if ( ( ( mTargetItemWidth == 0 && remainingWidth > 0 ) || remainingWidth >= mTargetItemWidth) &&
							(fillerChild = findViewForSpace( remainingWidth ) ) != null )
					{
						//Add filler, then repeat this iteration.
						currentRow.addChild( fillerChild );
						i--;
					}
					else
					{
						//Gap to be filled, no item to fill gap.
						//SKip this row, repeat iteration.
						rowCounter++;
						currentRow = getRowInfo(rowCounter);

						i--;

						continue;
					}

					remainingWidth = currentRow.getRemainingWidth( maxWidth );

					//Move to next row if full
					if  ( ( mTargetItemWidth == 0 && remainingWidth <= 0) || remainingWidth < mTargetItemWidth)
					{
						rowCounter++;
						currentRow = getRowInfo(rowCounter);
					}
				}
			}
		}

		for (RowInfo row : mRowInfo)
		{
			for (ChildViewInfo child : row.rowChildren)
			{
				if (child.height > row.height)
					row.height = child.height;
			}
		}
	}

	private void doLayout()
	{
		int left;
		int top;
		int right;
		int bottom;

		if (!mRowInfo.isEmpty())
		{
			//Layout!
			int heightCounter = 0;
			for (RowInfo row : mRowInfo)
			{
				//Left over to center
				int widthCounter =  (this.getMeasuredWidth()  - row.consumedWidth) / 2 ;

				for (ChildViewInfo child : row.rowChildren)
				{
					View childView = getChildAt(child.viewIndex);

					left = widthCounter;
					top = heightCounter;
					right = widthCounter+child.width;
					bottom = heightCounter+row.height;

					if (true)	//TODO add switch to remeasure maybe
					{
						//Remeasure view with final dimensions, The problem with this is that the view may decide to move things around.
						//If you want to get everything centered properly without this happening, see below
						childView.measure( MeasureSpec.makeMeasureSpec( child.width, MeasureSpec.EXACTLY ), MeasureSpec.makeMeasureSpec( row.height, MeasureSpec.EXACTLY )  );
					}
					else
					{
						//Figure out how much measured height and width differs from actual height and width, and shift the positions accordingly
						left += ( child.width - childView.getMeasuredWidth() ) / 2;
						top += ( child.height - childView.getMeasuredHeight() ) / 2;

					}



					childView.layout( left, top, right, bottom );

					widthCounter += child.width;
				}

				heightCounter += row.height;
			}
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		mPrevWidth = 0;
		doLayout();

	}
}
