package com.mayulive.swiftkeyexi.xposed.selection;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.keyboard.PriorityKeyboardClassManager;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysCommons;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysMethods;
import com.mayulive.swiftkeyexi.xposed.predictions.PredictionCommons;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorCommons;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorSelection;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SelectionBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SpaceModifierBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SwipeSpeedModifier;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.pointerInformation;

import java.text.Bidi;
import java.util.Map.Entry;

import static com.mayulive.swiftkeyexi.util.ContextUtils.getModuleContext;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.ensureCursorOrder;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.getInternalSelection;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.isDelete;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.isShift;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mFirstDown;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mIsRtl;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mLastExtractedText;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mLastFallbackSelectionPointerState;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mLastSelectionChangeWasFallback;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mModifierKeyActions;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.updateSelection;
import static com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState.LEFT_SWIPE;
import static com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState.RIGHT_SWIPE;
import static com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState.SWIPE;


public class SelectionMethods
{

	private static String LOGTAG = ExiModule.getLogTag(PopupkeysMethods.class);

	private static final int BIDI_CHECK_RANGE = 20;

	protected static void inputBatchMode(boolean enable)
	{
		InputConnection connection = PriorityKeyboardClassManager.getInputConnection();

		if (connection != null)
		{
			if (enable)
				connection.beginBatchEdit();
			else
				connection.endBatchEdit();
		}
	}



	//Return cursor position to where it was on last swipe start
	protected static void resetCursorPosition()
	{
		InputConnection connection = PriorityKeyboardClassManager.getInputConnection();
		if (connection != null)
			setSelectionWithOffset(connection, SelectionState.mLeftSelectPosition_Original, SelectionState.mRightSelectPosition_Original);
	}

	protected static boolean setSelectionWithOffset(InputConnection connection, int start, int end)
	{
		return connection.setSelection(SelectionState.mLastExtractedTextOffset+start, SelectionState.mLastExtractedTextOffset+end);
	}

	protected static boolean setSelectionWithoutOffset(InputConnection connection, int start, int end)
	{
		return connection.setSelection(start,end);
	}

	public static void moveCursorToStart()
	{
		InputConnection connection = PriorityKeyboardClassManager.getInputConnection();
		SelectionMethods.setSelectionWithoutOffset(connection, 0,0 );
	}

	public static void moveCursorToEnd()
	{
		//Just incase
		if (SelectionState.mLastExtractedText == null)
			SelectionState.mLastExtractedText = new String();

		InputConnection connection = PriorityKeyboardClassManager.getInputConnection();
		SelectionState.updateSelection();
		SelectionMethods.setSelectionWithOffset( connection, SelectionState.mLastExtractedText.length(), SelectionState.mLastExtractedText.length());
	}

	//Set selection using the previous values.
	private static boolean finalizeSelection()
	{
		InputConnection connection = PriorityKeyboardClassManager.getInputConnection();
		if (connection != null)
		{

			CursorSelection selection = SelectionState.getInternalSelection( SelectionState.mLastState );
			return setSelectionWithOffset ( connection, selection.start, selection.end );
		}

		return false;
	}


	private static final byte[] FILLER_EMBED_VALUES = new byte[BIDI_CHECK_RANGE*3];

	private static char[] fillRtlCheckArray(CharSequence source, int pos)
	{
		int start = pos - BIDI_CHECK_RANGE;
		int end = pos + BIDI_CHECK_RANGE;
		if (start < 0)
			start = 0;
		if (end > source.length())
			end = source.length();

		char[] returnArray = new char[end-start];


		for (int i = 0; i < returnArray.length; i++)
		{
			returnArray[i] = source.charAt(start+i);
		}

		return returnArray;
	}

	public static boolean updateRtlAndCheckForMixed()
	{
		SelectionState.mIsRtl = false;
		if ( !Settings.SWIPE_RTL_MODE_ENABLED)
			return false;

		int start = 0;
		int end = 0;

		boolean isMixed = false;

		//Just incase
		if (SelectionState.mLastExtractedText == null)
			SelectionState.mLastExtractedText = new String();

		InputConnection connection = PriorityKeyboardClassManager.getInputConnection();
		if (connection != null)
		{

			//Mixed needs selection update, slow.
			if (mLastSelectionChangeWasFallback)
			{
				ExtractedTextRequest request = new ExtractedTextRequest();
				request.hintMaxChars = 0;	//Only want selection, though we still get a lot of text.
				//Probably always get /at least/ the selected range?
				ExtractedText extractedText = connection.getExtractedText(request, 0);

				if (extractedText == null)
				{
					return false;
				}

				//Consider change in offset value
				int offsetDiff = SelectionState.mLastExtractedTextOffset - extractedText.startOffset;
				start = extractedText.selectionStart + offsetDiff;
				end = extractedText.selectionEnd + offsetDiff;
			}
			else	//Internal selection, fast
			{
				CursorSelection selection = getInternalSelection(null);
				start = selection.start;
				end = selection.end;
			}
		}
		else
		{
			return false;
		}

		char[] primRange = fillRtlCheckArray(mLastExtractedText, start);
		char[] secRange  = fillRtlCheckArray(mLastExtractedText, end);

		mIsRtl = Bidi.requiresBidi(primRange, 0, primRange.length);
		//Check sec if false and text selected
		if (start != end)
		{
			//Sometimes the cursor will be very far apart.
			//If first is LTR and second is RTL, set to mixed.
			isMixed = mIsRtl != Bidi.requiresBidi(secRange, 0, secRange.length);
		}

		//Only check for mixed if rtl present
		if (mIsRtl && !isMixed)
		{
			//If there are multiple runs, then we have both LTR and RTL text in the same chunk of text
			if (new Bidi(primRange, 0, FILLER_EMBED_VALUES, 0, primRange.length, 0).getRunCount() > 1
					|| new Bidi(secRange, 0, FILLER_EMBED_VALUES, 0, secRange.length, 0).getRunCount() > 1 )
			{
				isMixed = true;
			}
		}


		return isMixed;
	}

