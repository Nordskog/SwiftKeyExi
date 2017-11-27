package com.mayulive.swiftkeyexi.xposed.keyboard;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.util.Log;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.SharedTheme;
import com.mayulive.swiftkeyexi.providers.FontProvider;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;

import com.mayulive.swiftkeyexi.EmojiCache.NormalEmojiItem;

import com.mayulive.swiftkeyexi.xposed.selection.SelectionState;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.swiftkeyexi.util.ContextUtils;

import java.lang.reflect.Method;
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
		return XposedBridge.hookAllConstructors(KeyboardClassManager.keyboardServiceClass, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				 KeyboardClassManager.keyboardServiceInstance = param.thisObject;
			}
		});

	}

	public static XC_MethodHook.Unhook hookViewCreatedFallback(PackageTree param)
	{
		return XposedBridge.hookMethod(KeyboardClassManager.keyboardSizerClass_sizeKeyboardMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					ViewGroup view = (ViewGroup) param.getResult();

					//If cover is not null, maker sure we have not already added something
					if (OverlayCommons.mKeyboardOverlay != null)
					{
						view.removeView(OverlayCommons.mKeyboardOverlay);
					}

					RelativeLayout cover = new RelativeLayout(view.getContext());

					FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
					cover.setLayoutParams(params);
					view.addView(cover);
					OverlayCommons.mKeyboardOverlay = cover;
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
			return XposedHelpers.findAndHookMethod(KeyboardClassManager.keyboardServiceClass, "onStartInputView", EditorInfo.class, boolean.class, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					Settings.updateSettingsFromProvider(ContextUtils.getHookContext());

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

	public static Set<XC_MethodHook.Unhook> hookKeyboardConfigurationChanged()
	{

			return XposedBridge.hookAllMethods(KeyboardClassManager.keyboardServiceClass, "onConfigurationChanged", new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					for (KeyboardMethods.KeyboardEventListener listener : KeyboardMethods.mKeyboardEventListeners)
					{
						listener.afterKeyboardConfigurationChanged();
					}
				}
			});

	}

	private static Set<XC_MethodHook.Unhook> hookKeyboardLoaded()
	{
		return XposedBridge.hookAllConstructors(KeyboardClassManager.keyboardLoaderClass, new XC_MethodHook()
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
			return XposedBridge.hookMethod(KeyboardClassManager.keyboardLoader_loadMethod, new XC_MethodHook()
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
		return XposedBridge.hookMethod(KeyboardClassManager.keyboardLoader_clearCacheMethod, new XC_MethodHook()
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
			return XposedHelpers.findAndHookMethod(KeyboardClassManager.keyboardServiceClass, "onFinishInputView", boolean.class, new XC_MethodHook()
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
		return XposedBridge.hookMethod(KeyboardClassManager.punctuatorImplClass_AddRulesMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				//This method is only called when the app is launched.
				//I guess punctuation rules never really change between languages.
				//Since our settings won't be loaded at this point anyway, we deal with this
				//in a keyboardLoded callback instead.
				KeyboardClassManager.punctuatorImplInstance = param.thisObject;
			}

		});
	}

	private static XC_MethodHook.Unhook hookTheme(PackageTree param)
	{
		return XposedBridge.hookMethod(KeyboardClassManager.ThemeLoaderClass_getThemeMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				int newValue = ((boolean)param.getResult()) ? SharedTheme.DARK_THEME_IDENTIFIER : SharedTheme.LIGHT_THEME_IDENTIFIER;

				//Update regardless.
				//This value is 0-1, matching the values that exist in SharedTheme
				SharedTheme.setCurrenThemeType( ContextUtils.getHookContext(), newValue );

				if (newValue != KeyboardMethods.mTheme)
				{
					KeyboardMethods.mTheme = newValue;
					KeyboardMethods.callThemeChangedListeners(newValue);
				}

			}
		});
	}

	private static XC_MethodHook.Unhook hookFullscreen(PackageTree param)
	{
		return XposedBridge.hookMethod(KeyboardClassManager.keyboardService_onEvaluateFullscreenModeMethod, new XC_MethodHook()
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
		return XposedBridge.hookMethod(KeyboardClassManager.keyboardLoader_onSharedPreferenceChangedMethod, new XC_MethodHook()
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

	public static boolean HookAll(final PackageTree lpparam)
	{
		try
		{
			KeyboardClassManager.doAllTheThings(lpparam);

			if (Hooks.baseHooks_base.isRequirementsMet())
			{
				Hooks.baseHooks_base.addAll( hookServiceCreated() );
				Hooks.baseHooks_base.addAll( hookKeyboardConfigurationChanged() );
				Hooks.baseHooks_base.add( hookKeyboardOpened() );
				Hooks.baseHooks_base.add( hookKeyboardClosed() );

				Hooks.baseHooks_base.add( hookPrefChanged() );


				if (Hooks.baseHooks_fullscreenMode.isRequirementsMet())
				{
					hookFullscreen(lpparam);
				}

				if (Hooks.baseHooks_theme.isRequirementsMet())
				{
					Hooks.baseHooks_theme.add( hookTheme(lpparam) );
				}

				if (Hooks.baseHooks_punctuationSpace.isRequirementsMet())
				{
					Hooks.baseHooks_punctuationSpace.add( hookPunctuationRules() );
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
					}
				});

			}
		}
		catch(Exception ex)
		{
			Hooks.baseHooks_base.invalidate(ex, "Failed to Hook");
			return false;
		}

		return true;
	}
}
