package com.mayulive.swiftkeyexi.main.emoji;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.mayulive.swiftkeyexi.main.commons.PopupLinearLayout;
import com.mayulive.swiftkeyexi.util.VersionTools;
import com.mayulive.swiftkeyexi.R;

/**
 * Created by Roughy on 12/10/2016.
 */

public class EmojiColumnWidthConfigView
{
	PopupLinearLayout mLayout = null;
	NumberPicker mPicker = null;

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	EmojiColumnWidthConfigView(Context context,int startValue, int min, int max)
	{

		context.getApplicationContext().setTheme(R.style.AppTheme);

		mLayout = new PopupLinearLayout(context);
		View pickerRoot =  ( (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ).inflate(R.layout.emoji_column_width_config_dialog_layout,mLayout.getLayoutView()) ;


		mLayout.getLayoutView().setBackground(null);
		if (VersionTools.isLollipopOrGreater())
		{
			pickerRoot.setElevation( context.getResources().getDimension(R.dimen.popup_window_elevation) );
		}

		mPicker = (NumberPicker) pickerRoot.findViewById(R.id.numberpicker);

		mPicker.setMinValue(min);
		mPicker.setMaxValue(max);
		mPicker.setValue(startValue);
		//mLayout.addItem(pickerRoot);
	}

	public PopupLinearLayout getPopup()
	{
		return mLayout;
	}

	public NumberPicker getNumberPicker()
	{
		return mPicker;
	}

	public void showAbove(View anchor)
	{
		mLayout.showAbove(anchor);
	}

	public void showBelow(View anchor)
	{
		mLayout.showBelow(anchor);
	}

}
