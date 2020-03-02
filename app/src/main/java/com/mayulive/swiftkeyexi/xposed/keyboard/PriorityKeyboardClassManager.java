package com.mayulive.swiftkeyexi.xposed.keyboard;

import android.content.res.Configuration;
import android.view.inputmethod.InputConnection;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.key.KeyProfiles;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.mayulive.xposed.classhunter.Modifiers.ABSTRACT;
import static com.mayulive.xposed.classhunter.Modifiers.ARRAY;
import static com.mayulive.xposed.classhunter.Modifiers.BRIDGE;
import static com.mayulive.xposed.classhunter.Modifiers.EXACT;
import static com.mayulive.xposed.classhunter.Modifiers.FINAL;
import static com.mayulive.xposed.classhunter.Modifiers.INTERFACE;
import static com.mayulive.xposed.classhunter.Modifiers.PUBLIC;
import static com.mayulive.xposed.classhunter.Modifiers.SYNTHETIC;
import static com.mayulive.xposed.classhunter.Modifiers.VARARGS;

public class PriorityKeyboardClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(PriorityKeyboardClassManager.class);


	public static Class keyboardServiceClass =  null;
	public static Class FullKeyboardServiceDelegate = null;
	/////////////////////////
	//Methods
	/////////////////////////
	public static Method keyboardService_getCurrentInputConnectionMethod = null;
	public static Method FullKeyboardServiceDelegate_onCreateInputView = null;
	///////////////////
	//Objects and instances
	//////////////////

	public static Object keyboardServiceInstance = null;

	public static Class keyboardLoaderClass = null;
	public static Method keyboardLoader_loadMethod = null;

	public static Class keyboardLoaderPreferenceClass = null;
	public static Method keyboardLoaderPreference_onSharedPreferenceChangedMethod;


	public static Object punctuatorImplInstance = null;

	protected static Method keyboardService_onEvaluateFullscreenModeMethod = null;
	protected static Method keyboardService_onConfigurationChangedMethod = null;
	protected static Method keyboardService_isFullscreenModeMethod = null;
	protected static Method punctuatorImplClass_AddRulesMethod = null;
	protected static Method punctuatorImplClass_ClearRulesMethod = null;
	protected static Class punctuatorImplClass = null;

	public static void loadKnownClasses(PackageTree param)
	{
		PriorityKeyboardClassManager.keyboardServiceClass = ClassHunter.loadClass("com.touchtype.KeyboardService", param.getClassLoader());

		PriorityKeyboardClassManager.punctuatorImplClass = ClassHunter.loadClass("com.touchtype_fluency.impl.PunctuatorImpl", param.getClassLoader());
	}

	public static void loadUnknownClasses(PackageTree param)
	{

		PriorityKeyboardClassManager.keyboardLoaderClass = ProfileHelpers.loadProfiledClass( KeyProfiles.get_KEYBOARD_LOADER_CLASS_PROFILE(), param );
		PriorityKeyboardClassManager.keyboardLoaderPreferenceClass = ProfileHelpers.loadProfiledClass( KeyProfiles.get_KEYBOARD_LOADER_PREFERENCE_CLASS_PROFILE(), param );

		PriorityKeyboardClassManager.FullKeyboardServiceDelegate = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_FULL_KEYBOARD_SERVICE_DELEGATE_CLASS_PROFILE(), param );
	}

	public static void loadMethods() throws NoSuchMethodException
	{
		if (PriorityKeyboardClassManager.keyboardServiceClass != null)
		{
			PriorityKeyboardClassManager.keyboardService_getCurrentInputConnectionMethod = PriorityKeyboardClassManager.keyboardServiceClass.getMethod("getCurrentInputConnection", (Class[])null);

			PriorityKeyboardClassManager.keyboardService_onEvaluateFullscreenModeMethod = PriorityKeyboardClassManager.keyboardServiceClass.getMethod("onEvaluateFullscreenMode", (Class[])null);
			PriorityKeyboardClassManager.keyboardService_isFullscreenModeMethod = PriorityKeyboardClassManager.keyboardServiceClass.getMethod("isFullscreenMode", (Class[])null);

			PriorityKeyboardClassManager.keyboardService_onConfigurationChangedMethod = PriorityKeyboardClassManager.keyboardServiceClass.getMethod("onConfigurationChanged", ( new Class[]{ Configuration.class }) );

		}

		if (FullKeyboardServiceDelegate != null)
		{
			FullKeyboardServiceDelegate_onCreateInputView = ProfileHelpers.findMostSimilar( new MethodProfile(
					PUBLIC | FINAL | VARARGS,
					new ClassItem(void.class),

					new ClassItem("android.view.View" , PUBLIC | ARRAY | EXACT )
			), FullKeyboardServiceDelegate.getDeclaredMethods(), FullKeyboardServiceDelegate );
		}

		if (PriorityKeyboardClassManager.punctuatorImplClass != null)
		{
			PriorityKeyboardClassManager.punctuatorImplClass_AddRulesMethod = ProfileHelpers.findFirstProfileMatch(

					new MethodProfile
							(
									PUBLIC,
									new ClassItem(void.class),

									new ClassItem(InputStream.class )
							),

					PriorityKeyboardClassManager.punctuatorImplClass.getDeclaredMethods(), PriorityKeyboardClassManager.punctuatorImplClass);

			PriorityKeyboardClassManager.punctuatorImplClass_ClearRulesMethod = ProfileHelpers.findFirstMethodByName(PriorityKeyboardClassManager.punctuatorImplClass.getDeclaredMethods(), "resetRules");


		}

		if ( keyboardLoaderPreferenceClass != null )
		{
			PriorityKeyboardClassManager.keyboardLoaderPreference_onSharedPreferenceChangedMethod = ProfileHelpers.findFirstMethodByName(PriorityKeyboardClassManager.keyboardLoaderPreferenceClass.getDeclaredMethods(), "onSharedPreferenceChanged");
		}

		if (PriorityKeyboardClassManager.keyboardLoaderClass != null)
		{

			{
				MethodProfile profile = new MethodProfile
				(
						PUBLIC | FINAL | EXACT ,
						new ClassItem(void.class),

						new ClassItem("" , PUBLIC | EXACT ),
						new ClassItem(boolean.class),
						new ClassItem(int.class),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | FINAL | EXACT )

				);

				PriorityKeyboardClassManager.keyboardLoader_loadMethod = ProfileHelpers.findMostSimilar(
						profile, PriorityKeyboardClassManager.keyboardLoaderClass.getDeclaredMethods(), PriorityKeyboardClassManager.keyboardLoaderClass);

				DebugTools.logIfMethodProfileMismatch(  keyboardLoader_loadMethod, keyboardLoaderClass, profile, "keyboardLoader_loadMethod");
			}


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
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_fullscreenMode,	 "keyboardService_keyboardService_isFullscreenModeMethod", 	PriorityKeyboardClassManager.keyboardService_isFullscreenModeMethod );


		//View created (Overlay)
		Hooks.logSetRequirementFalseIfNull( Hooks.overlayHooks_base,	 "KeyboardServiceClass", 	PriorityKeyboardClassManager.keyboardServiceClass );

		//Base
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "KeyboardServiceClass", 	PriorityKeyboardClassManager.keyboardServiceClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "keyboardService_getCurrentInputConnectionMethod", PriorityKeyboardClassManager.keyboardService_getCurrentInputConnectionMethod);
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "keyboardService_onConfigurationChangedMethod", 	PriorityKeyboardClassManager.keyboardService_onConfigurationChangedMethod );

		Hooks.logSetRequirementFalseIfNull( Hooks.overlayHooks_base,	 "FullKeyboardServiceDelegate_onCreateInputView", 	PriorityKeyboardClassManager.FullKeyboardServiceDelegate_onCreateInputView);


		//Punctuation space
		Hooks.logSetRequirementFalseIfNull(Hooks.baseHooks_punctuationSpace, "punctuatorImplClass_AddRulesMethod", PriorityKeyboardClassManager.punctuatorImplClass_AddRulesMethod);
		Hooks.logSetRequirementFalseIfNull(Hooks.baseHooks_punctuationSpace, "punctuatorImplClass_ClearRulesMethod", PriorityKeyboardClassManager.punctuatorImplClass_ClearRulesMethod);

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
