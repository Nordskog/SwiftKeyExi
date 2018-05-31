package com.mayulive.swiftkeyexi.xposed.predictions;

import android.widget.LinearLayout;

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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.robv.android.xposed.XposedHelpers;

import static com.mayulive.xposed.classhunter.Modifiers.ABSTRACT;
import static com.mayulive.xposed.classhunter.Modifiers.EXACT;
import static com.mayulive.xposed.classhunter.Modifiers.FINAL;
import static com.mayulive.xposed.classhunter.Modifiers.INTERFACE;
import static com.mayulive.xposed.classhunter.Modifiers.PRIVATE;
import static com.mayulive.xposed.classhunter.Modifiers.PUBLIC;
import static com.mayulive.xposed.classhunter.Modifiers.STATIC;

public class PriorityPredictionsClassManager
{
	public static Class candidatesViewFactory;
	protected static Class candidateViewClass;
	protected static Class KeyboardUxOptionsInterfaceClass;
	protected static Class keyboardFrameClass;
	protected static Class buClass = null;

	protected static Constructor candidateViewClass_Constructor;
	public static Method candidatesViewFactory_getViewMethod;
	public static Method candidatesViewFactory_ReturnWrapperClass_GetViewMethod = null;
	protected static Method candidateViewClass_setCandidateMethod;

	protected static Method keyboardFrameClass_setBuMethod;
	protected static Field keyboardFrameClass_buField;

	public static int candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition = 1;
	protected static int[] getViewMethod_CandidateViewClassConstructorArgPositions;
	protected static int getViewMethod_EnumArgPosition;
	protected static int getViewMethod_BooleanArgPosition = 5;
	protected static int keyboardFrameClass_setBuMethod_KeyboardUxOptionsPosition = -1;	//Will check if not found

	protected static Object buInstance = null;
	protected static Object KeyboardUxOptionsInstance = null;


	public static void loadKnownClasses(PackageTree param) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException
	{
		CandidateManager.doAllTheThings(param);
		CandidateManager.updateDependencyState();

		PriorityPredictionsClassManager.keyboardFrameClass = ClassHunter.loadClass("com.touchtype.keyboard.view.frames.KeyboardFrame", param.getClassLoader());
	}

	public static void loadUnknownClasses(PackageTree param)
	{
		PriorityPredictionsClassManager.candidateViewClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_CANDIDATE_VIEW_CLASS_PROFILE(), param );

