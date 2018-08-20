package com.mayulive.swiftkeyexi.xposed.key;

import android.graphics.RectF;
import android.util.Log;
import android.view.inputmethod.InputConnection;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;
import com.mayulive.swiftkeyexi.xposed.selection.SelectionMethods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Roughy on 2/14/2017.
 */

public class KeyCommons
{

	private static String LOGTAG = ExiModule.getLogTag(KeyCommons.class);

	///////////////////
	//Key down stuff
	///////////////////


	private static ArrayList<OnKeyDownListener> mKeyDownListeners = new ArrayList<OnKeyDownListener>();


	//Unlike hitboxes, which will overwrite any existing entries, key definitions will stay around
	//until we clear them. Use system has to avoid blocking garbage collection.
	// In the case of android, the system identifier for an object is (last I checked) just the pointer address.
	private static Map<Integer, KeyDefinition> mKeyDefinitions = new HashMap<>();

	//Since we cannot reliably detect when a layout is invalidated, and swiftkey will often create a key in a layout
	//but display the old one instead of the new one, we will keep a buffer of... 150? keys that we keep references to.
	//Remove from mKeyDefinitions as they expire. Some chinese keyboards do weirds things that may cause problems,
	//but they're broken anyway.
	private static Map<String, LinkedList< Integer> > mLayoutKeyStack = new HashMap<>();

	//Sometimes we potentially don't want a key to fire on down.
	//We can later decide to fire these.
	private static ArrayList<DelayedKey> mDelayedKeys = new ArrayList<>();

	public static String sLastSymbolDefined = null;
	public static String sSymboledDefinedOnLastKeyLoop = null;


	protected static KeyDefinition mLastKeyDefined = null;

	static String mLastTag = null;			//Last tag defined

	////////////
	//Misc
	////////////

	//Fun fact: You cannot obtain multiple capture groups when using quantifiers, and java regex does not support recursion. Result: This nonsense.
	//Matches: ag { Content: {Bottom: com.touchtype.keyboard.e.f.j@ec9390a, Top: {Text: , Label: }}, Area: RectF(0.0025000572, 0.42631578, 0.14750004, 0.6263158) }
	//Specifically, the 4 numbers that make up RectF.  The capture groups are just pasted 4 times.
	private static Pattern AREA_RECTF_STRING_PATTERN = Pattern.compile("Area: RectF\\((?:(\\d+(?:\\.\\d+)?)(?:, )?)(?:(\\d+(?:\\.\\d+)?)(?:, )?)(?:(\\d+(?:\\.\\d+)?)(?:, )?)(?:(\\d+(?:\\.\\d+)?)(?:, )?)\\)");

	//Takes a key and uses its toString method to get the area.
	//Trust me, it's the least-horrible-but-also-stable method.
	public static RectF getKeyArea(Object key)
	{
		//Expected output
		//ag { Content: {Bottom: com.touchtype.keyboard.e.f.j@ec9390a, Top: {Text: , Label: }}, Area: RectF(0.0025000572, 0.42631578, 0.14750004, 0.6263158) }
		float[] values = new float[4];

		try
		{
			Matcher matcher = AREA_RECTF_STRING_PATTERN.matcher(key.toString());
			if (matcher.find())
			{
				for (int i = 0; i < 4; i++)
				values[i] = Float.valueOf(matcher.group(i+1));
			}
		}
		catch (NumberFormatException ex)
		{
			ex.printStackTrace();
		}
		catch (NullPointerException ex)	//ToString calls toString on null objects
		{
			return null;
		}

		return new RectF(values[0], values[1], values[2], values[3] );
	}

	/////////////
	//Key down
	/////////////

	private static LinkedList<Integer> getKeyStackForCurrentLayout()
	{
		LinkedList<Integer> keyStack = mLayoutKeyStack.get( KeyboardMethods.getCurrentLayoutName() );
		if (keyStack == null)
		{
			keyStack = new LinkedList<>();
			mLayoutKeyStack.put( KeyboardMethods.getCurrentLayoutName(), keyStack );

		}

		return keyStack;
	}

