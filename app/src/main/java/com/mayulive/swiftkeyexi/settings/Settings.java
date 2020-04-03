package com.mayulive.swiftkeyexi.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.settings.CustomSearchStringInputDialog;
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

	//////////////////////////
	// Settings
	//////////////////////////


	public static boolean DICTIONARY_ENABLED = true;

	public static boolean SHORTCUT_KEYS_ENABLED = true;
	public static boolean EMOJI_PANEL_ENABLED = true;

	public static boolean MORE_SUGGESTIONS_ENABLED = true;
	public static boolean FLOW_SUGGESTIONS_ENABLED = true;
	public static boolean PREDICTION_SUGGESTIONS_ENABLED = true;

	public static boolean DISABLE_SWIPE_AUTO_CORRECT = true;

	public static boolean OLD_FLOW_BEHAVIOR = false;
	public static boolean DISABLE_PERIOD_CLICK = false;

	public static boolean REMOVE_SUGGESTIONS_PADDING = false;
	public static boolean REPLACE_TOOLBAR_TOGGLE_WITH_SWIPE_GESTURE = false;	// Also set to false if REMOVE_SUGGESTIONS_PADDING not enabled

	public static boolean USE_CUSTOM_KEYPRESS_SOUND = false;

	public static boolean DISABLE_CURSOR_JUMPING = false;

	public static boolean DISPLAY_NSFW_GIFS = false;
	public static boolean USE_US_GIF_REGION = false;
	public static boolean DISPLAY_GIFS_FROM_MORE_SOURCES = false;
	public static boolean GIF_REMOVE_REDIRECT = true;

	public static boolean DISABLE_FULLSCREEN_KEYBOARD = false;

	public static float KEYBOARD_SIZE_MULTIPLIER = 1;
	public static float KEYBOARD_SIZE_MULTIPLIER_LANDSCAPE = 1;
	public static float KEYBOARD_SIZE_EMOJI_MULTIPLIER = 1;
	public static float KEYBOARD_SIZE_EMOJI_MULTIPLIER_LANDSCAPE = 1;

	public static boolean USE_CUSTOM_SEARCH_STRING = false;
	public static String CUSTOM_SEARCH_STRING = CustomSearchStringInputDialog.GOOGLE_SEARCH_STRING;

	//public static boolean SPACE_SWIPE_MODIFIER_ENABLED = true;

	public static SpaceModifierBehavior SPACE_MODIFIER_BEHAVIOR = SpaceModifierBehavior.MENU;

	public static SelectionBehavior SWIPE_SELECTION_BEHAVIOR = SelectionBehavior.HYBRID;
	public static CursorBehavior SWIPE_CURSOR_BEHAVIOR = CursorBehavior.SWIPE;

	public static boolean SWIPE_DIRECTION_ANY = true;

	public static float SWIPE_CURSOR_UNITS = 50;
	public static float SWIPE_THRESHOLD = 50;

	public static boolean HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD = false;

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
	public static String EMOJI_DEFAULT_DIVERSE_MODIFIER = "";
	public static boolean EMOJI_TAP_VIBRATE = false;

	public static float QUICKMENU_TEXT_SIZE_RATIO = 0.15f;

	public static boolean DISABLE_PUNCTUATION_AUTO_SPACE = false;
	public static boolean DISABLE_PUNCTUATION_SPACE_REMOVAL = false;
	public static boolean DISABLE_PREDICTION_AUTO_SPACE = false;

	//Set to true if any setting that requires a realod of the keyboard is changed
	public static boolean request_KEYBOARD_RELOAD = false;

	//Transparent keyboard is transparent
	public static float KEYBOARD_OPACITY = 1f;

	public static boolean SWIPE_RTL_MODE_ENABLED = false;

	public static boolean HIDE_PREDICTIONS_BAR = false;

	///////////////////////
	// Settings changed
	///////////////////////

	//We want to keep track of some settings, specifically to make changes when they change
	public static boolean changed_SUGGESTIONS_PADDING_OR_TOOLBAR_BUTTON = true;
	public static boolean changed_EMOJI_TEXT_RESOURCE = true;
	public static boolean changed_HIDE_PREDICTIONS_BAR = true;

	///////////////////////
	// Ever modified
	///////////////////////

	//If never enabled, we do not need to perform the work required when disabling it.
	public static boolean everActivated_HIDE_PREDICTIONS_BAR = false;


	public static void loadSettings(SharedPreferences prefs)
	{
		DICTIONARY_ENABLED = prefs.getBoolean(PreferenceConstants.pref_dictionary_toggle_key, true);
		//LONGPRESS_BUTTON_INSERTION_ENABLED = prefs.getBoolean(PreferenceConstants.pref_longpress_key_toggle_key, true);
		SHORTCUT_KEYS_ENABLED = prefs.getBoolean(PreferenceConstants.pref_shortcut_keys_toggle_key, true);

		MORE_SUGGESTIONS_ENABLED = prefs.getBoolean(PreferenceConstants.pref_more_suggestions_key, true);
		FLOW_SUGGESTIONS_ENABLED = prefs.getBoolean(PreferenceConstants.pref_flow_suggestions_key, false);
		PREDICTION_SUGGESTIONS_ENABLED = prefs.getBoolean(PreferenceConstants.pref_prediction_suggestions_key, true);

		DISABLE_PUNCTUATION_AUTO_SPACE = prefs.getBoolean(PreferenceConstants.pref_disable_punctuation_autospace_key, false);
		DISABLE_PUNCTUATION_SPACE_REMOVAL = prefs.getBoolean(PreferenceConstants.pref_disable_punctuation_space_removal_key, false);
		DISABLE_PREDICTION_AUTO_SPACE = prefs.getBoolean(PreferenceConstants.pref_disable_prediction_autospace_key, false);


		DISABLE_SWIPE_AUTO_CORRECT = prefs.getBoolean(PreferenceConstants.pref_disable_auto_correct_on_cursor_move_key, true);

		DISABLE_CURSOR_JUMPING = prefs.getBoolean(PreferenceConstants.pref_disable_jumping_cursor_key, false);

		EMOJI_PANEL_ENABLED = prefs.getBoolean(PreferenceConstants.pref_emoji_panel_key, true);

		USE_CUSTOM_KEYPRESS_SOUND = prefs.getBoolean(PreferenceConstants.pref_sound_use_custom_keypress_key, false);

		DISPLAY_NSFW_GIFS = prefs.getBoolean(PreferenceConstants.pref_gifs_enable_nsfw_key, false);
		USE_US_GIF_REGION = prefs.getBoolean(PreferenceConstants.pref_gifs_us_region_key, false);
		DISPLAY_GIFS_FROM_MORE_SOURCES = prefs.getBoolean(PreferenceConstants.pref_gifs_more_sources_key, false);
		GIF_REMOVE_REDIRECT = prefs.getBoolean(PreferenceConstants.pref_gifs_remove_redirect_key, true);

		SWIPE_DIRECTION_ANY = prefs.getBoolean(PreferenceConstants.pref_swipe_direction_any_key, true);

		DISABLE_FULLSCREEN_KEYBOARD = prefs.getBoolean(PreferenceConstants.pref_disable_fullscreen_key, false);

		HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD = prefs.getBoolean(PreferenceConstants.pref_hardware_remap_only_in_keyboard_key, false);


		SWIPE_RTL_MODE_ENABLED = prefs.getBoolean(PreferenceConstants.pref_swipe_rtl_mode_key, false);

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

		{
			changed_SUGGESTIONS_PADDING_OR_TOOLBAR_BUTTON = REMOVE_SUGGESTIONS_PADDING;

			REMOVE_SUGGESTIONS_PADDING = prefs.getBoolean(PreferenceConstants.pref_remove_suggestion_padding_key, false);
			changed_SUGGESTIONS_PADDING_OR_TOOLBAR_BUTTON = changed_SUGGESTIONS_PADDING_OR_TOOLBAR_BUTTON != REMOVE_SUGGESTIONS_PADDING;

		}

		{
			boolean changed = REPLACE_TOOLBAR_TOGGLE_WITH_SWIPE_GESTURE;
			REPLACE_TOOLBAR_TOGGLE_WITH_SWIPE_GESTURE = prefs.getBoolean(PreferenceConstants.pref_replace_toolbar_button_with_swipe_key, false);

			// This should only be enabled if remove_suggestions_padding is also enabled
			REPLACE_TOOLBAR_TOGGLE_WITH_SWIPE_GESTURE = REPLACE_TOOLBAR_TOGGLE_WITH_SWIPE_GESTURE && REMOVE_SUGGESTIONS_PADDING;

			changed = changed != REPLACE_TOOLBAR_TOGGLE_WITH_SWIPE_GESTURE;

			changed_SUGGESTIONS_PADDING_OR_TOOLBAR_BUTTON = changed_SUGGESTIONS_PADDING_OR_TOOLBAR_BUTTON || changed;
		}


		{
			float tempEmojiTextSize = EMOJI_TEXT_SIZE;
			EMOJI_TEXT_SIZE = prefs.getInt(PreferenceConstants.pref_emoji_text_size_key, 12);
			changed_EMOJI_TEXT_RESOURCE = tempEmojiTextSize != EMOJI_TEXT_SIZE;
		}

		{
			String tempDefaultDiverseModifier = EMOJI_DEFAULT_DIVERSE_MODIFIER;
			EMOJI_DEFAULT_DIVERSE_MODIFIER = prefs.getString(PreferenceConstants.pref_emoji_default_diverse_modifier_key, "");
			changed_EMOJI_TEXT_RESOURCE = changed_EMOJI_TEXT_RESOURCE || !tempDefaultDiverseModifier.equals( EMOJI_TEXT_SIZE );
		}


		EMOJI_TAP_VIBRATE = prefs.getBoolean(PreferenceConstants.pref_emoji_tap_vibrate_key, true);

		OLD_FLOW_BEHAVIOR = prefs.getBoolean(PreferenceConstants.pref_old_flow_behavior_key, false);
		DISABLE_PERIOD_CLICK = prefs.getBoolean(PreferenceConstants.pref_disable_period_click_key, false);

		QUICK_MENU_HIGHLIGHT_COLOR = prefs.getInt(PreferenceConstants.pref_quick_menu_color_key, 0xFF2d5bc6);
		QUICKMENU_TEXT_SIZE_RATIO = prefs.getFloat(PreferenceConstants.pref_hotkey_menu_text_size_key, 0.15f);

		{
			float existingValue = KEYBOARD_SIZE_MULTIPLIER + (KEYBOARD_SIZE_MULTIPLIER_LANDSCAPE * 10);
			KEYBOARD_SIZE_MULTIPLIER =  prefs.getFloat(PreferenceConstants.pref_keyboard_size_multiplier_key, 1f);
			KEYBOARD_SIZE_MULTIPLIER_LANDSCAPE =  prefs.getFloat(PreferenceConstants.pref_keyboard_size_multiplier_landscape_key, 1f);
			KEYBOARD_SIZE_EMOJI_MULTIPLIER =  prefs.getFloat(PreferenceConstants.pref_keyboard_size_emoji_multiplier_key, 1f);
			KEYBOARD_SIZE_EMOJI_MULTIPLIER_LANDSCAPE =  prefs.getFloat(PreferenceConstants.pref_keyboard_size_emoji_multiplier_landscape_key, 1f);

			if (existingValue != KEYBOARD_SIZE_MULTIPLIER + (KEYBOARD_SIZE_MULTIPLIER_LANDSCAPE * 10) + ( KEYBOARD_SIZE_EMOJI_MULTIPLIER * 100 ) + ( KEYBOARD_SIZE_EMOJI_MULTIPLIER_LANDSCAPE * 1000 ) )
			{
				KeyboardMethods.forceKeyboardResize();
			}

		}


		//Value is int 0 to 100, convert to 0-1f
		KEYBOARD_OPACITY = ( (float)prefs.getInt(PreferenceConstants.pref_keyboard_opacity_key, 100) / (float)100  ) ;

		{
			boolean originalValue = HIDE_PREDICTIONS_BAR;
			HIDE_PREDICTIONS_BAR = prefs.getBoolean(PreferenceConstants.pref_hide_predictions_key, false);
			if (originalValue != HIDE_PREDICTIONS_BAR)
				changed_HIDE_PREDICTIONS_BAR = true;

			everActivated_HIDE_PREDICTIONS_BAR = everActivated_HIDE_PREDICTIONS_BAR || HIDE_PREDICTIONS_BAR;
		}

		{
			USE_CUSTOM_SEARCH_STRING = prefs.getBoolean( PreferenceConstants.pref_use_custom_search_key, false );
			CUSTOM_SEARCH_STRING = prefs.getString( PreferenceConstants.pref_custom_search_string_key, CustomSearchStringInputDialog.GOOGLE_SEARCH_STRING );
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
			try
			{
				listener.OnSettingsUpdated();
			}
			catch (Exception ex )
			{
				Log.e(LOGTAG, "Problem calling settings updated listener");
				ex.printStackTrace();
			}

		}

		//At this point any changes that require data to be read from exi's database should have completed.
		//If they require the keyboard to be reloaded, do so.
		if (Settings.request_KEYBOARD_RELOAD)
		{
			Settings.request_KEYBOARD_RELOAD = false;
			KeyboardMethods.requestKeyboardReload();
		}
	}

	public static void updateSettingsFromProviderSync( Context context )
	{
		SharedPreferences sharedPrefs = SharedPreferencesProvider.getSharedPreferences(context);
		loadSettings(sharedPrefs);
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
					updateSettingsFromProviderSync(context);
					Handler handler = new Handler(Looper.getMainLooper());
					handler.post(new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
								callSettingsUpdatedListeners();
							}
							catch( Throwable ex)
							{
								//This usually happens when the user installs an Exi update that
								//modifies the database definitions and forgets to reboot.
								Log.e(LOGTAG, "Failed to update settings from exi");
								ex.printStackTrace();
							}
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
