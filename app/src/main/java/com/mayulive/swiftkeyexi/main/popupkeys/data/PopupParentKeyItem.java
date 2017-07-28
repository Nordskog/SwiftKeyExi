package com.mayulive.swiftkeyexi.main.popupkeys.data;

import com.mayulive.swiftkeyexi.database.TableList;

import java.util.ArrayList;

/**
 * Created by Roughy on 3/16/2017.
 */

public class PopupParentKeyItem
{
	private String _parentKey = "";

	protected TableList<DB_PopupKeyItem> _items = new TableList<>();

	public PopupParentKeyItem(){};

	public PopupParentKeyItem(String key)
	{
		set_parentKey(key);
	}

	public void add_item(DB_PopupKeyItem item)
	{
		_items.add(item);
	}

	public void clear_items()
	{
		_items.clear();
	}


	public TableList<DB_PopupKeyItem> get_items() {
		return _items;
	}


	public void set_items(ArrayList<DB_PopupKeyItem> _items)
	{
		throw new UnsupportedOperationException();
	}

	public String get_parentKey() {
		return _parentKey;
	}

	public void set_parentKey(String _parentKey) {
		this._parentKey = _parentKey;
	}

}
