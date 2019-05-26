package com.mayulive.swiftkeyexi.xposed.key;

/**
 * Created by Roughy on 2/15/2017.
 */


import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;
import com.mayulive.xposed.classhunter.Modifiers;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


import static com.mayulive.xposed.classhunter.Modifiers.*;


public class KeyClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(KeyClassManager.class);

	///////////////////
	//Unknown classes
	///////////////////

	//protected static Class onButtonReleaseHandlerClass = null;	//Deprecated key-up

	protected static List<Class> normalButtonClickItemClass = null;

	protected static Class keyDefinitionKeyClass = null;
	protected static Class pointerLocationClass = null;
	public static Class keyRawDefinitionClass = null;

	public static Class newKeyInfoClass = null;


	/////////////////////////
	//Methods
	/////////////////////////

	//protected static Method onButtonReleasedHandlerMethod = null;	//Deprecated key-up
	protected static List<Method> normalButtonClickItemMethods = null;

	protected static Method keyboardSingleKeyDownMethod = null;

	protected static Method keyRawDefinitionClass_newKeyMethod = null;


	/////////////////////////
	//Fields
	/////////////////////////


	public static void loadUnknownClasses_A(PackageTree param ) throws IOException
	{
		//Necessary for Selection, hotkeys, popups
		{
			keyDefinitionKeyClass = 			ProfileHelpers.loadProfiledClass(	KeyProfiles.get_KEY_DEFINITION_KEY_CLASS_PROFILE(), 	param);
			pointerLocationClass = 				ProfileHelpers.loadProfiledClass(	KeyProfiles.get_POINTER_LOCATION_PROFILE(), 			param);
			keyRawDefinitionClass = 			ProfileHelpers.loadProfiledClass(	KeyProfiles.get_KEY_RAW_DEFINITION_CLASS_PROFILE(),		param);
			newKeyInfoClass = 					ProfileHelpers.loadProfiledClass(	KeyProfiles._get_NEW_KEY_INFO_CLASS_PROFILE(),			param);
		}

		{
			//These two classes share the exact same outward apperance, only differing in a few lines of in-method code.
			//We can't tell them apart, but need to hook both. If the classes at the defined path both match the profile then we're good.
			//Otherwise do a search to make sure we get both of them.
			{
				normalButtonClickItemClass = ProfileHelpers.loadProfiledClasses(KeyProfiles.get_NORMAL_BUTTON_CLICK_ITEM_PROFILE(),2, param);
			}
		}
	}


	public static void loadMethods_A() throws NoSuchMethodException
	{

		if (keyDefinitionKeyClass != null && pointerLocationClass != null)
		{
			//Method order expected to to remain the same.
			//Should they change, maybe we can check the input touch event to figure out which is which?
			keyboardSingleKeyDownMethod  = ProfileHelpers.findFirstProfileMatch(new MethodProfile(
							new ClassItem(void.class),
							new ClassItem(pointerLocationClass)
					),
					keyDefinitionKeyClass.getDeclaredMethods(), keyDefinitionKeyClass);

		}

		if (keyRawDefinitionClass != null)
		{


			MethodProfile profile = new MethodProfile
			(
					PUBLIC | STATIC | EXACT ,
					new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),

					new ClassItem("" , PUBLIC | FINAL | EXACT ),
					new ClassItem("" , PUBLIC | FINAL | EXACT ),
					new ClassItem(java.util.Locale.class),
					new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

			);

			keyRawDefinitionClass_newKeyMethod  = ProfileHelpers.findMostSimilar(profile, keyRawDefinitionClass.getDeclaredMethods(), keyRawDefinitionClass);

			DebugTools.logIfProfileMismatch(keyRawDefinitionClass_newKeyMethod, keyRawDefinitionClass, profile, "keyRawDefinitionClass_newKeyMethod");

		}


		if (pointerLocationClass != null && !normalButtonClickItemClass.isEmpty())
		{
			normalButtonClickItemMethods = new ArrayList<Method>();
			for (int i = 0; i < normalButtonClickItemClass.size(); i++)
			{
				Class clazz = normalButtonClickItemClass.get(i);


				Method method = ProfileHelpers.firstMethodByName( clazz.getDeclaredMethods(), "b");	//TODO this is going to fall soon

				if (method != null)
					normalButtonClickItemMethods.add(method);
			}
		}


	}

	public static void loadFields()
	{


	}



	public static void doAllTheThings(PackageTree param) throws IOException, NoSuchFieldException, NoSuchMethodException
	{

		loadUnknownClasses_A(param);
		loadMethods_A();
		loadFields();


		updateDependencyState();
	}

	protected static void updateDependencyState()
	{

		//Key definition
		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyDefinition,	 "keyRawDefinitionClass", 	keyRawDefinitionClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyDefinition,	 "keyRawDefinitionClass_newKeyMethod", 	keyRawDefinitionClass_newKeyMethod );

		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyDefinition,	 "keyDefinitionKeyClass", 	keyDefinitionKeyClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyDefinition,	 "pointerLocationClass", 	pointerLocationClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyDefinition,	 "keyboardSingleKeyDownMethod", 	keyboardSingleKeyDownMethod );

		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyDefinition,	 "newKeyInfoClass", 	newKeyInfoClass );

		//Key cancellation
		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyCancel,	 "normalButtonClickItemClass", 	normalButtonClickItemClass );
		Hooks.logSetRequirement( Hooks.keyHooks_keyCancel,	 "normalButtonClickItemMethods", 	(normalButtonClickItemMethods != null && !normalButtonClickItemMethods.isEmpty()) );

	}
}