	private static void updateKeyStack(KeyDefinition key, Object newSwiftkeyKey)
	{
		Integer pointer = System.identityHashCode(newSwiftkeyKey);

		//Sometimes we are fed the same object multiple times, skip.
		if ( mKeyDefinitions.containsKey( pointer ) )
			return;

		LinkedList<Integer> keyStack = getKeyStackForCurrentLayout();

		//Prune to 150, starting with oldest.
		keyStack.add(pointer);
		if (keyStack.size() > 150)
		{
			Integer removedPointer = keyStack.removeFirst();
			mKeyDefinitions.remove(removedPointer);

		}
	}

	public static void addKeyDefinition(Object swiftkeyKey, KeyDefinition key)
	{
		updateKeyStack(key, swiftkeyKey);
		mKeyDefinitions.put( System.identityHashCode(swiftkeyKey), key);

	}

	public static void removeKeyDefinition(Object swiftkeyObject)
	{
		mKeyDefinitions.remove( System.identityHashCode(swiftkeyObject) );
	}

	public static KeyDefinition getKeyDefinition(Object swiftkeyKey)
	{
		return mKeyDefinitions.get( System.identityHashCode(swiftkeyKey) );
	}

	protected static void callKeyDownListeners(KeyDefinition key)
	{
		for (OnKeyDownListener listener : mKeyDownListeners)
		{
			listener.onKeyDown(key);
		}
	}

	public interface OnKeyDownListener
	{
		void onKeyDown(KeyDefinition key);
	}

	public static void addOnKeyDownListener(OnKeyDownListener listener)
	{
		mKeyDownListeners.add(listener);
	}

	public static void removeOnKeyDownListener(OnKeyDownListener listener)
	{
		mKeyDownListeners.remove(listener);
	}

	//////////////////
	// Delay key stuff
	/////////////////

	protected static boolean mDelayNextKey = false;
	protected static long mDelayNextKey_RequestTime = 0;

	public static void addDelayedKey( DelayedKey key)
	{
		mDelayedKeys.add(key);
	}
	
	public static void processDelayedKeys()
	{
		for (DelayedKey key : mDelayedKeys)
		{
			try
			{
				key.execute();
			}
			catch ( Exception ex)
			{
				Log.e(LOGTAG, "Failed to executed delayed key");
				ex.printStackTrace();
			}

		}

		mDelayedKeys.clear();
	}

	public static void clearDelayedKeys()
	{
		mDelayedKeys.clear();
	}

	public static void requestDelayNextKey()
	{
		mDelayNextKey = true;
		mDelayNextKey_RequestTime = System.currentTimeMillis();
	}

	//////////////////
	//Cancel tap stuff
	//////////////////


	protected static boolean mCancelNextKey = false;
	protected static long mCancelNextKey_RequestTime = 0;

	protected static boolean mCancelAllKeys = false;

	public static void requestCancelNextKey()
	{
		mCancelNextKey = true;
		mCancelNextKey_RequestTime = System.currentTimeMillis();
	}

	public static void setCancelAllKeys(boolean cancel)
	{
		mCancelAllKeys = cancel;
	}


	private static ArrayList<OnInputEventListener> mInputEventListeners = new ArrayList<OnInputEventListener>();


	public interface OnInputEventListener
	{
		/**
		 * Return true to cancel input
		 */
		boolean onInputEvent();
	}

	public static void addOnInputEventListener(OnInputEventListener listener)
	{
		mInputEventListeners.add(listener);
	}

	public static void removeOnInputEventListener(OnInputEventListener listener)
	{
		mInputEventListeners.remove(listener);
	}

	protected static boolean callInputEventListeners()
	{
		boolean requestCancel = false;
		for (OnInputEventListener listener : mInputEventListeners)
		{
			requestCancel = listener.onInputEvent() || requestCancel;
		}

		return requestCancel;
	}


