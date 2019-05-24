package com.mayulive.swiftkeyexi.xposed.predictions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;


public class PredictionHooks
{
	private static String LOGTAG = ExiModule.getLogTag(PredictionHooks.class);

	public static Set<XC_MethodHook.Unhook> hookCandidatesUpdated( PackageTree param ) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
	{
		Set<XC_MethodHook.Unhook> returnHooks = new HashSet<>();

		///////////////////////////////
		//Prediction / Candidate list
		///////////////////////////////
		returnHooks.addAll( XposedBridge.hookAllConstructors(PredictionClassManager.updateCandidateDisplayClass, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					PredictionHandlers.handleCandidatesUpdateHook(param);
				}
				catch (Throwable ex)
				{
					Hooks.predictionHooks_base.invalidate(ex, "Unexpected problem in Candidates Updated hook");
				}

			}
		}));

		////////////////////////////

		returnHooks.add( XposedBridge.hookMethod(PredictionClassManager.UpdateCandidateTaskClass_getTopCandidateMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{

				PredictionCommons.mLastCandidateResultType = param.args[PredictionClassManager.UpdateCandidateTaskClass_getTopCandidateMethod_EnumPosition];

				if (DebugTools.DEBUG_PREDICTIONS)
				{
					Log.i(LOGTAG, "Top candidate hook called");
				}

				try
				{

					//Method changed from single candidate to list.
					boolean returnList = ((Method)param.method).getReturnType() == List.class;

					PredictionCommons.mLastCandidateResultType = param.args[PredictionClassManager.UpdateCandidateTaskClass_getTopCandidateMethod_EnumPosition];
					PredictionHandlers.handleGetTopCandidateHook(param, returnList);
				}
				catch (Throwable ex)
				{
					Hooks.predictionHooks_base.invalidate(ex, "Unexpected problem in Handle Top Candidate hook");
				}
			}
		}));

		return returnHooks;
	}

	public static XC_MethodHook.Unhook hookCandidatesDisplayView( ) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
	{
		/////////////////
		//Candidate bar
		////////////////

		{
			return XposedBridge.hookMethod(PriorityPredictionsClassManager.candidatesViewFactory_getViewMethod, new XC_MethodHook()
			//XposedBridge.hookAllConstructors(keyboardEventClass, new XC_MethodHook()
			{
				
	        	   @Override
	        	   protected void beforeHookedMethod(MethodHookParam param) throws Throwable
	        	   {
					   try
					   {
						   PredictionHandlers.handleCandidateViewHook_parameters(param);

					   }
					   catch (Throwable ex)
					   {
						   Hooks.predictionHooks_more.invalidate(ex, "Unexpected problem in Candidates Display View hook");
					   }
	        	   }

				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{

					//Again, this doesn't really go here, but this method happens to be called
					//after a layout change. Layout changes resets some of our changes, soooo
					//run again here if we need to.
					//This runs at a 500ms delay because ... things arne't ready yet I guess.
					if (Settings.HIDE_PREDICTIONS_BAR)
					{
						if (OverlayCommons.mKeyboardOverlay != null)
						{
							Handler handler = new Handler(Looper.getMainLooper());

							handler.postDelayed(new Runnable()
							{
								@Override
								public void run()
								{
									try
									{
										View parent = CodeUtils.getTopParent( OverlayCommons.mKeyboardOverlay );
										KeyboardMethods.updateHidePredictionBarAndPadKeyboardTop( parent );
									}
									catch ( Throwable ex )
									{
										Log.e(LOGTAG, "Tried to update hide predictionss bar in predictions hooks, failed.");
									}

								}
							}, 500);


						}
					}


				}
			});


		}
	}

	public static XC_MethodHook.Unhook hookCandidatesDisplayView_getViewWrapper( ) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
	{
		/////////////////
		//Candidate bar
		////////////////
		{
			return XposedBridge.hookMethod(PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{

					try
					{
						//Due a view hierarchy change, we must make sure to remove the candidate kview from
						//our container before we enter this method, as it will attempt to attach it to a new parent
						LinearLayout centerLinear = (LinearLayout)param.args[PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition];
						if (centerLinear.getChildCount() >= 3)
						{
							View centerView = centerLinear.getChildAt(1);
							if (centerView instanceof FrameLayout)
							{
								ViewGroup centerFrame = (FrameLayout)centerView;
								ViewGroup scrollHeader = (ViewGroup)centerFrame.getChildAt(0);
								scrollHeader.removeAllViews();
								centerFrame.removeAllViews();
								centerFrame.removeAllViews();
							}
						}
					}
					catch (Throwable ex)
					{
						Hooks.predictionHooks_more.invalidate(ex, "Unexpected problem in Candidates Display View hook");
					}
				}

				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						PredictionHandlers.handleCandidateViewHook_replace((ViewGroup) param.args[PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition]);
					}
					catch (Throwable ex)
					{
						Hooks.predictionHooks_more.invalidate(ex, "Unexpected problem in Candidates Display View hook");
					}

					try
					{
						//Not really this guy's responsbility, but good to run here. Lots of other places too.
						View parent = CodeUtils.getTopParent( (ViewGroup) param.args[PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition] );
						KeyboardMethods.updateHidePredictionBarAndPadKeyboardTop(parent);
					}
					catch (Throwable ex)
					{
						Log.e(LOGTAG, "Tried to update hide prediction bar in candidatesDisplayView hook, but failed.");
					}

				}
			});
		}
	}


	public static XC_MethodHook.Unhook hookBu() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{

		//XposedHelpers.findAndHookMethod(fluencyCandidateClass, "toString", new XC_MethodHook()
		return XposedBridge.hookMethod(PriorityPredictionsClassManager.keyboardFrameClass_setBuMethod,  new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					PriorityPredictionsClassManager.buInstance = PriorityPredictionsClassManager.keyboardFrameClass_buField.get(param.thisObject);
				}
				catch (Throwable ex)
				{
					Hooks.predictionHooks_base.invalidate(ex, "Unexpected problem in Bu hook");
				}


			}
		});

	}

	//Depends on ExiCandidate
	public static Set<XC_MethodHook.Unhook> hookCandidateSelected( )
	{
		Set<XC_MethodHook.Unhook> returnHooks = new HashSet<>();
		{
			//Only needed to update shortcut priority, not critical.
			if ( PredictionClassManager.handleCandidateClass_candidateSelectedMethod != null )
			{
				returnHooks.add( XposedBridge.hookMethod(PredictionClassManager.handleCandidateClass_candidateSelectedMethod, new XC_MethodHook()
				{
					@Override
					protected void beforeHookedMethod(MethodHookParam param) throws Throwable
					{
						try
						{
							PredictionHandlers.handleCandidateSelectedHook(param);
						}
						catch (Throwable ex)
						{
							Hooks.predictionHooks_base.invalidate(ex, "Unexpected problem in Candidate Selected hook");
						}
					}
				}));
			}
		}

		return returnHooks;
	}

	public static boolean hookPriority(final PackageTree param)
	{
		try
		{
			//Loads all the classes we will ever need to load!
			PriorityPredictionsClassManager.doAllTheThings(param);

			if (Hooks.predictionHooks_more.isRequirementsMet())
			{
				Hooks.predictionHooks_more.add( hookCandidatesDisplayView() );
				Hooks.predictionHooks_more.add( hookCandidatesDisplayView_getViewWrapper() );
				Hooks.predictionHooks_more.add( hookBu() );
			}

		}
		catch(Throwable ex)
		{
			Hooks.predictionHooks_base.invalidate(ex, "Failed to hook");
			return false;
		}

		return true;


	}

	public static boolean HookAll(final PackageTree param)
	{
        try
        {      	
         	//Loads all the classes we will ever need to load!
        	PredictionClassManager.doAllTheThings(param);

			if (Hooks.predictionHooks_base.isRequirementsMet())
			{
				Hooks.predictionHooks_base.addAll( hookCandidatesUpdated(param) );
				Hooks.predictionHooks_base.addAll( hookCandidateSelected() );
			}

			if (Hooks.predictionHooks_priority.isRequirementsMet())
			{
				Hooks.predictionHooks_priority.addAll( hookCandidateSelected() );
			}

			Settings.addOnSettingsUpdatedListener(new Settings.OnSettingsUpdatedListener()
			{
				@Override
				public void OnSettingsUpdated()
				{
					if (PredictionCommons.mLastUpdateTime < Settings.LAST_DICTIONARY_UPDATE)
					{
						PredictionSetup.loadShortcuts();
					}
					PredictionCommons.mLastUpdateTime = System.currentTimeMillis();

					if (Settings.changed_REMOVE_SUGGESTIONS_PADDING)
					{
						PredictionCommons.setSuggestionsPaddingVisibility( !Settings.REMOVE_SUGGESTIONS_PADDING );
					}
				}
			});

        }
        catch(Throwable ex)
        {
			Hooks.predictionHooks_base.invalidate(ex, "Failed to hook");
        	return false;
        }
        
        return true;
	}
	
	
}
