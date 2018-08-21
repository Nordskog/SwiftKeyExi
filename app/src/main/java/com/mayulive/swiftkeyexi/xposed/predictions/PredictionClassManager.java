package com.mayulive.swiftkeyexi.xposed.predictions;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Future;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.Modifiers;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import static com.mayulive.xposed.classhunter.Modifiers.*;

public class PredictionClassManager
{
	private static String LOGTAG = ExiModule.getLogTag(PredictionClassManager.class);

	//All the classes we'll be using will be loaded here and shared
		
	
	/////////////
	//Known classes
	/////////////


	/////////////
	//Unknown classes
	/////////////


	protected static Class updateCandidateDisplayClass;

	protected static Class UpdateCandidateTaskClass;

	protected static Class resultTypeEnum;
	protected static Class CandidateSourceTypeEnum;


	protected static Class handleCandidateClass;


	///////////
	//Fields
	///////////

	protected static Field UpdateCandidateTaskClass_FutureField ;



	///////////
	//Methods
	///////////

	protected static Method UpdateCandidateTaskClass_getTopCandidateMethod;


	protected static Method handleCandidateClass_candidateSelectedMethod;


	//////////
	//Instances
	////////////

	protected static Object resultTypeEnum_flow;
	protected static Object resultTypeEnum_flow_success;
	protected static Object resultTypeEnum_flow_liftoff;



	protected static Object candidateSourceTypeEnum_candidate_bar;

	/////////////
	//Misc
	///////////

	protected static int handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition = 2;

	protected static int updateCandidateDisplayClass_constructor_listArgPosition = 1;
	protected static int updateCandidateDisplayClass_constructor_lastCandidateResultTypeArgPosition = 2;

	protected static int UpdateCandidateTaskClass_getTopCandidateMethod_EnumPosition = 1;

	public static void loadKnownClasses(PackageTree param) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
	{

	}
	public static void loadUnknownClasses(PackageTree param) throws IOException
	{
		UpdateCandidateTaskClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_CANDIDATE_UPDATER_CLASS_PROFILE(), param );	//candidates.k

		updateCandidateDisplayClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_UPDATE_CANDIDATE_DISPLAY_CLASS_PROFILE(), param );	//candidates.k

		handleCandidateClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_HANDLE_CANDIDATE_CLASS_PROFILE(), param );
		resultTypeEnum = ProfileHelpers.loadProfiledClass(PredictionProfiles.get_RESULT_TYPE_ENUM_PROFILE(), param );


		CandidateSourceTypeEnum = ProfileHelpers.loadProfiledClass(PredictionProfiles.get_CANDIDATE_SOURCE_TYPE_ENUN_PROFILE(), param );


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

		if (CandidateSourceTypeEnum != null)
		{
			candidateSourceTypeEnum_candidate_bar = CodeUtils.findEnumByName( (Enum[]) CandidateSourceTypeEnum.getEnumConstants(), "CANDIDATE_BAR");
		}

	}

	public static void loadMethods()
	{

		if (UpdateCandidateTaskClass != null)
		{
			UpdateCandidateTaskClass_getTopCandidateMethod = ProfileHelpers.findMostSimilar(			new MethodProfile
					(
							FINAL | EXACT ,
							new ClassItem(java.util.List.class),

							new ClassItem("" , PUBLIC | EXACT ),
							new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
							new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
							new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

					), UpdateCandidateTaskClass.getDeclaredMethods(), UpdateCandidateTaskClass);

			if (UpdateCandidateTaskClass_getTopCandidateMethod != null && resultTypeEnum != null)
			{
				UpdateCandidateTaskClass_getTopCandidateMethod_EnumPosition = ProfileHelpers.findFirstClassIndex( resultTypeEnum, UpdateCandidateTaskClass_getTopCandidateMethod.getParameterTypes() );

			}
		}

		if (handleCandidateClass != null)
		{

			handleCandidateClass_candidateSelectedMethod = ProfileHelpers.findMostSimilar(new MethodProfile
							(
									PUBLIC | FINAL | EXACT ,
									new ClassItem(void.class),

									new ClassItem("" , PUBLIC | FINAL | EXACT ),
									new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
									new ClassItem(int.class)

							),
					handleCandidateClass.getDeclaredMethods(), handleCandidateClass);

			if (handleCandidateClass_candidateSelectedMethod != null)
			{
				handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition = ProfileHelpers.findFirstClassIndex
						(
							new ClassItem(null, "com.touchtype_fluency.service.candidates.Candidate"),
							handleCandidateClass_candidateSelectedMethod.getParameterTypes(),
							null
						);
			}
		}



	}

	public static void loadFields()
	{
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
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_priority,	 "handleCandidateClass_candidateSelectedMethod", handleCandidateClass_candidateSelectedMethod);

	}
	


}
