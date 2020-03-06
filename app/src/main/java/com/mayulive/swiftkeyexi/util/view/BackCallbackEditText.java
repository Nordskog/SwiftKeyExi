package com.mayulive.swiftkeyexi.util.view;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 * Created by Roughy on 8/7/2017.
 */

public class BackCallbackEditText extends AppCompatEditText
{

	private OnBackPressed mBackPressedListener = null;

	public BackCallbackEditText(Context context)
	{
		super(context);
	}

	public BackCallbackEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public BackCallbackEditText(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean onKeyPreIme(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK  && event.getAction() == KeyEvent.ACTION_DOWN)
		{
			if (mBackPressedListener != null)
			{
				return mBackPressedListener.onBackPressed();
			}
		}

		return false;
	}

	public void setOnBackPressedListener(OnBackPressed listener)
	{
		mBackPressedListener = listener;
	}

	public interface OnBackPressed
	{
		public boolean onBackPressed();
	}
}
