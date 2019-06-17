package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.EmojiCache.NormalEmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.util.view.FixedTabLayout;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by Roughy on 12/4/2016.
 */

public class EmojiPanelPagerAdapter extends PagerAdapter
{
	List<DB_EmojiPanelItem> mItems = null;

	private EmojiPanelView.OnEmojiItemClickListener mItemClickListener = null;

	private OnPrimaryViewChangedListener mPrimaryViewChangedListener = null;

	EmojiPanelView mCurrentView = null;

	boolean mProvidePageTitles = true;

	boolean mConfigMode = false;
	EmojiFragment.EmojiPanelType mType;

	public EmojiPanelPagerAdapter(List<DB_EmojiPanelItem> mKeyboardPanels, EmojiFragment.EmojiPanelType type, boolean configMode)
	{
		mItems = mKeyboardPanels;
		mConfigMode = configMode;
		mType = type;
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object)
	{
		super.setPrimaryItem(container,position,object);

		if (mCurrentView != object)
		{
			mCurrentView = (EmojiPanelView)object;

			if (mPrimaryViewChangedListener != null)
			{
				mPrimaryViewChangedListener.onPrimaryViewChanged(mCurrentView, position);
			}
		}
	}

	public void setProvidePageTitles(boolean provide)
	{
		mProvidePageTitles = provide;
	}

	//This is only assured to return a valid item if the item is currently visible.
	//Ie only use this to get the current visible item
	public EmojiPanelView getCurrentView()
	{
		return mCurrentView;
	}



	@Override
	public int getItemPosition(Object object)
	{
		for (int i = 0; i < mItems.size(); i++)
		{
			if (mItems.get(i) == object)
				return i;
		}

		return POSITION_NONE;
	}

	@Override
	public View instantiateItem(ViewGroup container, final int panelPosition)
	{
		final DB_EmojiPanelItem panelItem = mItems.get(panelPosition);

		if (panelItem.get_source() == EmojiPanelItem.PANEL_SOURCE.RECENTS)
		{
			final RecentsEmojiPanelView newPanel = new RecentsEmojiPanelView(container.getContext());
			newPanel.init(panelItem);

			newPanel.setOnEmojiItemClickedListener(new EmojiPanelView.OnEmojiItemClickListener()
			{
				@Override
				public void onClick(DB_EmojiItem item, View view, EmojiPanelView panelView, DB_EmojiPanelItem panel, int position)
				{
					if (mItemClickListener != null)
						mItemClickListener.onClick( item, view,newPanel, mItems.get(panelPosition), position);
				}

				@Override
				public void onLongPress(DB_EmojiItem item, View view, EmojiPanelView panelView, DB_EmojiPanelItem panel, int position)
				{
					if (mItemClickListener != null)
						mItemClickListener.onLongPress(item, view, newPanel, panelItem, position);
				}
			});

			container.addView(newPanel);
			return newPanel;
		}
		else
		{
			final NormalEmojiPanelView newPanel;

			//newPanel = new NormalEmojiPanelView(container.getContext());
			newPanel = NormalEmojiPanelView.getWithScrollbars(container.getContext());
			newPanel.init(
					panelItem,
					container.getMeasuredWidth(),
					panelItem.get_column_width(),
					mConfigMode && panelItem.get_source() == EmojiPanelItem.PANEL_SOURCE.USER,
					mConfigMode && mType == EmojiFragment.EmojiPanelType.DICTIONARY );

			//I have no idea what I'm doing with these settings
			newPanel.setHasFixedSize(true);
			newPanel.setItemViewCacheSize(20);
			//newPanel.setDrawingCacheEnabled(true);
			//newPanel.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

			newPanel.setOnEmojiItemClickedListener(new EmojiPanelView.OnEmojiItemClickListener()
			{
				@Override
				public void onClick(DB_EmojiItem item, View view, EmojiPanelView panelView, DB_EmojiPanelItem panel, int position)
				{
					if (mItemClickListener != null)
						mItemClickListener.onClick( item, view,newPanel, mItems.get(panelPosition), position);
				}

				@Override
				public void onLongPress(DB_EmojiItem item, View view, EmojiPanelView panelView, DB_EmojiPanelItem panel, int position)
				{
					if (mItemClickListener != null)
						mItemClickListener.onLongPress(item, view, newPanel, panelItem, position);
				}
			});


			container.addView(newPanel);
			return newPanel;
		}
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object view)
	{
		if (view == mCurrentView)
			mCurrentView = null;

		container.removeView((View)view);
	}

	@Override
	public int getCount()
	{
		return mItems.size();
	}

	public String getPageTitle(int position)
	{
		if (mProvidePageTitles)
			return mItems.get(position).get_icon();
		else
			return null;
	}

	@Override
	public boolean isViewFromObject(View view, Object object)
	{
		return view == object;
	}

	public void setOnItemClickListener(EmojiPanelView.OnEmojiItemClickListener listener)
	{
		mItemClickListener = listener;
	}

	//For feeding tabs with with icons
	public void setupWithFixedTabLayout(FixedTabLayout tabLayout)
	{

		tabLayout.setOnTabAddedListener(new FixedTabLayout.OnTabAddedListener()
		{
			@Override
			public void onTabAdded(Context context, TabLayout.Tab tab, int position)
			{
				DB_EmojiPanelItem item = mItems.get(position);

				//Would have to clear entire emoji cache when user changes between light and
				//dark themes, using text-only instead.
				//ImageEmojiItem emojiView = new ImageEmojiItem(context);
				NormalEmojiItem emojiView = new NormalEmojiItem(context);
				//emojiView.setRenderImmediate(true);

				emojiView.setPadding(0,0,0,0);


				emojiView.setSingleLine(true);

				EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(context);
				//TODO panel key for tablayout icons ... ?
				emojiView.setEmojiText(item.get_icon(), dimens.default_emojiTextSize * 0.8f, null, item.get_icon_style());
				tab.setCustomView(emojiView);

			}
		});
	}

	public void setOnPrimaryViewChangedListener(EmojiPanelPagerAdapter.OnPrimaryViewChangedListener listener)
	{
		mPrimaryViewChangedListener = listener;
	}

	//We override setPrimaryItem to get the current view, but the pager's onPageChanged listener is called /before/ it,
	//So ... yeah. this shit.
	public interface OnPrimaryViewChangedListener
	{
		void onPrimaryViewChanged(EmojiPanelView view, int position);
	}

}
