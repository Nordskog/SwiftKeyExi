package com.mayulive.swiftkeyexi.xposed.keyboard;

/**
 * Created by Roughy on 2/15/2017.
 */

import android.net.Uri;
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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.mayulive.xposed.classhunter.Modifiers.*;


public class KeyboardClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(KeyboardClassManager.class);

	/////////////////////////////////////////

	private static Class insertGifClass = null;
	protected static Method insertGifClass_insertGifMethod = null;

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

	////////////////////////////////////////////

	public static Class quickSettingsClass;
	public static Method quickSettingsClass_createSettingsMethod;

	public static Class quickSettingPrefReferenceClass;
	public static Constructor quickSettingPrefReferenceClass_constructor;

	public static Class quicksettingPrefItemClass;
	public static Constructor quicksettingPrefItemClass_constructor;

	//////////////////////////////////////////////

	protected static Class searchClass;
	protected static List<Method> searchClass_bingSearchMethods = new ArrayList<>();


	public static void loadKnownClasses(PackageTree param)
	{
		layoutClass = ClassHunter.loadClass("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout", param.getClassLoader());
	}

	public static void loadUnknownClasses(PackageTree param)
	{
		KeyHeightClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_KEY_HEIGHT_CLASS_PROFILE(), param );

		incogControllerClass =  ProfileHelpers.loadProfiledClass( KeyboardProfiles._get_INCOG_CONTROL_CLASS_PROFILE(), param );

		insertGifClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles.get_INSERT_GIF_CLASS_PROFILE(), param );


		quickSettingsClass =  ProfileHelpers.loadProfiledClass( KeyboardProfiles._get_QUICK_SETTINGS_CLASS_PROFILE(), param );
		{
			if (quickSettingsClass != null)
			{
				quickSettingsClass_createSettingsMethod = ProfileHelpers.findMostSimilar( 	new MethodProfile
						(
								PRIVATE | STATIC | EXACT ,
								new ClassItem(java.util.List.class),

								new ClassItem(android.content.Context.class),
								new ClassItem("" , PUBLIC | FINAL | EXACT ),
								new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
								new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
								new ClassItem("" , PUBLIC | FINAL | EXACT ),
								new ClassItem("" , PUBLIC | FINAL | EXACT ),
								new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
								new ClassItem("" , PUBLIC | FINAL | EXACT ),
								new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
								new ClassItem("" , PUBLIC | FINAL | EXACT ),
								new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )


						), quickSettingsClass.getDeclaredMethods(), quickSettingsClass);

				if (quickSettingsClass_createSettingsMethod != null)
				{
					Class[] params =  quickSettingsClass_createSettingsMethod.getParameterTypes();

					// actually longer than this but just a sanity check
					// If wrong method it will just fail later.
					if (params.length > 5)
					{
						quickSettingPrefReferenceClass =  ProfileHelpers.loadProfiledClass( KeyboardProfiles._get_QUICK_SETTINGS_PREF_REFERENCE_CLASS_PROFILE(params[1]), param );
						quicksettingPrefItemClass =  ProfileHelpers.loadProfiledClass( KeyboardProfiles._get_QUICK_SETTINGS_PREF_ITEM_CLASS_PROFILE(), param );
					}




				}
			}
		}

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
			incogControllerClass_ChangeIncogStateMethod = ProfileHelpers.findMostSimilar( new MethodProfile(
					 Modifiers.PUBLIC | Modifiers.FINAL | Modifiers.EXACT,
					new ClassItem(void.class),
					new ClassItem(int.class),
					new ClassItem(boolean.class)
			), incogControllerClass.getDeclaredMethods(), incogControllerClass);

		}


		if (quickSettingPrefReferenceClass != null)
		{
			Constructor[] constructors = quickSettingPrefReferenceClass.getDeclaredConstructors();
			if (constructors.length > 0)
				quickSettingPrefReferenceClass_constructor = constructors[0];
		}

		if (quicksettingPrefItemClass != null)
		{
			Constructor[] constructors = quicksettingPrefItemClass.getDeclaredConstructors();
			if (constructors.length > 0)
				quicksettingPrefItemClass_constructor = constructors[0];
		}

		if (searchClass != null)
		{
			// There are 3 methods with a very similar pattern.
			// We only care for 2 of them I think, but we perform a check on the string
			// to see if it's a search string, so probably fine.
			// One is for text typed into search box, other is for search suggestions when clicked.

			searchClass_bingSearchMethods = ProfileHelpers.findMostSimilar(  new MethodProfile
					(
							new ClassItem(java.lang.String.class),

							new ClassItem(java.lang.String.class),
							new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

					), searchClass.getDeclaredMethods(), searchClass, 2 );

		}

		if (insertGifClass != null)
		{
			insertGifClass_insertGifMethod = ProfileHelpers.findMostSimilar( new MethodProfile(
					new ClassItem( void.class ),
					new ClassItem( Uri.class ),
					new ClassItem( Uri.class ),
					new ClassItem( String.class )), insertGifClass.getDeclaredMethods(), insertGifClass );
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


		//Popup
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_invalidateLayout,	 "keyboardLoader_onSharedPreferenceChangedMethod", 	PriorityKeyboardClassManager.keyboardLoader_onSharedPreferenceChangedMethod );

		//Hitbox
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "layoutClass", 	layoutClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "keyboardLoader_loadMethod", 	PriorityKeyboardClassManager.keyboardLoader_loadMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.baseHooks_layoutChange,	 "keyboardLoaderClass_layoutField", 	keyboardLoaderClass_layoutField );

		//Keyboard size
		Hooks.logSetRequirement( Hooks.baseHooks_keyHeight,	 "keyHeightClass_getKeyHeightMethod", 	!keyHeightClass_getKeyHeightMethods.isEmpty() );

		//Quicksettings
		Hooks.logSetRequirementFalseIfNull( Hooks.quickSettings,	 "quickSettingsClass_createSettingsMethod", 	quickSettingsClass_createSettingsMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.quickSettings,	 "quickSettingPrefReferenceClass_constructor", 	quickSettingPrefReferenceClass_constructor );
		Hooks.logSetRequirementFalseIfNull( Hooks.quickSettings,	 "quicksettingPrefItemClass_constructor", 	quicksettingPrefItemClass_constructor );

		//Incognito
		Hooks.logSetRequirementFalseIfNull( Hooks.incognito,	 "incogControllerClass_ChangeIncogStateMethod", 	incogControllerClass_ChangeIncogStateMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.incognito,	 "incogControllerClass_staticInstanceField;", 	incogControllerClass_staticInstanceField );

		//Search
		Hooks.logSetRequirement( Hooks.search,  "searchClass_bingSearchMethods is empty", !searchClass_bingSearchMethods.isEmpty());

		//Gif redirect
		Hooks.logSetRequirementFalseIfNull( Hooks.gifRemoveRedirect, "insertGifClass_insertGifMethod is null", insertGifClass_insertGifMethod );
	}
}
