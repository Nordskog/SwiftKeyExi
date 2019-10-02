package com.mayulive.swiftkeyexi.xposed.predictions;

import android.util.Log;
import android.widget.LinearLayout;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
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
import static com.mayulive.xposed.classhunter.Modifiers.ENUM;
import static com.mayulive.xposed.classhunter.Modifiers.EXACT;
import static com.mayulive.xposed.classhunter.Modifiers.FINAL;
import static com.mayulive.xposed.classhunter.Modifiers.INTERFACE;
import static com.mayulive.xposed.classhunter.Modifiers.PRIVATE;
import static com.mayulive.xposed.classhunter.Modifiers.PUBLIC;
import static com.mayulive.xposed.classhunter.Modifiers.STATIC;

public class PriorityPredictionsClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(PriorityPredictionsClassManager.class);


	public static Class candidatesViewFactory;
	protected static Class candidateViewClass;
	protected static Class keyboardFrameClass;
	protected static Class buClass = null;
	protected static Class hpeClass = null;

	protected static Constructor candidateViewClass_Constructor;
	public static Method candidatesViewFactory_getViewMethod;
	public static Method candidatesViewFactory_ReturnWrapperClass_GetViewMethod = null;
	protected static Method candidateViewClass_setCandidateMethod;

	protected static Method keyboardFrameClass_setBuMethod;
	protected static Field keyboardFrameClass_buField;

	protected static Method buClass_submitCandidateMethod;

	protected static Constructor hpeClass_constructor;

	public static int candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition = 1;
	protected static int[] getViewMethod_CandidateViewClassConstructorArgPositions;
	protected static int getViewMethod_EnumArgPosition;
	protected static int getViewMethod_FloatArgPosition;
	protected static int getViewMethod_BooleanArgPosition = 5;

	protected static Object buInstance = null;


	public static void loadKnownClasses(PackageTree param) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException
	{
		CandidateManager.doAllTheThings(param);
		CandidateManager.updateDependencyState();

		PriorityPredictionsClassManager.keyboardFrameClass = ClassHunter.loadClass("com.touchtype.keyboard.view.frames.KeyboardFrame", param.getClassLoader());
	}

	public static void loadUnknownClasses(PackageTree param)
	{
		PriorityPredictionsClassManager.candidateViewClass = ProfileHelpers.loadProfiledClass(PredictionProfiles.get_CANDIDATE_VIEW_CLASS_PROFILE(), param );

		PriorityPredictionsClassManager.buClass = ProfileHelpers.loadProfiledClass(PredictionProfiles.get_BU_CLASS_PROFILE(), param );

		PriorityPredictionsClassManager.candidatesViewFactory = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_CANDIDATES_VIEW_FACTORY_CLASS_PROFILE_NEW(), param );
	}

	public static void loadMethods() throws NoSuchMethodException
	{

		if (buClass != null)
		{
			MethodProfile profile = new MethodProfile
			(
					PUBLIC | ABSTRACT | EXACT ,
					new ClassItem(void.class),

					new ClassItem("" , PUBLIC | EXACT ),
					new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
					new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
					new ClassItem(int.class)

			);

			buClass_submitCandidateMethod =  ProfileHelpers.findMostSimilar( profile, buClass.getDeclaredMethods(), buClass);
			DebugTools.logIfMethodProfileMismatch(  buClass_submitCandidateMethod, buClass, profile, "buClass_submitCandidateMethod");


			hpeClass = buClass_submitCandidateMethod.getParameterTypes()[0];	//Some class we need as first param
			hpeClass_constructor = hpeClass.getConstructors()[0];	//No arguments

		}

		if (PriorityPredictionsClassManager.candidatesViewFactory != null)
		{

			//There is another method that's 99% the same as the one we're after, so no fuzzy selection here.
			//If it breaks we just have to update manually.
			//Edit: Aaaaand they changed something. It's okay with most similar though maybe?
			//Edit2: Nope. Lots of changes later we're back. Can tell the method apart now.

			{
				MethodProfile profile = new MethodProfile
				(
						PUBLIC | STATIC | EXACT ,
						new ClassItem(android.view.View.class),

						new ClassItem(android.content.Context.class),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem(android.view.View.class),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | EXACT ),
						new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT )
				);

				PriorityPredictionsClassManager.candidatesViewFactory_getViewMethod = ProfileHelpers.findMostSimilar(	profile, PriorityPredictionsClassManager.candidatesViewFactory.getDeclaredMethods(), PriorityPredictionsClassManager.candidatesViewFactory);

				DebugTools.logIfMethodProfileMismatch(  candidatesViewFactory_getViewMethod, candidatesViewFactory, profile, "candidatesViewFactory_getViewMethod");

			}

			{

				MethodProfile profile = new MethodProfile
				(
						PUBLIC | STATIC | EXACT ,
						new ClassItem(void.class),

						new ClassItem(android.content.Context.class),
						new ClassItem(android.widget.LinearLayout.class),
						new ClassItem(android.view.View.class),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | EXACT ),
						new ClassItem("" , PUBLIC | EXACT )

				);

				PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod = ProfileHelpers.findMostSimilar(
						profile, PriorityPredictionsClassManager.candidatesViewFactory.getDeclaredMethods(), PriorityPredictionsClassManager.candidatesViewFactory);
				DebugTools.logIfMethodProfileMismatch(  candidatesViewFactory_ReturnWrapperClass_GetViewMethod, candidatesViewFactory, profile, "candidatesViewFactory_ReturnWrapperClass_GetViewMethod");

			}





			if (PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod != null)
			{
				PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition = ProfileHelpers.findFirstClassIndex( LinearLayout.class, PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod.getParameterTypes() );
			}
		}

		if (PriorityPredictionsClassManager.candidateViewClass != null)
		{
			PriorityPredictionsClassManager.candidateViewClass_Constructor = PriorityPredictionsClassManager.candidateViewClass.getDeclaredConstructors()[0];
			candidateViewClass_setCandidateMethod = ProfileHelpers.findFirstMethodByName(PriorityPredictionsClassManager.candidateViewClass.getDeclaredMethods(), "setCandidate");
		}

		if (PriorityPredictionsClassManager.candidatesViewFactory_getViewMethod != null && candidateViewClass_Constructor != null)
		{
			getViewMethod_CandidateViewClassConstructorArgPositions = ProfileHelpers.findParameterPositions(candidateViewClass_Constructor.getParameterTypes(), PriorityPredictionsClassManager.candidatesViewFactory_getViewMethod.getParameterTypes());
			getViewMethod_EnumArgPosition = ProfileHelpers.findFirstClassIndex(new ClassItem(Modifiers.ENUM), candidateViewClass_Constructor.getParameterTypes(), null);
			getViewMethod_FloatArgPosition = ProfileHelpers.findFirstClassIndex(new ClassItem(float.class), candidateViewClass_Constructor.getParameterTypes(), null);
			getViewMethod_BooleanArgPosition = ProfileHelpers.findFirstClassIndex(new ClassItem(boolean.class), candidateViewClass_Constructor.getParameterTypes(), null);
		}

		if (PriorityPredictionsClassManager.keyboardFrameClass != null)
		{
			MethodProfile profile = new MethodProfile
			(
					PUBLIC | EXACT ,
					new ClassItem(void.class),

					new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
					new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
					new ClassItem("" , PUBLIC | EXACT ),
					new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
					new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
					new ClassItem("" , PUBLIC | EXACT ),
					new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
					new ClassItem("" , PUBLIC | EXACT ),
					new ClassItem("" , PUBLIC | EXACT ),
					new ClassItem("" , PUBLIC | EXACT )

			);

			PriorityPredictionsClassManager.keyboardFrameClass_setBuMethod = ProfileHelpers.findMostSimilar(
					profile, PriorityPredictionsClassManager.keyboardFrameClass.getDeclaredMethods(), PriorityPredictionsClassManager.keyboardFrameClass);

			DebugTools.logIfMethodProfileMismatch(  keyboardFrameClass_setBuMethod, keyboardFrameClass, profile, "keyboardFrameClass_setBuMethod");
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


		Hooks.logSetRequirement( Hooks.predictionHooks_more,	 "getViewMethod_EnumArgPosition", PriorityPredictionsClassManager.getViewMethod_EnumArgPosition != -1 ) ;

	}

}
