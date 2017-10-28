package com.mayulive.swiftkeyexi.xposed;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.keyboard.HotkeyPanel;
import com.mayulive.swiftkeyexi.main.keyboard.KeyboardOverlay;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.style.StyleCommons;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Roughy on 2/22/2017.
 */

public class OverlayCommons
{
	private static String LOGTAG = ExiModule.getLogTag(OverlayCommons.class);

	public static RelativeLayout mKeyboardOverlay = null;



	public static String mLastKeyDisplayed = null;

	private static ArrayList<TextView> mPopupViews = new ArrayList<>();
	private static ArrayList<TextView> mCheckedPopupViews = new ArrayList<>();

	private static float mPopupTextSize = 0;
	private static int mPopupPaddingX = 0;
	private static int mPopupPaddingY = 0;

	private static HotkeyPanel mHotkeyMenuPanel = null;

	private static KeyboardOverlay mDebugOverlay = null;

	public static void setPopupDimensions(float textSize, int paddingX, int paddingY)
	{
		mPopupTextSize = textSize;
		mPopupPaddingX = paddingX;
		mPopupPaddingY = paddingY;
	}


	public static void clearPopupviewCache()
	{
		mPopupViews.clear();
	}


	private static TextView getPopup()
	{
		if (mPopupViews.isEmpty())
		{
			if (mKeyboardOverlay != null)
			{
				TextView button = StyleCommons.getPopupTextView(mKeyboardOverlay.getContext());

				button.setIncludeFontPadding(false);
				button.setPadding(mPopupPaddingX,mPopupPaddingY,mPopupPaddingX,mPopupPaddingY);

				button.setGravity(Gravity.CENTER);
				button.setTextSize( TypedValue.COMPLEX_UNIT_PX, mPopupTextSize);
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				button.setLayoutParams(params);

				mCheckedPopupViews.add(button);
				return button;
			}
			else
			{
				Log.e(LOGTAG, "Strange, overlay was null");
			}

		}

		TextView button = mPopupViews.remove(mPopupViews.size()-1);
		mCheckedPopupViews.add(button);
		return button;
	}

	private static void returnPopup(TextView view)
	{
		view.setText("");
		view.measure(0,0);
		view.layout(0,0,0,0);

		mCheckedPopupViews.remove(view);
		mPopupViews.add(view);
	}


	//Set a key manually to avoid extra calls
	public static void setPopupKeyManually(String key)
	{
		mLastKeyDisplayed = key;
	}

	public static boolean isDisplayed(String key)
	{
		return mLastKeyDisplayed != null && mLastKeyDisplayed.equals(key);
	}

	public static boolean isHotkeyMenuDisplayed()
	{
		return mHotkeyMenuPanel != null;
	}


	//Full-screen coordinates
	public static void displayKeyAbove(String key, String text, float fromBottom, float xCenter)
	{

		mLastKeyDisplayed = key;

		if (mKeyboardOverlay == null)
		{
			Log.e(LOGTAG, "Strange, overlay was null");

			return;
		}

		TextView button = getPopup();


		button.setText(text);

		//Get the size of the view so we know where to place it
		button.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

		int viewWidth = button.getMeasuredWidth();
		int viewHeight = button.getMeasuredHeight();

		//Get the x position we want the left side of the view to be at
		float xPos = xCenter - (viewWidth / 2);
		if (xPos < 0)
			xPos = 0;

		//Don't try to consider width if size has not been set
		if (mKeyboardOverlay.getMeasuredWidth() > 0)
		{
			//Check if we are off-screen on the right
			float rightPos = xPos + viewWidth;
			if ( rightPos >= mKeyboardOverlay.getMeasuredWidth() )
			{
				//Offset left position accordingly
				xPos -=( rightPos - mKeyboardOverlay.getMeasuredWidth()) ;
			}

		}

		//////////////////
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)button.getLayoutParams();
		params.leftMargin = (int)xPos;
		params.topMargin = (int) mKeyboardOverlay.getMeasuredHeight() - (int) fromBottom - viewHeight;

		button.setLayoutParams(params);

		mKeyboardOverlay.addView(button);
	}


	public static void clearPopups()
	{
		if (mKeyboardOverlay != null)
		{
			ListIterator<TextView> iterator = mCheckedPopupViews.listIterator();
			while(iterator.hasNext())
			{
				TextView view = iterator.next();
				returnPopup(view);
				mKeyboardOverlay.removeView(view);
			}

			mKeyboardOverlay.removeAllViews();
		}
		else
			Log.e(LOGTAG, "Strange, overlay was null");


		mHotkeyMenuPanel = null;
		mLastKeyDisplayed = null;


	}




	public static void handleDisplayHotkeyMenuTouch(float x, float y, float viewHeight)
	{

		if (mHotkeyMenuPanel != null)
		{
			float heightOffset = mHotkeyMenuPanel.getMeasuredHeight() - viewHeight;
			mHotkeyMenuPanel.handleTouch(x, y + heightOffset);
		}
	}


	//Full-screen coordinates
	public static void displayHotkeyMenu(float fromBottom, int height, float xCenter, List<? extends HotkeyPanel.HotkeyMenuItem> items)
	{
		if (mKeyboardOverlay == null)
		{
			Log.e(LOGTAG, "Strange, overlay was null");
			return;
		}


		//if (mHotkeyMenuPanel == null)
		{
			mHotkeyMenuPanel = new HotkeyPanel( mKeyboardOverlay.getContext(), Settings.QUICK_MENU_HIGHLIGHT_COLOR );
			RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			mHotkeyMenuPanel.setLayoutParams(params);
		}


		mHotkeyMenuPanel.setHighlightColor(Settings.QUICK_MENU_HIGHLIGHT_COLOR);
		mHotkeyMenuPanel.setItems(items);

		float centerRatio = mKeyboardOverlay.getMeasuredWidth() != 0 ? xCenter /  mKeyboardOverlay.getMeasuredWidth() : 0.5f;

		mHotkeyMenuPanel.setHorizontalCenterRadio( centerRatio );
		//mHotkeyMenuPanel.setTargetRadius( maxRadius_Y);
		mHotkeyMenuPanel.setBottomMargin(fromBottom);
		mHotkeyMenuPanel.setCoverTop(0);
		mHotkeyMenuPanel.setTextSizeRatio(Settings.QUICKMENU_TEXT_SIZE_RATIO);

		///////////////////

		mKeyboardOverlay.addView(mHotkeyMenuPanel);
	}

	public static HotkeyPanel getHotkeyPanel()
	{
		return mHotkeyMenuPanel;
	}

	public static void clearDebugOverlay()
	{
		if (mKeyboardOverlay != null)
		{
			if (mDebugOverlay != null)
			{
				mKeyboardOverlay.removeView(mDebugOverlay);
				mDebugOverlay = null;
			}
		}
	}

	public static void displayDebugHithoxes(Context context,int height)
	{
		clearDebugOverlay();
		if (mKeyboardOverlay == null)
		{
			Log.e(LOGTAG, "Strange, overlay was null");
			return;
		}


		mDebugOverlay = new KeyboardOverlay(context);
		mDebugOverlay.setDisplayKeys( KeyCommons.getHitboxMap().values() );

		RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mDebugOverlay.setLayoutParams(params);

		mKeyboardOverlay.addView(mDebugOverlay);

	}


}
