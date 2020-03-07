package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiContainer;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.shared.SharedStyles;
import com.mayulive.swiftkeyexi.util.view.FixedTabLayout;

/**
 * Created by Roughy on 5/19/2017.
 */

public class EmojiPanelTabLayout extends FixedTabLayout
{
	private int mSelectedColor = 0;
	private OnMeasuredListener mMeasureListener = null;

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
		mSelectedColor = SharedStyles.getAccentColor(this.getContext() );
		this.setSelectedTabIndicatorColor( mSelectedColor  );
		this.setSelectedTabIndicatorHeight( SharedStyles.getTabHeight(this.getContext() ));

		setCurrentHighlight();
	}

	@Override
	public void addTab(@NonNull final Tab tab, int position, boolean setSelected)
	{

		//Set tab padding to roughly half, remove min width
		View view = getTabView(tab);

		if ( view != null)
		{
			EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(this.getContext());

			//In the swiftkey hook, there is no default padding, in the config app there is.
			//Basing the padding on the emoji width seems reasonable.
			view.setPadding( (int) (dimens.default_singleEmojiWidth * 0.1f),
					view.getPaddingTop(),
					(int) (dimens.default_singleEmojiWidth * 0.1f),
					view.getPaddingBottom());

			//So it can resize itself to whatever
			view.setMinimumWidth(1 );

		}


		super.addTab(tab, position, setSelected);



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

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (mMeasureListener != null)
		{
			mMeasureListener.onMeasured(this.getMeasuredWidth(), this.getMeasuredHeight());
		}
	}

	public void setOnMeasureListener( OnMeasuredListener listener )
	{
		mMeasureListener = listener;
	}

	public interface OnMeasuredListener
	{
		void onMeasured(int width, int height);
	}
}
