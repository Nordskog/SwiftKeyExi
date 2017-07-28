package com.mayulive.swiftkeyexi.main.commons.data;

import android.content.Context;
import android.graphics.RectF;

import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;

/**
 * Created by Roughy on 3/16/2017.
 */ //A wrapper for swiftkey key items.
public class KeyDefinition
{
	public String content;
	public KeyCommons.KeyType type;
	public RectF hitbox = new RectF(0,0,0,0);


	public KeyDefinition()
	{
		this.content = "";
		this.type = KeyCommons.KeyType.DEFAULT;

	}

	public KeyDefinition(String content, KeyCommons.KeyType type)
	{
		this(content,type, new RectF(0,0,0,0));
	}

	public KeyDefinition(String content, KeyCommons.KeyType type, RectF hitbox)
	{
		this.content = content;
		this.type = type;
		this.hitbox = hitbox;
	}

	public boolean is(KeyCommons.KeyType type)
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

	public KeyCommons.KeyType getType()
	{
		return type;
	}

	public void setType(KeyCommons.KeyType type)
	{
		this.type = type;
	}

	public static String getKeyDefinitionDisplayString(Context context, KeyDefinition definition)
	{
		if (definition.is(KeyCommons.KeyType.SYMBOL))
		{
			return definition.getContent();
		} else
		{
			return KeyCommons.KeyType.getKeyDefinitionDisplayString(context, definition.type);
		}
	}

}
