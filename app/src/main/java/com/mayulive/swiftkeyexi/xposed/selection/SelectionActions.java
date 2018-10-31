package com.mayulive.swiftkeyexi.xposed.selection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

import com.mayulive.swiftkeyexi.main.keyboard.HotkeyPanel;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.keyboard.PriorityKeyboardClassManager;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.pointerInformation;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.PointerState;

import static com.mayulive.swiftkeyexi.xposed.selection.SelectionState.mModifierKeyActions;

/**
 * Created by Roughy on 6/7/2017.
 */

public class SelectionActions
{


	protected static void handeModifierAction(pointerInformation mLastPointerInfo, String key)
	{
		if (key == null || key.isEmpty())
			return;

		key = key.toLowerCase();
		KeyboardInteraction.TextAction action = mModifierKeyActions.get(key);

		if (action != null)
		{
			KeyCommons.PerformTextAction( PriorityKeyboardClassManager.getInputConnection(), action);
			mLastPointerInfo.state = PointerState.ACTION;
			SelectionState.mActionTriggered = true;
		}
	}

	protected static void handleSpaceModifierKey(float posX, float posY)
	{
		//Ex
		String key = KeyCommons.getKeyAtLocation(posX , posY);
		if (key != null)
		{
			key = key.toLowerCase();
			KeyboardInteraction.TextAction action = mModifierKeyActions.get(key);
			handleTextAction(action, true);
		}
	}

	protected static void handleSpaceModifierMenu()
	{
		HotkeyPanel panel = OverlayCommons.getHotkeyPanel();

		//Order of operations maybe maybe.
		// Pointer-up is handled here rather than in the hotkeypanel though, so no listeners.
		if (panel != null)
		{
			HotkeyPanel.HotkeyMenuItem item = panel.getLastSelectedItem();
			if (item != null && item.action != KeyboardInteraction.TextAction.DEFAULT)
			{
				handleTextAction(item.action, true, item.text);
			}
		}
	}

	protected static void handleTextAction(KeyboardInteraction.TextAction action, boolean vibrate)
	{
		handleTextAction(action, vibrate, null);
	}

	@SuppressLint("MissingPermission")
	protected static void handleTextAction(KeyboardInteraction.TextAction action, boolean vibrate, @Nullable String text)
	{
		if (action != null)
		{
			//Vibrate?
			if(vibrate)
			{
				Vibrator v = (Vibrator) ContextUtils.getHookContext().getSystemService(Context.VIBRATOR_SERVICE);
				v.vibrate(KeyboardMethods.getVibrationDuration());
			}


			KeyCommons.PerformTextAction( PriorityKeyboardClassManager.getInputConnection(), action, text);
		}
	}

	protected static void sendKeyPress(int keyEvent, int repeatCount)
	{
		InputConnection connection =  PriorityKeyboardClassManager.getInputConnection();
		if (connection != null)
		{
			connection.sendKeyEvent(new KeyEvent(0,0, KeyEvent.ACTION_DOWN, keyEvent, repeatCount) );
			connection.sendKeyEvent(new KeyEvent(0,0, KeyEvent.ACTION_UP, keyEvent, 0) );
		}
	}

	protected static void sendShiftedKeyPress(int keyEvent, int repeatCount)
	{
		InputConnection connection =  PriorityKeyboardClassManager.getInputConnection();
		if (connection != null)
		{

			//Some apps react to the shift key-down event (everything), others only to the meta state (chrome).
			connection.sendKeyEvent(new KeyEvent(0,0, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT, 1 ));
			connection.sendKeyEvent(new KeyEvent(0,0, KeyEvent.ACTION_DOWN, keyEvent, repeatCount, KeyEvent.META_SHIFT_ON ));
			connection.sendKeyEvent(new KeyEvent(0,0, KeyEvent.ACTION_UP, keyEvent, 0, KeyEvent.META_SHIFT_ON ));
			connection.sendKeyEvent(new KeyEvent(0,0, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT, 0 ));

		}
	}
}
