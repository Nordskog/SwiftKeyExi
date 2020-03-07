package com.mayulive.swiftkeyexi.xposed.selection;

import androidx.annotation.Nullable;

import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.DB_HotkeyMenuItem;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.keyboard.PriorityKeyboardClassManager;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorSelection;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.UpDownMotionEvent;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SpaceModifierBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.pointerInformation;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Roughy on 3/20/2017.
 */

public class SelectionState
{

	private static String LOGTAG = ExiModule.getLogTag(SelectionState.class);


	//////////////////////////////
	//Constants
	//////////////////////////////

	static final KeyDefinition DUMMY_SPACEBAR = new KeyDefinition("", KeyType.SPACE);

	//////////////////////////////
	// KEy down and some states
	//////////////////////////////

	protected static boolean mSpaceModifierTriggered = false;	//swipe-from-space hotkey down
	protected static boolean mActionModifierDown = false;		//Modifier (ctrl) down
	protected static boolean mActionTriggered = false;			//Hotkey triggered
	protected static KeyDefinition mFirstDown = new KeyDefinition();
	protected static boolean mValidFirstDown = false;
	protected static boolean mSwiping = false;					//We are swiping (not swiftkey)
	protected static boolean mSwipeBlocked = false;				//We should not enter our swipe if true
	protected static boolean mSpaceDown = false;
	protected static boolean mShiftDown = false;
	protected static boolean mDeleteDown = false;
	protected static boolean mNumberDown = false;
	protected static long mSwipeEndTime = 0;

	protected static UpDownMotionEvent mLastUpDownEvent = UpDownMotionEvent.DEFAULT;

	protected static long mLastPointerUpTime = 0;	//Last time of a pointer up event

	protected static CharSequence mLastExtractedText = null;
	protected static int mLastExtractedTextOffset = 0;

	///////////////
	//Cursor position. Only modify with setSelectionChange()
	///////////////

	protected static boolean mIsRtl = false;
	protected static boolean mLastSelectionChangeWasKeyPress = false;
	protected static PointerState mLastFallbackSelectionPointerState = null;

	//If true, update cursor position from extracted text before moving horizontally
	protected static boolean mCursorMovedVertical = false;

	//Active cursor position while we're messing with it
	protected static int mCursorPosition = 0;
	protected static int mLeftSelectPosition = 0;
	protected static int mRightSelectPosition = 0;

	//The cursor position when we entered swipe, as we sometimes need to reset to it.
	protected static int mLeftSelectPosition_Original = 0;
	protected static int mRightSelectPosition_Original = 0;


	protected static pointerInformation mFirstPointerDownInfo = null;
	protected static pointerInformation mLastPointerDownInfo = null;

	protected static int mLastPointerDownAction = 0;
	protected static int mLastPointerCount = 0;
	protected static int mPrimaryPointerID = -1;

	protected static HashSet<String> mShiftKeys = new HashSet<String>();
	protected static HashSet<String> mDeleteKeys = new HashSet<String>();
	protected static HashSet<String> mSymbolKeys = new HashSet<String>();
	protected static EnumSet<KeyType> mShiftKeyEnums = EnumSet.noneOf(KeyType.class);
	protected static EnumSet<KeyType> mDeleteKeyEnums = EnumSet.noneOf(KeyType.class);
	protected static EnumSet<KeyType> mSymbolKeyEnums = EnumSet.noneOf(KeyType.class);
	protected static Map<String,KeyboardInteraction.TextAction> mModifierKeyActions = new HashMap<String,KeyboardInteraction.TextAction>();
	protected static Map<Integer,pointerInformation> mPointerInformation = new HashMap<Integer,pointerInformation>();

	protected static PointerState mLastState = PointerState.DEFAULT;

	protected static List<DB_HotkeyMenuItem> mHotkeyMenuItems = new ArrayList<DB_HotkeyMenuItem>();

	//////////

