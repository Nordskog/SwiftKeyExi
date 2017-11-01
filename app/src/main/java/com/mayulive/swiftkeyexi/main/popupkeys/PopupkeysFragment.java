package com.mayulive.swiftkeyexi.main.popupkeys;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.MainActivity;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupKeyItem;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.popupkeys.data.PopupParentKeyItem;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;

/**
 * Created by Roughy on 2/13/2017.
 */

public class PopupkeysFragment extends Fragment
{
	View mRootView = null;

	DatabaseWrapper mDbWrap;

	ListView mListView = null;

	PopupkeysAdapter mAdapter = null;

	TableList<DB_PopupParentKeyItem> mItems = null;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setupReferences();

		if (mItems == null)
		{
			mItems = new TableList<>(mDbWrap, TableInfoTemplates.POPUP_KEY_TABLE_INFO);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		mRootView = inflater.inflate(R.layout.popupkeys_fragment_layout, container, false);

		mListView = (ListView)mRootView.findViewById(R.id.popupkeys_listview);

		mAdapter = new PopupkeysAdapter(mRootView.getContext(), mItems);
		mListView.setAdapter(mAdapter);

		mAdapter.setOnKeyClickedListener(new PopupkeysPositionWidget.OnKeyClickedListener()
		{

			@Override
			public void onKeyClicked(int position, @Nullable PopupParentKeyItem parentItem)
			{
				DB_PopupKeyItem key =  getKeyWithPosition(parentItem,position);
				if (key != null)
				{
					displayKeyDialog( (DB_PopupParentKeyItem)parentItem,key, position );
				}
				else
				{
					//displayKeyDialog( (DB_PopupParentKeyItem)parentItem, new DB_PopupKeyItem(-1,position, "", "", KeyboardInteraction.TextAction.COPY, false) , position );
					displayKeyDialog( (DB_PopupParentKeyItem)parentItem,null, position );
				}
			}
		});

		mAdapter.setOnParentKeyClickedListener(new PopupkeysPositionWidget.OnKeyClickedListener()
		{
			@Override
			public void onKeyClicked(int position, @Nullable PopupParentKeyItem parentItem)
			{
				displayParentKeyDialog((DB_PopupParentKeyItem) parentItem);
			}
		});

		mAdapter.setOnAddKeyClickedListener(new PopupkeysPositionWidget.OnKeyClickedListener()
		{
			@Override
			public void onKeyClicked(int position, @Nullable PopupParentKeyItem parentItem)
			{
				displayKeyDialog( (DB_PopupParentKeyItem)parentItem,null, -1 );
			}
		});

		/////////////////
		//Buttons
		/////////////////

		View keyboardButton = mRootView.findViewById(R.id.testkeyboardbutton);
		keyboardButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				((MainActivity)getActivity()).displayInputTest();
			}
		});

		FloatingActionButton addButton = (FloatingActionButton)mRootView.findViewById(R.id.addPopupkeyButton);
		addButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				displayParentKeyDialog(null);
			}
		});



		return mRootView;
	}

	////////////
	//Misc
	///////////

	private void setLastUpdateTime()
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext(), SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);
		editor.putLong(PreferenceConstants.pref_popup_last_update_key, System.currentTimeMillis());
		editor.apply();
	}

	private DB_PopupKeyItem getKeyWithPosition( PopupParentKeyItem parent, int index)
	{
		for (DB_PopupKeyItem item : parent.get_items())
		{
			if (item.get_insertIndex() == index)
				return item;
		}

		return null;
	}

	///////////
	//Setup
	///////////

	private void setupReferences()
	{
		mDbWrap = DatabaseHolder.getWrapped(this.getContext());
	}

	//////////
	//Dialogs
	//////////

	private void displayKeyDialog(final DB_PopupParentKeyItem parent, final DB_PopupKeyItem item, int index)
	{
		popupkeysEntryDialog dialog = new popupkeysEntryDialog(getContext(), parent, item, index)
		{
			@Override
			public void onEntrySaved(DB_PopupKeyItem item)
			{
				if (item != null)
				{
					if (item.get_id() < 0)
					{
						parent.add_item(item);
					}
					else
					{
						parent.get_items().update(item);
					}
				}

				//Delete existing value may have changed
				mItems.update(parent);

				setLastUpdateTime();
				mAdapter.notifyDataSetChanged();
			}

			@Override
			public void onEntryDeleted(DB_PopupKeyItem item)
			{
				parent.get_items().remove(item);
				setLastUpdateTime();
				mAdapter.notifyDataSetChanged();
			}
		};
		dialog.show();
	}

	private void displayParentKeyDialog(final DB_PopupParentKeyItem parent)
	{
		popupkeysParentEntryDialog dialog = new popupkeysParentEntryDialog(this.getContext(),parent)
		{

			@Override
			public void onEntrySaved(DB_PopupParentKeyItem item, String oldKey)
			{


				//Key changed?
				DB_PopupParentKeyItem existingParent = null;
				if ( !item.get_parentKey().equalsIgnoreCase(oldKey))
				{
					for (DB_PopupParentKeyItem existingParentItem : mItems)
					{
						if (existingParentItem == item)
							continue;

						if (existingParentItem.get_parentKey().equalsIgnoreCase(item.get_parentKey()))
						{
							existingParent = existingParentItem;
							break;
						}
					}
				}


				if (item.get_id() < 0)
				{
					//Only add if no existing item with same key exists
					if (existingParent == null)
					{
						mItems.add(item);
						mAdapter.notifyDataSetChanged();
					}
				}
				else
				{
					//If we try to change the name of popup config to that of
					//an existing one, we should probably just fail. There may be
					//conflicting keys configured already.
					if (existingParent != null)
					{
						//Revert key
						item.set_parentKey(oldKey);
						new AlertDialog.Builder(
								getContext()).setTitle(R.string.popups_parent_change_failed_title)
								.setMessage(R.string.popups_parent_change_failed_message)
								.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener()
								{
									@Override
									public void onClick(DialogInterface dialog, int which)
									{
										dialog.dismiss();
									}
								})
								.create().show();
					}
					else
					{
						mItems.update(item);
					}
				}

				setLastUpdateTime();

			}

			@Override
			public void onEntryDeleted(DB_PopupParentKeyItem item)
			{
				mItems.remove(item);
				mAdapter.notifyDataSetChanged();
				setLastUpdateTime();
			}
		};

		dialog.show();



	}


}