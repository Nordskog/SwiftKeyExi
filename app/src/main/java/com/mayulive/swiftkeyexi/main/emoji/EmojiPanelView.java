package com.mayulive.swiftkeyexi.main.emoji;

/**
 * Created by Roughy on 7/7/2017.
 */


import android.view.View;

import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;

import java.util.ArrayList;


/**
 * Created by Roughy on 12/4/2016.
 */

public interface EmojiPanelView
{

	void addItem(int position, DB_EmojiItem item);
	void addItem(DB_EmojiItem item);
	void addAll(ArrayList<DB_EmojiItem> inputItems);
	void moveItem(int currentPosition, int insertPosition);
	void removeItem(int position);

	void scrollToEnd();


	void clear();
	void trimItems(int maxSize);

	int getColumnWidth();
	void setColumnWidth(int itemWidth);

	void invalidateAllViews();

	void setPanelIcon(String icon);
	String getPanelIcon();

	NormalEmojiPanelView.EmojiUsedCounter getUsedCounter();

	DB_EmojiPanelItem getPanelItem();

	void notifyCompanionItemsChanged();
	void setCompanions(EmojiPanelView companion, View garbageView );
	void receiveDrop( float x, float y, DB_EmojiItem item );

	void setOnDragEventListener(OnDragEventListener listener);

	interface OnDragEventListener
	{
		void onDragStarted();
		void onDragEnded();
		void onCompanionHover( boolean entered );
		void onGarbageHover( boolean entered );
	}

	interface OnEmojiItemClickListener
	{
		void onClick(DB_EmojiItem item, View view, EmojiPanelView panelView, DB_EmojiPanelItem panel, int position);
		void onLongPress(DB_EmojiItem item, View view, EmojiPanelView panelView, DB_EmojiPanelItem panel, int position);
	}

}
