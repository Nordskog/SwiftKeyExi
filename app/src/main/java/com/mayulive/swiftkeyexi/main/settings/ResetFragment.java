package com.mayulive.swiftkeyexi.main.settings;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.main.emoji.data.FancyEmojiPanelTemplates;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;

/**
 * Created by Roughy on 1/4/2017.
 */

public class ResetFragment extends PreferenceFragmentCompat
{

	WrappedDatabase mDbWrap;

	private void setupReferences()
	{
		mDbWrap = DatabaseHolder.getWrapped(this.getContext());
	}


	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
	{

		setupReferences();

		PreferenceManager prefMgr = getPreferenceManager();
		prefMgr.setSharedPreferencesName(SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);

		addPreferencesFromResource(R.xml.preferences_reset);


		//Prepare two arrays containing pref keys and warning messages
		String[] prefKeys = new String[4];
		int[] prefWarnings = new int[4];
		ExiModule.ModuleDatabaseType[] prefTypes = new ExiModule.ModuleDatabaseType[4];
		{
			prefKeys[0] = this.getContext().getResources().getString( R.string.pref_emoji_reset_key );
			prefKeys[1] = this.getContext().getResources().getString( R.string.pref_hotkeys_reset_key );
			prefKeys[2] = this.getContext().getResources().getString( R.string.pref_keys_reset_key );
			prefKeys[3] = this.getContext().getResources().getString( R.string.pref_quickmenu_reset_key );

			prefWarnings[0] = R.string.pref_emoji_reset_warning ;
			prefWarnings[1] = R.string.pref_hotkeys_reset_warning;
			prefWarnings[2] = R.string.pref_keys_reset_warning;
			prefWarnings[3] = R.string.pref_quickmenu_reset_warning;

			prefTypes[0] = ExiModule.ModuleDatabaseType.EMOJI;
			prefTypes[1] = ExiModule.ModuleDatabaseType.HOTKEY;
			prefTypes[2] = ExiModule.ModuleDatabaseType.KEYS;
			prefTypes[3] = ExiModule.ModuleDatabaseType.HOTKEY_MENU_ITEM;
		}

		for (int i = 0; i < prefKeys.length; i++)
		{
			final ExiModule.ModuleDatabaseType type = prefTypes[i];
			final String prefKey = prefKeys[i];
			final int prefWarning = prefWarnings[i];

			Preference pref = findPreference( prefKey );
			pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
			{
				@Override
				public boolean onPreferenceClick(Preference preference)
				{
					showRestoreDefaultsConfirmationDialog(type, prefWarning);

					return true;
				}
			});
		}
	}


	void showRestoreDefaultsConfirmationDialog(final ExiModule.ModuleDatabaseType type, int warning)
	{
		new AlertDialog.Builder(getContext())

				.setTitle(R.string.pref_reset_title)
				.setMessage(warning)
				.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialogInterface, int i)
					{
						ExiModule.clear(getContext(), mDbWrap, type);

						SharedPreferences prefs = SettingsCommons.getSharedPreferences(ResetFragment.this.getContext());
						FancyEmojiPanelTemplates.EmojiPanelVersion newVersion = FancyEmojiPanelTemplates.EmojiPanelVersion.getFromPref( ExiModule.EMOJI_PANEL_VERSION.get(getContext(), FancyEmojiPanelTemplates.EmojiPanelVersion.AUTO));


						ExiModule.loadDefaults(getContext(), mDbWrap, type, newVersion.getSdkVersion());
					}
				})
				.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialogInterface, int i)
					{

					}
				})
				.show();
	}

	@Override
	public void onResume()
	{
		super.onResume();
		getActivity().setTitle(R.string.pref_reset_title);
	}


}
