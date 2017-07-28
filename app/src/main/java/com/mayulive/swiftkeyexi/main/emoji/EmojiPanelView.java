package com.mayulive.swiftkeyexi.main.emoji;

/**
 * Created by Roughy on 7/7/2017.
 */


import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Roughy on 12/4/2016.
 */

public interface EmojiPanelView
{
	void addMark(String emojiString);
	void subtractMark(String emojiString);
	void unmarkAll();
	void markAll();
	void markInput(List<DB_EmojiItem> items);


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

	void setPanelItem(DB_EmojiPanelItem item);
	DB_EmojiPanelItem getPanelItem();

	interface OnEmojiItemClickListener
	{
		void onClick(DB_EmojiItem item, EmojiPanelView view, DB_EmojiPanelItem panel, int position);
		void onLongPress(DB_EmojiItem item, EmojiPanelView view, DB_EmojiPanelItem panel, int position);
	}
}
