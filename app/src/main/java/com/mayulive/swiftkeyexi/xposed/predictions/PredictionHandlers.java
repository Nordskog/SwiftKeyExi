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

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
		if (DebugSettings.DEBUG_PREDICTIONS)
		{
			Log.i(LOGTAG, "Entered handle candidate selected");
		}


		if (DebugSettings.DEBUG_PREDICTIONS)
		{
			Log.i(LOGTAG ,"Candidate selected. Wrapped count: "+ CandidateManager.getMapSize());
		}


		Object candidate = param.args[PredictionClassManager.handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition];
		CandidateManager.WrappedCandidate wrapped = CandidateManager.getWrappedCandidate(candidate);


		if (DebugSettings.DEBUG_PREDICTIONS)
		{
			Log.i(LOGTAG, "Selected: "+CandidateManager.getCandidateText(candidate)+" : "+System.identityHashCode(candidate));
		}



		//We used to clear the fluency map here. It is already cleared when we insert shortcuts,
		//so it isn't necessary. Normally leaving it was just fine, but at some point swiftkey started
		//calling this hook /twice/, and things would break on the second run because we just cleared the map.
		//CandidateManager.clearFluencyMap();

		if (wrapped != null)
		{
			if (DebugSettings.DEBUG_PREDICTIONS)
			{
				Log.i(LOGTAG, "Got wrapped candidate: "+wrapped.toString());
			}

			//If enabled, update priority as time
			//TODO add pref, maybe?
			wrapped.updateTime();

			//We only want to map raw candidates, ie our inserted ones.
			//The mappings will also contain a mapping of primary candidate -> first inserted candidate
			//for when the user is flowing and this method is never called.
			if (CandidateManager.rawCandidateClass.isAssignableFrom( candidate.getClass() ))
			{

				if (DebugSettings.DEBUG_PREDICTIONS)
				{
					Log.e(LOGTAG, "Wrapped candidate was raw, adding reverse mapping");
					Log.e(LOGTAG, "Key hash: "+CandidateManager.getHash(wrapped));
					Log.e(LOGTAG, "Value hash: "+CandidateManager.getHash(candidate));
				}


				//Reverse the mapping so we can identify our inserted candidate
				//using the wrapped, original candidate
				CandidateManager.clearFluencyMap();
				CandidateManager.addToMap(wrapped.candidate, new CandidateManager.WrappedCandidate(candidate) );

				//Pass the original candidate
				param.args[PredictionClassManager.handleCandidateClass_CandidateSelectedMethod_CandidateArgPosition] = wrapped.candidate;

				Method superMethod = (Method)param.method;

				try
				{
					Object superResult = superMethod.invoke(param.thisObject, param.args);
					param.setResult(superResult);
				}
				catch (Throwable ex)
				{
					Hooks.predictionHooks_base.invalidate(ex, "Failed to call Candidate Selected super method");
				}
			}
			else
			{
				//Log.e("###", "Clicked candidate was not raw candidate, skipping.");
			}
		}
	}

	public static void handleTextCommitHook(XC_MethodHook.MethodHookParam param, int candidateArgPo)
	{



		//Log.e("###", "Arg 3 type: "+param.args[3].getClass().getCanonicalName());
		//Object arg3 = param.args[3];
		//CodeUtils.printFieldValues(arg3.getClass(),arg3);

		if (DebugSettings.DEBUG_PREDICTIONS)
		{
			Log.i(LOGTAG, "Entered commit text");
		}

		Object candidate = param.args[candidateArgPo];
		Object wrapped = null;

		if (DebugSettings.DEBUG_PREDICTIONS)
		{
			if (candidate != null)
			{
				Log.i(LOGTAG,"Candidate is: "+CandidateManager.getCandidateText( candidate));
			}
			else
			{
				Log.i(LOGTAG, "Candidate was empty!");
			}
		}

		{

			CandidateManager.WrappedCandidate wrapper = CandidateManager.getWrappedCandidate(candidate);
			if (wrapper != null)
				wrapped = CandidateManager.getWrappedCandidate(candidate).candidate;

			if (DebugSettings.DEBUG_PREDICTIONS)
			{
				Log.i(LOGTAG,"Checking for normal candidate before commiting text");
				Log.i(LOGTAG, "Key: "+CandidateManager.getHash(candidate));
				Log.i(LOGTAG, "Values: ");
				CandidateManager.printMap();

				Log.i(LOGTAG, "Got object: "+CandidateManager.getHash(wrapped));
			}
		}



		if (wrapped != null)
		{
			if (DebugSettings.DEBUG_PREDICTIONS)
			{
				Log.i(LOGTAG, "Got wrapped candidate, replacing.");
			}

			param.args[candidateArgPo] = wrapped;

			Method superMethod = (Method)param.method;

			try
			{
				param.setResult(superMethod.invoke(param.thisObject, param.args));
			}
			catch (Exception ex)
			{

				Log.e(LOGTAG, "Failed to call super in TextCommit. Shortkey will not be inserted");

			}

		}
	}

	static private Object[] candidateViewArgs = null;

	public static void handleCandidateViewHook_parameters(XC_MethodHook.MethodHookParam param)
	{
		//To create a single candidate view we need 5 args.
		//4 of these are passed to the method we are hooking, while the last
		//is an enum. The enum we're after is "CANDIDATE".
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

		//Log.i(LOGTAG, "Hello world!");
		//Log.i(LOGTAG, "I am: "+ frameLayout.toString());
		//View top = CodeUtils.getTopParent(frameLayout);
		//CodeUtils.traverseLayout(top,0);

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
				//Log.i(LOGTAG, "Insufficient children, doing nothing");
				return;
			}


			ViewGroup centerLinear = childFrame;
			//ViewGroup parentFrame = (ViewGroup)childFrame.getParent();

			//This method now returns a linear layout containing 2... thingies,
			//with a framelayout containing the candidates view inbetween.

			{


				childFrame = (ViewGroup)centerLinear.getChildAt(1);

				//Some changes have been made that cause some of these views to persist.
				//Childframe should only have a single child, but our old ones may also be present.
				//Remove any linear layouts or slowrecyclerviews
				//Realistically we could just leave them where they are in this case but... ehhh...
				{
					ArrayList<View> killList = new ArrayList<>();
					for (int i = 0; i < childFrame.getChildCount(); i++)
					{
						Class clazz = childFrame.getChildAt(i).getClass();
						if (clazz == LinearLayout.class || SlowRecyclerView.class.isAssignableFrom( clazz ))
						{
							killList.add(childFrame.getChildAt(i));
						}
					}

					for (View view : killList)
					{
						if (view.getClass() == LinearLayout.class)
						{
							//We kinda left the existing view inside this
							ViewGroup group = (ViewGroup)view;
							if (group.getChildCount() > 0)
							{
								View kview = group.getChildAt(0);
								group.removeView(kview);
								childFrame.addView(kview);
							}
						}

						childFrame.removeView(view);
					}



				}

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
				final View kView = childFrame.getChildAt(0);
				childFrame.removeView(kView);

				ViewGroup.LayoutParams kViewParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

				final LinearLayout headerScroller = new LinearLayout(kView.getContext());
				headerScroller.setLayoutParams(kViewParams);

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
							//Log.e("###", "Shit hit the fan");
							ex.printStackTrace();
							return null;
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
							//Log.e("###", "Failed to set candidate");
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
								final Object candidateClickListener = PredictionClassManager.candidateClickConstructor.newInstance(PredictionClassManager.buInstance,null,null,null);
								PredictionClassManager.candidateOnCLickMethod.invoke(candidateClickListener, holder.view, holder.source, 0);
								//Log.e("###", "Click success!");
							}
							catch (IllegalAccessException | InvocationTargetException | InstantiationException e)
							{
								//Log.e("###", "Click failed!");
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


				childFrame.addView(headerScroller);
				childFrame.addView(PredictionCommons.mCandidatesRecycler);


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

	public static void handleGetTopCandidateHook(XC_MethodHook.MethodHookParam param)
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
				param.setResult(candidatesList.get(0));
			}
			else
			{
				if (DebugSettings.DEBUG_PREDICTIONS)
				{
					Log.i(LOGTAG, "Returning top with flow shortcut insert");
				}

				CandidateManager.clearFluencyMap();

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
						param.setResult(insertedCandidate);

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
