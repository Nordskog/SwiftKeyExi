package com.mayulive.swiftkeyexi.xposed.predictions;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Future;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.LoadPackageHook;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.Modifiers;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import android.app.Dialog;
import android.util.Log;
import android.widget.LinearLayout;

import de.robv.android.xposed.XposedHelpers;

import static com.mayulive.xposed.classhunter.Modifiers.*;

public class PredictionClassManager
{
	private static String LOGTAG = ExiModule.getLogTag(PredictionClassManager.class);

	//All the classes we'll be using will be loaded here and shared
		
	
	/////////////
	//Known classes
	/////////////

	//protected static Class breadcrumbCandidateWrapperInterfaceClass = null;


	/////////////
	//Unknown classes
	/////////////


	protected static Class updateCandidateDisplayClass;

	protected static Class UpdateCandidateTaskClass;

	protected static Class resultTypeEnum;


	protected static Class handleCandidateClass;

	protected static Class buClass = null;


	protected static Class keyboardFrameClass;

	protected static Class candidateClickCLass;

	protected static Class candidateViewClass;

	protected static Class candidatesViewFactory;


	//protected static Class canAClass;

	///////////
	//Fields
	///////////

	protected static Field UpdateCandidateTaskClass_FutureField ;

	protected static Field keyboardFrameClass_buField;


	//protected static Field breadcrumbCandidateWrapperInterfaceClass_candidateField;

	///////////
	//Methods
	///////////

	protected static Method UpdateCandidateTaskClass_getTopCandidateMethod;

	protected static Constructor candidateClickConstructor;
	protected static Method candidateOnCLickMethod;

	protected static Method keyboardFrameClass_setBuMethod;

	protected static Method candidateSelectedMethod;


	protected static Constructor candidateViewClass_Constructor;

	protected static Method candidateViewClass_setCandidateMethod;

	protected static Method candidatesViewFactory_getViewMethod;

	protected static Method candidatesViewFactory_ReturnWrapperClass_GetViewMethod = null;

	//////////
	//Instances
	////////////

	protected static Object buInstance = null;
	protected static Object resultTypeEnum_flow;
	protected static Object resultTypeEnum_flow_success;
	protected static Object resultTypeEnum_flow_liftoff;

	/////////////
	//Misc
	///////////

	protected static int handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition = 2;

	protected static int updateCandidateDisplayClass_constructor_listArgPosition = 1;
	protected static int updateCandidateDisplayClass_constructor_lastCandidateResultTypeArgPosition = 2;

	protected static int[] getViewMethod_CandidateViewClassConstructorArgPositions;
	protected static int getViewMethod_EnumArgPosition;

	protected static int UpdateCandidateTaskClass_getTopCandidateMethod_EnumPosition = 1;
	protected static int UpdateCandidateTaskClass_getTopCandidateMethod_intPosition = 4;

	protected static int candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition = 1;

	public static void loadKnownClasses(PackageTree param) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
	{
		CandidateManager.doAllTheThings(param);
		CandidateManager.updateDependencyState();

		keyboardFrameClass = ClassHunter.loadClass("com.touchtype.keyboard.view.frames.KeyboardFrame", param.getClassLoader());
	}
	public static void loadUnknownClasses(PackageTree param) throws IOException
	{
		//canAClass = ProfileHelpers.loadProfiledClass( CAN_A_CLASS_PROFILE, param );

		UpdateCandidateTaskClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_CANDIDATE_UPDATER_CLASS_PROFILE(), param );	//candidates.k

		updateCandidateDisplayClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_UPDATE_CANDIDATE_DISPLAY_CLASS_PROFILE(), param );	//candidates.k

		handleCandidateClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_HANDLE_CANDIDATE_CLASS_PROFILE(), param );
		resultTypeEnum = ProfileHelpers.loadProfiledClass(PredictionProfiles.get_RESULT_TYPE_ENUM_PROFILE(), param );

		buClass = ProfileHelpers.loadProfiledClass(PredictionProfiles.get_BU_CLASS_PROFILE(), param );
		candidateClickCLass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_CANDIDATE_CLICK_CLASS_PROFILE(), param );

		candidateViewClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_CANDIDATE_VIEW_CLASS_PROFILE(), param );

		//Targets >= 6.6.7.24
		candidatesViewFactory = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_CANDIDATES_VIEW_FACTORY_CLASS_PROFILE(), param );

		if (updateCandidateDisplayClass != null)
		{
			Constructor constructor = updateCandidateDisplayClass.getDeclaredConstructors()[0];	//Single constructor

			updateCandidateDisplayClass_constructor_listArgPosition = ProfileHelpers.findFirstClassIndex(List.class, constructor.getParameterTypes());
			updateCandidateDisplayClass_constructor_lastCandidateResultTypeArgPosition = ProfileHelpers.findFirstProfileMatchIndex(new ClassItem(Modifiers.ENUM), constructor.getParameterTypes(), null);

		}


	}

	public static void loadObjects()
	{

		if (resultTypeEnum != null)
		{
			resultTypeEnum_flow = CodeUtils.findEnumByName( (Enum[]) resultTypeEnum.getEnumConstants(), "FLOW");
			resultTypeEnum_flow_success = CodeUtils.findEnumByName( (Enum[]) resultTypeEnum.getEnumConstants(), "FLOW_SUCCEEDED");
			resultTypeEnum_flow_liftoff = CodeUtils.findEnumByName( (Enum[]) resultTypeEnum.getEnumConstants(), "FLOW_LIFT_OFF");
		}
	}

	public static void loadMethods()
	{

		if (UpdateCandidateTaskClass != null)
		{
			UpdateCandidateTaskClass_getTopCandidateMethod = ProfileHelpers.findMostSimilar(			new MethodProfile
					(
							PRIVATE | EXACT ,
							new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

							new ClassItem("com.touchtype.telemetry.Breadcrumb" , PUBLIC | EXACT ),
							new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | EXACT ),
							new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
							new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
							new ClassItem(int.class)

					), UpdateCandidateTaskClass.getDeclaredMethods(), UpdateCandidateTaskClass);

			if (UpdateCandidateTaskClass_getTopCandidateMethod != null && resultTypeEnum != null)
			{
				UpdateCandidateTaskClass_getTopCandidateMethod_EnumPosition = ProfileHelpers.findFirstClassIndex( resultTypeEnum, UpdateCandidateTaskClass_getTopCandidateMethod.getParameterTypes() );

				UpdateCandidateTaskClass_getTopCandidateMethod_intPosition= ProfileHelpers.findFirstClassIndex( int.class, UpdateCandidateTaskClass_getTopCandidateMethod.getParameterTypes() );
			}
		}


		if (keyboardFrameClass != null)
		{
			keyboardFrameClass_setBuMethod = ProfileHelpers.findMostSimilar(new MethodProfile
					(
							PUBLIC | EXACT ,
							new ClassItem(void.class),

							new ClassItem("com.touchtype.keyboard." , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
							new ClassItem("com.touchtype.telemetry." , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
							new ClassItem("com.touchtype.keyboard." , PUBLIC | EXACT ),
							new ClassItem("com.touchtype.keyboard." , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
							new ClassItem("com.touchtype.keyboard." , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
							new ClassItem("com.touchtype.util." , PUBLIC | EXACT ),
							new ClassItem("com.touchtype.keyboard." , PUBLIC | INTERFACE | ABSTRACT | EXACT )
					),
					keyboardFrameClass.getDeclaredMethods(), keyboardFrameClass);
		}

		if (candidateClickCLass != null)
		{
			candidateClickConstructor = candidateClickCLass.getDeclaredConstructors()[0];


			candidateOnCLickMethod = ProfileHelpers.findMostSimilar(new MethodProfile
							(
									PUBLIC | EXACT ,
									new ClassItem(void.class),

									new ClassItem(android.view.View.class),
									new ClassItem(null, "com.touchtype_fluency.service.candidates.Candidate" ),
									new ClassItem(int.class)
							),
					candidateClickCLass.getDeclaredMethods(), candidateClickCLass);
		}

		if (handleCandidateClass != null)
		{

			candidateSelectedMethod = ProfileHelpers.findMostSimilar(new MethodProfile
					(
							PUBLIC | EXACT ,
							new ClassItem(void.class),

							new ClassItem("com.touchtype.keyboard." , PUBLIC | EXACT ),
							new ClassItem(null, "com.touchtype_fluency.service.candidates.Candidate"),
							new ClassItem("com.touchtype.keyboard." , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
							new ClassItem("com.touchtype.keyboard." , PUBLIC | FINAL | ENUM | EXACT ),
							new ClassItem(int.class),
							new ClassItem("com.touchtype.telemetry.Breadcrumb" , PUBLIC | EXACT )

					),
					handleCandidateClass.getDeclaredMethods(), handleCandidateClass);

			if (candidateSelectedMethod != null)
			{
				handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition = ProfileHelpers.findFirstClassIndex
						(
							new ClassItem(null, "com.touchtype_fluency.service.candidates.Candidate"),
							candidateSelectedMethod.getParameterTypes(),
							null
						);
			}
		}

		if (candidateViewClass != null)
		{
			candidateViewClass_Constructor = candidateViewClass.getDeclaredConstructors()[0];
			candidateViewClass_setCandidateMethod = XposedHelpers.findMethodExact(candidateViewClass, "setCandidate", CandidateManager.candidateInterfaceClass);
		}


		if (candidatesViewFactory != null)
		{

			//There is another method that's 99% the same as the one we're after, so no fuzzy selection here.
			//If it breaks we just have to update manually.
			//Edit: Aaaaand they changed something. It's okay with most similar though maybe?
			//Edit2: Nope. Lots of changes later we're back. Can tell the method apart now.

			candidatesViewFactory_getViewMethod = ProfileHelpers.findMostSimilar(		new MethodProfile
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
					candidatesViewFactory.getDeclaredMethods(), candidatesViewFactory);

			candidatesViewFactory_ReturnWrapperClass_GetViewMethod = ProfileHelpers.findMostSimilar(		new MethodProfile
							(
									STATIC | EXACT ,
									new ClassItem(void.class),

									new ClassItem(android.content.Context.class),
									new ClassItem(android.widget.LinearLayout.class),
									new ClassItem(android.view.View.class),
									new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype.keyboard.view" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT )
							),
					candidatesViewFactory.getDeclaredMethods(), candidatesViewFactory);



			if (candidatesViewFactory_ReturnWrapperClass_GetViewMethod != null)
			{
				candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition = ProfileHelpers.findFirstClassIndex( LinearLayout.class, candidatesViewFactory_ReturnWrapperClass_GetViewMethod.getParameterTypes() );
			}
		}

		if (candidatesViewFactory_getViewMethod != null && candidateViewClass_Constructor != null)
		{
			getViewMethod_CandidateViewClassConstructorArgPositions = ProfileHelpers.findParameterPositions(candidateViewClass_Constructor.getParameterTypes(), candidatesViewFactory_getViewMethod.getParameterTypes());
			getViewMethod_EnumArgPosition = ProfileHelpers.findFirstClassIndex(new ClassItem(Modifiers.ENUM), candidateViewClass_Constructor.getParameterTypes(), null);
		}


	}

	public static void loadFields()
	{
		if (keyboardFrameClass != null)
		{
			keyboardFrameClass_buField = ProfileHelpers.findFirstDeclaredFieldWithType(buClass, keyboardFrameClass);
			if (keyboardFrameClass_buField != null)
				keyboardFrameClass_buField.setAccessible(true);
		}

		if (UpdateCandidateTaskClass != null)
		{
			UpdateCandidateTaskClass_FutureField  = ProfileHelpers.findFirstDeclaredFieldWithType(Future.class, UpdateCandidateTaskClass );
			if (UpdateCandidateTaskClass_FutureField != null)
				UpdateCandidateTaskClass_FutureField .setAccessible(true);
		}

	}

	public static void doAllTheThings(PackageTree param) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, NoSuchFieldException
	{
		loadKnownClasses(param);
		loadUnknownClasses(param);
		loadMethods();
		loadFields();
		loadObjects();




		updateDependencyState();
	}


	protected static void updateDependencyState()
	{


		//Too many classes, too many methods.

		//Suggestions
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_base,	 "resultTypeEnum", 	resultTypeEnum );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_base,	 "handleCandidateClass", 	handleCandidateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_base,	 "resultTypeEnum_flow", 	resultTypeEnum_flow );
		Hooks.logSetRequirement( Hooks.predictionHooks_base,	 "handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition", 	handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition != -1 ) ;
		Hooks.logSetRequirement( Hooks.predictionHooks_base,	 "updateCandidateDisplayClass_constructor_listArgPosition", 	updateCandidateDisplayClass_constructor_listArgPosition != -1 ) ;
		Hooks.logSetRequirement( Hooks.predictionHooks_base,	 "updateCandidateDisplayClass_constructor_lastCandidateResultTypeArgPosition ", 	updateCandidateDisplayClass_constructor_lastCandidateResultTypeArgPosition  != -1 ) ;

		//Priority update
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_priority,	 "candidateSelectedMethod", 	candidateSelectedMethod );

		//More suggestions
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "keyboardFrameClass", 	keyboardFrameClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidateViewClass", 	candidateViewClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidatesViewFactory", 	candidatesViewFactory );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidatesViewFactory_getViewMethod", 	candidatesViewFactory_getViewMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidateViewClass_Constructor", 	candidateViewClass_Constructor );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidateViewClass_setCandidateMethod", 	candidateViewClass_setCandidateMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidateClickConstructor", 	candidateClickConstructor );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidateOnCLickMethod", 	candidateOnCLickMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "keyboardFrameClass_setBuMethod", 	keyboardFrameClass_setBuMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "candidateClickCLass", 	candidateClickCLass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_more,	 "buClass", 	buClass );


		Hooks.logSetRequirement( Hooks.predictionHooks_more,	 "getViewMethod_EnumArgPosition", getViewMethod_EnumArgPosition != -1 ) ;
	}
	


}
