package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


import com.mayulive.swiftkeyexi.EmojiCache.EmojiCache;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiContainer;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.shared.SharedStyles;
import com.mayulive.swiftkeyexi.util.view.ScrollbarRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by Roughy on 12/4/2016.
 */

public class NormalEmojiPanelView extends ScrollbarRecyclerView implements EmojiPanelView
{
	emojiPanelAdapter mAdapter = null;

	LayoutManager mLayoutManager = null;
	GridLayoutManager mGridLayoutManager = null;


	DB_EmojiPanelItem mItem = null;


	ArrayList<EmojiAdapterItem> mAdapterItems = new ArrayList<EmojiAdapterItem>();

	Map<String, EmojiUsedCounter> mUsedCounter;

	public NormalEmojiPanelView(Context context, @Nullable AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public NormalEmojiPanelView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
	}

	public NormalEmojiPanelView(Context context)
	{
		//The things you have to do to get scrollbars around here
		super(context);
	}

	public static NormalEmojiPanelView getWithScrollbars(Context context)
	{
		//return new NormalEmojiPanelView(new ContextThemeWrapper(context, R.style.ScrollbarRecyclerView));

		NormalEmojiPanelView panel = new NormalEmojiPanelView(context);

		return panel;
	}


	public void init(DB_EmojiPanelItem item, int viewWidth, int itemWidth)
	{
		mItem = item;
		prepareAdapterItems(true);
		prepareUsedCounter();

		mAdapter = new emojiPanelAdapter(this.getContext(), mAdapterItems, mItem, false, false);

		mGridLayoutManager = new GridLayoutManager(this.getContext(), EmojiResources.calculateColCount(this.getContext(), viewWidth, itemWidth));
		mLayoutManager = mGridLayoutManager;

		this.setScrollbarEnabled(true);
		this.setScrollbarColor(SharedStyles.getAccentColor(this.getContext()));

		this.setLayoutManager(mLayoutManager);
		this.setAdapter(mAdapter);
	}

	//Only intended for use with static lists, does not support item changes
	//It also assumes there is only one instance of any given string.
	//When loading user-defined dictionaries, make sure to remove any duplicates.
	private void prepareUsedCounter()
	{
		mUsedCounter = new HashMap<String, EmojiUsedCounter>();

		ArrayList<DB_EmojiItem> items = mItem.get_items();

		for (int i = 0; i < items.size(); i++)
		{
			String currentEmoji = items.get(i).get_text();

			EmojiUsedCounter currentCounter = mUsedCounter.get(currentEmoji);
			if (currentCounter == null)
			{
				currentCounter = new EmojiUsedCounter(i, 0);
				mUsedCounter.put(currentEmoji, currentCounter);
			}
			else
			{
				currentCounter.addPosition(i);
			}
		}
	}

	@Override
	public void addMark(String emojiString)
	{

		EmojiUsedCounter currentCounter = mUsedCounter.get(emojiString);
		if (currentCounter != null)
		{
			currentCounter.add();
			updateMarkByCounter(currentCounter);
		}
	}

	@Override
	public void subtractMark(String emojiString)
	{

		EmojiUsedCounter currentCounter = mUsedCounter.get(emojiString);
		if (currentCounter != null)
		{
			currentCounter.subtract();
			updateMarkByCounter(currentCounter);
		}
	}

	private int itemToViewPos (int position)
	{
		return mAdapter.getViewIndexFromItemIndex(position);
	}

	//Assumes counter has changed, but adapter items and views
	//have not yet been updated to reflect this change.
	private void updateMarkByCounter(EmojiUsedCounter counter)
	{
		if (counter.count < 1)	//Not used
		{
			for (Integer currentPosition : counter.position)
			{
				EmojiAdapterItem adapterItem = mAdapterItems.get(currentPosition);
				boolean currentValue = adapterItem.marked;

				if (currentValue)	//Only update if state has changed
				{
					EmojiContainer currentContainer = (EmojiContainer) mLayoutManager.findViewByPosition(  itemToViewPos(currentPosition) );
					if (currentContainer != null)
						currentContainer.setMarked(false);
				}

				adapterItem.marked = false;
			}
		}
		else	//Used
		{
			for (Integer currentPosition : counter.position)
			{
				EmojiAdapterItem adapterItem = mAdapterItems.get(currentPosition);
				boolean currentValue = adapterItem.marked;

				if (!currentValue)	//Only update if state has changed
				{
					EmojiContainer currentContainer = (EmojiContainer) mLayoutManager.findViewByPosition(  itemToViewPos(currentPosition) );
					if (currentContainer != null)
						currentContainer.setMarked(true);
				}

				adapterItem.marked = true;
			}
		}
	}

