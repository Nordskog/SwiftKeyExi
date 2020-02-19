package com.mayulive.swiftkeyexi.xposed.predictions;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryShortcutItem;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.main.dictionary.CandidatesRecyclerAdapter;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryWordItem;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Roughy on 2/25/2017.
 */

public class PredictionCommons
{

	private static String LOGTAG = ExiModule.getLogTag(PredictionCommons.class);

	protected static Object mLastCandidateResultType = null;


	protected static boolean mMakeLiteralPrimarySuggestionRequested = false;
	protected static boolean mMakeLiteralPrimarySuggestionRequestedLastUpdate = false;

	protected static WeakReference<View>[] mSuggestionsPadding = (WeakReference<View>[] ) new WeakReference[2];

	protected static long mLastUpdateTime = -1;


	protected static Map<String, DB_DictionaryShortcutItem> mShortcutMap = new HashMap<String, DB_DictionaryShortcutItem>();
	protected static CandidatesRecyclerAdapter mCandidatesAdapter = null;
	protected static RecyclerView mCandidatesRecycler = null;
	protected static LinearLayoutManager mCandidatesManager = null;
	protected static ViewGroup mCandidateContainer = null;

	protected static List<Object> mPrevCandidateList = null;

	protected static long mTimeOfLastInsertException = -1;
	protected static int mInsertExceptionCount = 0;

	protected static Object mLastSelectedCandidateBarCandiate;
	protected static Object mLastSelectedCandidateBarCandidateWrapped;

	public static void setSuggestionsPaddingVisibility(boolean visible)
	{
		if ( mSuggestionsPadding[0] != null && mSuggestionsPadding[1] != null)
		{
			View pad1 = mSuggestionsPadding[0].get();
			View pad2 = mSuggestionsPadding[1].get();

			if ( pad1 != null && pad2 != null )
			{
				if (visible)
				{
					pad1.setVisibility(View.VISIBLE);
					pad2.setVisibility(View.VISIBLE);
				}
				else
				{
					pad1.setVisibility(View.GONE);
					pad2.setVisibility(View.GONE);
				}

				//Doesn't update without this
				ViewParent parent = pad1.getParent();
				if (parent != null)
					parent.requestLayout();

			}
		}
	}


	public static boolean shortcutsExist()
	{
		return mShortcutMap != null && !mShortcutMap.isEmpty();
	}

