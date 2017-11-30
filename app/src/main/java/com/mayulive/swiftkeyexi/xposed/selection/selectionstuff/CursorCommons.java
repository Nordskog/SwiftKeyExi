package com.mayulive.swiftkeyexi.xposed.selection.selectionstuff;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.MainActivity;
import com.mayulive.swiftkeyexi.util.TextUtils;

import java.text.Bidi;

import static com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState.SWIPE;

/**
 * Created by Roughy on 6/7/2017.
 */

public class CursorCommons
{

	private static String LOGTAG = ExiModule.getLogTag(CursorCommons.class);

	public enum Direction
	{
		UNDEFINED, LEFT, RIGHT
	}

	//Return true if surrogate found and corrected
	public static CursorSelection moveCursorOutsideSurrogate(boolean direction, int startSelect, int endSelect, CharSequence sequence, PointerState state)
	{
		//Make sure we're not inside a surrogate pair. This is apparently expensive.
		//CharSequence afterCursor = connection.getTextAfterCursor(1,0);
		//if (afterCursor.length() > 0)
		if (endSelect < sequence.length())
		{
			//Surrogates!
			if ( Character.isLowSurrogate(sequence.charAt(endSelect) ) )
			{
				//We are inside a surrogate pair!
				//We need to shift the cursor one char forward or backward.
				if (direction)
				{
					endSelect++;
					if ( endSelect > sequence.length() )
					{
						//... As far as the text editor is concerned this is fine.
						//room-on-fire fine, but fine.
						//Being inside surrogate pairs will crash swiftkey sometimes, however.
						//Though it's not like I can delete the user's text either ...
						//We're screwed.
					}
				}
				else
				{
					endSelect--;
					if (endSelect < 0)
					{
						//Again ... We can't really do anything about this.
						//Further editing of the text will probably crash swiftkey,
						//Swiftkey's fault for not failing gracefully.
					}
				}

				if(state == SWIPE)
				{
					startSelect = endSelect;
				}
			}
		}

		return new CursorSelection(startSelect, endSelect);
	}

	public static int getStartOfNormalChar(int start, CharSequence sequence)
	{
		if (start >= sequence.length())
			return sequence.length();
		else if (start <= 0)
			return 0;



		while (start > 0)
		{
			char charizard = sequence.charAt(start);

			//Niether low surrogate or variant, so proper single char or high surrogate. Also jump over ZWJs, incase we start on one.
			if ( !TextUtils.isVariantSelector(charizard) && !Character.isLowSurrogate(charizard) && !TextUtils.isZWJ(charizard) )
			{
				break;
			}

			start--;
		}

		return start;
	}

	public static int getEndOfNormalChar(int start, CharSequence sequence)
	{
		if (start >= sequence.length())
			return sequence.length();

		if ( TextUtils.isZWJ(sequence.charAt(start) ))
			start++;

		while (start < sequence.length())
		{
			char charizard = sequence.charAt(start);

			//Variant selector should come after
			if ( TextUtils.isVariantSelector(charizard) )
			{
				start++;
				break;
			}
			else if ( !Character.isHighSurrogate(charizard) && start + 1 < sequence.length())
			{
				//otherwise we have to be not a high surrogate,
				//or any character not followed by a variant selector
				char nextCharizard = sequence.charAt(start+1);
				if ( !TextUtils.isVariantSelector(nextCharizard) )
				{
					//Next is not variant, set output to after current car
					start++;
					break;

				}
				else
				{
					//next is variant, set output to after variant
					start+=2;
					break;
				}
			}

			start++;
		}

		if (start > sequence.length())
			start = sequence.length();

		return start;
	}

