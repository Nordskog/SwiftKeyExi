package com.mayulive.swiftkeyexi.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.providers.SharedPreferencesProvider;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SelectionBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SpaceModifierBehavior;

import java.util.ArrayList;


/**
 * Created by Roughy on 1/1/2017.
 */


public class Settings
{

	private static String LOGTAG = ExiModule.getLogTag(Settings.class);

	private static ArrayList<OnSettingsUpdatedListener> mSettingsUpdatedListeners = new ArrayList<>();

	private static boolean mFirstUpdateRun = false;

	//These should be considered read-only for any external class
	public static boolean DICTIONARY_ENABLED = true;
	//public static boolean LONGPRESS_BUTTON_INSERTION_ENABLED = true;
	public static boolean SHORTCUT_KEYS_ENABLED = true;
	public static boolean EMOJI_PANEL_ENABLED = true;

	public static boolean MORE_SUGGESTIONS_ENABLED = true;
	public static boolean FLOW_SUGGESTIONS_ENABLED = true;
	public static boolean PREDICTION_SUGGESTIONS_ENABLED = true;

	public static boolean DISABLE_SWIPE_AUTO_CORRECT = true;

	public static boolean OLD_FLOW_BEHAVIOR = false;
	public static boolean DISABLE_PERIOD_CLICK = false;

	public static boolean REMOVE_SUGGESTIONS_PADDING = false;

	public static boolean USE_CUSTOM_KEYPRESS_SOUND = false;

	public static boolean DISABLE_CURSOR_JUMPING = false;

	public static boolean DISPLAY_NSFW_GIFS = false;
	public static boolean DISPLAY_GIFS_FROM_MORE_SOURCES = false;

	public static boolean DISABLE_FULLSCREEN_KEYBOARD = false;

	public static float KEYBOARD_SIZE_MULTIPLIER = 1;

	//public static boolean SPACE_SWIPE_MODIFIER_ENABLED = true;

	public static SpaceModifierBehavior SPACE_MODIFIER_BEHAVIOR = SpaceModifierBehavior.MENU;

	public static SelectionBehavior SWIPE_SELECTION_BEHAVIOR = SelectionBehavior.HYBRID;
	public static CursorBehavior SWIPE_CURSOR_BEHAVIOR = CursorBehavior.SWIPE;

	public static float SWIPE_CURSOR_UNITS = 50;
	public static float SWIPE_THRESHOLD = 50;

	//Time last changed on config app
	public static long LAST_DICTIONARY_UPDATE = 0;
	public static long LAST_EMOJI_UPDATE = 0;
	public static long LAST_ADDITIONAL_KEYS_UPDATE = 0;
	public static long LAST_POPUP_UPDATE = 0;
	public static long LAST_HOTKEYS_UPDATE = 0;
	public static long LAST_QUICKMENU_UPDATE = 0;
	public static long LAST_KEYPRESS_SOUND_UPDATE = 0;

	public static int QUICK_MENU_HIGHLIGHT_COLOR = 0xFF2d5bc6;

	public static int EMOJI_TEXT_SIZE = 12;
	public static boolean EMOJI_TAP_VIBRATE = false;

	public static float QUICKMENU_TEXT_SIZE_RATIO = 0.15f;

	public static boolean DISABLE_PUNCTUATION_AUTO_SPACE = false;

	//We want to keep track of some settings, specifically to make changes when they change
	public static boolean changed_REMOVE_SUGGESTIONS_PADDING = true;
	public static boolean changed_EMOJI_TEXT_SIZE = true;

	//Set to true if any setting that requires a realod of the keyboard is changed
	public static boolean request_KEYBOARD_RELOAD = false;