	@Override
	public void unmarkAll()
	{
		for (int i = 0; i < mAdapterItems.size(); i++)
		{
			mAdapterItems.get(i).marked = false;
		}

		for (int i = 0; i < mLayoutManager.getChildCount(); i++)
		{
			EmojiContainer currentContainer = (EmojiContainer)mLayoutManager.getChildAt(i);
			currentContainer.setMarked(false);
		}


		for (EmojiUsedCounter currentCounter : mUsedCounter.values())
		{
			currentCounter.clearCounter();
			updateMarkByCounter(currentCounter);
		}

	}

	@Override
	public void clear()
	{
		//mItem.set_needsUpdate(true);
		mItem.get_items().clear();
		mAdapterItems.clear();
		mAdapter.notifyDataSetChanged();

	}

	@Override
	public void markAll()
	{
		for (int i = 0; i < mAdapterItems.size(); i++)
		{
			mAdapterItems.get(i).marked = true;
		}

		for (int i = 0; i < mLayoutManager.getChildCount(); i++)
		{
			EmojiContainer currentContainer = (EmojiContainer)mLayoutManager.getChildAt(i);
			currentContainer.setMarked(true);
		}

		for (EmojiUsedCounter currentCounter : mUsedCounter.values())
		{
			currentCounter.clearCounter();
			currentCounter.add();
			updateMarkByCounter(currentCounter);
		}


	}

	@Override
	public void markInput(List<DB_EmojiItem> items)
	{
		//Reset count
		for (EmojiUsedCounter currentCounter : mUsedCounter.values())
		{
			currentCounter.count = 0;
		}

		//Count using input array
		for (DB_EmojiItem currentItem : items)
		{
			EmojiUsedCounter currentCounter = mUsedCounter.get(currentItem.get_text());
			if (currentCounter != null)
			{
				currentCounter.add();
			}
		}

		//Update mark on all
		for (EmojiUsedCounter currentCounter : mUsedCounter.values())
		{
			updateMarkByCounter(currentCounter);
		}

	}

	private void prepareAdapterItems(boolean reset)
	{
		ArrayList<DB_EmojiItem> emojiItems = mItem.get_items();

		while (mAdapterItems.size() < emojiItems.size())
		{
			mAdapterItems.add( new EmojiAdapterItem( emojiItems.get(mAdapterItems.size()), false) );
		}

		while (mAdapterItems.size() > emojiItems.size() )
		{
			mAdapterItems.remove( mAdapterItems.size()-1);
		}


		if (reset)
		{
			for (EmojiAdapterItem currentPair : mAdapterItems)
			{
				currentPair.marked = false;
			}
		}
	}

	@Override
	public void addAll(ArrayList<DB_EmojiItem> inputItems)
	{
		//mItem.set_needsUpdate(true);

		int startCount = mItem.get_items().size();

		TableList localItems = mItem.get_items();
		localItems.startBatchEdit();

		for (int i = 0; i < inputItems.size(); i++)
		{
			DB_EmojiItem newItem = new DB_EmojiItem(inputItems.get(i).get_text());

			localItems.add( newItem);
			mAdapterItems.add(new EmojiAdapterItem(newItem,false));
		}

		localItems.endBatchEdit();

		//mAdapter.notifyDataSetChanged();
		mAdapter.notifyItemRangeInserted(startCount, inputItems.size());
	}

	@Override
	public void addItem(int position, DB_EmojiItem item)
	{
		//mItem.set_needsUpdate(true);
		mItem.get_items().add(position,item);
		mAdapterItems.add(position, new EmojiAdapterItem(item,false));
		mAdapter.notifyItemInserted(position);
	}

