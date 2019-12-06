package com.mayulive.swiftkeyexi.xposed.emoji;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiCache;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.emoji.EmojiFragment;
import com.mayulive.swiftkeyexi.main.emoji.EmojiModifiersPopup;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.emoji.EmojiPanelPagerAdapter;
import com.mayulive.swiftkeyexi.main.emoji.EmojiPanelView;
import com.mayulive.swiftkeyexi.util.CodeUtils;
import com.mayulive.swiftkeyexi.util.DimenUtils;
import com.mayulive.swiftkeyexi.util.view.FixedViewPager;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.main.emoji.EmojiPanelTabLayout;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

import static com.mayulive.swiftkeyexi.util.ContextUtils.getModuleContext;

public class EmojiHooks 
{

	private static String LOGTAG = ExiModule.getLogTag(EmojiHooks.class);

	public static XC_MethodHook.Unhook hookEmojiPanel(final PackageTree packageParam) throws NoSuchMethodException
	{
		{
			//XposedBridge.hookAllConstructors(emojiPanelClass, new XC_MethodHook()
			return XposedBridge.hookMethod(EmojiClassManager.emojiPanel_staticConstructorMethod, new XC_MethodHook()
			{
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable
				{
					try
					{
						if (Settings.EMOJI_PANEL_ENABLED)
						{
							ViewGroup thiz = (ViewGroup) param.getResult();
							EmojiHookCommons.mEmojiTopRelative = thiz;

							//These are the two views we want to replace.
							int pagerID = thiz.getResources().getIdentifier("emoji_pager", "id", ExiXposed.HOOK_PACKAGE_NAME);
							int titlesID = thiz.getResources().getIdentifier("emoji_tabs", "id", ExiXposed.HOOK_PACKAGE_NAME);

							int emojiTopBarID = thiz.getResources().getIdentifier("emoji_top_bar", "id", ExiXposed.HOOK_PACKAGE_NAME);

							View pagerView = thiz.findViewById(pagerID);
							View titlesView = thiz.findViewById(titlesID);

							ViewGroup emojiTopBarView = thiz.findViewById(emojiTopBarID);


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

								//Before lollipop, tablayout doesn't size its tabs properly.
								//I mean it's pretty broken after lollipop too but you get the idea.
								//if (!VersionTools.isLollipopOrGreater())
								//Turns out this is a tablet thing, not a kitkat thing.
								{
									EmojiResources.EmojiPixelDimensions dimens = EmojiResources.getDimensions(context);

									//If no min is set they're all super wide,
									//if set to 0 they're all tiny.
									EmojiHookCommons.mEmojiPanelTabs.setTabMinWidth( (int)(dimens.default_singleEmojiWidth * 1.1f) );
								}


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
								emojiTopBarView.addView( EmojiHookCommons.mEmojiPanelTabs );

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
			});
		}
	}


	public static Set<XC_MethodHook.Unhook> hookResourceLookup( PackageTree param) throws NoSuchMethodException
	{
		//ViewPager fails to fetch these two when spawned  inside the hook context.
		//While I'm sure returning random values affects something, at least it doesn't crash.
		//We can't just intercept and catch all exceptions here, as other bits of
		//code sometimes rely on catching the exception.
		final int res1 = R.dimen.design_tab_text_size_2line;
		final int res2 =  R.dimen.design_tab_scrollable_min_width;

		HashSet resSet = new HashSet();

		Class resourcesClass = ClassHunter.loadClass("android.content.res.Resources", param.getClassLoader());
		{

			resSet.add( XposedHelpers.findAndHookMethod(resourcesClass, "getDimensionPixelSize", int.class, new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param)
				{
					int id = (int)param.args[0];

					if (id == res1 || id == res2)
					{
						//param.setResult((int)10);
						param.setResult( (int) getModuleContext().getResources().getDimension(id) );
					}

				}
			}));
		}


		// Note that we are getting the class loader of the module ( exi ), not swiftkey.
		Class moduleAppCompatResourcesClass = ClassHunter.loadClass("android.support.v7.content.res.AppCompatResources", EmojiHooks.class.getClassLoader());

		resSet.add( XposedHelpers.findAndHookMethod(moduleAppCompatResourcesClass, "getColorStateList", Context.class, int.class, new XC_MethodHook()
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

	public static XC_MethodHook.Unhook hookCheckAppCompat(PackageTree param)
	{
		//TabLayout (design lib) requires that the parent theme extends AppCompat. Swiftkey does not.
		//This used to work fine, but suddenly started trggering the check. Not a support lib change, can't figure it out.
		//
		//Since we can't track down what broke it, we'll just... preventing the exception instead.
		//This is fine.

		//Note that we are using getClass().getClassLoader() instead of the classloader stored in param. This is because the class belongs to us,
		//not swiftkey, and is thus not present in the other classloader.
		Class themeUtilsClass = ClassHunter.loadClass("android.support.design.widget.ThemeUtils", param.getClass().getClassLoader() );
		Method ThemeUtilsClass_checkAppCompatThemeMethod = ProfileHelpers.firstMethodByName(themeUtilsClass.getDeclaredMethods(), "checkAppCompatTheme");


		return XposedBridge.hookMethod(ThemeUtilsClass_checkAppCompatThemeMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				//I am okay with this.
				param.setResult(null);
			}
		});


	}



	//This is actually the the individual icon views in our own TabLayout.
	//At some point it inflates a layout consisting of a single imageview, but fails:
	//"Caused by: java.lang.ClassNotFoundException: Didn't find class "android.view.set" on path"
	//During an update it checks if if mIconView is null, and inflates it if necessary.
	//Fix by hooking constructor and setting it ourselves.
	//Same for the textview, it just fails to find that one (which is what should happen)
	public static Set<XC_MethodHook.Unhook> hookTabView() throws NoSuchFieldException
	{

		Set<XC_MethodHook.Unhook> returnSet = new HashSet<>();

		Class TabLayoutClass = TabLayout.class;
		Class[] innerClasses = TabLayoutClass.getDeclaredClasses();
		Class tabViewClass = null;
		for (int i = 0; i < innerClasses.length; i++)
		{
			//Log.e("###", "Checking class: "+innerClasses[i].getSimpleName());
			if (innerClasses[i].getSimpleName().equals("TabView"))
			{
				tabViewClass = innerClasses[i];
				break;
			}
		}


		final Field tabViewClass_mIconViewField = tabViewClass.getDeclaredField("mIconView");
		tabViewClass_mIconViewField.setAccessible(true);
		final Field tabViewClass_mTextViewField = tabViewClass.getDeclaredField("mTextView");
		tabViewClass_mTextViewField.setAccessible(true);


		//XposedHelpers.findAndHookMethod(EmojiClassManager.keyboardServiceClass, "onStartInputView", EditorInfo.class, boolean.class, new XC_MethodHook()
		returnSet.addAll( XposedBridge.hookAllConstructors(tabViewClass, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				//Only has a single constructor taking a context

				//Context context =(Context)param.args[0];
				View thiz = (View)param.thisObject;

				Context context = thiz.getContext();
				Context moduleContext = getModuleContext();


				//////////////
				//Icon view
				//////////////
				int viewSize = (int)moduleContext.getResources().getDimension(R.dimen.design_layout_tab_icon_size);
				ImageView imageView = new ImageView(context);
				{
					ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(viewSize,viewSize);
					imageView.setLayoutParams(params);
					imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
				}

				tabViewClass_mIconViewField.set(thiz, imageView);


				/////////////
				//Text view
				/////////////

				//We use a custom view, so none of this actually matters to us.

				TextView textView = new TextView(context);
				{
					ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
					textView.setLayoutParams(params);
					textView.setEllipsize(TextUtils.TruncateAt.END);
					textView.setGravity(Gravity.CENTER);
					textView.setMaxLines(2);

					textView.setText("hookButtonOrder");
					textView.setTextSize(56);
					textView.setTextColor(Color.RED);

				}

				tabViewClass_mTextViewField.set(thiz, textView);

				////////////////////
				//More broken stuff
				////////////////////


			}
		}));



		//tablayout views are not being added in hook context.
		//Temp workaround is to just add them manually
		//We actually use a custom view now so this bit isn't needed,
		//but I tried to remove it once and it broke so... voodoo.
		returnSet.addAll( XposedBridge.hookAllMethods(tabViewClass, "update", new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				LinearLayout view = (LinearLayout) param.thisObject;

				view.setVisibility(View.VISIBLE);

				ImageView imageView = (ImageView) tabViewClass_mIconViewField.get(view);

				//Log.e("###","Child count: "+view.getChildCount());
				if (view.getChildCount() == 0)
				{
					view.addView(imageView);
				}
			}
		}) );

		return returnSet;
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
							Object[] newArgs = param.args;
							if (isSafeSearch)
								newArgs[1] ="Off";

							if (isLicense)
								newArgs[1] ="All";

							Method superMethod = (Method)param.method;
							Object result = superMethod.invoke(param.thisObject, newArgs);
							param.setResult(result);
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
				Hooks.emojiHooks_base.add( hookCheckAppCompat(param) );
				Hooks.emojiHooks_base.addAll( hookTabView() );
				Hooks.emojiHooks_base.addAll( hookResourceLookup(param) );

				//Swiftkey hooks
				Hooks.emojiHooks_base.add( hookEmojiPanel(param) );

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
