package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import android.util.AttributeSet;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiContainer;
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
		try
		{
			mSelectedColor = ThemeUtils.getThemeAccentColor(this.getContext());
		}
		catch (Exception ex)
		{
			//Will get called in the hook as well, but we don't need it there.
			//Might fail?
		}

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

	//Call if we are in a hooked context
	public void setHookValues(Context hookContext, Context moduleContext)
	{
		//Not available in hook land
		int indicatorColorRes = hookContext.getResources().getIdentifier("emoji_highlight_color", "color", ExiXposed.HOOK_PACKAGE_NAME);
		//int indicatorHeightRes = hookContext.getResources().getIdentifier("tabIndicatorHeight", "attr", "com.touchtype.swiftkey.beta");

		this.setSelectedTabIndicatorColor( hookContext.getResources().getColor(indicatorColorRes));
		this.setSelectedTabIndicatorHeight( (int) moduleContext.getResources().getDimension(R.dimen.xposed_tabIndicatorHeight) );

		mSelectedColor = hookContext.getResources().getColor(indicatorColorRes);
	}


}
