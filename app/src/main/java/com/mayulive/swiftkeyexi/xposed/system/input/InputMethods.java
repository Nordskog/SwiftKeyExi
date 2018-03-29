package com.mayulive.swiftkeyexi.xposed.system.input;

import android.util.Log;
import android.view.KeyEvent;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.database.WrappedProvider;
import com.mayulive.swiftkeyexi.main.commons.data.DB_RemappedKey;
import com.mayulive.swiftkeyexi.main.commons.data.HardKey;
import com.mayulive.swiftkeyexi.main.commons.data.HardKeyType;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.providers.Provider;
import com.mayulive.swiftkeyexi.util.ContextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roughy on 3/22/2018.
 */

public class InputMethods
{

	private static String LOGTAG = ExiModule.getLogTag(InputMethods.class);

	//Remapped keys
	private static Map<Integer, HardKey> mRemappedKeycodes = new HashMap<>();
	private static Map<Integer, HardKey> mRemappedScanCodes = new HashMap<>();

	//Metastate containing modifiers we have mapped to.
	//Where possible contains the actual keys( left ctrl, right ctrl) instead of the common state (ctrl)
	private static int mActiveVirtualModifiers = 0;

	//Metastate containing modifiers applied by keys we are mapping from.
	//If present, they should be removed from the key metastate.
	//This filter should be applied before the virtual states are applied.
	private static int mFilteredModifiers = 0;

	//Keycode to metastate map for modifiers
	private static Map<Integer, Integer> MODIFIER_KEYCODE_TO_META = new HashMap<>();

	//Keycode to metastate map for locks (scroll, caps, scroll). Does anyone even use scroll-lock?
	private static Map<Integer, Integer> LOCK_KEYCODE_TO_META = new HashMap<>();

	//An unused bitflag, borrowed from xposedaddition.
	public static int FLAG_INJECTED = 0x40000000;

	//If any key has been mapped to caps/num/scroll lock.
	protected static boolean mVirtualLockKeyExists = false;
	//If any keys have been remapped
	protected static boolean mKeysRemapped = false;

	static
	{
		//////////////
		// Modifiers
		//////////////

		// ctrl
		MODIFIER_KEYCODE_TO_META.put( KeyEvent.KEYCODE_CTRL_LEFT, KeyEvent.META_CTRL_LEFT_ON );
		MODIFIER_KEYCODE_TO_META.put( KeyEvent.KEYCODE_CTRL_RIGHT, KeyEvent.META_CTRL_RIGHT_ON );

		// shift
		MODIFIER_KEYCODE_TO_META.put( KeyEvent.KEYCODE_SHIFT_LEFT, KeyEvent.META_SHIFT_LEFT_ON );
		MODIFIER_KEYCODE_TO_META.put( KeyEvent.KEYCODE_SHIFT_RIGHT, KeyEvent.META_SHIFT_RIGHT_ON );

		// alt. Note that right-alt is actually alt-gr in some locales, so telling them apart is important.
		MODIFIER_KEYCODE_TO_META.put( KeyEvent.KEYCODE_ALT_LEFT, KeyEvent.META_ALT_LEFT_ON );
		MODIFIER_KEYCODE_TO_META.put( KeyEvent.KEYCODE_ALT_RIGHT, KeyEvent.KEYCODE_ALT_RIGHT );

		// sym
		MODIFIER_KEYCODE_TO_META.put( KeyEvent.KEYCODE_SYM, KeyEvent.META_SYM_ON );

		// fun. as in function, not the gay fun.
		MODIFIER_KEYCODE_TO_META.put( KeyEvent.KEYCODE_FUNCTION, KeyEvent.META_FUNCTION_ON );

		// meta ... meta?
		MODIFIER_KEYCODE_TO_META.put( KeyEvent.KEYCODE_META_LEFT, KeyEvent.META_META_LEFT_ON );
		MODIFIER_KEYCODE_TO_META.put( KeyEvent.KEYCODE_META_RIGHT, KeyEvent.META_META_RIGHT_ON );

		/////////////////
		// Locks
		/////////////////

		// Scroll
		LOCK_KEYCODE_TO_META.put( KeyEvent.KEYCODE_SCROLL_LOCK, KeyEvent.META_SCROLL_LOCK_ON );

		//Caps
		LOCK_KEYCODE_TO_META.put( KeyEvent.KEYCODE_CAPS_LOCK, KeyEvent.META_CAPS_LOCK_ON );

		//Num
		LOCK_KEYCODE_TO_META.put( KeyEvent.KEYCODE_NUM_LOCK, KeyEvent.META_NUM_LOCK_ON );
	}


