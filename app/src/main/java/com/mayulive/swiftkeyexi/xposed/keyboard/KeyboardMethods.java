package com.mayulive.swiftkeyexi.xposed.keyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.ViewGroup;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;

import java.io.ByteArrayInputStream;
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
	protected static ArrayList<SharedPreferences.OnSharedPreferenceChangeListener> mSwiftkeyPrefChangedListeners = new ArrayList<>();

	protected static ViewGroup mKeyboardRoot = null;
	protected static float mLastKeyboardOpacity = 1;


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

	public static String getCurrentLayoutName()
	{
		return mCurrentLayoutName;
	}

	public static void addKeyboardEventListener(KeyboardEventListener listener)
	{
		mKeyboardEventListeners.add(listener);
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

	public static void forceKeyboardResize()
	{
		try
		{
			SharedPreferences prefs = SettingsCommons.getSharedPreferences(ContextUtils.getHookContext(), ExiXposed.getPrefsPath());

			//This is the keyboard size pref, numbers 1-6? 0-5? Int in that range
			//To force an update we set it to something else, then return it to what it was.
			//Crude but effective.
			int existingVal = prefs.getInt("docked_full_portrait", 0);
			int tempVal = 1;
			if (existingVal == 1)
				tempVal = 2;

			SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor( ContextUtils.getHookContext(), ExiXposed.getPrefsPath() );
			//Usage of commit is important, as otherwise the prefchanged callback
			//only gets called once for the latter change.
			editor.putInt("docked_full_portrait", tempVal);
			editor.commit();

			editor.putInt("docked_full_portrait", existingVal);
			editor.commit();

			//This honestly probably completely redraws the entire keyboard, and could replace requestKeyboardReload below.
			//The opposite is not true.
		}
		catch ( Exception ex)
		{
			Log.e(LOGTAG, "Failed to force keyboard resize, user can still change the size manually");
		}
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
				PriorityKeyboardClassManager.keyboardLoader_onSharedPreferenceChangedMethod.invoke(mKeyboardLoadObject, null, "pref_arrows_key");
			}
			catch (Exception ex)
			{
				Log.e(LOGTAG, "Failed to request keyboard reload");
				ex.printStackTrace();
			}
		}
		mKeyboardReloadLastRequested = time;
	}


	public static boolean loadPunctuationRules()
	{
		return KeyboardMethods.loadPunctuationRules( Settings.DISABLE_PUNCTUATION_AUTO_SPACE ?
						KeyboardMethods.PunctuationRuleMode.MODIFIED : KeyboardMethods.PunctuationRuleMode.STOCK,
				false );
	}

	public static boolean loadPunctuationRules(PunctuationRuleMode mode, boolean force)
	{
		//Don't bother changing if mode already matches
		if (mode != mActivePunctuationMode || force)
		{

			//PunctuatorImpl instance must be present
			if (PriorityKeyboardClassManager.punctuatorImplInstance != null)
			{

				//Do not update active mode if we were unable to set it
				//In that scenario we will also be calling this method when we set the instance
				mActivePunctuationMode = mode;

				try
				{
					PriorityKeyboardClassManager.punctuatorImplClass_ClearRulesMethod.invoke(PriorityKeyboardClassManager.punctuatorImplInstance);

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

					PriorityKeyboardClassManager.punctuatorImplClass_AddRulesMethod.invoke(PriorityKeyboardClassManager.punctuatorImplInstance, args);

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

	public static void addOnSwiftkeySharedPrefChangedListener(SharedPreferences.OnSharedPreferenceChangeListener listener)
	{
		mSwiftkeyPrefChangedListeners.add(listener);
	}

	public static void removeOnSwiftkeySharedPrefChangedListener(SharedPreferences.OnSharedPreferenceChangeListener listener)
	{
		mSwiftkeyPrefChangedListeners.remove(listener);
	}

	public static SharedPreferences getSwiftkeySharedPrefs()
	{
		Context context = ContextUtils.getHookContext();
		return context.getSharedPreferences(ExiXposed.HOOK_PACKAGE_NAME+"_preferences", Context.MODE_PRIVATE);
	}

	public static void setKeyboardOpacity()
	{
		//Run if changed or opacity not 100%
		if (mLastKeyboardOpacity != Settings.KEYBOARD_OPACITY || Settings.KEYBOARD_OPACITY < 1)
		{
			if (mKeyboardRoot != null)
				mKeyboardRoot.setAlpha( Settings.KEYBOARD_OPACITY );
		}

		mLastKeyboardOpacity = Settings.KEYBOARD_OPACITY;

	}
}