	private static void updateSelectionDirectionForKeyCursorMovement(PointerState state)
	{
		if (state != PointerState.SWIPE && SelectionState.mLastFallbackSelectionPointerState != null)
		{
			InputConnection connection = PriorityKeyboardClassManager.getInputConnection();

			if (PointerState.isSelect(mLastFallbackSelectionPointerState) && state != SelectionState.mLastFallbackSelectionPointerState)
			{
				CursorSelection selection = SelectionState.getInternalSelection(null);
				{
					//This completely ignores the cursor order stuff. Start is start, end moves.
					//If we switch directions we swap them. Things get weird if the base direction
					//changes.
					selection.swap();
					SelectionState.setInternalSelectionValue(selection.start,selection.end, null);
					setSelectionWithOffset(connection,selection.start,selection.end);
				}
			}
		}
	}

	public static void setVerticalSelection(int yCursorChange, final PointerState state)
	{
		//Update selection so we can check if we can actually move anywhere
		SelectionState.updateSelection();
		updateSelectionDirectionForKeyCursorMovement(state);

		//Negative should move up, positive down
		//Note that regardless of how much we want to move it, we limit it to 1.
		//This is because dpad cursor keys will move the focus to the next field
		//or item if we are at the beginning /end of the text.
		if (yCursorChange > 0)
		{
			if (!SelectionState.cursorAtEnd() )
			{
				yCursorChange = 1;
				if (PointerState.isSelect(state))
					SelectionActions.sendShiftedKeyPress(KeyEvent.KEYCODE_DPAD_DOWN, yCursorChange);
				else
					SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_DOWN, yCursorChange);
			}
		}
		else
		{
			if (!SelectionState.cursorAtBeginning())
			{
				yCursorChange = 1;
				if (PointerState.isSelect(state))
					SelectionActions.sendShiftedKeyPress(KeyEvent.KEYCODE_DPAD_UP, yCursorChange);
				else
					SelectionActions.sendKeyPress( KeyEvent.KEYCODE_DPAD_UP, yCursorChange);
			}
		}
	}

	public static void setSelectionChange(int change, final PointerState state)
	{
		if (SelectionState.mLastSelectionChangeWasFallback)
		{
			updateSelection();
			ensureCursorOrder();
		}

		//Just incase
		if (SelectionState.mLastExtractedText == null)
			SelectionState.mLastExtractedText = new String();

		//Reverse direction if state is rtl
		int origChange = change;
		if (SelectionState.mIsRtl)
			change *= -1;

		boolean direction = change >= 0;	//true forward, false backwards

		CursorSelection selection = SelectionState.getInternalSelection(state);

		//The view will scroll to follow the end value, so that's always what
		//we will be changing.

		//Set new position
		int originalPosition = selection.end;
		selection.end += change;

		//Make sure we're within the string
		if (selection.end > SelectionState.mLastExtractedText.length())
			selection.end = SelectionState.mLastExtractedText.length();
		if (selection.end < 0)
			selection.end = 0;

		if (state == SWIPE)
		{
			selection.start = selection.end;
		}

		SelectionState.setInternalSelectionValue(selection.start, selection.end, state);

		//SelectionClassManager.updateInputConnection();

		InputConnection connection = PriorityKeyboardClassManager.getInputConnection();

		//but the chances of this ever being a problem are fairly slim, and the consequences minor.

		if (connection != null && selection.end > 0 && selection.end < SelectionState.mLastExtractedText.length())
		{
			selection = CursorCommons.moveCursorOutsideSurrogate(direction, selection.start, selection.end, SelectionState.mLastExtractedText, state);

			selection = CursorCommons.moveCursorOutsideCWJandVariant(selection.start, selection.end, SelectionState.mLastExtractedText, direction, state);

			//TODO consider ZWNJ too? How would that even work?
			//TODO Combining marks, but any IME worth a damn will input 99% of them as distinct characters, so probably won't be a problem.
		}


		if (Settings.SWIPE_RTL_MODE_ENABLED)
		{
			//If base directioanlity differs from the diretionality of the final section of text,
			//we will hit 0 and get stuck. If we use normal cursor control to move the cursor to position 0,
			//it is visually moved to the beginning of the run, so we have to switch to fallback mode
			// /before/ this happens.
			if (selection.end >= mLastExtractedText.length()-1)
			{
				setSelectionChangeFallback(origChange, state, true);
				return;
			}

			//Do make sure line breaks are handled properly when RTL and LTR lines are mixed,
			//we need to handle any line breaks using key inputs.
			//Change range covered by change, move to fallback if \n present
			CursorSelection moveRange = new CursorSelection(originalPosition, selection.end);
			if (moveRange.end < moveRange.start)
				moveRange.swap();
			moveRange.end += 2;
			//moveRange.start -= 2;
			if (moveRange.start < 0)
				moveRange.start = 0;
			if (moveRange.end >= mLastExtractedText.length())
				moveRange.end = mLastExtractedText.length()-1;
			moveRange = CursorCommons.moveCursorOutsideSurrogate(direction, moveRange.start, moveRange.end, SelectionState.mLastExtractedText, state);
			moveRange = CursorCommons.moveCursorOutsideCWJandVariant(moveRange.start, moveRange.end, SelectionState.mLastExtractedText, direction, state);

			for (int i = moveRange.start; i < moveRange.end; i++)
			{
				if (mLastExtractedText.charAt(i) == '\n')
				{
					setSelectionChangeFallback(origChange, state, false);
					return;
				}
			}
		}

		SelectionState.setInternalSelectionValue(selection.start,selection.end, state);
		setSelectionWithOffset(connection,selection.start,selection.end);

		SelectionState.mLastSelectionChangeWasFallback = false;
		SelectionState.mLastFallbackSelectionPointerState = null;

	}

	//Remember to update position if switching from fallback to normal
	public static void setSelectionChangeFallback(int change, final PointerState state, boolean moveCursorWithSelection)
	{
		//Update selection to get new end position.
		//Firefox doesn't seem to provide updates during selection, so you'll be stuck in fallback mode in that case.
		SelectionState.updateSelection();

		//Just incase
		if (SelectionState.mLastExtractedText == null)
			SelectionState.mLastExtractedText = new String();

		if (SelectionState.mLastExtractedText.length() <= 0)
		{
			//Nothing to do
			return;
		}

		//Fallback uses keypresses to move cursor visually, no need to flip dhirection
		boolean direction = change >= 0;	//true forward, false backwards

		int absoluteChange = Math.abs(change);

		updateSelectionDirectionForKeyCursorMovement(state);

		if (PointerState.isSelect(state) || moveCursorWithSelection)
		{
			if (direction)
				SelectionActions.sendShiftedKeyPress(KeyEvent.KEYCODE_DPAD_RIGHT, absoluteChange);
			else
				SelectionActions.sendShiftedKeyPress(KeyEvent.KEYCODE_DPAD_LEFT, absoluteChange);
		}
		else
		{
			if (direction)
				SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_RIGHT, absoluteChange);
			else
				SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_LEFT, absoluteChange);
		}

		//If we are using this mode to swipe we set this bool to correct it to a single position later.
		//If we are selecting we just... select.
		if (!PointerState.isSelect(state) && moveCursorWithSelection)
		{

			//We want to one keypress in the same direction as teh selection to make it a
			//single-position (not selecting) cursor. If we are at the visual end or beginning
			//of the text, this will move us to the neighboring element (buttons etc).
			//To avoid this, we move one char in the opposite direction, and then back again.
			//Believe it or not, this is not only the best solution, but the only one.
			//Even firefox and chrome are happy with this, unlike everything else I've tried.

			if (!direction)
				SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_RIGHT, 1);
			else
				SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_LEFT, 1);

			if (direction)
				SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_RIGHT, 1);
			else
				SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_LEFT, 1);


			/*
			//If moving with select, add an extra keypress to deselect but keep the cursor in the same spot.
			if (direction)
				SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_RIGHT, 1);
			else
				SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_LEFT, 1);
				*/

		}

		SelectionState.mLastSelectionChangeWasFallback = true;
	}

	protected static void setSwipePointerState(pointerInformation firstPointerInfo, pointerInformation currentPointerInfo, boolean isPrimary)
	{

		//Uuhhh, this was a requirement for double-swipe below, but maybe broke something else too.
		//if (currentPointerInfo.xDistance == 0)
		//	return;

		//TODO Terrible buggy implementation of two-finger movement. Ditching for now.
		//There are some other leftover bits, but they only act if triggerd by this.
		/*
		//Double-swipe maybe?
		if (SelectionCommons.mLastPointerCount > 1)
		{
			if ( (Settings.SWIPE_CURSOR_BEHAVIOR == SelectionCommons.CursorBehavior.SWIPE && SelectionCommons.mLastPointerCount > 1) ||
					( (Settings.SWIPE_CURSOR_BEHAVIOR  == SelectionCommons.CursorBehavior.HOLD_SHIFT_SWIPE || Settings.SWIPE_CURSOR_BEHAVIOR == SelectionCommons.CursorBehavior.HOLD_ANY_SWIPE) && SelectionCommons.mLastPointerCount > 2))
			{
				SelectionCommons.pointerInformation otherPointer = getOtherPointer(currentPointerInfo);

				float xDistanceA = currentPointerInfo.xDistanceChange;
				float xDistanceB = otherPointer.xDistanceChange;

				//Log.e("###", "Distance A: "+xDistanceA);
				//Log.e("###", "Distance B: "+xDistanceB);




				//Check if both pointers are moving roughly in teh same direction,
				//considering both axes
				if ((xDistanceA > 0 && xDistanceB > 0) ||
					(xDistanceB < 0 && xDistanceA < 0) )
				{

					xDistanceA = Math.abs(xDistanceA);
					xDistanceB = Math.abs(xDistanceB);

					float fraction = 0;
					if (xDistanceA > xDistanceB)
						fraction = xDistanceB / xDistanceA;
					else
						fraction = xDistanceA / xDistanceB;

					//Log.e("###", "Fraction: "+fraction);


					if (fraction > 0.3)
					{
						currentPointerInfo.state = SelectionCommons.PointerState.SWIPE;
						otherPointer.state = SelectionCommons.PointerState.SWIPE;

						currentPointerInfo.partner = otherPointer;
						otherPointer.partner = currentPointerInfo;

						currentPointerInfo.modifier = SelectionCommons.SwipeSpeedModifier.DOUBLE;
						otherPointer.modifier = SelectionCommons.SwipeSpeedModifier.ZERO;

						return;
					}
				}
			}
		}

		*/

		//Non-primary pointer triggered swipe
		if (!isPrimary)
		{
			//Consider cursor implications. If the cursor behavior is set to either of these modes,
			//there are no compatible two-point selection behaviors
			if (Settings.SWIPE_CURSOR_BEHAVIOR  == CursorBehavior.HOLD_SHIFT_SWIPE || Settings.SWIPE_CURSOR_BEHAVIOR == CursorBehavior.HOLD_ANY_SWIPE)
			{
				if (Settings.SWIPE_CURSOR_BEHAVIOR == CursorBehavior.HOLD_SHIFT_SWIPE)
				{
					if (firstPointerInfo.state == PointerState.SHIFT)
					{
						currentPointerInfo.state = SWIPE;
						return;
					}
				}
				else if(Settings.SWIPE_CURSOR_BEHAVIOR  == CursorBehavior.HOLD_ANY_SWIPE)
				{
					//First pointer can be in any state, as long as it is not mid-swipe
					if ( !PointerState.isSwipe(firstPointerInfo.state) )
					{
						currentPointerInfo.state = SWIPE;
						return;
					}
				}

				//Nothing then I guess
				currentPointerInfo.state = PointerState.DEFAULT;
				//return;
			}

			//If we have not returned yet, the CursorBehavior is in a mode that allows
			//for multi-pointer selection of some sort.
			if (Settings.SWIPE_SELECTION_BEHAVIOR == SelectionBehavior.HOLD_AND_DRAG_SWIPE || Settings.SWIPE_SELECTION_BEHAVIOR == SelectionBehavior.HYBRID)
			{

				if (currentPointerInfo.downX < firstPointerInfo.downX)
				{
					currentPointerInfo.state = LEFT_SWIPE;
					firstPointerInfo.state = RIGHT_SWIPE;
				}
				else
				{
					currentPointerInfo.state = RIGHT_SWIPE;
					firstPointerInfo.state = LEFT_SWIPE;
				}

				//In this scenario the primary pointer likely won't be moving.
				//To avoid it jumping at the beginning of the operation, 
				//null its position bank.
				firstPointerInfo.xCursorBank = 0;

				return;
			}

			//Nothing then I guess
			currentPointerInfo.state = PointerState.DEFAULT;
		}
		//Primary pointer triggered swipe
		else
		{
			//Consider if this can cause a selection event first
			if (Settings.SWIPE_SELECTION_BEHAVIOR == SelectionBehavior.SHIFT_DELETE_DRAG_SWIPE || Settings.SWIPE_SELECTION_BEHAVIOR == SelectionBehavior.HYBRID)
			{
				if ( SelectionState.isShift(SelectionState.mFirstDown)) //  mFirstDown.equals("Shift") || mFirstDown.equals("-"))
				{
					currentPointerInfo.state = RIGHT_SWIPE;
					return;
				}
				else if (SelectionState.isDelete(SelectionState.mFirstDown) )
				{
					currentPointerInfo.state = LEFT_SWIPE;

					return;
				}
			}

			//To properly trigger two-finger selection swiping, we must also transition into selection mode if
			//two fingers are down and the first finger moves. Before we only supported the opposite.
			//If we have not returned yet, the CursorBehavior is in a mode that allows
			//for multi-pointer selection of some sort.
			if (SelectionState.mLastPointerCount > 1)
			{
				if (Settings.SWIPE_SELECTION_BEHAVIOR == SelectionBehavior.HOLD_AND_DRAG_SWIPE || Settings.SWIPE_SELECTION_BEHAVIOR == SelectionBehavior.HYBRID)
				{

					//CurrentPointerInfo is currently the same as FirstPointerInfo.
					//Fetch the other one.
					pointerInformation otherPointer = SelectionState.getOtherPointer(currentPointerInfo);


					if (currentPointerInfo.downX < otherPointer.downX)
					{
						currentPointerInfo.state = LEFT_SWIPE;
						otherPointer.state = RIGHT_SWIPE;
					}
					else
					{
						currentPointerInfo.state = RIGHT_SWIPE;
						otherPointer.state = LEFT_SWIPE;
					}


					//Log.e("###", "New cursor state: "+currentPointerInfo.state);

					//ensureCursorOrder();


					//In this scenario the primary pointer likely won't be moving.
					//To avoid it jumping at the beginning of the operation,
					//null its position bank.
					otherPointer.xCursorBank = 0;

					return;
				}
			}





			//Otherwise swipe as usual if behavior permits
			if (Settings.SWIPE_CURSOR_BEHAVIOR  == CursorBehavior.SWIPE ||
					Settings.SWIPE_CURSOR_BEHAVIOR  == CursorBehavior.SPACE_SWIPE ||
					Settings.SWIPE_CURSOR_BEHAVIOR  == CursorBehavior.NUMBER_ROW_SWIPE)
			{
				if (Settings.SWIPE_CURSOR_BEHAVIOR  == CursorBehavior.SPACE_SWIPE)
				{
					//Space swipe requires that first key down is space
					if ( SelectionState.mFirstDown.is(KeyType.SPACE) )
					{
						currentPointerInfo.state = SWIPE;
					}
				}
				else if (Settings.SWIPE_CURSOR_BEHAVIOR  == CursorBehavior.SPACE_SWIPE)
				{
					//Space swipe requires that first key down is number
					if ( SelectionState.mFirstDown.is(KeyType.NUMBER) )
					{
						currentPointerInfo.state = SWIPE;
					}
				}
				else
				{
						currentPointerInfo.state = SWIPE;
				}

				return;
			}


			//Otherwise nothing
			currentPointerInfo.state = PointerState.DEFAULT;
			return;
		}
	}


	protected static void setDownPointerState(int action, float downPos, pointerInformation firstPointerInfo, pointerInformation currentPointerInfo)
	{
		SelectionState.mLastPointerDownAction = action;

		SelectionState.mLastPointerDownInfo = currentPointerInfo;
		SelectionState.mFirstPointerDownInfo = firstPointerInfo;

		if (action == MotionEvent.ACTION_DOWN)
		{


			if ( SelectionState.isShift(SelectionState.mFirstDown) )
			{
				currentPointerInfo.state = PointerState.SHIFT;
			}
			else if ( SelectionState.isDelete(SelectionState.mFirstDown) )
			{
				currentPointerInfo.state = PointerState.DELETE;
			}
			else if ( SelectionState.isSymbols(SelectionState.mFirstDown) )
			{
				currentPointerInfo.state = PointerState.MODIFIER;
			}
			else
			{
				currentPointerInfo.state = PointerState.HOLD;
			}

		}
		else if (action == MotionEvent.ACTION_POINTER_DOWN)
		{

			//Swipe has already been triggered. 
			if (SelectionState.mSwiping && firstPointerInfo != null)
			{
				//setSwipePointerState(firstPointerInfo, currentPointerInfo, false);

			}

		}

	}


	protected static void handleSingleMotionEventInit(MotionEvent event, int index)
	{
		float newX = event.getX(index);
		float newY = event.getY(index);
		int pointerID = event.getPointerId(index);

		pointerInformation currentPointerInfo = SelectionState.mPointerInformation.get(pointerID);
		if (currentPointerInfo == null)
		{
			currentPointerInfo = new pointerInformation();
			SelectionState.mPointerInformation.put(pointerID, currentPointerInfo);
		}

		if (event.getActionMasked() == MotionEvent.ACTION_DOWN || event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN)
		{
			currentPointerInfo.downX = newX;
			currentPointerInfo.downY = newY;
		}

		SelectionState.calculateDistance(currentPointerInfo, newX,newY);
	}

	//If we have entered swipe, cancel it and return the cursor to where it was
	protected static void cancelSwipe()
	{
		if (SelectionState.mSwiping)
		{
			SelectionState.mSwiping = false;
			resetCursorPosition();
		}
	}


	protected static boolean handleSingleMotionEvent(View view, MotionEvent event, int index)
	{

		float newX = event.getX(index);
		float newY = event.getY(index);

		int actionIndex = event.getActionIndex();

		int pointerID = event.getPointerId(index);
		int action = event.getActionMasked();
		int pointerCount = event.getPointerCount();

		boolean actionIsCurrent = actionIndex == index;
		boolean isPrimary = pointerID == SelectionState.mPrimaryPointerID;

		pointerInformation currentPointerInfo = SelectionState.mPointerInformation.get(pointerID);
		pointerInformation firstPointerInfo = SelectionState.mPointerInformation.get( SelectionState.mPrimaryPointerID);

		//////////////////////////
		//Handle down events
		//////////////////////////
		if (actionIsCurrent && (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) )
		{

			if (action == MotionEvent.ACTION_DOWN)
			{

				SelectionState.clearState(pointerCount);
				SelectionState.mPrimaryPointerID = pointerID;

				//Moved so it only runs on swipe selection start
				//updateSelection();
			}

			currentPointerInfo.downX = newX;
			currentPointerInfo.downY = newY;

			setDownPointerState(action, newX, firstPointerInfo, currentPointerInfo);

			return false;
		}


		//////////////////////////
		//Handle move events
		//////////////////////////

		if ( action == MotionEvent.ACTION_MOVE)	//Handle move events
		{

			///////////////////////////////////////////////////////////////
			//Nothing going on yet. Check if we should enter swipe mode
			///////////////////////////////////////////////////////////////

			//First off, the current pointer must have come down on the space bar
			if ( currentPointerInfo.key.is(KeyType.SPACE) )
			{
				//Since we may transition from swipe to space modifier, always check space modifier first.
				//This will only happen if we are swiping on the spacebar.
				if (  !SelectionState.mSpaceModifierTriggered && ( !SelectionState.mSwiping || Settings.SWIPE_CURSOR_BEHAVIOR == CursorBehavior.SPACE_SWIPE )  )
				{
					float spaceModifierMaxHeight = view.getMeasuredHeight() - ( ( 1.0f - mFirstDown.hitbox.top )*view.getMeasuredHeight());
					//The above value means space modifier will trigger as soon as we swipe above the spacebar.
					//This makes it a tiny bit too sensitive, so add 0.25 the height of the spacebar too.
					spaceModifierMaxHeight -=  (mFirstDown.hitbox.height()*view.getMeasuredHeight()) * 0.25f;

					if (SelectionState.getSpaceModifierBehavior().isEnabled() && SelectionState.mFirstDown.is(KeyType.SPACE) && newY < spaceModifierMaxHeight)
					{

						if ( SelectionState.mSwiping )
						{
							//We've already triggered swipe.
							cancelSwipe();
						}

						currentPointerInfo.state = PointerState.SPACE_MODIFIER;
						SelectionState.mSpaceModifierTriggered = true;
						SelectionState.mSwipeBlocked = true;
					}
				}

			}

			if  (!SelectionState.mSwiping && !SelectionState.mSpaceModifierTriggered)
			{

				if (SelectionState.mValidFirstDown && !PointerState.isSwipe(currentPointerInfo.state))
				{
					//Must not already be swiping, and current pointer must have come down on spacebar if space_swipe cursor mode.
					//Or shift/delet if enabled
					if (!SelectionState.mSwiping
							 && (
							 		(
							 				Settings.SWIPE_CURSOR_BEHAVIOR != CursorBehavior.SPACE_SWIPE || currentPointerInfo.key.is(KeyType.SPACE) ||
													( Settings.SWIPE_SELECTION_BEHAVIOR.triggersFromShiftAndDelete() && ( isShift(currentPointerInfo.key) || isDelete(currentPointerInfo.key) ) ) ||
													Settings.SWIPE_CURSOR_BEHAVIOR != CursorBehavior.NUMBER_ROW_SWIPE || currentPointerInfo.key.is(KeyType.NUMBER)
									)

					))
					{
						if (Math.abs(currentPointerInfo.xDistance) > Settings.SWIPE_THRESHOLD || ( Math.abs(currentPointerInfo.yDistance) > Settings.SWIPE_THRESHOLD && Settings.SWIPE_CURSOR_BEHAVIOR != CursorBehavior.SPACE_SWIPE ) )
						{
							if (SelectionState.isSwipeAllowed())
							{
								setSwipePointerState(firstPointerInfo, currentPointerInfo, isPrimary);

								if (PointerState.isSwipe(currentPointerInfo.state))
								{
									SelectionState.mSwiping = true;

									InputConnection connection = PriorityKeyboardClassManager.getInputConnection();


									if (connection != null)
									{
										//Finish any composing going on.
										//Doing this directly probably angers swiftkey a bit, but nothing breaks.
										connection.finishComposingText();
									}

									//Grab the current selection and text
									SelectionState.updateSelection();

									//Updating suggestions while we swipe is okay on newer devices,
									//but older ones will struggle and feel sluggish. This is ended in clearState()
									SelectionMethods.inputBatchMode(true);

									//If we try to move the cursor while multiple characters are selected, the selection should
									//be collapsed to the right pointer position
									if (currentPointerInfo.state == SWIPE && (SelectionState.mLeftSelectPosition != SelectionState.mRightSelectPosition))
									{
										SelectionState.mCursorPosition = SelectionState.mRightSelectPosition;
									}

									SelectionState.ensureCursorOrder();
								}
							}
						}
					}
					else
					{
						if ( Math.abs(currentPointerInfo.xDistance) > Settings.SWIPE_THRESHOLD ||  Math.abs(currentPointerInfo.yDistance) > Settings.SWIPE_THRESHOLD)
						{
							setSwipePointerState(firstPointerInfo, currentPointerInfo, false);
						}
					}
				}
			}
			else if ( !PointerState.isSwipe(currentPointerInfo.state) )
			{
				if ( Math.abs(currentPointerInfo.xDistance) > Settings.SWIPE_THRESHOLD || Math.abs(currentPointerInfo.yDistance) > Settings.SWIPE_THRESHOLD)
				{
					setSwipePointerState(firstPointerInfo, currentPointerInfo, false);
				}
			}


			///////////////////////////////////////////////////////////////
			//Perform appropriate action if we are swiping
			///////////////////////////////////////////////////////////////

			if (SelectionState.mSwiping || SelectionState.mSpaceModifierTriggered)
			{
				KeyCommons.clearDelayedKeys();

				if (SelectionState.mSpaceModifierTriggered)
				{
					if (SelectionState.getSpaceModifierBehavior() == SpaceModifierBehavior.MENU)
					{

						if (!OverlayCommons.isHotkeyMenuDisplayed())
						{
							// Cover spacebar, but display options above it
							float spacebarMargin = ( 1.0f - mFirstDown.hitbox.top )*view.getMeasuredHeight();
							OverlayCommons.displayHotkeyMenu( spacebarMargin, view.getMeasuredWidth(), view.getMeasuredHeight(), currentPointerInfo.downX, SelectionState.mHotkeyMenuItems, view );
						}

						OverlayCommons.handleDisplayHotkeyMenuTouch(event.getX() , event.getY(), view.getMeasuredHeight());

					}
					else if (SelectionState.getSpaceModifierBehavior()== SpaceModifierBehavior.KEY)
					{
						KeyDefinition key = KeyCommons.getHitboxAtLocation(event.getX() / view.getMeasuredWidth(), event.getY() / view.getMeasuredHeight());

						if (key != null)
						{
							if (!OverlayCommons.isDisplayed(key.getContent()))
							{
								OverlayCommons.clearPopups();

								KeyboardInteraction.TextAction textAction = mModifierKeyActions.get(key.getContent().toLowerCase());

								if (textAction == null)
								{
									OverlayCommons.setPopupKeyManually( key.getContent() );
								}
								else
								{
									OverlayCommons.displayKeyAbovePosition( 	key,
																				KeyboardInteraction.TextAction.getTextRepresentation(getModuleContext(), textAction),
																				view	);
								}
							}
						}
					}
				}
				else //Must be swiping
				{
					if (SelectionState.mSwiping && PointerState.isSwipe(currentPointerInfo.state))
					{
						//Cast to int to floor
						int xCursorChange = (int) currentPointerInfo.xCursorDistanceChange;
						int yCursorChange = (int) currentPointerInfo.yCursorDistanceChange;

						//If swipe direction is not set to any direction, null y change.
						if (!Settings.SWIPE_DIRECTION_ANY)
						{
							yCursorChange = 0;
						}

						//Prioritize horizontal change
						if ( Math.abs(yCursorChange) > (Math.abs(xCursorChange) * 2f) )
						{
							setVerticalSelection(yCursorChange, currentPointerInfo.state);

							if (Settings.DISABLE_SWIPE_AUTO_CORRECT)
								PredictionCommons.requestLiteralPrimarySuggestion(true);

							SelectionState.mCursorMovedVertical = true;
							SelectionState.mLastFallbackSelectionPointerState = currentPointerInfo.state;

							//Clear bank of other axis to make it less finicky
							currentPointerInfo.xCursorBank = 0;
						}
						else if (xCursorChange != 0)
						{
							//We have no idea where the cursor ends up when we move vertically,
							//so selection must be updated before we can move horizontally again.
							if (SelectionState.mCursorMovedVertical)
							{
								SelectionState.mCursorMovedVertical = false;
								SelectionState.updateSelection();
								SelectionState.ensureCursorOrder();
							}

							if (Settings.DISABLE_SWIPE_AUTO_CORRECT)
								PredictionCommons.requestLiteralPrimarySuggestion(true);

							//RTL is difficult. So difficult in fact that we can't really do it properly.
							//If we are just moving the cursor (not select), use the fallback method
							//that relies on keypresses instead of actually setting the cursor position
							//If RTL mode disabled this just set rtl status to false and returns false.
							if( updateRtlAndCheckForMixed())
							{
								//Only works for cursor movement, not selection
								setSelectionChangeFallback(xCursorChange, currentPointerInfo.state, true);
							}
							else
							{
								//It is pretty much impossible to handle selection with RTL text properly,
								//so we simply assume everything is RTL if there is any RTL text
								setSelectionChange(xCursorChange, currentPointerInfo.state);
							}

							//Prev state was set inside fallback selection before, but needs to be here so it
							//updates even when the other cursor is using normal selection
							SelectionState.mLastFallbackSelectionPointerState = currentPointerInfo.state;

							//Clear bank of other axis to make it less finicky
							currentPointerInfo.yCursorBank = 0;
						}

					}
				}

				return true;
			}

			//Not doing anything, don't take control.
			return false;
		}


		//////////////////////////
		//Handle up events
		//////////////////////////

		if (actionIsCurrent && action == MotionEvent.ACTION_UP)
		{
			KeyCommons.processDelayedKeys();

			if (SelectionState.mSpaceModifierTriggered)
			{
				if (SelectionState.getSpaceModifierBehavior() == SpaceModifierBehavior.KEY)
				{
					SelectionActions.handleSpaceModifierKey(event.getX() / view.getMeasuredWidth(), event.getY() / view.getMeasuredHeight());
				}
				else
				{
					SelectionActions.handleSpaceModifierMenu();
				}
				OverlayCommons.clearPopups();
			}

			if (SelectionState.mActionTriggered)
			{
				KeyCommons.requestCancelNextKey();
			}

			SelectionState.clearState(0);
			SelectionState.mSwipeEndTime = System.currentTimeMillis();

			SelectionState.mPointerInformation.remove(pointerID);

			return false;
		}
		else if (actionIsCurrent && action == MotionEvent.ACTION_POINTER_UP)
		{
			SelectionState.mLastPointerUpTime = System.currentTimeMillis();

			KeyCommons.processDelayedKeys();

			SelectionState.mPointerInformation.remove(pointerID);

			if (pointerID == SelectionState.mPrimaryPointerID)
			{
				//Our primary is gone!
				//Deletegate position to whatever point remains
				for (Entry<Integer, pointerInformation> otherPointer : SelectionState.mPointerInformation.entrySet())
				{
					SelectionState.mPrimaryPointerID = otherPointer.getKey();
				}
			}

			if (currentPointerInfo.modifier != null)
			{
				if (currentPointerInfo.partner != null)
				{
					currentPointerInfo.partner.modifier = SwipeSpeedModifier.DEFAULT;
					currentPointerInfo.partner.partner = null;
				}
			}

			if (SelectionState.mActionModifierDown && currentPointerInfo.state == PointerState.MODIFIER)
			{
				SelectionState.mActionModifierDown = false;    // ???
				KeyCommons.setCancelAllKeys(false);
			}

			//if ((currentPointerInfo.state == SelectionCommons.PointerState.MODIFIER || currentPointerInfo.state == SelectionCommons.PointerState.ACTION) && SelectionCommons.mActionTriggered)
			if (SelectionState.mActionTriggered)
			{
				KeyCommons.requestCancelNextKey();
			}

		}
		else if (action == MotionEvent.ACTION_CANCEL)
		{
			SelectionState.clearState(0);
		}

		return false;
	}

	public static boolean handleMotionEvent(View view, MotionEvent event)
	{

		boolean returnValue = false;

		SelectionState.mLastPointerCount = event.getPointerCount();

		for (int i = 0; i < event.getPointerCount(); i++)
		{
			handleSingleMotionEventInit(event,i);

			if (i >= 2)
				break;
		}

		for (int i = 0; i < event.getPointerCount(); i++)
		{
			returnValue = handleSingleMotionEvent(view, event,i) || returnValue;

			if (i >= 2)
				break;
		}

		return returnValue;
	}


	public static void handleFirstKeyDown(KeyDefinition key)
	{
			PredictionCommons.requestLiteralPrimarySuggestion(false);
	   		SelectionState.mFirstDown = key;


			//Workaround for period swipe gesture. When you swipe from it it triggers
			//a different key. Solution is to always block swipe on period key-down.
			if (key.is(KeyType.PERIOD))
				SelectionState.mSwipeBlocked = true;


			//Whether and how we set the pointer down state is decided later, but we should always set the key for the current pointer
			if (SelectionState.mLastPointerDownInfo != null)
			{
				SelectionState.mLastPointerDownInfo.key = key;
			}
			if ( SelectionState.isShift(SelectionState.mFirstDown))
			{
				SelectionState.mShiftDown = true;
			}

			if (  SelectionState.isDelete(SelectionState.mFirstDown) )
			{
				SelectionState.mDeleteDown = true;
			}
			if (SelectionState.mFirstDown.is(KeyType.SPACE))
			{
				SelectionState.mSpaceDown = true;
			}
			if (SelectionState.mFirstDown.is(KeyType.NUMBER))
			{
				SelectionState.mNumberDown = true;
			}


			if (SelectionState.mActionModifierDown)
			{
				//if (SelectionCommons.mFirstDown.is(KeyCommons.KeyType.SYMBOL))
				{
					SelectionActions.handeModifierAction(SelectionState.mLastPointerDownInfo, SelectionState.mFirstDown.content);
				}
			}
			else if ( SelectionState.getSpaceModifierBehavior().isEnabled() && key.is(KeyType.SPACE) )
			//else if ( SelectionState.getSpaceModifierBehavior().isEnabled() && ( Settings.SWIPE_CURSOR_BEHAVIOR != CursorBehavior.SPACE_SWIPE && key.is(KeyCommons.KeyType.SPACE) ) )
			{
				if ( SelectionState.isSymbols( SelectionState.mFirstDown ))
				{
					SelectionState.mActionModifierDown = true;
					KeyCommons.setCancelAllKeys(true);
				}
			}
			//if (mFirstDown.length() <= 1 || mShiftKeys.contains(mFirstDown) || mDeleteKeys.contains(mFirstDown) || ( mSpaceKey.equals(key) && Settings.SWIPE_CURSOR_BEHAVIOR == CursorBehavior.SPACE_SWIPE)  )

		    if ( !SelectionState.mActionModifierDown && SelectionState.isSymbols( SelectionState.mFirstDown ))
			{
				SelectionState.mActionModifierDown = true;
				KeyCommons.setCancelAllKeys(true);
				setDownPointerState(SelectionState.mLastPointerDownAction, SelectionState.mLastPointerDownInfo.downX, SelectionState.mFirstPointerDownInfo, SelectionState.mLastPointerDownInfo);
			}


			//Swiping should normally not be allowed when the modifier key is down.
			//An exception is made the spacebar is the modifier, and spacebar swiping is enabled.
			if (!SelectionState.mActionModifierDown || (Settings.SWIPE_CURSOR_BEHAVIOR == CursorBehavior.SPACE_SWIPE &&  SelectionState.isSymbols(SelectionState.DUMMY_SPACEBAR) ) )
			{

				if ( ( SelectionState.mFirstDown.is(KeyType.SPACE) && Settings.SWIPE_CURSOR_BEHAVIOR == CursorBehavior.SPACE_SWIPE) || !SelectionState.mFirstDown.is(KeyType.SPACE)   )
				{
					SelectionState.mValidFirstDown = true;

					if (SelectionState.mFirstDown.is(KeyType.SPACE))
					{
						if (Settings.SWIPE_CURSOR_BEHAVIOR == CursorBehavior.SPACE_SWIPE)
						{
							//SelectionCommons.mDelayNextPopup = true;
							PopupkeysCommons.requestPopupDelay();
						}
					}


					//The state of the pointer is determined in the ontouchevent listener, /this/ is called after that.
					//If the key is relevant, it needs to be run a second time here.
					if (SelectionState.mLastPointerDownAction == MotionEvent.ACTION_DOWN || SelectionState.mLastPointerDownAction == MotionEvent.ACTION_POINTER_DOWN)
					{
						if (SelectionState.mLastPointerDownInfo != null)
						{

							setDownPointerState(SelectionState.mLastPointerDownAction, SelectionState.mLastPointerDownInfo.downX, SelectionState.mFirstPointerDownInfo, SelectionState.mLastPointerDownInfo);
						}

					}
				}

			}
	}

	public static boolean handleLongPress()
	{
		if (DebugSettings.DEBUG_KEYS)
		{
			Log.i(LOGTAG, "Long press triggered!");
		}

		if (SelectionState.mSwiping || (SelectionState.mActionModifierDown && !(mFirstDown.is(KeyType.SPACE) && Settings.SWIPE_CURSOR_BEHAVIOR == CursorBehavior.SPACE_SWIPE))  )
			return true;
		//If we trigger a long-press on a character that has multiple sub-characters, we want to
		//block swiping. mMultipleKeyPopups is the list of keys that have multiple sub-characters.

		//There are some non-symbol/letter keys that have popups, such as the period key, thus the removal of this if clause.
		//There are also some key types specific to certain languages, such as KoreanKey. tl;dr symbol key type is not very useful.
		//else if (mFirstDown.is(KeyCommons.KeyType.SYMBOL) || mFirstDown.is(KeyCommons.KeyType.ENTER))
		{
			if (PopupkeysCommons.hasMultiplePopupKeys(mFirstDown.content) || mFirstDown.is(KeyType.ENTER)  || (mFirstDown.is(KeyType.SPACE)))
				SelectionState.mSwipeBlocked = true;
		}

		return false;
	}

}