	//Keeping track of settings
	static long mLastAdditionalKeysUpdateTime = -1;
	static long mLastHotkeyUpdateTime = -1;
	static long mLastQuickmenuUpdateTime = -1;

	protected static SpaceModifierBehavior getSpaceModifierBehavior()
	{
		if (Settings.SPACE_MODIFIER_BEHAVIOR == SpaceModifierBehavior.KEY)
		{
			//Unsupported for some layouts, and probably not useful in symbols layouts
			if (KeyboardMethods.isLayoutWeird() || KeyboardMethods.isLayoutSymbols())
			{
				return SpaceModifierBehavior.MENU;
			}
		}
		return Settings.SPACE_MODIFIER_BEHAVIOR;
	}

	/////////////////////////////////////////////////////////////////////////////////////
	//Additional keys may be defined to function as shift / delete / symbols (modifier).
	/////////////////////////////////////////////////////////////////////////////////////

	static boolean isShift(KeyDefinition key)
	{
		if (mShiftKeyEnums.contains(key.getType()))
			return true;
		else if (key.is(KeyType.SYMBOL))
		{
			return mShiftKeys.contains(key.content);
		}

		return false;
 	}

	static boolean isDelete(KeyDefinition key)
	 {
		 if (mDeleteKeyEnums.contains(key.getType()))
			 return true;
		 else if (key.is(KeyType.SYMBOL))
		 {
			 return mDeleteKeys.contains(key.content);
		 }

		 return false;
	 }

	static boolean isSymbols(KeyDefinition key)
	 {
		 if (mSymbolKeyEnums.contains(key.getType()))
			 return true;
		 else if (key.is(KeyType.SYMBOL))
		 {
			 return mSymbolKeys.contains(key.content);
		 }

		 return false;
	 }

	 public static int getSwipeOverlayHeight()
	 {
	 	// TODO replace this, probably wrong width sometimes.
		 if (OverlayCommons.mKeyboardOverlay != null)
		 {
			 return OverlayCommons.mKeyboardOverlay.getMeasuredHeight();
		 }

		 return 0;
	 }

	static void setInternalSelectionValue(int startValue, int endValue, @Nullable PointerState state)
	{
		if (state == null)
		{
			SelectionState.mLeftSelectPosition = startValue;
			SelectionState.mRightSelectPosition = endValue;
			return;
		}

		mLastState = state;

		if (mIsRtl)
		{
			switch(state)
			{
				case LEFT_SWIPE:
				{
					mLeftSelectPosition = startValue;
					SelectionState.mRightSelectPosition = endValue;
					break;
				}
				case RIGHT_SWIPE:
				{
					SelectionState.mRightSelectPosition = startValue;
					mLeftSelectPosition = endValue;
					break;
				}
				case SWIPE:
				{
					mCursorPosition = endValue;
					mLeftSelectPosition = endValue;
					SelectionState.mRightSelectPosition = endValue;
				}

				default:
				{

				}
			}
		}
		else
		{
			switch(state)
			{
				case LEFT_SWIPE:
				{
					SelectionState.mRightSelectPosition = startValue;
					mLeftSelectPosition = endValue;
					break;
				}
				case RIGHT_SWIPE:
				{
					mLeftSelectPosition = startValue;
					SelectionState.mRightSelectPosition = endValue;
					break;
				}
				case SWIPE:
				{
					mCursorPosition = endValue;
					mLeftSelectPosition = endValue;
					SelectionState.mRightSelectPosition = endValue;
				}

				default:
				{

				}
			}
		}
	}