	public static void handleCandidateInsertionHook(List<Object> candidatesList, boolean isOnMainThread)
	{
		try
		{
			if (candidatesList != mPrevCandidateList)
			{
				mPrevCandidateList = candidatesList;

				//PredictionCommons.mLastCandidateResultType = param.args[1];
				CandidateManager.clearSelectedShortcutMap();

				if (candidatesList != null && !candidatesList.isEmpty())
				{
					String mLastInput = CandidateManager.getSubrequestA(candidatesList.get(0));

					if (DebugTools.DEBUG_PREDICTIONS)
					{
						//Chinese 9-pin uses first letter of key hit
						Log.i(LOGTAG, "Prediction meta: "+mLastInput);
					}

					PredictionCommons.insertShortcuts(mLastInput, candidatesList);

					if (PredictionCommons.mMakeLiteralPrimarySuggestionRequestedLastUpdate )
						//if (PredictionCommons.mMakeLiteralPrimarySuggestionRequestedLastUpdate ||  PredictionCommons.mLastCandidateResultType == PredictionsClassManager.resultTypeEnum_flow_success )
						PredictionCommons.makeVerbatimPrimary(candidatesList);
				}
				else
				{
					if (DebugTools.DEBUG_PREDICTIONS)
					{
						Log.i(LOGTAG, "Candidates list null or empty");
					}

				}
			}
			else
			{
				//Log.i(LOGTAG, "Candidates list same as prev");
			}
		}
		catch (Exception ex)
		{
			//It would appear the list handed to us here is also modified somewhere else,
			//and on a separate thread no less. We do intercept this method on the main thread
			//and onre more, but the main thread one should not be called until the other one has finished.
			//Despite this, I have observed an isEmpty() check immediately followed by a crash caused by
			//the list being empty. I have only seen this happen /once/, so it should be very very rare.
			//Log it and keep going. This goes for anything that might go wrong here, really. Won't break swiftkey in any way.

			//Still, if it happens frequently we should probably call it quits.
			// > 5 occurences within 10 seconds probably means we're toast.
			long currentTime = System.currentTimeMillis();
			if ( currentTime - PredictionCommons.mTimeOfLastInsertException < 10000)	//10 seconds
				PredictionCommons.mInsertExceptionCount++;
			else
				PredictionCommons.mInsertExceptionCount = 0;	//Reset counter

			PredictionCommons.mTimeOfLastInsertException = currentTime;

			if (PredictionCommons.mInsertExceptionCount > 5)
			{
				Hooks.predictionHooks_base.invalidate(ex, "Error in handleCandidateInsertionHook. 5 exceptions in less than 10 seconds, probably completely broken.");
			}
			else
			{
				Log.i(LOGTAG, "Exception in handleCandidateInsertionHook. Not usually a problem.");
				ex.printStackTrace();
			}
		}

		//Update candidate view
		if (isOnMainThread)
		{
			if (mCandidatesRecycler != null)
				mCandidatesRecycler.scrollToPosition(0);

			if (PredictionCommons.mCandidatesAdapter != null)
			{
				if (!Settings.MORE_SUGGESTIONS_ENABLED)
				{
					//Log.e("###", "Updating more");
					if ( !PredictionCommons.mCandidatesAdapter.getArray().isEmpty() )
					{
						PredictionCommons.mCandidatesAdapter.getArray().clear();
						PredictionCommons.mCandidatesAdapter.notifyDataSetChanged();
					}
				}
				else
				{
					int existingItemCount = PredictionCommons.mCandidatesAdapter.getArray().size();

					if (existingItemCount > 0)
					{
						PredictionCommons.mCandidatesAdapter.getArray().clear();
						PredictionCommons.mCandidatesAdapter.notifyItemRangeRemoved(1, existingItemCount);
					}


					if (candidatesList.size() > 3)
					{
						PredictionCommons.mCandidatesAdapter.getArray().addAll(candidatesList.subList(3, candidatesList.size()));
						PredictionCommons.mCandidatesAdapter.notifyItemRangeInserted(1, candidatesList.size() - 3);
					}
				}
			}
		}
	}


	public static void requestLiteralPrimarySuggestion(boolean enable)
	{
		mMakeLiteralPrimarySuggestionRequested = enable;
	}

	public static void savePriority()
	{
		if (mLastUpdateTime != -1)
		{
			for ( DB_DictionaryShortcutItem item :  mShortcutMap.values() )
			{
				item.updateIfPriorityGreaterThan(mLastUpdateTime);
			}
		}
	}

	private static DB_DictionaryShortcutItem getKeyItems(String key)
	{
		key = key.toLowerCase();
		return mShortcutMap.get(key);
	}


	//For use with flow_successful
	static void simpleMakeVerbatimPrimary(List<Object> candidates)
	{
		if (candidates.size() > 1)
		{
			Object primCan = candidates.get(0);
			Object secCan = candidates.get(1);

			//Should never be fluency
			if ( CandidateManager.isFluency( primCan ) )
			{
				if (CandidateManager.isVerbatim(secCan))
				{
					candidates.set(0,secCan);
					candidates.set(1, primCan);
				}
			}
		}
	}

	// Like simpleMakeVerbatimPrimary, and just gives up if there is no verbatim
	static void makeVerbatimPrimary( List<Object> candidates)
	{
		if (!candidates.isEmpty())
		{
			Object verbatim = candidates.get(0);

				if ( CandidateManager.isVerbatim(verbatim) )
				{
					return;
				}
				else if (candidates.size() > 1)
				{
					verbatim = candidates.get(1);

					if ( CandidateManager.isVerbatim(verbatim) )
					{
						candidates.remove(1);
						candidates.add(0,verbatim);
					}
				}
		}
		else
		{
			Log.e(LOGTAG, "Tried setting verbatim but list was empty!");
		}
	}

