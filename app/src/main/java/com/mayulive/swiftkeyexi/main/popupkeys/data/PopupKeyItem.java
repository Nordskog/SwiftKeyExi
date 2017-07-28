package com.mayulive.swiftkeyexi.main.popupkeys.data;

import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

/**
 * Created by Roughy on 3/16/2017.
 */

public class PopupKeyItem
{
	private int _insertIndex = -1;
	private String _popupLower = "";
	private String _popupUpper = "";
	private boolean _deleteExisting = false;
	private KeyboardInteraction.TextAction _textAction = KeyboardInteraction.TextAction.DEFAULT;

	public PopupKeyItem()
	{

	}

	public PopupKeyItem(int insertIndex, String popupKeyLower, String popupKeyUpper, KeyboardInteraction.TextAction action, boolean deleteExisting)
	{
		set_insertIndex(insertIndex);
		set_popupLower(popupKeyLower);
		set_popupUpper(popupKeyUpper);
		set_textAction(action);
		set_deleteExisting(deleteExisting);
	}

	public int get_insertIndex() {
		return _insertIndex;
	}

	public void set_insertIndex(int _insertIndex) {
		this._insertIndex = _insertIndex;
	}

	public String get_popupUpper() {
		return _popupUpper;
	}

	public void set_popupUpper(String _popupUpper) {
		this._popupUpper = _popupUpper;
	}

	public String get_popupLower() {
		return _popupLower;
	}

	public void set_popupLower(String _popupLower) {
		this._popupLower = _popupLower;
	}

	public KeyboardInteraction.TextAction get_textAction() {
		return _textAction;
	}

	public void set_textAction(KeyboardInteraction.TextAction _textAction) {
		this._textAction = _textAction;
	}

	public boolean is_deleteExisting()
	{
		return _deleteExisting;
	}

	public void set_deleteExisting(boolean _deleteExisting)
	{
		this._deleteExisting = _deleteExisting;
	}

}
