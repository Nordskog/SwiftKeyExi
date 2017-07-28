package com.mayulive.swiftkeyexi.main.emoji.data;

import com.mayulive.swiftkeyexi.util.FontLoader;


import static com.mayulive.swiftkeyexi.main.emoji.data.EmojiItem.EmojiType.TEXT;
import static com.mayulive.swiftkeyexi.util.FontLoader.containsEmoji;

/**
 * Created by Roughy on 3/16/2017.
 */

public class EmojiItem
{

	public enum EmojiType
	{
		TEXT, CONTAINS_EMOJI, IMAGE;

		public static EmojiType getType(String text)
		{
			if (containsEmoji(text) == FontLoader.GLYPH_TYPE.BITMAP)
				return CONTAINS_EMOJI;
			return TEXT;
		}
	}

	protected String _text = "";
	protected int _style = 0;
	protected EmojiType _type = TEXT;
	protected String _variants = "";

	public EmojiItem copy()
	{
		return new EmojiItem(_text,_style,_type);
	}

	public EmojiItem(){};

	public EmojiItem(EmojiItem other)
	{
		this.set_text(other.get_text());
		this.set_style( other.get_style());
		this.set_type(other.get_type());
	}

	public EmojiItem(String text, int style, EmojiType type)
	{
		set_text(text);
		set_style(style);
		set_type(type);
	}

	public EmojiItem(int id, String text, int style)
	{
		set_text(text);
		set_style(style);

		set_type( EmojiType.getType(text) );

		//Log.e("###", get_text()+", Type: "+get_type());

	}

	public EmojiItem(String text, EmojiType type)
	{
		set_text(text);
		set_type(type);
	}

	//Panels will contain emoji items, but will store them as strings
	//Type is usually determined on load from database
	public EmojiItem(String text, int style)
	{
		set_text(text);
		set_style(style);

		set_type( EmojiType.getType(text) );

		//Log.e("###", get_text()+", Type: "+get_type());
	}

	public EmojiItem(String text)
	{
		set_text(text);
		set_type( EmojiType.getType(text) );
	}

	public String get_text() {
		return _text;
	}

	public void set_variants(String variants)
	{
		this._variants = variants;
	}

	public String get_variants()
	{
		return this._variants;
	}

	public void set_text(String _text) {
		this._text = _text;
	}

	public int get_style()
	{
		return _style;
	}

	public void set_style(int _style)
	{
		this._style = _style;
	}


	public EmojiType get_type()
	{
		return _type;
	}

	public void set_type(EmojiType _type)
	{
		this._type = _type;
	}
}