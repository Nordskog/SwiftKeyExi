package com.mayulive.swiftkeyexi.xposed.key;

/**
 * Created by Roughy on 2/15/2017.
 */


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

	///////////////////
	//Unknown classes
	///////////////////

	//protected static Class onButtonReleaseHandlerClass = null;	//Deprecated key-up

	protected static List<Class> normalButtonClickItemClass = null;


	protected static Class keyDefinitionKeyClass = null;

	protected static Class pointerLocationClass = null;

	public static Class keyRawDefinitionClass = null;

	protected static Class simpleKeyClass = null;

	protected static Class keyFieldsClass = null;

	/////////////////////////
	//Methods
	/////////////////////////

	//protected static Method onButtonReleasedHandlerMethod = null;	//Deprecated key-up
	protected static List<Method> normalButtonClickItemMethods = null;

	protected static Method keyboardSingleKeyDownMethod = null;

	protected static Method keyRawDefinitionClass_newKeyMethod = null;

	protected static Method keyFieldsClass_setIntegerMethod = null;
	protected static List<Method> keyFieldsClass_setStringMethods = new ArrayList<>();

	/////////////////////////
	//Fields
	/////////////////////////


	public static void loadUnknownClasses_A(PackageTree param ) throws IOException
	{

		//popupKeyItemClass = 				ClassSearch.loadProfiledClass("com.touchtype.keyboard.d.b.aw", 		"com.touchtype.keyboard", 2, false, SelectionProfiles.POPUP_KEY_ITEM_PROFILE, classLoader);
		//onButtonReleaseHandlerClass = 		ClassSearch.loadProfiledClass("com.touchtype.keyboard.d.b.u", 		"com.touchtype.keyboard", 2, false, SelectionProfiles.ON_BUTTON_RELEASE_HANDLER_PROFILE, classLoader);

		//Necessary for Selection, hotkeys, popups
		{
			keyDefinitionKeyClass = 			ProfileHelpers.loadProfiledClass(	KeyProfiles.get_KEY_DEFINITION_KEY_CLASS_PROFILE(), 	param);
			pointerLocationClass = 				ProfileHelpers.loadProfiledClass(	KeyProfiles.get_POINTER_LOCATION_PROFILE(), 			param);
			keyRawDefinitionClass = 			ProfileHelpers.loadProfiledClass(	KeyProfiles.get_KEY_RAW_DEFINITION_CLASS_PROFILE(),	param);

			simpleKeyClass = ProfileHelpers.loadProfiledClass(	KeyProfiles.get_SIMPLE_KEY_CLASS_PROFILE(),	param);
			keyFieldsClass =  ProfileHelpers.loadProfiledClass(	KeyProfiles.get_KEY_FIELDS_CLASS_PROFILE(),	param);
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
		//onButtonReleasedHandlerMethod = XposedHelpers.findMethodExact(onButtonReleaseHandlerClass, "a_", pointerLocationClass);	//deprecated key-up

		if (keyDefinitionKeyClass != null && pointerLocationClass != null)
		{
			//keyboardSingleKeyDownMethod = XposedHelpers.findMethodExact(keyDefinitionKeyClass, "b", pointerLocationClass);

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

			keyRawDefinitionClass_newKeyMethod  = ProfileHelpers.findMostSimilar(new MethodProfile(
							Modifiers.PUBLIC | Modifiers.STATIC,
							new ClassItem(String.class),

							new ClassItem(String.class)
					),
					keyRawDefinitionClass.getDeclaredMethods(), keyRawDefinitionClass);
		}




		if (pointerLocationClass != null && !normalButtonClickItemClass.isEmpty())
		{
			normalButtonClickItemMethods = new ArrayList<Method>();
			for (int i = 0; i < normalButtonClickItemClass.size(); i++)
			{
				Class clazz = normalButtonClickItemClass.get(i);

				//Luckily the only protected method
				Method method = ProfileHelpers.findFirstProfileMatch(new MethodProfile
						(
								Modifiers.PROTECTED,
								new ClassItem(void.class)
						), clazz.getDeclaredMethods(), clazz);

				if (method != null)
					normalButtonClickItemMethods.add(method);
			}
		}

		if (keyFieldsClass != null)
		{
			keyFieldsClass_setIntegerMethod = ProfileHelpers.findFirstProfileMatch(new MethodProfile
					(
							Modifiers.PUBLIC,
							new ClassItem(null),

							new ClassItem(Integer.class)

					), keyFieldsClass.getDeclaredMethods(), keyFieldsClass);

			keyFieldsClass_setStringMethods = ProfileHelpers.findAllProfileMatches(new MethodProfile
					(
							Modifiers.PUBLIC,
							new ClassItem(null),

							new ClassItem(String.class)

					), keyFieldsClass.getDeclaredMethods(), keyFieldsClass);

		}


	}

	public static void loadFields() throws IOException
	{
		//popupKeyItemClass_stringField = ProfileCommons.findFirstDeclaredFieldWithType(KeyClassManager.popupKeyItemClass, String.class);
		//popupKeyItemClass_stringField.setAccessible(true);
	}



	public static void doAllTheThings(PackageTree param) throws IOException, NoSuchFieldException, NoSuchMethodException
	{

		loadUnknownClasses_A(param);
		loadMethods_A();
		loadFields();

		//Util.printFieldValues(KeyClassManager.class);

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

		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyDefinition,	 "keyFieldsClass", 	keyFieldsClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyDefinition,	 "simpleKeyClass", 	simpleKeyClass );

		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyDefinition,	 "keyFieldsClass_setIntegerMethod", 	keyFieldsClass_setIntegerMethod );
		Hooks.logSetRequirement( Hooks.keyHooks_keyDefinition,	 "keyFieldsClass_setStringMethods", 	!keyFieldsClass_setStringMethods.isEmpty() );

		//Key cancellation
		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyCancel,	 "normalButtonClickItemClass", 	normalButtonClickItemClass );
		Hooks.logSetRequirement( Hooks.keyHooks_keyCancel,	 "normalButtonClickItemMethods", 	(normalButtonClickItemMethods != null && !normalButtonClickItemMethods.isEmpty()) );

	}
}