	public static void loadSettings(SharedPreferences prefs)
	{
		DICTIONARY_ENABLED = prefs.getBoolean(PreferenceConstants.pref_dictionary_toggle_key, true);
		//LONGPRESS_BUTTON_INSERTION_ENABLED = prefs.getBoolean(PreferenceConstants.pref_longpress_key_toggle_key, true);
		SHORTCUT_KEYS_ENABLED = prefs.getBoolean(PreferenceConstants.pref_shortcut_keys_toggle_key, true);

		MORE_SUGGESTIONS_ENABLED = prefs.getBoolean(PreferenceConstants.pref_more_suggestions_key, true);
		FLOW_SUGGESTIONS_ENABLED = prefs.getBoolean(PreferenceConstants.pref_flow_suggestions_key, false);
		PREDICTION_SUGGESTIONS_ENABLED = prefs.getBoolean(PreferenceConstants.pref_prediction_suggestions_key, true);

		DISABLE_PUNCTUATION_AUTO_SPACE = prefs.getBoolean(PreferenceConstants.pref_disable_punctuation_autospace_key, false);

		DISABLE_SWIPE_AUTO_CORRECT = prefs.getBoolean(PreferenceConstants.pref_disable_auto_correct_on_cursor_move_key, true);

		DISABLE_CURSOR_JUMPING = prefs.getBoolean(PreferenceConstants.pref_disable_jumping_cursor_key, false);

		EMOJI_PANEL_ENABLED = prefs.getBoolean(PreferenceConstants.pref_emoji_panel_key, true);

		USE_CUSTOM_KEYPRESS_SOUND = prefs.getBoolean(PreferenceConstants.pref_sound_use_custom_keypress_key, false);

		DISPLAY_NSFW_GIFS = prefs.getBoolean(PreferenceConstants.pref_gifs_enable_nsfw_key, false);
		DISPLAY_GIFS_FROM_MORE_SOURCES = prefs.getBoolean(PreferenceConstants.pref_gifs_more_sources_key, false);

		DISABLE_FULLSCREEN_KEYBOARD = prefs.getBoolean(PreferenceConstants.pref_disable_fullscreen_key, false);

		SWIPE_SELECTION_BEHAVIOR = SelectionBehavior.valueOf
				(
					prefs.getString(PreferenceConstants.pref_selection_behavior_key, SelectionBehavior.HYBRID.toString() )
				);

		SWIPE_CURSOR_BEHAVIOR = CursorBehavior.valueOf
				(
						prefs.getString(PreferenceConstants.pref_cursor_behavior_key, CursorBehavior.SWIPE.toString() )
				);

		SWIPE_CURSOR_UNITS = prefs.getFloat(PreferenceConstants.pref_cursor_speed_key, 50);
		SWIPE_THRESHOLD = prefs.getFloat(PreferenceConstants.pref_swipe_threshold_key, 50);

		//SPACE_SWIPE_MODIFIER_ENABLED = prefs.getBoolean(PreferenceConstants.pref_space_swipe_modifier_key, true);

		SPACE_MODIFIER_BEHAVIOR = SpaceModifierBehavior.valueOf
				(
						prefs.getString(PreferenceConstants.pref_space_swipe_modifier_mode_key, SpaceModifierBehavior.MENU.toString() )
				);

		LAST_DICTIONARY_UPDATE = prefs.getLong(PreferenceConstants.pref_dictionary_last_update_key, 0);
		LAST_EMOJI_UPDATE = prefs.getLong(PreferenceConstants.pref_emoji_last_update_key, 0);
		LAST_ADDITIONAL_KEYS_UPDATE = prefs.getLong(PreferenceConstants.pref_additional_keys_last_update_key, 0);
		LAST_HOTKEYS_UPDATE = prefs.getLong(PreferenceConstants.pref_hotkeys_last_update_key, 0);
		LAST_QUICKMENU_UPDATE = prefs.getLong(PreferenceConstants.pref_quickmenu_last_update_key, 0);
		LAST_KEYPRESS_SOUND_UPDATE = prefs.getLong(PreferenceConstants.pref_sound_keypress_last_update_key, 0);

		//Require keyboard reload
		{
			{
				long existingValue = LAST_POPUP_UPDATE;
				LAST_POPUP_UPDATE = prefs.getLong(PreferenceConstants.pref_popup_last_update_key, 0);
				if (LAST_POPUP_UPDATE != existingValue)
					request_KEYBOARD_RELOAD = true;
			}
		}

		changed_REMOVE_SUGGESTIONS_PADDING = REMOVE_SUGGESTIONS_PADDING;

		REMOVE_SUGGESTIONS_PADDING = prefs.getBoolean(PreferenceConstants.pref_remove_suggestion_padding_key, false);
		changed_REMOVE_SUGGESTIONS_PADDING = changed_REMOVE_SUGGESTIONS_PADDING != REMOVE_SUGGESTIONS_PADDING;

		{
			float tempEmojiTextSize = EMOJI_TEXT_SIZE;
			EMOJI_TEXT_SIZE = prefs.getInt(PreferenceConstants.pref_emoji_text_size_key, 12);
			changed_EMOJI_TEXT_SIZE = tempEmojiTextSize != EMOJI_TEXT_SIZE;
		}
		EMOJI_TAP_VIBRATE = prefs.getBoolean(PreferenceConstants.pref_emoji_tap_vibrate_key, true);

		OLD_FLOW_BEHAVIOR = prefs.getBoolean(PreferenceConstants.pref_old_flow_behavior_key, false);
		DISABLE_PERIOD_CLICK = prefs.getBoolean(PreferenceConstants.pref_disable_period_click_key, false);

		QUICK_MENU_HIGHLIGHT_COLOR = prefs.getInt(PreferenceConstants.pref_quick_menu_color_key, 0xFF2d5bc6);
		QUICKMENU_TEXT_SIZE_RATIO = prefs.getFloat(PreferenceConstants.pref_hotkey_menu_text_size_key, 0.15f);


		{
			float existingValue = KEYBOARD_SIZE_MULTIPLIER;
			KEYBOARD_SIZE_MULTIPLIER =  prefs.getFloat(PreferenceConstants.pref_keyboard_size_multiplier_key, 1f);
			if (existingValue != KEYBOARD_SIZE_MULTIPLIER)
			{
				KeyboardMethods.forceKeyboardResize();
			}

		}

		checkSettingRequirements();
	}

