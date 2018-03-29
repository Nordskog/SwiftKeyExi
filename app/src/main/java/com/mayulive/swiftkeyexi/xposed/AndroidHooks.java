package com.mayulive.swiftkeyexi.xposed;


import com.mayulive.swiftkeyexi.xposed.system.input.InputHooks;

public class AndroidHooks
{
	public static final String SYSTEM_SERVER_PACKAGE = "android";

	public static HookCategory InputManagerHooks_base = new HookCategory("InputManagerHooks Base");

	public static HookCategory SystemHooks_base = new HookCategory("SystemHooks Base", InputManagerHooks_base);

	public static void hookAll(ClassLoader loader)
	{
		com.mayulive.swiftkeyexi.xposed.system.system.SystemHooks.HookAll(loader);
		InputHooks.HookAll(loader);
	}
}
