package com.mayulive.swiftkeyexi.main.emoji.data;

import com.mayulive.swiftkeyexi.database.TableList;

import java.util.Comparator;

import static com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem.PANEL_SOURCE.USER;
import static com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem.PANEL_TYPE.TEXT;

/**
 * Created by Roughy on 3/16/2017.
 */

public class EmojiPanelItem
{

	public PANEL_TYPE get_type()
	{
		return _type;
	}

	public void set_type(PANEL_TYPE _type)
	{
		this._type = _type;
	}

	public PANEL_SOURCE get_source()
	{
		return _source;
	}

	public void set_source(PANEL_SOURCE _source)
	{
		this._source = _source;
	}

	public int get_panel_identifier()
	{
		return _panel_identifier;
	}

	public void set_panel_identifier(int _panel_identifier)
	{
		this._panel_identifier = _panel_identifier;
	}

	public enum PANEL_TYPE
	{
		TEXT, GIF
	}


	public enum PANEL_SOURCE
	{
		TEMPLATE, USER, RECENTS;

		public boolean isEditable()
		{
			return this != TEMPLATE && this != RECENTS;
		}

		public boolean isTemplateDeletable()
		{
			return this == USER;
		}

		public boolean isKeyboardDeleteable()
		{
			return this != RECENTS;
		}
	}

	protected int _index = -1;
	protected int _column_width = 1;
	protected String _caption = "";
	protected String _icon = "";
	protected int _style = 0;
	protected int _icon_style = 0;

	protected PANEL_TYPE _type = TEXT;
	protected PANEL_SOURCE _source = USER;
	private int _panel_identifier = -1;

	protected TableList<DB_EmojiItem> _items = new TableList<DB_EmojiItem>();

	public EmojiPanelItem(){};

	public EmojiPanelItem(EmojiPanelItem other)
	{
		this(other.get_index(), other.get_column_width(), new String(other.get_caption()), new String(other.get_icon()), other.get_style(), other.get_icon_style());
		for (DB_EmojiItem currentItem : other.get_items())
		{
			this._items.add( currentItem.copy() );
		}
	}

	public EmojiPanelItem(int index, int colWidth, String caption, String icon, int style, int iconStyle)
	{
		set_index(index);
		set_column_width(colWidth);
		set_caption(caption);
		set_icon(icon);
		set_style(style);
		set_icon_style(iconStyle);

	}

	public String get_icon() {
		return _icon;
	}

	public void set_icon(String _icon) {
		this._icon = _icon;
	}

	public String get_caption() {
		return _caption;
	}

	public void set_caption(String _caption) {
		this._caption = _caption;
	}

	public void clear_items()
	{
		_items.clear();
	}


	public int get_index() {
		return _index;
	}

	public void set_index(int _index) {
		this._index = _index;
	}

	public TableList<DB_EmojiItem> get_items() {
		return _items;
	}


	public void set_items(TableList<DB_EmojiItem> _items)
	{
		this._items = _items;
	}


	/**
	 * @return the _column_width
	 */
	public int get_column_width() {
		return _column_width;
	}

	/**
	 * @param _column_width the _column_width to set
	 */
	public void set_column_width(int _column_width) {
		this._column_width = _column_width;
	}

	public int get_style()
	{
		return _style;
	}

	public void set_style(int _style)
	{
		this._style = _style;
	}

	public int get_icon_style()
	{
		return _icon_style;
	}

	public void set_icon_style(int _icon_style)
	{
		this._icon_style = _icon_style;
	}

	public static class EmojiPanelComparator implements Comparator<EmojiPanelItem>
	{
		@Override
		public int compare(EmojiPanelItem a, EmojiPanelItem b)
		{
			if (a.get_index() == b.get_index())
				return 0;
			else if (a.get_index() < b.get_index())
				return -1;
			else
				return 1;
		}
	}

	public void trimItems(int maxCount)
	{
		while (_items.size() > maxCount)
		{
			_items.remove(_items.size()-1);
		}
	}

	public int findExistingItemIndex(DB_EmojiItem pattern)
	{
		//maybe do hashmap search, but there are so few entries it barely matters

		for (int i = 0; i < _items.size(); i++)
		{
			DB_EmojiItem currentItem = _items.get(i);

			if (currentItem.get_text().equals( pattern.get_text() ))
			{
				if (currentItem.get_style() == pattern.get_style())
				{
					return i;
				}
			}

		}

		return -1;
	}

	public void updateModifierSupport()
	{
		for (DB_EmojiItem item : _items)
		{
			item.set_modifiers_supported( EmojiModifiers.supportsModifiers(item.get_text()) );
		}
	}

}
