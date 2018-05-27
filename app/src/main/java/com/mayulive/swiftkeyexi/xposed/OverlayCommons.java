package com.mayulive.swiftkeyexi.xposed;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.swiftkeyexi.main.keyboard.HotkeyPanel;
import com.mayulive.swiftkeyexi.main.keyboard.KeyboardOverlay;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.util.view.ViewTools;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.style.StyleCommons;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

	//Due to nonsense we often have two overlay views, once of which does nothing.
	//Easier to keep track of multiple here than to try and fix that.
	private static ArrayList<TextView> mLoadingWarnings = new ArrayList<>();


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


	//Positions relative to input view
	public static void displayKeyAbovePosition(KeyDefinition key, String text, View inView)
	{

		//Compensate for floating keyboard
		int[] inWindow = ViewTools.getPositionInWindow(inView);

		int verticalOffset = (CodeUtils.getTopParent(inView).getMeasuredHeight()) - (inWindow[1] + inView.getMeasuredHeight());
		int horizontalOffset = inWindow[0];

		displayKeyAbovePosition(key, text, inView.getMeasuredWidth(), inView.getMeasuredHeight(), horizontalOffset, verticalOffset);

	}

	//Full-screen coordinates
	public static void displayKeyAbovePosition(KeyDefinition key, String text, int keyboardWidth, int keyboardHeight, int horizontalOffset, int verticalOffset)
	{

		mLastKeyDisplayed = key.content;

		if (mKeyboardOverlay == null)
		{
			Log.e(LOGTAG, "Strange, overlay was null");

			return;
		}

		/////////////////////////////////////////////
		//Calculate where we want the key to appear
		/////////////////////////////////////////////

		//Update key size
		{

			float keyY = (key.hitbox.bottom - key.hitbox.top) * keyboardHeight;
			float keyX = (key.hitbox.bottom - key.hitbox.top) * keyboardWidth;

			int paddingX = (int) (keyX * 0.25);
			int paddingY = (int) (keyY * 0.25);
			float textSize = (float) (keyY * 0.7);

			OverlayCommons.setPopupDimensions(textSize, paddingX, paddingY);
		}

		float xCenter = keyboardWidth * key.hitbox.centerX();
		xCenter += horizontalOffset;

		int yPos = (int)(keyboardHeight * key.hitbox.top);
		yPos = keyboardHeight - yPos;    //From bottom

		////////////////////////////////
		//Decide on the final position
		////////////////////////////////
		TextView button = getPopup();

		button.setText(text);

		//Get the size of the view so we know where to place it
		button.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

		int keyWidth = button.getMeasuredWidth();
		int keyHeight = button.getMeasuredHeight();

		//Get the x position we want the left side of the view to be at
		float xPos = xCenter - (keyWidth / 2);
		if (xPos < 0)
			xPos = 0;

		//Don't try to consider width if size has not been set
		if (mKeyboardOverlay.getMeasuredWidth() > 0)
		{
			//Check if we are off-screen on the right
			float rightPos = xPos + keyWidth;
			if ( rightPos >= mKeyboardOverlay.getMeasuredWidth() )
			{
				//Offset left position accordingly
				xPos -=( rightPos - mKeyboardOverlay.getMeasuredWidth()) ;
			}

		}

		//////////////////
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)button.getLayoutParams();
		params.leftMargin = (int)xPos;
		params.topMargin = mKeyboardOverlay.getMeasuredHeight() - yPos - verticalOffset - keyHeight;

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

	//In view
	public static void displayHotkeyMenu(float spacebarMargin, int width, int height, float xCenter, List<? extends HotkeyPanel.HotkeyMenuItem> items,  View inView)
	{
		int[] inWindow = ViewTools.getPositionInWindow(inView);
		int verticalOffset = (CodeUtils.getTopParent(inView).getMeasuredHeight()) - (inWindow[1] + inView.getMeasuredHeight());
		int horizontalOffset = inWindow[0];

		displayHotkeyMenu(spacebarMargin, width, height, xCenter, items, horizontalOffset, verticalOffset);
	}

	public static void displayLoadingMessage()
	{
		if (mKeyboardOverlay == null)
		{
			Log.e(LOGTAG, "Strange, overlay was null");
			return;
		}

		TextView loadingMessage = new TextView(ContextUtils.getHookContext() );


		int width = ViewGroup.LayoutParams.MATCH_PARENT;
		//Usually enough to cover the keyboard
		int height = ViewTools.getScrenSize()[1];
		int margin = (int)(height * 0.4f);
		height = (int)(height * 0.6f);

		loadingMessage.setTextSize( 30 );
		loadingMessage.setBackgroundColor(Color.BLACK );
		loadingMessage.setAlpha(0.75f);
		loadingMessage.setGravity(Gravity.CENTER);
		loadingMessage.setTextColor(Color.WHITE);
		loadingMessage.setClickable(true);


		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( width, height);
		params.topMargin = margin;

		//Not going to risk trying to talk to content from here
		String percentage = String.valueOf( ExiXposed.getLoadingProgress() );
		loadingMessage.setText( "Exi loading\n"+percentage+"%");
		loadingMessage.setLayoutParams(params);

		mLoadingWarnings.add(loadingMessage);
		mKeyboardOverlay.addView(loadingMessage);
	}

	public static void updateLoadingMessage(int progressPercentage)
	{
		String percentage = String.valueOf(progressPercentage);

		for (TextView warning : mLoadingWarnings)
		{
			warning.setText( "Exi loading\n"+percentage+"%");
		}
	}

	public static void removeLoadingMessage()
	{
		for (TextView warning : mLoadingWarnings)
		{
			ViewGroup parent = (ViewGroup)warning.getParent();
			if (parent != null)
				parent.removeAllViews();
		}
	}

	//Full-screen coordinates
	public static void displayHotkeyMenu(float spacebarMargin, int width, int height, float xCenter, List<? extends HotkeyPanel.HotkeyMenuItem> items, int horizontalOffset, int verticalOffset)
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
			params.bottomMargin = verticalOffset;
			params.leftMargin = horizontalOffset;
			params.width = width;
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);


			mHotkeyMenuPanel.setLayoutParams(params);
		}


		mHotkeyMenuPanel.setHighlightColor(Settings.QUICK_MENU_HIGHLIGHT_COLOR);
		mHotkeyMenuPanel.setItems(items);

		float centerRatio = mKeyboardOverlay.getMeasuredWidth() != 0 ? xCenter /  width : 0.5f;


		mHotkeyMenuPanel.setHorizontalCenterRadio( centerRatio );
		//mHotkeyMenuPanel.setTargetRadius( maxRadius_Y);
		mHotkeyMenuPanel.setBottomMargin(spacebarMargin);
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