	private static String matchCase(String matchToMe, String matchMe)
	{
		try
		{
			if (matchToMe != null && matchMe != null && !matchToMe.isEmpty() && !matchMe.isEmpty())
			{
				if ( Character.isUpperCase(matchToMe.charAt(0)))
				{
					if ( !Character.isUpperCase(matchMe.charAt(0)))
					{
						return Character.toUpperCase( matchMe.charAt(0) ) + matchMe.substring(1);
					}
				}
				else
				{
					if ( !Character.isLowerCase(matchMe.charAt(0)))
					{
						return Character.toLowerCase( matchMe.charAt(0) ) + matchMe.substring(1);
					}
				}
			}
		}
		catch (Exception ex)
		{
			//I can imagine locale and stuff messing this up, better to let them have the shortcut as-is then
			//Log.e(LOGTAG, "Something went wrong trying to match case");
		}

		return matchMe;
	}

	//Only used by flow ?
	static Object getFirstShortcut(Object candidate, boolean addSpace)
	{
		DB_DictionaryShortcutItem shortcut = getKeyItems( CandidateManager.getCandidateText(candidate) );

		//If secondary, we should insert into flow
		if (shortcut != null && shortcut.get_items().size() > 0	&& !shortcut.getInsertSecondary())
		{
			//In previous versions of swiftkey, a successful flow would commit the suggestion
			//and add a space (trailing separator) afterwards. Later this was changed so that the space is
			//not added until a new flow is started, or the user presses another key.
			//This allows the user to correct the flow suggestions.


			Object newCandidate = null;

			try
			{
				String trailingSep = (String)CandidateManager.candidate_getTrailingSeparatorMethod.invoke(candidate);

				DB_DictionaryWordItem word = shortcut.get_items().get(0);

				String candidateText = CandidateManager.getCandidateText(candidate);
				String wordString = matchCase(  candidateText, word.get_text());
				if (addSpace)
					wordString += trailingSep;

				newCandidate = CandidateManager.getClipboardCandidate(candidate, candidateText, wordString);

				CandidateManager.candidate_setTrailingSeparatorMethod.invoke(newCandidate, trailingSep);

			}
			catch (Exception ex) { ex.printStackTrace(); Log.e(LOGTAG, "Failed to set trailing separator on flow candidate."); }

			return newCandidate;
		}

		return null;

	}