	public static int getClosestValidCursorPosition(int cursorLocation, CharSequence sequence, boolean direction)
	{
		int start = getStartOfNormalChar(cursorLocation, sequence);	//Skip ahead of any variant selectors
		int end = start;

		//Identify the start and end of the current block.
		//start should now be the location of a valid character (or the high surrogate of one).
		//Next we want to check for ZWJs ahead of us.
		if (start > 0 && TextUtils.isZWJ( sequence.charAt(start-1) ))
		{
			while(true)
			{
				start = getStartOfNormalChar(start - 1, sequence);

				if (start <= 0)
				{
					start = 0;
					break;
				}

				if ( !TextUtils.isZWJ( sequence.charAt(start-1) ))
					break;
			}
		}

		end = start;

		//Next lets fine end
		if (end < sequence.length())
		{
			while(true)
			{
				end = getEndOfNormalChar(end, sequence);

				if (end >= sequence.length())
				{
					break;
				}

				if ( !TextUtils.isZWJ( sequence.charAt(end) ))
					break;


			}
		}

		if (direction)
		{
			//Only move to end of block if cursorLocation was inside block.
			if (start == cursorLocation)
				return start;
			return end;
		}
		else
		{
			return start;
		}
	}

	public static CursorSelection moveCursorOutsideCWJandVariant(int start, int end, CharSequence sequence, boolean direction, PointerState state)
	{

		//If we were into a surrogate, we should now be outside it,
		//and thus between two actual characters. These characters may
		//still be joined by a Zero-width joiner. If that is the case
		//all connected characters should be treated as a single character.

		//Even though they are joined, the characters may not be displayed
		//as a single character, usually because the system cannot
		//render the requested character, in which case it just displays its
		//components instead. From here we have no way of quickly telling how
		//characters are being rendered, and will instead treat all zero-width-
		//joined characters as a single character.

		// Basically, we want to continue in whatever direction we are going until we are
		//after a character, and the next character is now a ZWJ. Keep in mind that the characters
		//we pass may be surrogate characters, or variant selectors characters. Or other nonense.

		//ZWJ is thankfully not a surrogated character, and neither are the variant seletors.
		//Except for the extended variant selectors, which are not used ... yet

		//rather than base+mark. The only exception is when users do it manually to create scrambled text.
		//Not very important.


		CursorSelection selection = new CursorSelection(start,end);

		if (selection.end > 0 && selection.end < sequence.length())
		{
			//If there are characters we need to care about, they will be within this range
			int rangeStart = selection.end-3;
			if (rangeStart < 0 )
				rangeStart = 0;
			int rangeEnd = selection.end+4;	//Last +1
			if (rangeEnd > sequence.length())
				rangeEnd = sequence.length();

			for (int i = rangeStart; i < rangeEnd; i++)
			{
				char currChar = sequence.charAt(i);
				if (  TextUtils.isZWJ( currChar ) || TextUtils.isVariantSelector( currChar )  )
				{
					//At the time of writing you may combine up to 4 emoji.
					//These emoji may all have variants, and may be surrogate pairs.
					//4 emoji means 3.

					//Move forward until we find a character (possibly up to 3 characters combined)
					//that are followed by another normal character of some sort, rather than a a ZWJ.
					selection.end = getClosestValidCursorPosition(selection.end, sequence, direction);

					if (state == SWIPE)
					{
						selection.start = selection.end;
					}

					break;
				}
			}
		}

		return selection;
	}

	public static boolean isRTL(char cha)
	{
		byte direction = Character.getDirectionality(cha);
		if (direction == 1 || direction == 2)
			return true;
		return false;
	}

	public static boolean isLTR(char cha)
	{
		byte direction = Character.getDirectionality(cha);
		if (direction == Character.DIRECTIONALITY_LEFT_TO_RIGHT || direction == Character.DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING)
			return true;
		return false;
	}

