package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.EmojiCache.ImageEmojiItem;
import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.SharedTheme;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiModifierBackgroundDrawable;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiModifiers;
import com.mayulive.swiftkeyexi.util.ColorUtils;
import com.mayulive.swiftkeyexi.util.view.ViewTools;


/**
 * Created by Roughy on 10/25/2017.
 */

public class EmojiModifiersPopup extends RelativeLayout
{

	private static String LOGTAG = ExiModule.getLogTag(EmojiModifiersPopup.class);

	private LinearLayout mContentWindow;

	private OnEmojiClickedListener mClickListener = null;

	public EmojiModifiersPopup(Context context, String baseEmoji)
	{
		super(context);
		init(context,baseEmoji);
	}

	private void init(Context context, String baseEmoji)
	{
		mContentWindow = new LinearLayout(context);

		mContentWindow.setBackground( new EmojiModifierBackgroundDrawable(
				context,
				SharedTheme.getSwiftkeyThemeAccentColor(),
				ColorUtils.multiplyColorBrightness(SharedTheme.getSwiftkeyThemeAccentColor(),
						SharedTheme.getCurrentThemeType() == SharedTheme.DARK_THEME_IDENTIFIER ? 0.4f : 0.8f)
		));


		String[] modifierApplied = new String[EmojiModifiers.FITZPATRICK_MODIFIERS.length + 1];
		modifierApplied[0] = baseEmoji;
		for (int i = 1; i < modifierApplied.length; i++)
		{
			modifierApplied[i] = EmojiModifiers.applyModifier( baseEmoji, EmojiModifiers.FITZPATRICK_MODIFIERS[i-1] );
		}


		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(context);

		for (int i = 0; i < modifierApplied.length; i++)
		{

			final String modifiedString = modifierApplied[i];

			ImageEmojiItem imageView = new ImageEmojiItem(context);
			imageView.setRenderImmediate(true);
			imageView.setEmojiText(modifierApplied[i], dimens.configured_emojiTextSize, null, 0);
			mContentWindow.addView(imageView);

			imageView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (mClickListener != null)
					{
						mClickListener.onEmojiClicked(modifiedString);
					}
				}
			});

			imageView.setMinimumHeight((int)dimens.configured_singleEmojiWidth);
			imageView.setMinimumWidth((int)dimens.configured_singleEmojiWidth);
		}
	}

	public void showInOverlay(RelativeLayout overlay, ViewGroup emojiParent, View anchor)
	{
		if (emojiParent == null)
		{
			Log.e(LOGTAG, "Parent was null, cannot display");
			return;
		}
		if (anchor == null)
		{
			Log.e(LOGTAG, "Anchor was null, cannot display");
			return;
		}
		if (overlay == null)
		{
			Log.e(LOGTAG, "Overlay was null, cannot display");
			return;
		}

		//Add a background layer that will dismiss on touch
		FrameLayout background = new FrameLayout(overlay.getContext());
		RelativeLayout.LayoutParams outerParams = new RelativeLayout.LayoutParams(emojiParent.getMeasuredWidth(), emojiParent.getMeasuredHeight());
		outerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		this.addView(background, outerParams);

		final View thiz = this;
		background.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				//Remove self if touched outside of content
				if (event.getActionMasked() == MotionEvent.ACTION_DOWN || event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN)
				{
					ViewGroup parent = (ViewGroup)thiz.getParent();
					if (parent != null)
						parent.removeView(thiz);
				}
				return true;
			}
		});

		////////////

		int x = 0; int y = 0;

		//Get position in parent
		{
			Point relativePosition = ViewTools.getPositionInParent(overlay, anchor);
			x = relativePosition.x;
			y = relativePosition.y;
		}

		//For reasons unknown, any method of getting a recyclerview viewholder's position will
		//result in a result that is offset by 20% of the viewholder's height and width.
		//That goes for the above method, and if you try to loop through until you reach the parent yourself.
		x += anchor.getMeasuredWidth()*0.2;
		//A bit of extra space makes them a lot easier to click, so leave this out.
		//y+= anchor.getMeasuredHeight()*0.2;

		//Measure content view
		mContentWindow.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

		//Offset to center at top of anchor
		x += anchor.getMeasuredWidth() / 2;

		//adjust positions to top/left layout values
		y -= mContentWindow.getMeasuredHeight();
		x -= mContentWindow.getMeasuredWidth() / 2;

		//Depending on the position of the anchor and the width of the content view
		//we may end up off-screen. Adjust accordingly.
		if (x < 0 )
			x = 0;


		if (x + mContentWindow.getMeasuredWidth() >= overlay.getMeasuredWidth() )
		{
			x = overlay.getMeasuredWidth() - mContentWindow.getMeasuredWidth();
		}

		//Set params for content
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.leftMargin = x;
		params.topMargin = y;

		this.addView(mContentWindow, params);


		//And add self to parent
		RelativeLayout.LayoutParams selfParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		overlay.addView(this, selfParams);
	}

	public void setOnEmojiClickedListener(OnEmojiClickedListener listener)
	{
		mClickListener = listener;
	}

	public interface OnEmojiClickedListener
	{
		void onEmojiClicked(String emoji);
	}



}
