package com.mayulive.swiftkeyexi.xposed.emoji;

import android.content.Context;
import android.content.res.ColorStateList;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.res.XmlResourceParser;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiCache;
import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.emoji.EmojiFragment;
import com.mayulive.swiftkeyexi.main.emoji.EmojiModifiersPopup;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.EmojiPanelPagerAdapter;
import com.mayulive.swiftkeyexi.main.emoji.EmojiPanelView;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.util.view.FixedViewPager;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.main.emoji.EmojiPanelTabLayout;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class EmojiHooks 
{

	private static String LOGTAG = ExiModule.getLogTag(EmojiHooks.class);

	public static Set<XC_MethodHook.Unhook> hookEmojiPanel(final PackageTree packageParam) throws NoSuchMethodException
	{
		HashSet<XC_MethodHook.Unhook> hooks = new HashSet<>();


		{
			//XposedBridge.hookAllConstructors(emojiPanelClass, new XC_MethodHook()
			hooks.add( XposedBridge.hookMethod(EmojiClassManager.emojiPanel_staticConstructorMethod, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					KeyboardMethods.mIsInEmojiPanel = true;

					if ( KeyboardMethods.getEmojiPanelSizeModifier() != 1.0f )
					{
						// Bad idea to do here?
						KeyboardMethods.forceKeyboardResize();
					}

				}

				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						/////////////////////////////////////////////
						// Needed for resize
						/////////////////////////////////////////////

						if (Settings.EMOJI_PANEL_ENABLED)
						{

							ViewGroup thiz = (ViewGroup) param.getResult();
							EmojiHookCommons.mEmojiTopRelative = thiz;

							int emojiTopBarID = thiz.getResources().getIdentifier("emoji_top_bar", "id", ExiXposed.HOOK_PACKAGE_NAME);
							ViewGroup emojiTopBar = thiz.findViewById(emojiTopBarID);

							//These are the two views we want to replace.
							int pagerID = thiz.getResources().getIdentifier("emoji_pager", "id", ExiXposed.HOOK_PACKAGE_NAME);
							int titlesID = thiz.getResources().getIdentifier("emoji_tabs", "id", ExiXposed.HOOK_PACKAGE_NAME);

							View pagerView = thiz.findViewById(pagerID);
							View titlesView = thiz.findViewById(titlesID);


							pagerView.setVisibility(View.GONE);
							titlesView.setVisibility(View.GONE);


							Context context = thiz.getContext();

							boolean reuse = 	EmojiHookCommons.mEmojiPanelTabs != null &&
									EmojiHookCommons.mEmojiPanelAdapter != null &&
									EmojiHookCommons.mEmojiPanelPager != null;


							//Reuse existing views of they exist
							if (reuse)
							{
								try
								{
									//Which basically just involves removing them from their parents
									((ViewGroup) EmojiHookCommons.mEmojiPanelTabs.getParent()).removeView(EmojiHookCommons.mEmojiPanelTabs);
									((ViewGroup) EmojiHookCommons.mEmojiPanelPager.getParent()).removeView(EmojiHookCommons.mEmojiPanelPager);
								}
								catch (Exception ex)
								{
									//Huh, that's strange. Something must have changed.
									//I'm not really sure why they're still attached anyway.
									//No worry, just re-create instead.
									Log.e(LOGTAG, "Failed to reuse emoji panel. This is fine, might leak some memory.");
									ex.printStackTrace();
									reuse = false;
								}
							}

							if (!reuse)
							{
								// Sits in a constraint layout. Adding it at pos 0 with match parent will make it fill it.
								// It is wrapped in a framelayout below so we can add enough margin so the top tablayout doesn't cover it.
								EmojiHookCommons.mEmojiPanelPager = new FixedViewPager(context);

								LinearLayout.LayoutParams pagerParams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT  );

								EmojiHookCommons.mEmojiPanelPager.setLayoutParams( new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ) );

								EmojiHookCommons.mEmojiPanelPager.setLayoutParams( pagerParams );

								EmojiHookCommons.mEmojiPanelAdapter = new EmojiPanelPagerAdapter(EmojiHookCommons.mPanelItems, EmojiFragment.EmojiPanelType.KEYBOARD, false);
								EmojiHookCommons.mEmojiPanelAdapter.setOnItemClickListener(new EmojiPanelView.OnEmojiItemClickListener()
								{
									@Override
									public void onClick(DB_EmojiItem item, View view, EmojiPanelView panelView, DB_EmojiPanelItem panel, int position)
									{
										EmojiHookCommons.handleEmojiClick(panel.get_items().get(position), panel.get_style(), panel.get_source() == EmojiPanelItem.PANEL_SOURCE.RECENTS);
									}

									@Override
									public void onLongPress(DB_EmojiItem  item, View view, EmojiPanelView panelView, DB_EmojiPanelItem panel, int position)
									{
										if (item.get_modifiers_supported())
										{
											EmojiModifiersPopup popup = new EmojiModifiersPopup(view.getContext(), item.get_text());
											popup.setOnEmojiClickedListener(new EmojiModifiersPopup.OnEmojiClickedListener()
											{
												@Override
												public void onEmojiClicked(String emoji, String modifier)
												{
													DB_EmojiItem newItem = new DB_EmojiItem(emoji);
													newItem.set_modifiers_supported(false);	//Recents panel should only display the chosen emoji
													newItem.set_type(EmojiItem.EmojiType.CONTAINS_EMOJI);

													EmojiHookCommons.handleEmojiClick(newItem, 0, false);

													OverlayCommons.clearPopups();
												}
											});
											EmojiModifiersPopup.showInOverlay(popup,OverlayCommons.mKeyboardOverlay, EmojiHookCommons.mEmojiTopRelative, view);
										}
									}

								});


								EmojiHookCommons.mEmojiPanelPager.setAdapter(EmojiHookCommons.mEmojiPanelAdapter);

								EmojiHookCommons.mEmojiPanelPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
								{
									@Override
									public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
									{

									}

									@Override
									public void onPageSelected(int position)
									{
										EmojiHookCommons.mLastPanelIndex = position;
									}

									@Override
									public void onPageScrollStateChanged(int state)
									{

									}
								});


								EmojiHookCommons.mEmojiPanelTabs = new EmojiPanelTabLayout(context);

								// Center vertically
								LinearLayout.LayoutParams tabsParamas = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );
								tabsParamas.gravity = Gravity.CENTER;
								EmojiHookCommons.mEmojiPanelTabs.setLayoutParams(tabsParamas);

								EmojiHookCommons.mEmojiPanelTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
								EmojiHookCommons.mEmojiPanelTabs.setTabGravity(TabLayout.GRAVITY_CENTER);

								EmojiHookCommons.mEmojiPanelAdapter.setProvidePageTitles(false);
								EmojiHookCommons.mEmojiPanelAdapter.setupWithFixedTabLayout(EmojiHookCommons.mEmojiPanelTabs);

								EmojiHookCommons.mEmojiPanelTabs.setupWithViewPager(EmojiHookCommons.mEmojiPanelPager);

								// Since are are match_parenting the parent constraintlayout, we need to know the size of the top bar
								// so we can push ourselves out from undeneath it. This is nasty, but works.
								EmojiHookCommons.mEmojiPanelTabs.setOnMeasureListener(new EmojiPanelTabLayout.OnMeasuredListener()
								{
									@Override
									public void onMeasured(int width, int height)
									{
										EmojiHookCommons.mEmojiPanelPager.setPadding(0, height, 0, 0);
									}
								});

							}


							{

								/////////////
								// Tabs
								/////////////

								// Now a linear layout makes upe the top bar, with the bakc button and tabs inside of it. a lot simpler.
								emojiTopBar.addView( EmojiHookCommons.mEmojiPanelTabs );

								////////////////
								// Pager
								////////////////

								thiz.addView(EmojiHookCommons.mEmojiPanelPager,0);
							}

						}


					}
					catch (Throwable ex)
					{
						Hooks.emojiHooks_base.invalidate(ex, "Unexpected problem in Emoji Panel hook");
					}
				}
			}));
		}

		if (EmojiClassManager.emojiPanel_onAttachedToWindowMethod != null)
		{
			hooks.add( XposedBridge.hookMethod(EmojiClassManager.emojiPanel_onAttachedToWindowMethod, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param)
				{
					if ( Settings.EMOJI_PANEL_ENABLED && KeyboardMethods.getEmojiPanelSizeModifier() != 1.0f )
					{
						ViewGroup thiz = (ViewGroup) param.thisObject;

						int emojiTopBarID = thiz.getResources().getIdentifier("emoji_top_bar", "id", ExiXposed.HOOK_PACKAGE_NAME);

						ViewGroup emojiTopBar = thiz.findViewById(emojiTopBarID);

						if (emojiTopBar != null)
						{
							ViewGroup.LayoutParams topParams =  emojiTopBar.getLayoutParams();
							if (topParams != null)
							{
								int height = topParams.height;
								float modifier = KeyboardMethods.getKeyboardSizeModifier() / KeyboardMethods.getEmojiPanelSizeModifier();
								height *= modifier;

								topParams.height = height;
								emojiTopBar.setLayoutParams(topParams);
							}
						}
					}
				}
			}));
		}
		else
		{
			Log.e(LOGTAG, "EmojiClassManager.emojiPanel_onAttachedToWindowMethod was null, top bar size cannot be adjusted.");
		}

		return hooks;
	}

	public static Set<XC_MethodHook.Unhook> hookResourceLookup( PackageTree param) throws NoSuchMethodException, NoSuchFieldException
	{
		HashSet<XC_MethodHook.Unhook> resSet = new HashSet();

		Method getLayoutMethod = ProfileHelpers.findFirstMethodByName( android.content.res.Resources.class.getDeclaredMethods(), "getLayout" );

		resSet.add( XposedBridge.hookMethod( getLayoutMethod, new XC_MethodHook()
		{
			boolean inHook = false;

			@Override
			protected void beforeHookedMethod(MethodHookParam param)
			{
				if (inHook)
					return;

				inHook = true;

				switch ( (int) param.args[0] )
				{
					case R.layout.design_layout_tab_icon:
					case R.layout.design_layout_tab_text:
					{
						try
						{
							XmlResourceParser parser = ContextUtils.getModuleContext().getResources().getLayout( (int) param.args[0] );
							param.setResult(parser);
						}
						catch ( Exception ex )
						{
							Log.e(LOGTAG, "Problem hooking layout inflate");
							ex.printStackTrace();
						}

					}


					default:
						break;

				}

				inHook = false;

			}
		}));


		Method stateListMethod = ProfileHelpers.findFirstMethodByName( androidx.appcompat.widget.TintTypedArray.class.getDeclaredMethods(),  "getColorStateList");

		// Note that we are getting the class loader of the module ( exi ), not swiftkey.
		resSet.add( XposedBridge.hookMethod(stateListMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param)
			{
				// As of writing it is only looking for a tint list thing, which we don't need.
				// culprit is appcompat imageview in the emoji panel
				// Check here if things go weird with that.
				param.setResult( new ColorStateList( new int[0][0], new int[0] ) );
			}
		}));

		return resSet;
	}

	public static Set<XC_MethodHook.Unhook> hookCheckAppCompat(PackageTree param) throws NoSuchFieldException
	{
		//TabLayout (design lib) requires that the parent theme extends AppCompat. Swiftkey does not.
		//This used to work fine, but suddenly started trggering the check. Not a support lib change, can't figure it out.
		//
		//Since we can't track down what broke it, we'll just... preventing the exception instead.
		//This is fine.

		HashSet<XC_MethodHook.Unhook> hooks = new HashSet<>();


		//Note that we are using getClass().getClassLoader() instead of the classloader stored in param. This is because the class belongs to us,
		//not swiftkey, and is thus not present in the other classloader.
		Class themeUtilsClass = ClassHunter.loadClass("com.google.android.material.internal.ThemeEnforcement", param.getClass().getClassLoader() );
		Method ThemeUtilsClass_checkAppCompatThemeMethod = ProfileHelpers.firstMethodByName(themeUtilsClass.getDeclaredMethods(), "checkTheme");
		Method ThemeUtilsClass_checkTextAppearance = ProfileHelpers.firstMethodByName(themeUtilsClass.getDeclaredMethods(), "checkTextAppearance");

		hooks.add(XposedBridge.hookMethod(ThemeUtilsClass_checkAppCompatThemeMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				//I am okay with this.
				param.setResult(null);
			}
		}));


		hooks.add(XposedBridge.hookMethod(ThemeUtilsClass_checkTextAppearance, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				//I am okay with this.
				param.setResult(null);
			}
		}));



		return hooks;

	}


	private static String lastAuthority = null;

	public static Set<XC_MethodHook.Unhook> hookGifSafesearch( PackageTree param) throws NoSuchMethodException
	{


		HashSet<XC_MethodHook.Unhook> returnSet = new HashSet<>();

		returnSet.add(XposedBridge.hookMethod( EmojiClassManager.uriBuilderSetAuthorityMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param)
			{

				lastAuthority = (String)param.args[0];
			}
		}));


		returnSet.add(XposedBridge.hookMethod( EmojiClassManager.uriBuilderAppendParameterMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param)
			{
				if (Settings.DISPLAY_NSFW_GIFS || Settings.DISPLAY_GIFS_FROM_MORE_SOURCES)
				{
					try
					{
						String parameter = (String)param.args[0];

						boolean isSafeSearch = Settings.DISPLAY_NSFW_GIFS 			&& 	parameter.equals("safeSearch");
						boolean isLicense = Settings.DISPLAY_GIFS_FROM_MORE_SOURCES && 	parameter.equals("license");

						if ( (isLicense || isSafeSearch ) && lastAuthority != null && lastAuthority.equals("www.bingapis.com"))
						{

							if (isSafeSearch)
							{
								param.args[1] ="Off";
							}

							if (isLicense)
							{
								param.args[1] ="All";
							}

						}
					}
					catch ( Throwable ex)
					{
						Hooks.gifHooksNSFW.invalidate(ex, "Something went wrong changing gif safesearch value");
						ex.printStackTrace();
					}
				}

			}
		}));

		return returnSet;

	}

	public static boolean hookAll(final PackageTree param)
	{
		//Super simple so just bunch everything into priority
		return true;
	}

	public static boolean hookPriority(final PackageTree param)
	{
        try
        {
        	EmojiClassManager.doAllTheThings(param);

			if (Hooks.emojiHooks_base.isRequirementsMet())
			{
				//Various non-swiftkey fixes
				Hooks.emojiHooks_base.addAll( hookCheckAppCompat(param) );
				Hooks.emojiHooks_base.addAll( hookResourceLookup(param) );

				//Swiftkey hooks
				Hooks.emojiHooks_base.addAll( hookEmojiPanel(param) );

				Settings.addOnSettingsUpdatedListener(new Settings.OnSettingsUpdatedListener()
				{
					@Override
					public void OnSettingsUpdated()
					{
						EmojiHookCommons.loadEmoji();
					}
				});

				KeyboardMethods.addKeyboardEventListener(new KeyboardMethods.KeyboardEventListener()
				{

					@Override
					public void beforeKeyboardOpened()
					{

					}

					@Override
					public void afterKeyboardOpened()
					{

					}

					@Override
					public void beforeKeyboardClosed()
					{
						//EmojiCommons.saveRecents();
						//Handled by shared thread in loadpacakgehook
					}


					@Override
					public void afterKeyboardConfigurationChanged()
					{
						//Required to update width of items.
						//If Adapter and other related views are non-null, they will be reused.
						//Also clear non-shared cache, as item width may have changed.
						EmojiCache.clearMappedCaches();
						EmojiHookCommons.mEmojiPanelAdapter = null;
					}
				});

			}

			try
			{
				if (Hooks.gifHooksNSFW.isRequirementsMet())
				{
					Hooks.gifHooksNSFW.addAll(hookGifSafesearch(param));
				}
			}
			catch(Throwable ex)
			{
				Hooks.gifHooksNSFW.invalidate(ex, "Failed to hook");
			}
        }
        catch(Throwable ex)
        {
			Hooks.emojiHooks_base.invalidate(ex, "Failed to hook");
        	return false;
        }

		return true;
	}
	
	
}
