package com.mayulive.swiftkeyexi.xposed.predictions;

import android.support.annotation.Nullable;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryShortcutItem;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryWordItem;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.Modifiers;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Roughy on 1/15/2017.
 */




public class CandidateManager
{

private static String LOGTAG = ExiModule.getLogTag(CandidateManager.class);

	protected static Class candidateInterfaceClass = null;
	protected static Method candidate_getCorrectionSpanReplacementText = null;
	protected static Method candidate_sourceMetadataMethod = null;


	protected static Class fluencyCandidateClass = null;
	protected static Method fluencyCandidate_getTrailingSeparatorMethod = null;

	protected static Class contextPromotedFluencyCandidateClass = null;
	protected static Method contextPromotedFluencyCandidate_getTrailingSeparatorMethod = null;

	protected static Class rawTextCandidateClass = null;
	protected static Method rawTextCandidateClass_getTrailingSeparatorMethod = null;

	protected static Class clipboardCandidateClass = null;
	protected static Constructor clipboardCandidate_Constructor = null;
	protected static Method clipboardCandidateClass_getTrailingSeparatorMethod = null;


	protected static Class subCandidateClass = null;
	protected static Field subCandidateClass_inputStringField = null;


	protected static Class tokenClass = null;


	protected static Method candidate_getSubRequestMethod = null;
	protected static Method candidate_setTrailingSeparatorMethod = null;
	protected static Method candidate_getTrailingSeparatorMethod = null;
	protected static Method candidate_getTokensMethod = null;


	protected static Class verbatimCandidate = null;
	protected static Method verbatimCandidate_getTrailingSeparatorMethod = null;

	protected static Class variantCandidateClass = null;
	protected static Method variantCandidateClass_getWrappedMethod = null;

	protected static Class emailAddressCandidateClass = null;
	protected static Method emailAddressCandidateClass_getTrailingSeparatorMethod = null;
	protected static Constructor emailAddressCandidateClass_constructor = null;

	protected static Class CandidateSourceMetadataClass;	// Actually an interface
	protected static Method CandidateSourceMetadataClass_textOriginMethod;

	protected static Class TextOriginEnum;
	protected static Object TextOriginEnum_PREDICTED_BY_EMOJI_FLUENCY_SESSION;


	protected static Method token_staticConstructor = null;

	protected static Map<Integer, SelectedShortcut> mSelectedShortcutMap = new HashMap<>();

	public static class SelectedShortcut
	{
		public DB_DictionaryShortcutItem parent;
		public DB_DictionaryWordItem word;

		public SelectedShortcut(DB_DictionaryShortcutItem parent, DB_DictionaryWordItem word)
		{
			this.parent = parent;
			this.word = word;
		}

		public void updateTime()
		{
			if (parent != null && word != null)
			{
				word.set_priority( System.currentTimeMillis() );
				parent.moveToTop(word);
			}
		}
	}

	public static SelectedShortcut getSelectedShortcut(Object candidate)
	{
		return mSelectedShortcutMap.get( getHash( candidate ));
	}

	public static String getCandidateText(Object candidate)
	{
		String returnString = "";

		if (candidate == null)
			return returnString;
		try
		{
			//Candidate toString implementation removed, this added instead.
			if (candidate_getCorrectionSpanReplacementText != null)
				returnString = (String)candidate_getCorrectionSpanReplacementText.invoke(candidate);
		}
		catch (Exception ex)
		{
			return returnString;
		}

		return returnString;
	}

	// Alas, the emoji have teh same origin as other words.
	// This doesn't work, but kepe around as a reference should we ever need to look up teh rest.
	public static boolean isPredictedEmoji( Object candidate )
	{
		if ( Hooks.predictionHooks_candidateGetTextOrigin.isRequirementsMet() )
		{
			try
			{
				Object metaData = candidate_sourceMetadataMethod.invoke(candidate);
				Object textOrigin = CandidateSourceMetadataClass_textOriginMethod.invoke(metaData);

				Log.i(LOGTAG, "TextOrigin:"+textOrigin.toString());

				return (textOrigin == TextOriginEnum_PREDICTED_BY_EMOJI_FLUENCY_SESSION);
			}
			catch ( Throwable ex )
			{
				Log.e(LOGTAG, "Could not get candidate text origin");
				ex.printStackTrace();
			}

			return false;

		}
		else
		{
			// Can't tell, always false.
			return false;
		}
	}

