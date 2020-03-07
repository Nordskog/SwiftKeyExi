package com.mayulive.swiftkeyexi.main.emoji.data;

import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.FontLoader;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Roughy on 10/22/2017.
 */

public class EmojiModifiers
{
	public static final String NO_MODIFIER = "";
	public static final String FITZPATRICK_1_2 = "\uD83C\uDFFB";
	public static final String FITZPATRICK_3 = "\uD83C\uDFFC";
	public static final String FITZPATRICK_4= "\uD83C\uDFFD";
	public static final String FITZPATRICK_5 = "\uD83C\uDFFE";
	public static final String FITZPATRICK_6 = "\uD83C\uDFFF";

	public static final String[] FITZPATRICK_MODIFIERS = new String[]
			{
					NO_MODIFIER, FITZPATRICK_1_2, FITZPATRICK_3, FITZPATRICK_4, FITZPATRICK_5, FITZPATRICK_6
			};

	private static String LOGTAG = ExiModule.getLogTag(EmojiModifiers.class);

	private static Paint mPaint = new Paint();

	//Set of base emoji that support modifiers
	private static Set<String> mModifiableEmoji = new HashSet<String>();

	static
	{
		resolveModifableEmoji();
	}

	public static void forceInit()
	{
		Log.i(LOGTAG, "I am a teapot");
	}

	public static boolean singleSupportsModifiers(String emoji)
	{
		return mModifiableEmoji.contains(emoji);
	}

	private static void resolveModifableEmoji()
	{
		//Single emoji that take a modifier are simple, just plonk the modifier after it.
		//When said emoji is combined with other emoji things get complicated, as the modifier
		//must come directly /after/ the emoji to be modified.
		//and then with family emoji we have multiple of these in the same single emoji.

		//First step, assume that all other fancy emoji will be a combination of another existing single emoji,
		//go through all the smiley emoji that are a single character (no combinations or variants), and see if they
		//take a modifier.
		for (String string : FancyEmojiPanelTemplates.SMILEY_EMOJI)
		{
			String singleEmoji = stripModifableEmoji(string);
			if (singleEmoji != null)
			{
				mModifiableEmoji.add(singleEmoji);
			}
		}

		for (String string : FancyEmojiPanelTemplates.PEOPLE_EMOJI)
		{
			String singleEmoji = stripModifableEmoji(string);
			if (singleEmoji != null)
			{
				mModifiableEmoji.add(singleEmoji);
			}
		}
	}

	//Strip the first emoji character from the string if it supports modifiers, otherwise null
	private static String stripModifableEmoji(String text)
	{
		//Empty strings will never support anything
		if (text.isEmpty())
		{
			return null;
		}

		//We consider the whole string, meaning we are only interested in base emoji here.
		String singleEmoji;
		if (Character.isSurrogate( text.charAt(0) ))
			singleEmoji = text.substring(0,2);
		else
			singleEmoji = text.substring(0,1);


		Rect bounds = new Rect();
		Rect originalBounds = new Rect();

		//Throw a modifier at the first character in the string.
		//The modifier should go directly AFTER the colorable-emoji bit.
		//If the width remains the same with or without the mofidier we're good.
		String withModifier = singleEmoji + FITZPATRICK_1_2;
		mPaint.getTextBounds(withModifier,0,withModifier.length(), bounds);
		mPaint.getTextBounds(text, 0, text.length(), originalBounds);

		if (bounds.width() == originalBounds.width())
		{
			return singleEmoji;
		}
		else
			return null;
	}

	public static String applyModifier(String text, String modifier)
	{
		if (modifier.isEmpty())
			return text;


		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < text.length(); i++)
		{
			int stride = 1;
			if (Character.isHighSurrogate( text.charAt(i) ))
			{
				stride = 2;
			}

			String current = text.substring(i,i+stride);

			builder.append(current);

			if (singleSupportsModifiers(current))
			{
				builder.append(modifier);
			}

			//Skip extra if surrogate
			i += stride - 1;
		}

		return builder.toString();
	}

	public static boolean supportsModifiers(String text)
	{
		//Just apply the modifier to all single emoji we know support them
		//and see what happens
		String modifiedString = EmojiModifiers.applyModifier(text,FITZPATRICK_1_2);

		//If no modifiers were added, there were not emoji that take modifiers
		if (modifiedString.equals(text))
			return false;
		if (FontLoader.isSingleChar(modifiedString))
		{
			return true;
		}

		return false;
	}


}
