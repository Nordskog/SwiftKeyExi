package com.mayulive.swiftkeyexi.xposed.system.input;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.AndroidHooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;

import java.io.IOException;
import java.lang.reflect.Method;


public class InputClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(InputClassManager.class);

	/////////////////////////
	//Classes
	/////////////////////////

	protected static Class InputManagerServiceClass;

	/////////////////////////
	//Methods
	/////////////////////////
	protected static Method InputManagerServiceClass_interceptKeyBeforeDispatching = null;
	protected static Method InputManagerServiceClass_injectInputEvent = null;


	public static void loadClasses( ClassLoader loader )
	{
		InputManagerServiceClass = ClassHunter.loadClass("com.android.server.input.InputManagerService", loader);
	}

	public static void loadMethods() throws NoSuchMethodException
	{
		if (InputManagerServiceClass != null)
		{
			InputManagerServiceClass_interceptKeyBeforeDispatching = ProfileHelpers.findFirstMethodByName( InputManagerServiceClass.getDeclaredMethods(), "interceptKeyBeforeDispatching");
			InputManagerServiceClass_injectInputEvent = ProfileHelpers.findFirstMethodByName( InputManagerServiceClass.getDeclaredMethods(), "injectInputEvent");
		}
	}

	public static void doAllTheThings(ClassLoader loader) throws IOException, NoSuchFieldException, NoSuchMethodException
	{

		loadClasses(loader);
		loadMethods();
		updateDependencyState();
	}

	protected static void updateDependencyState()
	{
		Hooks.logSetRequirementFalseIfNull( AndroidHooks.InputManagerHooks_base,	 "InputManagerServiceClass", InputManagerServiceClass);
		Hooks.logSetRequirementFalseIfNull( AndroidHooks.InputManagerHooks_base,	 "InputManagerServiceClass_interceptKeyBeforeDispatching", InputManagerServiceClass_interceptKeyBeforeDispatching);
	}
}
