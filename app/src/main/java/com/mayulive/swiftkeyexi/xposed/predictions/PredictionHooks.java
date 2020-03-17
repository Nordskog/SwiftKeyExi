package com.mayulive.swiftkeyexi.xposed.predictions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.Settings;
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
					// and then as of 7.3.7.18 back to candidate again
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
										KeyboardMethods.updateHidePredictionBarAndPadKeyboardTop();
										KeyboardMethods.handleExpandButton();
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

	public static XC_MethodHook.Unhook hookCandidatesDisplayView_getViewWrapper( )
	{
		/////////////////
		//Candidate bar
		////////////////
		{
			return XposedBridge.hookMethod(PriorityPredictionsClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod, new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						ViewGroup parent = (ViewGroup) param.getResult();
						View replacement = PredictionHandlers.handleCandidateViewHook_replace( parent );
						if (replacement != null)
						{
							param.setResult(replacement);
						}
					}
					catch (Throwable ex)
					{
						Hooks.predictionHooks_more.invalidate(ex, "Unexpected problem in Candidates Display View hook");
					}

					try
					{
						//Not really this guy's responsbility, but good to run here. Lots of other places too.
						KeyboardMethods.updateHidePredictionBarAndPadKeyboardTop();
						KeyboardMethods.handleExpandButton();
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

	//Depends on ExiCandidate
	public static XC_MethodHook.Unhook hookEllipsize( )
	{

		return XposedBridge.hookMethod(PredictionClassManager.ellipsizeCheckerClass_shouldEllipsizeMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{

				try
				{
					Object candidate = PredictionClassManager.ellipsizeCheckerClass_candidateField.get(param.thisObject);

					//Have shouldEllipsize return false if one of our inserted candidates.
					CandidateManager.SelectedShortcut selectedShortcut = CandidateManager.getSelectedShortcut(candidate);
					if (selectedShortcut != null)
					{
						param.setResult(false);
					}
				}
				catch (Throwable ex)
				{
					Hooks.predictionHooks_Ellipsize.invalidate(ex, "Unexpected problem in set ellipsize hook");
				}

			}
		});
	}

	// Email address candidates use a completely differnet system, and tend to be really long.
	public static XC_MethodHook.Unhook hookEmailCandidateEllipsize( )
	{
		return XposedBridge.hookMethod( CandidateManager.emailAddressCandidateClass_constructor , new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				if (Settings.MORE_SUGGESTIONS_ENABLED)
				{
					try
					{
						String actualText = CandidateManager.getCandidateText(param.args[0]);
						if (actualText != null && !actualText.isEmpty())
						{
							param.args[1] = actualText;
						}
					}
					catch (Throwable ex)
					{
						Hooks.predictionHooks_EllipsizeMailAddress.invalidate(ex, "Unexpected problem in email set ellipsize hook");
					}
				}
			}
		});
	}


	//Depends on ExiCandidate
	public static Set<XC_MethodHook.Unhook> hookFluencyTrailingSeperator( )
	{
		Set<XC_MethodHook.Unhook> returnHooks = new HashSet<>();
		{
			Method[] methods = new Method[]{ 	CandidateManager.verbatimCandidate_getTrailingSeparatorMethod,
												CandidateManager.fluencyCandidate_getTrailingSeparatorMethod,
												CandidateManager.clipboardCandidateClass_getTrailingSeparatorMethod,
												CandidateManager.emailAddressCandidateClass_getTrailingSeparatorMethod,
												CandidateManager.contextPromotedFluencyCandidate_getTrailingSeparatorMethod,
												CandidateManager.rawTextCandidateClass_getTrailingSeparatorMethod

			};

			for (Method method : methods)
			{
				if (method != null)
				{
					returnHooks.add( XposedBridge.hookMethod( method, new XC_MethodHook()
					{
						@Override
						protected void afterHookedMethod(MethodHookParam param) throws Throwable
						{
							if (Settings.DISABLE_PREDICTION_AUTO_SPACE)
							{
								// If enabled, do not add space after adding prediction from candidate bar
								if ( param.thisObject == PredictionCommons.mLastSelectedCandidateBarCandiate || param.thisObject == PredictionCommons.mLastSelectedCandidateBarCandidateWrapped)
								{
									param.setResult("");
								}
							}
						}
					}));
				}
				else
				{
					Log.e(LOGTAG, "Missing getTrailingSeparator method, not hooking");
				}
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
				Hooks.predictionHooks_base.addAll( hookFluencyTrailingSeperator() );
			}

			if ( Hooks.predictionHooks_Ellipsize.isRequirementsMet() )
			{
				Hooks.predictionHooks_Ellipsize.add(hookEllipsize());
			}

			if ( Hooks.predictionHooks_EllipsizeMailAddress.isRequirementsMet())
			{
				Hooks.predictionHooks_EllipsizeMailAddress.add(hookEmailCandidateEllipsize());
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
						KeyboardMethods.handleExpandButton();
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