	//Some settings should be switched to off if certain hook requirements are not met.
	//Easier to do here than where the setting is actually applied.
	//In most cases the hook where the setting would be relevant will already have been
	//removed at this point, but some switches exist within hooks shared with other features.
	public static void checkSettingRequirements()
	{
		if (!Hooks.overlayHooks_base.isRequirementsMet())
		{
			SPACE_MODIFIER_BEHAVIOR = SpaceModifierBehavior.DISABLED;
		}
	}

	public interface OnSettingsUpdatedListener
	{
		void OnSettingsUpdated();
	}

	public static void addOnSettingsUpdatedListener(OnSettingsUpdatedListener listener)
	{
		mSettingsUpdatedListeners.add(listener);
	}

	public static void removeOnSettingsUpdatedListener(OnSettingsUpdatedListener listener)
	{
		mSettingsUpdatedListeners.remove(listener);
	}

	private static void callSettingsUpdatedListeners()
	{
		for (OnSettingsUpdatedListener listener : mSettingsUpdatedListeners)
		{
			listener.OnSettingsUpdated();
		}

		//At this point any changes that require data to be read from exi's database should have completed.
		//If they require the keyboard to be reloaded, do so.
		if (Settings.request_KEYBOARD_RELOAD)
		{
			Settings.request_KEYBOARD_RELOAD = false;
			KeyboardMethods.requestKeyboardReload();
		}
	}

	//Loading from provider is slow
	public static void updateSettingsFromProvider(final Context context)
	{
		AsyncTask.execute(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					SharedPreferences sharedPrefs = SharedPreferencesProvider.getSharedPreferences(context);
					loadSettings(sharedPrefs);
					Handler handler = new Handler(Looper.getMainLooper());
					handler.post(new Runnable()
					{
						@Override
						public void run()
						{
							callSettingsUpdatedListeners();
						}
					});

				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});

	}




}