	public static void doAllTheThings(PackageTree param) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException
	{
		ClassLoader classLoader = param.getClassLoader();

		candidateInterfaceClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.Candidate", classLoader);
		if (candidateInterfaceClass != null)
		{
			candidate_getSubRequestMethod = ProfileHelpers.firstMethodByName(candidateInterfaceClass.getDeclaredMethods(), "subrequest");
			candidate_getTrailingSeparatorMethod = ProfileHelpers.firstMethodByName(candidateInterfaceClass.getDeclaredMethods(), "getTrailingSeparator");
			candidate_setTrailingSeparatorMethod = ProfileHelpers.firstMethodByName(candidateInterfaceClass.getDeclaredMethods(), "setTrailingSeparator");
			candidate_getCorrectionSpanReplacementText = ProfileHelpers.firstMethodByName(candidateInterfaceClass.getDeclaredMethods(), "getCorrectionSpanReplacementText");
			candidate_getTokensMethod =  ProfileHelpers.firstMethodByName(candidateInterfaceClass.getDeclaredMethods(), "getTokens");
			candidate_sourceMetadataMethod = ProfileHelpers.firstMethodByName(candidateInterfaceClass.getDeclaredMethods(), "sourceMetadata");
		}

		CandidateSourceMetadataClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.CandidateSourceMetadata", classLoader);
		if ( CandidateSourceMetadataClass != null)
		{
			CandidateSourceMetadataClass_textOriginMethod = ProfileHelpers.findFirstMethodByName( CandidateSourceMetadataClass.getDeclaredMethods(), "textOrigin" );
			if (CandidateSourceMetadataClass_textOriginMethod != null)
				CandidateSourceMetadataClass_textOriginMethod.setAccessible(true);
		}

		TextOriginEnum =  ClassHunter.loadClass("com.swiftkey.avro.telemetry.sk.android.TextOrigin", classLoader);
		if (TextOriginEnum != null)
		{
			TextOriginEnum_PREDICTED_BY_EMOJI_FLUENCY_SESSION = ProfileHelpers.findEnumByName( (Enum[])TextOriginEnum.getEnumConstants(), "PREDICTED_BY_EMOJI_FLUENCY_SESSION" );
		}

		fluencyCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.FluencyCandidate", classLoader);

		if (fluencyCandidateClass != null)
		{
			fluencyCandidate_getTrailingSeparatorMethod = ProfileHelpers.firstMethodByName(fluencyCandidateClass.getDeclaredMethods(), "getTrailingSeparator");
		}

		/////////////

		verbatimCandidate = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.VerbatimCandidate", classLoader);

		if (verbatimCandidate != null)
		{
			verbatimCandidate_getTrailingSeparatorMethod = ProfileHelpers.firstMethodByName(verbatimCandidate.getDeclaredMethods(), "getTrailingSeparator");
		}


		///////////////

		clipboardCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.ClipboardCandidate", classLoader);
		if (clipboardCandidateClass == null)
		{
			// Renamed from 7.3.5.18
			clipboardCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.ClipboardShortcutCandidate", classLoader);
		}

		if (clipboardCandidateClass != null)
		{
			clipboardCandidateClass_getTrailingSeparatorMethod = ProfileHelpers.firstMethodByName(verbatimCandidate.getDeclaredMethods(), "getTrailingSeparator");
		}

		/////////////

		if (candidate_getTokensMethod != null)
		{
			tokenClass = (Class)  ( ( ParameterizedType )candidate_getTokensMethod.getGenericReturnType() ).getActualTypeArguments()[0] ;

		}
		else
		{
			Log.i(LOGTAG, "get tokens was null");
		}

		//////////////


		emailAddressCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.EmailAddressCandidate", classLoader);

		if (emailAddressCandidateClass != null)
		{
			emailAddressCandidateClass_getTrailingSeparatorMethod = ProfileHelpers.firstMethodByName(emailAddressCandidateClass.getDeclaredMethods(), "getTrailingSeparator");
			{
				Constructor[] constructors = emailAddressCandidateClass.getDeclaredConstructors();
				if (constructors.length > 0)
				{
					Constructor constructor = constructors[0];

					Class<?>[] parameters = constructor.getParameterTypes();

					if (parameters.length == 2)
					{
						if ( parameters[0] == CandidateManager.candidateInterfaceClass && parameters[1] == String.class )
						{
							emailAddressCandidateClass_constructor = constructor;
						}
						else
							Log.e(LOGTAG, "emailAddressCandidateClass: constructor param mismatch "+constructor.toString());
					}
					else
						Log.e(LOGTAG, "emailAddressCandidateClass: constructor param length not 2: "+constructor.toString());
				}
				else
					Log.e(LOGTAG, "emailAddressCandidateClass: constructor array empty");
			}
		}

		///////////

		contextPromotedFluencyCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.ContextPromotedFluencyCandidate", classLoader);

		if (contextPromotedFluencyCandidateClass != null)
		{
			contextPromotedFluencyCandidate_getTrailingSeparatorMethod = ProfileHelpers.firstMethodByName(contextPromotedFluencyCandidateClass.getDeclaredMethods(), "getTrailingSeparator");
		}

		///////////

		rawTextCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.RawTextCandidate", classLoader);

		if (rawTextCandidateClass != null)
		{
			rawTextCandidateClass_getTrailingSeparatorMethod = ProfileHelpers.firstMethodByName(rawTextCandidateClass.getDeclaredMethods(), "getTrailingSeparator");
		}

		///////////

		variantCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.VariantCandidate", classLoader);

		if (variantCandidateClass != null)
		{
			variantCandidateClass_getWrappedMethod = ProfileHelpers.firstMethodByName(variantCandidateClass.getDeclaredMethods(), "getWrapped");
			if (variantCandidateClass_getWrappedMethod != null)
			{
				variantCandidateClass_getWrappedMethod.setAccessible(true);
			}
			else
			{
				Log.e(LOGTAG, "variantCandidateClass_getWrappedMethod null");
			}
		}

		///////////

		if (candidate_getSubRequestMethod != null)
		{
			subCandidateClass = candidate_getSubRequestMethod.getReturnType();
			//Should be the input, methods rarely change order.

			if (subCandidateClass != null)
			{
				subCandidateClass_inputStringField = ProfileHelpers.findAllDeclaredFieldsWithType(String.class, subCandidateClass).get(1);
				subCandidateClass_inputStringField.setAccessible(true);
			}
		}

		if (clipboardCandidateClass != null)
		{
			clipboardCandidate_Constructor = clipboardCandidateClass.getDeclaredConstructors()[0];	//Only has a single constructor
		}

		if (tokenClass != null)
		{

			MethodProfile profile = new MethodProfile
			(
					Modifiers.PUBLIC | Modifiers.STATIC | Modifiers.EXACT ,
					new ClassItem( Modifiers.THIS  ),

					new ClassItem(java.lang.String.class)
			);

			token_staticConstructor = ProfileHelpers.findMostSimilar(profile, tokenClass.getDeclaredMethods(), tokenClass);

			DebugTools.logIfProfileMismatch(  token_staticConstructor, tokenClass, profile, "token_staticConstructor");
		}
	}

