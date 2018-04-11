package com.mayulive.swiftkeyexi.main.shortcutkeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mayulive.swiftkeyexi.MainActivity;
import com.mayulive.swiftkeyexi.main.commons.data.ModifierKeyItem;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;

/**
 * Created by Roughy on 2/13/2017.
 */

public class ShortcutkeysFragment extends Fragment
{
	View mRootView = null;

	DatabaseWrapper mDbWrap;

	ListView mListView = null;

	ShortcutkeysAdapter mShortcutsAdapter = null;

	TableList<DB_ModifierKeyItem> mItems = null;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setupReferences();
		loadItems();
	}

	private void loadItems()
	{
		if (mItems == null)
		{
			mItems = new TableList<>(mDbWrap, TableInfoTemplates.MODIFIER_KEY_TABLE_INFO);
		}

		if (mItems.sync())
		{
			if (mShortcutsAdapter != null)
				mShortcutsAdapter.notifyDataSetChanged();
		}
	}

	private void setupReferences()
	{
		mDbWrap = DatabaseHolder.getWrapped(this.getContext());


	}

	private void setLastUpdateTime()
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext(), SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);
		editor.putLong(PreferenceConstants.pref_hotkeys_last_update_key, System.currentTimeMillis());
		editor.apply();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		// Inflate the layout for this fragment
		mRootView = inflater.inflate(R.layout.shortcutkeys_fragment_layout, container, false);

		mListView = (ListView)mRootView.findViewById(R.id.shortcutkey_listview);


		mShortcutsAdapter = new ShortcutkeysAdapter(mRootView.getContext(), mItems);
		mListView.setAdapter(mShortcutsAdapter);


		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				displayEntryDialog(mItems.get(position), position);
			}
		});

		View keyboardButton = mRootView.findViewById(R.id.testkeyboardbutton);
		keyboardButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				((MainActivity)getActivity()).displayInputTest();
			}
		});

		FloatingActionButton addButton = (FloatingActionButton)mRootView.findViewById(R.id.addShortcutButton);
		addButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				displayEntryDialog(null, -1);
			}
		});


		return mRootView;
	}

	private void displayEntryDialog(DB_ModifierKeyItem item, int index)
	{

		shortcutkeysEntryDialog dialog = new shortcutkeysEntryDialog(getContext(), getChildFragmentManager(), item)
		{

			@Override
			public void onEntrySaved(ModifierKeyItem item)
			{

				DB_ModifierKeyItem aItem = (DB_ModifierKeyItem)item;

				if (aItem.get_id() < 0)
				{
					mItems.add(aItem);
				}
				else
				{
					mItems.update(aItem);
				}

				setLastUpdateTime();
				mShortcutsAdapter.notifyDataSetChanged();
			}

			@Override
			public void onEntryDeleted(ModifierKeyItem item)
			{
				DB_ModifierKeyItem aItem = (DB_ModifierKeyItem)item;

				mItems.remove(aItem);
				setLastUpdateTime();
				mShortcutsAdapter.notifyDataSetChanged();
			}
		};
		dialog.show();
	}

	public void onResume()
	{
		super.onResume();
		loadItems();
	}

}