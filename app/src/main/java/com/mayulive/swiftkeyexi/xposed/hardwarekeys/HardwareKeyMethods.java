package com.mayulive.swiftkeyexi.xposed.hardwarekeys;

import android.view.KeyEvent;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.database.WrappedProvider;
import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.main.commons.data.HardKey;
import com.mayulive.swiftkeyexi.main.commons.data.HardKeyType;
import com.mayulive.swiftkeyexi.main.commons.data.ModifierKeyItem;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.providers.Provider;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.keyboard.PriorityKeyboardClassManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.mayulive.swiftkeyexi.util.ContextUtils.getHookContext;

public class HardwareKeyMethods
{

	private static String LOGTAG = ExiModule.getLogTag(HardwareKeyMethods.class);

	//Modifiers ( with nested keys )
	private static Map<Integer, HardHotkeyKeyContainer> mModifiers_keycode = new HashMap<>();
	private static Map<Integer, HardHotkeyKeyContainer> mModifiers_scancode = new HashMap<>();

	//Keys ( shortcuts without modifiers)
	private static HardHotkeyKeyContainer mSingleKeyHotkeys = new HardHotkeyKeyContainer();

	//Active containers; single hotkeys or keys whose modifiers are down.
	private static HardHotkeyKeyContainer mActiveModifierHotkeys = new HardHotkeyKeyContainer();

	private static  long mLastHotkeyUpdateTime = -1;

	protected static boolean mHardwareShortcutsConfigured = false;

	//If the user does something stupid like use a non-modifier as a modifier, we need to prevent that
	//key from inputting anything. They will be unable to use the key for anything else.
	//Actual modifiers we can let pass though.
	private static Set<Integer> MODIFIER_KEYS = new HashSet<>();

	static
	{
		MODIFIER_KEYS.add( KeyEvent.KEYCODE_CTRL_LEFT );
		MODIFIER_KEYS.add( KeyEvent.KEYCODE_CTRL_LEFT );

		MODIFIER_KEYS.add( KeyEvent.KEYCODE_SHIFT_LEFT );
		MODIFIER_KEYS.add( KeyEvent.KEYCODE_SHIFT_RIGHT );

		MODIFIER_KEYS.add( KeyEvent.KEYCODE_ALT_LEFT );
		MODIFIER_KEYS.add( KeyEvent.KEYCODE_ALT_RIGHT );

		MODIFIER_KEYS.add( KeyEvent.KEYCODE_SYM );

		MODIFIER_KEYS.add( KeyEvent.KEYCODE_FUNCTION );
	}


	public static void populateHardwareHotkeys()
	{
		if (Settings.LAST_HOTKEYS_UPDATE > mLastHotkeyUpdateTime)
		{
			WrappedProvider dbWrap = Provider.getWrapped(getHookContext());
			ArrayList<DB_ModifierKeyItem> modifierKeys =  (ArrayList<DB_ModifierKeyItem>) DatabaseMethods.getAllItems(dbWrap,  TableInfoTemplates.MODIFIER_KEY_TABLE_INFO);

			clearAllHardHotkeys();

			for (DB_ModifierKeyItem currentKey : modifierKeys)
			{
				//Ignore soft key shortcuts
				if (currentKey.get_hotkey_type() != ModifierKeyItem.HotkeyKeyType.HARD)
					continue;

				mHardwareShortcutsConfigured = true;

				HardKey modifier = currentKey.get_hard_modifier_key();
				HardKey key = currentKey.get_hard_key();


				HardHotkeyKeyContainer container = null;
				if (modifier.getType() != HardKeyType.UNDEFINED)
					container = getOrCreateContainer(modifier);
				else
					container = mSingleKeyHotkeys;

				container.addMapping(key, currentKey.get_textAction());
			}


		}

		mLastHotkeyUpdateTime = Settings.LAST_HOTKEYS_UPDATE;
	}
	public static void clearAllHardHotkeys()
	{
		mModifiers_keycode.clear();
		mModifiers_scancode.clear();

		mSingleKeyHotkeys = new HardHotkeyKeyContainer();
		mActiveModifierHotkeys = new HardHotkeyKeyContainer();

		mHardwareShortcutsConfigured = false;
	}


	protected static HardHotkeyKeyContainer getContainerForKey(int code, HardKeyType type)
	{
		if (type == HardKeyType.KEY_CODE)
		{
			return mModifiers_keycode.get(code);
		}
		else
		{
			return mModifiers_scancode.get(code);
		}
	}

