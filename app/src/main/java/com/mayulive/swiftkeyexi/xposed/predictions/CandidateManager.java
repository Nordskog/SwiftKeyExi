package com.mayulive.swiftkeyexi.xposed.predictions;

import android.support.annotation.Nullable;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryShortcutItem;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryWordItem;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.Modifiers;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by Roughy on 1/15/2017.
 */




public class CandidateManager
{

	private static String LOGTAG = ExiModule.getLogTag(CandidateManager.class);

	protected static Class verbatimCandidate = null;
	protected static Class candidateInterfaceClass = null;

	protected static Class fluencyCandidateClass = null;
	protected static Class clipboardCandidateClass = null;
	protected static Class subCandidateClass = null;

	protected static Class tokenClass = null;

	protected static Method candidate_getSubRequestMethod = null;
	protected static Method candidate_setTrailingSeparatorMethod = null;
	protected static Method candidate_getTrailingSeparatorMethod = null;

	//May be null
	protected static Method candidate_getCorrectionSpanReplacementText = null;

	protected static Method subCandidateClass_inputStringMethod = null;

	//Takes text, shortcut, list of token(text), sburequest
	protected static Constructor clipboardCandidate_Constructor = null;

	protected static Method clipboardCandidate_shouldEllipsizeMethod = null;

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
		}

		fluencyCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.FluencyCandidate", classLoader);

		/////////////

		verbatimCandidate = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.VerbatimCandidate", classLoader);


		///////////////

		clipboardCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.ClipboardCandidate", classLoader);

		/////////////


		tokenClass = ProfileHelpers.loadProfiledClass(CandidateProfiles.get_PREDICTION_TOKEN_PROFILE(), param);

		///////////

		if (candidate_getSubRequestMethod != null)
		{
			subCandidateClass = candidate_getSubRequestMethod.getReturnType();
			//Should be the input, methods rarely change order.

			if (subCandidateClass != null)
			{
				subCandidateClass_inputStringMethod = ProfileHelpers.findAllMethodsWithReturnType(String.class, subCandidateClass.getDeclaredMethods()).get(1);
			}
		}

		if (clipboardCandidateClass != null)
		{
			clipboardCandidate_Constructor = clipboardCandidateClass.getDeclaredConstructors()[0];	//Only has a single constructor
			clipboardCandidate_shouldEllipsizeMethod = ProfileHelpers.findFirstMethodByName(clipboardCandidateClass.getDeclaredMethods(), "shouldEllipsize");
		}

		if (tokenClass != null)
		{
			token_staticConstructor = ProfileHelpers.findMostSimilar(new MethodProfile
							(
									Modifiers.PUBLIC | Modifiers.STATIC | Modifiers.EXACT ,
									new ClassItem( Modifiers.THIS  ),

									new ClassItem(java.lang.String.class)
							),
					tokenClass.getDeclaredMethods(), tokenClass);
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
			Log.e(LOGTAG, "Failed to create RawTextCandidate");
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
			return (String) subCandidateClass_inputStringMethod.invoke(subrequest);
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


	protected static void updateDependencyState()
	{
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "verbatimCandidate", 	verbatimCandidate );

		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidateInterfaceClass", 	candidateInterfaceClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "fluencyCandidateClass", 	fluencyCandidateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "subCandidateClass", 	subCandidateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidate_getSubRequestMethod", 	candidate_getSubRequestMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidate_getTrailingSeparatorMethod", 	candidate_getTrailingSeparatorMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidate_setTrailingSeparatorMethod", 	candidate_setTrailingSeparatorMethod );

		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "subCandidateClass_inputStringMethod", 	subCandidateClass_inputStringMethod );

		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "tokenClass", 	tokenClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "clipboardCandidateClass", 	clipboardCandidateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "clipboardCandidate_Constructor", 	clipboardCandidate_Constructor );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "token_staticConstructor", 	token_staticConstructor );

		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "clipboardCandidate_shouldEllipsizeMethod", 	clipboardCandidate_shouldEllipsizeMethod );

	}

}
