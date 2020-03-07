package com.mayulive.swiftkeyexi.xposed.predictions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.main.dictionary.CandidatesRecyclerAdapter;
import com.mayulive.swiftkeyexi.main.dictionary.SlowRecyclerView;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import de.robv.android.xposed.XC_MethodHook;

/**
 * Created by Roughy on 6/7/2017.
 */

public class PredictionHandlers
{

	private static String LOGTAG = ExiModule.getLogTag(PredictionHandlers.class);

	public static void handleCandidateSelectedHook(XC_MethodHook.MethodHookParam param)
	{
		Object candidate = param.args[PredictionClassManager.handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition];

		if (Settings.DISABLE_PREDICTION_AUTO_SPACE)
		{
			// Keep track of candidates selected via the candidate bar so we can remove their spaces, if enabled.
			Object candidateSource = param.args[PredictionClassManager.handleCandidateClass_CandidateSelectedMethod_sourceTypeEnumArgPosition];

			if ( candidateSource == PredictionClassManager.candidateSourceTypeEnum_candidate_bar )
			{
				PredictionCommons.mLastSelectedCandidateBarCandidateWrapped = null;	// Always reset
				PredictionCommons.mLastSelectedCandidateBarCandiate = candidate;

				// When a variant candidate ( multi-race emoji ) passes through, what is actually used later is the wrapped fluency candidate.
				if ( CandidateManager.isVariant(candidate) )
				{
					PredictionCommons.mLastSelectedCandidateBarCandidateWrapped = CandidateManager.getVariantWrapped(candidate);
				}
			}
			else
			{
				// They certainly don't reuse candidate objects but let's make sure.
				PredictionCommons.mLastSelectedCandidateBarCandiate = null;
				PredictionCommons.mLastSelectedCandidateBarCandidateWrapped = null;	// Always reset
			}

		}


		//Check if inserted shortcut
		CandidateManager.SelectedShortcut selectedShortcut = CandidateManager.getSelectedShortcut(candidate);
		if (selectedShortcut != null)
		{
			//Update priority
			selectedShortcut.updateTime();
		}
	}

	static private Object[] candidateViewArgs = null;

	public static void handleCandidateViewHook_parameters(XC_MethodHook.MethodHookParam param)
	{
		//To create a single candidate view we need 5 args.
		//4 of these are passed to the method we are hooking, while the last
		//is an enum. The enum we're after is "CANDIDATE".
		//As of 6.7.4.31 there is also a bool that switches the min-width between two resources
		candidateViewArgs = new Object[PriorityPredictionsClassManager.candidateViewClass_Constructor.getParameterTypes().length];

		{
			for (int i = 0; i < PriorityPredictionsClassManager.getViewMethod_CandidateViewClassConstructorArgPositions.length; i++)
			{
				if (PriorityPredictionsClassManager.getViewMethod_CandidateViewClassConstructorArgPositions[i] != -1)
				{
					candidateViewArgs[i] = param.args[ PriorityPredictionsClassManager.getViewMethod_CandidateViewClassConstructorArgPositions[i] ];
				}
			}

			Object someEnum =  CodeUtils.findEnumByName( (Enum[])  PriorityPredictionsClassManager.candidateViewClass_Constructor.getParameterTypes()[PriorityPredictionsClassManager.getViewMethod_EnumArgPosition].getEnumConstants(), "CANDIDATE");
			candidateViewArgs[PriorityPredictionsClassManager.getViewMethod_EnumArgPosition] = someEnum;
			if ( PriorityPredictionsClassManager.getViewMethod_BooleanArgPosition != -1)
				candidateViewArgs[ PriorityPredictionsClassManager.getViewMethod_BooleanArgPosition] = false;	//z ? R.dimen.floating_sequential_candidate_min_width : R.dimen.sequential_candidate_min_width);
			if ( PriorityPredictionsClassManager.getViewMethod_FloatArgPosition != -1)
			{
				// Set when we call the constructor
				candidateViewArgs[ PriorityPredictionsClassManager.getViewMethod_FloatArgPosition ] = 0;
			}
		}
	}

