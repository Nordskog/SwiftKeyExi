package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiContainer;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.EmojiCache.ImageEmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiModifiers;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.util.view.HeaderFooterRecyclerAdapter;
import com.mayulive.swiftkeyexi.EmojiCache.NormalEmojiItem;

import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_POSITION;


public class emojiPanelAdapter extends HeaderFooterRecyclerAdapter<emojiPanelAdapter.EmojiItemHolder>
{
	private DB_EmojiPanelItem mPanelItem = null;
    private List<? extends EmojiItem> mItems = null;

    private Context mContext;

	private OnEmojiHolderClickListener mItemClickListener = null;

	private boolean mSingleLine = false;
	private boolean mUseDefaultTextSize = false;

	private int mHorizontalPadding = 0;
	private int mVerticalPadding = 0;

	private boolean mSetLongPressListener = true;

	private boolean mUseItemStyle = false;

	private NormalEmojiPanelView.EmojiUsedCounter mUsedCounter;

    public emojiPanelAdapter(Context context, List<? extends EmojiItem> items, DB_EmojiPanelItem panelItem, boolean singleLine, boolean useDefaultTextSize, boolean setLongPressListener)
    {
		super(context);
        mContext = context;
        mItems = items;
		mPanelItem = panelItem;
		mSingleLine = singleLine;
		mUseDefaultTextSize = useDefaultTextSize;
		mSetLongPressListener = setLongPressListener;
    }

    public void setUsedCounter(NormalEmojiPanelView.EmojiUsedCounter counter )
	{
		mUsedCounter = counter;
	}

	public void setUseItemStyle(boolean use)
	{
		mUseItemStyle = use;
	}

	public void setPadding(int horizontal, int vertical)
	{
		mHorizontalPadding = horizontal;
		mVerticalPadding = vertical;
	}

	@Override
	public int getViewType(int index)
	{
		EmojiItem item = mItems.get(index);
		if (item.get_type() == DB_EmojiItem.EmojiType.CONTAINS_EMOJI)
			return 1;

		return 0;
	}

	@Override
	public EmojiItemHolder onCreateView(ViewGroup parent, int viewType)
	{
		EmojiContainer newItem = null;

		if (viewType == 0)
		{
			//Log.e("###", "Creating text view");
			newItem = new NormalEmojiItem(parent.getContext());
		}
		else
		{
			//Log.e("###", "Creating image view");
			newItem = new ImageEmojiItem(parent.getContext());
		}

		if (mSingleLine)
			newItem.setSingleLine(true);

		newItem.setItemWidth(mPanelItem.get_column_width());


		View itemView = newItem.getView();

		EmojiPanelCommons.setTouchAnimation(itemView);

		return new EmojiItemHolder( newItem );
	}

	@Override
	public void onBindView(final EmojiItemHolder holder, int position)
	{
		EmojiItem item = mItems.get(position);

		//holder.getContainer().getView().setPadding(mHorizontalPadding, mVerticalPadding, mHorizontalPadding, mVerticalPadding);

		holder.setEmojiItem(item);
		holder.getContainer().setMarked( mUsedCounter != null ? mUsedCounter.contains(item.get_text()) : false );
		holder.getContainer().setModifable(item.get_modifiers_supported());

		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(mContext);

		//TODO this is awful, fix text size config
		float textSize = dimens.configured_emojiTextSize;
		if (mUseDefaultTextSize)
			textSize = dimens.default_emojiTextSize;


		String emojiText = item.get_text();
		//If diverse modifier supported, check and apply default modifier
		if (item.get_modifiers_supported())
		{
			if (!EmojiResources.getDefaultDiverseModifier().isEmpty())
			{
				emojiText = EmojiModifiers.applyModifier(emojiText, EmojiResources.getDefaultDiverseModifier());
			}
		}

		holder.getContainer().setEmojiText(emojiText, textSize, mPanelItem, mUseItemStyle ? item.get_style() : mPanelItem.get_style());

		holder.getContainer().getView().setOnClickListener(new View.OnClickListener()
		{
			private EmojiItemHolder mHolder = holder;

			@Override
			public void onClick(View view)
			{
				if (mItemClickListener != null)
				{
					//Turns out the holder is still clickable during the removal animation
					int position = mHolder.getAdapterPosition();

					if (position != NO_POSITION)
						mItemClickListener.onClick(mHolder,position);
				}
			}
		});

		// when we have draggable emoji, this long press listener causes an extra delay and buzz
		if (mSetLongPressListener)
		{
			holder.getContainer().getView().setOnLongClickListener(new View.OnLongClickListener()
			{
				private EmojiItemHolder mHolder = holder;

				@Override
				public boolean onLongClick(View v)
				{
					//Turns out the holder is still clickable during the removal animation
					int position = mHolder.getAdapterPosition();

					if (position != NO_POSITION)
					{
						mItemClickListener.onLongPress(mHolder,position);
						return true;
					}

					return false;
				}
			});
		}



		//Reents is weird
		if (mPanelItem.get_source() == EmojiPanelItem.PANEL_SOURCE.RECENTS)
		{
			if ( item.get_type() == EmojiItem.EmojiType.CONTAINS_EMOJI )
			{
				holder.getContainer().getView().setLayoutParams( new RecyclerView.LayoutParams( 1,1 ));
			}
			else
			{
				holder.getContainer().getView().setLayoutParams( new RecyclerView.LayoutParams( 3,1 ));
			}
		}
	}

	@Override
	public int getCount()
	{
		return mItems.size();
	}

	public OnEmojiHolderClickListener getOnItemClickListener()
	{
		return mItemClickListener;
	}

	public void setOnItemClickListener(OnEmojiHolderClickListener listener)
	{
		mItemClickListener = listener;
	}

	public static class EmojiItemHolder extends HeaderFooterRecyclerAdapter.HFRecyclerViewHolder
	{

		EmojiItem mItem = null;
		EmojiContainer mContainer = null;

		public EmojiItemHolder(View view)
		{
			super(view);
		}

		public EmojiItemHolder(EmojiContainer itemView)
		{
			super(itemView.getView());
			mContainer = itemView;
		}

		public EmojiContainer getContainer()
		{
			return mContainer;
		}

		public void setEmojiItem( EmojiItem item )
		{
			mItem = item;
		}
	}



	public interface OnEmojiHolderClickListener
	{
		void onClick(EmojiItemHolder item, int position);
		void onLongPress(EmojiItemHolder item, int position);
	}

}

