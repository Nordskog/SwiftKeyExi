package com.mayulive.swiftkeyexi.main.commons.data;

/**
 * Created by Roughy on 3/20/2018.
 */

public class RemappedKey
{
	private HardKey from_key = new HardKey();
	private HardKey to_key = new HardKey();

	public RemappedKey(){};

	public RemappedKey(HardKey from, HardKey to)
	{
		this.from_key = from;
		this.to_key = to;
	}

	///////////////////
	// Stuffy
	//////////////////

	public void set(RemappedKey item)
	{
		this.from_key.set( item.from_key );
		this.to_key.set( item.to_key );
	}

	public RemappedKey clone()
	{
		RemappedKey item = new RemappedKey();
		item.set(this);

		return item;
	}

	//////////////////
	// Getty & Setty
	//////////////////


	public HardKey getFrom_key()
	{
		return from_key;
	}

	public void setFrom_key(HardKey from_key)
	{
		this.from_key = from_key;
	}

	public HardKey getTo_key()
	{
		return to_key;
	}

	public void setTo_key(HardKey to_key)
	{
		this.to_key = to_key;
	}
}