	public static void populateRemappedKeys()
	{
		clearAllRemappings();

		WrappedProvider dbWrap = Provider.getWrapped(ContextUtils.getSystemContext());
		ArrayList<DB_RemappedKey> remappedKeys =  (ArrayList<DB_RemappedKey>) DatabaseMethods.getAllItems(dbWrap,  TableInfoTemplates.REMAPPED_HARDWAREKEYS_TABLE_INFO);

		mKeysRemapped = !remappedKeys.isEmpty();

		for (DB_RemappedKey currentKey : remappedKeys)
		{
			Map<Integer, HardKey> map;

			HardKey from = currentKey.getFrom_key();
			HardKey to = currentKey.getTo_key();

			int key;

			if (from.keycode > 0)
			{
				//If we map from scroll/caps/num lock, it should be permanently
				//addeed to the filtered metastates.
				int meta = lockMetaMapLookup(from.keycode);
				if (meta != 0)
				{
					mFilteredModifiers = mFilteredModifiers | meta;
				}

				map = mRemappedKeycodes;
				key = from.keycode;
			}
			else
			{
				map = mRemappedScanCodes;
				key = from.scancode;
			}

			if ( LOCK_KEYCODE_TO_META.containsKey( to.keycode) )
			{
				//Special condition if we are mapping to a lock key
				mVirtualLockKeyExists = true;
			}

			map.put( key, to);
		}
	}

	public static void clearAllRemappings()
	{
		mRemappedKeycodes.clear();
		mRemappedScanCodes.clear();
		mFilteredModifiers = 0;
		mActiveVirtualModifiers = 0;
		mVirtualLockKeyExists = false;
		mKeysRemapped = false;
	}


	//Add metastate of key we are replacing to filter to be excluded
	private static void updatFilteredModifiers(int sourcekeycode, int action)
	{
		//If we map from a modifier to another key, we have to remove its metastate from all key inputs.
		//However, we map by mapping yet another key to the original modifier, so we apply this filter first,
		//then apply any virtual modifiers afterwards.
		int modifierMeta = modifierMetaMapLookup(sourcekeycode);
		if (modifierMeta != 0)
		{
			if (action == KeyEvent.ACTION_UP || action == KeyEvent.ACTION_MULTIPLE )
			{
				//Include action_multiple here because it can be literally anything.
				mFilteredModifiers = mFilteredModifiers & ~modifierMeta;
			}
			else if (action == KeyEvent.ACTION_DOWN )
			{
				mFilteredModifiers = mFilteredModifiers | modifierMeta;
			}
		}

	}

	private static int modifierMetaMapLookup(int meta)
	{
		Integer ret =  MODIFIER_KEYCODE_TO_META.get(meta);
		if (ret != null)
			return ret;
		else
			return 0;
	}

	private static int lockMetaMapLookup(int meta)
	{
		Integer ret =  LOCK_KEYCODE_TO_META.get(meta);
		if (ret != null)
			return ret;
		else
			return 0;
	}


	//Add require metastates to the list of active state if mapping to modifier
	private static void updateActiveVirtualModifiers( int targetKeycode, int action )
	{
		int modifierMeta = modifierMetaMapLookup(targetKeycode);

		if (modifierMeta != 0)
		{
			if (action == KeyEvent.ACTION_UP || action == KeyEvent.ACTION_MULTIPLE )
			{
				//Include action_multiple here because it can be literally anything.
				mActiveVirtualModifiers = mActiveVirtualModifiers & ~modifierMeta;
			}
			else if (action == KeyEvent.ACTION_DOWN )
			{
				mActiveVirtualModifiers = mActiveVirtualModifiers | modifierMeta;
			}
		}

	}

	//Add require metastates to the list of active state if mapping to lock
	private static void updateActiveVirtualLocks( int targetKeycode, int action )
	{
		//Just to make damn sure any problems introduce can be avoided by not having lock maps
		if (mVirtualLockKeyExists)
		{
			int modifierMeta = lockMetaMapLookup(targetKeycode);

			//Locks just flip the bit on every down.
			//Note that it will never be in sync with the real lock
			if (modifierMeta != 0 && action == KeyEvent.ACTION_DOWN )
			{
				mActiveVirtualModifiers = mActiveVirtualModifiers ^ modifierMeta;

				// If deactivated, we also need to add the filter the flag so it ignores
				// the real lock, otherwise it will only be in effect when the real
				// is disabled
				if ( (mActiveVirtualModifiers & modifierMeta) != modifierMeta)
				{
					//Bit set to off, add to filtered
					mFilteredModifiers = mFilteredModifiers | modifierMeta;
				}
				else
				{
					// remove from filtered
					mFilteredModifiers = mFilteredModifiers & ~modifierMeta;
				}
			}
		}
	}

	public static boolean isInjected(KeyEvent event)
	{
		return (event.getFlags() & InputMethods.FLAG_INJECTED) == InputMethods.FLAG_INJECTED;
	}

