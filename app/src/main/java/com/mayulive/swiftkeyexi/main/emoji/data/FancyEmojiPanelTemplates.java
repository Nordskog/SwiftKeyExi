package com.mayulive.swiftkeyexi.main.emoji.data;

import android.os.Build;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.FontLoader;
import com.mayulive.swiftkeyexi.util.TextUtils;

import java.util.ArrayList;

/**
 * Created by Roughy on 12/5/2016.
 */

public class FancyEmojiPanelTemplates
{

	//These sets were all copied from swiftkey's code.
	//some variants did not display correct as is.
	// "0\ufe0f\u20e3" required that the \ufe0f be removed for it to work right.
	//Same thing for a few other chars.

	private static String LOGTAG = ExiModule.getLogTag(FancyEmojiPanelTemplates.class);

	public static final int SMILEY = 0;
	public static final int NATURE= 1;
	public static final int FOOD= 2;
	public static final int TRAVEL= 3;
	public static final int ACTIVITIES= 4;
	public static final int OBJECTS= 5;
	public static final int SYMBOLS= 6;
	public static final int FLAGS = 7;
	public static final int REDDIT_EMOTICONS= 8;
	public static final int JAPANESE_EMOTICONSs= 9;
	public static final int EMOTICONS= 10;

	public static final int MARSHMALLOW_SMILEY = 100;
	public static final int MARSHMALLOW_CROWN = 101;
	public static final int MARSHMALLOW_FLOWER = 102;
	public static final int MARSHMALLOW_TRIANGLE = 103;
	public static final int MARSHMALLOW_CAR = 104;
	public static final int MARSHMALLOW_REDDIT= 105;
	public static final int MARSHMALLOW_JAPANESE= 106;
	public static final int MARSHMALLOW_EMOTICONS= 107;

	public enum EmojiPanelVersion
	{
		PRE_NOUGAT(Build.VERSION_CODES.M),
		POST_NOUGAT(28);	// Changed sdk version to trigger update, since saved value will differ

		private int mSDKVersion = 0;

		EmojiPanelVersion(int sdk)
		{
			mSDKVersion = sdk;
		}

		public int getSdkVersion()
		{
			return mSDKVersion;
		}

		public static EmojiPanelVersion getFromPref(String pref)
		{
			if (pref.equals("AUTO"))
			{
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
					return POST_NOUGAT;
				else
					return PRE_NOUGAT;
			}
			else if (pref.equals("PRE_NOUGAT"))
				return PRE_NOUGAT;
			else return
				POST_NOUGAT;
		}

		public static EmojiPanelVersion getFromSdkVersion(int version)
		{
			if (version <= Build.VERSION_CODES.M)
				return PRE_NOUGAT;
			else
				return POST_NOUGAT;
		}

	}

	private static ArrayList<DB_EmojiPanelItem> FANCY_EMOJI_PANEL_TEMPLATES = new ArrayList<DB_EmojiPanelItem>();
	private static ArrayList<DB_EmojiPanelItem> EMOJI_PANEL_TEMPLATES = new ArrayList<DB_EmojiPanelItem>();