	public static void printBidiDebug(CharSequence string, Bidi bidi)
	{
		Log.i(LOGTAG,"Bidi run count: "+bidi.getRunCount());
		for (int i = 0; i < bidi.getRunCount(); i++)
		{
			int start = bidi.getRunStart(i);
			int end = bidi.getRunLimit(i);
			CharSequence thisRun = string.subSequence( start,end );
			int level = bidi.getRunLevel(i);
			Log.i(LOGTAG, "Run: "+i+", level: "+level+" range: "+start+", "+end+", text: "+thisRun);
		}
	}

	// General bidi notice. Docs say getRunLimit() returns index relative to start.
	// This is incorrect, the index returned is absolute.

	public static int flipPositionInBidiChunk(CharSequence sequence, int position)
	{

		Bidi bidi = new Bidi(sequence.toString(), 0);

		int run = bidi.getRunCount()-1;
		for (int i = 0; i < bidi.getRunCount(); i++)
		{
			int runEnd = bidi.getRunLimit(i);
			if (runEnd >= position)
			{
				run = i;
				break;
			}
		}

		printBidiDebug(sequence,bidi);

		int start = bidi.getRunStart(run);
		int end = bidi.getRunLimit(run);


		int distance = position - start;

		return end - distance;
	}

	public static int flipPositionToLeftOppositeBidiChunk(CharSequence sequence, int position)
	{

		Bidi bidi = new Bidi(sequence.toString(), 0);

		int run = bidi.getRunCount()-1;
		for (int i = 0; i < bidi.getRunCount(); i++)
		{
			int runEnd = bidi.getRunLimit(i);
			if (runEnd >= position)
			{
				run = i;
				break;
			}
		}

		printBidiDebug(sequence,bidi);

		int start = bidi.getRunStart(run);
		int end = bidi.getRunLimit(run);

		int oppositeRun = run-2;
		if (oppositeRun < 0)
			oppositeRun = 0;

		int oppositeChunkEnd = bidi.getRunLimit(oppositeRun);

		int distance = position - start;

		return oppositeChunkEnd - distance;
	}

	public static int flipPositionToRightOppositeBidiChunk(CharSequence sequence, int position)
	{

		Bidi bidi = new Bidi(sequence.toString(), 0);

		int run = bidi.getRunCount()-1;
		for (int i = 0; i < bidi.getRunCount(); i++)
		{
			int runEnd = bidi.getRunLimit(i);
			if (runEnd >= position)
			{
				run = i;
				break;
			}
		}

		printBidiDebug(sequence,bidi);

		int start = bidi.getRunStart(run);
		int end = bidi.getRunLimit(run);


		int oppositeRun = run+2;
		if (oppositeRun >= bidi.getRunCount())
			oppositeRun = bidi.getRunCount()-1;

		int oppositeChunkStart = bidi.getRunStart(oppositeRun);

		int distance = end - position;

		return oppositeChunkStart + distance;
	}

	public static CursorCommons.Direction getBaseDirectionAtOffset(CharSequence text, int pos)
	{
		//Bidi will return you the base direction of any given any string,
		//but on android, text after line breaks is reset, and will adopt the direction of the char on the new line.
		//There are some exceptions to this, primarily in apps that have their own text-rendering backends (chrome, firefox).
		//where all ... TODO

		if (pos >= text.length() )
			pos = text.length() - 1;
		if (text.length() <= 0)
			return Direction.LEFT;

		//Find first linebreak from pos, or beginning of string
		int stringStart = 0;
		int counter = pos;
		while (counter > 0)
		{
			if (text.charAt(counter) == 0x0A)
			{
				stringStart = counter + 1;
				break;
			}

			counter--;
		}

		CharSequence testSeq = text.subSequence(stringStart,text.length());
		Bidi bidi = new Bidi(testSeq.toString(), 0 );

		//Bidi's isLeftToRight() always returns true.
		// getBaseLevel() always returns 0.
		// getLevelAt() returns the correct value, 0 is LTR, 1 if RTL

		if (bidi.getLevelAt(0) == 0)
		{
			return Direction.LEFT;
		}
		else
		{
			return Direction.RIGHT;
		}
	}


}
