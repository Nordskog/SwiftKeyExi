package com.mayulive.swiftkeyexi.xposed.predictions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.util.SteppedHookLog;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysCommons;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysSetup;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;

import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;


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
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					//Swiftkey expects the view we are moving into a container to not have a parent, so
					//make sure we remove it from our container. This is set again below in after.
					if (PredictionCommons.mKview != null && PredictionCommons.mKview.isAttachedToWindow())
					{
						ViewGroup parent = (ViewGroup)PredictionCommons.mKview.getParent();
						parent.removeView(PredictionCommons.mKview);
						PredictionCommons.mKview = null;
					}
				}

				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						PredictionHandlers.handleCandidateViewHook_replace((ViewGroup) param.args[PredictionClassManager.candidatesViewFactory_ReturnWrapperClass_GetViewMethod_LinearLayoutPosition]);
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
			//Only needed to update shortcut priority, not critical.
			if ( PredictionClassManager.candidateSelectedMethod != null )
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
		}

		return returnHooks;
	}


	public static XC_MethodHook.Unhook hookClipboardCandidateEllipsize( )
	{

		return XposedBridge.hookMethod(CandidateManager.clipboardCandidate_shouldEllipsizeMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					//Have shouldEllipsize return false if one of our inserted candidates.
					CandidateManager.SelectedShortcut selectedShortcut = CandidateManager.getSelectedShortcut(param.thisObject);
					if (selectedShortcut != null)
					{
						param.setResult(false);
					}
				}
				catch (Throwable ex)
				{
					Hooks.predictionHooks_base.invalidate(ex, "Unexpected problem in Candidate Selected hook");
				}

			}
		});

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
				Hooks.predictionHooks_base.add( hookClipboardCandidateEllipsize() );
			}

			if (Hooks.predictionHooks_priority.isRequirementsMet())
			{
				Hooks.predictionHooks_priority.addAll( hookCandidateSelected() );
			}

			if (Hooks.predictionHooks_more.isRequirementsMet())
			{
				Hooks.predictionHooks_more.add( hookCandidatesDisplayView() );
				Hooks.predictionHooks_more.add( hookCandidatesDisplayView_getViewWrapper() );
				Hooks.predictionHooks_more.add( hookBu() );
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
        catch(Exception ex)
        {
			Hooks.predictionHooks_base.invalidate(ex, "Failed to hook");
        	return false;
        }
        
        return true;
	}
	
	
}
