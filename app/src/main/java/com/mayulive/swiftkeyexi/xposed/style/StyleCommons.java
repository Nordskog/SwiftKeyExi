package com.mayulive.swiftkeyexi.xposed.style;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextPaint;
import android.util.Log;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.ExiModule;

/**
 * Created by Roughy on 2/23/2017.
 */

public class StyleCommons
{
	private static String LOGTAG = ExiModule.getLogTag(StyleCommons.class);

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



}