	static List<?> insertShortcuts(String lastInput, List<Object> candidates)
	{

		boolean isSuggestion = false;

		CandidateManager.clearSelectedShortcutMap();

		if ( candidates != null && !candidates.isEmpty() && PredictionCommons.shortcutsExist() )
		{
			try
			{
				Object primaryCandidate = candidates.get(0);

				//Space after a word. Use whatever the primary candidate is using
				String trailingSeparator = (String)CandidateManager.candidate_getTrailingSeparatorMethod.invoke(primaryCandidate);


				DB_DictionaryShortcutItem shortcut = null;

				//After a successful flow, there is a FLOW_SUCCESSFUL event. This comes with candidates, and also a valid lastInptu.
				//We don't want to accidentally trigger on the lastInput, since we are still prediciting the previous flow event.
				if ( PredictionCommons.mLastCandidateResultType != PredictionClassManager.resultTypeEnum_flow_success  )
					shortcut = getKeyItems(lastInput);

				//Beyond this, lastInput is only used to match text case.
				//It may be an empty string, in which case we'll be inserting based on teh candidate.
				if (lastInput == null || lastInput.isEmpty())
					lastInput = CandidateManager.getCandidateText(primaryCandidate);

				if ( (shortcut == null || shortcut.get_items().isEmpty()) )
				{

					if (DebugTools.DEBUG_PREDICTIONS)
					{
						Log.i(LOGTAG,"No shortcuts from input, checking flow and suggestions");
					}

					boolean predictionAllowedIfFlow = (PredictionCommons.mLastCandidateResultType != PredictionClassManager.resultTypeEnum_flow
														&& PredictionCommons.mLastCandidateResultType != PredictionClassManager.resultTypeEnum_flow_success
														&&	PredictionCommons.mLastCandidateResultType != PredictionClassManager.resultTypeEnum_flow_liftoff)
														|| Settings.FLOW_SUGGESTIONS_ENABLED;


					if (DebugTools.DEBUG_PREDICTIONS)
					{
						Log.i(LOGTAG,"Flow allowed: "+predictionAllowedIfFlow);
						Log.i(LOGTAG,"Predictions enabled allowed: "+ Settings.PREDICTION_SUGGESTIONS_ENABLED);
					}

					if ( predictionAllowedIfFlow && Settings.PREDICTION_SUGGESTIONS_ENABLED )
					{
						//If nothing, check if there are any hits for the primary suggestion
						shortcut = getKeyItems(  CandidateManager.getCandidateText(primaryCandidate) );

						if (shortcut != null && !shortcut.get_items().isEmpty())
						{
							//If there are any.
							isSuggestion = true;
						}
						else
						{
							//Log.i(LOGTAG, "No shortcuts for suggestion");
						}
					}
				}


				//Hey, hits!
				if ( shortcut != null &&  !shortcut.get_items().isEmpty() )
				{

					if (DebugTools.DEBUG_PREDICTIONS)
					{
						Log.i(LOGTAG,"Inserting main shortcuts");
					}

					//Check for a verbatim candidate.
					//Pretty much regardless of anything, we want this
					//to remain in position 1.
					Object verbatim = null;
					for (int i = 0; i < 2 &&  i < candidates.size(); i++)
					{
						Object currentCandidate = candidates.get(i);
						if ( CandidateManager.isVerbatim( currentCandidate) )
						{
							verbatim = currentCandidate;
						}
					}


					if (shortcut.getInsertSecondary())
					{


						//Most languages go middle - left - right.
						//Those with extended candidates just go in order.
						//Skip first 2 if normal, only 1 if extended.
						int offset = KeyboardMethods.isLayoutExtendedPredictions() ? 1 : 2;

						//Regardless of anything, the offset may not be higher than existing candidate count
						if (offset > candidates.size())
							offset = candidates.size();

						for (int i = 0; i < shortcut.get_items().size(); i++)
						{
							DB_DictionaryWordItem word = shortcut.get_items().get(i);

							String wordString = matchCase(lastInput, word.get_text());

							Object exiCandidate = CandidateManager.getClipboardCandidate(primaryCandidate, lastInput, wordString, shortcut, word);
							CandidateManager.candidate_setTrailingSeparatorMethod.invoke(exiCandidate,trailingSeparator);

							candidates.add(i+offset, exiCandidate);
						}
					}
					else
					{

						//If there's a verbatim candidate, remove it from
						//wherever it is so we can add it back to pos 1 later.
						//Only applies to non-extended predictions
						if (!KeyboardMethods.isLayoutExtendedPredictions() && verbatim != null)
						{
							candidates.remove(verbatim);
						}

						//We want our new candidates to act like the candidate we are... replacing.
						//If the key we used came from the primary suggestion, us whatever type it is.
						//The primary suggestion may be either fluency or verbatrim, so act accordingly.

						//Otherwise, use the verbatim candidate candidate.
						//If there is no verbatim candidate (strange), use RawtExt candidate.

						//TODO what if there no verbatim, and suggestion doesn't match input?
						if (!isSuggestion && verbatim != null)
						{
							primaryCandidate = verbatim;
						}

						for (int i = 0; i < shortcut.get_items().size(); i++)
						{
							DB_DictionaryWordItem word = shortcut.get_items().get(i);

							String wordString = matchCase(lastInput, word.get_text());

							Object exiCandidate = CandidateManager.getClipboardCandidate(primaryCandidate, lastInput, wordString, shortcut, word);
							CandidateManager.candidate_setTrailingSeparatorMethod.invoke(exiCandidate,trailingSeparator);

							candidates.add(i, exiCandidate);
						}

						//Insert the verbatim candidate back into position 1, ie left side
						if (!KeyboardMethods.isLayoutExtendedPredictions() && verbatim != null)
						{
							candidates.add(1, verbatim);
						}
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				Log.e(LOGTAG, "Failed to insert shortcuts");
			}
		}


		return candidates;
	}
}
