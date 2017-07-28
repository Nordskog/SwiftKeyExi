package com.mayulive.swiftkeyexi.util;

/**
 * Created by Roughy on 5/19/2017.
 */

public class ByteUtils
{
	public static String bytesToHex(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes)
		{
			sb.append(String.format("%02X ", b));
		}

		return sb.toString();
	}
}
