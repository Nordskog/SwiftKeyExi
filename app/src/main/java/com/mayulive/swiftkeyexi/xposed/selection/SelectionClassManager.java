package com.mayulive.swiftkeyexi.xposed.selection;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardClassManager;
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


	//protected static Class keyboardFrameClass = null;

	protected static Class frameHolderFactoryClass = null;
	protected static Class FlowDelegateClass = null;
	protected static Class swipeDelegateClass = null;
	protected static Class SelectionChangedInputEventClass = null;

	///////////////////
	//Methods
	///////////////////


	protected static Method frameHolderFactoryClass_frameHolderInflaterMethod = null;
	//protected static List<Method> FlowDelegate_flowDetectedMethods = null;
	protected static Method FlowDelegate_flowDetectedMethod = null;
	protected static Method swipeDelegate_flowDetectedMethod = null;
	protected static Method SelectionChangedInputEventClass_hasMovedAbruptlyMethod = null;


	////////////
	//Objects
	////////////

	protected static Object FlowDelegate_DRAG_ENUM = null;

	public static void loadUnknownClasses(PackageTree param) throws IOException
	{

		frameHolderFactoryClass = ProfileHelpers.loadProfiledClass( SelectionProfiles.get_FRAME_HOLDER_FACTORY_CLASS_PROFILE(), param );

		FlowDelegateClass = ProfileHelpers.loadProfiledClass( SelectionProfiles.get_FLOW_DELEGATE_CLASS_PROFILE(), param );
		swipeDelegateClass = ProfileHelpers.loadProfiledClass( SelectionProfiles.get_SWIPE_DELEGATE_CLASS_PROFILE(), param );


		SelectionChangedInputEventClass = ProfileHelpers.loadProfiledClass( SelectionProfiles.get_SELECTION_CHANGED_INPUT_EVENT_PROFILE(), param );
	}


	public static void loadMethods() throws NoSuchMethodException
	{

		if (frameHolderFactoryClass != null)
		{
				frameHolderFactoryClass_frameHolderInflaterMethod = ProfileHelpers.findMostSimilar(new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype.keyboard." , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard.expandedcandidate." , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard.expandedcandidate." , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard.candidates.view." , PUBLIC | EXACT )

								),
						frameHolderFactoryClass.getDeclaredMethods(), frameHolderFactoryClass);
		}

		if (FlowDelegateClass != null)
		{
			//Catches another method I don't quite remember what does, but it's ... probably fine.
			//It is possible to tell them apart if need be.
			//FlowDelegate_flowDetectedMethods = ProfileHelpers.findAllMethodsWithReturnType(boolean.class, FlowDelegateClass.getDeclaredMethods());



			FlowDelegate_flowDetectedMethod = ProfileHelpers.findMostSimilar(new MethodProfile
							(
									PRIVATE,
									new ClassItem(boolean.class),
									new ClassItem( ENUM)
							),
					FlowDelegateClass.getDeclaredMethods(), FlowDelegateClass);

			if (FlowDelegate_flowDetectedMethod != null)
			{
				if (FlowDelegate_flowDetectedMethod.getParameterTypes().length == 1)
				{
					Class enumClass = FlowDelegate_flowDetectedMethod.getParameterTypes()[0];
					if (enumClass.isEnum())
					{
						FlowDelegate_DRAG_ENUM = ProfileHelpers.findEnumByName((Enum[])enumClass.getEnumConstants(), "DRAG");
					}
				}
			}
		}


		if (swipeDelegateClass != null)
		{
			swipeDelegate_flowDetectedMethod = ProfileHelpers.findMostSimilar(new MethodProfile
							(
									PROTECTED | EXACT ,
									new ClassItem(boolean.class),

									new ClassItem(KeyboardClassManager.breadcrumbClass ),
									new ClassItem(float.class),
									new ClassItem(float.class),
									new ClassItem(float.class),
									new ClassItem(float.class)

							),
					swipeDelegateClass.getDeclaredMethods(), swipeDelegateClass);
		}



		try
		{
			//Not a requirement
			if ( SelectionChangedInputEventClass != null)
			{
				List<Method> methods = ProfileHelpers.findAllMethodsWithReturnType(boolean.class, SelectionChangedInputEventClass.getDeclaredMethods());

				//You generally don't want to rely on methods being in the same order, as this is not assured.
				//In the case of swiftkey fields change a lot, but method orders have been constant for years.
				//This class in particular hasns't been modified at all.
				SelectionChangedInputEventClass_hasMovedAbruptlyMethod = methods.get( methods.size() - 2 );
			}
		}
		catch (Exception ex)
		{
			Log.e(LOGTAG, "Something went wrong obtaining SelectionChangedInputEventClass_hasMovedAbruptlyMethod. Expect some flicker when swipig");
			ex.printStackTrace();
		}
	}
	

	public static void doAllTheThings(PackageTree param) throws IOException, NoSuchFieldException, NoSuchMethodException
	{
		loadUnknownClasses(param);
		loadMethods();

		updateDependencyState();
	}




	protected static void updateDependencyState()
	{

		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "frameHolderFactoryClass", 	frameHolderFactoryClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "FlowDelegateClass", 	FlowDelegateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "swipeDelegateClass", 	swipeDelegateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "frameHolderFactoryClass_frameHolderInflaterMethod", 	frameHolderFactoryClass_frameHolderInflaterMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "swipeDelegate_flowDetectedMethod", 	swipeDelegate_flowDetectedMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "FlowDelegate_flowDetectedMethod",  (FlowDelegate_flowDetectedMethod ));
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "FlowDelegate_DRAG_ENUM",  (FlowDelegate_DRAG_ENUM ));
	}

}