	///////////////////
	//Actions
	///////////////////

	public static boolean PerformTextAction(InputConnection connection, KeyboardInteraction.TextAction action)
	{
		if (action != null && action != KeyboardInteraction.TextAction.DEFAULT)
		{
			//Log.e("###", "Performing action: "+action.toString());

			int menuAction = 0;

			switch(action)
			{
				case UNDO:
				{
					menuAction = android.R.id.undo;
					break;
				}

				case REDO:
				{
					menuAction = android.R.id.redo;
					break;
				}

				case COPY:
				{
					menuAction = android.R.id.copy;
					break;
				}
				case CUT:
				{
					menuAction = android.R.id.cut;
					break;
				}
				case PASTE:
				{
					menuAction = android.R.id.paste;
					break;
				}
				case SELECT_ALL:
				{
					menuAction = android.R.id.selectAll;
					break;
				}
				case GO_TO_END:
				{
					/*
					//KEYCODE_MOVE_END only moves to the end of the line. We usually want to move to the end of the text, which
					//can be accomplished by selecting all before triggering the key.
					connection.performContextMenuAction(android.R.id.selectAll);
					connection.sendKeyEvent( new KeyEvent( KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MOVE_END ));
					connection.sendKeyEvent( new KeyEvent( KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MOVE_END ));
					*/

					SelectionMethods.moveCursorToEnd();

					return true;
				}
				case GO_TO_START:
				{
					SelectionMethods.moveCursorToStart();

					return true;
				}
				default:
					break;
			}

			connection.performContextMenuAction(menuAction);

			return true;
		}

		return false;
	}

	//////////////////////
	//Normal key up stuff
	//////////////////////

	//protected static Map< String, Pair<String, RectF> > mKeyHitboxes = new HashMap<>();

	private static Map<String, HitboxMap> mHitboxMap = new HashMap<String,HitboxMap>();
	private static HitboxMap mCurrentHitboxMap = new HitboxMap();	//Initialize incase we never set it


	public static class HitboxMap extends HashMap<String, KeyDefinition>
	{
		public HitboxMap()
		{
			super();
		}
	}

	public static void setCurrentHitboxMap(String layoutName)
	{
		HitboxMap map = mHitboxMap.get(layoutName);
		if (map == null)
		{
			map = new HitboxMap();
			mHitboxMap.put(KeyboardMethods.getCurrentLayoutName(), map);
		}

		mCurrentHitboxMap = map;
	}

	public static HitboxMap getHitboxMap()
	{
		return mCurrentHitboxMap;
	}

	public static String getKeyAtLocation(float posX, float posY)
	{
		HitboxMap map = getHitboxMap();

		for (KeyDefinition entry : map.values())
		{
			if (entry.hitbox.contains(posX, posY))
			{
				return entry.getContent();
			}
		}

		return null;
	}

	public static KeyDefinition getHitboxAtLocation(float posX, float posY)
	{
		//Log.e("###", "Checking input X: "+posX+", Y: "+posY+", count: "+getHitboxMap().size());

		HitboxMap map = getHitboxMap();

		for (KeyDefinition entry : map.values())
		{
			//Log.e("###", "Box: X: "+entry.hitbox+", key: "+entry.getContent());
			if (entry.hitbox.contains(posX, posY))
			{
				return entry;
			}

		}


		return null;
	}

	public static class DelayedKey
	{
		public Object[] arguments;
		public Object thiz;
		public Method method;
		public long createTime;
		public long timeout;

		public DelayedKey(Object[] arguments, Object thiz, Method method, long timeout)
		{
			this.arguments = arguments;
			this.thiz = thiz;
			this.method = method;
			this.timeout = timeout;
		}

		public void execute() throws InvocationTargetException, IllegalAccessException
		{
			long currentTime = System.currentTimeMillis();
			if ( createTime + timeout > currentTime )
				return;

			method.invoke(thiz, arguments);
		}

	}

}
