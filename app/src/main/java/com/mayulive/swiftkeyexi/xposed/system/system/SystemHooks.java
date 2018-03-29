package com.mayulive.swiftkeyexi.xposed.system.system;

import android.content.Context;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.xposed.AndroidHooks;
import com.mayulive.swiftkeyexi.xposed.system.SystemIntentService;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

/**
 * Created by Roughy on 3/18/2018.
 */

public class SystemHooks
{
	private static String LOGTAG = ExiModule.getLogTag(SystemHooks.class);

	private static XC_MethodHook.Unhook hookActivityManager()
	{
		return XposedBridge.hookMethod(SystemClassManager.ActivityManagerClass_Constructor, new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable
			{
				try
				{
					Context context =  (Context) param.args[0];
					ContextUtils.setSystemContext(context);
					SystemIntentService.startService(context);
				}
				catch ( Throwable ex)
				{
					AndroidHooks.SystemHooks_base.invalidate(ex, "Failed to create intent service");
				}


			}
		});
	}

	public static boolean HookAll(final ClassLoader loader)
	{
		try
		{
			SystemClassManager.doAllTheThings(loader);

			if (AndroidHooks.SystemHooks_base.isRequirementsMet())
			{
				hookActivityManager();
			}
		}
		catch(Throwable ex)
		{
			AndroidHooks.SystemHooks_base.invalidate(ex, "Failed to Hook");
			return false;
		}

		return true;
	}
}


