package com.mayulive.swiftkeyexi.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Roughy on 5/19/2017.
 */

public class KeyboardUtil
{
	public static void hideKeyboard(View view)
	{
		InputMethodManager inputMethodManager = (InputMethodManager)view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
}
