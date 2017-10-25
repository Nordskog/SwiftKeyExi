package com.mayulive.swiftkeyexi.main.emoji.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;

import com.mayulive.swiftkeyexi.database.DatabaseItem;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;

public class DB_EmojiItem extends EmojiItem implements DatabaseItem
{

	private int _id = -1;

	public DB_EmojiItem copy()
	{
		return new DB_EmojiItem(this);
	}

	public DB_EmojiItem(){ super(); };

	public DB_EmojiItem(DB_EmojiItem other)
	{
		super(other);
		this.set_id(-1);
	}

	public DB_EmojiItem(int id, String text, int style, EmojiType type)
	{
		super(text,style,type);
		set_id(id);
	}

	public DB_EmojiItem(int id, String text, int style)
	{
		super(text,style);
		set_id(id);
	}

	public DB_EmojiItem(String text, EmojiType type)
	{
		super(text,type);
	}

	//Panels will contain emoji items, but will store them as strings
	//Type is usually determined on load from database
	public DB_EmojiItem(String text, int style)
	{
		set_text(text);
		set_style(style);

		set_type( DB_EmojiItem.EmojiType.getType(text) );

		//Log.e("###", get_text()+", Type: "+get_type());
	}

	public DB_EmojiItem(String text)
	{
		set_text(text);
		set_type( DB_EmojiItem.EmojiType.getType(text) );
	}

	@Override
	public int get_id() {
		return _id;
	}

	@Override
	public void set_id(int _id) {
		this._id = _id;
	}


	/* Inner class that defines the table contents */
    public static class EmojiEntry implements BaseColumns
    {
        public static final String TEXT_COLUMN = "text";
        public static final String STYLE_COLUMN = "style";
		public static final String TYPE_COLUMN = "type";
		public static final String VARIANTS_COLUMN = "variants";
		public static final String MODIFIERS_SUPPORTED_COLUMN = "modifiers_supported";
    }
	//}
	
	public static final String[] PROJECTION =
	{
			EmojiEntry._ID,
			EmojiEntry.TEXT_COLUMN,
			EmojiEntry.STYLE_COLUMN,
			EmojiEntry.TYPE_COLUMN,
			EmojiEntry.VARIANTS_COLUMN,
			EmojiEntry.MODIFIERS_SUPPORTED_COLUMN
	};

	public static final String DEFINITION =
			"("+
					EmojiEntry._ID +" INTEGER PRIMARY KEY, " +
					EmojiEntry.TEXT_COLUMN +" TEXT, " +
					EmojiEntry.STYLE_COLUMN +" NUMBER, " +
					EmojiEntry.TYPE_COLUMN +" TEXT, " +
					EmojiEntry.VARIANTS_COLUMN +" TEXT, " +
					EmojiEntry.MODIFIERS_SUPPORTED_COLUMN +" INTEGER " +
			");";

	@Override
	public ContentValues getValues(boolean includeId)
	{
		ContentValues values = new ContentValues();
		if(includeId)
			values.put(BaseColumns._ID, get_id());
		values.put(EmojiEntry.TEXT_COLUMN, get_text());
		values.put(EmojiEntry.STYLE_COLUMN, get_style());
		values.put(EmojiEntry.TYPE_COLUMN, get_type().toString());
		values.put(EmojiEntry.VARIANTS_COLUMN, get_variants());
		values.put(EmojiEntry.MODIFIERS_SUPPORTED_COLUMN, get_modifiers_supported() ? 1 : 0);

		return values;
	}


	@Override
	public void populate(DatabaseWrapper dbWrap, Cursor c)
	{
		//Surely these are static for all rows
		int idColumnIndex = c.getColumnIndex(BaseColumns._ID);
		int textColumnIndex = c.getColumnIndex(EmojiEntry.TEXT_COLUMN);
		int styleColumnIndex = c.getColumnIndex(EmojiEntry.STYLE_COLUMN);
		int typeColumnIndex = c.getColumnIndex(EmojiEntry.TYPE_COLUMN);
		int variantsColumnIndex = c.getColumnIndex(EmojiEntry.VARIANTS_COLUMN);
		int intModifiersSupportedColumn = c.getColumnIndex(EmojiEntry.MODIFIERS_SUPPORTED_COLUMN);
		
		set_id( c.getInt(idColumnIndex) );
		set_text( c.getString(textColumnIndex) );
		set_style( c.getInt(styleColumnIndex) );
		set_type( EmojiType.valueOf( c.getString(typeColumnIndex) ) );
		set_variants( c.getString(variantsColumnIndex) );
		set_modifiers_supported( c.getInt(intModifiersSupportedColumn) != 0 );

	}

	@Override
	public void createChildTables(DatabaseWrapper dbWrap, String parentTable)
	{


	}

	@Override
	public void deleteChildTables()
	{


	}

	@Override
	public boolean hasChildTables()
	{
		return false;
	}

	@Override
	public DatabaseItem getNewInstance() 
	{
		return new DB_EmojiItem();
	}
}