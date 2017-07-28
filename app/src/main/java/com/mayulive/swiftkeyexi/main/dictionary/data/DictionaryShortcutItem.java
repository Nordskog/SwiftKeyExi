package com.mayulive.swiftkeyexi.main.dictionary.data;

import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.database.TableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Roughy on 3/16/2017.
 */

public class DictionaryShortcutItem
{
	private int _id = -1;
	private boolean _insertSecondary = false;
	private String _key = "";

	protected TableList<DB_DictionaryWordItem> _items = new TableList<>();

	public DictionaryShortcutItem(){};

	public DictionaryShortcutItem(String key)
	{
		set_key(key);
	}

	public DictionaryShortcutItem(String key, boolean insertSecondary)
	{
		set_key(key);
		setInsertSecondary(insertSecondary);
	}

	public void add_item(DB_DictionaryWordItem item)
	{
		_items.add(item);
	}

	public void clear_items()
	{
		_items.clear();
	}


	public TableList<DB_DictionaryWordItem> get_items() {
		return _items;
	}


	public void set_items(ArrayList<DB_DictionaryWordItem> _items)
	{
		throw new UnsupportedOperationException();
	}


	public String get_key() {
		return _key;
	}

	public void set_key(String _key) {
		this._key = _key;
	}


	public boolean getInsertSecondary()
	{
		return _insertSecondary;
	}

	public void setInsertSecondary(boolean insertSecondary)
	{
		this._insertSecondary = insertSecondary;
	}

	public void sort()
	{
		Collections.sort(_items, new DictionaryWordItem.DictionaryWordComparatorInverse());
	}

	public void moveToTop(DB_DictionaryWordItem item)
	{
		_items.moveToTop(item);
	}

	public void updateIfPriorityGreaterThan(long priority)
	{
		DatabaseWrapper dbWrap = _items.getDatabaseWrapper();
		if (dbWrap != null)
		{
			for (DB_DictionaryWordItem word : _items)
			{
				if (word.get_priority() > priority)
				{
					//Only update priority field
					DatabaseMethods.updateItem(dbWrap, word, _items.getTableInfo(), DB_DictionaryWordItem.DictionaryWordEntry.PRIORITY_COLUMN );
				}
			}
		}



	}

	public static class DictionaryShortcutComparator implements Comparator<DB_DictionaryShortcutItem>
	{
		@Override
		public int compare(DB_DictionaryShortcutItem a, DB_DictionaryShortcutItem b)
		{
			return a.get_key().compareToIgnoreCase(b.get_key());
		}
	}

}
