package com.mayulive.swiftkeyexi.main.dictionary.data;

import java.util.Comparator;

/**
 * Created by Roughy on 4/29/2017.
 */

public class DictionaryWordItem
{
	private long _priority = 0;
	private String _text = "";

	public DictionaryWordItem(){};

	public DictionaryWordItem(String text)
	{
		set_text(text);
	}

	public DictionaryWordItem(String text, long priority)
	{
		set_text(text);
		set_priority(priority);
	}

	public String get_text() {
		return _text;
	}

	public void set_text(String _text) {
		this._text = _text;
	}

	public long get_priority() {
		return _priority;
	}

	public void set_priority(long _priority) {
		this._priority = _priority;
	}

	public static class DictionaryWordComparator implements Comparator<DictionaryWordItem>
	{
		@Override
		public int compare(DictionaryWordItem a, DictionaryWordItem b)
		{
			if (a.get_priority() == b.get_priority())
				return 0;
			else if (a.get_priority() < b.get_priority())
				return -1;
			else
				return 1;
		}
	}

	public static class DictionaryWordComparatorInverse implements Comparator<DictionaryWordItem>
	{
		@Override
		public int compare(DictionaryWordItem a, DictionaryWordItem b)
		{
			if (a.get_priority() == b.get_priority())
				return 0;
			else if (a.get_priority() < b.get_priority())
				return 1;
			else
				return -1;
		}
	}

}