	//Start-end
	static CursorSelection getInternalSelection(@Nullable PointerState state)
	{
		if (state == null)
		{
			return new CursorSelection(SelectionState.mLeftSelectPosition,SelectionState.mRightSelectPosition);
		}

		if (mIsRtl)
		{
			switch(state)
			{
				case LEFT_SWIPE:
				{
					return new CursorSelection(mLeftSelectPosition, SelectionState.mRightSelectPosition);
				}
				case RIGHT_SWIPE:
				{
					return new CursorSelection(SelectionState.mRightSelectPosition, mLeftSelectPosition);
				}
				case SWIPE:
				{
					return new CursorSelection(mCursorPosition, mCursorPosition);
				}

				default:
				{
					return new CursorSelection(SelectionState.mLeftSelectPosition,SelectionState.mRightSelectPosition);
				}
			}
		}
		else
		{
			switch(state)
			{
				case LEFT_SWIPE:
				{
					return new CursorSelection(SelectionState.mRightSelectPosition, mLeftSelectPosition);
				}
				case RIGHT_SWIPE:
				{
					return new CursorSelection(mLeftSelectPosition, SelectionState.mRightSelectPosition);
				}
				case SWIPE:
				{
					return new CursorSelection(mCursorPosition, mCursorPosition);
				}

				default:
				{
					return new CursorSelection(SelectionState.mLeftSelectPosition,SelectionState.mRightSelectPosition);
				}
			}
		}


	}

	protected static pointerInformation getOtherPointer(pointerInformation currentPointer)
	{
		for (pointerInformation otherPointer : mPointerInformation.values())
		{
			if (otherPointer != currentPointer)
				return otherPointer;
		}

		return null;
	}

	protected static void ensureCursorOrder()
	{
		if (mLeftSelectPosition > SelectionState.mRightSelectPosition)
		{
			int tempSelection = SelectionState.mRightSelectPosition;
			SelectionState.mRightSelectPosition = mLeftSelectPosition;
			mLeftSelectPosition = tempSelection;
		}
	}

	protected static boolean cursorAtBeginning()
	{
		//We should consider the offset, but is is extremely unlikely
		//that the user will ever be able to move anywhere near it
		//without triggering a selection update
		return mCursorPosition == 0;
	}

	protected static boolean cursorAtEnd()
	{
		//Was there an offset here? I don't remember
		if ( mLastExtractedText != null && mCursorPosition >= mLastExtractedText.length() )
		{
			return true;
		}

		return false;
	}

	protected static boolean isSelecting()
	{
		return mLeftSelectPosition != mRightSelectPosition;
	}


	protected static void updateSelection()
	{
		InputConnection connection = PriorityKeyboardClassManager.getInputConnection();

		if (connection != null)
		{
			ExtractedText extractedText = connection.getExtractedText(new ExtractedTextRequest(), 0);

			if (extractedText != null)
			{
				mLastExtractedText = extractedText.text;
				mLastExtractedTextOffset = extractedText.startOffset;

				mLeftSelectPosition = extractedText.selectionStart;
				SelectionState.mRightSelectPosition = extractedText.selectionEnd;
				mCursorPosition = mRightSelectPosition;

				mLeftSelectPosition_Original = mLeftSelectPosition;
				mRightSelectPosition_Original = mRightSelectPosition;
			}
		}
	}

	public static UpDownMotionEvent getLastUpDownEvent()
	{
		return mLastUpDownEvent;
	}