	//Get separator
	public static String getTrailingSeparator(Object candidate)
	{
		if (candidate == null)
			return "";

		String trailingSep = "";
		try {	trailingSep = (String)CandidateManager.candidate_getTrailingSeparatorMethod.invoke(candidate); }
		catch (Exception ex) { return ""; }

		return trailingSep == null ? "" : trailingSep;
	}

	public static boolean hasTrailingSeparator(Object candidate)
	{
		if (candidate == null)
			return false;

		String trailingSep = "";
		try {	trailingSep = (String)CandidateManager.candidate_getTrailingSeparatorMethod.invoke(candidate); }
		catch (Exception ex) { return false; }

		return trailingSep == null ? false : !trailingSep.isEmpty();
	}

	public static Object getToken(String text)
	{
		Object token = null;

		try
		{
			token = token_staticConstructor.invoke(null, new Object[]{text});
		}
		catch ( IllegalAccessException | InvocationTargetException e)
		{
			e.printStackTrace();
		}

		return token;
	}

	//Returns a RawCandidate instance, borrowing the subrequest data from an existing candidate
	public static Object getClipboardCandidate(Object candidate, String shortcut, String text, @Nullable DB_DictionaryShortcutItem parent, @Nullable DB_DictionaryWordItem word)
	{
		try
		{
			//Borrow a subrequest
			Object subRequest = candidate_getSubRequestMethod.invoke(candidate);

			//Create a token
			ArrayList<Object> tokens = new ArrayList<>();
			tokens.add( getToken(text) );

			Object clipboardShortcut = clipboardCandidate_Constructor.newInstance(text, shortcut, tokens, subRequest);

			if (parent != null & word != null)
			{
				addToSelectedShortcutMap(clipboardShortcut, parent, word);
			}

			return clipboardShortcut;
		}
		catch( Exception ex)
		{
			Log.e(LOGTAG, "Failed to create ClipboardCandidate");
			ex.printStackTrace();

			return candidate;
		}
	}

