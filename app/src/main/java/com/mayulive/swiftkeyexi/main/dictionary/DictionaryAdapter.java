package com.mayulive.swiftkeyexi.main.dictionary;

import java.util.ArrayList;

import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryShortcutItem;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryWordItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DictionaryAdapter extends BaseAdapter
{
	private ArrayList<DB_DictionaryShortcutItem> mItems = null;
	private LayoutInflater mInflater = null;

	private ArrayList<DictionaryItemHolder> mListItems = new ArrayList<>();


	//Input array must always be sorted.
	public DictionaryAdapter(Context context, ArrayList<DB_DictionaryShortcutItem> sortedItems)
	{
		mItems = sortedItems;

		updateHeaders();

		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	//Super duper inefficient
	private void updateHeaders()
	{
		mListItems.clear();
		int headerCounter = 0;
		for (DB_DictionaryShortcutItem shortcutItem : mItems)
		{
			mListItems.add( new DictionaryItemHolder(shortcutItem, headerCounter));

			int originalItemCounter = 0;
			for (DB_DictionaryWordItem wordItem : shortcutItem.get_items())
			{
				mListItems.add( new DictionaryItemHolder(wordItem, shortcutItem, originalItemCounter) );

				originalItemCounter++;
			}

			headerCounter++;

		}

	}

	@Override
	public int getCount() 
	{
		return mListItems.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mListItems.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return mListItems.get(position).getId();
	}

	@Override
	public int getViewTypeCount()
	{
		return 2;
	}

	@Override
	public int getItemViewType(int position)
	{
		if (mListItems.get(position).isHeader)
			return 0;
		else
			return 1;
	}

	@Override
	public void notifyDataSetChanged()
	{
		updateHeaders();
		super.notifyDataSetChanged();


	}

	//Get the item at the given listview position, compensating for headers
	public DictionaryItemHolder getItemAtPosition(int position)
	{
		return mListItems.get(position);
	}

	//Get the index at the given listview position, compensating for headers
	public int getItemIndexFromPosition(int position)
	{
		DictionaryItemHolder item = mListItems.get(position);
		if (item.isHeader)
			return -1;
		else
			return item.position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{


		DictionaryItemHolder wrapper = mListItems.get(position);

		if (wrapper.isHeader)
		{

			if (convertView == null)
			{
				convertView = mInflater.inflate(R.layout.dictionary_list_header, parent,false);
			}

			TextView textValueView = (TextView)convertView.findViewById(R.id.titleText);
			TextView primarySecondaryView = (TextView)convertView.findViewById(R.id.primarySecondaryText);

			textValueView.setText( wrapper.shortcutItem.get_key() );

			if (!wrapper.shortcutItem.getInsertSecondary())
			{
				primarySecondaryView.setText( parent.getContext().getResources().getString( R.string.dictionary_primary_entry ) );
			}
			else
			{
				primarySecondaryView.setText( parent.getContext().getResources().getString( R.string.dictionary_secondary_entry ) );
			}

			return convertView;
		}
		else
		{

			DB_DictionaryWordItem entry = wrapper.wordItem;


			if (convertView == null)
			{
				convertView = mInflater.inflate(R.layout.dictionary_list_item, parent,false);
			}

			TextView textValueView = (TextView)convertView.findViewById(R.id.valueText);


			textValueView.setText( entry.get_text() );


			return convertView;
		}

	}

	protected static class DictionaryItemHolder
	{
		DB_DictionaryWordItem wordItem;
		DB_DictionaryShortcutItem shortcutItem;
		int position;

		boolean isHeader;

		DictionaryItemHolder(DB_DictionaryWordItem wordItem, DB_DictionaryShortcutItem shortcutItem,  int position)
		{
			this.shortcutItem = shortcutItem;
			this.wordItem = wordItem;
			isHeader = false;
			this.position = position;
		}

		DictionaryItemHolder(DB_DictionaryShortcutItem shortcutItem, int position)
		{
			this.shortcutItem = shortcutItem;
			isHeader = true;
		}

		int getId()
		{
			if (!isHeader)
				return System.identityHashCode(wordItem);
			else
				return System.identityHashCode(shortcutItem);
		}
	}

}
