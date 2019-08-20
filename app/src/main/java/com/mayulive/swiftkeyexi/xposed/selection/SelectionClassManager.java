package com.mayulive.swiftkeyexi.xposed.selection;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import static com.mayulive.xposed.classhunter.Modifiers.*;


public class SelectionClassManager
{

	////////////////////
	//Unknown classes
	////////////////////

	private static String LOGTAG = ExiModule.getLogTag(SelectionClassManager.class);



	protected static Class keyboardFrameHolderFrameHolderClass = null;

	protected static Class FlowDelegateClass = null;
	protected static Class swipeDelegateClass = null;
	protected static Class SelectionChangedInputEventClass = null;

	protected static Class selectionChangedInfoClass = null;
	protected static Class SelectionChangedMethodArgumentClass = null;

	///////////////////
	//Methods
	///////////////////


	//protected static List<Method> FlowDelegate_flowDetectedMethods = null;
	protected static Method FlowDelegate_flowDetectedMethod = null;
	protected static Method swipeDelegate_flowDetectedMethod = null;
	protected static Method SelectionChangedInputEventClass_hasMovedAbruptlyMethod = null;

	////////////////////////
	// Fields
	////////////////////////

	protected static Field selectionChangedInfoClass_aFirstIntField;

	////////////
	//Objects
	////////////

	protected static Object FlowDelegate_DRAG_ENUM = null;

	private static void loadKnownClasses(PackageTree param )
	{
		keyboardFrameHolderFrameHolderClass = ClassHunter.loadClass( "com.touchtype.keyboard.view.KeyboardFrameHolderFrame", param.getClassLoader() );
	}

	public static void loadUnknownClasses(PackageTree param) throws IOException
	{
		FlowDelegateClass = ProfileHelpers.loadProfiledClass( SelectionProfiles.get_FLOW_DELEGATE_CLASS_PROFILE(), param );
		swipeDelegateClass = ProfileHelpers.loadProfiledClass( SelectionProfiles.get_SWIPE_DELEGATE_CLASS_PROFILE(), param );

		//Don't need this directly, but classes we want are very generic, so daisy-chaining profiles.
		SelectionChangedMethodArgumentClass = ProfileHelpers.loadProfiledClass( SelectionProfiles.get_SELECTION_CHANGED_METHOD_ARGUMENT_CLASS_PROFILE(), param );
		//Class we actually care about, extends? implements? the above
		selectionChangedInfoClass = ProfileHelpers.loadProfiledClass( SelectionProfiles.get_SELECTION_CHANGED_INFO_CLASS_PROFILE(), param );

		SelectionChangedInputEventClass = ProfileHelpers.loadProfiledClass( SelectionProfiles.get_SELECTION_CHANGED_INPUT_EVENT_PROFILE(SelectionChangedMethodArgumentClass), param );
	}


	public static void loadMethods() throws NoSuchMethodException
	{

		if (FlowDelegateClass != null)
		{
			//Catches another method I don't quite remember what does, but it's ... probably fine.
			//It is possible to tell them apart if need be.

			MethodProfile profile = new MethodProfile
			(
					new ClassItem(boolean.class),
					new ClassItem( ENUM )
			);

			FlowDelegate_flowDetectedMethod = ProfileHelpers.findMostSimilar( profile, FlowDelegateClass.getDeclaredMethods(), FlowDelegateClass);
			DebugTools.logIfMethodProfileMismatch(  FlowDelegate_flowDetectedMethod, FlowDelegateClass, profile, "FlowDelegate_flowDetectedMethod");


			if (FlowDelegate_flowDetectedMethod != null)
			{
				if (FlowDelegate_flowDetectedMethod.getParameterTypes().length == 1)
				{
					Class enumClass = FlowDelegate_flowDetectedMethod.getParameterTypes()[0];
					if (enumClass.isEnum())
					{
						FlowDelegate_DRAG_ENUM = ProfileHelpers.findEnumByName((Enum[])enumClass.getEnumConstants(), "DRAG");
					}
					else
					{
						Log.e(LOGTAG, "FlowDelegate_flowDetectedMethod param not enum: " + enumClass.toString());
					}
				}
				else
				{
					Log.e(LOGTAG, "FlowDelegate_flowDetectedMethod param type count not 1: " + FlowDelegate_flowDetectedMethod.toString());
				}
			}
			else
			{
				Log.e(LOGTAG, "FlowDelegate_flowDetectedMethod null in loadMethods");
			}
		}


		if (swipeDelegateClass != null)
		{
			MethodProfile profile = new MethodProfile
			(
					PUBLIC | EXACT ,
					new ClassItem(void.class),

					new ClassItem("" ,  STATIC | INTERFACE )

			);

			swipeDelegate_flowDetectedMethod = ProfileHelpers.findMostSimilar(
					profile, swipeDelegateClass.getDeclaredMethods(), swipeDelegateClass);

			DebugTools.logIfMethodProfileMismatch(  swipeDelegate_flowDetectedMethod, swipeDelegateClass, profile, "swipeDelegate_flowDetectedMethod");
		}

		try
		{
			//Not a requirement
			if ( SelectionChangedInputEventClass != null)
			{

				MethodProfile profile = new MethodProfile
				(
						PUBLIC | EXACT ,
						new ClassItem(void.class),

						new ClassItem(SelectionChangedMethodArgumentClass),
						new ClassItem("" , PUBLIC | ABSTRACT | EXACT )	//I am castable to selectionChangedInfoClass

				);

				SelectionChangedInputEventClass_hasMovedAbruptlyMethod = ProfileHelpers.findMostSimilar(
						profile, SelectionChangedInputEventClass.getDeclaredMethods(), SelectionChangedInputEventClass);

				DebugTools.logIfMethodProfileMismatch(  SelectionChangedInputEventClass_hasMovedAbruptlyMethod, SelectionChangedInputEventClass, profile, "SelectionChangedInputEventClass_hasMovedAbruptlyMethod");
			}
			else
			{
				Log.i(LOGTAG, "SelectionChangedInputEventClass was null, word select changed event may fire.");
			}
		}
		catch (Exception ex)
		{
			Log.e(LOGTAG, "Something went wrong obtaining SelectionChangedInputEventClass_hasMovedAbruptlyMethod. Expect some flicker when swipig");
			ex.printStackTrace();
		}
	}

	public static void loadFields() throws NoSuchMethodException
	{
		// Fields have a nasty habit of moving around, but these match the constructor param order, so hopefully we're good.
		selectionChangedInfoClass_aFirstIntField = ProfileHelpers.findFirstDeclaredFieldWithType( int.class, selectionChangedInfoClass );
		selectionChangedInfoClass_aFirstIntField.setAccessible(true);
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

		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "keyboardFrameHolderFrameHolderClass", 	keyboardFrameHolderFrameHolderClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "FlowDelegateClass", 	FlowDelegateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "swipeDelegateClass", 	swipeDelegateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "swipeDelegate_flowDetectedMethod", 	swipeDelegate_flowDetectedMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "FlowDelegate_flowDetectedMethod",  (FlowDelegate_flowDetectedMethod ));
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "FlowDelegate_DRAG_ENUM",  (FlowDelegate_DRAG_ENUM ));

		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_movedAbruptly,	 "SelectionChangedInputEventClass_hasMovedAbruptlyMethod",  (SelectionChangedInputEventClass_hasMovedAbruptlyMethod ));
	}

}
