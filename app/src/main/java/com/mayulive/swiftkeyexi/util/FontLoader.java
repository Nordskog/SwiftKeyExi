package com.mayulive.swiftkeyexi.util;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.util.Xml;

import com.mayulive.swiftkeyexi.ExiModule;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class FontLoader
{
	private static String LOGTAG = ExiModule.getLogTag(FontLoader.class);

	static boolean mLibraryLoaded = false;
	static boolean mFontsLoaded = false;
	private static final String xmlNamespace = null;

	private static Paint mPaint = new Paint();


	public enum GLYPH_TYPE
	{
		INVALID, NONE, NORMAL, BITMAP
	}

	//Returns the top glyph value (unrenderable, normal, bitmap), or the lowest if returnOnUnrenderable == true
	private static native int nGetGlyphInfo(byte[] string, boolean returnOnUnrenderable);
	public static native void nInitFontLoader(String[] paths);

	static
	{
		try
		{
			System.loadLibrary("FontLoader");
			mLibraryLoaded = true;
		}
		catch(UnsatisfiedLinkError  ex)
		{
			Log.e(LOGTAG, "Failed to load FontLoader library. Expected in Swiftkey Context");

			ex.printStackTrace();
			mLibraryLoaded = false;
		}
	}

	public static void initFontLoader(String[] paths)
	{
		if (!mFontsLoaded && mLibraryLoaded)
		{
			nInitFontLoader(paths);
			mFontsLoaded = true;
		}
	}

	public static Typeface loadFont(Context context, FileInputStream stream, String fontName)
	{
		File fontBuffer = new File(context.getFilesDir(), fontName);

		if (!fontBuffer.exists())
		{
			try
			{
				BufferedInputStream bufferStream = new BufferedInputStream(stream);
				BufferedOutputStream outputStream = new BufferedOutputStream( context.openFileOutput(fontName, Context.MODE_PRIVATE) );
				byte[] buffer = new byte[32000];

				int read = -1;
				while ( (read = bufferStream.read(buffer) ) != -1)
				{
					outputStream.write(buffer,0,read);
				}

				outputStream.close();
			}
			catch (Exception e)
			{
				Log.e(LOGTAG, "Failed to load TypeFace: "+fontName);
				e.printStackTrace();
			}
		}

		return Typeface.createFromFile(fontBuffer);
	}


	private static void addAll(ArrayList<String> paths, File fontXml, boolean isKitkat)
	{

		try
		{
			if (fontXml.exists())
			{
				if (isKitkat)
					paths.addAll( readFontPathsKitkat( fontXml, "/system/fonts/") );
				else
					paths.addAll( readFontPathsLollipop( fontXml, "/system/fonts/") );
			}
			else
			{
				Log.e(LOGTAG, "Font list does not exist: "+fontXml.getAbsolutePath());
			}

		}
		catch (FileNotFoundException ex)
		{
			Log.e(LOGTAG, "Font xml not found: "+fontXml.getPath());
		}
		catch (Exception ex)
		{
			Log.e(LOGTAG, "Failed to read font list xml");
			ex.printStackTrace();
		}


	}

	public static String[] getFontPathArray()
	{
		ArrayList<String> paths = new ArrayList<>();

		//Before lollipop fonts are separated into multiple xml files.
		//Starting with kitkat they are consolidated into a single fonts.xml
		//It stays that system_fonts.xml and fallback fonts should still be maintained,
		//but they vanished by nougat.

		if (VersionTools.isLollipopOrGreater())
		{
			File fonts = new File("/system/etc/fonts.xml");
			addAll(paths, fonts, false);
		}
		else
		{
			File systemFonts = new File("/system/etc/system_fonts.xml");			//Gone on nougat?
			File fallbackFonts = new File("/system/etc/fallback_fonts.xml");		//Gone on nougat?
			File vendorFallbackFonts = new File("/vendor/etc/fallback_fonts.xml");	//Optional

			addAll(paths, systemFonts,true);
			addAll(paths, fallbackFonts, true);
			addAll(paths, vendorFallbackFonts, true);
		}

		String[] returnArray = new String[paths.size()];

		return paths.toArray(returnArray);
	}

	private static ArrayList<String> readFontPathsKitkat(File file, String suffix) throws IOException, XmlPullParserException
	{
		XmlPullParser parser = Xml.newPullParser();

		try
		{
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(new FileInputStream(file), null);
			parser.nextTag();
			return parseKitkatFonts(parser, suffix);
		}
		catch(Exception ex)
		{
			Log.e(LOGTAG, "Font path reader failed to obtain paths");
			ex.printStackTrace();
		}
		finally
		{
			//in.close();
		}

		return new ArrayList<String>();
	}

	private static ArrayList<String> readFontPathsLollipop(File file, String suffix) throws IOException, XmlPullParserException
	{
		XmlPullParser parser = Xml.newPullParser();

		try
		{
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(new FileInputStream(file), null);
			parser.nextTag();
			return parseLollipopFonts(parser, suffix);
		}
		catch(Exception ex)
		{
			Log.e(LOGTAG, "Font path reader failed to obtain paths");
			ex.printStackTrace();
		}
		finally
		{
			//in.close();
		}

		return new ArrayList<String>();
	}

	private static ArrayList<String> parseLollipopFonts(XmlPullParser parser, String suffix) throws IOException, XmlPullParserException
	{
		ArrayList<String> paths = new ArrayList<>();

		parser.require(XmlPullParser.START_TAG, xmlNamespace, "familyset");
		while (parser.nextTag() != XmlPullParser.END_TAG)
		{
			if (parser.getName().equals("family"))
			{
				boolean firstAdded = false;
				while (parser.nextTag() != XmlPullParser.END_TAG)
				{
					if (parser.getName().equals("font") && !firstAdded)
					{
						firstAdded = true;
						paths.add( suffix+parser.nextText());
					}
					else
						XmlUtils.skip(parser);
				}
			}
			else
			{
				XmlUtils.skip(parser);
			}
		}
		return paths;
	}

	private static ArrayList<String> parseKitkatFonts(XmlPullParser parser, String suffix) throws IOException, XmlPullParserException
	{
		ArrayList<String> paths = new ArrayList<>();

		parser.require(XmlPullParser.START_TAG, xmlNamespace, "familyset");
		while (parser.nextTag() != XmlPullParser.END_TAG)
		{

			Log.i(LOGTAG, "Familyset");

			if (parser.getName().equals("family"))
			{

				Log.i(LOGTAG, "family");

				while (parser.nextTag() != XmlPullParser.END_TAG)
				{
					if (parser.getName().equals("fileset"))
					{
						boolean firstAdded = false;
						while (parser.nextTag() != XmlPullParser.END_TAG)
						{
							if (parser.getName().equals("file") && !firstAdded)
							{
								firstAdded = true;

								paths.add( suffix+parser.nextText());
							}
							else
								XmlUtils.skip(parser);
						}
					}
					else
					{
						XmlUtils.skip(parser);
					}
				}
			}
			else
			{
				XmlUtils.skip(parser);
			}
		}
		return paths;
	}

	public static boolean isRenderable(String string)
	{
		return getGlyphType(string,true) != GLYPH_TYPE.NONE;
	}

	private static GLYPH_TYPE getGlyphType(String string, boolean returnOnUnrenderable)
	{
		if (!mLibraryLoaded)
			return GLYPH_TYPE.NORMAL;

		try
		{
			//Thinking about MUTF-8 is too much work
			byte[] bytes = string.getBytes("UTF-8");

			switch( nGetGlyphInfo(bytes, returnOnUnrenderable) )
			{
				case 0:
					return GLYPH_TYPE.NONE;
				case 1:
					return GLYPH_TYPE.NORMAL;
				case 2:
					return GLYPH_TYPE.BITMAP;
				default:
					return GLYPH_TYPE.NORMAL;
			}
		}
		catch (UnsupportedEncodingException e)
		{
			Log.e(LOGTAG, "UnsupportedEncoding when trying to convert string to UTF-8");
			return GLYPH_TYPE.NORMAL;
		}
	}

	//Given a string that should render as a single emoji character,
	//return NONE if it instead renders as multiple. See family emoji
	//Oddly enough this fails to detect emoji with unrenderable modifiers.
	public static boolean isSingleChar(String text)
	{
		float[] widths = new float[text.length()];
		mPaint.getTextWidths(text,widths);

		//So this basically returns the render width of each individual character.
		//If the whole lot is rendered as a single character, only the first elemnent will be non-zero
		//So if any of the other values are non-zero, it is rendering as multiple characters
		for (int i = 1; i< widths.length; i++)
		{
			if (widths[i] != 0.0f)
				return false;
		}

		return true;
	}



	public static GLYPH_TYPE containsEmoji(String string)
	{
		GLYPH_TYPE type = getGlyphType(string, false);

		return type;

	}



}
