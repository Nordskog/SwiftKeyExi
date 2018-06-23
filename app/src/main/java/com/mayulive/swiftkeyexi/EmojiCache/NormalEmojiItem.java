package com.mayulive.swiftkeyexi.EmojiCache;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import com.mayulive.swiftkeyexi.R;

/**
 * Created by Roughy on 12/3/2016.
 */

public class NormalEmojiItem extends AppCompatTextView implements EmojiContainer
{
	boolean mMarked = false;
	float mTextSize = 14;

	private static boolean mAssetsLoaded = false;
	private static Typeface mSimpleEmojiFont = null;

	public NormalEmojiItem(Context context) {
		super(context);
		init();
	}

	public NormalEmojiItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}



	public NormalEmojiItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public static boolean isAssetsLoaded()
	{
		return mAssetsLoaded;
	}

	public static void loadAssets(Typeface font)
	{
		mSimpleEmojiFont = font;
		mAssetsLoaded = true;
	}


	private void init()
	{
		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(this.getContext());
		//In the config app we want to use the default, provided by the theme.
		//Swiftkey uses a dark or light font depending on the theme of the keyboard,
		//which should have been loaded for us.
		if (EmojiResources.EMOJI_COLOR != 0)
		{
			setTextColor(EmojiResources.EMOJI_COLOR);
		}

		setGravity(Gravity.CENTER);
		setClickable(true);

		//this.setPaddingRelative(0,25,0,25);

		int hPadding = (int)dimens.emojiHorizontalPadding;
		int vPadding = (int)dimens.emojiVerticalPadding;

		this.setIncludeFontPadding(false);

		this.setPadding(hPadding,vPadding,hPadding,vPadding);
	}

	@Override
	public void setMarked(boolean marked)
	{
		mMarked = marked;

		if (marked)
		{
			setTextColor(getTextColors().withAlpha(50));
		}
		else
		{
			setTextColor(getTextColors().withAlpha(255));
		}
	}

	@Override
	public boolean getMarked()
	{
		return mMarked;
	}

	@Override
	public void setItemWidth(int emojiWidthUnits)
	{

	}

	@Override
	public void setTint(int tintColor)
	{
		//Save current color in highlightColor value because we're not using it anyway ...
		//This is a great idea.
		this.setHighlightColor(this.getCurrentTextColor());
		this.setTextColor(tintColor);
	}

	@Override
	public void clearTint()
	{
		this.setTextColor(this.getHighlightColor());

	}

	//Linter is wrong, claims that COMPLEX_UNIT_PX should be typeface.normal/bold/italic/bold_italic.
	@SuppressWarnings("WrongConstant")
	@Override
	public void setEmojiText(String text, float textSize, Object key, int styleId)
	{
		mTextSize = textSize;

		this.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSize);

		if (styleId != 0 && mSimpleEmojiFont != null)
		{
			super.setTypeface(mSimpleEmojiFont,0);
		}
		else
		{
			super.setTypeface(Typeface.DEFAULT);
		}

		//Does not cache
		super.setText(text);
	}

	@Override
	public String getText()
	{
		return super.getText().toString();
	}

	@Override
	public View getView()
	{
		return this;
	}

	@Override
	public void setModifable(boolean modifiable)
	{
		//Stub, will never be used
	}
}
