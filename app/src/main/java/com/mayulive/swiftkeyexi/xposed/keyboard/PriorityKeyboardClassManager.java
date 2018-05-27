package com.mayulive.swiftkeyexi.xposed.keyboard;

import android.content.res.Configuration;
import android.view.inputmethod.InputConnection;

import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.key.KeyProfiles;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.mayulive.xposed.classhunter.Modifiers.ABSTRACT;
import static com.mayulive.xposed.classhunter.Modifiers.ARRAY;
import static com.mayulive.xposed.classhunter.Modifiers.ENUM;
import static com.mayulive.xposed.classhunter.Modifiers.EXACT;
import static com.mayulive.xposed.classhunter.Modifiers.FINAL;
import static com.mayulive.xposed.classhunter.Modifiers.PRIVATE;
import static com.mayulive.xposed.classhunter.Modifiers.PUBLIC;
import static com.mayulive.xposed.classhunter.Modifiers.STATIC;

public class PriorityKeyboardClassManager
{


	public static Class keyboardServiceClass =  null;
	/////////////////////////
	//Methods
	/////////////////////////
	public static Method keyboardService_getCurrentInputConnectionMethod = null;
	///////////////////
	//Objects and instances
	//////////////////
	public static Object keyboardServiceInstance = null;
	public static Class keyboardSizerClass = null;
	public static Class keyboardLoaderClass = null;
	public static Method keyboardLoader_onSharedPreferenceChangedMethod;
	public static Method keyboardLoader_loadMethod = null;
	public static Method keyboardLoader_clearCacheMethod = null;
	protected static Method keyboardService_onEvaluateFullscreenModeMethod = null;
	protected static Method keyboardService_onConfigurationChangedMethod = null;
	protected static Method keyboardSizerClass_sizeKeyboardMethod = null;

	public static void loadKnownClasses(PackageTree param)
	{
		PriorityKeyboardClassManager.keyboardServiceClass = ClassHunter.loadClass("com.touchtype.KeyboardService", param.getClassLoader());
	}

	public static void loadUnknownClasses(PackageTree param)
	{
		PriorityKeyboardClassManager.keyboardSizerClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_KEYBOARD_SIZER_CLASS_PROFILE(), param );

		PriorityKeyboardClassManager.keyboardLoaderClass = ProfileHelpers.loadProfiledClass( KeyProfiles.get_KEYBOARD_LOADER_CLASS_PROFILE(), param );

	}

	public static void loadMethods() throws NoSuchMethodException
	{
		if (PriorityKeyboardClassManager.keyboardServiceClass != null)
		{
			PriorityKeyboardClassManager.keyboardService_getCurrentInputConnectionMethod = PriorityKeyboardClassManager.keyboardServiceClass.getMethod("getCurrentInputConnection", (Class[])null);

			PriorityKeyboardClassManager.keyboardService_onEvaluateFullscreenModeMethod = PriorityKeyboardClassManager.keyboardServiceClass.getMethod("onEvaluateFullscreenMode", (Class[])null);
			PriorityKeyboardClassManager.keyboardService_onConfigurationChangedMethod = PriorityKeyboardClassManager.keyboardServiceClass.getMethod("onConfigurationChanged", ( new Class[]{ Configuration.class }) );
		}



		if (PriorityKeyboardClassManager.keyboardLoaderClass != null)
		{

			PriorityKeyboardClassManager.keyboardLoader_onSharedPreferenceChangedMethod = ProfileHelpers.findFirstMethodByName(PriorityKeyboardClassManager.keyboardLoaderClass.getDeclaredMethods(), "onSharedPreferenceChanged");

			PriorityKeyboardClassManager.keyboardLoader_loadMethod = ProfileHelpers.findMostSimilar(

					new MethodProfile
							(
									PRIVATE | EXACT ,
									new ClassItem("com.touchtype.keyboard" , PUBLIC | ABSTRACT | ARRAY | EXACT ),

									new ClassItem("com.touchtype.telemetry.Breadcrumb" , PUBLIC | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | ARRAY | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | EXACT ),
									new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
									new ClassItem(int[].class),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | EXACT ),
									new ClassItem(boolean.class)
							),

					PriorityKeyboardClassManager.keyboardLoaderClass.getDeclaredMethods(), PriorityKeyboardClassManager.keyboardLoaderClass);

			PriorityKeyboardClassManager.keyboardLoader_clearCacheMethod = ProfileHelpers.findFirstProfileMatch(

					new MethodProfile
							(
									PRIVATE | EXACT ,
									new ClassItem(void.class ),
									new ClassItem[0]

							),

					PriorityKeyboardClassManager.keyboardLoaderClass.getDeclaredMethods(), PriorityKeyboardClassManager.keyboardLoaderClass);
		}





		if (PriorityKeyboardClassManager.keyboardSizerClass != null)
		{

			PriorityKeyboardClassManager.keyboardSizerClass_sizeKeyboardMethod = ProfileHelpers.findMostSimilar(		new MethodProfile
			(
					PUBLIC | STATIC | EXACT ,
					new ClassItem(android.view.View.class),

					new ClassItem(android.content.Context.class),
					new ClassItem(android.view.View.class),
					new ClassItem(int.class),
					new ClassItem(int.class)

			), PriorityKeyboardClassManager.keyboardSizerClass.getDeclaredMethods(), PriorityKeyboardClassManager.keyboardSizerClass );

		}
	}

	public static void loadFields()
	{

	}


	public static void doAllTheThings(PackageTree param) throws IOException, NoSuchFieldException, NoSuchMethodException
	{

		loadKnownClasses(param);
		loadUnknownClasses(param);
		loadMethods();
		loadFields();

		updateDependencyState();
	}


	protected static void updateDependencyState()
	{
		//Fullscreen mode
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_fullscreenMode,	 "keyboardService_onEvaluateFullscreenModeMethod", 	PriorityKeyboardClassManager.keyboardService_onEvaluateFullscreenModeMethod );


		//View created (Overlay)
		Hooks.logSetRequirementFalseIfNull( Hooks.overlayHooks_base,	 "KeyboardServiceClass", 	PriorityKeyboardClassManager.keyboardServiceClass );

		//Base
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "KeyboardServiceClass", 	PriorityKeyboardClassManager.keyboardServiceClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "keyboardService_getCurrentInputConnectionMethod", PriorityKeyboardClassManager.keyboardService_getCurrentInputConnectionMethod);
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "keyboardService_onConfigurationChangedMethod", 	PriorityKeyboardClassManager.keyboardService_onConfigurationChangedMethod );

		Hooks.logSetRequirementFalseIfNull( Hooks.overlayHooks_base,	 "keyboardSizerClass_sizeKeyboardMethod", 	PriorityKeyboardClassManager.keyboardSizerClass_sizeKeyboardMethod );

	}

	public static InputConnection getInputConnection()
	{
		try
		{
			return KeyboardClassManager.currentInputConnection = (InputConnection) keyboardService_getCurrentInputConnectionMethod.invoke(keyboardServiceInstance, (Object[])null);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();

			return null;
		}
	}
}