	@SuppressLint("MissingPermission")
	public static void handleCandidateViewHook_replace( ViewGroup childFrame)
	{

		if (candidateViewArgs == null)
		{
			Log.e(LOGTAG, "Expeceted candidateViewArgs to be popuplated, cannot create candidate views");
			return;
		}

		//I did consider not adding it for disabled, but then the user would
		//require a reboot/app-restart to re-enable it.
		//If disabled we don't do any of the work required later anyway,
		//so we should be good to just keep this here regardless.

			//if (true)
			{
			//Due to changes, now actually a linear layout wrapping the framelayout and a few other views
			//ViewGroup frameLayout = (ViewGroup)param.getResult();

			//Full and compact layouts are fine. The two-thumb one has a weird split candidate view.
			//It turns out it is actually two separate instances of the candidates view, each calling this method on their own.
			//We could probably could with this somehow, but I'm not sure what we'd do about it.
			//Full and compact both and 3 children now. Two-thumb only has two.  Skip if two.
			if (childFrame.getChildCount() <= 2)
			{
				Log.i(LOGTAG, "Insufficient candidate children, doing nothing");
				return;
			}

			//Oddly enough this is still called multiple times after you open another keyboard.
				//Think it's called for every layout you have accessed in the current session,
				// so we will return above a lot, even when on a compatible layout.


			ViewGroup centerLinear = childFrame;
			{
				childFrame = (ViewGroup)centerLinear.getChildAt(1);

				//Try hiding spacers?
				//Works! Maybe only hide one side like in asian layouts? Hmmm
				if (centerLinear.getChildCount() == 3)
				{
					PredictionCommons.mSuggestionsPadding[0] = new WeakReference<View>( centerLinear.getChildAt(0) );
					PredictionCommons.mSuggestionsPadding[1] = new WeakReference<View>( centerLinear.getChildAt(2) );

					PredictionCommons.setSuggestionsPaddingVisibility(!Settings.REMOVE_SUGGESTIONS_PADDING );
				}

			}

			//if(false)
			{


				final View kView = childFrame;
				centerLinear.removeView(kView);

				ViewGroup.LayoutParams kViewParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

				final LinearLayout headerScroller = new LinearLayout(kView.getContext());
				headerScroller.setLayoutParams(kViewParams);

				//kView is removed from this parent in the beforeHookedMethod hook, as swiftkey expects it to be orphaned.
				headerScroller.addView(kView);


				class dummyHeaderLayout extends FrameLayout
				{

					public dummyHeaderLayout(Context context)
					{
						super(context);
					}

					@Override
					public boolean onTouchEvent(MotionEvent event)
					{
						return kView.dispatchTouchEvent(event);
					}

				}

				final FrameLayout dummyHeader = new dummyHeaderLayout(kView.getContext());
				dummyHeader.setLayoutParams(kViewParams);

				PredictionCommons.mCandidatesAdapter = new CandidatesRecyclerAdapter(kView.getContext())
				{
					@Override
					public int getViewType(int position)
					{
						return 0;
					}

					@Override
					public View getCandidateViewInstance()
					{
						try
						{
							if ( PriorityPredictionsClassManager.getViewMethod_FloatArgPosition  != -1 )
							{
								// Sets the text size of the textpaint. There is a bunch of fancy logic that I have no idea what does,
								// but setting it to a quarter the height of the candidate bar seems okay. At 0 all views are the same small size with
								// the text scaled to fit, and too high of a value and you get cut-off candidates with empty space between them.
								// It seems to function as a sort of max-width
								candidateViewArgs[ PriorityPredictionsClassManager.getViewMethod_FloatArgPosition ] = dummyHeader.getMeasuredHeight() / 4f;

								//We also lose the padding for some reason. Setting padding doesn't do anything. Will just have to deal with it. Doesn't look that bad.

							}

							View returnView = (View) PriorityPredictionsClassManager.candidateViewClass_Constructor.newInstance(candidateViewArgs);

							RecyclerView.LayoutParams candidateParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.MATCH_PARENT);
							returnView.setLayoutParams(candidateParams);

							return returnView;

						}
						catch( Exception ex)
						{
							Log.e( LOGTAG, "Failed to construct candidate view item");
							ex.printStackTrace();
							//Returning a null will crash. Not happy about LinearLayouts, but it just spams logcat.
							return new LinearLayout(headerScroller.getContext());
						}
					}

					@Override
					public View setCandidateViewText(View view, Object source)
					{
						try
						{
							PriorityPredictionsClassManager.candidateViewClass_setCandidateMethod.invoke(view, source);
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
						}

						return null;
					}
				};
				PredictionCommons.mCandidatesAdapter.setHeader( new CandidatesRecyclerAdapter.CandidateItemViewHolder(dummyHeader) );


				PredictionCommons.mCandidatesAdapter.setOnItemClickListener(new CandidatesRecyclerAdapter.OnItemClickListener()
				{
					@Override
					public void onItemClick(CandidatesRecyclerAdapter.CandidateItemViewHolder holder)
					{

						if (holder != null && holder.source != null)
						{
							try
							{
								Object[] args = new Object[4];
								args[0] = PriorityPredictionsClassManager.hpeClass_constructor.newInstance();
								args[1] = holder.source;	//Apparently this is the candidate?
								args[2] = PredictionClassManager.candidateSourceTypeEnum_candidate_bar;
								args[3] = 0;

								PriorityPredictionsClassManager.buClass_submitCandidateMethod.invoke( PriorityPredictionsClassManager.buInstance, args );

								//Nested tries!
								try
								{
									//We used to create a click listener which would do this for us, but now we call the submit method directly.
									Vibrator v = (Vibrator) ContextUtils.getHookContext().getSystemService(Context.VIBRATOR_SERVICE);
									v.vibrate( KeyboardMethods.getVibrationDuration() );
								}
								catch (Throwable ex)
								{
									Log.i(LOGTAG, "Emoji click vibrate failed, super weird.");
									ex.printStackTrace();
								}

							}
							catch ( Throwable e)
							{
								Log.i(LOGTAG, "Failed to invoke onclick, method was: "+PriorityPredictionsClassManager.buClass_submitCandidateMethod.toString());
								e.printStackTrace();
							}
						}
						else
						{
							Log.i(LOGTAG, "Source was null. Weird.");
						}
					}
				});





				PredictionCommons.mCandidatesAdapter.notifyDataSetChanged();
				PredictionCommons.mCandidatesRecycler = new SlowRecyclerView(kView.getContext());

				PredictionCommons.mCandidatesRecycler.setId( View.generateViewId() );

				PredictionCommons.mCandidatesRecycler.setItemAnimator(null);
				PredictionCommons.mCandidatesRecycler.setLayoutParams(kViewParams);
				PredictionCommons.mCandidatesManager = new LinearLayoutManager(kView.getContext());
				PredictionCommons.mCandidatesManager.setOrientation(LinearLayoutManager.HORIZONTAL);


				// support library 28 recyclerview does not handle match_parent properly, so we have to manually make sure things are the right size.
				PredictionCommons.mCandidatesRecycler.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) ->
				{
					CandidatesRecyclerAdapter.CandidateItemViewHolder holder = PredictionCommons.mCandidatesAdapter.getHeader();
					if (holder != null && holder.itemView != null)
					{
						ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();

						if (params.width != right || params.height != bottom && params.width > 0 && params.height > 0)
						{
							params.width = right;
							params.height = bottom;
							holder.itemView.setLayoutParams( params );
						}
					}
				});



