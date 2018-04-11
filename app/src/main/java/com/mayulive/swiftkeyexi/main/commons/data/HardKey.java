package com.mayulive.swiftkeyexi.main.commons.data;

/**
 * Created by Roughy on 3/24/2018.
 */

public class HardKey
{
	public int keycode = -1;
	public int scancode = -1;

	HardKey(){};

	public HardKey(int keycode, int scancode)
	{
		this.keycode = keycode;
		this.scancode = scancode;
	}

	public HardKey(int code, HardKeyType type)
	{
		this.setForType(code,type);
	}

	public void set(HardKey other)
	{
		this.keycode = other.keycode;
		this.scancode = other.scancode;
	}

	public HardKeyType getType()
	{
		//If keycode is defined, keycode.
		//Otherwise scan code.


		if ( keycode > 0)
			return HardKeyType.KEY_CODE;
		else if (scancode > 0)
			return HardKeyType.SCAN_CODE;
		else
			return HardKeyType.UNDEFINED;
	}

	public int getCodeForType()
	{
		if (keycode < 0)
			return scancode;
		else
			return keycode;
	}

	public void setForType(int code, HardKeyType type)
	{
		if (type == HardKeyType.KEY_CODE)
		{
			keycode = code;
			scancode = 0;
		}
		else
		{
			scancode = code;
			keycode = 0;
		}

	}
}
