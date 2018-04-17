package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiContainer;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.EmojiCache.ImageEmojiItem;
import com.mayulive.swiftkeyexi.EmojiCache.NormalEmojiItem;
import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiModifiers;
import com.mayulive.swiftkeyexi.util.view.DistributedLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roughy on 7/7/2017.
 */

public class RecentsEmojiPanelView extends ScrollView implements EmojiPanelView
{

	private static String LOGTAG = ExiModule.getLogTag(RecentsEmojiPanelView.class);

	//com.nex3z.flowlayout.FlowLayout mFlow = null;
	DistributedLayout mFlow = null;
	private DB_EmojiPanelItem mItem;
	EmojiPanelView.OnEmojiItemClickListener mClickListener = null;
	private int mColCount = 1;
	private int mLastWidth = 0;

	public RecentsEmojiPanelView(Context context)
	{
		super(context);
		mFlow = new DistributedLayout(context);
	}

	public RecentsEmojiPanelView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mFlow = new DistributedLayout(context, attrs);
	}

	public void init(DB_EmojiPanelItem item)
	{
		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(this.getContext());

		mItem = item;
		this.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		mFlow.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		mFlow.setTargetItemWidth((int)dimens.configured_singleEmojiWidth);
		mFlow.setFittingWeight(0.9f);
		populate();
		this.addView(mFlow);
	}

	private void populate()
	{
		for (DB_EmojiItem item : mItem.get_items())
		{
			addView(item);
		}
	}

	private void addView(DB_EmojiItem item)
	{
		addView(mFlow.getChildCount(),item);
	}

	private void addView(int position, DB_EmojiItem item)
	{

		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(this.getContext());

		final EmojiContainer itemView;
		if (item.get_type() == EmojiItem.EmojiType.CONTAINS_EMOJI)
		{
			itemView = new ImageEmojiItem(this.getContext());
		}
		else
		{
			itemView = new NormalEmojiItem(this.getContext());
			((NormalEmojiItem)itemView).setMinWidth(  (int) dimens.configured_singleEmojiWidth);
		}

		String emojiText = item.get_text();
		//If diverse modifier supported, check and apply default modifier
		if (item.get_modifiers_supported())
		{
			if (!EmojiResources.getDefaultDiverseModifier().isEmpty())
			{
				emojiText = EmojiModifiers.applyModifier(emojiText, EmojiResources.getDefaultDiverseModifier());
			}
		}

		itemView.setItemWidth(EmojiContainer.AUTO);
		itemView.setEmojiText(emojiText, dimens.configured_emojiTextSize, null, 0 );
		itemView.setModifable(item.get_modifiers_supported());

		EmojiPanelCommons.setTouchAnimation((View)itemView);



		((View)itemView).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (mClickListener != null)
				{
					int position = mFlow.indexOfChild(v);
     					mClickListener.onClick( mItem.get_items().get(position), (View)itemView, RecentsEmojiPanelView.this, mItem, position );
				}
			}
		});

		((View)itemView).setOnLongClickListener(new OnLongClickListener()
		{
			@Override
			public boolean onLongClick(View v)
			{
				if (mClickListener != null)
				{
					int position = mFlow.indexOfChild(v);
					mClickListener.onLongPress( mItem.get_items().get(position), (View)itemView, RecentsEmojiPanelView.this, mItem, position );

					return true;
				}

				return false;
			}
		});

		mFlow.addView(((View)itemView), position);
	}

	public void setOnEmojiItemClickedListener( OnEmojiItemClickListener listener )
	{
		mClickListener = listener;
	}

	///////////
	//Interface
	///////////

	//Recents doesn't need these
	@Override public void addMark(String emojiString){}
	@Override public void subtractMark(String emojiString){}
	@Override public void unmarkAll(){}
	@Override public void markAll(){}
	@Override public void markInput(List<DB_EmojiItem> items){}
	@Override public void setColumnWidth(int itemWidth){}
	@Override public int getColumnWidth(){ return 0; }


	@Override
	public void addItem(int position, DB_EmojiItem item)
	{
		mItem.get_items().add(position, item);
		addView(position, item);
	}

	@Override
	public void addItem(DB_EmojiItem item)
	{
		mItem.get_items().add(item);
		addView(item);

	}

	@Override
	public void addAll(ArrayList<DB_EmojiItem> inputItems)
	{

	}

	@Override
	public void moveItem(int currentPosition, int insertPosition)
	{
		DB_EmojiItem item = mItem.get_items().remove(currentPosition);
		mItem.get_items().add(insertPosition, item);

		View view = mFlow.getChildAt(currentPosition);
		mFlow.removeViewAt(currentPosition);
		mFlow.addView(view, insertPosition);
	}

	@Override
	public void removeItem(int position)
	{
		mItem.get_items().remove(position);
		mFlow.removeViewAt(position);
	}

	@Override
	public void scrollToEnd()
	{

	}

	@Override
	public void clear()
	{
		this.mItem.clear_items();
		mFlow.removeAllViews();
	}

	@Override
	public void trimItems(int maxSize)
	{
		if (maxSize > 0)
		{
			while(mItem.get_items().size() > maxSize)
			{
				mItem.get_items().remove( mItem.get_items().size() - 1 );
				mFlow.removeViewAt( mFlow.getChildCount() - 1);
			}
		}
	}


	@Override
	public void invalidateAllViews()
	{

	}

	@Override
	public void setPanelIcon(String icon)
	{
		mItem.set_icon(icon);
	}

	@Override
	public String getPanelIcon()
	{
		return mItem.get_icon();
	}

	@Override
	public void setPanelItem(DB_EmojiPanelItem item)
	{
		mItem = item;
	}

	@Override
	public DB_EmojiPanelItem getPanelItem()
	{
		return mItem;
	}
}
