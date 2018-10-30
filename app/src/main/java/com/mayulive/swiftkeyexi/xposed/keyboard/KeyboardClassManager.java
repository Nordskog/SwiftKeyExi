package com.mayulive.swiftkeyexi.xposed.keyboard;

/**
 * Created by Roughy on 2/15/2017.
 */

import android.util.Log;
import android.view.inputmethod.InputConnection;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.Modifiers;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class KeyboardClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(KeyboardClassManager.class);

	/////////////////
	//Known classes
	/////////////////

	public static Class layoutClass = null;

	/////////////////
	//unknown classes
	/////////////////

	public static Class KeyHeightClass = null;

	public static Class incogControllerClass;


	protected static Method incogControllerClass_ChangeIncogStateMethod;

	protected static List<Method> keyHeightClass_getKeyHeightMethods = new ArrayList<Method>();

	////////////////////
	//Fields
	////////////////////
	public static Field keyboardLoaderClass_layoutField = null;

	public static InputConnection currentInputConnection = null;

	public static Field incogControllerClass_staticInstanceField;


	public static void loadKnownClasses(PackageTree param)
	{

		layoutClass = ClassHunter.loadClass("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout", param.getClassLoader());

	}

	public static void loadUnknownClasses(PackageTree param)
	{
		KeyHeightClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_KEY_HEIGHT_CLASS_PROFILE(), param );

		incogControllerClass =  ProfileHelpers.loadProfiledClass( KeyboardProfiles._get_INCOG_CONTROL_CLASS_PROFILE(), param );
	}


	public static void loadMethods() throws NoSuchMethodException
	{

		if (KeyHeightClass != null)
		{
			//Profile used to have multiple float, int, and bool inputs, but has been simplified.
			//There are now several methods with the same signature returning int. The first, and only complicated one, decides key height.
			//The other two will increase bar height (toolbars).
			keyHeightClass_getKeyHeightMethods = ProfileHelpers.findAllMethodsWithReturnType( int.class, KeyHeightClass.getDeclaredMethods() );
			if (!keyHeightClass_getKeyHeightMethods.isEmpty())
			{
				Method firstMethod = keyHeightClass_getKeyHeightMethods.get(0);
				keyHeightClass_getKeyHeightMethods = new ArrayList<Method>();
				keyHeightClass_getKeyHeightMethods.add(firstMethod);
			}
		}

		if (incogControllerClass != null)
		{
			incogControllerClass_ChangeIncogStateMethod = ProfileHelpers.findMostSimilar( new MethodProfile(
					Modifiers.STATIC | Modifiers.FINAL,
					new ClassItem(void.class),
					new ClassItem(int.class)
			), incogControllerClass.getDeclaredMethods(), incogControllerClass);

		}

	}

	public static void loadFields()
	{
		keyboardLoaderClass_layoutField = ProfileHelpers.findFirstDeclaredFieldWithType( layoutClass, PriorityKeyboardClassManager.keyboardLoaderClass);
		keyboardLoaderClass_layoutField.setAccessible(true);

		if (incogControllerClass != null)
		{
			incogControllerClass_staticInstanceField = ProfileHelpers.findFirstDeclaredFieldWithType( incogControllerClass, incogControllerClass );
			if ( incogControllerClass_staticInstanceField != null )
			{
				incogControllerClass_staticInstanceField.setAccessible(true);
			}

		}
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
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_base,	 "keyboardLoaderClass", 	PriorityKeyboardClassManager.keyboardLoaderClass );


		//Popup
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_invalidateLayout,	 "keyboardLoader_onSharedPreferenceChangedMethod", 	PriorityKeyboardClassManager.keyboardLoader_onSharedPreferenceChangedMethod );

		//Hitbox
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "layoutClass", 	layoutClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "keyboardLoader_loadMethod", 	PriorityKeyboardClassManager.keyboardLoader_loadMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "keyboardLoaderClass_layoutField", 	keyboardLoaderClass_layoutField );

		//Keyboard size
		Hooks.logSetRequirement( Hooks.baseHooks_keyHeight,	 "keyHeightClass_getKeyHeightMethod", 	!keyHeightClass_getKeyHeightMethods.isEmpty() );

	}
}
