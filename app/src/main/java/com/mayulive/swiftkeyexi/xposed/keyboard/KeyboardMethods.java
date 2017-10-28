package com.mayulive.swiftkeyexi.xposed.keyboard;

import android.content.Context;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.providers.SharedPreferencesProvider;
import com.mayulive.swiftkeyexi.settings.Settings;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class KeyboardMethods
{
	public enum PunctuationRuleMode
	{
		STOCK, MODIFIED
	}

	private static String LOGTAG = ExiModule.getLogTag(KeyboardMethods.class);

	protected static Object mKeyboardLoadObject = null;
	protected static String mCurrentLayoutName = null;

	protected static boolean mLayoutIsWeird = false;
	protected static boolean mLayoutIsExtendedPredictions = false;	//Assume false if we have a hook failure or something
	protected static boolean mIsSymbols = false;

	protected static PunctuationRuleMode mActivePunctuationMode = PunctuationRuleMode.STOCK;

	protected static ArrayList<KeyboardEventListener> mKeyboardEventListeners = new ArrayList<>();
	protected static ArrayList<ThemeChangedListener> mThemeChangedListeners = new ArrayList<>();

	protected static int mTheme = -1;

	public static int getTheme()
	{
		return mTheme;
	}

	static protected Set<String> mExtendedPredictionsLayouts = new HashSet<>();
	static
	{
		String[] extendedPredictionLayouts = new String[]
				{
						"PINYIN_CN",
						"PINYIN12",
						"PINYIN_TW",
						"CANTONES2",
						"CANTONESE12",
						"FIVESTROKE_CN",
						"FIVESTROKE_HK",
						"FIVESTROKE_TW",
						"ZHUYIN",
						"ZHUYIN12",
						"CANGJIE",
						"QCANGJIE",
						"ROMAJI"
				};

		//There a bit of code that defines the prediction model for each layout.
		//I'm fairly sure this covers all with extended predictions.
		//Note that these do not necessarily overlap with weird layouts.
		for (String layout : extendedPredictionLayouts)
		{
			mExtendedPredictionsLayouts.add( layout );
		}
	}

	public static void loadSettings(Context context)
	{
		Settings.loadSettings( SharedPreferencesProvider.getSharedPreferences(context) );
	}

	public static String getCurrentLayoutName()
	{
		return mCurrentLayoutName;
	}

	public static void addThemeChangedListener(ThemeChangedListener listener)
	{
		mThemeChangedListeners.add(listener);
	}

	public static void addKeyboardEventListener(KeyboardEventListener listener)
	{
		mKeyboardEventListeners.add(listener);
	}

	protected static void callThemeChangedListeners(int theme)
	{
		for (ThemeChangedListener listener : mThemeChangedListeners)
		{
			if (listener != null)
				listener.themeChanged(theme);
		}
	}

	public static void removeKeyboardEventListener(KeyboardEventListener listener)
	{
		mKeyboardEventListeners.remove(listener);
	}

	public interface KeyboardEventListener
	{
		void beforeKeyboardOpened();
		void beforeKeyboardClosed();
		void keyboardInvalidated();
		void afterKeyboardConfigurationChanged();
	}

	public interface ThemeChangedListener
	{
		void themeChanged(int newTheme);
	}

	private static long mKeyboardReloadLastRequested = -1;

	protected static boolean isLayoutExtendedPredictions(String layoutName)
	{
		return mExtendedPredictionsLayouts.contains(layoutName);
	}

	public static boolean isLayoutExtendedPredictions()
	{
		return mLayoutIsExtendedPredictions;
	}

	public static boolean isLayoutWeird()
	{
		return mLayoutIsWeird;
	}


	public static boolean isLayoutSymbols()
	{
		return mIsSymbols;
	}


	public static void requestKeyboardReload()
	{

		long time = System.currentTimeMillis();

		//Enforce minimum delay of 2 sec to avoid infinite loops
		if (mKeyboardLoadObject != null && time - mKeyboardReloadLastRequested > 2000)
		{
			try
			{
				//Takes a shared pref object, but doesn't use it.
				//The keyboard will only reload if it cares about the preference. arrow keys pref is as good as any.
				KeyboardClassManager.keyboardLoader_onSharedPreferenceChangedMethod.invoke(mKeyboardLoadObject, null, "pref_arrows_key");
			}
			catch (Exception ex)
			{
				Log.e(LOGTAG, "Failed to request keyboard reload");
				ex.printStackTrace();
			}
		}
		mKeyboardReloadLastRequested = time;
	}

	public static boolean loadPunctuationRules(PunctuationRuleMode mode, boolean force)
	{
		//Don't bother changing if mode already matches
		if (mode != mActivePunctuationMode || force)
		{
			mActivePunctuationMode = mode;

			//PunctuatorImpl instance must be present
			if (KeyboardClassManager.punctuatorImplInstance != null)
			{
				try
				{
					KeyboardClassManager.punctuatorImplClass_ClearRulesMethod.invoke(KeyboardClassManager.punctuatorImplInstance);

					Object[] args = new Object[1];

					switch(mActivePunctuationMode)
					{
						case STOCK:
						{
							args[0] = new ByteArrayInputStream( KeyboardStrings.PUNCTUATION_STOCK_RULES.getBytes(StandardCharsets.UTF_8.name()));
							break;
						}
						case MODIFIED:
						{
							args[0] = new ByteArrayInputStream( KeyboardStrings.PUNCTUATION_MODIFIED_RULES.getBytes(StandardCharsets.UTF_8.name()));
							break;
						}
					}

					KeyboardClassManager.punctuatorImplClass_AddRulesMethod.invoke(KeyboardClassManager.punctuatorImplInstance, args);

				}
				catch (Throwable ex)
				{
					Log.i(LOGTAG, "Something went wrong updating punctuation rules.");
					ex.printStackTrace();
					return false;
				}
			}
		}

		return true;
	}


}
