package com.mayulive.swiftkeyexi.util;

import android.content.Context;
import android.view.KeyEvent;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.data.HardKey;
import com.mayulive.swiftkeyexi.main.commons.data.HardKeyType;

/**
 * Created by Roughy on 3/17/2018.
 */

public class KeyUtils
{
	private static final int KEY_CODE_COUNT = 284;
	private static int[] KEY_CODES = new int[KEY_CODE_COUNT];

	static
	{
		for (int i = 0; i < KEY_CODE_COUNT; i++)
		{
			KEY_CODES[i] = i+1;
		}
	}

	public static String getKeyString(Context context, int key, HardKeyType type)
	{
		if (key == -1 || key == 0)
		{
			return context.getResources().getString(R.string.hotkeys_hardware_key_undefined);
		}
		if (type == HardKeyType.KEY_CODE)
		{
			return KeyEvent.keyCodeToString(key)
					.replace("KEYCODE", "")
					.replace("_"," ");
		}
		else
		{
			return type.toString()+": "+Integer.toString(key);
		}
	}

	public static String getKeyString(Context context, HardKey key)
	{
		if (key.keycode > 0)
		{
			return getKeyString(context, key.keycode, HardKeyType.KEY_CODE);
		}
		else
		{
			return getKeyString(context, key.scancode, HardKeyType.SCAN_CODE);
		}
	}

	public static int[] getValidKeycodes()
	{
		return KEY_CODES;
	}


}
