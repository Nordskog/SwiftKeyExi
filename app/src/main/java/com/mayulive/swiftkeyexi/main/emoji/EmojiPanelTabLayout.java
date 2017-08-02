package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import android.util.AttributeSet;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiContainer;
import com.mayulive.swiftkeyexi.shared.SharedStyles;
import com.mayulive.swiftkeyexi.util.ThemeUtils;
import com.mayulive.swiftkeyexi.util.view.FixedTabLayout;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;

/**
 * Created by Roughy on 5/19/2017.
 */

public class EmojiPanelTabLayout extends FixedTabLayout
{
	private int mSelectedColor = 0;

	public EmojiPanelTabLayout(Context context)
	{
		super(context);
		init();
	}

	public EmojiPanelTabLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public EmojiPanelTabLayout(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init()
	{
		/*
		try
		{
			mSelectedColor = ThemeUtils.getThemeAccentColor(this.getContext());
		}
		catch (Exception ex)
		{
			//Will get called in the hook as well, but we don't need it there.
			//Might fail?
		}
		*/

		mSelectedColor = SharedStyles.getAccentColor(this.getContext() );
		this.setSelectedTabIndicatorColor( mSelectedColor  );
		this.setSelectedTabIndicatorHeight( SharedStyles.getTabHeight(this.getContext() ));

		setCurrentHighlight();
	}

	private void setCurrentHighlight()
	{
		OnTabSelectedListener listener = new OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(Tab tab)
			{
				if (tab.getCustomView() != null)
				{
					EmojiContainer view = (EmojiContainer)tab.getCustomView();
					view.setTint(mSelectedColor);

				}

			}

			@Override
			public void onTabUnselected(Tab tab)
			{
				if (tab.getCustomView() != null)
				{
					EmojiContainer view = (EmojiContainer)tab.getCustomView();
					view.clearTint();
				}
			}

			@Override
			public void onTabReselected(Tab tab)
			{

			}
		};

		this.addOnTabSelectedListener(listener);
	}

}
