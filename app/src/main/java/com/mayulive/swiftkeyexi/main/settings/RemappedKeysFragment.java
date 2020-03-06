package com.mayulive.swiftkeyexi.main.settings;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.main.commons.data.DB_RemappedKey;
import com.mayulive.swiftkeyexi.main.commons.data.RemappedKey;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.util.KeyUtils;
import com.mayulive.swiftkeyexi.xposed.system.SystemIntentService;

import java.util.ArrayList;

/**
 * Created by Roughy on 3/20/2018.
 */

public class RemappedKeysFragment extends Fragment
{
	View mRootView = null;

	ListView mKeyListView = null;
	RemappedKeyListAdapter mAdapter = null;

	WrappedDatabase mDbWrap = null;

	TableList<DB_RemappedKey> mItems = null;

	String mHeaderTitle = "";

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setupReferences();

		mItems = new TableList<DB_RemappedKey>(mDbWrap, TableInfoTemplates.REMAPPED_HARDWAREKEYS_TABLE_INFO);
		mHeaderTitle = getContext().getString(R.string.pref_additional_delete_keys_title);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		mRootView = inflater.inflate(R.layout.remapped_keys_fragment_layout, container, false);

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

		mAdapter = new RemappedKeyListAdapter(mRootView.getContext(), mItems);

		mKeyListView.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
	}

	/////////////
	//Content
	/////////////

	private static class RemappedKeyListAdapter extends BaseAdapter
	{
		ArrayList<? extends RemappedKey> mItems = null;
		Context mContext = null;
		LayoutInflater mInflater = null;

		RemappedKeyListAdapter(Context context,  ArrayList<? extends RemappedKey> items)
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

			RemappedKey item = mItems.get(position);

			if (convertView == null)
			{
				convertView = mInflater.inflate(R.layout.remapped_key_list_item,parent,false);
			}

			Button fromKey = convertView.findViewById(R.id.remapped_key_from);
			Button toKey = convertView.findViewById(R.id.remapped_key_to);

			fromKey.setText(KeyUtils.getKeyString(mContext, item.getFrom_key() ));
			toKey.setText(KeyUtils.getKeyString(mContext, item.getTo_key() ));


			//TextView textview = (TextView)convertView.findViewById(R.id.key_defition_list_item_text);
			//textview.setText(KeyDefinition.getKeyDefinitionDisplayString(mContext, mItems.get(position)));

			return convertView;
		}
	}

	/////////////////////////////
	//Entries and interactions
	/////////////////////////////

	private void displayEntryDialog(DB_RemappedKey item, int index)
	{
		RemappedKeyDialog dialog = new RemappedKeyDialog(this.getContext(), item)
		{

			@Override
			public void onEntrySaved(DB_RemappedKey item)
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

				SystemIntentService.sendKeyMappingsUpdatedBroadcast(RemappedKeysFragment.this.getContext());
			}

			@Override
			public void onEntryDeleted(DB_RemappedKey item)
			{
				mItems.remove(item);
				mAdapter.notifyDataSetChanged();

				SystemIntentService.sendKeyMappingsUpdatedBroadcast(RemappedKeysFragment.this.getContext());
			}
		};
		dialog.show();

	}


	////////////
	//Misc
	////////////

	public void onResume()
	{
		if (mItems != null)
		{
			if (mItems.sync())
			{
				if (mAdapter != null)
					mAdapter.notifyDataSetChanged();
			}
		}

		super.onResume();

		getActivity().setTitle(R.string.pref_remapped_keys_title);

	}

	@Override
	public void onPause()
	{
		super.onPause();
	}


}
