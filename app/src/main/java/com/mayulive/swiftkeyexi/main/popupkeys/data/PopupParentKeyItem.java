package com.mayulive.swiftkeyexi.main.popupkeys.data;

import com.mayulive.swiftkeyexi.database.TableList;

import java.util.ArrayList;

/**
 * Created by Roughy on 3/16/2017.
 */

public class PopupParentKeyItem
{
	protected String _parentKey = "";

	protected TableList<DB_PopupKeyItem> _items = new TableList<>();
	protected boolean _delete_existing = false;

	public PopupParentKeyItem(){};

	public PopupParentKeyItem(String key, boolean deleteExisting)
	{
		set_parentKey(key);
		set_delete_existing(deleteExisting);

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

	public boolean get_delete_existing()
	{
		return _delete_existing;
	}

	public void set_delete_existing(boolean _delete_existing)
	{
		this._delete_existing = _delete_existing;
	}
}
