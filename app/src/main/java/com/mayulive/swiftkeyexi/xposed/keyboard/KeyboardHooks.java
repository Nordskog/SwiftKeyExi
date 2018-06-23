package com.mayulive.swiftkeyexi.xposed.keyboard;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.providers.FontProvider;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;

import com.mayulive.swiftkeyexi.EmojiCache.NormalEmojiItem;

import com.mayulive.swiftkeyexi.xposed.selection.SelectionState;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.swiftkeyexi.util.ContextUtils;

import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by Roughy on 1/6/2017.
 */

public class KeyboardHooks
{
	private static String LOGTAG = ExiModule.getLogTag(KeyboardHooks.class);

	//Keyboard service created
	public static Set<XC_MethodHook.Unhook> hookServiceCreated() throws NoSuchMethodException
	{
		return XposedBridge.hookAllConstructors(PriorityKeyboardClassManager.keyboardServiceClass, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				 PriorityKeyboardClassManager.keyboardServiceInstance = param.thisObject;
			}
		});

	}

	public static XC_MethodHook.Unhook hookViewCreatedFallback(PackageTree param)
	{
		return XposedBridge.hookMethod(PriorityKeyboardClassManager.FullKeyboardServiceDelegate_onCreateInputView, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					KeyboardMethods.mKeyboardRoot = (ViewGroup) param.getResult();

					KeyboardMethods.setKeyboardOpacity();

					//If cover is not null, maker sure we have not already added something
					if (OverlayCommons.mKeyboardOverlay != null)
					{
						KeyboardMethods.mKeyboardRoot.removeView(OverlayCommons.mKeyboardOverlay);
					}

					RelativeLayout cover = new RelativeLayout( KeyboardMethods.mKeyboardRoot.getContext());

					FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
					cover.setLayoutParams(params);
					KeyboardMethods.mKeyboardRoot.addView(cover);
					OverlayCommons.mKeyboardOverlay = cover;

					if (!ExiXposed.isFinishedLoading())
					{
						OverlayCommons.displayLoadingMessage();
					}

					//For some absurd reason, the overlay will refuse to measure any views that are added immediately.
					//You have to wait 500-1000ms. Any views added before this will never be given a size, and have to be removed and re-added.
					//Updating the progress now does this as a workaround, so here we throw in a few extra fake progress update to force it to display.
					//I tried everything. Short of digging into FrameLayout/RelativeLayout (they both behave like this)'s code, this is the least painful solution.
					Handler handler = new Handler(Looper.getMainLooper());
					Runnable fakeProgressUpdate = () -> ExiXposed.updateLoadingProgress( ExiXposed.getLoadingProgress() );

					handler.postDelayed( fakeProgressUpdate, 300);
					handler.postDelayed( fakeProgressUpdate, 500);
					handler.postDelayed( fakeProgressUpdate, 1000);
					handler.postDelayed( fakeProgressUpdate, 2000);

				}
				catch (Throwable ex)
				{
					Hooks.baseHooks_viewCreated.invalidate(ex, "Unexpected problem in viewCreated hook");
				}

			}
		});

	}

	public static XC_MethodHook.Unhook hookKeyboardOpened()
	{
		{
			return XposedHelpers.findAndHookMethod(PriorityKeyboardClassManager.keyboardServiceClass, "onStartInputView", EditorInfo.class, boolean.class, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{

					//Do not run unless setup has finished
					if (ExiXposed.isFinishedLoading())
					{
						Settings.updateSettingsFromProvider(ContextUtils.getHookContext());
					}

					//Something may trigger the keyboard to close without the user interacting with it,
					//which would leave our popups visible when it is opened next.
					OverlayCommons.clearPopups();

					if (!NormalEmojiItem.isAssetsLoaded())
					{
						NormalEmojiItem.loadAssets( FontProvider.getFont(ContextUtils.getHookContext(), "NotoEmoji_der_nougat.ttf") );
					}

					for (KeyboardMethods.KeyboardEventListener listener : KeyboardMethods.mKeyboardEventListeners)
					{
						listener.beforeKeyboardOpened();
					}

				}

				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					if (DebugSettings.DEBUG_HITBOXES)
						OverlayCommons.displayDebugHithoxes(ContextUtils.getHookContext(), SelectionState.getSwipeOverlayHeight());
				}

			});
		}
	}

	public static XC_MethodHook.Unhook hookKeyboardConfigurationChanged()
	{
			return XposedBridge.hookMethod( PriorityKeyboardClassManager.keyboardService_onConfigurationChangedMethod,  new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					KeyboardMethods.setKeyboardOpacity();

					for (KeyboardMethods.KeyboardEventListener listener : KeyboardMethods.mKeyboardEventListeners)
					{
						listener.afterKeyboardConfigurationChanged();
					}
				}
			});

	}

	private static Set<XC_MethodHook.Unhook> hookKeyboardLoaded()
	{
		return XposedBridge.hookAllConstructors(PriorityKeyboardClassManager.keyboardLoaderClass, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				KeyboardMethods.mKeyboardLoadObject = param.thisObject;
			}
		});
	}



	private static XC_MethodHook.Unhook hookLayoutChanged(PackageTree param)
	{
			return XposedBridge.hookMethod(PriorityKeyboardClassManager.keyboardLoader_loadMethod, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{

					try
					{
						Enum layoutEnum = (Enum)KeyboardClassManager.keyboardLoaderClass_layoutField.get(param.thisObject);

						KeyboardMethods.mCurrentLayoutName = layoutEnum.name();
						KeyCommons.setCurrentHitboxMap(layoutEnum.name());

						//Werid layouts that are split into multiple boxes, meaning the hitboxes we
						//get don't match the layout.
						// Anything that contains "12"
						// Anything that contains FIVESTROKE
						// HANDWRITING_CN maybe? I can't find it anywhere
						if (KeyboardMethods.mCurrentLayoutName.contains("12")
								|| KeyboardMethods.mCurrentLayoutName.contains("FIVESTROKE") )
						{
							KeyboardMethods.mLayoutIsWeird = true;
						}
						else
						{
							KeyboardMethods.mLayoutIsWeird = false;
						}

						KeyboardMethods.mLayoutIsExtendedPredictions = KeyboardMethods.isLayoutExtendedPredictions( KeyboardMethods.mCurrentLayoutName );

						KeyboardMethods.mIsSymbols = KeyboardMethods.mCurrentLayoutName.contains("SYMBOLS");
					}
					catch (Throwable ex)
					{
						Hooks.baseHooks_layoutChange.invalidate(ex, "Unexpected problem in Layout Changed hook");
					}


				}

			});
	}

	private static XC_MethodHook.Unhook hookLayoutInvalidated(PackageTree param)
	{
		return XposedBridge.hookMethod(PriorityKeyboardClassManager.keyboardLoader_clearCacheMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				{
					for (KeyboardMethods.KeyboardEventListener listener : KeyboardMethods.mKeyboardEventListeners)
					{
						listener.keyboardInvalidated();
					}

				}
			}
		});
	}


	public static XC_MethodHook.Unhook hookKeyboardClosed()
	{
			return XposedHelpers.findAndHookMethod(PriorityKeyboardClassManager.keyboardServiceClass, "onFinishInputView", boolean.class, new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					for (KeyboardMethods.KeyboardEventListener listener : KeyboardMethods.mKeyboardEventListeners)
					{
						listener.beforeKeyboardClosed();
					}
				}
			});
	}

	private static XC_MethodHook.Unhook hookPunctuationRules()
	{
		return XposedBridge.hookMethod(PriorityKeyboardClassManager.punctuatorImplClass_AddRulesMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				//This method is only called when the app is launched.
				//I guess punctuation rules never really change between languages.
				//Since our settings won't be loaded at this point anyway, we deal with this
				//in a keyboardLoded callback instead.
				PriorityKeyboardClassManager.punctuatorImplInstance = param.thisObject;
			}

		});
	}


	private static XC_MethodHook.Unhook hookFullscreen(PackageTree param)
	{
		return XposedBridge.hookMethod(PriorityKeyboardClassManager.keyboardService_onEvaluateFullscreenModeMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				if (Settings.DISABLE_FULLSCREEN_KEYBOARD)
					param.setResult(false);
			}
		});
	}

	private static XC_MethodHook.Unhook hookPrefChanged()
	{
		return XposedBridge.hookMethod(PriorityKeyboardClassManager.keyboardLoader_onSharedPreferenceChangedMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				for (SharedPreferences.OnSharedPreferenceChangeListener listener : KeyboardMethods.mSwiftkeyPrefChangedListeners)
				{
					listener.onSharedPreferenceChanged((SharedPreferences)param.args[0], (String)param.args[1]);
				}
			}
		});

	}

	private static  XC_MethodHook.Unhook hookKeyHeight()
	{
		return XposedBridge.hookMethod(KeyboardClassManager.keyHeightClass_getKeyHeightMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					if ( Settings.KEYBOARD_SIZE_MULTIPLIER != 1f )
					{
						int value = (int) param.getResult();
						param.setResult((int) (value * Settings.KEYBOARD_SIZE_MULTIPLIER));
					}
				}
				catch (Throwable ex)
				{

					Hooks.baseHooks_keyHeight.invalidate(ex, "Unexpected problem in Key Height hook");

				}
			}
		});
	}

	public static boolean hookPriority(final PackageTree lpparam)
	{
		try
		{
			PriorityKeyboardClassManager.doAllTheThings(lpparam);

			if (Hooks.baseHooks_base.isRequirementsMet())
			{
				Hooks.baseHooks_base.addAll( hookServiceCreated() );
				Hooks.baseHooks_base.add( hookKeyboardConfigurationChanged() );
				Hooks.baseHooks_base.add( hookKeyboardOpened() );
				Hooks.baseHooks_base.add( hookKeyboardClosed() );

				if (Hooks.baseHooks_fullscreenMode.isRequirementsMet())
				{
					hookFullscreen(lpparam);
				}

				if (Hooks.baseHooks_invalidateLayout.isRequirementsMet())
				{
					//Required by popups
					Hooks.baseHooks_invalidateLayout.addAll( hookKeyboardLoaded() );
				}

				if (Hooks.baseHooks_viewCreated.isRequirementsMet())
				{
					Hooks.baseHooks_viewCreated.add(hookViewCreatedFallback(lpparam));
				}

				if (Hooks.baseHooks_punctuationSpace.isRequirementsMet())
				{
					Hooks.baseHooks_punctuationSpace.add( hookPunctuationRules() );
				}
			}
		}
		catch(Throwable ex)
		{
			Hooks.baseHooks_base.invalidate(ex, "Failed to Hook");
			return false;
		}

		return true;
	}

	public static boolean HookAll(final PackageTree lpparam)
	{
		try
		{
			KeyboardClassManager.doAllTheThings(lpparam);

			if (Hooks.baseHooks_base.isRequirementsMet())
			{

				Hooks.baseHooks_base.add( hookPrefChanged() );

				if (Hooks.baseHooks_keyHeight.isRequirementsMet())
				{
					Hooks.baseHooks_keyHeight.add(  hookKeyHeight() );
				}



				if (Hooks.baseHooks_layoutChange.isRequirementsMet())
				{
					Hooks.baseHooks_layoutChange.add( hookLayoutChanged(lpparam) );
					Hooks.baseHooks_layoutChange.add( hookLayoutInvalidated(lpparam) );
				}

				Settings.addOnSettingsUpdatedListener(new Settings.OnSettingsUpdatedListener()
				{
					@Override
					public void OnSettingsUpdated()
					{
						KeyboardMethods.loadPunctuationRules( Settings.DISABLE_PUNCTUATION_AUTO_SPACE ?
										KeyboardMethods.PunctuationRuleMode.MODIFIED : KeyboardMethods.PunctuationRuleMode.STOCK,
								false );

						KeyboardMethods.setKeyboardOpacity();
					}
				});

			}
		}
		catch(Throwable ex)
		{
			Hooks.baseHooks_base.invalidate(ex, "Failed to Hook");
			return false;
		}

		return true;
	}
}