	private static void initPanels()
	{


		//Stolen from Swiftkey

		//Stock Emoji Smiley
		{
			String[] newStrings = new String[]{"\u263a", "\ud83d\ude0a", "\ud83d\ude00", "\ud83d\ude01", "\ud83d\ude02", "\ud83e\udd23", "\ud83d\ude03", "\ud83d\ude04", "\ud83d\ude05", "\ud83d\ude06", "\ud83d\ude07", "\ud83d\ude08", "\ud83d\ude09", "\ud83d\ude2f", "\ud83d\ude42", "\ud83d\ude43", "\ud83d\ude10", "\ud83d\ude11", "\ud83d\ude15", "\ud83d\ude41", "\ud83d\ude20", "\ud83d\ude2c", "\ud83d\ude21", "\ud83d\ude22", "\ud83d\ude34", "\ud83d\ude2e", "\ud83d\ude23", "\ud83d\ude24", "\ud83d\ude25", "\ud83d\ude26", "\ud83d\ude27", "\ud83d\ude28", "\ud83d\ude29", "\ud83d\ude30", "\ud83d\ude1f", "\ud83d\ude31", "\ud83d\ude32", "\ud83d\ude33", "\ud83d\ude35", "\ud83d\ude36", "\ud83d\ude37", "\ud83d\ude1e", "\ud83d\ude12", "\ud83d\ude0d", "\ud83d\ude1b", "\ud83d\ude1c", "\ud83d\ude1d", "\ud83d\ude0b", "\ud83d\ude17", "\ud83d\ude19", "\ud83d\ude18", "\ud83d\ude1a", "\ud83d\ude0e", "\ud83d\ude2d", "\ud83d\ude0c", "\ud83d\ude16", "\ud83d\ude14", "\ud83d\ude2a", "\ud83d\ude0f", "\ud83e\udd11", "\ud83e\udd24", "\ud83e\udd13", "\ud83e\udd25", "\ud83e\udd17", "\ud83d\ude44", "\ud83e\udd14", "\ud83e\udd10", "\ud83e\udd12", "\ud83e\udd22", "\ud83e\udd27", "\ud83e\udd15", "\ud83d\ude13", "\ud83d\ude2b", "\ud83e\udd20", "\ud83d\ude4b", "\ud83d\ude4f", "\ud83d\ude4d", "\ud83d\ude45", "\ud83d\ude46", "\ud83d\ude47", "\ud83d\ude4e", "\ud83e\udd37", "\ud83e\udd26", "\ud83e\udd30", "\ud83d\ude4c", "\u2639\ufe0f", "\ud83d\ude3a", "\ud83d\ude3c", "\ud83d\ude38", "\ud83d\ude39", "\ud83d\ude3b", "\ud83d\ude3d", "\ud83d\ude3f", "\ud83d\ude3e", "\ud83d\ude40", "\ud83d\ude48", "\ud83d\ude49", "\ud83d\ude4a", "\ud83d\udca9", "\ud83d\udc76", "\ud83d\udc66", "\ud83d\udc67", "\ud83d\udc68", "\ud83d\udc69", "\ud83d\udc74", "\ud83d\udc75", "\ud83d\udc8f", "\ud83d\udc69\u200d\u2764\ufe0f\u200d\ud83d\udc8b\u200d\ud83d\udc69", "\ud83d\udc68\u200d\u2764\ufe0f\u200d\ud83d\udc8b\u200d\ud83d\udc68", "\ud83d\udc91", "\ud83d\udc69\u200d\u2764\ufe0f\u200d\ud83d\udc69", "\ud83d\udc68\u200d\u2764\ufe0f\u200d\ud83d\udc68", "\ud83d\udc6a", "\ud83d\udc68\u200d\ud83d\udc69\u200d\ud83d\udc67", "\ud83d\udc68\u200d\ud83d\udc69\u200d\ud83d\udc67\u200d\ud83d\udc66", "\ud83d\udc68\u200d\ud83d\udc69\u200d\ud83d\udc66\u200d\ud83d\udc66", "\ud83d\udc68\u200d\ud83d\udc69\u200d\ud83d\udc67\u200d\ud83d\udc67", "\ud83d\udc69\u200d\ud83d\udc69\u200d\ud83d\udc66", "\ud83d\udc69\u200d\ud83d\udc69\u200d\ud83d\udc67", "\ud83d\udc69\u200d\ud83d\udc69\u200d\ud83d\udc67\u200d\ud83d\udc66", "\ud83d\udc69\u200d\ud83d\udc69\u200d\ud83d\udc66\u200d\ud83d\udc66", "\ud83d\udc69\u200d\ud83d\udc69\u200d\ud83d\udc67\u200d\ud83d\udc67", "\ud83d\udc68\u200d\ud83d\udc68\u200d\ud83d\udc66", "\ud83d\udc68\u200d\ud83d\udc68\u200d\ud83d\udc67", "\ud83d\udc68\u200d\ud83d\udc68\u200d\ud83d\udc67\u200d\ud83d\udc66", "\ud83d\udc68\u200d\ud83d\udc68\u200d\ud83d\udc66\u200d\ud83d\udc66", "\ud83d\udc68\u200d\ud83d\udc68\u200d\ud83d\udc67\u200d\ud83d\udc67", "\ud83d\udc6b", "\ud83d\udc6c", "\ud83d\udc6d", "\ud83d\udc64", "\ud83d\udc65", "\ud83d\udde3", "\ud83d\udc6e", "\ud83d\udd75", "\ud83d\udc77", "\ud83d\udc81", "\ud83d\udc82", "\ud83d\udc6f", "\ud83d\udc70", "\ud83e\udd35", "\ud83d\udc78", "\ud83e\udd34", "\ud83c\udf85", "\ud83e\udd36", "\ud83d\udc7c", "\ud83d\udc71", "\ud83d\udc72", "\ud83d\udc73", "\ud83d\udc83", "\ud83d\udd7a", "\ud83d\udc86", "\ud83d\udc87", "\ud83d\udc85", "\ud83d\udc7b", "\ud83e\udd21", "\ud83d\udc79", "\ud83d\udc7a", "\ud83d\udc7d", "\ud83e\udd16", "\ud83d\udc7e", "\ud83d\udc7f", "\ud83d\udc80", "\ud83d\udcaa", "\ud83d\udc40", "\ud83d\udc41", "\ud83d\udc42", "\ud83d\udc43", "\ud83d\udc63", "\ud83d\udc44", "\ud83d\udc45", "\ud83d\udc8b", "\u2764\ufe0f", "\ud83d\udc99", "\ud83d\udc9a", "\ud83d\udc9b", "\ud83d\udc9c", "\ud83d\udda4", "\ud83d\udc93", "\ud83d\udc94", "\ud83d\udc95", "\ud83d\udc96", "\ud83d\udc97", "\ud83d\udc98", "\ud83d\udc9d", "\ud83d\udc9e", "\ud83d\udc9f", "\u2763\ufe0f", "\ud83e\udd33", "\u270d\ufe0f", "\ud83d\udc4d", "\ud83d\udc4e", "\ud83d\udc4c", "\u270a", "\u270c\ufe0f", "\ud83e\udd1e", "\u270b", "\ud83e\udd1a", "\ud83d\udc4a", "\ud83e\udd1c", "\ud83e\udd1b", "\u261d\ufe0f", "\ud83d\udc46", "\ud83d\udc47", "\ud83d\udc48", "\ud83d\udc49", "\ud83d\udd95", "\ud83d\udd90", "\ud83e\udd18", "\ud83e\udd19", "\ud83d\udd96", "\ud83d\udc4b", "\ud83d\udc4f", "\ud83e\udd1d", "\ud83d\udc50"};
			EMOJI_PANEL_TEMPLATES.add( getEmojiPanel(newStrings, 1, "☺", "☺", 0, true, MARSHMALLOW_SMILEY) );
		}

		//Stock Emoji Crown
		{
			String[] newStrings = new String[]{"\ud83d\udd30", "\ud83d\udc84", "\ud83d\udc5e", "\ud83d\udc5f", "\ud83d\udc51", "\ud83d\udc52", "\ud83c\udfa9", "\u26d1", "\ud83c\udf93", "\ud83d\udd76", "\ud83d\udc53", "\u231a\ufe0f", "\ud83d\udc54", "\ud83d\udc55", "\ud83d\udc56", "\ud83d\udc57", "\ud83d\udc58", "\ud83d\udc59", "\ud83d\udc60", "\ud83d\udc61", "\ud83d\udc62", "\ud83d\udc5a", "\ud83d\udc5c", "\ud83d\udcbc", "\ud83c\udf92", "\ud83d\udc5d", "\ud83d\udc5b", "\ud83d\uded2", "\ud83d\udcb0", "\ud83d\udcb3", "\ud83d\udcb2", "\ud83d\udcb5", "\ud83d\udcb4", "\ud83d\udcb6", "\ud83d\udcb7", "\ud83d\udcb8", "\ud83d\udcb1", "\ud83d\udcb9", "\ud83d\udd2b", "\ud83d\udd2a", "\ud83d\udca3", "\ud83d\udc89", "\ud83d\udc8a", "\ud83c\udf21", "\ud83d\udeac", "\ud83d\udd6f", "\u2620", "\u26b0", "\ud83c\udffa", "\ud83d\udd14", "\ud83d\udd15", "\ud83d\udeaa", "\ud83d\udd2c", "\ud83d\udd2d", "\ud83d\udddc", "\ud83d\udd2e", "\ud83d\udcff", "\ud83d\udd26", "\ud83d\udd0b", "\ud83d\udd0c", "\ud83d\udddd", "\ud83d\udcdc", "\ud83d\udcd7", "\ud83d\udcd8", "\ud83d\udcd9", "\ud83d\udcda", "\ud83d\udcd4", "\ud83d\udcd2", "\ud83d\udcd1", "\ud83d\udcd3", "\ud83d\udcd5", "\ud83d\udcd6", "\ud83d\udcf0", "\ud83d\udcdb", "\ud83c\udf83", "\ud83c\udf84", "\ud83c\udf80", "\ud83c\udf81", "\ud83d\udecd", "\ud83c\udff7", "\ud83c\udf82", "\ud83c\udf88", "\ud83c\udf86", "\ud83c\udf87", "\ud83c\udf89", "\ud83c\udf8a", "\ud83c\udf8d", "\ud83c\udf8f", "\ud83c\udf8c", "\ud83c\udf90", "\ud83c\udf8b", "\ud83c\udf8e", "\ud83d\udcf1", "\ud83d\udcf2", "\ud83d\udcdf", "\u260e\ufe0f", "\ud83d\udcde", "\ud83d\udda8", "\ud83d\udce0", "\ud83d\udce6", "\u2709\ufe0f", "\ud83d\udce8", "\ud83d\udce9", "\ud83d\udcea", "\ud83d\udceb", "\ud83d\udced", "\ud83d\udcec", "\ud83d\udcee", "\ud83d\udce4", "\ud83d\udce5", "\ud83d\udce2", "\ud83d\udce3", "\ud83d\udce1", "\ud83d\udcac", "\ud83d\udcad", "\ud83d\uddef", "\ud83d\udc41\u200d\ud83d\udde8", "\ud83d\udd8a", "\ud83d\udd8b", "\u2712\ufe0f", "\u270f\ufe0f", "\ud83d\udd8d", "\ud83d\udd8c", "\ud83d\udcdd", "\ud83d\uddd3", "\ud83d\uddc3", "\ud83d\uddf3", "\ud83d\uddc4", "\ud83d\uddd2", "\ud83d\uddc2", "\ud83d\uddde", "\ud83d\udccf", "\ud83d\udcd0", "\ud83d\udccd", "\ud83d\udccc", "\ud83d\udd87", "\ud83d\udcce", "\ud83d\uddd1", "\ud83d\udee2", "\u2702\ufe0f", "\ud83d\udcba", "\ud83d\udcbb", "\ud83d\udda5", "\ud83d\uddb1", "\u2328", "\ud83d\udcbd", "\ud83d\udcbe", "\ud83d\udcbf", "\ud83d\udcc6", "\ud83d\udcc5", "\ud83d\udcc7", "\ud83d\udccb", "\ud83d\udcc1", "\ud83d\udcc2", "\ud83d\udcc3", "\ud83d\udcc4", "\ud83d\udcca", "\ud83d\udcc8", "\ud83d\udcc9", "\u26fa", "\ud83c\udfa1", "\ud83c\udfa2", "\ud83c\udfa0", "\ud83c\udfaa", "\ud83c\udfa8", "\ud83c\udfac", "\ud83c\udfa5", "\ud83d\udcf7", "\ud83d\udcf8", "\ud83d\udcf9", "\ud83d\udcfd", "\ud83c\udf9e", "\ud83c\udfa6", "\ud83c\udfad", "\ud83c\udfab", "\ud83c\udf9f", "\ud83c\udfae", "\ud83d\uddb2", "\ud83d\udd79", "\ud83c\udfb2", "\ud83c\udfb0", "\ud83c\udccf", "\ud83c\udfb4", "\ud83c\udc04", "\ud83c\udfaf", "\ud83d\udcfa", "\ud83d\udcfb", "\ud83d\udcc0", "\ud83d\udcfc", "\ud83c\udfa7", "\ud83c\udfa4", "\ud83c\udf99", "\ud83c\udf9a", "\ud83c\udf9b", "\ud83c\udfb5", "\ud83c\udfb6", "\ud83c\udfbc", "\ud83c\udfbb", "\ud83c\udfb9", "\ud83c\udfb7", "\ud83c\udfba", "\ud83d\udcef", "\ud83c\udfb8", "\ud83e\udd41", "\u303d\ufe0f", "\ud83d\udd73", "\ud83c\udff3", "\ud83c\udff4"};
			EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 1, "\uD83D\uDC51", "\uD83D\uDC51", 0, true, MARSHMALLOW_CROWN) );
		}

		//Stock Emoji Flower
		{
			String[] newStrings = new String[]{"\ud83d\udc15", "\ud83d\udc36", "\ud83d\udc29", "\ud83d\udc08", "\ud83d\udc05", "\ud83d\udc31", "\ud83e\udd81", "\ud83e\udd8d", "\ud83d\udc3f", "\ud83d\udc00", "\ud83d\udc01", "\ud83d\udc2d", "\ud83d\udc39", "\ud83d\udc22", "\ud83d\udc07", "\ud83d\udc30", "\ud83d\udc13", "\ud83d\udc14", "\ud83d\udc23", "\ud83d\udc24", "\ud83d\udc25", "\ud83d\udc26", "\ud83e\udd83", "\ud83d\udd4a", "\ud83e\udd85", "\ud83e\udd86", "\ud83e\udd89", "\ud83d\udc0f", "\ud83d\udc11", "\ud83d\udc10", "\ud83d\udc3a", "\ud83e\udd8a", "\ud83d\udc03", "\ud83d\udc02", "\ud83d\udc04", "\ud83d\udc2e", "\ud83e\udd8c", "\ud83d\udc34", "\ud83e\udd84", "\ud83d\udc17", "\ud83d\udc16", "\ud83d\udc37", "\ud83d\udc3d", "\ud83d\udc38", "\ud83d\udc0d", "\ud83e\udd90", "\ud83d\udc3c", "\ud83d\udc27", "\ud83d\udc18", "\ud83d\udc28", "\ud83d\udc12", "\ud83d\udc35", "\ud83d\udc06", "\ud83d\udc2f", "\ud83d\udc3b", "\ud83e\udd8f", "\ud83d\udc2b", "\ud83d\udc2a", "\ud83d\udc0a", "\ud83e\udd87", "\ud83d\udc33", "\ud83d\udc0b", "\ud83e\udd88", "\ud83d\udc1f", "\ud83d\udc20", "\ud83d\udc21", "\ud83d\udc19", "\ud83e\udd91", "\ud83d\udc1a", "\ud83e\udd80", "\ud83d\udc2c", "\ud83d\udc0c", "\ud83d\udc1b", "\ud83d\udc1c", "\ud83d\udc1d", "\ud83e\udd8b", "\ud83d\udc1e", "\ud83d\udd77", "\ud83d\udd78", "\ud83e\udd82", "\ud83e\udd8e", "\ud83d\udc32", "\ud83d\udc09", "\ud83d\udc3e", "\ud83c\udf78", "\ud83c\udf7a", "\ud83c\udf7b", "\ud83e\udd43", "\ud83c\udf77", "\ud83c\udf79", "\ud83e\udd42", "\ud83c\udf7e", "\ud83c\udf76", "\ud83e\udd5b", "\u2615\ufe0f", "\ud83c\udf75", "\ud83c\udf7c", "\ud83c\udf74", "\ud83e\udd44", "\ud83c\udf7d", "\ud83c\udf68", "\ud83c\udf67", "\ud83c\udf66", "\ud83c\udf69", "\ud83c\udf70", "\ud83c\udf6a", "\ud83c\udf7f", "\ud83c\udf6b", "\ud83c\udf6c", "\ud83c\udf6d", "\ud83c\udf6e", "\ud83e\udd5e", "\ud83c\udf6f", "\ud83e\udd5a", "\ud83c\udf73", "\ud83e\udd53", "\ud83c\udf54", "\ud83c\udf5f", "\ud83c\udf2d", "\ud83c\udf2e", "\ud83c\udf2f", "\ud83e\udd59", "\ud83c\udf5d", "\ud83e\udd58", "\ud83e\udd57", "\ud83c\udf55", "\ud83c\udf56", "\ud83c\udf57", "\ud83c\udf64", "\ud83c\udf63", "\ud83c\udf71", "\ud83c\udf5e", "\ud83e\udd50", "\ud83e\udd56", "\ud83e\uddc0", "\ud83c\udf5c", "\ud83c\udf59", "\ud83c\udf5a", "\ud83c\udf5b", "\ud83c\udf72", "\ud83c\udf65", "\ud83c\udf62", "\ud83c\udf61", "\ud83c\udf58", "\ud83c\udf60", "\ud83e\udd54", "\ud83e\udd55", "\ud83c\udf4c", "\ud83c\udf4e", "\ud83c\udf4f", "\ud83c\udf4a", "\ud83c\udf4b", "\ud83c\udf44", "\ud83c\udf45", "\ud83e\udd51", "\ud83c\udf46", "\ud83e\udd52", "\ud83c\udf47", "\ud83c\udf48", "\ud83c\udf49", "\ud83e\udd5d", "\ud83c\udf50", "\ud83c\udf51", "\ud83c\udf52", "\ud83c\udf53", "\ud83c\udf4d", "\ud83c\udf30", "\ud83e\udd5c", "\ud83c\udf31", "\ud83c\udf32", "\ud83c\udf33", "\ud83c\udf34", "\ud83c\udf35", "\ud83c\udf37", "\ud83c\udf38", "\ud83c\udf39", "\ud83e\udd40", "\ud83c\udf40", "\ud83c\udf41", "\ud83c\udf42", "\ud83c\udf43", "\ud83c\udf3a", "\ud83c\udf3b", "\ud83c\udf3c", "\ud83c\udf3d", "\ud83c\udf36", "\ud83c\udf3e", "\ud83c\udf3f", "\u2600\ufe0f", "\ud83c\udf24", "\u26c5\ufe0f", "\ud83c\udf25", "\ud83c\udf26", "\u2601\ufe0f", "\ud83c\udf27", "\u26c8", "\ud83c\udf29", "\ud83c\udf28", "\ud83c\udf08", "\ud83c\udf01", "\ud83c\udf02", "\u2614\ufe0f", "\ud83d\udca7", "\u26a1\ufe0f", "\ud83c\udf00", "\u2744\ufe0f", "\u26c4\ufe0f", "\u2603", "\ud83c\udf2c", "\ud83c\udf2a", "\ud83c\udf2b", "\ud83c\udf19", "\ud83c\udf1e", "\ud83c\udf1d", "\ud83c\udf1a", "\ud83c\udf1b", "\ud83c\udf1c", "\ud83c\udf11", "\ud83c\udf12", "\ud83c\udf13", "\ud83c\udf14", "\ud83c\udf15", "\ud83c\udf16", "\ud83c\udf17", "\ud83c\udf18", "\ud83c\udf91", "\ud83c\udf04", "\ud83c\udf05", "\ud83c\udf07", "\ud83c\udf06", "\ud83c\udf03", "\ud83c\udf0c", "\ud83c\udf09", "\u2604", "\ud83c\udf0a", "\ud83c\udf0b", "\ud83c\udf0e", "\ud83c\udf0f", "\ud83c\udf0d", "\ud83c\udf10"};
			EMOJI_PANEL_TEMPLATES.add( getEmojiPanel(newStrings, 1, "\uD83C\uDF3C", "\uD83C\uDF3C", 0, true, MARSHMALLOW_FLOWER));
		}

		//Stock emoji car
		{
			String[] newStrings = new String[]{"\ud83c\udfe0", "\ud83c\udfe1", "\ud83c\udfda", "\ud83c\udfdb", "\u26ea\ufe0f", "\ud83d\udc92", "\ud83d\udd4c", "\ud83d\udd4d", "\ud83d\udd4b", "\u26e9", "\ud83c\udfe2", "\ud83c\udfe3", "\ud83c\udfe4", "\ud83c\udfe5", "\ud83c\udfe6", "\ud83c\udfe7", "\ud83c\udfe8", "\ud83c\udfe9", "\ud83c\udfea", "\ud83c\udfeb", "\u26f2", "\ud83c\udfec", "\ud83c\udfef", "\ud83c\udff0", "\ud83c\udfed", "\ud83d\uddfb", "\ud83d\uddfc", "\ud83d\uddfd", "\ud83d\uddfe", "\ud83c\udfd7", "\u26f0", "\ud83c\udfd4", "\ud83c\udfd5", "\ud83c\udfde", "\ud83d\udee3", "\ud83d\udee4", "\ud83c\udfdc", "\ud83c\udfd6", "\u26f1", "\ud83c\udfdd", "\ud83c\udfd9", "\ud83c\udfd8", "\ud83c\udfdf", "\ud83d\udecb", "\ud83d\udecc", "\ud83d\udecf", "\ud83d\udece", "\ud83d\uddbc", "\ud83d\uddfa", "\ud83d\uddff", "\u2693\ufe0f", "\ud83c\udfee", "\ud83d\udc88", "\ud83d\udd27", "\ud83d\udd28", "\ud83d\udee0", "\u2692", "\u26cf", "\ud83d\udd29", "\ud83d\udebf", "\ud83d\udec1", "\ud83d\udec0", "\ud83d\udebd", "\ud83d\udebe", "\ud83c\udfbd", "\ud83c\udfa3", "\ud83d\udef6", "\ud83c\udfb1", "\ud83c\udfb3", "\u26be\ufe0f", "\ud83c\udfcc", "\u26f3\ufe0f", "\ud83c\udfbe", "\ud83c\udfd3", "\ud83c\udff8", "\ud83c\udfd2", "\ud83c\udfd1", "\ud83c\udfcf", "\ud83c\udff9", "\u26bd\ufe0f", "\ud83e\udd45", "\ud83c\udfd0", "\ud83c\udfbf", "\u26f7", "\u26f8", "\ud83e\udd3a", "\u26f9", "\ud83e\udd4a", "\ud83e\udd4b", "\ud83c\udfc0", "\ud83c\udfc1", "\ud83c\udfc2", "\ud83c\udfc3", "\ud83e\udd38", "\ud83e\udd3c", "\ud83e\udd3d", "\ud83e\udd3e", "\ud83e\udd39", "\ud83c\udfc4", "\ud83c\udfcb", "\ud83d\udd74", "\ud83e\udd47", "\ud83e\udd48", "\ud83e\udd49", "\ud83c\udfc5", "\ud83c\udf96", "\ud83c\udf97", "\ud83c\udff5", "\ud83c\udfc6", "\ud83c\udfc7", "\ud83d\udc0e", "\ud83c\udfc8", "\ud83c\udfc9", "\ud83c\udfca", "\u26d3", "\ud83d\udde1", "\u2694", "\ud83d\udee1", "\ud83d\ude82", "\ud83d\ude83", "\ud83d\ude84", "\ud83d\ude85", "\ud83d\ude86", "\ud83d\ude87", "\u24c2\ufe0f", "\ud83d\ude88", "\ud83d\ude8a", "\ud83d\ude8b", "\ud83d\ude8c", "\ud83d\ude8d", "\ud83d\ude8e", "\ud83d\ude8f", "\ud83d\ude90", "\ud83d\ude91", "\ud83d\ude92", "\ud83d\ude93", "\ud83d\ude94", "\ud83d\ude95", "\ud83d\ude96", "\ud83d\ude97", "\ud83d\ude98", "\ud83c\udfce", "\ud83c\udfcd", "\ud83d\udef5", "\ud83d\ude99", "\ud83d\ude9a", "\ud83d\ude9b", "\ud83d\ude9c", "\ud83d\ude9d", "\ud83d\ude9e", "\ud83d\ude9f", "\ud83d\udea0", "\ud83d\udea1", "\ud83d\udea2", "\ud83d\udea3", "\ud83d\ude81", "\ud83d\udee9", "\u2708\ufe0f", "\ud83d\udeeb", "\ud83d\udeec", "\ud83d\udec2", "\ud83d\udec3", "\ud83d\udec4", "\ud83d\udec5", "\ud83d\udeb2", "\ud83d\udef4", "\ud83d\udeb3", "\ud83d\udeb4", "\ud83d\udeb5", "\ud83d\udeb7", "\ud83d\udeb8", "\ud83d\ude89", "\ud83d\ude80", "\ud83d\udef0", "\ud83d\udea4", "\u26f5", "\ud83d\udee5", "\u26f4", "\ud83d\udef3", "\ud83d\udeb6", "\u26fd", "\ud83c\udd7f\ufe0f", "\ud83d\udea5", "\ud83d\udea6", "\ud83d\udea7", "\ud83d\udea8", "\u2668\ufe0f", "\ud83d\udc8c", "\ud83d\udc8d", "\ud83d\udc8e", "\ud83d\udc90"};
			EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 1, "\uD83D\uDE98", "\uD83D\uDE98", 0, true, MARSHMALLOW_CAR) );
		}




		//Stock emoji triangle
		{
			String[] newStrings = new String[]{"\ud83d\udd1d", "\ud83d\udd19", "\ud83d\udd1b", "\ud83d\udd1c", "\ud83d\udd1a", "\u23f3", "\u231b\ufe0f", "\u23f0", "\u23f1", "\u23f2", "\ud83d\udd70", "\u2648\ufe0f", "\u2649\ufe0f", "\u264a\ufe0f", "\u264b\ufe0f", "\u264c\ufe0f", "\u264d\ufe0f", "\u264e\ufe0f", "\u264f\ufe0f", "\u2650\ufe0f", "\u2651\ufe0f", "\u2652\ufe0f", "\u2653\ufe0f", "\u26ce", "\u271d\ufe0f", "\ud83d\udd49", "\u2721\ufe0f", "\ud83d\udd4e", "\u262f\ufe0f", "\ud83d\uded0", "\ud83d\udd2f", "\u262e", "\u262a", "\u2638", "\u2626", "\u269b", "\u2622", "\u2623", "\u269c", "\ud83d\udd31", "\ud83d\udebb", "\ud83d\udeae", "\ud83d\udeaf", "\ud83d\udeb0", "\ud83d\udeb1", "\ud83c\udd70\ufe0f", "\ud83c\udd71\ufe0f", "\ud83c\udd8e", "\ud83c\udd7e\ufe0f", "\ud83d\udcae", "\ud83d\udcaf", "\ud83d\udd20", "\ud83d\udd21", "\ud83d\udd22", "\ud83d\udd23", "\ud83d\udd24", "\u27bf", "\ud83d\udcf6", "\ud83d\udcf3", "\ud83d\udcf4", "\u2734\ufe0f", "\ud83d\udcf5", "\ud83d\udeb9", "\ud83d\udeba", "\ud83d\udebc", "\u267f\ufe0f", "\u267b\ufe0f", "\ud83d\udead", "\ud83d\udea9", "\u26a0\ufe0f", "\ud83c\ude01", "\ud83d\udd1e", "\u26d4\ufe0f", "\ud83c\udd92", "\ud83c\udd97", "\ud83c\udd95", "\ud83c\udd98", "\ud83c\udd99", "\ud83c\udd93", "\ud83c\udd96", "\ud83c\udd9a", "\ud83c\ude32", "\ud83c\ude33", "\ud83c\ude34", "\ud83c\ude35", "\ud83c\ude36", "\ud83c\ude37\ufe0f", "\ud83c\ude38", "\ud83c\ude39", "\ud83c\ude02\ufe0f", "\ud83c\ude3a", "\ud83c\ude50", "\ud83c\ude51", "\u3299\ufe0f", "\u3297\ufe0f", "\u00a9\ufe0f", "\u00ae\ufe0f", "\u2122\ufe0f", "\ud83c\ude1a\ufe0f", "\ud83c\ude2f\ufe0f", "\ud83d\uded1", "\u2b55\ufe0f", "\u274c", "\u274e", "\ud83d\udeab", "\u2705", "\u2714\ufe0f", "\ud83d\udd17", "\u2747\ufe0f", "\u2733\ufe0f", "\u2795", "\u2796", "\u2716\ufe0f", "\u2797", "\ud83d\udca0", "\ud83d\udca1", "\ud83d\udca4", "\ud83d\udca2", "\ud83d\udd25", "\ud83d\udca5", "\ud83d\udca8", "\ud83d\udca6", "\ud83d\udcab", "\ud83d\udd5b", "\ud83d\udd67", "\ud83d\udd50", "\ud83d\udd5c", "\ud83d\udd51", "\ud83d\udd5d", "\ud83d\udd52", "\ud83d\udd5e", "\ud83d\udd53", "\ud83d\udd5f", "\ud83d\udd54", "\ud83d\udd60", "\ud83d\udd55", "\ud83d\udd61", "\ud83d\udd56", "\ud83d\udd62", "\ud83d\udd57", "\ud83d\udd63", "\ud83d\udd58", "\ud83d\udd64", "\ud83d\udd59", "\ud83d\udd65", "\ud83d\udd5a", "\ud83d\udd66", "0\u20e3", "1\u20e3", "2\u20e3", "3\u20e3", "4\u20e3", "5\u20e3", "6\u20e3", "7\u20e3", "8\u20e3",  "9\u20e3", "\ud83d\udd1f", "\u2195\ufe0f", "\u2b06\ufe0f", "\u2197\ufe0f", "\u27a1\ufe0f", "\u2198\ufe0f", "\u2b07\ufe0f", "\u2199\ufe0f", "\u2b05\ufe0f", "\u2196\ufe0f", "\u2194\ufe0f", "\u2934\ufe0f", "\u2935\ufe0f", "\u21aa\ufe0f", "\u21a9\ufe0f", "\u23ea", "\u23eb", "\u23ec", "\u23e9", "\u25b6\ufe0f", "\u23f8", "\u23ef", "\u23f9", "\u23fa", "\u23ed", "\u23ee", "\u25c0\ufe0f", "\ud83d\udd3d", "\ud83d\udd3c", "#\u20e3", "*\u20e3", "\u2139\ufe0f", "\u2728", "\ud83d\udd34", "\ud83d\udd35", "\u26aa\ufe0f", "\u26ab\ufe0f", "\ud83d\udd33", "\ud83d\udd32", "\u2b50", "\ud83c\udf1f", "\ud83c\udf20", "\u25aa\ufe0f", "\u25ab\ufe0f", "\u2b1b\ufe0f", "\u2b1c\ufe0f", "\u25fc\ufe0f", "\u25fb\ufe0f", "\u25fe\ufe0f", "\u25fd\ufe0f", "\ud83d\udd38", "\ud83d\udd39", "\ud83d\udd36", "\ud83d\udd37", "\ud83d\udd3a", "\ud83d\udd3b", "\u2754", "\u2753", "\u2755", "\u2757\ufe0f", "\u203c\ufe0f", "\u2049\ufe0f", "\u3030", "\u27b0", "\u2660\ufe0f", "\u2663\ufe0f", "\u2665\ufe0f", "\u2666\ufe0f", "\ud83c\udd94", "\ud83d\udd11", "\ud83c\udd91", "\ud83d\udd0d", "\ud83d\udd12", "\ud83d\udd13", "\ud83d\udd10", "\u2611\ufe0f", "\ud83d\udd18", "\ud83d\udd0e", "\ud83d\udd16", "\ud83d\udd0f", "\ud83d\udd03", "\ud83d\udd00", "\ud83d\udd01", "\ud83d\udd02", "\ud83d\udd04", "\ud83d\udce7", "\ud83d\udd05", "\ud83d\udd06", "\ud83d\udd07", "\ud83d\udd08", "\ud83d\udd09", "\ud83d\udd0a", "\u2696", "\u2699", "\u2697", "\ud83c\udde6\ud83c\uddeb", "\ud83c\udde6\ud83c\uddfd", "\ud83c\udde6\ud83c\uddf1", "\ud83c\udde9\ud83c\uddff", "\ud83c\udde6\ud83c\uddf8", "\ud83c\udde6\ud83c\udde9", "\ud83c\udde6\ud83c\uddf4", "\ud83c\udde6\ud83c\uddee", "\ud83c\udde6\ud83c\uddf6", "\ud83c\udde6\ud83c\uddec", "\ud83c\udde6\ud83c\uddf7", "\ud83c\udde6\ud83c\uddf2", "\ud83c\udde6\ud83c\uddfc", "\ud83c\udde6\ud83c\uddfa", "\ud83c\udde6\ud83c\uddf9", "\ud83c\udde6\ud83c\uddff", "\ud83c\udde7\ud83c\uddf8", "\ud83c\udde7\ud83c\udded", "\ud83c\udde7\ud83c\udde9", "\ud83c\udde7\ud83c\udde7", "\ud83c\udde7\ud83c\uddfe", "\ud83c\udde7\ud83c\uddea", "\ud83c\udde7\ud83c\uddff", "\ud83c\udde7\ud83c\uddef", "\ud83c\udde7\ud83c\uddf2", "\ud83c\udde7\ud83c\uddf9", "\ud83c\udde7\ud83c\uddf4", "\ud83c\udde7\ud83c\udde6", "\ud83c\udde7\ud83c\uddfc", "\ud83c\udde7\ud83c\uddf7", "\ud83c\uddee\ud83c\uddf4", "\ud83c\uddfb\ud83c\uddec", "\ud83c\udde7\ud83c\uddf3", "\ud83c\udde7\ud83c\uddec", "\ud83c\udde7\ud83c\uddeb", "\ud83c\udde7\ud83c\uddee", "\ud83c\uddf0\ud83c\udded", "\ud83c\udde8\ud83c\uddf2", "\ud83c\udde8\ud83c\udde6", "\ud83c\uddee\ud83c\udde8", "\ud83c\udde8\ud83c\uddfb", "\ud83c\udde7\ud83c\uddf6", "\ud83c\uddf0\ud83c\uddfe", "\ud83c\udde8\ud83c\uddeb", "\ud83c\uddf9\ud83c\udde9", "\ud83c\udde8\ud83c\uddf1", "\ud83c\udde8\ud83c\uddf3", "\ud83c\udde8\ud83c\uddfd", "\ud83c\udde8\ud83c\udde8", "\ud83c\udde8\ud83c\uddf4", "\ud83c\uddf0\ud83c\uddf2", "\ud83c\udde8\ud83c\uddec", "\ud83c\udde8\ud83c\udde9", "\ud83c\udde8\ud83c\uddf0", "\ud83c\udde8\ud83c\uddf7", "\ud83c\udde8\ud83c\uddee", "\ud83c\udded\ud83c\uddf7", "\ud83c\udde8\ud83c\uddfa", "\ud83c\udde8\ud83c\uddfc", "\ud83c\udde8\ud83c\uddfe", "\ud83c\udde8\ud83c\uddff", "\ud83c\udde9\ud83c\uddf0", "\ud83c\udde9\ud83c\uddef", "\ud83c\udde9\ud83c\uddf2", "\ud83c\udde9\ud83c\uddf4", "\ud83c\uddea\ud83c\udde8", "\ud83c\uddea\ud83c\uddec", "\ud83c\uddf8\ud83c\uddfb", "\ud83c\uddec\ud83c\uddf6", "\ud83c\uddea\ud83c\uddf7", "\ud83c\uddea\ud83c\uddea", "\ud83c\uddea\ud83c\uddf9", "\ud83c\uddea\ud83c\uddfa", "\ud83c\uddeb\ud83c\uddf0", "\ud83c\uddeb\ud83c\uddf4", "\ud83c\uddeb\ud83c\uddef", "\ud83c\uddeb\ud83c\uddee", "\ud83c\uddeb\ud83c\uddf7", "\ud83c\uddec\ud83c\uddeb", "\ud83c\uddf5\ud83c\uddeb", "\ud83c\uddf9\ud83c\uddeb", "\ud83c\uddec\ud83c\udde6", "\ud83c\uddec\ud83c\uddf2", "\ud83c\uddec\ud83c\uddea", "\ud83c\udde9\ud83c\uddea", "\ud83c\uddec\ud83c\udded", "\ud83c\uddec\ud83c\uddee", "\ud83c\uddec\ud83c\uddf7", "\ud83c\uddec\ud83c\uddf1", "\ud83c\uddec\ud83c\udde9", "\ud83c\uddec\ud83c\uddf5", "\ud83c\uddec\ud83c\uddfa", "\ud83c\uddec\ud83c\uddf9", "\ud83c\uddec\ud83c\uddec", "\ud83c\uddec\ud83c\uddf3", "\ud83c\uddec\ud83c\uddfc", "\ud83c\uddec\ud83c\uddfe", "\ud83c\udded\ud83c\uddf9", "\ud83c\udded\ud83c\uddf3", "\ud83c\udded\ud83c\uddf0", "\ud83c\udded\ud83c\uddfa", "\ud83c\uddee\ud83c\uddf8", "\ud83c\uddee\ud83c\uddf3", "\ud83c\uddee\ud83c\udde9", "\ud83c\uddee\ud83c\uddf7", "\ud83c\uddee\ud83c\uddf6", "\ud83c\uddee\ud83c\uddea", "\ud83c\uddee\ud83c\uddf2", "\ud83c\uddee\ud83c\uddf1", "\ud83c\uddee\ud83c\uddf9", "\ud83c\uddef\ud83c\uddf2", "\ud83c\uddef\ud83c\uddf5", "\ud83c\uddef\ud83c\uddea", "\ud83c\uddef\ud83c\uddf4", "\ud83c\uddf0\ud83c\uddff", "\ud83c\uddf0\ud83c\uddea", "\ud83c\uddf0\ud83c\uddee", "\ud83c\uddfd\ud83c\uddf0", "\ud83c\uddf0\ud83c\uddfc", "\ud83c\uddf0\ud83c\uddec", "\ud83c\uddf1\ud83c\udde6", "\ud83c\uddf1\ud83c\uddfb", "\ud83c\uddf1\ud83c\udde7", "\ud83c\uddf1\ud83c\uddf8", "\ud83c\uddf1\ud83c\uddf7", "\ud83c\uddf1\ud83c\uddfe", "\ud83c\uddf1\ud83c\uddee", "\ud83c\uddf1\ud83c\uddf9", "\ud83c\uddf1\ud83c\uddfa", "\ud83c\uddf2\ud83c\uddf4", "\ud83c\uddf2\ud83c\uddf0", "\ud83c\uddf2\ud83c\uddec", "\ud83c\uddf2\ud83c\uddfc", "\ud83c\uddf2\ud83c\uddfe", "\ud83c\uddf2\ud83c\uddfb", "\ud83c\uddf2\ud83c\uddf1", "\ud83c\uddf2\ud83c\uddf9", "\ud83c\uddf2\ud83c\udded", "\ud83c\uddf2\ud83c\uddf6", "\ud83c\uddf2\ud83c\uddf7", "\ud83c\uddf2\ud83c\uddfa", "\ud83c\uddfe\ud83c\uddf9", "\ud83c\uddf2\ud83c\uddfd", "\ud83c\uddeb\ud83c\uddf2", "\ud83c\uddf2\ud83c\udde9", "\ud83c\uddf2\ud83c\udde8", "\ud83c\uddf2\ud83c\uddf3", "\ud83c\uddf2\ud83c\uddea", "\ud83c\uddf2\ud83c\uddf8", "\ud83c\uddf2\ud83c\udde6", "\ud83c\uddf2\ud83c\uddff", "\ud83c\uddf2\ud83c\uddf2", "\ud83c\uddf3\ud83c\udde6", "\ud83c\uddf3\ud83c\uddf7", "\ud83c\uddf3\ud83c\uddf5", "\ud83c\uddf3\ud83c\uddf1", "\ud83c\uddf3\ud83c\udde8", "\ud83c\uddf3\ud83c\uddff", "\ud83c\uddf3\ud83c\uddee", "\ud83c\uddf3\ud83c\uddea", "\ud83c\uddf3\ud83c\uddec", "\ud83c\uddf3\ud83c\uddfa", "\ud83c\uddf3\ud83c\uddeb", "\ud83c\uddf0\ud83c\uddf5", "\ud83c\uddf2\ud83c\uddf5", "\ud83c\uddf3\ud83c\uddf4", "\ud83c\uddf4\ud83c\uddf2", "\ud83c\uddf5\ud83c\uddf0", "\ud83c\uddf5\ud83c\uddfc", "\ud83c\uddf5\ud83c\uddf8", "\ud83c\uddf5\ud83c\udde6", "\ud83c\uddf5\ud83c\uddec", "\ud83c\uddf5\ud83c\uddfe", "\ud83c\uddf5\ud83c\uddea", "\ud83c\uddf5\ud83c\udded", "\ud83c\uddf5\ud83c\uddf3", "\ud83c\uddf5\ud83c\uddf1", "\ud83c\uddf5\ud83c\uddf9", "\ud83c\uddf5\ud83c\uddf7", "\ud83c\uddf6\ud83c\udde6", "\ud83c\uddf7\ud83c\uddea", "\ud83c\uddf7\ud83c\uddf4", "\ud83c\uddf7\ud83c\uddfa", "\ud83c\uddf7\ud83c\uddfc", "\ud83c\uddfc\ud83c\uddf8", "\ud83c\uddf8\ud83c\uddf2", "\ud83c\uddf8\ud83c\uddf9", "\ud83c\uddf8\ud83c\udde6", "\ud83c\uddf8\ud83c\uddf3", "\ud83c\uddf7\ud83c\uddf8", "\ud83c\uddf8\ud83c\udde8", "\ud83c\uddf8\ud83c\uddf1", "\ud83c\uddf8\ud83c\uddec", "\ud83c\uddf8\ud83c\uddfd", "\ud83c\uddf8\ud83c\uddf0", "\ud83c\uddf8\ud83c\uddee", "\ud83c\uddec\ud83c\uddf8", "\ud83c\uddf8\ud83c\udde7", "\ud83c\uddf8\ud83c\uddf4", "\ud83c\uddff\ud83c\udde6", "\ud83c\uddf0\ud83c\uddf7", "\ud83c\uddf8\ud83c\uddf8", "\ud83c\uddea\ud83c\uddf8", "\ud83c\uddf1\ud83c\uddf0", "\ud83c\udde7\ud83c\uddf1", "\ud83c\uddf8\ud83c\udded", "\ud83c\uddf0\ud83c\uddf3", "\ud83c\uddf1\ud83c\udde8", "\ud83c\uddf5\ud83c\uddf2", "\ud83c\uddfb\ud83c\udde8", "\ud83c\uddf8\ud83c\udde9", "\ud83c\uddf8\ud83c\uddf7", "\ud83c\uddf8\ud83c\uddff", "\ud83c\uddf8\ud83c\uddea", "\ud83c\udde8\ud83c\udded", "\ud83c\uddf8\ud83c\uddfe", "\ud83c\uddf9\ud83c\uddfc", "\ud83c\uddf9\ud83c\uddef", "\ud83c\uddf9\ud83c\uddff", "\ud83c\uddf9\ud83c\udded", "\ud83c\uddf9\ud83c\uddf1", "\ud83c\uddf9\ud83c\uddec", "\ud83c\uddf9\ud83c\uddf0", "\ud83c\uddf9\ud83c\uddf4", "\ud83c\uddf9\ud83c\uddf9", "\ud83c\uddf9\ud83c\uddf3", "\ud83c\uddf9\ud83c\uddf7", "\ud83c\uddf9\ud83c\uddf2", "\ud83c\uddf9\ud83c\udde8", "\ud83c\uddf9\ud83c\uddfb", "\ud83c\uddfb\ud83c\uddee", "\ud83c\uddfa\ud83c\uddec", "\ud83c\uddfa\ud83c\udde6", "\ud83c\udde6\ud83c\uddea", "\ud83c\uddec\ud83c\udde7", "\ud83c\uddfa\ud83c\uddf8", "\ud83c\uddfa\ud83c\uddfe", "\ud83c\uddfa\ud83c\uddff", "\ud83c\uddfb\ud83c\uddfa", "\ud83c\uddfb\ud83c\udde6", "\ud83c\uddfb\ud83c\uddea", "\ud83c\uddfb\ud83c\uddf3", "\ud83c\uddfc\ud83c\uddeb", "\ud83c\uddea\ud83c\udded", "\ud83c\uddfe\ud83c\uddea", "\ud83c\uddff\ud83c\uddf2", "\ud83c\uddff\ud83c\uddfc"};
			EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 1, "\uD83D\uDD3A", "\uD83D\uDD3A", 0, true, MARSHMALLOW_TRIANGLE) );
		}

		//Custom templates

		//Reddit emoticons
		{
			String[] newStrings = new String[]{
					"ʘ‿ʘ",
					"ಠ_ಠ",
					"(╯°□°）╯︵ ┻━┻",
					"┬─┬ ノ( ゜-゜ノ)",
					"┬─┬⃰͡ (ᵔᵕᵔ͜ )",
					"(ノ`Д´)ノ┻┻",
					"ლ(｀ー´ლ)",
					"ʕ•ᴥ•ʔ",
					"(｡◕‿◕｡)",
					"（　ﾟДﾟ）",
					"¯\\_(ツ)_/¯",
					"¯\\(°_o)/¯",
					"(`･ω･´)",
					"(╬ ಠ益ಠ)",
					"☜(⌒▽⌒)☞",
					"ε=ε=ε=┌(;*´Д`)ﾉ",
					"ヽ(´▽`)/",
					"ヽ(´ー｀)ノ",
					"ᵒᴥᵒ#",
					"V●ᴥ●V",
					"ฅ^•ﻌ•^ฅ",
					"ಠ‿ಠ",
					"( ͡° ͜ʖ ͡°)",
					"ಥ_ಥ",
					"ಥ﹏ಥ",
					"٩◔̯◔۶",
					"ᕙ(⇀‸↼‶)ᕗ",
					"ᕦ(ò_óˇ)ᕤ",
					"⊂(◉‿◉)つ",
					"q(❂‿❂)p",
					"⊙﹏⊙",
					"¯\\_(⊙︿⊙)_/¯",
					"°‿‿°",
					"¿ⓧ_ⓧﮌ",
					"(⊙.☉)7",
					"(´･_･`)",
					"щ（ﾟДﾟщ）",
					"٩(͡๏_๏)۶",
					"ఠ_ఠ",
					"ᕕ( ᐛ )ᕗ",
					"(⊙_◎)",
					"ミ●﹏☉ミ",
					"༼∵༽ ༼⍨༽ ༼⍢༽ ༼⍤༽",
					"ヽ༼ ಠ益ಠ ༽ﾉ",
					"t(-_-t)",
					"(ಥ⌣ಥ)",
					"(づ￣ ³￣)づ",
					"(づ｡◕‿‿◕｡)づ",
					"(ノಠ ∩ಠ)ノ彡( \\o°o)\\",
					"｡ﾟ( ﾟஇ‸இﾟ)ﾟ｡",
					"༼ ༎ຶ ෴ ༎ຶ༽",
					"“ヽ(´▽｀)ノ”",
					"┌(ㆆ㉨ㆆ)ʃ",
					"눈_눈",
					"( ఠൠఠ )ﾉ",
					"(๑•́ ₃ •̀๑) ",
					"⁽⁽ଘ( ˊᵕˋ )ଓ⁾⁾",
					"◔_◔",
					"♥‿♥",
					"ԅ(≖‿≖ԅ)",
					"( ˘ ³˘)♥ ",
					"( ˇ෴ˇ )",
					"ヾ(-_- )ゞ",
					"ʕ •́؈•̀ ₎",
					"ლ(•́•́ლ)",
					" {•̃_•̃}",
					"ᵔᴥᵔ)",
					"(Ծ‸ Ծ)",
					"(•̀ᴗ•́)و ̑̑",
					"[¬º-°]¬",
					"(☞ﾟヮﾟ)☞",
					"''⌐(ಠ۾ಠ)¬'''",
					"(っ•́｡•́)♪♬",
					"(҂◡_◡)",
					"ƪ(ړײ)‎ƪ​", };
			EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 4, "ಠ_ಠ", "ಠ_ಠ", 0,false, MARSHMALLOW_REDDIT) );
		}


		{
			String[] newStrings = new String[]{
					"( ・ω・)",
					"ヾ(❀╹◡╹)ﾉﾞ❀",
					"ヾ(｡･ω･)ﾉ",
					"(*´･ω･｀)b",
					"∠(´д｀)",
					"ლ(ﾟДﾟლ)",
					"m(_ _)m",
					"(´･ω･`)",
					"(`･ω･´)",
					"(｀-´)>",
					"(´；ω；`)",
					"ヽ(´ー｀)ﾉ",
					"ヽ(`Д´)ﾉ",
					"(＃ﾟДﾟ)",
					"（ ´Д｀）",
					"（　ﾟДﾟ）",
					"┐('～`；)┌",
					"（´∀｀）",
					"（　´_ゝ`）",
					"Σ(゜д゜;)",
					"( ﾟヮﾟ)",
					"(((( ；ﾟДﾟ)))",
					"Σ(ﾟДﾟ)",
					"( ´∀｀)σ)∀`)",
					"( ﾟдﾟ)",
					"(´ー`)y-~~",
					"m9(・∀・)",
					"('A`)",
					"（ ´,_ゝ`)",
					"(ﾟДﾟ;≡;ﾟДﾟ)",
					"(ﾟдﾟ)",
					"(ﾟ⊿ﾟ)",
					"щ(ﾟДﾟщ) (屮ﾟДﾟ)屮",
					"（・∀・）",
					"（・Ａ・）",
					"(ﾟ∀ﾟ)",
					"（ つ Д ｀）",
					"エェェ(´д｀)ェェエ",
					"(￣ー￣)",
					"[ﾟдﾟ]",
					"d(*⌒▽⌒*)b",
					"＿|￣|○",
					"(╬ ಠ益ಠ)",
					"(≧ロ≦)",
					"(ΘεΘ;)",
					"┌(；`～,)┐",
					"ヽ(´▽`)/",
					"^ㅂ^",
					"(l'o'l)",
					"ヽ(ｏ`皿′ｏ)ﾉ",
					"(☞ﾟヮﾟ)☞",
					"☜(ﾟヮﾟ☜)",
					"☜(⌒▽⌒)☞", };
			EMOJI_PANEL_TEMPLATES.add( getEmojiPanel(newStrings, 4, "( ・ω・)", "( ・ω・)", 0,false, MARSHMALLOW_JAPANESE) );
		}


		//Stock  emoticons
		//After everything else because I doubt anyone uses them
		{
			String[] newStrings = new String[]{":-)",":-(",";-)",":-P",":-D",":-|",":-*",":O","B-)",":-$",":3",":-/",":-[","O:-)",":-\\",":'(","(^_-)","(-:",":-{"};
			EMOJI_PANEL_TEMPLATES.add( getEmojiPanel(newStrings, 2, ":-)", ":-)", 0,false, MARSHMALLOW_EMOTICONS) );
		}

		//Set display order
		int iterator = 0;
		for (DB_EmojiPanelItem item : EMOJI_PANEL_TEMPLATES)
		{
			item.set_index(iterator);
			iterator++;
		}

	}


	private static void initFancyPanels()
	{
		//Stolen from Swiftkey

		//Stock Emoji Smiley
		{
			String[] newStrings = new String[]{

					"😀","😁","😂","🤣","😃","😄","😅","😆","😉","😊","😋","😎","😍","😘","🥰","😗","😙","😚","☺","🙂","🤗","🤩","🤔","🤨","😐","😑","😶","🙄","😏","😣","😥","😮","🤐","😯","😪","😫","😴","😌","😛","😜","😝","🤤","😒","😓","😔","😕","🙃","🤑","😲","☹","🙁","😖","😞","😟","😤","😢","😭","😦","😧","😨","😩","🤯","😬","😰","😱","🥵","🥶","😳","🤪","😵","😡","😠","🤬","😷","🤒","🤕","🤢","🤮","🤧","😇","🤠","🤡","🥳","🥴","🥺","🤥","🤫","🤭","🧐","🤓","😈","👿","👹","👺","💀","☠","👻","👽","👾","🤖","💩","😺","😸","😹","😻","😼","😽","🙀","😿","😾","👶","👦","👧","👨","👩","👴","👵","👨‍⚕️","👩‍⚕️","👨‍🎓","👩‍🎓","👨‍⚖️","👩‍⚖️","👨‍🌾","👩‍🌾","👨‍🍳","👩‍🍳","👨‍🔧","👩‍🔧","👨‍🏭","👩‍🏭","👨‍💼","👩‍💼","👨‍🔬","👩‍🔬","👨‍💻","👩‍💻","👨‍🎤","👩‍🎤","👨‍🎨","👩‍🎨","👨‍✈️","👩‍✈️","👨‍🚀","👩‍🚀","👨‍🚒","👩‍🚒","👮","👮‍♂️","👮‍♀️","🕵","🕵️‍♂️","🕵️‍♀️","💂","💂‍♂️","💂‍♀️","👷","👷‍♂️","👷‍♀️","🤴","👸","👳","👳‍♂️","👳‍♀️","👲","🧕","🧔","👱","👱‍♂️","👱‍♀️","👨‍🦰","👩‍🦰","👨‍🦱","👩‍🦱","👨‍🦲","👩‍🦲","👨‍🦳","👩‍🦳","🤵","👰","🤰","🤱","👼","🎅","🤶","🦸","🦸‍♀️","🦸‍♂️","🦹","🦹‍♀️","🦹‍♂️","🧙","🧙‍♀️","🧙‍♂️","🧚‍♀️","🧚‍♂️","🧛","🧛‍♀️","🧛‍♂️","🧜","🧜‍♀️","🧜‍♂️","🧝","🧝‍♀️","🧝‍♂️","🧞","🧞‍♀️","🧞‍♂️","🧟","🧟‍♀️","🧟‍♂️","🙍","🙍‍♂️","🙍‍♀️","🙎","🙎‍♂️","🙎‍♀️","🙅","🙅‍♂️","🙅‍♀️","🙆","🙆‍♂️","🙆‍♀️","💁","💁‍♂️","💁‍♀️","🙋","🙋‍♂️","🙋‍♀️","🙇","🙇‍♂️","🙇‍♀️","🤦","🤦‍♂️","🤦‍♀️","🤷","🤷‍♂️","🤷‍♀️","💆","💆‍♂️","💆‍♀️","💇","💇‍♂️","💇‍♀️","🚶","🚶‍♂️","🚶‍♀️","🏃","🏃‍♂️","🏃‍♀️","💃","🕺","👯","👯‍♂️","👯‍♀️","🧖","🧖‍♀️","🧖‍♂️","🧘","🕴","🗣","👤","👥","👫","👬","👭","💏","👨‍❤️‍💋‍👨","👩‍❤️‍💋‍👩","💑","👨‍❤️‍👨","👩‍❤️‍👩","👪","👨‍👩‍👦","👨‍👩‍👧","👨‍👩‍👧‍👦","👨‍👩‍👦‍👦","👨‍👩‍👧‍👧","👨‍👨‍👦","👨‍👨‍👧","👨‍👨‍👧‍👦","👨‍👨‍👦‍👦","👨‍👨‍👧‍👧","👩‍👩‍👦","👩‍👩‍👧","👩‍👩‍👧‍👦","👩‍👩‍👦‍👦","👩‍👩‍👧‍👧","👨‍👦","👨‍👦‍👦","👨‍👧","👨‍👧‍👦","👨‍👧‍👧","👩‍👦","👩‍👦‍👦","👩‍👧","👩‍👧‍👦","👩‍👧‍👧","🤳","💪","🦵","🦶","👈","👉","☝","👆","🖕","👇","✌","🤞","🖖","🤘","🤙","🖐","✋","👌","👍","👎","✊","👊","🤛","🤜","🤚","👋","🤟","✍","👏","👐","🙌","🤲","🙏","🤝","💅","👂","👃","👣","👀","👁","🧠","🦴","🦷","👅","👄","💋","👓","🕶","🥽","🥼","👔","👕","👖","🧣","🧤","🧥","🧦","👗","👘","👙","👚","👛","👜","👝","🎒","👞","👟","🥾","🥿","👠","👡","👢","👑","👒","🎩","🎓","🧢","⛑","💄","💍","🧳","🌂","☂","💼","🧵","🧶",

			};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel(newStrings, 1, "☺", "☺", 0, true, SMILEY) );
		}

		//Stock Emoji nature
		{
			String[] newStrings = new String[]{

					"🙈", "🙉", "🙊", "💥", "💦", "💨", "💫", "🐵", "🐒", "🦍", "🐶", "🐕", "🐩", "🐺", "🦊", "🦝", "🐱", "🐈", "🦁", "🐯", "🐅", "🐆", "🐴", "🐎", "🦄", "🦓", "🐮", "🐂", "🐃", "🐄", "🐷", "🐖", "🐗", "🐽", "🐏", "🐑", "🐐", "🐪", "🐫", "🦙", "🦒", "🐘", "🦏", "🦛", "🐭", "🐁", "🐀", "🐹", "🐰", "🐇", "🐿", "🦔", "🦇", "🐻", "🐨", "🐼", "🦘", "🦡", "🐾", "🦃", "🐔", "🐓", "🐣", "🐤", "🐥", "🐦", "🐧", "🕊", "🦅", "🦆", "🦢", "🦉", "🦚", "🦜", "🐸", "🐊", "🐢", "🦎", "🐍", "🐲", "🐉", "🦕", "🦖", "🐳", "🐋", "🐬", "🐟", "🐠", "🐡", "🦈", "🐙", "🐚", "🦀", "🦞", "🦐", "🦑", "🐌", "🦋", "🐛", "🐜", "🐝", "🐞", "🦗", "🕷", "🕸", "🦂", "🦟", "🦠", "💐", "🌸", "💮", "🏵", "🌹", "🥀", "🌺", "🌻", "🌼", "🌷", "🌱", "🌲", "🌳", "🌴", "🌵", "🌾", "🌿", "☘", "🍀", "🍁", "🍂", "🍃", "🍄", "🌰", "🌍", "🌎", "🌏", "🌐", "🌑", "🌒", "🌓", "🌔", "🌕", "🌖", "🌗", "🌘", "🌙", "🌚", "🌛", "🌜", "☀", "🌝", "🌞", "⭐", "🌟", "🌠", "☁", "⛅", "⛈", "🌤", "🌥", "🌦", "🌧", "🌨", "🌩", "🌪", "🌫", "🌬", "🌈", "☂", "☔", "⚡", "❄", "☃", "⛄", "☄", "🔥", "💧", "🌊", "🎄", "✨", "🎋", "🎍",


			};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 1, "\uD83D\uDC3B", "\uD83D\uDC3B", 0, true, NATURE) );
		}

		//Stock Emoji food
		{
			String[] newStrings = new String[]{"🍇","🍈","🍉","🍊","🍋","🍌","🍍","🥭","🍎","🍏","🍐","🍑","🍒","🍓","🥝","🍅","🥥","🥑","🍆","🥔","🥕","🌽","🌶","🥒","🥬","🥦","🍄","🥜","🌰","🍞","🥐","🥖","🥨","🥯","🥞","🧀","🍖","🍗","🥩","🥓","🍔","🍟","🍕","🌭","🥪","🌮","🌯","🥙","🍳","🥘","🍲","🥣","🥗","🍿","🧂","🥫","🍱","🍘","🍙","🍚","🍛","🍜","🍝","🍠","🍢","🍣","🍤","🍥","🥮","🍡","🥟","🥠","🥡","🍦","🍧","🍨","🍩","🍪","🎂","🍰","🧁","🥧","🍫","🍬","🍭","🍮","🍯","🍼","🥛","☕","🍵","🍶","🍾","🍷","🍸","🍹","🍺","🍻","🥂","🥃","🥤","🥢","🍽","🍴","🥄",};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel(newStrings, 1, "\uD83E\uDD64", "\uD83E\uDD64", 0, true, FOOD));
		}

		//Stock emoji travel
		{
			String[] newStrings = new String[]{"🚣","🏎","🏍","🗾","🏔","⛰","🌋","🗻","🏕","🏖","🏜","🏝","🏞","🏟","🏛","🏗","🏘","🏚","🏠","🏡","🏢","🏣","🏤","🏥","🏦","🏨","🏩","🏪","🏫","🏬","🏭","🏯","🏰","💒","🗼","🗽","⛪","🕌","🕍","⛩","🕋","⛲","⛺","🌁","🌃","🏙","🌄","🌅","🌆","🌇","🌉","🌌","🎠","🎡","🎢","🚂","🚃","🚄","🚅","🚆","🚇","🚈","🚉","🚊","🚝","🚞","🚋","🚌","🚍","🚎","🚐","🚑","🚒","🚓","🚔","🚕","🚖","🚗","🚘","🚚","🚛","🚜","🚲","🛴","🛵","🚏","🛤","⛽","🚨","🚥","🚦","🚧","⚓","⛵","🚤","🛳","⛴","🛥","🚢","✈","🛩","🛫","🛬","💺","🚁","🚟","🚠","🚡","🛰","🚀","🛸","🌠","⛱","🎆","🎇","🎑","💴","💵","💶","💷","🗿","🛂","🛃","🛄","🛅",};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 1, "\uD83C\uDFD9", "\uD83C\uDFD9", 0, true, TRAVEL) );
		}


		//Stock emoji activities
		{
			String[] newStrings = new String[]{"🧗","🧗‍♀️","🧗‍♂️","🧘","🧘‍♀️","🧘‍♂️","🕴","🏇","⛷","🏂","🏌","🏌️‍♂️","🏌️‍♀️","🏄","🏄‍♂️","🏄‍♀️","🚣","🚣‍♂️","🚣‍♀️","🏊","🏊‍♂️","🏊‍♀️","⛹","⛹️‍♂️","⛹️‍♀️","🏋","🏋️‍♂️","🏋️‍♀️","🚴","🚴‍♂️","🚴‍♀️","🚵","🚵‍♂️","🚵‍♀️","🤸","🤸‍♂️","🤸‍♀️","🤼","🤼‍♂️","🤼‍♀️","🤽","🤽‍♂️","🤽‍♀️","🤾","🤾‍♂️","🤾‍♀️","🤹","🤹‍♂️","🤹‍♀️","🎪","🛹","🎗","🎟","🎫","🎖","🏆","🏅","🥇","🥈","🥉","⚽","⚾","🥎","🏀","🏐","🏈","🏉","🎾","🥏","🎳","🏏","🏑","🏒","🥍","🏓","🏸","🥊","🥋","⛳","⛸","🎣","🎽","🎿","🛷","🥌","🎯","🎱","🎮","🎰","🎲","🧩","♟","🎭","🎨","🎼","🎤","🎧","🎷","🎸","🎹","🎺","🎻","🥁","🎬","🏹","🧵","🧶",};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 1, "⚽", "⚽", 0, true, ACTIVITIES) );
		}

		//Stock emoji objects
		{
			String[] newStrings = new String[]{"🛀","🛌","💌","💣","🕳","🛍","📿","💎","🔪","🏺","🗺","🧭","🧱","💈","🛢","🛎","🧳","⌛","⏳","⌚","⏰","⏱","⏲","🕰","🌡","⛱","🧨","🎈","🎉","🎊","🎎","🎏","🎐","🧧","🎀","🎁","🔮","🧿","🕹","🧸","🖼","📯","🎙","🎚","🎛","📻","📱","📲","☎","📞","📟","📠","🔋","🔌","💻","🖥","🖨","⌨","🖱","🖲","💽","💾","💿","📀","🧮","🎥","🎞","📽","📺","📷","📸","📹","📼","🔍","🔎","🕯","💡","🔦","🏮","📔","📕","📖","📗","📘","📙","📚","📓","📃","📜","📄","📰","🗞","📑","🔖","🏷","💰","💴","💵","💶","💷","💸","💳","🧾","✉","📧","📨","📩","📤","📥","📦","📫","📪","📬","📭","📮","🗳","✏","✒","🖋","🖊","🖌","🖍","📝","📁","📂","🗂","📅","📆","🗒","🗓","📇","📈","📉","📊","📋","📌","📍","📎","🖇","📏","📐","✂","🗃","🗄","🗑","🔒","🔓","🔏","🔐","🔑","🗝","🔨","⛏","⚒","🛠","🗡","⚔","🔫","🛡","🔧","🔩","⚙","🗜","⚖","🔗","⛓","🧰","🧲","⚗","🧪","🧫","🧬","🧯","🔬","🔭","📡","💉","💊","🚪","🛏","🛋","🚽","🚿","🛁","🧴","🧵","🧶","🧷","🧹","🧺","🧻","🧼","🧽","🚬","⚰","⚱","🗿","🚰",};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 1, "\uD83D\uDCA1", "\uD83D\uDCA1", 0, true, OBJECTS) );
		}

		//Stock emoji symbols
		{
			String[] newStrings = new String[]{"👁‍🗨","💘","❤","💓","💔","💕","💖","💗","💙","💚","💛","🧡","💜","🖤","💝","💞","💟","❣","💤","💢","💬","🗯","💭","💮","♨","💈","🛑","🕛","🕧","🕐","🕜","🕑","🕝","🕒","🕞","🕓","🕟","🕔","🕠","🕕","🕡","🕖","🕢","🕗","🕣","🕘","🕤","🕙","🕥","🕚","🕦","🌀","♠","♥","♦","♣","🃏","🀄","🎴","🔇","🔈","🔉","🔊","📢","📣","📯","🔔","🔕","🎵","🎶","🏧","🚮","🚰","♿","🚹","🚺","🚻","🚼","🚾","⚠","🚸","⛔","🚫","🚳","🚭","🚯","🚱","🚷","🔞","☢","☣","⬆","↗","➡","↘","⬇","↙","⬅","↖","↕","↔","↩","↪","⤴","⤵","🔃","🔄","🔙","🔚","🔛","🔜","🔝","🛐","⚛","♾","🕉","✡","☸","☯","✝","☦","☪","☮","🕎","🔯","♈","♉","♊","♋","♌","♍","♎","♏","♐","♑","♒","♓","⛎","🔀","🔁","🔂","▶","⏩","◀","⏪","🔼","⏫","🔽","⏬","⏹","⏏","🎦","🔅","🔆","📶","📳","📴","♻","🔱","📛","🔰","⭕","✅","☑","✔","✖","❌","❎","➕","➖","➗","➰","➿","〽","✳","✴","❇","‼","⁉","❓","❔","❕","❗","©","®","™","#️⃣","0️⃣","1️⃣","2️⃣","3️⃣","4️⃣","5️⃣","6️⃣","7️⃣","8️⃣","9️⃣","🔟","💯","🔠","🔡","🔢","🔣","🔤","🅰","🆎","🅱","🆑","🆒","🆓","ℹ","🆔","Ⓜ","🆕","🆖","🅾","🆗","🅿","🆘","🆙","🆚","🈁","🈂","🈷","🈶","🈯","🉐","🈹","🈚","🈲","🉑","🈸","🈴","🈳","㊗","㊙","🈺","🈵","▪","▫","◻","◼","◽","◾","⬛","⬜","🔶","🔷","🔸","🔹","🔺","🔻","💠","🔲","🔳","⚪","⚫","🔴","🔵",};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 1, "\uD83D\uDD23", "\uD83D\uDD23", 0, true, SYMBOLS) );
		}

		//Stock emoji flags
		{
			String[] newStrings = new String[]{"🏁","🚩","🎌","🏴","🏳","🏳️‍🌈","🏴‍☠️","🇦🇨","🇦🇩","🇦🇪","🇦🇫","🇦🇬","🇦🇮","🇦🇱","🇦🇲","🇦🇴","🇦🇶","🇦🇷","🇦🇸","🇦🇹","🇦🇺","🇦🇼","🇦🇽","🇦🇿","🇧🇦","🇧🇧","🇧🇩","🇧🇪","🇧🇫","🇧🇬","🇧🇭","🇧🇮","🇧🇯","🇧🇱","🇧🇲","🇧🇳","🇧🇴","🇧🇶","🇧🇷","🇧🇸","🇧🇹","🇧🇻","🇧🇼","🇧🇾","🇧🇿","🇨🇦","🇨🇨","🇨🇩","🇨🇫","🇨🇬","🇨🇭","🇨🇮","🇨🇰","🇨🇱","🇨🇲","🇨🇳","🇨🇴","🇨🇵","🇨🇷","🇨🇺","🇨🇻","🇨🇼","🇨🇽","🇨🇾","🇨🇿","🇩🇪","🇩🇬","🇩🇯","🇩🇰","🇩🇲","🇩🇴","🇩🇿","🇪🇦","🇪🇨","🇪🇪","🇪🇬","🇪🇭","🇪🇷","🇪🇸","🇪🇹","🇪🇺","🇫🇮","🇫🇯","🇫🇰","🇫🇲","🇫🇴","🇫🇷","🇬🇦","🇬🇧","🇬🇩","🇬🇪","🇬🇫","🇬🇬","🇬🇭","🇬🇮","🇬🇱","🇬🇲","🇬🇳","🇬🇵","🇬🇶","🇬🇷","🇬🇸","🇬🇹","🇬🇺","🇬🇼","🇬🇾","🇭🇰","🇭🇲","🇭🇳","🇭🇷","🇭🇹","🇭🇺","🇮🇨","🇮🇩","🇮🇪","🇮🇱","🇮🇲","🇮🇳","🇮🇴","🇮🇶","🇮🇷","🇮🇸","🇮🇹","🇯🇪","🇯🇲","🇯🇴","🇯🇵","🇰🇪","🇰🇬","🇰🇭","🇰🇮","🇰🇲","🇰🇳","🇰🇵","🇰🇷","🇰🇼","🇰🇾","🇰🇿","🇱🇦","🇱🇧","🇱🇨","🇱🇮","🇱🇰","🇱🇷","🇱🇸","🇱🇹","🇱🇺","🇱🇻","🇱🇾","🇲🇦","🇲🇨","🇲🇩","🇲🇪","🇲🇫","🇲🇬","🇲🇭","🇲🇰","🇲🇱","🇲🇲","🇲🇳","🇲🇴","🇲🇵","🇲🇶","🇲🇷","🇲🇸","🇲🇹","🇲🇺","🇲🇻","🇲🇼","🇲🇽","🇲🇾","🇲🇿","🇳🇦","🇳🇨","🇳🇪","🇳🇫","🇳🇬","🇳🇮","🇳🇱","🇳🇴","🇳🇵","🇳🇷","🇳🇺","🇳🇿","🇴🇲","🇵🇦","🇵🇪","🇵🇫","🇵🇬","🇵🇭","🇵🇰","🇵🇱","🇵🇲","🇵🇳","🇵🇷","🇵🇸","🇵🇹","🇵🇼","🇵🇾","🇶🇦","🇷🇪","🇷🇴","🇷🇸","🇷🇺","🇷🇼","🇸🇦","🇸🇧","🇸🇨","🇸🇩","🇸🇪","🇸🇬","🇸🇭","🇸🇮","🇸🇯","🇸🇰","🇸🇱","🇸🇲","🇸🇳","🇸🇴","🇸🇷","🇸🇸","🇸🇹","🇸🇻","🇸🇽","🇸🇾","🇸🇿","🇹🇦","🇹🇨","🇹🇩","🇹🇫","🇹🇬","🇹🇭","🇹🇯","🇹🇰","🇹🇱","🇹🇲","🇹🇳","🇹🇴","🇹🇷","🇹🇹","🇹🇻","🇹🇼","🇹🇿","🇺🇦","🇺🇬","🇺🇲","🇺🇳","🇺🇸","🇺🇾","🇺🇿","🇻🇦","🇻🇨","🇻🇪","🇻🇬","🇻🇮","🇻🇳","🇻🇺","🇼🇫","🇼🇸","🇽🇰","🇾🇪","🇾🇹","🇿🇦","🇿🇲","🇿🇼","🏴󠁧󠁢󠁥󠁮󠁧󠁿","🏴󠁧󠁢󠁳󠁣󠁴󠁿","🏴󠁧󠁢󠁷󠁬󠁳󠁿",};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 1, "\u2690", "\u2690", 0, true, FLAGS) );
		}

		//Custom templates

		//Reddit emoticons
		{
			String[] newStrings = new String[]{
					"ʘ‿ʘ","ಠ_ಠ","(╯°□°）╯︵┻━┻","┬─┬ノ(゜-゜ノ)","┬─┬⃰͡(ᵔᵕᵔ͜)","(ノ`Д´)ノ┻┻","ლ(｀ー´ლ)","ʕ•ᴥ•ʔ","(｡◕‿◕｡)","（ﾟДﾟ）","¯\\_(ツ)_/¯","¯\\(°_o)/¯","(`･ω･´)","(╬ಠ益ಠ)","☜(⌒▽⌒)☞","ε=ε=ε=┌(;*´Д`)ﾉ","ヽ(´▽`)/","ヽ(´ー｀)ノ","ᵒᴥᵒ#","V●ᴥ●V","ฅ^•ﻌ•^ฅ","ಠ‿ಠ","(͡°͜ʖ͡°)","ಥ_ಥ","ಥ﹏ಥ","٩◔̯◔۶","ᕙ(⇀‸↼‶)ᕗ","ᕦ(ò_óˇ)ᕤ","⊂(◉‿◉)つ","q(❂‿❂)p","⊙﹏⊙","¯\\_(⊙︿⊙)_/¯","°‿‿°","¿ⓧ_ⓧﮌ","(⊙.☉)7","(´･_･`)","щ（ﾟДﾟщ）","٩(͡๏_๏)۶","ఠ_ఠ","ᕕ(ᐛ)ᕗ","(⊙_◎)","ミ●﹏☉ミ","༼∵༽༼⍨༽༼⍢༽༼⍤༽","ヽ༼ಠ益ಠ༽ﾉ","t(-_-t)","(ಥ⌣ಥ)","(づ￣³￣)づ","(づ｡◕‿‿◕｡)づ","(ノಠ∩ಠ)ノ彡(\\o°o)\\","｡ﾟ(ﾟஇ‸இﾟ)ﾟ｡","༼༎ຶ෴༎ຶ༽","“ヽ(´▽｀)ノ”","┌(ㆆ㉨ㆆ)ʃ","눈_눈","(ఠൠఠ)ﾉ","(๑•́₃•̀๑)","⁽⁽ଘ(ˊᵕˋ)ଓ⁾⁾","◔_◔","♥‿♥","ԅ(≖‿≖ԅ)","(˘³˘)♥","(ˇ෴ˇ)","ヾ(-_-)ゞ","ʕ•́؈•̀₎","ლ(•́•́ლ)","{•̃_•̃}","ᵔᴥᵔ)","(Ծ‸Ծ)","(•̀ᴗ•́)و̑̑","[¬º-°]¬","(☞ﾟヮﾟ)☞","''⌐(ಠ۾ಠ)¬'''","(っ•́｡•́)♪♬","(҂◡_◡)","ƪ(ړײ)‎ƪ​",};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel( newStrings, 4, "ಠ_ಠ", "ಠ_ಠ", 0,false, REDDIT_EMOTICONS) );
		}


		{
			String[] newStrings = new String[]{
					"(・ω・)","ヾ(❀╹◡╹)ﾉﾞ❀","ヾ(｡･ω･)ﾉ","(*´･ω･｀)b","∠(´д｀)","ლ(ﾟДﾟლ)","m(__)m","(´･ω･`)","(`･ω･´)","(｀-´)>","(´；ω；`)","ヽ(´ー｀)ﾉ","ヽ(`Д´)ﾉ","(＃ﾟДﾟ)","（´Д｀）","（ﾟДﾟ）","┐('～`；)┌","（´∀｀）","（´_ゝ`）","Σ(゜д゜;)","(ﾟヮﾟ)","((((；ﾟДﾟ)))","Σ(ﾟДﾟ)","(´∀｀)σ)∀`)","(ﾟдﾟ)","(´ー`)y-~~","m9(・∀・)","('A`)","（´,_ゝ`)","(ﾟДﾟ;≡;ﾟДﾟ)","(ﾟдﾟ)","(ﾟ⊿ﾟ)","щ(ﾟДﾟщ)(屮ﾟДﾟ)屮","（・∀・）","（・Ａ・）","(ﾟ∀ﾟ)","（つД｀）","エェェ(´д｀)ェェエ","(￣ー￣)","[ﾟдﾟ]","d(*⌒▽⌒*)b","＿|￣|○","(╬ಠ益ಠ)","(≧ロ≦)","(ΘεΘ;)","┌(；`～,)┐","ヽ(´▽`)/","^ㅂ^","(l'o'l)","ヽ(ｏ`皿′ｏ)ﾉ","(☞ﾟヮﾟ)☞","☜(ﾟヮﾟ☜)","☜(⌒▽⌒)☞",};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel(newStrings, 4, "( ・ω・)", "( ・ω・)", 0,false, JAPANESE_EMOTICONSs) );
		}


		//Stock  emoticons
		//After everything else because I doubt anyone uses them
		{
			String[] newStrings = new String[]{":-)",":-(",";-)",":-P",":-D",":-|",":-*",":O","B-)",":-$",":3",":-/",":-[","O:-)",":-\\",":'(","(^_-)","(-:",":-{"};
			FANCY_EMOJI_PANEL_TEMPLATES.add( getEmojiPanel(newStrings, 2, ":-)", ":-)", 0,false, EMOTICONS) );
		}

		//Set display order
		int iterator = 0;
		for (DB_EmojiPanelItem item : FANCY_EMOJI_PANEL_TEMPLATES)
		{
			item.set_index(iterator);
			iterator++;
		}

	}



	//Returns a deep copy
	private static ArrayList<DB_EmojiPanelItem> getFancyEmojiPanelTemplates()
	{
		if (FANCY_EMOJI_PANEL_TEMPLATES.isEmpty())
			initFancyPanels();

		ArrayList<DB_EmojiPanelItem> returnItems = new ArrayList<DB_EmojiPanelItem>();
		for (DB_EmojiPanelItem templateItem : FANCY_EMOJI_PANEL_TEMPLATES)
		{
			returnItems.add( new DB_EmojiPanelItem(templateItem));
		}

		return returnItems;
	}

	//Returns a deep copy
	private static ArrayList<DB_EmojiPanelItem> getEmojiPanelTemplates()
	{
		if (EMOJI_PANEL_TEMPLATES.isEmpty())
			initPanels();

		ArrayList<DB_EmojiPanelItem> returnItems = new ArrayList<DB_EmojiPanelItem>();
		for (DB_EmojiPanelItem templateItem : EMOJI_PANEL_TEMPLATES)
		{
			returnItems.add( new DB_EmojiPanelItem(templateItem));
		}

		return returnItems;
	}

	public static DB_EmojiPanelItem getRecentsPanelItem()
	{

		DB_EmojiPanelItem recentsPanel = new DB_EmojiPanelItem(0,0,1,"\uD83D\uDD52", "\uD83D\uDD52",0,1);
		recentsPanel.set_source(EmojiPanelItem.PANEL_SOURCE.RECENTS);

		return recentsPanel;
	}

	public static ArrayList<DB_EmojiPanelItem> getForEmojiPanelVersion(EmojiPanelVersion version)
	{
		switch(version)
		{
			case PRE_NOUGAT:
				return getEmojiPanelTemplates();
			case POST_NOUGAT:
					return getFancyEmojiPanelTemplates();
			default:
				return getFancyEmojiPanelTemplates();
		}
	}


	private static DB_EmojiPanelItem getEmojiPanel(String[] strings, int colWidth, String icon, String caption, int index, boolean emojiOnly, int identifier)
	{
		DB_EmojiPanelItem newItem = new DB_EmojiPanelItem( -1,index,colWidth,icon, caption, 0, 1 );
		newItem.set_panel_identifier(identifier);
		//newItem.set_items_as_strings( strings);

		//We may want to replace these in the dictionary later, for when android updates and adds
		//new emoji and stuff. Panels with their source set to TEMPLATE should not be modifiable by the user.
		newItem.set_source(DB_EmojiPanelItem.PANEL_SOURCE.TEMPLATE );

		Log.i(LOGTAG, "Consdering "+strings.length+" emoji for panel "+icon);

		for (String string : strings)
		{
			//Add the display-as-emoji variant selector.
			//If you don't include this, there is no telling how it will be displayed
			//on any given system. Android may render it as a text symbol, firefox as an emoji.
			//I guess we just need to add this to everything ... ?
			if (emojiOnly)
				string = TextUtils.addEmojiVariantSelector(string);

			boolean renderable = FontLoader.isRenderable(string);
			boolean singleChar = ( !emojiOnly || FontLoader.isSingleChar(string) );

			//Skip items that are completely unrenderable, and emoji that should be single-char that are not renderd as such.
			if (renderable && singleChar)
			//if (FontLoader.isRenderable(string) )
			{
				newItem.get_items().add(new DB_EmojiItem(string, newItem.get_style()));
			}
			else
			{

				Log.i(LOGTAG, "Discarding "+string+" as unrenderable. Renderable: "+renderable+", single char: "+singleChar);
			}
		}

		if (emojiOnly)
		{
			newItem.updateModifierSupport();

		}

		//newItem.set_needsUpdate(true);

		return newItem;
	}

}
