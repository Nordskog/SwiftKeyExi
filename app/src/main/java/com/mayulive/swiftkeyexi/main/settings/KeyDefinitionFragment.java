package com.mayulive.swiftkeyexi.main.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.main.commons.data.DB_KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;

import java.util.ArrayList;

import static com.mayulive.swiftkeyexi.settings.SettingsCommons.MODULE_SHARED_PREFERENCES_KEY;

/**
 * Created by Roughy on 3/8/2017.
 */

public class KeyDefinitionFragment extends Fragment
{
	View mRootView = null;

	ListView mKeyListView = null;
	KeyDefinitionListAdapter mAdapter = null;

	WrappedDatabase mDbWrap = null;

	TableList<DB_KeyDefinition> mItems = null;

	public static final String KEY_DEFINITION_FRAGMENT_BUNDLE_KEY = "KEY_DEFINITION_FRAGMENT_BUNDLE_KEY";
	public static final int KEY_DEFINITION_FRAGMENT_SHIFT_KEY_BUNDLE_VALUE = 10;
	public static final int KEY_DEFINITION_FRAGMENT_DELETE_KEY_BUNDLE_VALUE = 20;
	public static final int KEY_DEFINITION_FRAGMENT_SYMBOL_KEY_BUNDLE_VALUE = 30;

	String mHeaderTitle = "";


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setupReferences();

		int bundleInput = this.getArguments().getInt(KEY_DEFINITION_FRAGMENT_BUNDLE_KEY, 0);

		if (bundleInput == KEY_DEFINITION_FRAGMENT_SHIFT_KEY_BUNDLE_VALUE)
		{
			mItems = new TableList<DB_KeyDefinition>(mDbWrap, TableInfoTemplates.ADDITIONAL_SHIFT_KEYS_TABLE_INFO);
			mHeaderTitle = getContext().getString(R.string.pref_additional_shift_keys_title);

		}
		else if (bundleInput == KEY_DEFINITION_FRAGMENT_SYMBOL_KEY_BUNDLE_VALUE)
		{
			mItems = new TableList<DB_KeyDefinition>(mDbWrap, TableInfoTemplates.ADDITIONAL_SYMBOL_KEYS_TABLE_INFO);
			mHeaderTitle = getContext().getString(R.string.pref_additional_symbol_keys_title);
		}
		else if (bundleInput == KEY_DEFINITION_FRAGMENT_DELETE_KEY_BUNDLE_VALUE)
		{
			mItems = new TableList<DB_KeyDefinition>(mDbWrap, TableInfoTemplates.ADDITIONAL_DELETE_KEYS_TABLE_INFO);
			mHeaderTitle = getContext().getString(R.string.pref_additional_delete_keys_title);
		}
		else
		{
			//Uhh....
			//mItems = new ArrayList<>();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		mRootView = inflater.inflate(R.layout.key_definition_list_fragment_layout, container, false);

		setupContent();
		setupButtons();

		return mRootView;
	}

	//////////
	//Setup
	/////////

	private void setupButtons()
	{
		FloatingActionButton addShortcutButton = (FloatingActionButton)mRootView.findViewById(R.id.addKeyButton);

		addShortcutButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				displayEntryDialog(null, -1);
			}

		});

		mKeyListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				displayEntryDialog(mItems.get(position),position);
			}

		});


	}

	private void setupReferences()
	{
		mDbWrap = DatabaseHolder.getWrapped(this.getContext());
	}

	private void setupContent()
	{

		mKeyListView = (ListView)mRootView.findViewById(R.id.keyListView);

		mAdapter = new KeyDefinitionListAdapter(mRootView.getContext(), mItems);

		//mDictionaryAdapter = new DictionaryAdapter(getActivity(),mItems);

		TextView header = (TextView)mRootView.findViewById(R.id.key_definition_type_header);
		header.setText(mHeaderTitle);


		mKeyListView.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
	}

	/////////////
	//Content
	/////////////

	private static class KeyDefinitionListAdapter extends BaseAdapter
	{
		ArrayList<? extends KeyDefinition> mItems = null;
		Context mContext = null;
		LayoutInflater mInflater = null;

		KeyDefinitionListAdapter(Context context,  ArrayList<? extends KeyDefinition> items)
		{
			mContext = context;
			mItems = items;
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount()
		{
			return mItems.size();
		}

		@Override
		public Object getItem(int position)
		{
			return mItems.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			if (convertView == null)
			{
				convertView = mInflater.inflate(R.layout.key_definition_list_item,parent,false);
			}

			TextView textview = (TextView)convertView.findViewById(R.id.key_defition_list_item_text);

			textview.setText(KeyDefinition.getKeyDefinitionDisplayString(mContext, mItems.get(position)));

			return convertView;
		}
	}

	/////////////////////////////
	//Entries and interactions
	/////////////////////////////

	private void displayEntryDialog(DB_KeyDefinition item, int index)
	{

		KeyDefinitionEntryDialog dialog = new KeyDefinitionEntryDialog(this.getContext(), item)
		{

			@Override
			public void onEntrySaved(DB_KeyDefinition item)
			{
				if (item.get_id() < 0)
				{
					mItems.add(item);
				}
				else
				{
					mItems.update(item);
				}

				mAdapter.notifyDataSetChanged();
			}

			@Override
			public void onEntryDeleted(DB_KeyDefinition item)
			{
				mItems.remove(item);
				mAdapter.notifyDataSetChanged();
			}
		};
		dialog.show();
	}



	////////////
	//Misc
	////////////

	private void setLastUpdateTime()
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext(), MODULE_SHARED_PREFERENCES_KEY);
		editor.putLong(PreferenceConstants.pref_additional_keys_last_update_key, System.currentTimeMillis());
		editor.apply();
	}

	@Override
	public void onPause()
	{
		//Set last time regardless of changes
		setLastUpdateTime();

		super.onPause();
	}
}
