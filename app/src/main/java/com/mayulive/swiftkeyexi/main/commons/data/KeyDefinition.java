package com.mayulive.swiftkeyexi.main.commons.data;

import android.content.Context;
import android.graphics.RectF;

/**
 * Created by Roughy on 3/16/2017.
 */ //A wrapper for swiftkey key items.
public class KeyDefinition
{
	public String content;
	public KeyType type;
	public RectF hitbox = new RectF(0,0,0,0);


	public KeyDefinition()
	{
		this.content = "";
		this.type = KeyType.DEFAULT;

	}

	public KeyDefinition(String content, KeyType type)
	{
		this(content,type, new RectF(0,0,0,0));
	}

	public KeyDefinition(String content, KeyType type, RectF hitbox)
	{
		this.content = content;
		this.type = type;
		this.hitbox = hitbox;
	}

	public boolean is(KeyType type)
	{
		return this.type == type;
	}

	/*
	@Override
	public String toString()
	{
		return content;
	}
	*/

	@Override
	public String toString()
	{
		return "Type: " + type.toString() + ", Content: " + content;
	}


	/////////////////

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public KeyType getType()
	{
		return type;
	}

	public void setType(KeyType type)
	{
		this.type = type;
	}

	public static String getKeyDefinitionDisplayString(Context context, KeyDefinition definition)
	{
		if (definition.is(KeyType.SYMBOL))
		{
			return definition.getContent();
		} else
		{
			return KeyType.getKeyDefinitionDisplayString(context, definition.type);
		}
	}

}
