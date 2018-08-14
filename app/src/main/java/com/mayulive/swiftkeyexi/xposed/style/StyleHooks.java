package com.mayulive.swiftkeyexi.xposed.style;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.SharedTheme;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class StyleHooks
{

	private static String LOGTAG = ExiModule.getLogTag(StyleHooks.class);

	private static XC_MethodHook.Unhook hookBgTheme(PackageTree param)
	{
		return XposedBridge.hookMethod(StyleClassManager.OverlayThemeUtil_getFancyEmojiBackgroundMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param)
			{
				try
				{
					ViewGroup fancyContainer = (ViewGroup) param.args[0];

					if (StyleCommons.bottomBarId == 0)
					{
						StyleCommons.bottomBarId = ContextUtils.getHookContext().getResources().getIdentifier("fancy_bottom_bar", "id", ExiXposed.HOOK_PACKAGE_NAME);
					}

					View bottomBar = fancyContainer.findViewById(StyleCommons.bottomBarId);
					StyleCommons.setCurrentRaisedBackground(bottomBar.getBackground());
					StyleCommons.callThemeChangedListeners(StyleCommons.getCurrentRaisedBackground());
				}
				catch (Throwable ex)
				{
					Log.e(LOGTAG, "Failed to get raised background drawable, emoji panel will now look terrible");
					ex.printStackTrace();
				}
			}
		});
	}

	private static XC_MethodHook.Unhook hookTheme(PackageTree param)
	{

		return XposedBridge.hookMethod(StyleClassManager.drawableLoaderClass_loadEmojiDrawableMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param)
			{
				try
				{
					String lookupString = (String)param.args[0];

					int newValue = -1;
					if ( lookupString.equals(StyleCommons.TOOLBAR_DARK_SEARCH_TEXT_STRING ))
					{
						newValue = SharedTheme.DARK_THEME_IDENTIFIER;
					}
					else if (lookupString.equals(StyleCommons.TOOLBAR_LIGHT_SEARCH_TEXT_STRING ))
					{
						newValue = SharedTheme.LIGHT_THEME_IDENTIFIER;
					}

					if (newValue != -1)
					{
						//Update regardless.
						//This value is 0-1, matching the values that exist in SharedTheme
						SharedTheme.setCurrenThemeType( ContextUtils.getHookContext(), newValue );

						if (newValue != StyleCommons.mTheme)
						{
							StyleCommons.mTheme = newValue;
							StyleCommons.callThemeChangedListeners(newValue);
						}
					}
				}
				catch (Throwable ex)
				{
					Log.e(LOGTAG, "Failed to get dark/light theme identifier. Emoji may look terrible.");
					ex.printStackTrace();
				}
			}
		});
	}



	public static boolean HookAll(final PackageTree param)
	{
		try
		{
			StyleClassManager.doAllTheThings(param);

			if (Hooks.styleHooks_darklight.isRequirementsMet())
			{
				Hooks.styleHooks_darklight.add( hookTheme(param) );
			}

			if (Hooks.styleHooks_raisedbg.isRequirementsMet())
			{
				Hooks.styleHooks_raisedbg.add( hookBgTheme(param) );
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
