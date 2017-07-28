package com.mayulive.swiftkeyexi.main.commons.data;

import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

/**
 * Created by Roughy on 3/16/2017.
 */

public class ModifierKeyItem
{
	private String _key = "";
	private KeyboardInteraction.TextAction _textAction = KeyboardInteraction.TextAction.DEFAULT;

	public ModifierKeyItem(){};

	public ModifierKeyItem(String key, KeyboardInteraction.TextAction action)
	{
		set_key(key);
		set_textAction(action);
	}


	public String get_key() {
		return _key;
	}

	public void set_key(String _key) {
		this._key = _key;
	}

	public KeyboardInteraction.TextAction get_textAction() {
		return _textAction;
	}

	public void set_textAction(KeyboardInteraction.TextAction _textAction) {
		this._textAction = _textAction;
	}
}
