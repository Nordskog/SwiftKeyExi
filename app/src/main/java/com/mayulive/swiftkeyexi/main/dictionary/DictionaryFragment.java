package com.mayulive.swiftkeyexi.main.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.main.commons.PopupLinearLayout;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryShortcutItem;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryWordItem;
import com.mayulive.swiftkeyexi.main.emoji.EmojiFragment;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.util.DimenUtils;
import com.mayulive.swiftkeyexi.util.TextUtils;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.w3c.dom.Text;

import static com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates.DICTIONARY_SHORTCUT_TABLE_INFO;
import static com.mayulive.swiftkeyexi.settings.SettingsCommons.MODULE_SHARED_PREFERENCES_KEY;

public class DictionaryFragment extends Fragment
{
	private static String LOGTAG = ExiModule.getLogTag(DictionaryFragment.class);

	View mRootView = null;
	
	ListView mDictionaryListView = null;
	DictionaryAdapter mDictionaryAdapter = null;
	
	WrappedDatabase mDbWrap = null;

	TableList<DB_DictionaryShortcutItem> mItems = null;
	Map<String, DB_DictionaryShortcutItem> mMap = new HashMap<>();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setupReferences();

		if (mItems == null)
		{
			mItems = new TableList<DB_DictionaryShortcutItem>(mDbWrap, DICTIONARY_SHORTCUT_TABLE_INFO);
			for (DB_DictionaryShortcutItem item : mItems)
			{
				item.sort();
				mMap.put(item.get_key(), item);
			}

			sortByKey();
		}
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
        // Inflate the layout for this fragment
    	mRootView = inflater.inflate(R.layout.dictionary_fragment_layout, container, false);

    	setupContent();
    	setupButtons();	
        
