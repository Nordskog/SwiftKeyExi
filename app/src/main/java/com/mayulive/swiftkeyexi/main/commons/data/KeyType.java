package com.mayulive.swiftkeyexi.main.commons.data;

import android.content.Context;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;

/**
 * Created by Roughy on 8/1/2017.
 */
public enum KeyType
{
	//There are many other keys in addition to these.
	//They are weird duplicates (e.g. EmojiSwitchKey)
	//and things I don't understand at all (e.g ReturnLetterKey).
	//There are also keys I assume are legacy (e.g. EmojiEnterKey)
	//More obscure languages will likely also have keys not listed here.

	DEFAULT,            //Any other type
	SYMBOL,            //SymbolKey, LetterKey
	DELETE,                //DeleteKey
	SHIFT,                //ShiftKey
	SPACE,                //LanguageSwitchingSpaceKey, SpaceKey
	SWITCH_LAYOUT,        //SwitchLayoutKey. Note: There will be multiple of these. abc -> symbols -> symbols2
	EMOJI,                //EmojiLayoutKey
	ENTER,                //IMEGoKey
	PERIOD,            //PuncKey
	CLEAR_BUFFER_KEY,    //ClearBufferKey. 清空 key
	TAB;                //Tab

	public static KeyType getType(String tag)
	{
		if (tag.contains("SpaceKey"))
			return SPACE;
		if (tag.equals("SymbolKey") || tag.contains("LetterKey") || tag.contains("LayoutMappedLetterKey"))
			return SYMBOL;
		if (tag.contains("DeleteKey"))
			return DELETE;
		if (tag.contains("ShiftKey"))
			return SHIFT;
		if (tag.equals("SwitchLayoutKey"))
			return SWITCH_LAYOUT;
		if (tag.equals("EmojiLayoutKey"))
			return EMOJI;
		if (tag.equals("IMEGoKey") || tag.contains("EnterKey"))
			return ENTER;
		if (tag.equals("PuncKey"))
			return PERIOD;
		if (tag.equals("ClearBufferKey"))
			return CLEAR_BUFFER_KEY;
		if (tag.equals("Tab"))
			return TAB;

		return DEFAULT;
	}

	public static String getKeyDefinitionDisplayString(Context context, KeyType type)
	{

		switch (type)
		{
			case DEFAULT:
				return context.getResources().getString(R.string.keydefinition_default);
			case SYMBOL:
				return context.getResources().getString(R.string.keydefinition_symbol);
			case DELETE:
				return context.getResources().getString(R.string.keydefinition_delete);
			case SHIFT:
				return context.getResources().getString(R.string.keydefinition_shift);
			case SPACE:
				return context.getResources().getString(R.string.keydefinition_space);
			case SWITCH_LAYOUT:
				return context.getResources().getString(R.string.keydefinition_switch_layout);
			case EMOJI:
				return context.getResources().getString(R.string.keydefinition_emoji);
			case ENTER:
				return context.getResources().getString(R.string.keydefinition_enter);
			case PERIOD:
				return context.getResources().getString(R.string.keydefinition_period);
			case CLEAR_BUFFER_KEY:
				return context.getResources().getString(R.string.keydefinition_clearbuffer);
			case TAB:
				return context.getResources().getString(R.string.keydefinition_tab);
		}

		return "";
	}
}
