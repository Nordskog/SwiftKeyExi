package com.mayulive.swiftkeyexi.xposed.key;

/**
 * Created by Roughy on 2/15/2017.
 */


import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
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

	/////////////////////////
	//Methods
	/////////////////////////

	//protected static Method onButtonReleasedHandlerMethod = null;	//Deprecated key-up
	protected static List<Method> normalButtonClickItemMethods = null;

	protected static Method keyboardSingleKeyDownMethod = null;

	protected static Method keyRawDefinitionClass_newKeyMethod = null;


	protected static Method keyRawDefinitionClass_getKeyFieldsMethod = null;


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

			keyRawDefinitionClass_getKeyFieldsMethod = ProfileHelpers.findMostSimilar(new MethodProfile
					(
							PUBLIC | STATIC | EXACT ,
							new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),

							new ClassItem(android.content.res.TypedArray.class),
							new ClassItem(java.lang.String.class),
							new ClassItem("" , PUBLIC | FINAL | EXACT ),
							new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

					),
					keyRawDefinitionClass.getDeclaredMethods(), keyRawDefinitionClass);

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

		//Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyDefinition,	 "keyFieldsClass_setIntegerMethod", 	keyFieldsClass_setIntegerMethod );
		//Hooks.logSetRequirement( Hooks.keyHooks_keyDefinition,	 "keyFieldsClass_setStringMethods", 	!keyFieldsClass_setStringMethods.isEmpty() );

		//Key cancellation
		Hooks.logSetRequirementFalseIfNull( Hooks.keyHooks_keyCancel,	 "normalButtonClickItemClass", 	normalButtonClickItemClass );
		Hooks.logSetRequirement( Hooks.keyHooks_keyCancel,	 "normalButtonClickItemMethods", 	(normalButtonClickItemMethods != null && !normalButtonClickItemMethods.isEmpty()) );

	}
}
