package com.mayulive.swiftkeyexi.xposed.style;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.Modifiers;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;
import com.mayulive.xposed.classhunter.profiles.Profile;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class StyleClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(StyleClassManager.class);

	/////////////////
	//Unknown classes
	/////////////////

	protected static Class drawableLoaderClass;


	/////////////////
	//Methods
	/////////////////

	protected static Method drawableLoaderClass_loadEmojiDrawableMethod;


	protected static void loadKnownClasses( PackageTree param )
	{

	}

	protected static void loadUnknownClasses(PackageTree param)
	{

		drawableLoaderClass = ProfileHelpers.loadProfiledClass(StyleProfiles.get_DRAWABLE_LOADER_CLASS_PROFILE(), param);

	}


	protected static void loadMethods()
	{

		if (drawableLoaderClass != null)
		{
			drawableLoaderClass_loadEmojiDrawableMethod = ProfileHelpers.findAllMethodsWithReturnType( Integer.class, drawableLoaderClass.getDeclaredMethods() ).get(0);
		}
	}

	protected static void doAllTheThings(PackageTree param)
	{
		loadKnownClasses(param);
		loadUnknownClasses(param);
		loadMethods();

		updateDependencyState();
	}

	protected static void updateDependencyState()
	{
		Hooks.logSetRequirementFalseIfNull( Hooks.styleHooks_darklight,	 "drawableLoaderClass_loadEmojiDrawableMethod", drawableLoaderClass_loadEmojiDrawableMethod);
	}

}
