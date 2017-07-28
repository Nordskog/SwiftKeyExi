package com.mayulive.swiftkeyexi.xposed.predictions;

import android.support.annotation.Nullable;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryShortcutItem;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryWordItem;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Roughy on 1/15/2017.
 */




public class CandidateManager
{

	//This... "hook" is comprised entirely of known classes, and should be fairly stable.
	private static String LOGTAG = ExiModule.getLogTag(CandidateManager.class);

	protected static Class verbatimCandidate = null;
	protected static Class candidateInterfaceClass = null;
	protected static Class rawCandidateClass = null;
	protected static Class fluencyCandidateClass = null;
	protected static Class subCandidateClass = null;

	protected static Method candidate_getSubRequestMethod = null;
	protected static Method candidate_setTrailingSeparatorMethod = null;
	protected static Method candidate_getTrailingSeparatorMethod = null;

	//May be null
	protected static Method candidate_getCorrectionSpanReplacementText = null;



	protected static Method subCandidateClass_inputStringMethod = null;


	protected static Constructor rawCandidate_Constructor= null;

	//We instead use System.identityHashCode()
	private static Map<Integer, WrappedCandidate> mWrappedCandidateMap = new HashMap<>();



	//Contains a candidate object. and a parent table and source shortcut key if applicable
	public static class WrappedCandidate
	{
		public Object candidate;
		public DB_DictionaryShortcutItem parent;
		public DB_DictionaryWordItem word;

		public WrappedCandidate(Object candidate, DB_DictionaryShortcutItem parent, DB_DictionaryWordItem word)
		{
			this.candidate = candidate;
			this.parent = parent;
			this.word = word;
		}

		public WrappedCandidate(Object candidate)
		{
			this(candidate, null, null);
		}

		//Fairly fast operation, don't think we need to thread it
		public void updateTime()
		{
			if (parent != null && word != null)
			{
				word.set_priority( System.currentTimeMillis() );
				//parent.get_items().update(word);
				parent.moveToTop(word);
			}
		}

		@Override
		public String toString()
		{
			String string = "";

			if (candidate != null)
			{
				string += "Candidate: "+candidate.toString()+", ";
			}

			if (parent != null)
			{
				string += "word: "+parent.get_key()+", ";
			}

			if (word != null)
			{
				string += "word: "+word.get_text()+", ";
			}

			return string;
		}

	}

	public static String getCandidateText(Object candidate)
	{
		if (candidate == null)
			return "";

		try
		{
			//Candidate toString implementation removed, this added instead.
			if (candidate_getCorrectionSpanReplacementText != null)
				return (String)candidate_getCorrectionSpanReplacementText.invoke(candidate);
		}
		catch (Exception ex)
		{
			return "";
		}

		return candidate.toString();
	}

	public static WrappedCandidate getWrappedCandidate(Object key)
	{
		return mWrappedCandidateMap.get(getHash(key));
	}


	public static void doAllTheThings(ClassLoader classLoader) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException
	{
		candidateInterfaceClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.Candidate", classLoader);
		if (candidateInterfaceClass != null)
		{
			candidate_getSubRequestMethod = ProfileHelpers.firstMethodByName(candidateInterfaceClass.getDeclaredMethods(), "subrequest");
			candidate_getTrailingSeparatorMethod = ProfileHelpers.firstMethodByName(candidateInterfaceClass.getDeclaredMethods(), "getTrailingSeparator");
			candidate_setTrailingSeparatorMethod = ProfileHelpers.firstMethodByName(candidateInterfaceClass.getDeclaredMethods(), "setTrailingSeparator");
			candidate_getCorrectionSpanReplacementText = ProfileHelpers.firstMethodByName(candidateInterfaceClass.getDeclaredMethods(), "getCorrectionSpanReplacementText");
		}


		///////////////////////

		rawCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.RawTextCandidate", classLoader);
		rawCandidate_Constructor = rawCandidateClass.getDeclaredConstructors()[0];

		///////////////////////

		fluencyCandidateClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.FluencyCandidate", classLoader);

		/////////////

		verbatimCandidate = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.VerbatimCandidate", classLoader);



		///////////

		if (candidate_getSubRequestMethod != null)
		{
			subCandidateClass = candidate_getSubRequestMethod.getReturnType();  //XposedHelpers.findClassIfExists("com.touchtype.keyboard.candidates.h", classLoader);
			//Should be the input, methods rarely change order.

			if (subCandidateClass != null)
			{
				subCandidateClass_inputStringMethod = ProfileHelpers.findAllMethodsWithReturnType(String.class, subCandidateClass.getDeclaredMethods()).get(1);
				//subCandidateClass_inputStringMethod = ProfileCommons.firstMethodByName("f", subCandidateClass.getDeclaredMethods());
			}
		}

	}

