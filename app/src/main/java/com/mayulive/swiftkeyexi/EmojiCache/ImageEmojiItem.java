package com.mayulive.swiftkeyexi.EmojiCache;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;


// "You should be using appcompat image view". Yes, but that requires extra resources not available in hook-land.
@SuppressLint("AppCompatCustomView")
public class ImageEmojiItem extends ImageView implements EmojiContainer
{
	private boolean mSingleLine = false;
	boolean mRenderImmediate = false;

	boolean mMarked = false;
	String mText = "";
	float mTextSize = 14;

	//Bitmap bitmap = null;
	EmojiCacheItem mCacheItem = null;

	Object mPanelKey = null;
	int mItemWidth = 1;
	int mConfiguredItemWidth = 1;
	int mStyleIndex = 0;

	int mPrevParentWidth = 0;

	Drawable mNullDrawable = new BitmapDrawable( Bitmap.createBitmap(1,1, Bitmap.Config.ALPHA_8) );

	private EmojiCache.OnRenderCompleteListener mRenderListener = new EmojiCache.OnRenderCompleteListener()
	{
		@Override
		public void onRenderComplete(EmojiCacheItem item)
		{
			ImageEmojiItem.this.mCacheItem = item;
			display();

			//We will probably be changing size once we've finished rendering. Let parent know.
			ViewParent parent = ImageEmojiItem.this.getParent();
			if (parent != null)
			{
				parent.requestLayout();
			}

			fadeIn();
		}
	};

	public ImageEmojiItem(Context context) {
		super(context);
		init();
	}

	public ImageEmojiItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ImageEmojiItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public void setRenderImmediate(boolean renderImmediate)
	{
		mRenderImmediate = renderImmediate;
	}

	public void setSingleLine(boolean singleLine)
	{
		mSingleLine = singleLine;
	}

	@Override
	public void setTint(int tintColor)
	{
		setColorFilter( tintColor,  PorterDuff.Mode.SRC_IN);
	}

	@Override
	public void clearTint()
	{
		super.clearColorFilter();
	}


	private void init()
	{
		this.setScaleType(ScaleType.CENTER);

		setClickable(true);

		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(this.getContext());

		int hPadding = (int)dimens.emojiHorizontalPadding;
		int vPadding = (int)dimens.emojiVerticalPadding;

		this.setPadding(hPadding,vPadding,hPadding,vPadding);

		//Fun fact: If an imageview has passed the layout phase and you set the a net bitmap,
		//it will grow bigger to accommodate it, but not smaller.
	}

	@Override
	public void setMarked(boolean marked)
	{
		mMarked = marked;

		if (marked)
		{
			this.setImageAlpha(50);
		}
		else
		{
			this.setImageAlpha(255);
		}

	}

	public Bitmap getBitmap()
	{
		return mCacheItem.getBitmap();
	}

	@Override
	public boolean getMarked()
	{
		return mMarked;
	}

	@Override
	public void setItemWidth(int emojiWidthUnits)
	{
		mItemWidth = emojiWidthUnits;
		mConfiguredItemWidth = mItemWidth;
	}

	private void updateCacheItem()
	{
		mCacheItem = EmojiCache.getCacheItem(mText,mPanelKey,mStyleIndex);
	}

	private void removeRenderListener()
	{
		if (mRenderListener != null && mCacheItem != null)
		{
			mCacheItem.removeOnRenderCompleteListener(mRenderListener);
		}
	}

	private void display()
	{
		this.setAlpha(1f);

		switch(mCacheItem.getStatus())
		{

			case UNDEFINED:
			{
				if (mRenderImmediate)
				{
					EmojiCacheRenderer.populateEmojiCacheItem(
							ImageEmojiItem.super.getContext(),
							mCacheItem,
							mText,
							mTextSize,
							mStyleIndex,
							mItemWidth,
							mSingleLine
					);
					this.setImageBitmap(mCacheItem.getBitmap());
					mCacheItem.setStatus(EmojiCacheItem.CacheItemStatus.READY);
				}
				else
				{
					mCacheItem.addOnRenderCompleteListener(mRenderListener);
					EmojiCache.requestRender(new EmojiRenderInterface
							(
							ImageEmojiItem.super.getContext(),
							mCacheItem,
							mText,
							mTextSize,
							mStyleIndex,
							mItemWidth,
							mSingleLine,
							mPanelKey)
					);
					this.setImageDrawable(null);

				}

				break;

			}

			case READY:
			{
				this.setImageBitmap(mCacheItem.getBitmap());
				break;
			}

			case RENDERING:
			{
				if (mRenderImmediate)
				{
					mCacheItem.setStatus(EmojiCacheItem.CacheItemStatus.READY);
					EmojiCacheRenderer.populateEmojiCacheItem(
							ImageEmojiItem.super.getContext(),
							mCacheItem,
							mText,
							mTextSize,
							mStyleIndex,
							mItemWidth,
							mSingleLine
					);
					this.setImageBitmap(mCacheItem.getBitmap());
				}
				else
				{
					mCacheItem.addOnRenderCompleteListener(mRenderListener);
					this.setImageDrawable(null);
				}

				break;
			}

		}
	}



	ValueAnimator mAlphaAnimator = null;

	private void fadeIn()
	{

		mAlphaAnimator = ValueAnimator.ofFloat(0,1);
		mAlphaAnimator.setDuration(250);
		mAlphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
		{
			@Override
			public void onAnimationUpdate(ValueAnimator animation)
			{

				ImageEmojiItem.this.setAlpha( (float)animation.getAnimatedValue() );
			}


		});
		mAlphaAnimator.start();

	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{

		if (mConfiguredItemWidth == AUTO)
		{
			int mode = MeasureSpec.getMode(widthMeasureSpec);
			int width = MeasureSpec.getSize(widthMeasureSpec);

			if ( mode == MeasureSpec.AT_MOST && width != mPrevParentWidth)
			{
				mPrevParentWidth = width;
				mItemWidth = EmojiResources.calculateColCount(this.getContext(), width, 1);

				updateCacheItem();

				if (mAlphaAnimator != null)
					mAlphaAnimator.cancel();
				mAlphaAnimator = null;

				display();
			}

		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public void setEmojiText(String text, float textSize, Object panelKey, int styleId)
	{
		removeRenderListener();

		mTextSize = textSize;
		mStyleIndex = styleId;
		mText = text;
		mPanelKey = panelKey;

		//If width is set to match parent, we want our
		//cached items to have a width up to the
		//width of the parent. Do not do anything until measure.
		if (mConfiguredItemWidth != AUTO)
		{
			updateCacheItem();

			if (mAlphaAnimator != null)
				mAlphaAnimator.cancel();
			mAlphaAnimator = null;

			display();
		}
	}

	@Override
	public String getText()
	{
		//return super.getText().toString();
		return mText;
	}

	@Override
	public View getView()
	{
		return this;
	}

}