		//Targets >= 6.6.7.24
		PriorityPredictionsClassManager.candidatesViewFactory = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_CANDIDATES_VIEW_FACTORY_CLASS_PROFILE(), param );

		PriorityPredictionsClassManager.buClass = ProfileHelpers.loadProfiledClass(PredictionProfiles.get_BU_CLASS_PROFILE(), param );

		PriorityPredictionsClassManager.KeyboardUxOptionsInterfaceClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_KEYBOARD_UX_OPTIONS_INTERFACE_PROFILE(), param );
	}

	public static void loadMethods() throws NoSuchMethodException
	{
		if (PriorityPredictionsClassManager.candidatesViewFactory != null)
		{

			//There is another method that's 99% the same as the one we're after, so no fuzzy selection here.
			//If it breaks we just have to update manually.
			//Edit: Aaaaand they changed something. It's okay with most similar though maybe?
			//Edit2: Nope. Lots of changes later we're back. Can tell the method apart now.

			PriorityPredictionsClassManager.candidatesViewFactory_getViewMethod = ProfileHelpers.findMostSimilar(		new MethodProfile
							(
									PRIVATE | STATIC | EXACT ,
									new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

									new ClassItem(android.content.Context.class),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem(int.class),
									new ClassItem(int.class),
									new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem(android.view.View.class),
									new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard.candidates.view" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype.keyboard.view.fancy.richcontent.gifs.searchbox" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT )

							),
					PriorityPredictionsClassManager.candidatesViewFactory.getDeclaredMethods(), PriorityPredictionsClassManager.candidatesViewFactory);

			PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod = ProfileHelpers.findMostSimilar(		new MethodProfile
							(
									STATIC | EXACT ,
									new ClassItem(void.class),

									new ClassItem(android.content.Context.class),
									new ClassItem(android.widget.LinearLayout.class),
									new ClassItem(android.view.View.class),
									new ClassItem("com.touchtype.keyboard.view" , PUBLIC | INTERFACE | ABSTRACT | EXACT )
							),
					PriorityPredictionsClassManager.candidatesViewFactory.getDeclaredMethods(), PriorityPredictionsClassManager.candidatesViewFactory);



			if (PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod != null)
			{
				PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition = ProfileHelpers.findFirstClassIndex( LinearLayout.class, PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod.getParameterTypes() );
			}
		}

		if (PriorityPredictionsClassManager.candidateViewClass != null)
		{
			PriorityPredictionsClassManager.candidateViewClass_Constructor = PriorityPredictionsClassManager.candidateViewClass.getDeclaredConstructors()[0];
			candidateViewClass_setCandidateMethod = XposedHelpers.findMethodExact(PriorityPredictionsClassManager.candidateViewClass, "setCandidate", CandidateManager.candidateInterfaceClass);
		}

		if (PriorityPredictionsClassManager.candidatesViewFactory_getViewMethod != null && candidateViewClass_Constructor != null)
		{
			getViewMethod_CandidateViewClassConstructorArgPositions = ProfileHelpers.findParameterPositions(candidateViewClass_Constructor.getParameterTypes(), PriorityPredictionsClassManager.candidatesViewFactory_getViewMethod.getParameterTypes());
			getViewMethod_EnumArgPosition = ProfileHelpers.findFirstClassIndex(new ClassItem(Modifiers.ENUM), candidateViewClass_Constructor.getParameterTypes(), null);
			getViewMethod_BooleanArgPosition = ProfileHelpers.findFirstClassIndex(new ClassItem(boolean.class), candidateViewClass_Constructor.getParameterTypes(), null);
		}

		if (PriorityPredictionsClassManager.keyboardFrameClass != null)
		{
			PriorityPredictionsClassManager.keyboardFrameClass_setBuMethod = ProfileHelpers.findMostSimilar(	new MethodProfile
							(
									PUBLIC | EXACT ,
									new ClassItem(void.class),

									new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),	//ba
									new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT )

							),
					PriorityPredictionsClassManager.keyboardFrameClass.getDeclaredMethods(), PriorityPredictionsClassManager.keyboardFrameClass);

			PriorityPredictionsClassManager.keyboardFrameClass_setBuMethod_KeyboardUxOptionsPosition = ProfileHelpers.findFirstClassIndex(
					PriorityPredictionsClassManager.KeyboardUxOptionsInterfaceClass,
					PriorityPredictionsClassManager.keyboardFrameClass_setBuMethod.getParameterTypes() );
		}

	}

	public static void loadFields()
	{
		if (PriorityPredictionsClassManager.keyboardFrameClass != null)
		{
			PriorityPredictionsClassManager.keyboardFrameClass_buField = ProfileHelpers.findFirstDeclaredFieldWithType(PriorityPredictionsClassManager.buClass, PriorityPredictionsClassManager.keyboardFrameClass);
			if (PriorityPredictionsClassManager.keyboardFrameClass_buField != null)
				PriorityPredictionsClassManager.keyboardFrameClass_buField.setAccessible(true);
		}
	}


	public static void doAllTheThings(PackageTree param) throws IOException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
	{

		loadKnownClasses(param);
		loadUnknownClasses(param);
		loadMethods();
		loadFields();

		updateDependencyState();
	}


	protected static void updateDependencyState()
	{
		//Too many classes, too many methods.

		//More suggestions
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "keyboardFrameClass", 	PriorityPredictionsClassManager.keyboardFrameClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidateViewClass", 	PriorityPredictionsClassManager.candidateViewClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidatesViewFactory", 	PriorityPredictionsClassManager.candidatesViewFactory );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidatesViewFactory_getViewMethod", 	PriorityPredictionsClassManager.candidatesViewFactory_getViewMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidateViewClass_Constructor", 	PriorityPredictionsClassManager.candidateViewClass_Constructor );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidateViewClass_setCandidateMethod", 	PriorityPredictionsClassManager.candidateViewClass_setCandidateMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "keyboardFrameClass_setBuMethod", 	PriorityPredictionsClassManager.keyboardFrameClass_setBuMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "buClass", 	PriorityPredictionsClassManager.buClass );

		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "KeyboardUxOptionsInterfaceClass", 	PriorityPredictionsClassManager.KeyboardUxOptionsInterfaceClass );

		Hooks.logSetRequirement( Hooks.predictionHooks_more,	 "getViewMethod_EnumArgPosition", PriorityPredictionsClassManager.getViewMethod_EnumArgPosition != -1 ) ;

	}

}
