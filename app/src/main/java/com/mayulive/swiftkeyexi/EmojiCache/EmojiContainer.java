package com.mayulive.swiftkeyexi.EmojiCache;

import android.view.View;

/**
 * Created by Roughy on 12/3/2016.
 */

public interface EmojiContainer
{
	int AUTO = 0;

	//textSize is in pixel units
	void setEmojiText(String text, float textSize, Object panelKey, int StyleId);
	String getText();
	View getView();
	void setMarked(boolean marked);
	boolean getMarked();
	void setItemWidth(int emojiWidthUnits);
	void setSingleLine(boolean singleLine);
	void setTint(int tintColor);
	void clearTint();
}