				//Order of these 3 is important, otherwise a few of the first items will be the wrong height
				//Ref http://stackoverflow.com/a/35916393/2312367
				PredictionCommons.mCandidatesRecycler.setAdapter(PredictionCommons.mCandidatesAdapter);
				PredictionCommons.mCandidatesManager.setAutoMeasureEnabled(false);
				PredictionCommons.mCandidatesRecycler.setLayoutManager(PredictionCommons.mCandidatesManager);

				PredictionCommons.mCandidatesRecycler.addOnScrollListener(new RecyclerView.OnScrollListener()
				{
					@Override
					public void onScrolled(RecyclerView recyclerView, int dx, int dy)
					{
						int firstVisibleIndex = PredictionCommons.mCandidatesManager.findFirstVisibleItemPosition();

						int scrollPosition = recyclerView.computeHorizontalScrollOffset();

						if (firstVisibleIndex == 0)
						{
							headerScroller.scrollTo(scrollPosition, 0);
						}
						else
						{
							headerScroller.scrollTo(headerScroller.getMeasuredWidth(), 0);
						}
					}
				});


				//Candidate view doesn't have a container anymore, need to create our own.
				LinearLayout.LayoutParams candidateContainerParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
				candidateContainerParams.weight = 0.75f;
				PredictionCommons.mCandidateContainer = new FrameLayout(kView.getContext());
				PredictionCommons.mCandidateContainer.setLayoutParams(candidateContainerParams);

				PredictionCommons.mCandidateContainer.addView(headerScroller);
				PredictionCommons.mCandidateContainer.addView(PredictionCommons.mCandidatesRecycler);

				centerLinear.addView(PredictionCommons.mCandidateContainer, 1);

