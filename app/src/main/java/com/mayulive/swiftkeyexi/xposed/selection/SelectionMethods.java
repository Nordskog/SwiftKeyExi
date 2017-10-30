package com.mayulive.swiftkeyexi.xposed.selection;

import java.util.Map.Entry;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardClassManager;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysCommons;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysMethods;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SpaceModifierBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.pointerInformation;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.predictions.PredictionCommons;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorCommons;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorSelection;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SelectionBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SwipeSpeedModifier;

import android.text.Selection;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import static com.mayulive.swiftkeyexi.util.ContextUtils.getModuleContext;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.isDelete;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.isShift;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mLastExtractedText;
import static com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState.LEFT_SWIPE;
import static com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState.RIGHT_SWIPE;
import static com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState.SWIPE;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mFirstDown;
import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mModifierKeyActions;


public class SelectionMethods
{

	private static String LOGTAG = ExiModule.getLogTag(PopupkeysMethods.class);

	protected static void inputBatchMode(boolean enable)
	{
		InputConnection connection = KeyboardClassManager.getInputConnection();

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
		InputConnection connection = KeyboardClassManager.getInputConnection();
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
		InputConnection connection = KeyboardClassManager.getInputConnection();
		SelectionMethods.setSelectionWithoutOffset(connection, 0,0 );
	}

	public static void moveCursorToEnd()
	{
		InputConnection connection = KeyboardClassManager.getInputConnection();
		SelectionState.updateSelection();
		SelectionMethods.setSelectionWithOffset( connection, SelectionState.mLastExtractedText.length(), SelectionState.mLastExtractedText.length());
	}

	//Set selection using the previous values.
	private static boolean finalizeSelection()
	{
		InputConnection connection = KeyboardClassManager.getInputConnection();
		if (connection != null)
		{

			CursorSelection selection = SelectionState.getInternalSelection( SelectionState.mLastState );
			return setSelectionWithOffset ( connection, selection.start, selection.end );
		}

		return false;
	}


