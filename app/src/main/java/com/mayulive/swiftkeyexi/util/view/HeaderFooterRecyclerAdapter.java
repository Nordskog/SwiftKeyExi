package com.mayulive.swiftkeyexi.util.view;

/**
 * Created by Roughy on 2/18/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * Created by Roughy on 1/10/2017.
 */

public abstract class HeaderFooterRecyclerAdapter<T extends HeaderFooterRecyclerAdapter.HFRecyclerViewHolder> extends RecyclerView.Adapter<T>
{

	public static final int HEADER = 100;
	public static final int FOOTER = 101;

	protected T mHeader = null;
	protected T mFooter = null;


	private Context mContext = null;

	private int mItemOffset = 0;

	private enum HolderType
	{
		NORMAL,HEADER,FOOTER
	}

	public int getViewIndexFromItemIndex(int itemIndex)
	{
		if (mHeader != null)
			itemIndex++;

		return itemIndex;
	}

	public int getItemIndexFromViewIndex(int viewIndex)
	{
		if ( mFooter != null && viewIndex >= getFooterPosition())
		{
			return -1;
		}

		if (mHeader != null)
		{
			if (viewIndex == 0)
				return -1;

			viewIndex--;
		}
		return viewIndex;
	}

	public HeaderFooterRecyclerAdapter(Context context)
	{
		mContext = context;
	}

	public T getHeader()
	{
		return mHeader;
	}

	public void setHeader(T header)
	{
		mHeader = header;
		mItemOffset = 0;
		if (mHeader != null)
		{
			header.setHolderType(HolderType.HEADER);
			mItemOffset = -1;
		}
	}

	public void setFooter(T footer)
	{
		mFooter = footer;
		if (mFooter != null)
			mFooter.setHolderType(HolderType.FOOTER);
	}


	@Override
	public T onCreateViewHolder(ViewGroup parent, int viewType)
	{
		if (viewType == HEADER)
		{
			return mHeader;
		}

		else if (viewType == FOOTER)
			return mFooter;
		else
			return onCreateView(parent, viewType);
	}

	private int getFooterPosition()
	{
		if (mHeader == null)
			return getCount();
		else
			return getCount() + 1;
	}

	@Override
	public void onBindViewHolder( T holder, int position)
	{


		if (mHeader != null && position == 0)
		{
			//Header view, do nothing.
		}
		else if (mFooter != null && position == getFooterPosition() )
		{
			//Footer view, do nothing.
		}
		else
		{
			onBindView((T)holder, position + mItemOffset);
		}
	}

	@Override
	public int getItemViewType(int position)
	{

		if (mHeader != null && position == 0)
			return HEADER;
		else if (mFooter != null && position == getFooterPosition() )
			return FOOTER;
		else
			return getViewType( position + mItemOffset);
	}


	@Override
	public int getItemCount()
	{
		int count = getCount();
		if (mHeader != null)
			count++;
		if (mFooter != null)
			count++;

		return count;
	}


	public static class HFRecyclerViewHolder extends RecyclerView.ViewHolder
	{
		HolderType type = HolderType.NORMAL;

		public HFRecyclerViewHolder(View view)
		{
			super(view);
		}

		protected HolderType getHolderType()
		{
			return type;
		}

		protected void setHolderType(HolderType type)
		{
			this.type = type;
		}
	}

	public abstract T onCreateView(ViewGroup parent, int viewType);
	public abstract void onBindView( T holder, int position);

	public abstract int getViewType(int position);

	public abstract int getCount();

}
