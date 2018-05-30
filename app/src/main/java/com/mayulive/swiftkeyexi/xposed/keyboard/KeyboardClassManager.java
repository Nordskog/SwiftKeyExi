package com.mayulive.swiftkeyexi.xposed.keyboard;

/**
 * Created by Roughy on 2/15/2017.
 */

import android.view.inputmethod.InputConnection;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.mayulive.xposed.classhunter.Modifiers.*;


public class KeyboardClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(KeyboardClassManager.class);

	/////////////////
	//Known classes
	/////////////////

	public static Class layoutClass = null;
	public static Class breadcrumbClass = null;

	/////////////////
	//unknown classes
	/////////////////

	public static Class KeyHeightClass = null;


	protected static Method keyHeightClass_getKeyHeightMethod = null;

	////////////////////
	//Fields
	////////////////////
	public static Field keyboardLoaderClass_layoutField = null;


	public static InputConnection currentInputConnection = null;


	public static void loadKnownClasses(PackageTree param)
	{

		layoutClass = ClassHunter.loadClass("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout", param.getClassLoader());

	}

	public static void loadUnknownClasses(PackageTree param)
	{
		breadcrumbClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_BREADCRUMB_CLASS_PROFILE(), param );

		KeyHeightClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_KEY_HEIGHT_CLASS_PROFILE(), param );
	}


	public static void loadMethods() throws NoSuchMethodException
	{

		if (KeyHeightClass != null)
		{
			keyHeightClass_getKeyHeightMethod = ProfileHelpers.findMostSimilar(

					new MethodProfile
							(
									PUBLIC | EXACT ,
									new ClassItem(int.class),

									new ClassItem(int.class),
									new ClassItem(boolean.class),
									new ClassItem(int.class),
									new ClassItem(boolean.class)
							),

					KeyHeightClass.getDeclaredMethods(), KeyHeightClass);
		}

	}

	public static void loadFields()
	{
		keyboardLoaderClass_layoutField = ProfileHelpers.findFirstDeclaredFieldWithType( layoutClass, PriorityKeyboardClassManager.keyboardLoaderClass);
		keyboardLoaderClass_layoutField.setAccessible(true);
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
		//Base
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "breadcrumbClass", 	breadcrumbClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "keyboardLoaderClass", 	PriorityKeyboardClassManager.keyboardLoaderClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "keyboardLoader_clearCacheMethod", 	PriorityKeyboardClassManager.keyboardLoader_clearCacheMethod );


		//Popup
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_invalidateLayout,	 "keyboardLoader_onSharedPreferenceChangedMethod", 	PriorityKeyboardClassManager.keyboardLoader_onSharedPreferenceChangedMethod );

		//Hitbox
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "layoutClass", 	layoutClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "keyboardLoader_loadMethod", 	PriorityKeyboardClassManager.keyboardLoader_loadMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "keyboardLoaderClass_layoutField", 	keyboardLoaderClass_layoutField );

		//Keyboard size
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_keyHeight,	 "keyHeightClass_getKeyHeightMethod", 	keyHeightClass_getKeyHeightMethod );

	}
}
