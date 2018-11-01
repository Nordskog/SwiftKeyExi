package com.mayulive.swiftkeyexi.xposed.keyboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.providers.FontProvider;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.util.DimenUtils;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;

import com.mayulive.swiftkeyexi.EmojiCache.NormalEmojiItem;

import com.mayulive.swiftkeyexi.xposed.selection.SelectionState;
import com.mayulive.swiftkeyexi.xposed.style.StyleCommons;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.Modifiers;
import static com.mayulive.xposed.classhunter.Modifiers.*;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
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

					for (KeyboardMethods.KeyboardEventListener listener : KeyboardMethods.mKeyboardEventListeners)
					{
						listener.afterKeyboardOpened();
					}
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
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				//This method is only called when the app is launched.
				//I guess punctuation rules never really change between languages.
				//Since our settings won't be loaded at this point anyway, we deal with this
				//in a keyboardLoded callback instead.
				PriorityKeyboardClassManager.punctuatorImplInstance = param.thisObject;

				//There is a race condition between this and the settingsLoaded callback,
				//Manually load punctuation rules here incase it fired before this.
				KeyboardMethods.loadPunctuationRules();

			}

		});
	}


	private static Set<XC_MethodHook.Unhook> hookFullscreen(PackageTree param)
	{
		Set<XC_MethodHook.Unhook> unhooks = new HashSet<>();

		unhooks.add( XposedBridge.hookMethod(PriorityKeyboardClassManager.keyboardService_onEvaluateFullscreenModeMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				if (Settings.DISABLE_FULLSCREEN_KEYBOARD)
					param.setResult(false);
			}
		}));

		unhooks.add( XposedBridge.hookMethod(PriorityKeyboardClassManager.keyboardService_isFullscreenModeMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				if (Settings.DISABLE_FULLSCREEN_KEYBOARD)
					param.setResult(false);
			}
		}));

		return unhooks;
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
					//Log.i(LOGTAG, "Pref changed: "+(String)param.args[1]);

					listener.onSharedPreferenceChanged((SharedPreferences)param.args[0], (String)param.args[1]);
				}
			}
		});

	}

	private static  Set<XC_MethodHook.Unhook> hookKeyHeight()
	{

		HashSet<XC_MethodHook.Unhook> returnSet = new HashSet<>();

		for (Method getKeyHeightMethod : KeyboardClassManager.keyHeightClass_getKeyHeightMethods)
		{
			returnSet.add( XposedBridge.hookMethod(getKeyHeightMethod, new XC_MethodHook()
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
			}));
		}
		


		return returnSet;
	}

	private static XC_MethodHook.Unhook hookToolbarPredictionBarRemoval()
	{

		return XposedBridge.hookMethod(PriorityKeyboardClassManager.toolbarOpenButtonOverlayViewClass_createToolbarOpenMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{

				if (Settings.HIDE_PREDICTIONS_BAR)
				{
					ViewGroup thiz = (ViewGroup) param.thisObject;
					View button = thiz.getChildAt(0);
					if (button != null)
					{
						//Button will be centered inside this container
						FrameLayout.LayoutParams containerParams = new FrameLayout.LayoutParams(
								(int) DimenUtils.calculatePixelFromDp(thiz.getContext(), 32),
								(int) DimenUtils.calculatePixelFromDp(thiz.getContext(), 18)
						);
						FrameLayout container = new FrameLayout(button.getContext());
						container.setLayoutParams(containerParams);

						//The actual button will stretch to fit whatever dimensions you give it, so we render it
						//at 64x64dp before cropping it above.
						FrameLayout.LayoutParams buttonParams = (FrameLayout.LayoutParams) button.getLayoutParams();
						buttonParams.gravity = Gravity.CENTER;
						buttonParams.height = (int) DimenUtils.calculatePixelFromDp(thiz.getContext(), 64);
						buttonParams.width = (int) DimenUtils.calculatePixelFromDp(thiz.getContext(), 64);
						button.setLayoutParams(buttonParams);

						//Place view into container
						thiz.removeView(button);
						container.addView(button);
						thiz.addView(container);
					}
				}
			}
		});
	}


	private static XC_MethodHook.Unhook hookToolbarButton()
	{

		return XposedBridge.hookMethod(PriorityKeyboardClassManager.toolbarFrameClass_inflateMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{

				//Toolbar inflates a layout inside itself with a framelayout root containing a constriantlayout.
				//We remove the constraint layout and place it inside a linear layout along with our button,
				//and insert it back into the original frame layout.

				try
				{
					//Run init always since we may need to remove the view if disabled

					ViewGroup toolbarView = (ViewGroup)param.thisObject;

					int toolbar_content_ID = toolbarView.getResources().getIdentifier("toolbar_content", "id", ExiXposed.HOOK_PACKAGE_NAME);

					ViewGroup contentView = toolbarView.findViewById(toolbar_content_ID);

					if (contentView != null)
					{

						ViewGroup toolParent = (ViewGroup) contentView.getParent();

						//Check if we've already done this
						{
							ViewParent parentParent = toolParent.getParent();
							if (parentParent != null && parentParent instanceof LinearLayout)
							{

								if ( !Settings.DISPLAY_TOOLBAR_SHORTCUT)
								{
									//Hide icon if already present
									if (KeyboardMethods.mToolbarButton != null)
									{
										KeyboardMethods.mToolbarButton.setVisibility(View.GONE);
									}

								}
								else
								{
									//Make visible again
									if (KeyboardMethods.mToolbarButton != null)
									{
										KeyboardMethods.mToolbarButton.setVisibility(View.VISIBLE);
									}
								}


								return;
							}
						}

						//Do nto add if disabled.
						if ( !Settings.DISPLAY_TOOLBAR_SHORTCUT )
						{
							return;
						}

						toolParent.removeView(contentView);

						ViewGroup.LayoutParams matchParentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

						LinearLayout.LayoutParams originalContainerParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
						originalContainerParams.weight = 1;

						LinearLayout toolbarContainer = new LinearLayout( contentView.getContext() );
						FrameLayout originalContainer = new FrameLayout(contentView.getContext());

						toolbarContainer.setLayoutParams(matchParentParams);
						originalContainer.setLayoutParams(originalContainerParams);

						ExiIconView button = new ExiIconView(contentView.getContext());
						KeyboardMethods.mToolbarButton = button;

						int currentTheme = StyleCommons.getCurrentTheme();
						KeyboardMethods.updateToolbarButtonColor(currentTheme);

						FrameLayout buttonContainer = new FrameLayout( contentView.getContext() );
						LinearLayout.LayoutParams buttonContainerParams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
						buttonContainerParams.weight = 0;

						FrameLayout.LayoutParams buttonParams = new FrameLayout.LayoutParams( (int)DimenUtils.calculatePixelFromDp(contentView.getContext(), 26), (int)DimenUtils.calculatePixelFromDp(contentView.getContext(), 26));
						buttonParams.gravity = Gravity.CENTER;

						button.setLayoutParams(buttonParams);
						buttonParams.leftMargin =  (int)DimenUtils.calculatePixelFromDp(contentView.getContext(), 6);
						buttonParams.rightMargin = (int)DimenUtils.calculatePixelFromDp(contentView.getContext(), 8);	//A bit greater to compensate for lopsided icon
						buttonContainer.setLayoutParams(buttonContainerParams);



						buttonContainer.setOnClickListener(new View.OnClickListener()
						{
							@Override
							public void onClick(View v)
							{

								Intent launchIntent = v.getContext().getPackageManager().getLaunchIntentForPackage(ExiModule.PACKAGE);
								if (launchIntent != null)
								{
									v.getContext().startActivity(launchIntent);
								}
								else
								{
									Log.e(LOGTAG, "Could not obtain launch intent for swiftkeyexi");
								}
							}
						});


						buttonContainer.addView(button);
						originalContainer.addView(contentView);
						toolbarContainer.addView(originalContainer);
						toolbarContainer.addView(buttonContainer);

						toolParent.addView(toolbarContainer);
					}

				}
				catch (Throwable ex)
				{
					//No point in removing hook I don't think.
					Log.e(LOGTAG, "Something went wrong adding toolbar button");
					ex.printStackTrace();
				}
			}
		});
	}

	// This hook doesn't have any ... hooks. It just sets a value.
	// Not going to bother with the class manager.
	private static void hookLocation(  PackageTree lpparam )
	{
		try
		{
			Class locClass = ProfileHelpers.loadProfiledClass(KeyboardProfiles._get_LOCATION_MANAGER_CLASS_PROFILE(), lpparam);
			Class someCollectionClass = ProfileHelpers.loadProfiledClass( KeyboardProfiles._get_COLLECTION_CLASS_USE_BY_LOCATION_PROFILE() ,lpparam);


			Method someCollectionClassCreateMethod = ProfileHelpers.findMostSimilar( new MethodProfile(
					Modifiers.STATIC,
					new ClassItem(Modifiers.THIS),
					new ClassItem(Modifiers.ARRAY)
			), someCollectionClass.getDeclaredMethods(), someCollectionClass);

			Field locClass_arrField = ProfileHelpers.findFirstDeclaredFieldWithType( someCollectionClass,  locClass);
			locClass_arrField.setAccessible(true);

			Object casCollection = someCollectionClassCreateMethod.invoke(null, new Object[]{KeyboardStrings.ALL_COUNTRY_CODES});

			locClass_arrField.set(null, casCollection);
		}
		catch ( Throwable ex )
		{
			Log.e(LOGTAG, "Failed to add countries to location list");
			ex.printStackTrace();
		}
	}

	private static XC_MethodHook.Unhook hookIncognito(PackageTree lpparam )
	{
		return XposedBridge.hookMethod( KeyboardClassManager.incogControllerClass_ChangeIncogStateMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					int state = (int)param.args[0];

					// 0 for on, 2 for off. Gets called with 1 all the time for seemingly no reason.
					if (state == 0)
					{
						KeyboardMethods.saveIncogState(true);
					}
					else if (state == 2)
					{
						KeyboardMethods.saveIncogState(false);
					}
				}
				catch ( Throwable ex )
				{
					Hooks.incognito.invalidate(ex, "Failed to intercept incognito state change");

					// Since we don't know what it is anymore, set to false.
					KeyboardMethods.saveIncogState(false);
				}
			}
		});
	}

	private static XC_MethodHook.Unhook hookQuickSettings(PackageTree lpparam )
	{

			return XposedBridge.hookMethod( KeyboardClassManager.quickSettingsClass_createSettingsMethod, new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{

					try
					{
						Context context = (Context) param.args[0];

						Object dyhInstance = param.args[1];
						Object hmlInstance = param.args[2];
						Object hwcInstance = param.args[3];

						ArrayList itemList = (ArrayList)param.getResult();

						Object vibrateItem = KeyboardMethods.createQuicksettingItem(
								context,
								"pref_system_vibration_key",
								"prefs_system_vibration_title",
								dyhInstance,
								hmlInstance,
								hwcInstance );

						if (vibrateItem != null)
						{
							itemList.add(vibrateItem);
						}

					}
					catch ( Throwable ex)
					{
						Log.e(LOGTAG, "Failed to add quicksetting");
						ex.printStackTrace();
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

				if (Hooks.baseHooks_toolbarButton.isRequirementsMet())
				{
					Hooks.baseHooks_toolbarButton.add( hookToolbarButton() );
				}

				if (Hooks.baseHooks_hidePredictions.isRequirementsMet())
				{
					Hooks.baseHooks_hidePredictions.add( hookToolbarPredictionBarRemoval() );
				}


				if (Hooks.baseHooks_fullscreenMode.isRequirementsMet())
				{
					hookFullscreen(lpparam);

					StyleCommons.addThemeChangedListener(new StyleCommons.ThemeChangedListener()
					{
						@Override
						public void themeChanged(int newTheme)
						{
							KeyboardMethods.updateToolbarButtonColor(newTheme);
						}

						@Override
						public void raisedBackgroundChanged(Drawable bg)
						{

						}
					});

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
					Hooks.baseHooks_keyHeight.addAll(  hookKeyHeight() );
				}

				if (Hooks.quickSettings.isRequirementsMet())
				{
					hookQuickSettings(lpparam);
				}


				if (Hooks.baseHooks_layoutChange.isRequirementsMet())
				{
					Hooks.baseHooks_layoutChange.add( hookLayoutChanged(lpparam) );
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

						if ( Settings.changed_HIDE_PREDICTIONS_BAR && OverlayCommons.mKeyboardOverlay != null)
						{
							View parent = CodeUtils.getTopParent( OverlayCommons.mKeyboardOverlay );
							KeyboardMethods.updateHidePredictionBarAndPadKeyboardTop( parent );
						}


					}
				});

				//We ... need to update the toolbar and proediction bar thing a lot.
				KeyboardMethods.addKeyboardEventListener(new KeyboardMethods.KeyboardEventListener()
				{
					@Override
					public void beforeKeyboardOpened()
					{

					}

					@Override
					public void afterKeyboardOpened()
					{
						// I guess this does something
						if (OverlayCommons.mKeyboardOverlay != null)
						{
							View parent = CodeUtils.getTopParent( OverlayCommons.mKeyboardOverlay );
							KeyboardMethods.updateHidePredictionBarAndPadKeyboardTop( parent );
						}

						// Only called once
						if ( !KeyboardMethods.mIncogStateLoaded )
						{
							KeyboardMethods.mIncogStateLoaded = true;

							if (Hooks.incognito.isRequirementsMet())
							{
								KeyboardMethods.loadIncogState();
							}
						}
					}

					@Override
					public void beforeKeyboardClosed()
					{

					}

					@Override
					public void afterKeyboardConfigurationChanged()
					{
						if (OverlayCommons.mKeyboardOverlay != null)
						{
							View parent = CodeUtils.getTopParent( OverlayCommons.mKeyboardOverlay );
							KeyboardMethods.updateHidePredictionBarAndPadKeyboardTop( parent );
						}

					}
				});

				// Doesn't have any hooks, just sets a value.
				hookLocation(lpparam);

				if (Hooks.incognito.isRequirementsMet())
				{
					Hooks.incognito.add( hookIncognito(lpparam) );
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
}
