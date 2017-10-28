package com.mayulive.swiftkeyexi.xposed.predictions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;

import android.util.Log;
import android.view.ViewGroup;


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

				if (DebugSettings.DEBUG_PREDICTIONS)
				{
					Log.i(LOGTAG, "Top candidate hook called");
				}

				try
				{
					if ( PredictionClassManager.UpdateCandidateTaskClass_getTopCandidateMethod_intPosition != -1)
					{
						//Method might have changed to no longer have the int

						//The only reason we care to hook this method is because it sometimes grabs
						//the primary method from here, instead of from our previously modified list.
						//The int now passed here is now the index to get from the list, so don't do
						//anything if it is anything but 0.
						if ( (int)param.args[PredictionClassManager.UpdateCandidateTaskClass_getTopCandidateMethod_intPosition] != 0 )
						{
							if (DebugSettings.DEBUG_PREDICTIONS)
							{
								Log.i(LOGTAG, "Top candidate called with non-zero index. Not doing anything.");
							}

							return;
						}
					}

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



	//All our inserted candidates are raw candidates. This is generally fine, and also sorta assures they won't be accidentally learned.
	//Unfortunately, the visitor interface ... thing they use returns null when trying to get the prediction text.
	//This is usually fine. It is either not used, or a null check is performed, as this is a common occurence for several types of candidates.
	//Chinese input, however, is /always/ fluency predicted, and thus does not expect other candidate types. Suppose this partially explains why
	//it doesn't support clipboard shortcuts. A null check is not performed down the line in SpellingHelper (chinese stuff).

	//Luckily it calls a method in CandidateUtil to get the prediction string, and the candidate is passed as an argument there.
	//There for null, check for match against our mapped inserted candidates, insert candidate string if hit.
	public static XC_MethodHook.Unhook hookSpellingsHelperCrash(PackageTree param )
	{
		Class CandidateUtilClass = ClassHunter.loadClass("com.touchtype_fluency.service.candidates.CandidateUtil", param.getClassLoader());
		Method CandidateUtilClass_getSpellingHintsMethod = ProfileHelpers.findFirstMethodByName(CandidateUtilClass.getDeclaredMethods(), "getPredictionInput");

		return XposedBridge.hookMethod(CandidateUtilClass_getSpellingHintsMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				if (param.getResult() == null)
				{
					//Acutally, a null is never good so let's always return the string of the candidate.
					//This is literally only used for the case we're correcting, and one that only takes fluency candidates (And should not return null)
					//Edit they fixed this. Method no longer exists, but keeping for backwards compatibility.
					param.setResult( CandidateManager.getCandidateText(param.args[0]) );
				}
			}
		});
	}


	public static XC_MethodHook.Unhook hookCandidatesDisplayView( ) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
	{
		/////////////////
		//Candidate bar
		////////////////

		{
			return XposedBridge.hookMethod(PredictionClassManager.candidatesViewFactory_getViewMethod, new XC_MethodHook()
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
			});


		}
	}

	public static XC_MethodHook.Unhook hookCandidatesDisplayView_getViewWrapper( ) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
	{
		/////////////////
		//Candidate bar
		////////////////
		{
			return XposedBridge.hookMethod(PredictionClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod, new XC_MethodHook()
					//XposedBridge.hookAllConstructors(keyboardEventClass, new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						PredictionHandlers.handleCandidateViewHook_replace((ViewGroup) param.args[ PredictionClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition ]);
					}
					catch (Throwable ex)
					{
						Hooks.predictionHooks_more.invalidate(ex, "Unexpected problem in Candidates Display View hook");
					}
				}
			});


		}


	}


	public static XC_MethodHook.Unhook hookBu() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{

		//XposedHelpers.findAndHookMethod(fluencyCandidateClass, "toString", new XC_MethodHook()
		return XposedBridge.hookMethod(PredictionClassManager.keyboardFrameClass_setBuMethod,  new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					PredictionClassManager.buInstance = PredictionClassManager.keyboardFrameClass_buField.get(param.thisObject);
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
			returnHooks.add( XposedBridge.hookMethod(PredictionClassManager.candidateSelectedMethod, new XC_MethodHook()
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


		int counter = 0;
		for (final Method method : PredictionClassManager.commitTextMethods)
		{

			final int argPos = PredictionClassManager.CommitTextClass_commitTextMethod_CandidateArgPosition[counter];

			returnHooks.add( XposedBridge.hookMethod(method, new XC_MethodHook()
			{

				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						PredictionHandlers.handleTextCommitHook(param,argPos);
					}
					catch (Throwable ex)
					{
						Hooks.predictionHooks_base.invalidate(ex, "Unexpected problem in Commit Text hook");
					}


				}

			}));

			counter++;
		}

		return returnHooks;
	}


	//Depends on ExiCandidate
	public static Set<XC_MethodHook.Unhook> hookCandidateRemove()
	{

		Set<XC_MethodHook.Unhook> returnHooks = new HashSet<>();

		for (Method method : PredictionClassManager.dialogConstructorMethods)
		{

			returnHooks.add( XposedBridge.hookMethod(method, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						Object candidate = param.args[4];
						CandidateManager.WrappedCandidate wrapped = CandidateManager.getWrappedCandidate(candidate);

						if (wrapped != null)
						{
							param.args[4] = wrapped.candidate;

							Method superMethod = (Method)param.method;
							param.setResult(superMethod.invoke(param.thisObject, param.args));
						}
					}
					catch (Throwable ex)
					{
						Hooks.predictionHooks_base.invalidate(ex, "Unexpected problem in Candidate Remove hook");
					}


				}
			}));
		}

		return returnHooks;
	}


	public static boolean HookAll(final PackageTree param)
	{
        try
        {      	
         	//Loads all the classes we will ever need to load!
        	PredictionClassManager.doAllTheThings(param);

			//hookDisableAutocomplete(param);
			if (Hooks.predictionHooks_base.isRequirementsMet())
			{
				Hooks.predictionHooks_base.addAll( hookCandidatesUpdated(param) );
				Hooks.predictionHooks_base.addAll( hookCandidateSelected() );
				Hooks.predictionHooks_base.addAll( hookCandidateRemove() );

				try
				{
					Hooks.predictionHooks_base.add( hookSpellingsHelperCrash(param) );
				}
				catch (Exception ex)
				{
					Log.i(LOGTAG, "Spell Checker method gone, expceted in newer versions.");

					//It is impossible for this particular crash to occur without my modifications.
					//Evidently the swiftkey team are still getting my crash reports and... fixing them.
					//rawTextCandidate now takes an extra prediction string input for the constructor.
					// ... I feel kind of bad now.
				}
			}


			if (Hooks.predictionHooks_more.isRequirementsMet())
			{
				Hooks.predictionHooks_more.add( hookCandidatesDisplayView() );

				hookCandidatesDisplayView_getViewWrapper();

				Hooks.predictionHooks_more.add( hookBu() );
			}


			//Run regardless
			KeyboardMethods.addKeyboardEventListener(new KeyboardMethods.KeyboardEventListener()
			{

				@Override public void afterKeyboardConfigurationChanged() {}
				@Override public void beforeKeyboardClosed() { }

				@Override
				public void keyboardInvalidated()
				{

				}


				@Override
				public void beforeKeyboardOpened()
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
        catch(Exception ex)
        {
			Hooks.predictionHooks_base.invalidate(ex, "Failed to hook");
        	return false;
        }
        
        return true;
	}
	
	
}
