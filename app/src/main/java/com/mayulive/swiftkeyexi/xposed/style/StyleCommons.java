package com.mayulive.swiftkeyexi.xposed.style;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;

import java.util.ArrayList;

/**
 * Created by Roughy on 2/23/2017.
 */

public class StyleCommons
{
	private static String LOGTAG = ExiModule.getLogTag(StyleCommons.class);

	protected static ArrayList<ThemeChangedListener> mThemeChangedListeners = new ArrayList<>();

	public static final String TOOLBAR_LIGHT_SEARCH_TEXT_STRING = "light_gif_search_text";
	public static final String TOOLBAR_DARK_SEARCH_TEXT_STRING = "dark_gif_search_text";

	public static int TOOLBAR_LIGHT_SEARCH_TEXT_COLOR_RESOURCE = -1;
	public static int TOOLBAR_DARK_SEARCH_TEXT_COLOR_RESOURCE = -1;

	protected static int mTheme = -1;

	public static int getCurrentTheme()
	{
		return mTheme;
	}

	public static boolean toolbarColorResourceSet = false;

	protected static void setToolbarColorResources( Context context )
	{
		TOOLBAR_LIGHT_SEARCH_TEXT_COLOR_RESOURCE = context.getResources().getIdentifier(TOOLBAR_LIGHT_SEARCH_TEXT_STRING, "color", ExiXposed.HOOK_PACKAGE_NAME);
		TOOLBAR_DARK_SEARCH_TEXT_COLOR_RESOURCE = context.getResources().getIdentifier(TOOLBAR_DARK_SEARCH_TEXT_STRING, "color", ExiXposed.HOOK_PACKAGE_NAME);

		//Nowhere convenient to intercept the identifier, and we don't want to compare a bunch of strings.
		//Actual color value it is.
		if  (Build.VERSION.SDK_INT >= 23)
		{
			TOOLBAR_LIGHT_SEARCH_TEXT_COLOR_RESOURCE = context.getResources().getColor( TOOLBAR_LIGHT_SEARCH_TEXT_COLOR_RESOURCE, null );
			TOOLBAR_DARK_SEARCH_TEXT_COLOR_RESOURCE = context.getResources().getColor( TOOLBAR_DARK_SEARCH_TEXT_COLOR_RESOURCE, null );
		}
		else
		{
			TOOLBAR_LIGHT_SEARCH_TEXT_COLOR_RESOURCE = context.getResources().getColor( TOOLBAR_LIGHT_SEARCH_TEXT_COLOR_RESOURCE );
			TOOLBAR_DARK_SEARCH_TEXT_COLOR_RESOURCE = context.getResources().getColor( TOOLBAR_DARK_SEARCH_TEXT_COLOR_RESOURCE );
		}

		toolbarColorResourceSet = true;
	}

	//Fallback background
	private static Drawable getGenericBackground()
	{
		//int strokeWidth = 5; // 3px not dp
		int roundRadius = 15; // 8px not dp
		//int strokeColor = Color.parseColor("#000000");
		int fillColor = Color.parseColor("#9F000000");

		GradientDrawable gd = new GradientDrawable();
		gd.setColor(fillColor);
		gd.setCornerRadius(roundRadius);
		gd.setStroke(0, 0);

		//Inset so we are always hidden behind any existing backgrounds
		//Inset... doesn't acutally seem to inset here. Works fine in test view though. ah well.
		//InsetDrawable inset = new InsetDrawable(gd,15);

		return gd;
	}

	public static TextView getPopupTextView(Context context)
	{
		TextView textView = new TextView(context);

		textView.setBackground( getGenericBackground() );
		textView.setTextColor(Color.WHITE);

		return textView;
	}

	public static void addThemeChangedListener(ThemeChangedListener listener)
	{
		mThemeChangedListeners.add(listener);
	}

	protected static void callThemeChangedListeners(int theme)
	{
		for (ThemeChangedListener listener : mThemeChangedListeners)
		{
			if (listener != null)
				listener.themeChanged(theme);
		}
	}

	public interface ThemeChangedListener
	{
		void themeChanged(int newTheme);
	}
}
