package com.mayulive.swiftkeyexi.xposed.keyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.util.DimenUtils;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;

import java.io.ByteArrayInputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;


public class KeyboardMethods
{

	private static final String AUTOCOMPLETE_PREF_KEY = "pref_typing_style_autocomplete";
	public static Map<Integer, WeakReference<View>> mExpandButtons = new HashMap<>();
	public static int mExpandButtonOriginalWidth = 0;	// Set when added to above list


	public static void inputText(String text)
	{
		inputText(text, null);
	}

	public static void inputText(String text, @Nullable InputConnection currentConnection )
	{
		if (PriorityKeyboardClassManager.keyboardServiceInstance != null)
		{
			try
			{
				if (currentConnection == null)
				{
					currentConnection = (InputConnection) PriorityKeyboardClassManager.keyboardService_getCurrentInputConnectionMethod.invoke( PriorityKeyboardClassManager.keyboardServiceInstance );
				}

				if (currentConnection != null)
				{
					//If the cursor is after a letter or digit, swiftkey will insist on
					//putting us into composing mode. Committing text in this state will replace
					//the composing text. Finish composing to prevent. Might confuse swiftkey state?
					currentConnection.finishComposingText();
					currentConnection.commitText(text,1);
				}
			}
			catch (IllegalAccessException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static boolean getToolbarOpen()
	{
		for (WeakReference<View> reference : mExpandButtons.values())
		{
			View button = reference.get();
			if (button != null)
			{
				// State value [2], 0 if closed.
				int[] state = button.getDrawableState();
				if (state.length < 3)	// Isn't populated initially?
					return false;
				else
					return button.getDrawableState()[2] != 0;
			}
		}

		return false;
	}

	public static void handleExpandButton()
	{
		for (WeakReference<View> reference : mExpandButtons.values())
		{
			View button = reference.get();
			if (button != null)
			{
				ViewGroup.LayoutParams params = button.getLayoutParams();

				if (mExpandButtonOriginalWidth == 0)
				{
					mExpandButtonOriginalWidth = params.width;
				}

				if ( Settings.REMOVE_SUGGESTIONS_PADDING)
				{
					params.width = 0;
					params.height = 0;
				}
				else
				{
					if (mExpandButtonOriginalWidth != 0)
					{
						params.width = mExpandButtonOriginalWidth;
						params.height = -1;
					}
				}

				button.setLayoutParams(params);
			}
		}

	}


	public enum PunctuationRuleMode
	{
		STOCK(0x0),
		NO_PUNCTUATION_AUTO_SPACE(0x1),
		NO_SPACE_REMOVAL(0x2);

		int mask = 0;

		PunctuationRuleMode( int mask)
		{
			this.mask = mask;
		}

		public static int getMask( boolean noAutoSpace, boolean noSpaceRemoval )
		{
			return  (noAutoSpace ? NO_PUNCTUATION_AUTO_SPACE.mask : 0x0) |
					(noSpaceRemoval ? NO_SPACE_REMOVAL.mask : 0x0);
		}

		public boolean checkMask(int otherMask)
		{
			return (otherMask & this.mask) == this.mask;
		}

	}

	// Values used by both Swiftkey and us.
	// Default is made up by us, not used by Swiftkey.
	public static final int INCOGNITO_ON = 0;
	public static final int INCOGNITO_OFF = 2;
	public static final int INCOGNITO_DEFAULT = -1;

	private static String LOGTAG = ExiModule.getLogTag(KeyboardMethods.class);

	protected static Object mKeyboardLoadObject = null;
	protected static String mCurrentLayoutName = null;

	protected static boolean mLayoutIsWeird = false;
	protected static boolean mLayoutIsExtendedPredictions = false;	//Assume false if we have a hook failure or something
	protected static boolean mIsSymbols = false;

	// Handle landscape and portrait. Assume portrait if value unknown.
	protected static int mDeviceOrientation = Configuration.ORIENTATION_PORTRAIT;

	protected static int mActivePunctuationMode = PunctuationRuleMode.STOCK.mask;

	protected static ArrayList<KeyboardEventListener> mKeyboardEventListeners = new ArrayList<>();
	protected static ArrayList<SharedPreferences.OnSharedPreferenceChangeListener> mSwiftkeyPrefChangedListeners = new ArrayList<>();

	protected static ImageView mReplacementExpandToolbarButton = null;
	protected static ImageView mReplacementExpandToolbarButtonMinor = null;
	protected static ViewGroup mKeyboardRoot = null;
	protected static float mLastKeyboardOpacity = 1;

	// Only loaded once on first keyboard load.
	protected static boolean mIncogStateLoaded = false;

	public static boolean mKeyboardOpen = false;


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

	public static View getKeyboardRoot()
	{
		return mKeyboardRoot;
	}

	public static int getOrientation()
	{
		return mDeviceOrientation;
	}

	public static void saveIncogState( boolean state )
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor( ContextUtils.getHookContext(), ExiXposed.getPrefsPath() );
		editor.putBoolean("pref_exi_xposed_incog_enabled", state);
		editor.apply();
	}

	public static void loadIncogState( )
	{
		SharedPreferences prefs = SettingsCommons.getSharedPreferences( ContextUtils.getHookContext(), ExiXposed.getPrefsPath() );

		boolean state = prefs.getBoolean("pref_exi_xposed_incog_enabled", false);

		if ( state )
		{
			setIncogState(true);
		}
	}

	public static boolean getIncogState()
	{
		SharedPreferences prefs = SettingsCommons.getSharedPreferences( ContextUtils.getHookContext(), ExiXposed.getPrefsPath() );
		return prefs.getBoolean("pref_exi_xposed_incog_enabled", false);
	}

	/**
	 * Note that calling me will also cause incog state to be saved
	 * @param state
	 */
	public static void setIncogState( boolean state )
	{
		if ( KeyboardClassManager.incogControllerClass_staticInstanceField == null )
		{
			Log.e(LOGTAG, "incogControllerClass_staticInstanceField null");
			return;
		}

		if ( KeyboardClassManager.incogControllerClass_ChangeIncogStateMethod == null )
		{
			Log.e(LOGTAG, "incogControllerClass_ChangeIncogStateMethod null");
			return;
		}

		try
		{
			Object instance = KeyboardClassManager.incogControllerClass_staticInstanceField.get(null);
			if ( instance == null )
			{
				Log.e(LOGTAG, "Incog instance null");
				return;
			}

			// 0 is on, 2 is off. 1 I have no idea, but it keeps getting set.
			if ( KeyboardClassManager.incogControllerClass_ChangeIncogStateMethod.getParameterTypes().length < 2 )	// TODO: Legacy. Ditch.
			{
				KeyboardClassManager.incogControllerClass_ChangeIncogStateMethod.invoke(instance, state ? KeyboardMethods.INCOGNITO_ON : KeyboardMethods.INCOGNITO_OFF );
			}
			else
			{
				// No idea what the bool does, but it's always true when toggling.
				KeyboardClassManager.incogControllerClass_ChangeIncogStateMethod.invoke(instance, state ? KeyboardMethods.INCOGNITO_ON : KeyboardMethods.INCOGNITO_OFF, true);
			}


		}
		catch ( Throwable ex )
		{
			Log.e(LOGTAG, "Failed to set incog state");
			ex.printStackTrace();
		}
	}

	public interface KeyboardEventListener
	{
		void beforeKeyboardOpened();
		void afterKeyboardOpened();
		void beforeKeyboardClosed();
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

	public static void setUseSystemVibrate( boolean use )
	{
		try
		{
			SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor( ContextUtils.getHookContext(), ExiXposed.getPrefsPath() );
			editor.putBoolean("pref_system_vibration_key", use);
			editor.apply();
		}
		catch ( Exception ex )
		{
			Log.i(LOGTAG, "Problem setting use system vibrate setting");
			ex.printStackTrace();
		}
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

	public static int getVibrationDuration()
	{
		int duration = 25;
		// Default is 10 is we are using system value.
		// Otherwise try to get swiftkey value.

		try
		{
			SharedPreferences prefs = SettingsCommons.getSharedPreferences(ContextUtils.getHookContext(), ExiXposed.getPrefsPath());

			boolean useSystemVib = prefs.getBoolean("pref_system_vibration_key", true);
			if (useSystemVib)
				duration = 25;
			else
			{
				boolean vibOnKey = prefs.getBoolean("pref_vibrate_on_key", true);
				if (!vibOnKey)
				{
					duration = 0; //disabled!
				}
				else
				{
					duration = prefs.getInt("pref_vibration_slider_key", 10);	//Swiftkey's default is 10, which doesn't register at all.
				}


			}
		}
		catch ( Exception ex )
		{
			Log.e(LOGTAG, "Failed to get swiftkey vibration setting");
			ex.printStackTrace();
		}

		return duration;
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
				PriorityKeyboardClassManager.keyboardLoaderPreference_onSharedPreferenceChangedMethod.invoke(mKeyboardLoadObject, null, "pref_arrows_key");
			}
			catch (Exception ex)
			{
				Log.e(LOGTAG, "Failed to request keyboard reload");
				ex.printStackTrace();
			}
		}
		mKeyboardReloadLastRequested = time;
	}


	public static void updateOrientation( Context context )
	{
		mDeviceOrientation = context.getResources().getConfiguration().orientation;

		Log.i(LOGTAG, "Orientation now: "+mDeviceOrientation);
	}

	public static void updateHidePredictionBarAndPadKeyboardTop()
	{
		if (OverlayCommons.mKeyboardOverlay == null)
		{
			Log.e(LOGTAG, "Overlay null in updateHidePredictionBarAndPadKeyboardTop");
			return;
		}

		View rootView = CodeUtils.getTopParent( OverlayCommons.mKeyboardOverlay );

		try
		{
			int candidatesId = rootView.getContext().getResources().getIdentifier("ribbon_model_tracking_frame", "id", ExiXposed.HOOK_PACKAGE_NAME);
			{
				ViewGroup targetView = rootView.findViewById(candidatesId);	// Would like to hide this too but visibiltiy changes all the time
																			// Maybe remove but just leave as black space? would be easy.

				////////////////////////////////
				// Replacement toolbar button
				////////////////////////////////

				handleReplacementExpandToolbarButton(targetView);


				/////////////////////
				// Predictions bar
				/////////////////////

				if (targetView != null)
				{
					if ( Settings.everActivated_HIDE_PREDICTIONS_BAR)
					{
						// Visibility is the only thing it will listen to
						// params do nothing
						if (Settings.HIDE_PREDICTIONS_BAR)
						{
							targetView.setVisibility(View.GONE);
						}
						else
						{
							targetView.setVisibility(View.VISIBLE);
						}
					}
				}
			}
		}
		catch ( Throwable ex)
		{
			Log.e(LOGTAG, "Something went wrong hiding predictions bar");
			ex.printStackTrace();
		}

	}

	public static boolean loadPunctuationRules()
	{
		int newMode = PunctuationRuleMode.getMask( Settings.DISABLE_PUNCTUATION_AUTO_SPACE, Settings.DISABLE_PUNCTUATION_SPACE_REMOVAL );

		//Don't bother changing if mode already matches
		if ( newMode != mActivePunctuationMode )
		{

			//PunctuatorImpl instance must be present
			if (PriorityKeyboardClassManager.punctuatorImplInstance != null)
			{

				//Do not update active mode if we were unable to set it
				//In that scenario we will also be calling this method when we set the instance
				mActivePunctuationMode = newMode;

				try
				{
					PriorityKeyboardClassManager.punctuatorImplClass_ClearRulesMethod.invoke(PriorityKeyboardClassManager.punctuatorImplInstance);

					Object[] args = new Object[1];

					args[0] = new ByteArrayInputStream(
									KeyboardStrings.getPunctuationRules(
											PunctuationRuleMode.NO_PUNCTUATION_AUTO_SPACE.checkMask(mActivePunctuationMode),
											PunctuationRuleMode.NO_SPACE_REMOVAL.checkMask(mActivePunctuationMode))
									.getBytes(StandardCharsets.UTF_8.name()));

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

	public static boolean getAutocorrectEnabled()
	{
		SharedPreferences prefs = getSwiftkeySharedPrefs();
		return prefs.getInt(AUTOCOMPLETE_PREF_KEY, 1) == 1; // Probably defaults to on?
	}

	public static void setAutocorrectEnabled(boolean on)
	{
		SharedPreferences prefs = getSwiftkeySharedPrefs();
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt( AUTOCOMPLETE_PREF_KEY,  on ? 1 : 0);
		editor.apply();

		return;
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

	private static ImageView getExpandToolbarButton( Context context, boolean makeCompact )
	{
		ImageView button = new ImageView( context );

		int toolbarOpenDrawable = context.getResources().getIdentifier("toolbar_control_button_open", "drawable", ExiXposed.HOOK_PACKAGE_NAME);
		int toolbarClosedDrawable = context.getResources().getIdentifier("toolbar_control_button_closed", "drawable", ExiXposed.HOOK_PACKAGE_NAME);

		// This will probably be called too early
		if ( getToolbarOpen() )
		{
			button.setImageResource(toolbarOpenDrawable);
		}
		else
		{
			button.setImageResource(toolbarClosedDrawable);
		}

		button.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

		FrameLayout.LayoutParams params;
		if (makeCompact)
		{
			params = new FrameLayout.LayoutParams( (int)DimenUtils.calculatePixelFromDp(context, 32), (int)DimenUtils.calculatePixelFromDp(context, 32));
			button.setPadding(0,0, (int)DimenUtils.calculatePixelFromDp(context, 10), 0 );
		}
		else
		{
			params = new FrameLayout.LayoutParams( (int)DimenUtils.calculatePixelFromDp(context, 42), ViewGroup.LayoutParams.MATCH_PARENT );
		}

		//FrameLayout.LayoutParams params = new FrameLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT );

		params.gravity = Gravity.TOP | Gravity.LEFT;
		button.setLayoutParams(params);

		// For some reason the button will stop responding to click events, but touch events are still received.
		button.setOnTouchListener(new View.OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if (event.getActionMasked() == MotionEvent.ACTION_DOWN )
				{
					for (WeakReference<View> reference : mExpandButtons.values())
					{
						View expandButton = reference.get();
						if (expandButton != null)
						{
							expandButton.performClick();

							// TODO: maybe update state of other button too
							if ( getToolbarOpen() )
							{
								button.setImageResource(toolbarOpenDrawable);
							}
							else
							{
								button.setImageResource(toolbarClosedDrawable);
							}

							// Visibility gets reset so refresh here
							updateHidePredictionBarAndPadKeyboardTop();
							break;
						}
					}

					return true;
				}

				return false;
			}
		});


		return button;
	}

	public static void handleReplacementExpandToolbarButton( ViewGroup toolbarContainer )
	{
		if (toolbarContainer != null)
		{
			if (mReplacementExpandToolbarButton == null)
			{
				mReplacementExpandToolbarButton = getExpandToolbarButton(toolbarContainer.getContext(), false);
				mReplacementExpandToolbarButtonMinor = getExpandToolbarButton(toolbarContainer.getContext(), true);
			}

			//////////////////////////////////////////
			// Button that goes next to suggestions
			//////////////////////////////////////////


			if ( Settings.REMOVE_SUGGESTIONS_PADDING )
			{
				// Ensure we are the top view
				mReplacementExpandToolbarButton.setVisibility(View.VISIBLE);

				if (toolbarContainer != null )
				{
					// Sometimes a new container is created, so we cannot assume that
					// the, if the view has a parent, it is the same as this new container.
					{
						ViewParent currentParent = mReplacementExpandToolbarButton.getParent();
						if (currentParent != null && currentParent != toolbarContainer)
						{
							// Has parent, but is a different container
							((ViewGroup)currentParent).removeView(mReplacementExpandToolbarButton);
						}
					}

					// Will handle inserting the ivew, and moving it back to the top if something has decided to cover it.
					int existingIndex = toolbarContainer.indexOfChild( mReplacementExpandToolbarButton );
					if ( existingIndex < toolbarContainer.getChildCount() -1 )
					{
						if (existingIndex != -1) // Skip removing if not actually a child yet
							toolbarContainer.removeView(mReplacementExpandToolbarButton);
						toolbarContainer.addView(mReplacementExpandToolbarButton);
					}
				}
			}
			else
			{
				mReplacementExpandToolbarButton.setVisibility(View.GONE);
			}

			//////////////////////////////////////////////////////////
			// Inserted at top of keyboard when suggestions hidden
			//////////////////////////////////////////////////////////


			if ( Settings.HIDE_PREDICTIONS_BAR )
			{
				// Ensure we are the top view
				mReplacementExpandToolbarButtonMinor.setVisibility(View.VISIBLE);

				if ( mKeyboardRoot != null )
				{
					int keyboardFrameHolderId = mKeyboardRoot.getContext().getResources().getIdentifier("keyboard_frame_holder", "id", ExiXposed.HOOK_PACKAGE_NAME);
					toolbarContainer = mKeyboardRoot.findViewById(keyboardFrameHolderId);

					if (toolbarContainer != null)
					{
						// Sometimes a new container is created, so we cannot assume that
						// the, if the view has a parent, it is the same as this new container.
						{
							ViewParent currentParent = mReplacementExpandToolbarButtonMinor.getParent();
							if (currentParent != null && currentParent != toolbarContainer)
							{
								// Has parent, but is a different container
								((ViewGroup)currentParent).removeView(mReplacementExpandToolbarButtonMinor);
							}
						}

						int existingIndex = toolbarContainer.indexOfChild( mReplacementExpandToolbarButtonMinor );
						if ( existingIndex < toolbarContainer.getChildCount() -1 )
						{
							if (existingIndex != -1) // Skip removing if not actually a child yet
								toolbarContainer.removeView(mReplacementExpandToolbarButtonMinor);
							toolbarContainer.addView(mReplacementExpandToolbarButtonMinor);
						}

					}
				}
			}
			else
			{
				mReplacementExpandToolbarButtonMinor.setVisibility(View.GONE);
			}


		}
	}

	public static void setThemeByHash(String themeHash)
	{
		if ( KeyboardClassManager.themeSetter_dummyCtiInstance == null )
		{
			Log.e(LOGTAG, "themeSetter_dummyCtiInstance was null, not setting theme");
			return;
		}

		if ( KeyboardClassManager.themeSetterClass_instance == null )
		{
			Log.e(LOGTAG, "themeSetterClass_instance was null, not setting theme");
			return;
		}

		if ( KeyboardClassManager.themeSetterClass_setThemeMethod == null )
		{
			Log.e(LOGTAG, "themeSetterClass_setThemeMethod was null, not setting theme");
			return;
		}

		Object[] args = new Object[4];

		args[0] = themeHash;
		args[1] = true;	// No idea what this does but should be true.
		args[2] = KeyboardClassManager.themeSetter_dummyCtiInstance;	// Proxy of interface that does nothing
		args[3] = new ThemeExecutor();	// Callback that does nothing


		try
		{
			KeyboardClassManager.themeSetterClass_setThemeMethod.invoke(KeyboardClassManager.themeSetterClass_instance, args);
		}
		catch ( Exception ex )
		{
			Log.e(LOGTAG, "Failed to call set theme method");
			ex.printStackTrace();
		}
	}

	private static class ThemeExecutor implements Executor
	{
		@Override
		public void execute(@NonNull Runnable command)
		{
			// Doesn't need to do anything, used by Theme Setter.
		}
	}
}
