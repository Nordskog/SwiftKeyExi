package com.mayulive.swiftkeyexi.main.emoji;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiCache;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiItem;
import com.mayulive.swiftkeyexi.shared.SharedStyles;
import com.mayulive.swiftkeyexi.util.view.ScrollbarRecyclerView;
import com.mayulive.swiftkeyexi.util.view.ViewTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Roughy on 12/4/2016.
 */

public class NormalEmojiPanelView extends ScrollbarRecyclerView implements EmojiPanelView
{
	emojiPanelAdapter mAdapter = null;

	LayoutManager mGridLayoutManager = null;

	ItemTouchHelper.SimpleCallback mDragHelperCallback;
	EmojiDragHelper mDragHelper;

	DB_EmojiPanelItem mItem = null;

	EmojiPanelView mCompanionPanel = null;
	View mDropTargetGarbage = null;

	EmojiPanelView.OnEmojiItemClickListener mClickListener = null;

	OnDragEventListener mDragEventListener;

	//////////////////
	// Dragging
	/////////////////////

	boolean mIsDragDropEditable = false;
	boolean mIsDragDropSource = false;

	OverdragLocation mOverdragLocation = OverdragLocation.NONE;

	boolean mIsDragging = false;

	float mDragEndPosX = 0;
	float mDragEndPosY = 0;

	int mViewWidth = 0;
	int mItemWidth = 0;

	enum OverdragLocation
	{
		NONE, ABOVE, BELOW, GARBAGE
	}


	//ArrayList<EmojiAdapterItem> mAdapterItems = new ArrayList<EmojiAdapterItem>();

	private EmojiUsedCounter mUsedCounter = new EmojiUsedCounter();

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
		NormalEmojiPanelView panel = new NormalEmojiPanelView(context);