	public static boolean isSwipeAllowed()
	{
		if (mSwiping)
		{
			return true;
		}
		else
		{
			if (mSwipeBlocked)
				return false;

			//There's a quick swipe gesture on the period key for inserting ! and ?
			//However, Once you start swiping it triggers another keypress of god only knows what type.
			//Solution: In handleFirstKeyDown we set swipeBlocked to true on period down
			if (SelectionState.mFirstDown.is(KeyType.PERIOD))
				return false;

			switch(Settings.SWIPE_CURSOR_BEHAVIOR)
			{
				case SWIPE:
				{
					//Block swiftkey swipe
					return true;
				}
				case HOLD_SHIFT_SWIPE:
				{
					//If shift is held (or was teh first to be hit), block flow
					if (mShiftDown && mLastPointerCount > 1)
						return true;

					if ( Settings.SWIPE_SELECTION_BEHAVIOR.triggersFromShiftAndDelete() )
					{
						if (mShiftDown || mDeleteDown)
							return true;
					}

					break;
				}
				case HOLD_ANY_SWIPE:
				{
					//Swipe should never be triggered if there are
					//more than two pointers down
					if (mLastPointerCount > 1)
						return true;

					if ( Settings.SWIPE_SELECTION_BEHAVIOR.triggersFromShiftAndDelete() )
					{
						if (mShiftDown || mDeleteDown)
							return true;
					}


					break;
				}
				case SPACE_SWIPE:
				{
					//Movement on space bar does not trigger flow.
					//UNLESS we can select from shift-delete keys

					if ( Settings.SWIPE_SELECTION_BEHAVIOR.triggersFromShiftAndDelete() )
					{
						if (mShiftDown || mDeleteDown)
							return true;
					}


					if (mSpaceDown)
						return true;

					break;
				}
				case NUMBER_ROW_SWIPE:
				{
					if (mNumberDown)
						return true;
					else if ( Settings.SWIPE_SELECTION_BEHAVIOR.triggersFromShiftAndDelete() )
					{
						if (mShiftDown || mDeleteDown)
							return true;
					}

					break;
				}

				default:
					return false;

			}

			return false;
		}
	}

	protected static void clearState(int pointerCount)
	{
		if (mSwiping)
		{
			if (mLastSelectionChangeWasKeyPress)
			{
				// When the last movement was caused by a key press ( setSelectionFallback() or setSelectionVertical() ),
				// it is undone when we end batch mode. Fix by getting updated selection from
				// inputconnection and setting it again.
				SelectionMethods.updateAndFinalizeSelection();
			}

			SelectionMethods.inputBatchMode(false);
		}

		mCursorMovedVertical = false;
		SelectionState.mFirstDown = new KeyDefinition();
		mSwiping = false;
		mValidFirstDown = false;
		mActionModifierDown = false;
		mShiftDown = false;
		mDeleteDown = false;
		mSpaceDown = false;
		mLastPointerCount = pointerCount;
		mSwipeBlocked = false;
		mActionTriggered = false;
		mSpaceModifierTriggered = false;
		mNumberDown = false;

		KeyCommons.setCancelAllKeys(false);
	}

	//I'll ... just leave this here
	private static float distance(float originX, float originY, float posX, float posY)
	{
	    float x = posX - originX;
		float y = posY - originY;

		x *= x;
		y *= y;

		return (float) Math.sqrt( x+y );
	}

	static void calculateDistance(pointerInformation pointer, float newX, float newY)
	{
		pointer.xDistanceLast = pointer.xDistance;
		pointer.yDistanceLast = pointer.yDistance;

		pointer.xDistance = pointer.downX - newX;
		pointer.yDistance = pointer.downY - newY;

		pointer.xDistanceChange = (pointer.xDistanceLast - pointer.xDistance);
		pointer.yDistanceChange = (pointer.yDistanceLast - pointer.yDistance);

		switch(pointer.modifier)
		{
			case DEFAULT:
				pointer.xCursorDistanceChange = pointer.xDistanceChange;
				pointer.yCursorDistanceChange = pointer.yDistanceChange;
				break;
			case ZERO:
				pointer.xCursorDistanceChange = 0;
				pointer.yCursorDistanceChange = 0;
				break;
			case DOUBLE:
				pointer.xCursorDistanceChange = pointer.xDistanceChange * 10;
				pointer.yCursorDistanceChange = pointer.yDistanceChange * 10;
				break;
		}

		pointer.xCursorDistanceChange +=  pointer.xCursorBank;
		pointer.xCursorBank = pointer.xCursorDistanceChange % Settings.SWIPE_CURSOR_UNITS;
		pointer.xCursorDistanceChange /= Settings.SWIPE_CURSOR_UNITS;

		pointer.yCursorDistanceChange +=  pointer.yCursorBank;
		pointer.yCursorBank = pointer.yCursorDistanceChange % Settings.SWIPE_CURSOR_UNITS;
		pointer.yCursorDistanceChange /= Settings.SWIPE_CURSOR_UNITS;
	}


}
