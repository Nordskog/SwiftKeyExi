package com.mayulive.swiftkeyexi.main.swipe.quickmenu;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.Theme;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.util.KeyboardUtil;
import com.mayulive.swiftkeyexi.util.view.BackCallbackEditText;

/**
 * Created by Roughy on 9/29/2017.
 */

public class QuickMenuActivity extends AppCompatActivity implements Theme.ThemeApplicable
{
	public static int mAppliedTheme = R.style.AppTheme;
	BackCallbackEditText mTestInput = null;
	FrameLayout mTestInputContainer = null;

	FrameLayout mFragContainer = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Theme.setTheme(this, Theme.getThemeResId(this, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY));
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_quickmenu_config);

		mFragContainer = (FrameLayout)this.findViewById(R.id.quick_menu_config_fragment_container);

		setupKeyboard();
	}

	@Override
	public int getAppliedTheme()
	{
		return mAppliedTheme;
	}

	@Override
	public void setAppliedTheme(int themeResId)
	{
		mAppliedTheme = themeResId;
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		hideInputOnBack();
	}

	public void displayInputTest()
	{
		mTestInputContainer.setVisibility(View.VISIBLE);
		mTestInput.requestFocus();
		KeyboardUtil.showKeyboard(mTestInput);
	}

	public void hideInputTest()
	{
		mTestInputContainer.setVisibility(View.GONE);
		KeyboardUtil.hideKeyboard(mTestInput);
	}

	public void hideInputOnBack()
	{
		//For when we override backup. Keyboard will hide itself.
		if (mTestInputContainer.getVisibility() == View.VISIBLE)
		{
			mTestInputContainer.setVisibility(View.GONE);
		}
	}

	private void setupKeyboard()
	{
		mTestInput = (BackCallbackEditText) findViewById(R.id.test_keyboard);
		mTestInputContainer = (FrameLayout) findViewById(R.id.test_keyboard_container);

		mTestInput.setOnBackPressedListener(new BackCallbackEditText.OnBackPressed()
		{
			@Override
			public boolean onBackPressed()
			{
				hideInputOnBack();
				return false;
			}
		});

		mTestInputContainer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				hideInputTest();
			}
		});
	}

	@Override
	public Context getContext()
	{
		return this;
	}
}
