package com.mayulive.swiftkeyexi.xposed.keyboard;

import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.key.KeyProfiles;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.Modifiers;
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
import static com.mayulive.xposed.classhunter.Modifiers.ENUM;
import static com.mayulive.xposed.classhunter.Modifiers.EXACT;
import static com.mayulive.xposed.classhunter.Modifiers.FINAL;
import static com.mayulive.xposed.classhunter.Modifiers.PRIVATE;
import static com.mayulive.xposed.classhunter.Modifiers.PUBLIC;
import static com.mayulive.xposed.classhunter.Modifiers.STATIC;
import static com.mayulive.xposed.classhunter.Modifiers.SYNTHETIC;

public class PriorityKeyboardClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(PriorityKeyboardClassManager.class);


	public static Class keyboardServiceClass =  null;
	public static Class FullKeyboardServiceDelegate = null;
	public static Class toolbarFrameClass = null;

	public static Class toolbarOpenButtonOverlayViewClass = null;
	/////////////////////////
	//Methods
	/////////////////////////
	public static Method keyboardService_getCurrentInputConnectionMethod = null;
	public static Method FullKeyboardServiceDelegate_onCreateInputView = null;
	public static Method toolbarOpenButtonOverlayViewClass_createToolbarOpenMethod = null;
	///////////////////
	//Objects and instances
	//////////////////
	public static Object keyboardServiceInstance = null;
	//public static Class keyboardSizerClass = null;
	public static Class keyboardLoaderClass = null;
	public static Method keyboardLoader_onSharedPreferenceChangedMethod;
	public static Method keyboardLoader_loadMethod = null;
	public static Object punctuatorImplInstance = null;
	protected static Method keyboardService_onEvaluateFullscreenModeMethod = null;
	protected static Method keyboardService_onConfigurationChangedMethod = null;
	protected static Method keyboardService_isFullscreenModeMethod = null;
	protected static Method punctuatorImplClass_AddRulesMethod = null;
	protected static Method punctuatorImplClass_ClearRulesMethod = null;
	protected static Class punctuatorImplClass = null;

	protected static Method toolbarFrameClass_inflateMethod = null;

	public static void loadKnownClasses(PackageTree param)
	{
		PriorityKeyboardClassManager.keyboardServiceClass = ClassHunter.loadClass("com.touchtype.KeyboardService", param.getClassLoader());

		PriorityKeyboardClassManager.punctuatorImplClass = ClassHunter.loadClass("com.touchtype_fluency.impl.PunctuatorImpl", param.getClassLoader());

		toolbarFrameClass = ClassHunter.loadClass("com.touchtype.keyboard.toolbar.ToolbarFrame", param.getClassLoader());
	}

	public static void loadUnknownClasses(PackageTree param)
	{


		PriorityKeyboardClassManager.keyboardLoaderClass = ProfileHelpers.loadProfiledClass( KeyProfiles.get_KEYBOARD_LOADER_CLASS_PROFILE(), param );

		toolbarOpenButtonOverlayViewClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_TOOLBAR_OPEN_BUTTON_OVERLAY_CLASS_PROFILE(), param );

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
			// 7.1.6.30+
			FullKeyboardServiceDelegate_onCreateInputView = ProfileHelpers.findFirstProfileMatch( new MethodProfile(
					PRIVATE | EXACT,
					new ClassItem(View.class)
			), FullKeyboardServiceDelegate.getDeclaredMethods(), FullKeyboardServiceDelegate );

			if ( FullKeyboardServiceDelegate_onCreateInputView == null )
			{
				Log.i(LOGTAG, "FullKeyboardServiceDelegate_onCreateInputView 7.1.6.30+ not found, using old profile");
				// Older. Remove next update.
				FullKeyboardServiceDelegate_onCreateInputView = ProfileHelpers.findFirstProfileMatch( new MethodProfile(
						FINAL | EXACT,
						new ClassItem(View.class)
				), FullKeyboardServiceDelegate.getDeclaredMethods(), FullKeyboardServiceDelegate );
			}



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


		if (PriorityKeyboardClassManager.keyboardLoaderClass != null)
		{

			PriorityKeyboardClassManager.keyboardLoader_onSharedPreferenceChangedMethod = ProfileHelpers.findFirstMethodByName(PriorityKeyboardClassManager.keyboardLoaderClass.getDeclaredMethods(), "onSharedPreferenceChanged");

			PriorityKeyboardClassManager.keyboardLoader_loadMethod = ProfileHelpers.findMostSimilar(

					new MethodProfile
							(
									PRIVATE | EXACT ,
									new ClassItem("" , PUBLIC | ABSTRACT | ARRAY | EXACT ),

									new ClassItem("" , PUBLIC | EXACT ),
									new ClassItem("" , PUBLIC | FINAL | ARRAY | EXACT ),
									new ClassItem("" , PUBLIC | STATIC | EXACT ),
									new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
									new ClassItem(int[].class),
									new ClassItem("" , PUBLIC | FINAL | EXACT )

							),

					PriorityKeyboardClassManager.keyboardLoaderClass.getDeclaredMethods(), PriorityKeyboardClassManager.keyboardLoaderClass);
		}

		if (toolbarFrameClass != null)
		{
			toolbarFrameClass_inflateMethod = ProfileHelpers.findMostSimilar(

					new MethodProfile
							(
									PRIVATE | Modifiers.EXACT,
									new ClassItem( void.class ),
									new ClassItem( boolean.class )
							),

					toolbarFrameClass.getDeclaredMethods(), toolbarFrameClass);

		}

		if (toolbarOpenButtonOverlayViewClass != null)
		{
			toolbarOpenButtonOverlayViewClass_createToolbarOpenMethod = ProfileHelpers.findMostSimilar(

					new MethodProfile
							(
									PUBLIC | FINAL | SYNTHETIC | EXACT ,
									new ClassItem(void.class),

									new ClassItem(java.lang.Object.class),
									new ClassItem(int.class)

							),

					PriorityKeyboardClassManager.toolbarOpenButtonOverlayViewClass.getDeclaredMethods(), PriorityKeyboardClassManager.toolbarOpenButtonOverlayViewClass);
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


		//Toolbar button
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_toolbarButton,	 "toolbarFrameClass_inflateMethod", 	PriorityKeyboardClassManager.toolbarFrameClass_inflateMethod );

		//Base
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "KeyboardServiceClass", 	PriorityKeyboardClassManager.keyboardServiceClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "keyboardService_getCurrentInputConnectionMethod", PriorityKeyboardClassManager.keyboardService_getCurrentInputConnectionMethod);
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "keyboardService_onConfigurationChangedMethod", 	PriorityKeyboardClassManager.keyboardService_onConfigurationChangedMethod );

		Hooks.logSetRequirementFalseIfNull( Hooks.overlayHooks_base,	 "FullKeyboardServiceDelegate_onCreateInputView", 	PriorityKeyboardClassManager.FullKeyboardServiceDelegate_onCreateInputView);


		//Punctuation space
		Hooks.logSetRequirementFalseIfNull(Hooks.baseHooks_punctuationSpace, "punctuatorImplClass_AddRulesMethod", PriorityKeyboardClassManager.punctuatorImplClass_AddRulesMethod);
		Hooks.logSetRequirementFalseIfNull(Hooks.baseHooks_punctuationSpace, "punctuatorImplClass_ClearRulesMethod", PriorityKeyboardClassManager.punctuatorImplClass_ClearRulesMethod);

		//Hide predictions
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_hidePredictions, "toolbarOpenButtonOverlayViewClass_createToolbarOpenMethod", toolbarOpenButtonOverlayViewClass_createToolbarOpenMethod );


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