        return mRootView;
    }

    ///////////////////
	//Misc
	//////////////////

	private void setLastUpdateTime()
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext(), MODULE_SHARED_PREFERENCES_KEY);
		editor.putLong(PreferenceConstants.pref_dictionary_last_update_key, System.currentTimeMillis());
		editor.apply();
	}

	private static long getTime()
	{
		return System.currentTimeMillis();
	}

    ///////////////////
	//Setup
	////////////////////

	private void setupReferences()
	{
		mDbWrap = DatabaseHolder.getWrapped(this.getContext());
	}

	private void setupContent()
	{

		mDictionaryListView = (ListView)mRootView.findViewById(R.id.dictionaryListView);

		mDictionaryAdapter = new DictionaryAdapter(getActivity(),mItems);

		mDictionaryListView.setAdapter(mDictionaryAdapter);

		mDictionaryAdapter.notifyDataSetChanged();
	}

	private void setupButtons()
	{
		FloatingActionButton addShortcutButton = (FloatingActionButton) mRootView.findViewById(R.id.addShortcutButton);
		addShortcutButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				displayWordDialog(null, null);
			}

		});


		final FloatingActionButton menuButton = (FloatingActionButton)mRootView.findViewById(R.id.dictionaryPanelMenuButton);

		menuButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//Switching to linearpopuplayout for consistency. Stock popupmenu doesn't have have shadows or anything.
				//TODO make generic
				final PopupLinearLayout dictionaryPopup = new PopupLinearLayout(DictionaryFragment.this.getContext());
				NavigationView dictionaryMenuView = new NavigationView(DictionaryFragment.this.getContext());

				dictionaryMenuView.inflateMenu(R.menu.dictionary_popup_menu);

				{
					//Add margin so there's space to display the shadow. PopupLinearLayout is supposed to be elevated, but doesn't work.
					//So the shadow needs to be displayed by the navigation view /inside/ the popup
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

					//Should be enough
					float shadowMargin = DimenUtils.calculatePixelFromDp(DictionaryFragment.this.getContext(), 30);

					params.setMargins((int)shadowMargin,0,0, (int)shadowMargin);
					dictionaryPopup.addItem(dictionaryMenuView, params);

					//Compensate for margin. This leaves a gap, but is visually superior.
					dictionaryPopup.setOffset( 0, (int)(shadowMargin/2f) );
				}

				dictionaryMenuView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
				{
					@Override
					public boolean onNavigationItemSelected(@NonNull MenuItem item)
					{
						switch(item.getItemId())
						{
							case R.id.clear_menu_item:
							{

								showDictionaryClearConfirmationDialog();

								break;
							}

							case R.id.import_menu_item:
							{
								showLoadDictionaryFromFileDialog();
								break;
							}

							default:
								break;
						}

						dictionaryPopup.dismiss();


						return true;
					}
				});


				dictionaryPopup.showAbove(menuButton);
			}

		});


		mDictionaryListView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				//Get the clicked entry
				DictionaryAdapter.DictionaryItemHolder wrappedItem = mDictionaryAdapter.getItemAtPosition(position);  //mItems.get(position);
				if (wrappedItem.isHeader)
				{
					displayShortcutDialog(wrappedItem.shortcutItem, wrappedItem.position);

				}
				else
				{
					displayWordDialog(wrappedItem.wordItem, wrappedItem.shortcutItem.get_key());

				}
			}

		});
	}



    ////////////////////
	//Dialogs
	////////////////////

	void showDictionaryClearConfirmationDialog()
	{
		new AlertDialog.Builder(getContext())

				.setTitle(R.string.dictionary_clear_dictionary)
				.setMessage(R.string.action_warning_message)
				.setPositiveButton(R.string.button_clear, new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialogInterface, int i)
					{
						setLastUpdateTime();
						mItems.clear();
						mMap.clear();
						mDictionaryAdapter.notifyDataSetChanged();
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

	private void displayShortcutDialog(DB_DictionaryShortcutItem item, int index)
	{
		EditDictionaryShortcutDialog dialog = new EditDictionaryShortcutDialog(getContext(),item)
		{
			@Override
			public void onEntrySaved(DB_DictionaryShortcutItem item, String oldKey)
			{
				if (!item.get_key().equalsIgnoreCase(oldKey))
				{
					DB_DictionaryShortcutItem existingShortcut = mMap.get(item.get_key());
					if (existingShortcut != null)
					{
						//Changed key to a key that already exists.
						//Move all items there and remove self.
						duplicateWords(item,existingShortcut);
						mItems.remove(item);
					}
					else
					{
						//Else set key on item and update map
						mItems.update(item);
						mMap.put(item.get_key(), item);
					}

					//Always remove original key
					mMap.remove(oldKey);
				}
				else
				{
					mItems.update(item);
				}

				mDictionaryAdapter.notifyDataSetChanged();
				setLastUpdateTime();
			}

			@Override
			public void onEntryDeleted(DB_DictionaryShortcutItem item, String oldKey)
			{
				mMap.remove(oldKey);
				mItems.remove(item);
				setLastUpdateTime();

				mDictionaryAdapter.notifyDataSetChanged();
			}
		};

		dialog.show();
	}


	private void displayWordDialog(DB_DictionaryWordItem item, final String key)
	{
		EditDictionaryWordDialog dialog = new EditDictionaryWordDialog(getContext(), item, key)
		{

			@Override
			public void onEntrySaved(DB_DictionaryWordItem item, String newKey, String oldKey)
			{

				if (oldKey == null)
				{
					addEntry(item,newKey);
				}
				else
				{
					updateEntry(item,newKey, oldKey);
				}

				setLastUpdateTime();
				mDictionaryAdapter.notifyDataSetChanged();
			}

			@Override
			public void onEntryDeleted(DB_DictionaryWordItem item, String key)
			{
				deleteEntry(item,key);
				setLastUpdateTime();
				mDictionaryAdapter.notifyDataSetChanged();
			}
		};
		dialog.show();
	}


	//////////////////////////////
	//Entry and item shenanigans
	////////////////////////////

	//Move all words from old to new
	private void duplicateWords(DB_DictionaryShortcutItem oldShortcut, DB_DictionaryShortcutItem newShortcut)
	{

		long newPriority = getTime();

		mItems.startBatchEdit();

		for (DB_DictionaryWordItem wordItem : oldShortcut.get_items())
		{
			newShortcut.add_item( new DB_DictionaryWordItem(-1, wordItem.get_text(), newPriority ) );
		}

		mItems.remove(oldShortcut);

		mItems.endBatchEdit();

		newShortcut.sort();
	}


	private void addEntry(DB_DictionaryWordItem wordItem, String key)
	{
		wordItem.set_priority(getTime());

		if (!key.isEmpty())
		{
			DB_DictionaryShortcutItem shortcutItem =  mMap.get(key);

			if (shortcutItem == null)
			{
				//Create new
				shortcutItem = new DB_DictionaryShortcutItem(-1, key);
				mItems.add( shortcutItem );
				mMap.put(key, shortcutItem);
			}

			shortcutItem.add_item(wordItem);

			shortcutItem.sort();

			mDictionaryAdapter.notifyDataSetChanged();

		}
	}

	private void updateEntry(DB_DictionaryWordItem wordItem, String key, String oldKey)
	{
		wordItem.set_priority(getTime());

		if (!key.isEmpty())
		{
			//Check key change
			if ( !key.equalsIgnoreCase(oldKey))
			{
				DB_DictionaryShortcutItem shortcutItem =  mMap.get(oldKey);
				if (shortcutItem == null)
				{
					Log.e(LOGTAG, "Attempted to update item that does not exist");
				}
				else
				{
					shortcutItem.get_items().remove(wordItem);
					if (shortcutItem.get_items().size() < 1)
					{
						mItems.remove(shortcutItem);
						mMap.remove(oldKey);
					}
				}

				addEntry(wordItem, key);
			}
			else	//Key unchanged
			{
				DB_DictionaryShortcutItem shortcutItem =  mMap.get(key);

				if (shortcutItem == null)
				{
					Log.e(LOGTAG, "Attempted to update item that does not exist");
				}
				else
				{
					shortcutItem.get_items().update(wordItem);
					shortcutItem.sort();
				}

			}

			mDictionaryAdapter.notifyDataSetChanged();
		}
	}


	private void deleteEntry(DB_DictionaryWordItem wordItem, String key)
	{
		if (!key.isEmpty())
		{
			DB_DictionaryShortcutItem shortcutItem = mMap.get(key);
			if (shortcutItem == null)
			{
				Log.e(LOGTAG, "Attempted to update item that does not exist");
			} else
			{
				shortcutItem.get_items().remove(wordItem);

				if (shortcutItem.get_items().size() < 1)
				{
					mItems.remove(shortcutItem);
					mMap.remove(key);
				}
			}
		}

		mDictionaryAdapter.notifyDataSetChanged();
	}

    //Sort current items by key, then by priority
    private void sortByKey()
    {
    	Collections.sort(mItems, new DB_DictionaryShortcutItem.DictionaryShortcutComparator());
    }


    /////////////
    //Other
	/////////////

    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

		//Resultcode is always -1 ...
		if (requestCode == READ_REQUEST_CODE) // && resultCode == Activity.RESULT_OK)
		{
			// The document selected by the user won't be returned in the intent.
			// Instead, a URI to that document will be contained in the return intent
			// provided to this method as a parameter.
			// Pull that URI using resultData.getData().
			Uri uri = null;
			if (data != null)
			{
				uri = data.getData();
				loadDictionaryFromFile(uri);
			}
			else
			{
				Log.e(LOGTAG, "Could not import directory because ActivityResult data was null");
			}
		}
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

	private static final int READ_REQUEST_CODE = 42;

	void showLoadDictionaryFromFileDialog()
	{
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("text/plain");

		startActivityForResult(intent, READ_REQUEST_CODE);
	}


	private static final Pattern WHITESPACE = Pattern.compile("\\s+");

	void loadDictionaryFromFile(Uri filePath)
	{
		setLastUpdateTime();
		mDbWrap.beginTransaction();

		try
		{
			BufferedReader in = new BufferedReader (new InputStreamReader( getContext().getContentResolver().openInputStream(filePath)) );

			String line = null;

			long newPriority = System.currentTimeMillis();

			while ( (line = in.readLine() ) != null)
			{
				String[] split = WHITESPACE.split(line,2);


				if (split.length == 2)
				{
					String text = TextUtils.stripBom( split[1].trim() );
					String key = TextUtils.stripBom( split[0].trim().toLowerCase() );

					DB_DictionaryWordItem newEntry = new DB_DictionaryWordItem(-1,text);
					newEntry.set_priority(newPriority);

					addEntry(newEntry,key);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			mDbWrap.setTransactionSuccessful();
			mDbWrap.endTransaction();
			sortByKey();
			setLastUpdateTime();
			mDictionaryAdapter.notifyDataSetChanged();
		}
	}
}
