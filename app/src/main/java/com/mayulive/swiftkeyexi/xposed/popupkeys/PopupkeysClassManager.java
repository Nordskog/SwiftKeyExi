package com.mayulive.swiftkeyexi.xposed.popupkeys;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.profiles.ClassItem;

import com.mayulive.xposed.classhunter.profiles.MethodProfile;
import com.mayulive.xposed.classhunter.ProfileHelpers;

import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.swiftkeyexi.xposed.key.KeyClassManager;


import de.robv.android.xposed.XposedHelpers;

import static com.mayulive.xposed.classhunter.Modifiers.*;


public class PopupkeysClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(PopupkeysClassManager.class);

	/////////////////
	//Known classes
	/////////////////



	///////////////////
	//Unknown classes
	///////////////////


	protected static Class keyLongPressPopupConfigureClass = null;
	protected static Class keyRawDefinitionInputClass = null;

	protected static Class extraKeyPopupRunnableContainerClass = null;
	protected static Class extraKeyPopupRunnableClass = null;

	protected static Class btSubClass = null;


	public static Class keyConfigureClass = null;


	/////////////////////////
	//Methods
	/////////////////////////

	protected static Method addLongPressCharacters_A_Method = null;

	protected static Method extraKeyPopupRunnableRunMethod = null;

	protected static Method btSubClass_getProperOrderMethod = null;


	////////////
	//Fields
	////////////

	protected static ArrayList<Field> keyRawDefinitionInputClass_listFields = new ArrayList<>();

	protected static Field btSubClass_aField = null;
	protected static Field btSubClass_bField = null;

	///////
	//misc
	///////



	private static void loadUnknownClasses(PackageTree param) throws IOException
	{

		//Daisy-chain profiles
		extraKeyPopupRunnableContainerClass = ProfileHelpers.loadProfiledClass( PopupkeyProfiles._get_EXTRA_KEY_POPUP_RUNNABLE_CONTAINER_PROFILE(), param);
		extraKeyPopupRunnableClass =  ProfileHelpers.loadProfiledClass( PopupkeyProfiles._get_EXTRA_KEY_POPUP_RUNNABLE_PROFILE( extraKeyPopupRunnableContainerClass ), param);

		keyLongPressPopupConfigureClass = ProfileHelpers.loadProfiledClass( PopupkeyProfiles.get_KEY_LONGPRESS_POPUP_CONFIGURE_PROFILE(), param);


		{
			btSubClass = keyLongPressPopupConfigureClass.getDeclaredClasses()[0];	//Only has a single inner class
		}
		{
			//XposedHelpers.findClassIfExists("com.touchtype.keyboard.d.l.a", classLoader);
			Class[] innerClasses = KeyClassManager.keyRawDefinitionClass.getDeclaredClasses();

			//There are two classes, one with 2 fields, one with lots more.
			for (Class clazz : innerClasses)
			{
				if (clazz.getDeclaredFields().length > 3)
				{
					keyRawDefinitionInputClass = clazz;
					break;
				}
			}
		}

		keyConfigureClass = ProfileHelpers.loadProfiledClass( PopupkeyProfiles.get_KEY_LONGPRESS_CONFIGURE_PROFILE(), param);
	}


	private static void loadMethods() throws NoSuchMethodException
	{


		addLongPressCharacters_A_Method = ProfileHelpers.findMostSimilar(new MethodProfile
				(
						EXACT,
						new ClassItem(List.class),

						new ClassItem(List.class),
						new ClassItem(CharSequence.class),
						new ClassItem(boolean.class),
						new ClassItem(boolean.class)
				),
				keyConfigureClass.getDeclaredMethods(), keyConfigureClass);


		extraKeyPopupRunnableRunMethod = XposedHelpers.findMethodExact(extraKeyPopupRunnableClass, "run");
		btSubClass_getProperOrderMethod = ProfileHelpers.findAllMethodsWithReturnType(btSubClass, keyLongPressPopupConfigureClass.getDeclaredMethods()).get(0);

	}

	private static void loadFields()
	{
		final Field[] fields = keyRawDefinitionInputClass.getDeclaredFields();
		for (Field field : fields)
		{
			if (field.getType() == List.class)
			{
				keyRawDefinitionInputClass_listFields.add(field);
				field.setAccessible(true);
			}
		}

		btSubClass_aField = ProfileHelpers.findFirstDeclaredFieldWithType(List.class, btSubClass);
		btSubClass_bField =  ProfileHelpers.findFirstDeclaredFieldWithType(int.class, btSubClass);

		btSubClass_aField.setAccessible(true);
		btSubClass_bField.setAccessible(true);
	}

	public static void doAllTheThings(PackageTree param) throws IOException, NoSuchFieldException, NoSuchMethodException
	{
		loadUnknownClasses(param);
		loadMethods();
		loadFields();


		updateDependencyState();
	}



	protected static void updateDependencyState()
	{
		//Necessary to cancel/block popups
		Hooks.logSetRequirementFalseIfNull(Hooks.popupHooks_cancel, "extraKeyPopupRunnableClass", extraKeyPopupRunnableClass);
		Hooks.logSetRequirementFalseIfNull(Hooks.popupHooks_cancel, "extraKeyPopupRunnableRunMethod", extraKeyPopupRunnableRunMethod);

		//Necessary to read popup data
		Hooks.logSetRequirementFalseIfNull(Hooks.popupHooks_read, "keyLongPressPopupConfigureClass", keyLongPressPopupConfigureClass);
		Hooks.logSetRequirementFalseIfNull(Hooks.popupHooks_read, "keyRawDefinitionInputClass", keyRawDefinitionInputClass);
		Hooks.logSetRequirementFalseIfNull(Hooks.popupHooks_read, "addLongPressCharacters_A_Method", addLongPressCharacters_A_Method);

		//Necessary to also modify popups
		Hooks.logSetRequirementFalseIfNull(Hooks.popupHooks_modify, "btSubClass", btSubClass);
		Hooks.logSetRequirementFalseIfNull(Hooks.popupHooks_modify, "btSubClass_aField", btSubClass_aField);
		Hooks.logSetRequirementFalseIfNull(Hooks.popupHooks_modify, "btSubClass_bField", btSubClass_bField);
		Hooks.logSetRequirementFalseIfNull(Hooks.popupHooks_modify, "btSubClass_getProperOrderMethod", btSubClass_getProperOrderMethod);
	}
}