	protected static HardHotkeyKeyContainer getContainerForKey(int keyCode, int scanCode)
	{
		HardHotkeyKeyContainer container = null;
		container = mModifiers_keycode.get(keyCode);
		if (container == null)
			container = mModifiers_scancode.get(scanCode);

		return container;
	}

	private static HardHotkeyKeyContainer getOrCreateContainer(HardKey key)
	{
		HardHotkeyKeyContainer container = null;
		Map<Integer, HardHotkeyKeyContainer> map = null;

		if (key.getType() == HardKeyType.KEY_CODE)
			map = mModifiers_keycode;
		else
			map = mModifiers_scancode;

		container = map.get(key.getCodeForType());
		if (container == null)
		{
			container = new HardHotkeyKeyContainer();
			map.put(key.getCodeForType(), container);
		}

		return container;
	}

	public static class HardHotkeyKeyContainer
	{
		HardHotkeyKeyContainer()
		{

		}

		protected Map<Integer, KeyboardInteraction.TextAction> mKeys_keycode = new HashMap<>();
		protected Map<Integer, KeyboardInteraction.TextAction> mKeys_scancode = new HashMap<>();

		public void addMapping( HardKey key, KeyboardInteraction.TextAction action)
		{
			Map<Integer, KeyboardInteraction.TextAction> map = getMap(key.getType());
			map.put(key.getCodeForType(),action);
		}

		protected Map<Integer, KeyboardInteraction.TextAction> getMap(HardKeyType type)
		{
			if (type == HardKeyType.KEY_CODE)
				return mKeys_keycode;
			else
				return mKeys_scancode;
		}

		public KeyboardInteraction.TextAction getMapping(int keycode, int scanCode)
		{
			KeyboardInteraction.TextAction action = null;
			action = mKeys_keycode.get(keycode);
			if (action == null)
				action = mKeys_scancode.get(scanCode);

			return action;
		}

		public void addAll(HardHotkeyKeyContainer container)
		{
			this.mKeys_keycode.putAll(container.mKeys_keycode);
			this.mKeys_scancode.putAll(container.mKeys_scancode);
		}

		public void removeAll(HardHotkeyKeyContainer container)
		{
			//This is probably inefficient.
			//Depending on the number of hotkeys associated with a single modifier maps in general might be inefficient.

			for ( Map.Entry<Integer, KeyboardInteraction.TextAction> mapping : container.mKeys_keycode.entrySet())
			{
				mKeys_keycode.remove(mapping.getKey());
			}

			for ( Map.Entry<Integer, KeyboardInteraction.TextAction> mapping : container.mKeys_scancode.entrySet())
			{
				mKeys_scancode.remove(mapping.getKey());
			}
		}

		@Override
		public String toString()
		{
			return Arrays.toString( mKeys_keycode.entrySet().toArray() ) + Arrays.toString( mKeys_scancode.entrySet().toArray() );
		}
	}

	private static void handleTextAction(KeyboardInteraction.TextAction action)
	{
		KeyCommons.PerformTextAction( PriorityKeyboardClassManager.getInputConnection(), action);
	}

	public static boolean handleHotkeyKeyDown(int keyCode, int scanCode )
	{
		KeyboardInteraction.TextAction action;

		//Check single key actions
		action = mSingleKeyHotkeys.getMapping(keyCode, scanCode);

		if (action != null)
		{
			handleTextAction(action);
			return true;
		}

		action = mActiveModifierHotkeys.getMapping(keyCode, scanCode);

		if (action != null)
		{
			handleTextAction(action);
			return true;
		}

		//Key will be intercepted unless if modifier or lock.
		//basically don't use non-modifiers as modifiers, because you will be unable to use it for anything else.
		HardHotkeyKeyContainer container = getContainerForKey(keyCode, scanCode);
		if (container != null)
		{
			mActiveModifierHotkeys.addAll(container);

			if ( MODIFIER_KEYS.contains(keyCode) )
				return false;

			return true;
		}

		return false;
	}

	public static boolean handleHotkeyUp(int keyCode, int scanCode )
	{
		//Only needs to worry about clearing any associated active modifier mappings
		HardHotkeyKeyContainer container = getContainerForKey(keyCode, scanCode);
		if (container != null)
		{
			mActiveModifierHotkeys.removeAll(container);
		}

		return false;
	}
}