	@Override
	public void addItem(DB_EmojiItem item)
	{
		//mItem.set_needsUpdate(true);
		mItem.get_items().add(item);
		mAdapterItems.add(new EmojiAdapterItem(item,false));
		mAdapter.notifyItemInserted(mItem.get_items().size()-1);
	}

	@Override
	public void removeItem(int position)
	{
		//I'd rather it crash so I can figure out what's broken upstream
		//if (position >= 0 && position < mItem.get_items().size())
		{
			//mItem.set_needsUpdate(true);
			mItem.get_items().remove(position);
			mAdapterItems.remove(position);
			mAdapter.notifyItemRemoved(position);
		}
	}

	@Override
	public void scrollToEnd()
	{
		if (!mAdapterItems.isEmpty())
			this.scrollToPosition( mAdapterItems.size() - 1 );
	}

	@Override
	public void moveItem(int currentPosition, int insertPosition)
	{
		ArrayList<DB_EmojiItem> items = mItem.get_items();

		DB_EmojiItem item = items.get(currentPosition);

		items.remove(currentPosition);
		mAdapterItems.remove(currentPosition);

		items.add(insertPosition,item);
		mAdapterItems.add(insertPosition,new EmojiAdapterItem(item,false));

		mAdapter.notifyItemMoved(currentPosition,insertPosition);
	}


	@Override
	public emojiPanelAdapter getAdapter()
	{
		return mAdapter;
	}

	@Override
	public void trimItems(int maxSize)
	{
		if (mItem.get_items().size() > maxSize)
		{
			int startSize = mItem.get_items().size();

			mItem.trimItems(maxSize);
			while (mAdapterItems.size() > maxSize)
			{
				mAdapterItems.remove( mAdapterItems.size() - 1 );
			}

			mAdapter.notifyItemRangeRemoved(maxSize-1, startSize-maxSize );
		}


	}

	@Override
	public int getColumnWidth()
	{
		return mItem.get_column_width();
	}


	@Override
	public void setColumnWidth(int itemWidth)
	{
		//Only allow if layout is grid
		if (mGridLayoutManager != null)
		{
			if (itemWidth < 1)
			{
				itemWidth = 1;
			}

			EmojiCache.clearIfWidthTooSmall(mItem, itemWidth);

			mItem.set_column_width(itemWidth);

			invalidateAllViews();

			mGridLayoutManager.setSpanCount( EmojiResources.calculateColCount(this.getContext(), this.getMeasuredWidth(), itemWidth ) );
		}
		else
		{

		}
	}

	@Override
	public void invalidateAllViews()
	{
		//Stackoverflow says this is how to do it, so this is how we do it.
		this.setAdapter(null);
		this.setAdapter(mAdapter);
	}



	public class EmojiAdapterItem
	{
		boolean marked = false;
		DB_EmojiItem item = null;


		EmojiAdapterItem(DB_EmojiItem item, boolean marked)
		{
			this.item = item;
			this.marked = marked;


			//checkIfEmoji();
		}
	}

	public void setPanelIcon(String icon)
	{
		mItem.set_icon(icon);
		//mItem.set_needsUpdate(true);
	}

	public String getPanelIcon()
	{
		return mItem.get_icon();
	}

	public void setPanelItem(DB_EmojiPanelItem item)
	{
		mItem = item;
	}

	public DB_EmojiPanelItem getPanelItem()
	{
		return mItem;
	}

	//Keeps track of how many instance of a given emoji
	//has already been added to the neighbor panel.
	//Access via a hash of the emoji string
	private static class EmojiUsedCounter
	{
		ArrayList<Integer> position = new ArrayList<Integer>();
		int count;

		public EmojiUsedCounter(int position, int count)
		{

			this.position.add(position);
			this.count = count;
		}

		public EmojiUsedCounter(int[] position, int count)
		{

			for (int currentInt : position)
			{
				this.position.add(currentInt);
			}
			this.count = count;
		}

		public void addPosition(int position)
		{
			this.position.add(position);
		}

		public void clearCounter()
		{
			count = 0;
		}

		public void add()
		{
			count++;
		}

		public void subtract()
		{
			count--;
		}

		public boolean isUsed()
		{
			return count > 0;
		}

	}


}
