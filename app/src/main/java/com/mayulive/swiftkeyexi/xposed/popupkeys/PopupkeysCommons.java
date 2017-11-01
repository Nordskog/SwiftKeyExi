package com.mayulive.swiftkeyexi.xposed.popupkeys;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupKeyItem;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by Roughy on 2/15/2017.
 */

public class PopupkeysCommons
{

	private static String LOGTAG = ExiModule.getLogTag(PopupkeysCommons.class);

	protected static boolean mDelayNextPopup = false;
	protected static long mLastUpdateTime = -1;

	public static final String NULLCHAR = "\0";
	//public static final String NULLCHAR = "IAMADOG!&=";

	//Things we need to keep track of when the popup keys adding thingy is being called
	//protected static boolean mKeyHitAfterKeyboardLoad = false;
	//public static String mLastSymbolsChar = null;


	//protected static boolean mLowerCaseAdded = false;

	protected static String mLastASymbol = "";
	protected static String mLastOrderSymbol = "";

	protected static Set<String> mMultipleKeyPopups = new HashSet<String>();
	protected static DB_PopupParentKeyItem mLastPopupParentKey = null;	//Our keys
	protected static List<String> mLastInitialPopupkeyList = null;			//Swiftkey's keys


	//Map a string to an action for popups keys
	protected static Map<String,KeyboardInteraction.TextAction> mPopupKeyActions = new HashMap<String,KeyboardInteraction.TextAction>();

	//For each parent key (key that can be long-pressed to popup more keys), a list of keys to insert into the popup.
	protected static Map<String, DB_PopupParentKeyItem> mPopupKeys = new HashMap<String,DB_PopupParentKeyItem >();

	public static boolean hasMultiplePopupKeys(String key)
	{
		//If the popups_read hook is not actie this data will not be valid.
		//Fall back is to assume that all keys have multiple popups.
		//Not ideal, but most things will keep working fine-ish
		if (Hooks.popupHooks_read.isRequirementsMet())
		{
			boolean value = mMultipleKeyPopups.contains(key);
			if (DebugSettings.DEBUG_POPUPS)
			{
				Log.i(LOGTAG, "Check multiple popup. Key: "+key+", has multiple?: "+value);
				Log.i(LOGTAG, "Hash was: "+Integer.toHexString(key.hashCode())+", also contains:" ) ;
			}
			return value;
		}
		else
		{
			if (DebugSettings.DEBUG_POPUPS)
			{
				Log.i(LOGTAG, "Checking for multiple popup, but popup read hook not active. Returning true.");
			}

			return true;
		}
	}

	///////////////////////////////
	//Long-press popup key stuff
	///////////////////////////////


	private static ArrayList<OnLongPressTriggeredListener> mLongPressListeners = new ArrayList<OnLongPressTriggeredListener>();


	public interface OnLongPressTriggeredListener
	{
		//Return true if the key should be cancelled
		boolean onLongPressTriggered();
	}

	public static void addOnLongPressListener(OnLongPressTriggeredListener listener)
	{
		mLongPressListeners.add(listener);
	}

	public static void removeOnKeyUpListener(OnLongPressTriggeredListener listener)
	{
		mLongPressListeners.remove(listener);
	}

	//Returns true if press should be cancelled
	protected static boolean callLongPressListeners()
	{
		boolean returnValue = false;

		for (OnLongPressTriggeredListener listener : mLongPressListeners)
		{
			returnValue = listener.onLongPressTriggered() || returnValue;
		}

		return returnValue;
	}

	////////////////////////////
	//Popup scheduelr stuff
	////////////////////////////
	public static void requestPopupDelay()
	{
		mDelayNextPopup = true;
	}


}