	public static void setSelectionChange(int change, final PointerState state)
	{
		//Just incase
		if (SelectionState.mLastExtractedText == null)
			SelectionState.mLastExtractedText = new String();

		boolean direction = change >= 0;	//true forward, false backwards

		CursorSelection selection = SelectionState.getInternalSelection(state);

		//The view will scroll to follow the end value, so that's always what
		//we will be changing.

		//Set new position
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

		InputConnection connection = KeyboardClassManager.getInputConnection();

		//but the chances of this ever being a problem are fairly slim, and the consequences minor.

		if (connection != null && selection.end > 0 && selection.end < SelectionState.mLastExtractedText.length())
		{
			selection = CursorCommons.moveCursorOutsideSurrogate(direction, selection.start, selection.end, SelectionState.mLastExtractedText, state);

			selection = CursorCommons.moveCursorOutsideCWJandVariant(selection.start, selection.end, SelectionState.mLastExtractedText, direction, state);

			//TODO consider ZWNJ too? How would that even work?
			//TODO Combining marks, but any IME worth a damn will input 99% of them as distinct characters, so probably won't be a problem.
		}

		SelectionState.setInternalSelectionValue(selection.start,selection.end, state);
		setSelectionWithOffset(connection,selection.start,selection.end);

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


				//Log.e("###", "New cursor state: "+currentPointerInfo.state);
				
				//ensureCursorOrder();

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
			if (Settings.SWIPE_CURSOR_BEHAVIOR  == CursorBehavior.SWIPE || Settings.SWIPE_CURSOR_BEHAVIOR  == CursorBehavior.SPACE_SWIPE)
			{
				if (Settings.SWIPE_CURSOR_BEHAVIOR  == CursorBehavior.SPACE_SWIPE)
				{
					//Space swipe requires that first key down is space
					if ( SelectionState.mFirstDown.is(KeyType.SPACE) )
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

		//Log.e("###", "Pointer count: "+event.getPointerCount());

		SelectionState.mLastIndex = index;
		
		float newX = event.getX(index);
		float newY = event.getY(index);
		
		int actionIndex = event.getActionIndex();
		
		int pointerID = event.getPointerId(index);
		int action = event.getActionMasked();
		int pointerCount = event.getPointerCount();
		
		boolean actionIsCurrent = actionIndex == index;
		boolean isPrimary = pointerID == SelectionState.mPrimaryPointerID;



		pointerInformation currentPointerInfo = SelectionState.mPointerInformation.get(pointerID);

		/*
		if (currentPointerInfo == null)
		{
			currentPointerInfo = new pointerInformation();
			mPointerInformation.put(pointerID, currentPointerInfo);
		}
		*/
		
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

			//downX = event.getX();
			currentPointerInfo.downX = newX;
			currentPointerInfo.downY = newY;

			//calculateDistance(currentPointerInfo, newX,newY);

			setDownPointerState(action, newX, firstPointerInfo, currentPointerInfo);
			
			//Log.e("###", "Down: "+currentPointerInfo.state.toString());

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

			//calculateDistance(currentPointerInfo, newX, newY);

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
							 				Settings.SWIPE_CURSOR_BEHAVIOR != CursorBehavior.SPACE_SWIPE || currentPointerInfo.key.is(KeyType.SPACE)
										|| Settings.SWIPE_SELECTION_BEHAVIOR.triggersFromShiftAndDelete() && ( isShift(currentPointerInfo.key) || isDelete(currentPointerInfo.key) )
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

									InputConnection connection = KeyboardClassManager.getInputConnection();

									//Finish any composing going on.
									//Doing this directly probably angers swiftkey a bit, but nothing breaks.
									if (connection != null)
									{
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
						if ( Math.abs(currentPointerInfo.xDistance) > Settings.SWIPE_THRESHOLD)
						{
							setSwipePointerState(firstPointerInfo, currentPointerInfo, false);
						}
					}
				}
			}
			else if ( !PointerState.isSwipe(currentPointerInfo.state) )
			{
				if ( Math.abs(currentPointerInfo.xDistance) > Settings.SWIPE_THRESHOLD)
				{
					setSwipePointerState(firstPointerInfo, currentPointerInfo, false);
				}
			}


			///////////////////////////////////////////////////////////////
			//Perform appropriate action if we are swiping
			///////////////////////////////////////////////////////////////


			if (SelectionState.mSwiping || SelectionState.mSpaceModifierTriggered)
			{


				if (SelectionState.mSpaceModifierTriggered)
				{

					//Compact or other weird layout mode.
					//View may be smaller and gravity'd right
					//TODO cache somewhere
					float xOffset = SelectionState.getKeyboardHorizontalOffset(view);

					if (SelectionState.getSpaceModifierBehavior() == SpaceModifierBehavior.MENU)
					{

						if (!OverlayCommons.isHotkeyMenuDisplayed())
						{
							//Log.e("###", "Spacebar height value is: "+KeyCommons.getSpacebarHeight());

							float spaceDistanceFromBottom = ( 1.0f - mFirstDown.hitbox.top )*view.getMeasuredHeight();



							OverlayCommons.displayHotkeyMenu( spaceDistanceFromBottom, view.getMeasuredHeight(), currentPointerInfo.downX + xOffset, SelectionState.mHotkeyMenuItems );
						}

						OverlayCommons.handleDisplayHotkeyMenuTouch(event.getX() + xOffset , event.getY(), view.getMeasuredHeight());

					}
					else if (SelectionState.getSpaceModifierBehavior()== SpaceModifierBehavior.KEY)
					{
						KeyDefinition key = KeyCommons.getHitboxAtLocation(event.getX() / view.getMeasuredWidth(), event.getY() / view.getMeasuredHeight());

						if (key != null)
						{
							//Log.e("###", "Key not null");

							if (!OverlayCommons.isDisplayed(key.getContent()))
							{
								//Log.e("###", "Not displayed");

								OverlayCommons.clearPopups();

								KeyboardInteraction.TextAction textAction = mModifierKeyActions.get(key.getContent().toLowerCase());

								if (textAction == null)
								{
									//Log.e("###", "No action");

									OverlayCommons.setPopupKeyManually( key.getContent() );
								} else
								{

									//Log.e("###", "Action");

									float keyY = (key.hitbox.bottom - key.hitbox.top) * view.getMeasuredHeight();
									float keyX = (key.hitbox.bottom - key.hitbox.top) * view.getMeasuredHeight();

									int paddingX = (int) (keyX * 0.25);
									int paddingY = (int) (keyY * 0.25);
									float textSize = (float) (keyY * 0.7);


									float center = view.getMeasuredWidth() * key.hitbox.centerX();
									//Keyboard may be in comapct mode, add x position offset
									center += xOffset;

									float bottom = view.getMeasuredHeight() * key.hitbox.top;

									float yPos = bottom;
									yPos = view.getMeasuredHeight() - yPos;    //From bottom

									OverlayCommons.setPopupDimensions(textSize, paddingX, paddingY);
									OverlayCommons.displayKeyAbove(key.getContent(), KeyboardInteraction.TextAction.getTextRepresentation(getModuleContext(), textAction), yPos, center);
								}
							} else
							{
								//Log.e("###", "Already displayed");
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

						//Prioritize vertical change
						if (yCursorChange != 0 && !SelectionState.isSelecting())
						{
							//Update selection so we can check if we can actually move anywhere
							SelectionState.updateSelection();


							//Negative should move up, positive down
							//Note that regardless of how much we want to move it, we limit it to 1.
							//This is because dpad cursor keys will move the focus to the next field
							//or item if we are at the beginning /end of the text.
							if (yCursorChange > 0)
							{
								if (!SelectionState.cursorAtEnd() )
								{
									yCursorChange = 1;
									SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_DOWN, yCursorChange);
								}
							}
							else
							{
								if (!SelectionState.cursorAtBeginning())
								{
									yCursorChange = 1;
									SelectionActions.sendKeyPress(KeyEvent.KEYCODE_DPAD_UP, yCursorChange);
								}
							}

							if (Settings.DISABLE_SWIPE_AUTO_CORRECT)
								PredictionCommons.requestLiteralPrimarySuggestion(true);

							SelectionState.mCursorMovedVertical = true;

							//Clear bank of other axis to make it less finicky
							currentPointerInfo.xCursorBank = 0;
						}
						else if (xCursorChange != 0)
						{
							//We have no idea where the cursor ends up when we move vertically,
							//so selection must be updated before we can move horizontally again.
							//TODO cursor order?
							if (SelectionState.mCursorMovedVertical)
							{
								SelectionState.mCursorMovedVertical = false;
								SelectionState.updateSelection();
							}

							if (Settings.DISABLE_SWIPE_AUTO_CORRECT)
								PredictionCommons.requestLiteralPrimarySuggestion(true);

							setSelectionChange(xCursorChange, currentPointerInfo.state);

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

			if (SelectionState.mSpaceModifierTriggered)
			{
				//Log.e("###", "Space modifier event triggered");

				if (SelectionState.getSpaceModifierBehavior() == SpaceModifierBehavior.KEY)
				{
					//OverlayCommons.removeKeyOver();
					SelectionActions.handleSpaceModifierKey(event.getX() / view.getMeasuredWidth(), event.getY() / view.getMeasuredHeight());
				}
				else
				{
					SelectionActions.handleSpaceModifierMenu();
				}
				OverlayCommons.clearPopups();
			}

			//if ( ( currentPointerInfo.state == PointerState.MODIFIER || currentPointerInfo.state == PointerState.ACTION ) && mActionTriggered)
			if (SelectionState.mActionTriggered)
			{
				//Log.e("###", "Main up, cancel next");
				KeyCommons.requestCancelNextKey();
			}

			SelectionState.clearState(0);
			SelectionState.mSwipeEndTime = System.currentTimeMillis();

			SelectionState.mPointerInformation.remove(pointerID);

			return false;
		}
		else if (actionIsCurrent && action == MotionEvent.ACTION_POINTER_UP)
		{
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
				//Log.e("###", "Key was modifier, disabling modifier, cancel all.");
				SelectionState.mActionModifierDown = false;    // ???
				KeyCommons.setCancelAllKeys(false);
			}

			//if ((currentPointerInfo.state == SelectionCommons.PointerState.MODIFIER || currentPointerInfo.state == SelectionCommons.PointerState.ACTION) && SelectionCommons.mActionTriggered)
			if (SelectionState.mActionTriggered)
			{
				//Log.e("###", "Action triggered, requesting cancel");
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
		
		//Log.e("###", "##########Event: "+event.getActionMasked());
		//printPointerStates();

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
