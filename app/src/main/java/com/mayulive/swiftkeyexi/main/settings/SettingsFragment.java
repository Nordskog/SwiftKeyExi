package com.mayulive.swiftkeyexi.main.settings;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.settings.DualNumberPickerPreferenceFragment;
import com.mayulive.swiftkeyexi.settings.FloatNumberPickerPreference;
import com.mayulive.swiftkeyexi.settings.NumberPickerPreference;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.settings.NumberPickerPreferenceFragment;
import com.mayulive.swiftkeyexi.settings.OpacityPreference;
import com.mayulive.swiftkeyexi.settings.OpacityPreferenceFragment;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;

/**
 * Created by Roughy on 1/4/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat
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

		addPreferencesFromResource(R.xml.preferences);

		Preference shiftPreference = findPreference(this.getContext().getResources().getString( R.string.pref_additional_shift_keys_key ));
		shiftPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				displayAdditionalKeysFragment(KeyType.SHIFT);
				return true;
			}
		});

		Preference deletePreference = findPreference(this.getContext().getResources().getString( R.string.pref_additional_delete_keys_key ));
		deletePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				displayAdditionalKeysFragment(KeyType.DELETE);
				return true;
			}
		});

		Preference symbolPreference = findPreference(this.getContext().getResources().getString( R.string.pref_additional_symbol_keys_key ));
		symbolPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				displayAdditionalKeysFragment(KeyType.SYMBOL);
				return true;
			}
		});

		Preference aboutPreference = findPreference(this.getContext().getResources().getString( R.string.pref_about_key ));
		aboutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				displayFragment( new AboutFragment() );
				return true;
			}
		});

		Preference resetPreference = findPreference(this.getContext().getResources().getString( R.string.pref_reset_key ));
		resetPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				displayFragment( new ResetFragment() );
				return true;
			}
		});

		Preference soundPreference = findPreference(this.getContext().getResources().getString( R.string.pref_key_sound_key ));
		soundPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				displayFragment( new SoundFragment() );
				return true;
			}
		});

		Preference remappedKeysPreference = findPreference(this.getContext().getResources().getString( R.string.pref_remapped_keys_key ));
		remappedKeysPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				displayFragment( new RemappedKeysFragment() );
				return true;
			}
		});

		Preference customSearchPreference = findPreference(this.getContext().getResources().getString( R.string.pref_section_custom_search_key ));
		customSearchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				displayFragment( new CustomSearchFragment() );
				return true;
			}
		});

	}

	private void displayAdditionalKeysFragment(KeyType type)
	{
		KeyDefinitionFragment nextFrag= new KeyDefinitionFragment();

		Bundle extras = new Bundle();

		if (type == KeyType.SHIFT)
			extras.putInt(KeyDefinitionFragment.KEY_DEFINITION_FRAGMENT_BUNDLE_KEY, KeyDefinitionFragment.KEY_DEFINITION_FRAGMENT_SHIFT_KEY_BUNDLE_VALUE);
		else if (type == KeyType.SYMBOL)
			extras.putInt(KeyDefinitionFragment.KEY_DEFINITION_FRAGMENT_BUNDLE_KEY, KeyDefinitionFragment.KEY_DEFINITION_FRAGMENT_SYMBOL_KEY_BUNDLE_VALUE);
		else
			extras.putInt(KeyDefinitionFragment.KEY_DEFINITION_FRAGMENT_BUNDLE_KEY, KeyDefinitionFragment.KEY_DEFINITION_FRAGMENT_DELETE_KEY_BUNDLE_VALUE);

		nextFrag.setArguments(extras);

		this.getFragmentManager().beginTransaction()
				.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,  android.R.anim.slide_in_left, android.R.anim.slide_out_right)
				.replace(R.id.settings_activity_fragment_container, nextFrag, null)
				.addToBackStack(null)
				.commit();

	}

	private void displayFragment(Fragment frag)
	{
		this.getFragmentManager().beginTransaction()
				.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,  android.R.anim.slide_in_left, android.R.anim.slide_out_right)
				.replace(R.id.settings_activity_fragment_container, frag, null)
				.addToBackStack(null)
				.commit();
	}


	//Needed for custom prefs to work... ? I don't even
	@Override
	public void onDisplayPreferenceDialog(Preference preference) {
		DialogFragment fragment;
		if (preference instanceof NumberPickerPreference || preference instanceof FloatNumberPickerPreference) {
			fragment = NumberPickerPreferenceFragment.newInstance(preference);
			fragment.setTargetFragment(this, 0);
			fragment.show(getFragmentManager(),
					"android.support.v7.preference.PreferenceFragment.DIALOG");

		}
		else if (preference instanceof OpacityPreference)
		{
			fragment = OpacityPreferenceFragment.newInstance(preference);
			fragment.setTargetFragment(this, 0);
			fragment.show(getFragmentManager(),
					"android.support.v7.preference.PreferenceFragment.DIALOG");
		}
		else if (preference instanceof DualNumberPickerPreferenceFragment.DualNumberPreference)
		{
			fragment = DualNumberPickerPreferenceFragment.newInstance( (DualNumberPickerPreferenceFragment.DualNumberPreference) preference);
			fragment.setTargetFragment(this, 0);
			fragment.show(getFragmentManager(),
					"android.support.v7.preference.PreferenceFragment.DIALOG");
		}
		else super.onDisplayPreferenceDialog(preference);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		getActivity().setTitle(R.string.settings_title);
	}
}
