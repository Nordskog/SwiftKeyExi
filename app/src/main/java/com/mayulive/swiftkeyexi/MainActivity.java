package com.mayulive.swiftkeyexi;

import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiCache;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.main.emoji.data.FancyEmojiPanelTemplates;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.EmojiCache.NormalEmojiItem;
import com.mayulive.swiftkeyexi.main.Theme;
import com.mayulive.swiftkeyexi.main.main.MainViewPagerAdapter;
import com.mayulive.swiftkeyexi.main.settings.SettingsActivity;
import com.mayulive.swiftkeyexi.util.FontLoader;
import com.mayulive.swiftkeyexi.util.KeyboardUtil;
import com.mayulive.swiftkeyexi.util.view.BackCallbackEditText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;


import static com.mayulive.swiftkeyexi.util.FontLoader.getFontPathArray;


public class MainActivity extends AppCompatActivity implements Theme.ThemeApplicable
{

	private static String LOGTAG = ExiModule.getLogTag(MainActivity.class);

	WrappedDatabase mDbWrap = null;
	MainViewPagerAdapter mAdapter;
	ViewPager mPager;

	BackCallbackEditText mTestInput = null;
	FrameLayout mTestInputContainer = null;

	public static int mAppliedTheme = R.style.AppTheme;

	private void openDatabase()
	{
		mDbWrap = DatabaseHolder.getWrapped(this);
	}

	//
	private void handleEmojiUpdate()
	{

		//Check what version of emojis we are using, currently have Marshmallow and Nougat.
		SharedPreferences prefs = SettingsCommons.getSharedPreferences(this, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);
		int emojiVersion = prefs.getInt(PreferenceConstants.status_api_version_emoji,  Build.VERSION_CODES.M );

		if (ExiModule.needsEmojiUpdate(emojiVersion))
		{
			//Update emoji
			int newVersion = ExiModule.update(this,mDbWrap);

			//Update emoji version pref
			SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);
			editor.putInt(PreferenceConstants.status_api_version_emoji, newVersion);
			editor.apply();

		}
	}

	private void handleFirstLaunch()
	{
		SharedPreferences prefs = SettingsCommons.getSharedPreferences(this, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);

		boolean isFirstLaunch = prefs.getBoolean(PreferenceConstants.status_first_launch_key, true);

		int lastApiVersion = prefs.getInt(PreferenceConstants.status_api_version_last_launch, Build.VERSION.SDK_INT);


		if (isFirstLaunch)
		{


			//Proper emoji will be populated anyway if first launch
			editor.putInt(PreferenceConstants.status_api_version_emoji, ExiModule.getEmojiVersionForSDK());
			editor.apply();

			if (mDbWrap != null)
			{
				Log.i(LOGTAG, "Loading default values");
				ExiModule.initialize(this.getApplicationContext(),mDbWrap);

				//Let hook-side know that data has changed
				long currentTime = System.currentTimeMillis();
				editor.putLong(PreferenceConstants.pref_emoji_last_update_key, currentTime);
				editor.putLong(PreferenceConstants.pref_dictionary_last_update_key, currentTime);
				editor.putLong(PreferenceConstants.pref_hotkeys_last_update_key, currentTime);
				editor.putLong(PreferenceConstants.pref_popup_last_update_key, currentTime);
				editor.putLong(PreferenceConstants.pref_additional_keys_last_update_key, currentTime);

				editor.apply();
			}
			else
			{
				Log.e(LOGTAG, "Tried to perform first-time setup, but the database was not initialized");
			}


			editor.putBoolean(PreferenceConstants.status_first_launch_key, false);
			editor.apply();
		}

		if (lastApiVersion != Build.VERSION.SDK_INT)
		{
			//Emoji that were previously not renderable may now be so, and vice versa. TODO Carefully reinit stock panels here.
			//TODO Should probably allow them to be added to panels and just marked as do-not-display instead.
			editor.putInt(PreferenceConstants.status_api_version_last_launch, Build.VERSION.SDK_INT);
			editor.apply();
		}
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

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{


		Theme.setTheme(this, Theme.getThemeResId(this, SettingsCommons.MODULE_SHARED_PREFERENCES_KEY) );

		//Database!
		openDatabase();

		////////////////////
		//Init font loader
		////////////////////
		//EmojiResources.loadResources(this);

		Typeface simpleFont = Typeface.createFromAsset(this.getAssets(), "fonts/NotoEmoji_der_nougat.ttf");
		FontLoader.initFontLoader(getFontPathArray());
		NormalEmojiItem.loadAssets(simpleFont);

		//Load defaults on first launch
		handleFirstLaunch();
		handleEmojiUpdate();

		//Child fragments are re-instanced inside of super,
		//so any data they depend on, such as the database,
		//must be prepared first.
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mAdapter = new MainViewPagerAdapter(this, getSupportFragmentManager());
		mPager = (ViewPager)findViewById(R.id.mainViewPager);
		mPager.setAdapter(mAdapter);

		TabLayout titleIndicator = (TabLayout)findViewById(R.id.mainViewTitles);
		titleIndicator.setupWithViewPager(mPager);

		setupKeyboard();

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			launchSettings();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void launchSettings()
	{
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onPause()
	{

		super.onPause();
		//Log.e("###", "Activity paused");

	}

	@Override
	protected void onResume()
	{
		super.onResume();
		hideInputOnBack();
		if (Theme.applyThemeIfNecessary(this))
		{
			//Some of our cached emoji will be a mix of
			//text and emoji, and the color from before the
			//theme change will stick. We currently can't tell them
			//apart from the rest though.

			//Clear entire cache for now, people won't be changing themes often anyway.
			EmojiCache.clearCache();

			//On Swiftkey's side, the theme changes for different keyboard ... themes.
			//Some are given a light material theme, other a dark one.
			//Lucky for us, they both use the same text color.
		}
		//Log.e("###", "Activity resumed");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		//Log.e("###", "Activity destroyed");
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
}
