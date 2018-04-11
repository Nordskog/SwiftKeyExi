package com.mayulive.swiftkeyexi.xposed.hardwarekeys;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardClassManager;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import java.io.IOException;
import java.lang.reflect.Method;


public class HardwareKeyClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(HardwareKeyClassManager.class);

	/////////////////////////
	//Methods
	/////////////////////////
	protected static Method keyboardServiceClass_keyDownMethod = null;
	protected static Method keyboardServiceClass_keyUpMethod = null;


	public static void loadMethods() throws NoSuchMethodException
	{
		keyboardServiceClass_keyDownMethod = ProfileHelpers.findFirstMethodByName( KeyboardClassManager.keyboardServiceClass.getDeclaredMethods(), "onKeyDown");
		keyboardServiceClass_keyUpMethod = ProfileHelpers.findFirstMethodByName( KeyboardClassManager.keyboardServiceClass.getDeclaredMethods(), "onKeyUp");
	}

	public static void doAllTheThings(PackageTree param) throws IOException, NoSuchFieldException, NoSuchMethodException
	{

		loadMethods();
		updateDependencyState();
	}

	protected static void updateDependencyState()
	{
		Hooks.logSetRequirementFalseIfNull( Hooks.hardwareKeys_base,	 "keyboardServiceClass_keyDownMethod", keyboardServiceClass_keyDownMethod);
		Hooks.logSetRequirementFalseIfNull( Hooks.hardwareKeys_base,	 "keyboardServiceClass_keyUpMethod", keyboardServiceClass_keyUpMethod);
	}
}
