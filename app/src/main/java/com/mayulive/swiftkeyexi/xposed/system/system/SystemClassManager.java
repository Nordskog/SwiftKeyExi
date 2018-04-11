package com.mayulive.swiftkeyexi.xposed.system.system;

import android.content.Context;
import android.os.Handler;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.AndroidHooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;

import java.io.IOException;
import java.lang.reflect.Constructor;


public class SystemClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(SystemClassManager.class);

	/////////////////////////
	//Classes
	/////////////////////////

	protected static Class ActivityManagerClass;

	/////////////////////////
	//Methods
	/////////////////////////

	protected static Constructor ActivityManagerClass_Constructor;

	public static void loadClasses( ClassLoader loader )
	{
		ActivityManagerClass = ClassHunter.loadClass("android.app.ActivityManager", loader);
	}

	public static void loadMethods() throws NoSuchMethodException
	{
		if (ActivityManagerClass != null)
		{
			ActivityManagerClass_Constructor = ProfileHelpers.findFirstProfileMatch(
					new ConstructorProfile(
							new ClassItem(Context.class),
							new ClassItem(Handler.class)
					),
					ActivityManagerClass.getDeclaredConstructors(), ActivityManagerClass
			);
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
		Hooks.logSetRequirementFalseIfNull( AndroidHooks.SystemHooks_base,	 "ActivityManagerClass", ActivityManagerClass);
		Hooks.logSetRequirementFalseIfNull( AndroidHooks.SystemHooks_base,	 "getDeclaredConstructors", ActivityManagerClass_Constructor);
	}


}