	public static Integer getHash(Object object)
	{
		//return object.hashCode();
		return System.identityHashCode(object);
	}

	public static Object getOnlyEntry()
	{
		if (getMapSize() == 1)
		{
			return mWrappedCandidateMap.values().iterator().next();
		}

		return null;
	}


	//Manually add a key-candidate pair to the fluency map
	public static void addToMap(Object key, WrappedCandidate value)
	{
		mWrappedCandidateMap.put(getHash(key), value);
	}

	public static void printMap()
	{
		for (Integer key : mWrappedCandidateMap.keySet())
		{
			Log.i(LOGTAG, "Map Key Entry: "+key);
		}
	}


	public static int getMapSize()
	{
		return mWrappedCandidateMap.size();
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

	//Returns a RawCandidate instance, borrowing the subrequest data from an existing candidate
	public static Object getRawInstance(Object wrappedCandidate, String text, boolean addToMap, @Nullable DB_DictionaryShortcutItem parent, @Nullable DB_DictionaryWordItem word)
	{
		try
		{
			Object subRequest = candidate_getSubRequestMethod.invoke(wrappedCandidate);

			Object newCandidate = null;
			if (rawCandidate_Constructor.getParameterTypes().length == 2)
			{
				newCandidate = rawCandidate_Constructor.newInstance(new Object[]{text,subRequest});
			}
			else if ( rawCandidate_Constructor.getParameterTypes().length == 3)
			{
				//To fix a crash I was causing the added a prediction string field to raw candidates ;_;
				newCandidate = rawCandidate_Constructor.newInstance(new Object[]{text,text,subRequest});
			}
			else
			{
				Log.e(LOGTAG, "RawTextCandidate/ Expected param count of 3 "+rawCandidate_Constructor.toString());
				throw new InstantiationException("RawTextCandidate had unexpected number of parameters");
			}

			if (addToMap)
				mWrappedCandidateMap.put(getHash(newCandidate), new WrappedCandidate( wrappedCandidate, parent, word)) ;

			return newCandidate;
		}
		catch( Exception ex)
		{
			Log.e(LOGTAG, "Failed to create RawTextCandidate");
			ex.printStackTrace();

			return wrappedCandidate;
		}
	}

	public static Object getRawInstance(Object wrappedCandidate, String text, boolean addToMap)
	{
		return getRawInstance(wrappedCandidate, text, addToMap, null, null);
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


	public static void clearFluencyMap()
	{
		mWrappedCandidateMap.clear();
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
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "rawCandidateClass", 	rawCandidateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "fluencyCandidateClass", 	fluencyCandidateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "subCandidateClass", 	subCandidateClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidate_getSubRequestMethod", 	candidate_getSubRequestMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidate_getTrailingSeparatorMethod", 	candidate_getTrailingSeparatorMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "candidate_setTrailingSeparatorMethod", 	candidate_setTrailingSeparatorMethod );

		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "subCandidateClass_inputStringMethod", 	subCandidateClass_inputStringMethod );
		Hooks.logSetRequirementFalseIfNull( Hooks.predictionHooks_candidate,	 "rawCandidate_Constructor", 	rawCandidate_Constructor );
	}

}