	// If the real lock button is remapped, its value should always
	// override any virtual keys mapped to it.
	protected static void syncLockFlag(KeyEvent event)
	{
		int keycode = event.getKeyCode();

		if (event.getAction() == KeyEvent.ACTION_UP && LOCK_KEYCODE_TO_META.containsKey(keycode) )
		{

			//But skip if this is an injected event
			if (isInjected(event))
				return;

			//When a lock key is pressed, it's metastate will remain what it was before the
			//effects of pressing the key come into effect. It is not actually activated until key up

			//If we have another key acting as a lock, we should override its state with this.
			int mask = lockMetaMapLookup(keycode);
			if ( (event.getMetaState() & mask) == mask )
			{
				//Bit inactive, will be activated.
				mActiveVirtualModifiers = mActiveVirtualModifiers | mask;
			}
			else
			{
				//Bit is active, will be deactivated.
				mActiveVirtualModifiers = mActiveVirtualModifiers & ~mask;
			}

			//Always remove corresponding flag from filtered state if present
			mFilteredModifiers = mFilteredModifiers & ~mask;
		}
	}

	// Apply filtered and active modifiers to existing meta.
	// If diferent apply, normalize, and return modified event.
	public static KeyEvent applyModifiers(KeyEvent event)
	{

		//Apply the masks for the individual keys (not common state) to see if changes are necessary
		int baseMeta = event.getMetaState();
		int modifiedMeta =  baseMeta & ~mFilteredModifiers;	//Filtered
		modifiedMeta =  modifiedMeta | mActiveVirtualModifiers;	//Active

		if (modifiedMeta != baseMeta)
		{
			//But never apply to locks, because they are weird and confusing.
			if (LOCK_KEYCODE_TO_META.containsKey( event.getKeyCode() ))
				return event;

			//Modifications needed
			event = new KeyEvent(
					event.getDownTime(),
					event.getEventTime(),
					event.getAction(),
					event.getKeyCode(),
					event.getRepeatCount(),
					applyModifiers(event.getMetaState()),
					event.getDeviceId(),
					event.getScanCode(),
					event.getFlags() | FLAG_INJECTED,
					event.getSource()
			);
		}

		return event;
	}

	//Apply filtered and active modifiers to existing meta
	private static int applyModifiers(int baseMeta)
	{
		//Normalize the filtered meta values so we don't leave any non-key specific ones hanging
		int normalizedFiltered = KeyEvent.normalizeMetaState(mFilteredModifiers);

		//remove filtered, add active virtual
		int modifiedMeta = baseMeta & ~normalizedFiltered;

		modifiedMeta =  modifiedMeta | mActiveVirtualModifiers;	//Active

		//Finally normalize to get the common masks for our new config
		modifiedMeta = KeyEvent.normalizeMetaState(modifiedMeta);

		return modifiedMeta;
	}

	public static KeyEvent handleKeyRemap(KeyEvent event)
	{
		//If both scan code and keycode are present, scancode is mapped to current layout.
		//otherwise it uses whichever is not 0
		//The separate keycode outside of keyEvent is just there for convenience it seems.

		int keycode = event.getKeyCode();
		int scanCode = event.getScanCode();

		HardKey mapping = mRemappedKeycodes.get(keycode);
		if (mapping == null)
		{
			mapping = mRemappedScanCodes.get(scanCode);
		}
		else
		{
			// If we enter this block we know thatwe are mapping a keycode
			// Check if modifier and add to filtered if necessary
			updatFilteredModifiers(keycode, event.getAction());

			//A modfiier will never be mapped as a scan code.
		}

		if (mapping != null)
		{
			if (mapping.getType() == HardKeyType.KEY_CODE)
			{
				updateActiveVirtualModifiers(mapping.keycode, event.getAction());

				event = new KeyEvent(
						event.getDownTime(),
						event.getEventTime(),
						event.getAction(),
						mapping.keycode,
						event.getRepeatCount(),
						applyModifiers( event.getMetaState() ),
						event.getDeviceId(),
						mapping.scancode,
						event.getFlags() | FLAG_INJECTED,
						event.getSource()
				);updateActiveVirtualLocks(mapping.keycode, event.getAction());
			}
			else
			{
				//The config app does not accept scancode input for modifier keys.
				//I'm hoping all the modifiers have universal scan codes, and this won't cause any trouble.
				//tl;dr when mapping to or from a scan code, they will will never be a modifier.
				event = new KeyEvent(
						event.getDownTime(),
						event.getEventTime(),
						event.getAction(),
						0,
						event.getRepeatCount(),
						applyModifiers( event.getMetaState() ),
						event.getDeviceId(),
						mapping.scancode,
						event.getFlags() | FLAG_INJECTED,
						event.getSource()
				);
			}
		}

		return event;
	}
}
