package com.mayulive.swiftkeyexi.xposed.keyboard;

/**
 * Created by Roughy on 2/15/2017.
 */

import android.net.Uri;
import android.util.Log;
import android.view.inputmethod.InputConnection;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.mayulive.xposed.classhunter.Modifiers.*;


public class KeyboardClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(KeyboardClassManager.class);

	////////////////////////////////////////

	protected static Class inputConnectionImplementationClass = null;
	protected static Method inputConnectionImplementationClass_sendKeyEventMethod = null;

	///////////////////////////////////////////

	protected static Class themeSetterClass = null;
	static Method themeSetterClass_setThemeMethod = null;

	static Object themeSetter_dummyCtiInstance = null;
	static Object themeSetterClass_instance = null;

	///////////////////////////////

	protected static Class themeContainerClass = null;
	protected static List<Method> themeContainerClass_booleanRetMethods = new ArrayList<>();


	/////////////////////////////////////////

	private static Class insertGifClass = null;
	protected static Method insertGifClass_insertGifMethod = null;

	public static List<Class> insertGifTextClasses = null;

	///////////////////////////////////////

	public static Class layoutClass = null;
	public static Field keyboardLoaderClass_layoutField = null;
	public static InputConnection currentInputConnection = null;

	///////////////////////////////////////////

	public static Class KeyHeightClass = null;
	protected static List<Method> keyHeightClass_getKeyHeightMethods = new ArrayList<Method>();

	//////////////////////////////////////////////

	public static Class incogControllerClass;
	protected static Method incogControllerClass_ChangeIncogStateMethod;
	public static Field incogControllerClass_staticInstanceField;

	//////////////////////////////////////////////

	protected static Class searchClass;
	protected static List<Method> searchClass_bingSearchMethods = new ArrayList<>();


	////////////////////////////

	protected static Class toolbarButtonClass = null;

	public static void loadKnownClasses(PackageTree param)
	{
		layoutClass = ClassHunter.loadClass("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout", param.getClassLoader());

		inputConnectionImplementationClass = ClassHunter.loadClass("com.android.internal.view.InputConnectionWrapper", param.getClassLoader());
	}

	public static void loadUnknownClasses(PackageTree param)
	{
		toolbarButtonClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_TOOLBAR_BUTTON_PROFILE(), param );

		themeSetterClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_KEYBOARD_THEME_SETTER_PROFILE(), param );

		KeyHeightClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_KEY_HEIGHT_CLASS_PROFILE(), param );

		incogControllerClass =  ProfileHelpers.loadProfiledClass( KeyboardProfiles._get_INCOG_CONTROL_CLASS_PROFILE(), param );

		insertGifClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_INSERT_GIF_CLASS_PROFILE(), param );
		insertGifTextClasses = ProfileHelpers.loadProfiledClasses( KeyboardProfiles.get_INSERT_GIF_TEXT_CLASS_RPFOILE(), 2, param );

		searchClass =  ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_SEARCH_CLASS_PROFILE(), param );



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
			MethodProfile profile = new MethodProfile
			(
					PUBLIC | EXACT ,
					new ClassItem(void.class),

					new ClassItem(int.class),
					new ClassItem(boolean.class)
			);

			incogControllerClass_ChangeIncogStateMethod = ProfileHelpers.findMostSimilar( profile, incogControllerClass.getDeclaredMethods(), incogControllerClass);

			DebugTools.logIfMethodProfileMismatch(incogControllerClass_ChangeIncogStateMethod, incogControllerClass, profile, "incogControllerClass_ChangeIncogStateMethod");

		}

		if (searchClass != null)
		{
			// There are 3 methods with a very similar pattern.
			// We only care for 2 of them I think, but we perform a check on the string
			// to see if it's a search string, so probably fine.
			// One is for text typed into search box, other is for search suggestions when clicked.

			MethodProfile profile = new MethodProfile
			(
					new ClassItem(java.lang.String.class)
			);

			// There are 3 methods, all return strings. Just check them all.
			searchClass_bingSearchMethods = ProfileHelpers.findMostSimilar(  profile, searchClass.getDeclaredMethods(), searchClass, 3 );

		}

		if (insertGifClass != null)
		{
			MethodProfile profile = new MethodProfile
			(
				new ClassItem( void.class ),
				new ClassItem( Uri.class ),
				new ClassItem( Uri.class ),
				new ClassItem( String.class )
			);


			insertGifClass_insertGifMethod = ProfileHelpers.findMostSimilar( profile, insertGifClass.getDeclaredMethods(), insertGifClass);

			DebugTools.logIfMethodProfileMismatch(insertGifClass_insertGifMethod, insertGifClass, profile, "insertGifClass_insertGifMethod");

		}

		if (themeSetterClass != null)
		{

			MethodProfile profile =  new MethodProfile
			(
					PUBLIC | EXACT ,
					new ClassItem(PUBLIC | INTERFACE | ABSTRACT | EXACT ),

					new ClassItem(java.lang.String.class),
					new ClassItem(boolean.class),
					new ClassItem( PUBLIC | INTERFACE | ABSTRACT | EXACT ),
					new ClassItem(java.util.concurrent.Executor.class)

			);

			KeyboardClassManager.themeSetterClass_setThemeMethod = ProfileHelpers.findMostSimilar( profile, themeSetterClass.getDeclaredMethods(), themeSetterClass );
			DebugTools.logIfMethodProfileMismatch(  themeSetterClass_setThemeMethod, themeSetterClass, profile, "themeSetterClass_setThemeMethod");

			//////////////////

			if ( KeyboardClassManager.themeSetterClass_setThemeMethod != null)
			{
				Type[] argTypes = ( (ParameterizedType)KeyboardClassManager.themeSetterClass_setThemeMethod.getGenericReturnType() ).getActualTypeArguments();
				if (argTypes.length > 0)
				{
					themeContainerClass = (Class)  argTypes[0];
					themeContainerClass_booleanRetMethods = ProfileHelpers.findAllMethodsWithReturnType(boolean.class, themeContainerClass.getDeclaredMethods());

					// There are multiple methods, but both seem to return matched true/false values
					// May be some weirdo themes where they are different
					for (Method method : themeContainerClass_booleanRetMethods)
					{
						method.setAccessible(true);
					}
				}
				else
				{
					Log.e(LOGTAG, "Set theme method return type args empty!");
				}

			}
		}

		if (inputConnectionImplementationClass != null)
		{
			inputConnectionImplementationClass_sendKeyEventMethod = ProfileHelpers.findFirstMethodByName( inputConnectionImplementationClass.getDeclaredMethods(), "sendKeyEvent" );
		}
	}

	public static void loadFields()
	{
		if ( PriorityKeyboardClassManager.keyboardLoaderClass != null && layoutClass != null)
		{
			keyboardLoaderClass_layoutField = ProfileHelpers.findFirstDeclaredFieldWithType( layoutClass, PriorityKeyboardClassManager.keyboardLoaderClass);
			if (keyboardLoaderClass_layoutField != null)
				keyboardLoaderClass_layoutField.setAccessible(true);
		}


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

		//Theme set
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_toolbarExpandButton, "toolbarButtonClass", toolbarButtonClass );

		//Popup
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_invalidateLayout,	 "keyboardLoaderPreference_onSharedPreferenceChangedMethod", 	PriorityKeyboardClassManager.keyboardLoaderPreference_onSharedPreferenceChangedMethod);

		//Hitbox
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "layoutClass", 	layoutClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "keyboardLoader_loadMethod", 	PriorityKeyboardClassManager.keyboardLoader_loadMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "keyboardLoaderClass_layoutField", 	keyboardLoaderClass_layoutField );

		//Keyboard size
		Hooks.logSetRequirement( Hooks.baseHooks_keyHeight,	 "keyHeightClass_getKeyHeightMethod", 	!keyHeightClass_getKeyHeightMethods.isEmpty() );

		//Incognito
		Hooks.logSetRequirementFalseIfNull( Hooks.incognito,	 "incogControllerClass_ChangeIncogStateMethod", 	incogControllerClass_ChangeIncogStateMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.incognito,	 "incogControllerClass_staticInstanceField;", 	incogControllerClass_staticInstanceField );

		//Search
		Hooks.logSetRequirement( Hooks.search,  "searchClass_bingSearchMethods is empty", !searchClass_bingSearchMethods.isEmpty());

		//Gif redirect
		Hooks.logSetRequirementFalseIfNull( Hooks.gifRemoveRedirect, "insertGifClass_insertGifMethod is null", insertGifClass_insertGifMethod );

		//Theme set
		Hooks.logSetRequirementFalseIfNull( Hooks.themeSet, "themeSetterClass_setThemeMethod is null", themeSetterClass_setThemeMethod );

		//Dark light
		Hooks.logSetRequirementFalseIfNull( 		Hooks.styleHooks_darklight, "themeContainerClass is null", themeContainerClass );
		Hooks.logSetRequirement( 					Hooks.styleHooks_darklight, "themeContainerClass is null", !themeContainerClass_booleanRetMethods.isEmpty() );

		// Select with arrow keys
		Hooks.logSetRequirementFalseIfNull( 		Hooks.baseHooks_selectWithArrows, "inputConnectionImplementationClass_sendKeyEventMethod is null", inputConnectionImplementationClass_sendKeyEventMethod );
	}
}
