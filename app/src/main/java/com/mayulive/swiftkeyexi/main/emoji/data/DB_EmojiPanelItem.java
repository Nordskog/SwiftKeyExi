package com.mayulive.swiftkeyexi.main.emoji.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.mayulive.swiftkeyexi.database.DatabaseItem;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.database.TableInfo;

public class DB_EmojiPanelItem extends EmojiPanelItem implements DatabaseItem
{

	private int _id = -1;

	public DB_EmojiPanelItem(){super();};

	public DB_EmojiPanelItem(DB_EmojiPanelItem other)
	{
		this(other.get_id(), other.get_index(), other.get_column_width(), new String(other.get_caption()), new String(other.get_icon()), other.get_style(), other.get_icon_style());

		this.set_source(other.get_source());

		for (DB_EmojiItem currentItem : other.get_items())
		{
			//Emoji items in a panel should use the panel style, not the item style.
			this._items.add( currentItem.copy());
		}
	}

	public DB_EmojiPanelItem(int id, int index, int colWidth, String caption, String icon, int style, int iconStyle)
	{
		super(index,colWidth,caption, icon,style, iconStyle);
		set_id(id);
	
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}


	public static class EmojiPanelContract implements BaseColumns
    {
        public static final String INDEX_COLUMN = "arrayIndex";
        public static final String COLUMN_WIDTH_COLUMN = "column_width";
        public static final String CAPTION_COLUMN = "caption";
        public static final String ICON_COLUMN = "icon";
		public static final String STYLE_COLUMN = "style";
		public static final String ICON_STYLE_COLUMN = "icon_style";
		public static final String TYPE_COLUMN = "type";
		public static final String SOURCE_COLUMN = "source";
        public static final String ITEMS_TABLE_COLUMN = "items";
    }

	public static final String[] PROJECTION =
	{
			EmojiPanelContract._ID,
			EmojiPanelContract.INDEX_COLUMN,
			EmojiPanelContract.COLUMN_WIDTH_COLUMN,
			EmojiPanelContract.CAPTION_COLUMN,
			EmojiPanelContract.ICON_COLUMN,
			EmojiPanelContract.STYLE_COLUMN,
			EmojiPanelContract.ICON_STYLE_COLUMN,

			EmojiPanelContract.TYPE_COLUMN,
			EmojiPanelContract.SOURCE_COLUMN,

			EmojiPanelContract.ITEMS_TABLE_COLUMN
	};
	
	public static final String DEFINITION =
			"("+
					EmojiPanelContract._ID +" INTEGER PRIMARY KEY, " +
					EmojiPanelContract.INDEX_COLUMN +" NUMBER, " +
					EmojiPanelContract.COLUMN_WIDTH_COLUMN +" NUMBER, " +
					EmojiPanelContract.CAPTION_COLUMN +" TEXT, " +
					EmojiPanelContract.ICON_COLUMN +" TEXT, " +
					EmojiPanelContract.STYLE_COLUMN +" NUMBER, " +
					EmojiPanelContract.ICON_STYLE_COLUMN +" NUMBER, " +
					EmojiPanelContract.TYPE_COLUMN +" TEXT, " +
					EmojiPanelContract.SOURCE_COLUMN +" TEXT, " +
					EmojiPanelContract.ITEMS_TABLE_COLUMN +" TEXT " +
			");";

	
	@Override
	public ContentValues getValues(boolean includeId)
	{
		ContentValues values = new ContentValues();
		if(includeId)
			values.put(BaseColumns._ID, get_id());
		values.put(EmojiPanelContract.INDEX_COLUMN, get_index());
		values.put(EmojiPanelContract.COLUMN_WIDTH_COLUMN, get_column_width());
		values.put(EmojiPanelContract.CAPTION_COLUMN, get_caption());	
		values.put(EmojiPanelContract.ICON_COLUMN, get_icon());
		values.put(EmojiPanelContract.STYLE_COLUMN, get_style());
		values.put(EmojiPanelContract.ICON_STYLE_COLUMN, get_icon_style());

		values.put(EmojiPanelContract.TYPE_COLUMN, get_type().toString());
		values.put(EmojiPanelContract.SOURCE_COLUMN, get_source().toString());

		values.put(EmojiPanelContract.ITEMS_TABLE_COLUMN, _items.getTableName());
		
		return values;
	}




	@Override
	public void populate(DatabaseWrapper dbWrap, Cursor c)
	{
		//Surely these are static for all rows
		int idColumnIndex = c.getColumnIndex(BaseColumns._ID);
		int indexColumnIndex = c.getColumnIndex(EmojiPanelContract.INDEX_COLUMN);
		int columnWidthIndex = c.getColumnIndex(EmojiPanelContract.COLUMN_WIDTH_COLUMN);
		int captionColumnIndex = c.getColumnIndex(EmojiPanelContract.CAPTION_COLUMN);
		int iconsColumnIndex = c.getColumnIndex(EmojiPanelContract.ICON_COLUMN);
		int styleColumnIndex = c.getColumnIndex(EmojiPanelContract.STYLE_COLUMN);
		int iconStyleColumn = c.getColumnIndex(EmojiPanelContract.ICON_STYLE_COLUMN);
		int items_table_ColumnIndex = c.getColumnIndex(EmojiPanelContract.ITEMS_TABLE_COLUMN);

		int typeColumnIndex = c.getColumnIndex(EmojiPanelContract.TYPE_COLUMN);
		int sourceColumnIndex = c.getColumnIndex(EmojiPanelContract.SOURCE_COLUMN);
		
		set_id( c.getInt(idColumnIndex) );
		set_index( c.getInt(indexColumnIndex));
		set_column_width( c.getInt(columnWidthIndex) );
		set_caption( c.getString(captionColumnIndex) );	
		set_icon( c.getString(iconsColumnIndex) );
		set_style( c.getInt(styleColumnIndex));
		set_icon_style( c.getInt(iconStyleColumn));

		set_type( PANEL_TYPE.valueOf( c.getString(typeColumnIndex)  ) );
		set_source( PANEL_SOURCE.valueOf( c.getString(sourceColumnIndex)  ) );

		TableInfo itemsTableInfo = new TableInfo(new DB_EmojiItem(), DB_EmojiItem.PROJECTION, DB_EmojiItem.DEFINITION, c.getString(items_table_ColumnIndex));
		_items.populateFromDb(dbWrap, itemsTableInfo);

		
	}


	@Override
	public void createChildTables(DatabaseWrapper dbWrap, String parentTable)
	{
		if (_items.getTableInfo() != null)
		{
			throw new IllegalStateException("Attempted to create child tables when tables already exist. Existing: "+_items.getTableInfo().tableName+", new: "+(parentTable+"_items_"+get_id()));
		}

		TableInfo itemsTableInfo = new TableInfo(new DB_EmojiItem(), DB_EmojiItem.PROJECTION, DB_EmojiItem.DEFINITION, parentTable+"_items_"+get_id());
		_items.addToDb(dbWrap, itemsTableInfo);
	}

	@Override
	public void deleteChildTables()
	{
		if (_items.getTableInfo() != null)
		{
			//throw new IllegalStateException("Attempted to delete tables when none exist");

			//May be null if item not added to database yet
			_items.removeFromDb();
		}


	}

	@Override
	public boolean hasChildTables()
	{
		return true;
	}




	@Override
	public DatabaseItem getNewInstance() 
	{
		return new DB_EmojiPanelItem();
	}


	
}