		return panel;
	}


	public void init(DB_EmojiPanelItem item, int viewWidth, int itemWidth, boolean isDragDropEditable, boolean isDragDropSource)
	{
		mItem = item;
		resetAndCountCurrent();

		mAdapter = new emojiPanelAdapter(this.getContext(), mItem.get_items(), mItem, false, false, !(isDragDropEditable || isDragDropSource) );

		this.setScrollbarEnabled(true);
		this.setScrollbarColor(SharedStyles.getAccentColor(this.getContext()));

		mViewWidth = viewWidth;
		mItemWidth = itemWidth;

		updateLayoutManager(mItemWidth, false);

		// Goes here

		this.setAdapter(mAdapter);

		this.mIsDragDropEditable = isDragDropEditable;
		this.mIsDragDropSource = isDragDropSource;

		this.setupDragDrop();

		mAdapter.setOnItemClickListener(new emojiPanelAdapter.OnEmojiHolderClickListener()
		{
			@Override
			public void onClick(emojiPanelAdapter.EmojiItemHolder item, int position)
			{
				if (mClickListener != null)
				{
					mClickListener.onClick( mItem.get_items().get(position), item.itemView, NormalEmojiPanelView.this, mItem, position);
				}
			}

			@Override
			public void onLongPress(emojiPanelAdapter.EmojiItemHolder item, int position)
			{
				// Drag and drop consumes long presses in this state.
				if (mIsDragDropEditable || mIsDragDropSource)
					return;

				if (mClickListener != null)
				{
					mClickListener.onLongPress( mItem.get_items().get(position), item.itemView, NormalEmojiPanelView.this, mItem, position);
				}
			}
		});

	}

	/////////////////////////
	// Counter
	///////////////////////

	@Override
	public EmojiUsedCounter getUsedCounter()
	{
		return mUsedCounter;
	}

	private void resetAndCountCurrent()
	{
		mUsedCounter.clearCounter();
		mUsedCounter.count( mItem.get_items() );
	}

	@Override
	public void notifyCompanionItemsChanged()
	{
		mAdapter.notifyDataSetChanged();
	}

	private void SendNotifyCompanionItemsChanged()
	{
		if ( mCompanionPanel != null )
		{
			mCompanionPanel.notifyCompanionItemsChanged();
		}
	}

	////////////////////
	// Drag and drop
	////////////////////

	private void setOverdragLocation( OverdragLocation location )
	{
		if (mDragEventListener != null)
		{
			if (location != mOverdragLocation)
			{
				// clear old hover state
				switch(mOverdragLocation)
				{
					case GARBAGE:
					{
						mDragEventListener.onGarbageHover(false);
						break;
					}

					case BELOW:
					{
						mDragEventListener.onCompanionHover(false);
						break;
					}
				}

				// Set new hover state
				switch(location)
				{
					case GARBAGE:
					{
						mDragEventListener.onGarbageHover(true);
						break;
					}

					case BELOW:
					{
						mDragEventListener.onCompanionHover(true);
						break;
					}
				}
			}
		}


		mOverdragLocation = location;

	}

	public void setCompanions(EmojiPanelView companion, View garbageDropTarget )
	{
		mCompanionPanel = companion;
		mDropTargetGarbage = garbageDropTarget;

		// Only keep track of items in companion if we are a drag-drop source
		if ( mIsDragDropSource && mCompanionPanel != null )
		{
			mAdapter.setUsedCounter( mCompanionPanel.getUsedCounter() );
			notifyCompanionItemsChanged();
		}
		else
		{
			mAdapter.setUsedCounter(null);
		}

	}

	public void receiveDrop( float x, float y, DB_EmojiItem item )
	{
		if ( !mIsDragDropEditable )
		{
			EmojiDialogCommons.displayUneditableWarning(this.getContext());
			return;
		}

		View closestView = this.findChildViewUnder(x, y);

		int insertPos = mItem.get_items().size();

		if (closestView != null)
		{
			ViewHolder holder = this.findContainingViewHolder(closestView);
			if (holder != null)
			{
				insertPos = holder.getAdapterPosition();
			}
		}

		addItem(insertPos, new DB_EmojiItem( item ));
	}

	@Override
	public void setOnDragEventListener(OnDragEventListener listener)
	{
		mDragEventListener = listener;
	}

	private void updateOrderFromIndex()
	{
		TableList<DB_EmojiItem> items = mItem.get_items();
		for (int i = 0; i < items.size(); i++ )
		{
			items.get(i).set_last_change(i);
		}

		items.updateAll();
	}

	@SuppressLint("ClickableViewAccessibility")
	private void setupDragDrop()
	{


		if (!mIsDragDropEditable && !mIsDragDropSource)
			return;

		//Item moved/removed in list
		this.mDragHelperCallback = new ItemTouchHelper.SimpleCallback(
				ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, 0)
		{

			@Override
			public void onSelectedChanged(ViewHolder viewHolder, int actionState)
			{
				// Docs lie, viewHolder is always null
				super.onSelectedChanged(viewHolder, actionState);

				if (actionState == ItemTouchHelper.ACTION_STATE_DRAG)
				{
					mOverdragLocation = OverdragLocation.NONE;
					mIsDragging = true;

					if ( mDragEventListener != null )
					{
						mDragEventListener.onDragStarted();
					}

				}
				else
				{
					// Ideally we'd remove the view here, but since the viewholder is null and onMove may not have been called,
					// We need to rely on clearView to do it instead. Gay.
					// overdrag location will be used and reset in onClearView.
					//mIsDragging = false;
				}

			}

			@Override
			public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
			{
				viewHolder.itemView.setZ(99);

				if (mIsDragDropEditable)
				{
					mItem.get_items().setDatabaseMode( TableList.DatabaseMode.MANUAL );

					int startPos = viewHolder.getAdapterPosition();
					int endPos = target.getAdapterPosition();

					int insertPos = endPos;

					DB_EmojiItem moveItem = mItem.get_items().remove(startPos);
					mItem.get_items().add(insertPos, moveItem);

					mItem.get_items().setDatabaseMode(TableList.DatabaseMode.IMMEDIATE);

					mAdapter.notifyItemMoved(startPos, endPos);

					return true;
				}

				return false;

			}


			@Override
			public boolean isLongPressDragEnabled() {
				return true;
			}

			@Override
			public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
			{
				return;
			}

			@Override
			public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int viewSize, int viewSizeOutOfBounds, int totalSize, long msSinceStartScroll)
			{
				if ( mIsDragDropSource )
				{
					// Don't want to trigger scroll when we are dragging emoji from template to keyboard
					return 0;
				}
				else
				{
					return super.interpolateOutOfBoundsScroll(recyclerView, viewSize, viewSizeOutOfBounds, totalSize, msSinceStartScroll);
				}

			}

			@Override
			public long getAnimationDuration(RecyclerView recyclerView, int animationType, float animateDx, float animateDy)
			{
				// Skip animation if we are dropping above or below
				if (	mOverdragLocation == OverdragLocation.BELOW ||
						mOverdragLocation == OverdragLocation.GARBAGE)
				{
					return 0;
				}
				else
				{
					return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
				}

			}

			@Override
			public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
			{

				viewHolder.itemView.setZ(0);

				mIsDragging = false;

				if (mDragEventListener != null)
				{
					mDragEventListener.onDragEnded();
				}

				super.clearView(recyclerView, viewHolder);

				boolean noAction = true;

				if (mIsDragDropEditable)
				{
					if (  mOverdragLocation == OverdragLocation.GARBAGE)
					{
						int position = viewHolder.getAdapterPosition();
						if (position != NO_POSITION)
						{
							NormalEmojiPanelView.this.removeItem( position );
						}

						noAction = false;
					}
					else if ( mOverdragLocation == OverdragLocation.NONE )
					{
						// No need to update anything on deletion.
						updateOrderFromIndex();
						noAction = false;
					}
				}

				if (noAction && mIsDragDropSource)
				{
					if (mOverdragLocation == OverdragLocation.BELOW)
					{
						if (mCompanionPanel != null && mCompanionPanel instanceof View)
						{


							// Get relative location in drop target
							int[] selfInWindow = ViewTools.getPositionInWindow( NormalEmojiPanelView.this );
							int[] otherInWindow = ViewTools.getPositionInWindow( (View) mCompanionPanel);

							// X should be the same.
							float relativeY = mDragEndPosY + selfInWindow[1];
							relativeY -= otherInWindow[1];

							if ( viewHolder instanceof emojiPanelAdapter.EmojiItemHolder)
							{
								emojiPanelAdapter.EmojiItemHolder emojiHolder = (emojiPanelAdapter.EmojiItemHolder) viewHolder;

								// Receive drop is responsible deciding if it can be edited, and displaying the appropriate warning otherwise.
								mCompanionPanel.receiveDrop( mDragEndPosX, relativeY, new DB_EmojiItem( emojiHolder.mItem ) );
							}


						}
					}
				}

				// Finally done with this. Reset.
				setOverdragLocation(OverdragLocation.NONE);
			}


		};


		// Gets called during move event and the following up. Does not receive other events.
		this.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if (mIsDragging)
				{
					if ( event.getActionMasked() == MotionEvent.ACTION_UP)
					{
						mDragEndPosX = event.getX();
						mDragEndPosY = event.getY();
					}

					// Above
					if (  mDropTargetGarbage != null && ViewTools.isInsideViewGlobal( NormalEmojiPanelView.this, event.getX(), event.getY(), mDropTargetGarbage ) )
					{
						setOverdragLocation(OverdragLocation.GARBAGE);
					}
					else if ( event.getY() < 0)
					{
						setOverdragLocation(OverdragLocation.ABOVE);
					}
					// Below
					else if ( event.getY() > NormalEmojiPanelView.this.getMeasuredHeight() )
					{
						setOverdragLocation(OverdragLocation.BELOW);
					}
					else	// Nothing
					{
						setOverdragLocation(OverdragLocation.NONE);
					}
				}

				return false;
			}
		});




		mDragHelper = new EmojiDragHelper(mDragHelperCallback);
		mDragHelper.attachToRecyclerView(this);
	}

	private int itemToViewPos (int position)
	{
		return mAdapter.getViewIndexFromItemIndex(position);
	}


	@Override
	public void clear()
	{
		mItem.get_items().clear();
		mUsedCounter.clearCounter();
		mAdapter.notifyDataSetChanged();
		SendNotifyCompanionItemsChanged();
	}

	@Override
	public void addAll(ArrayList<DB_EmojiItem> inputItems)
	{
		int startCount = mItem.get_items().size();

		TableList localItems = mItem.get_items();
		localItems.startBatchEdit();

		for (int i = 0; i < inputItems.size(); i++)
		{
			DB_EmojiItem newItem = new DB_EmojiItem(inputItems.get(i));
			newItem.set_last_change(i);

			localItems.add( newItem);
			mUsedCounter.add( newItem );

		}

		localItems.endBatchEdit();

		mAdapter.notifyItemRangeInserted(startCount, inputItems.size());
		SendNotifyCompanionItemsChanged();
	}

	@Override
	public void addItem(int position, DB_EmojiItem item)
	{
		//mItem.set_needsUpdate(true);
		mItem.get_items().add(position,item);
		mAdapter.notifyItemInserted(position);

		mUsedCounter.add( item );

		// Needlessly expensive, but we never actually call this.
		updateOrderFromIndex();
		SendNotifyCompanionItemsChanged();
	}

	@Override
	public void addItem(DB_EmojiItem item)
	{
		//mItem.set_needsUpdate(true);
		item.set_last_change( mItem.get_items().size() );	// Set order from index here
		mItem.get_items().add(item);
		mAdapter.notifyItemInserted(mItem.get_items().size()-1);
		mUsedCounter.add( item );
		SendNotifyCompanionItemsChanged();
	}

	@Override
	public void removeItem(int position)
	{
		//I'd rather it crash so I can figure out what's broken upstream
		//if (position >= 0 && position < mItem.get_items().size())
		{
			EmojiItem item = mItem.get_items().get(position);

			mItem.get_items().remove(position);
			mAdapter.notifyItemRemoved(position);
			mUsedCounter.remove( item.get_text() );
			SendNotifyCompanionItemsChanged();
		}
	}

	@Override
	public void scrollToEnd()
	{
		if (!mItem.get_items().isEmpty())
			this.scrollToPosition( mItem.get_items().size() - 1 );
	}

	@Override
	public void moveItem(int currentPosition, int insertPosition)
	{
		ArrayList<DB_EmojiItem> items = mItem.get_items();

		DB_EmojiItem item = items.get(currentPosition);

		items.remove(currentPosition);

		items.add(insertPosition,item);

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

			for (int i = maxSize - 1; i < mItem.get_items().size(); i++)
			{
				mUsedCounter.remove( mItem.get_items().get(i).get_text() );
			}

			mItem.trimItems(maxSize);

			mAdapter.notifyItemRangeRemoved(maxSize-1, startSize-maxSize );
			SendNotifyCompanionItemsChanged();
		}


	}

	@Override
	public int getColumnWidth()
	{
		return mItem.get_column_width();
	}


	/**
	 *
	 * @param itemWidth
	 * @param updateGridColumns whether we calculate and call setSpanCount. If done too early
	 *                          the items will have the wrong width for a while. I dunno.
	 */
	private void updateLayoutManager(int itemWidth, boolean updateGridColumns)
	{
		if (itemWidth < 1 )
		{
			FlexboxLayoutManager manager = null;
			if ( mGridLayoutManager instanceof FlexboxLayoutManager)
			{
				manager = (FlexboxLayoutManager) mGridLayoutManager;
			}
			else
			{
				manager = new FlexboxLayoutManager(this.getContext());
				manager.setFlexDirection(FlexDirection.ROW);
				manager.setJustifyContent(JustifyContent.SPACE_AROUND);
			}

			mGridLayoutManager = manager;
			this.setLayoutManager(mGridLayoutManager);
		}
		else
		{
			GridLayoutManager manager = null;
			if ( mGridLayoutManager instanceof GridLayoutManager )
			{
				manager = (GridLayoutManager) mGridLayoutManager;
			}
			else
			{
				manager = new GridLayoutManager(this.getContext(), EmojiResources.calculateColCount(this.getContext(), mViewWidth, mItemWidth));
			}

			mGridLayoutManager = manager;
			if (updateGridColumns)
				manager.setSpanCount( EmojiResources.calculateColCount(this.getContext(), this.getMeasuredWidth(), mItemWidth ) );
			this.setLayoutManager(mGridLayoutManager);
		}

	}

	@Override
	public void setColumnWidth(int itemWidth)
	{

		if (itemWidth < 1)
			itemWidth = 0;
		mItemWidth = itemWidth;

		EmojiCache.clearIfWidthTooSmall(mItem, itemWidth);
		mItem.set_column_width(itemWidth);
		invalidateAllViews();

		updateLayoutManager(itemWidth, true);
	}

	@Override
	public void invalidateAllViews()
	{
		//Stackoverflow says this is how to do it, so this is how we do it.
		this.setAdapter(null);
		this.setAdapter(mAdapter);
	}

	public void setOnEmojiItemClickedListener( OnEmojiItemClickListener listener )
	{
		mClickListener = listener;
	}

	public void setPanelIcon(String icon)
	{
		mItem.set_icon(icon);
	}

	public String getPanelIcon()
	{
		return mItem.get_icon();
	}


	public DB_EmojiPanelItem getPanelItem()
	{
		return mItem;
	}

	//Keeps track of how many instance of a given emoji
	//has already been added to the neighbor panel.
	//Access via a hash of the emoji string
	protected static class EmojiUsedCounter
	{
		HashMap<String, Integer> counter = new HashMap<>();

		public EmojiUsedCounter()
		{

		}

		private int getCount( String emoji )
		{
			Integer count = this.counter.get(emoji);
			if (count == null)
				return 0;
			else
				return count;
		}

		private void setCount( String emoji, int newCount )
		{
			if (newCount <= 0)
			{
				this.counter.remove(emoji);
			}
			else
			{
				this.counter.put(emoji, newCount);
			}
		}

		public void clearCounter()
		{
			counter.clear();
		}

		public void count(List<? extends EmojiItem> list)
		{
			for (EmojiItem item : list)
			{
				int getCount = getCount(item.get_text());
				setCount(item.get_text(), getCount + 1);
			}
		}

		public void add( EmojiItem emoji )
		{
			int getCount = getCount(emoji.get_text());
			setCount(emoji.get_text(), getCount + 1);
		}

		public void remove( EmojiItem emoji )
		{
			int getCount = getCount(emoji.get_text());
			setCount(emoji.get_text(), getCount - 1);
		}

		public void add( String emoji )
		{
			int getCount = getCount(emoji);
			setCount(emoji, getCount + 1);
		}

		public void remove( String emoji )
		{
			int getCount = getCount(emoji);
			setCount(emoji, getCount - 1);
		}

		public boolean contains( String emoji )
		{
			// When counter hits 0 we remove it, so it should be null.
			return counter.containsKey( emoji );
		}

	}


}
