package com.mayulive.swiftkeyexi.main.commons.data;

import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

/**
 * Created by Roughy on 3/16/2017.
 */

public class ModifierKeyItem implements Cloneable
{
	public enum HotkeyKeyType
	{
		SOFT, HARD
	}

	private String _key = "";
	private KeyboardInteraction.TextAction _textAction = KeyboardInteraction.TextAction.DEFAULT;
	private HotkeyKeyType _hotkey_type = HotkeyKeyType.SOFT;
	private HardKey _hard_key = new HardKey();
	private HardKey _hard_modifier_key = new HardKey();

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

	public HotkeyKeyType get_hotkey_type()
	{
		return _hotkey_type;
	}

	public void set_hotkey_type(HotkeyKeyType _hotkey_type)
	{
		this._hotkey_type = _hotkey_type;
	}

	public HardKey get_hard_key()
	{
		return _hard_key;
	}

	public void set_hard_key(HardKey _hard_key)
	{
		this._hard_key = _hard_key;
	}

	public HardKey get_hard_modifier_key()
	{
		return _hard_modifier_key;
	}

	public void set_hard_modifier_key(HardKey _hard_modifier_key)
	{
		this._hard_modifier_key = _hard_modifier_key;
	}

	public ModifierKeyItem clone()
	{
		ModifierKeyItem item = new ModifierKeyItem();

		item.set_key(get_key());
		item.set_textAction(get_textAction());
		item.set_hotkey_type(get_hotkey_type());
		item.set_hard_key(get_hard_key());
		item.set_hard_modifier_key(get_hard_modifier_key());

		return item;
	}

	public void set(ModifierKeyItem item)
	{
		this.set_key(item.get_key());
		this.set_textAction(item.get_textAction());
		this.set_hotkey_type(item.get_hotkey_type());
		this.set_hard_key(item.get_hard_key());
		this.set_hard_modifier_key(item.get_hard_modifier_key());


	}
}
