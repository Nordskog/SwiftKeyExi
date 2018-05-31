package com.mayulive.swiftkeyexi.xposed.style;

import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;
import com.mayulive.xposed.classhunter.profiles.Profile;

import java.lang.reflect.Method;


public class StyleClassManager
{

	/////////////////
	//Unknown classes
	/////////////////

	protected static Class OverlayThemeUtil;
	protected static Class drawableLoaderClass;

	/////////////////
	//Methods
	/////////////////

	protected static Method OverlayThemeUtil_getFancyEmojiBackgroundMethod;
	protected static Method drawableLoaderClass_loadEmojiDrawableMethod;



	protected static void loadKnownClasses( PackageTree param )
	{

	}

	protected static void loadUnknownClasses(PackageTree param)
	{
		OverlayThemeUtil = ProfileHelpers.loadProfiledClass(StyleProfiles.get_OVERLAY_THEME_UTIL_PROFILE(), param);
		drawableLoaderClass = ProfileHelpers.loadProfiledClass(StyleProfiles.get_DRAWABLE_LOADER_CLASS_PROFILE(), param);
	}


	protected static void loadMethods()
	{
		if (OverlayThemeUtil != null)
		{
			OverlayThemeUtil_getFancyEmojiBackgroundMethod = ProfileHelpers.findMostSimilar(

					new MethodProfile
							(
									new ClassItem( void.class ),
									new ClassItem(null, "com.touchtype.keyboard.view.fancy.FancyPanelContainer")

							),

					OverlayThemeUtil.getDeclaredMethods(), OverlayThemeUtil);
		}

		if (drawableLoaderClass != null)
		{
			drawableLoaderClass_loadEmojiDrawableMethod = ProfileHelpers.findMostSimilar(

					new MethodProfile
							(
									new ClassItem( int.class ),
									new ClassItem(String.class)

							),

					drawableLoaderClass.getDeclaredMethods(), drawableLoaderClass);
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
		Hooks.logSetRequirementFalseIfNull( Hooks.styleHooks_raisedbg,	 "OverlayThemeUtil_getFancyEmojiBackgroundMethod", OverlayThemeUtil_getFancyEmojiBackgroundMethod);

	}

}
