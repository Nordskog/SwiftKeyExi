package com.mayulive.swiftkeyexi.xposed.predictions;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.main.dictionary.CandidatesRecyclerAdapter;
import com.mayulive.swiftkeyexi.main.dictionary.SlowRecyclerView;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ProfileHelpers;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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
		candidateViewArgs = new Object[PredictionClassManager.candidateViewClass_Constructor.getParameterTypes().length];

		{
			for (int i = 0; i < PredictionClassManager.getViewMethod_CandidateViewClassConstructorArgPositions.length; i++)
			{
				if (PredictionClassManager.getViewMethod_CandidateViewClassConstructorArgPositions[i] != -1)
				{
					candidateViewArgs[i] = param.args[ PredictionClassManager.getViewMethod_CandidateViewClassConstructorArgPositions[i] ];
				}
			}

			Object someEnum =  CodeUtils.findEnumByName( (Enum[])  PredictionClassManager.candidateViewClass_Constructor.getParameterTypes()[PredictionClassManager.getViewMethod_EnumArgPosition].getEnumConstants(), "CANDIDATE");
			candidateViewArgs[PredictionClassManager.getViewMethod_EnumArgPosition] = someEnum;
			if ( PredictionClassManager.getViewMethod_BooleanArgPosition != -1)
				candidateViewArgs[ PredictionClassManager.getViewMethod_BooleanArgPosition] = false;	//z ? R.dimen.floating_sequential_candidate_min_width : R.dimen.sequential_candidate_min_width);
		}
	}

	public static void handleCandidateViewHook_replace(ViewGroup childFrame)
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
				//Log.i(LOGTAG, "Insufficient candidate children, doing nothing");
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

							View returnView = (View) PredictionClassManager.candidateViewClass_Constructor.newInstance(candidateViewArgs);

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
							PredictionClassManager.candidateViewClass_setCandidateMethod.invoke(view, source);
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
								Object candidateClickListener;
								int paramCount = PredictionClassManager.candidateClickConstructor.getParameterTypes().length;
								if ( paramCount == 4 )
								{
									candidateClickListener = PredictionClassManager.candidateClickConstructor.newInstance(PredictionClassManager.buInstance,null,null,null);
								}
								else // >= 7.0.5.22
								{
									candidateClickListener = PredictionClassManager.candidateClickConstructor.newInstance(	holder.view.getContext(),
																																		PredictionClassManager.KeyboardUxOptionsInstance,
																																		PredictionClassManager.buInstance,null,null,null);
								}
								PredictionClassManager.candidateOnCLickMethod.invoke(candidateClickListener, holder.view, holder.source, 0);
							}
							catch ( Throwable e)
							{
								Log.i(LOGTAG, "Failed to invoke onclick, method was: "+PredictionClassManager.candidateOnCLickMethod.toString());
								Log.i(LOGTAG, "Listener class was: "+PredictionClassManager.candidateClickConstructor.toString());
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
				final FrameLayout candidateContainer = new FrameLayout(kView.getContext());
				candidateContainer.setLayoutParams(candidateContainerParams);

				candidateContainer.addView(headerScroller);
				candidateContainer.addView(PredictionCommons.mCandidatesRecycler);

				centerLinear.addView(candidateContainer, 1);
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

		if (DebugSettings.DEBUG_PREDICTIONS)
		{
			Log.i(LOGTAG, "UpdateHook, last result is: "+PredictionCommons.mLastCandidateResultType);
		}

		if ( Settings.FLOW_SUGGESTIONS_ENABLED && PredictionCommons.mLastCandidateResultType == PredictionClassManager.resultTypeEnum_flow_success)
		{
			if (DebugSettings.DEBUG_PREDICTIONS)
			{
				Log.i(LOGTAG, "Last result was flow success, making verbatim primary");
			}

			PredictionCommons.simpleMakeVerbatimPrimary(candidatesList);

			if (DebugSettings.DEBUG_PREDICTIONS)
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
			if (DebugSettings.DEBUG_PREDICTIONS)
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

			if (DebugSettings.DEBUG_PREDICTIONS)
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
				if (DebugSettings.DEBUG_PREDICTIONS)
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
					if (DebugSettings.DEBUG_PREDICTIONS)
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

						if (DebugSettings.DEBUG_PREDICTIONS)
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