				new Handler(Looper.getMainLooper()).post(new Runnable()
				{
					@Override
					public void run()
					{
						KeyboardMethods.updateHidePredictionBarAndPadKeyboardTop();

						// For some reason the original candidates view isn't displayed unless we
						// scroll the container we place it in. Invalidate is does not work.
						headerScroller.scrollTo(0,0);
					}
				});
			}
		}
	}

	public static void handleCandidatesUpdateHook(XC_MethodHook.MethodHookParam param)
	{

		final List<Object> candidatesList = (List<Object>) param.args[PredictionClassManager.updateCandidateDisplayClass_constructor_listArgPosition];
		PredictionCommons.mLastCandidateResultType = param.args[PredictionClassManager.updateCandidateDisplayClass_constructor_lastCandidateResultTypeArgPosition];

		//After flowing and receiving a flow_successful event, the primary candidate should always be verbatim, i.e. the selection.
		//For some reason, the verbatrim candidate is in the ... verbatim position instead after inserting a flow shortcut.
		//Must be missing a verbatim-mode flag somewhere. This shifts it back to where it should be, hopefully without messing anything up.

		if (DebugTools.DEBUG_PREDICTIONS)
		{
			Log.i(LOGTAG, "UpdateHook, last result is: "+PredictionCommons.mLastCandidateResultType);
		}

		if ( Settings.FLOW_SUGGESTIONS_ENABLED && PredictionCommons.mLastCandidateResultType == PredictionClassManager.resultTypeEnum_flow_success)
		{
			if (DebugTools.DEBUG_PREDICTIONS)
			{
				Log.i(LOGTAG, "Last result was flow success, making verbatim primary");
			}

			PredictionCommons.simpleMakeVerbatimPrimary(candidatesList);

			if (DebugTools.DEBUG_PREDICTIONS)
			{
				Log.i(LOGTAG, "First item is now: "+candidatesList.get(0));
			}
		}

		PredictionCommons.mMakeLiteralPrimarySuggestionRequestedLastUpdate = PredictionCommons.mMakeLiteralPrimarySuggestionRequested;
		PredictionCommons.handleCandidateInsertionHook(candidatesList, true);
	}

	public static void handleGetTopCandidateHook(XC_MethodHook.MethodHookParam param, boolean returnList)
	{


		//Get top candidate is always called after a flow, discarding the previous results.
		//Unlike earlier versions of swiftkey, this result does not pass through the candidateSelected method,
		//but is passed on to commitText.

		//This method is also called occasioanlly for normal input, for some reason.

		//Insert candidates as usual, but if the last event is a flow event, instead pass the original top
		//candidate wit ha reverse mapping to our inserted candidate.

		List<Object> candidatesList = null;

		if (!Settings.FLOW_SUGGESTIONS_ENABLED)
		{
			if (DebugTools.DEBUG_PREDICTIONS)
			{
				Log.i(LOGTAG, "Flow suggestions not enabled, not doing anything in get top candidate.");
			}

			return;
		}

		try
		{
			candidatesList = ((Future<List>) PredictionClassManager.UpdateCandidateTaskClass_FutureField.get(param.thisObject)).get();
		}
		catch (Exception ex)
		{
			//Well shit

		}


		if (candidatesList != null && !candidatesList.isEmpty())
		{
			//Log.e("###", "Existing top is: "+candidatesList.get(0));

			if (DebugTools.DEBUG_PREDICTIONS)
			{

				Log.i(LOGTAG, "Get top candidate, existing top is: "+candidatesList.get(0).toString());
				Log.i(LOGTAG, "Get top candidate, last result type is: "+PredictionCommons.mLastCandidateResultType.toString());
			}

			if (PredictionCommons.mLastCandidateResultType != PredictionClassManager.resultTypeEnum_flow)
			{
				PredictionCommons.handleCandidateInsertionHook(candidatesList ,false);

				if (returnList)
					param.setResult(candidatesList);
				else
					param.setResult(candidatesList.get(0));
			}
			else
			{
				if (DebugTools.DEBUG_PREDICTIONS)
				{
					Log.i(LOGTAG, "Returning top with flow shortcut insert");
				}

				CandidateManager.clearSelectedShortcutMap();

				Object candidate = candidatesList.get(0);
				Object insertedCandidate = null;

				PredictionCommons.mLastCandidateResultType = null;

				if (Settings.FLOW_SUGGESTIONS_ENABLED)
				{
					insertedCandidate = PredictionCommons.getFirstShortcut(candidate, Settings.OLD_FLOW_BEHAVIOR);
				}

				{
					if (DebugTools.DEBUG_PREDICTIONS)
					{
						Log.i(LOGTAG, "Input candidate was: "+candidate.toString()+", trying to replace.");
					}

					//Otherwise just return our inserted canidate.
					//We have to do it here for the new preliminary_flow state to be triggered.
					//From what I can tell this routine doesn't trigger any learning behavior
					// and doesn't pass through the handle candidate method.
					if (insertedCandidate != null)
					{
						if (returnList)
						{
							ArrayList<Object> insertedCandidateList = new ArrayList<>();
							insertedCandidateList.add(insertedCandidate);
							param.setResult(insertedCandidateList);
						}
						else
						{
							param.setResult(insertedCandidate);
						}

						if (DebugTools.DEBUG_PREDICTIONS)
						{
							Log.i(LOGTAG, "Successfully replaced with "+insertedCandidate.toString());
						}
					}
				}
			}
		}
		else
		{
			Log.e(LOGTAG, "Get top candidate was called with null list");
		}
	}
}