	public static Object getClipboardCandidate(Object candidate, String shortcut, String text)
	{
		return getClipboardCandidate(candidate, shortcut, text, null, null);
	}


	public static String getSubrequestA(Object candidate)
	{
		try
		{
			Object subrequest = candidate_getSubRequestMethod.invoke(candidate);
			return (String) subCandidateClass_inputStringField.get(subrequest);
		}
		catch (IllegalAccessException | InvocationTargetException e)
		{
			e.printStackTrace();
			return "";
		}

	}

	public static Integer getHash(Object object)
	{
		//On android this returns the native pointer to the object,
		//Or at least it did back in kitkat.
		return System.identityHashCode(object);
	}

	public static void addToSelectedShortcutMap(Object clipboardShortcut, DB_DictionaryShortcutItem parent, DB_DictionaryWordItem word)
	{
		mSelectedShortcutMap.put( getHash(clipboardShortcut), new SelectedShortcut(parent,word) );
	}

	public static void clearSelectedShortcutMap()
	{
		mSelectedShortcutMap.clear();
	}

	public static boolean isFluency(Object candidate)
	{
		return candidate.getClass() == fluencyCandidateClass;
	}

	public static boolean isVerbatim(Object candidate)
	{
		if (candidate == null)
			return false;
		return candidate.getClass() == verbatimCandidate;
	}

	public static boolean isVariant(Object candidate)
	{
		if (candidate == null)
			return false;
		return candidate.getClass() == variantCandidateClass;
	}

	public static Object getVariantWrapped(Object candidate)
	{
		if (candidate == null)
			return false;

		if ( variantCandidateClass_getWrappedMethod == null )
			return false;

		Object wrappedCandidate = null;

		try
		{
			wrappedCandidate = variantCandidateClass_getWrappedMethod.invoke(candidate);
		}
		catch ( Exception ex )
		{
			Log.e(LOGTAG, "Problem getting variant wrapped candidate");
			ex.printStackTrace();
		}

		return wrappedCandidate;
	}


	protected static void updateDependencyState()
	{
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "verbatimCandidate", 	verbatimCandidate );

		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidateInterfaceClass", 	candidateInterfaceClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "fluencyCandidateClass", 	fluencyCandidateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "subCandidateClass", 	subCandidateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidate_getSubRequestMethod", 	candidate_getSubRequestMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidate_getTrailingSeparatorMethod", 	candidate_getTrailingSeparatorMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidate_setTrailingSeparatorMethod", 	candidate_setTrailingSeparatorMethod );

		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "subCandidateClass_inputStringMethod", 	subCandidateClass_inputStringField );

		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "tokenClass", 	tokenClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "clipboardCandidateClass", 	clipboardCandidateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "clipboardCandidate_Constructor", 	clipboardCandidate_Constructor );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "token_staticConstructor", 	token_staticConstructor );

		// Whether we can tell if a candidate is a predicted emoji
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidateGetTextOrigin,	 "candidate_sourceMetadataMethod", 	candidate_sourceMetadataMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidateGetTextOrigin,	 "CandidateSourceMetadataClass_textOriginMethod", 	CandidateSourceMetadataClass_textOriginMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidateGetTextOrigin,	 "TextOriginEnum_PREDICTED_BY_EMOJI_FLUENCY_SESSION", TextOriginEnum_PREDICTED_BY_EMOJI_FLUENCY_SESSION );

		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_EllipsizeMailAddress,	 "token_staticConstructor", 	emailAddressCandidateClass_constructor );
	}

}
