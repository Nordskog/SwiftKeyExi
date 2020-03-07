package com.mayulive.swiftkeyexi.EmojiCache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import com.mayulive.swiftkeyexi.SharedTheme;

/**
 * Created by Roughy on 12/3/2016.
 */

public class NormalEmojiItem extends AppCompatTextView implements EmojiContainer
{
	boolean mMarked = false;
	float mTextSize = 14;

	private static boolean mAssetsLoaded = false;
	private static Typeface mSimpleEmojiFont = null;

	boolean mModifiable = false;

	private static Bitmap mDiverseIndicator = Bitmap.createBitmap(10, 27, Bitmap.Config.RGB_565);
	private static int mDiverseIndicatorPadding = 15;

	private static Paint mPaint = new Paint();
	static
	{
		mPaint.setColor(Color.LTGRAY);
		mPaint.setAlpha(75);
		mPaint.setStyle(Paint.Style.FILL);
	}

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

	//For drawing diverse emoji indictaor
	public static void setThemeType(Context context, int themeType)
	{
		if (themeType == SharedTheme.DARK_THEME_IDENTIFIER)
			mPaint.setColor(ImageEmojiItem.DIVERSE_INDICATOR_DARK);
		else
			mPaint.setColor(ImageEmojiItem.DIVERSE_INDICATOR_LIGHT);

		updateDiverseIndicator(context);
	}

	protected static void updateDiverseIndicator(Context context)
	{
		EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(context);

		int indicatorSize = (int)(dimens.configured_singleEmojiWidth*0.15f);
		mDiverseIndicator = Bitmap.createBitmap(indicatorSize, indicatorSize, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(mDiverseIndicator);


		float[] positions = new float[6];

		positions[0] = indicatorSize;	//Right edge
		positions[1] = 0;	//Upper limit

		positions[2] = indicatorSize;	//Corner
		positions[3] = indicatorSize;				//Corner

		positions[4] = 0;	//Bottom left corner
		positions[5] = indicatorSize;

		Path path = new Path();
		path.moveTo(positions[0], positions[1]);
		path.lineTo(positions[2], positions[3]);
		path.lineTo(positions[4], positions[5]);
		path.close();

		canvas.drawPath(path,mPaint);

		//Padding based on original size because otherwise things look weird.
		mDiverseIndicatorPadding = (int)((dimens.default_singleEmojiWidth*0.15f) * 2);

	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		if (mModifiable)
		{

			//Basically just eyeballing this
			canvas.drawBitmap(
					mDiverseIndicator,
					//(this.getMeasuredWidth() / 2) - mDiverseIndicator.getHeight(),
					(this.getMeasuredWidth() / 2) - (mDiverseIndicatorPadding ) + (this.getMeasuredHeight() / 2),
					this.getMeasuredHeight() - (mDiverseIndicatorPadding),
					null
			);
		}
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
		mModifiable = modifiable;
	}
}
