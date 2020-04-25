package com.mayulive.swiftkeyexi.xposed.predictions;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Future;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
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
		

	protected static Class updateCandidateDisplayClass;

	protected static Class UpdateCandidateTaskClass;
	protected static Field UpdateCandidateTaskClass_FutureField ;

	////////////////////////////////

	protected static Class UpdateCandidateGetTopCandidateClass;
	protected static Method UpdateCandidateGetTopCandidateClass_getTopCandidateMethod;


	protected static Class handleCandidateClass;
	protected static Method handleCandidateClass_candidateSelectedMethod;


	protected static Class resultTypeEnum;
	protected static Object resultTypeEnum_flow;
	protected static Object resultTypeEnum_flow_success;
	protected static Object resultTypeEnum_flow_liftoff;


	protected static Class CandidateSourceTypeEnum;
	protected static Object candidateSourceTypeEnum_candidate_bar;

	protected static Class ellipsizeCheckerClass;
	protected static Method ellipsizeCheckerClass_shouldEllipsizeMethod;
	protected static Field ellipsizeCheckerClass_candidateField;



	/////////////
	//Misc
	///////////

	protected static int handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition = 2;

	protected static int handleCandidateClass_CandidateSelectedMethod_sourceTypeEnumArgPosition= 3;

	protected static int updateCandidateDisplayClass_constructor_listArgPosition = 1;
	protected static int updateCandidateDisplayClass_constructor_lastCandidateResultTypeArgPosition = 2;

	protected static int UpdateCandidateTaskClass_getTopCandidateMethod_EnumPosition = 1;

	public static void loadKnownClasses(PackageTree param) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
	{

	}
	public static void loadUnknownClasses(PackageTree param) throws IOException
	{
		resultTypeEnum = ProfileHelpers.loadProfiledClass(PredictionProfiles.get_RESULT_TYPE_ENUM_PROFILE(), param );

		UpdateCandidateTaskClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_CANDIDATE_UPDATER_CLASS_PROFILE(), param );
		UpdateCandidateGetTopCandidateClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_CANDIDATE_UPDATER_GET_TOP_CANDIDATE_CLASS_PROFILE(UpdateCandidateTaskClass, resultTypeEnum), param );

		updateCandidateDisplayClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_UPDATE_CANDIDATE_DISPLAY_CLASS_PROFILE(), param );	//candidates.k

		handleCandidateClass = ProfileHelpers.loadProfiledClass( PredictionProfiles.get_HANDLE_CANDIDATE_CLASS_PROFILE(), param );

		CandidateSourceTypeEnum = ProfileHelpers.loadProfiledClass(PredictionProfiles.get_CANDIDATE_SOURCE_TYPE_ENUN_PROFILE(), param );

		ellipsizeCheckerClass = ProfileHelpers.loadProfiledClass(PredictionProfiles.get_ELIPSIZE_CHECKER_CLASS_PROFILE(), param );

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
			MethodProfile profile = new MethodProfile
			(
					PUBLIC | EXACT ,
					new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

					new ClassItem("" , PUBLIC | EXACT ),
					new ClassItem(resultTypeEnum)

			);

			UpdateCandidateGetTopCandidateClass_getTopCandidateMethod = ProfileHelpers.findMostSimilar(
					profile, UpdateCandidateGetTopCandidateClass.getDeclaredMethods(), UpdateCandidateGetTopCandidateClass);

			DebugTools.logIfMethodProfileMismatch(UpdateCandidateGetTopCandidateClass_getTopCandidateMethod, UpdateCandidateGetTopCandidateClass, profile, "UpdateCandidateGetTopCandidateClass_getTopCandidateMethod");

			if (UpdateCandidateGetTopCandidateClass_getTopCandidateMethod != null && resultTypeEnum != null)
			{
				UpdateCandidateTaskClass_getTopCandidateMethod_EnumPosition = ProfileHelpers.findFirstClassIndex( resultTypeEnum, UpdateCandidateGetTopCandidateClass_getTopCandidateMethod.getParameterTypes() );

			}
		}

		if (handleCandidateClass != null)
		{

			MethodProfile profile = new MethodProfile
			(
					PUBLIC | EXACT ,
					new ClassItem(void.class),

					new ClassItem("" , PUBLIC | EXACT ),
					new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
					new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
					new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
					new ClassItem(int.class)
			);

			handleCandidateClass_candidateSelectedMethod = ProfileHelpers.findMostSimilar(
					profile, handleCandidateClass.getDeclaredMethods(), handleCandidateClass);

			DebugTools.logIfMethodProfileMismatch(  handleCandidateClass_candidateSelectedMethod, handleCandidateClass, profile, "handleCandidateClass_candidateSelectedMethod");

			if (handleCandidateClass_candidateSelectedMethod != null)
			{
				handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition = ProfileHelpers.findFirstClassIndex
						(
							new ClassItem(null, "com.touchtype_fluency.service.candidates.Candidate"),
							handleCandidateClass_candidateSelectedMethod.getParameterTypes(),
							null
						);

				handleCandidateClass_CandidateSelectedMethod_sourceTypeEnumArgPosition = ProfileHelpers.findFirstClassIndex
						(
								new ClassItem(CandidateSourceTypeEnum),
								handleCandidateClass_candidateSelectedMethod.getParameterTypes(),
								null
						);
			}
		}

		if ( ellipsizeCheckerClass != null)
		{
			List<Method> methods = ProfileHelpers.findAllMethodsWithReturnType( boolean.class, ellipsizeCheckerClass.getDeclaredMethods() );
			if (!methods.isEmpty())
			{

				// Not equals method, and should have no params
				// Equals method takes a param, so just check for no params.
				for (Method currentMethod : methods)
				{
					if (  currentMethod.getParameterTypes().length > 0 )
						continue;

					ellipsizeCheckerClass_shouldEllipsizeMethod = currentMethod;
					break;
				}
			}

			ellipsizeCheckerClass_candidateField = ProfileHelpers.findFirstDeclaredFieldWithType( CandidateManager.candidateInterfaceClass,  ellipsizeCheckerClass);
			if (ellipsizeCheckerClass_candidateField != null)
				ellipsizeCheckerClass_candidateField.setAccessible(true);
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

		//elipsize
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_Ellipsize,	 "ellipsizeCheckerClass_shouldEllipsizeMethod", ellipsizeCheckerClass_shouldEllipsizeMethod);
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_Ellipsize,	 "ellipsizeCheckerClass_candidateField", ellipsizeCheckerClass_candidateField);
	}
	


}
