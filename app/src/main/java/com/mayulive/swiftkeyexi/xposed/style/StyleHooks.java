package com.mayulive.swiftkeyexi.xposed.style;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.SharedTheme;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.OverlayCommons;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class StyleHooks
{

	private static String LOGTAG = ExiModule.getLogTag(StyleHooks.class);


	private static XC_MethodHook.Unhook hookTheme(PackageTree param)
	{

		return XposedBridge.hookMethod(StyleClassManager.drawableLoaderClass_loadEmojiDrawableMethod, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param)
			{
				try
				{
					int colorResource = (Integer)param.getResult();

					if (!StyleCommons.toolbarColorResourceSet)
					{
						StyleCommons.setToolbarColorResources( ContextUtils.getHookContext() );
					}

					int newValue = -1;
					if ( colorResource == StyleCommons.TOOLBAR_DARK_SEARCH_TEXT_COLOR_RESOURCE)
					{
						newValue = SharedTheme.DARK_THEME_IDENTIFIER;
					}
					else if ( colorResource == StyleCommons.TOOLBAR_LIGHT_SEARCH_TEXT_COLOR_RESOURCE )
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


						if (OverlayCommons.mKeyboardOverlay != null)
						{
							StyleCommons.updateRaisedBackground();

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
		}
		catch(Throwable ex)
		{
			Hooks.emojiHooks_base.invalidate(ex, "Failed to hook");
			return false;
		}

		return true;
	}

}
