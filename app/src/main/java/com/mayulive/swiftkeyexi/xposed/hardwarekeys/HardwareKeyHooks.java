package com.mayulive.swiftkeyexi.xposed.hardwarekeys;

import android.util.Log;
import android.view.KeyEvent;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.RemappedKey;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

/**
 * Created by Roughy on 3/18/2018.
 */

public class HardwareKeyHooks
{
	private static String LOGTAG = ExiModule.getLogTag(HardwareKeyHooks.class);

	private static XC_MethodHook.Unhook hookHardKeyDown()
	{
		return XposedBridge.hookMethod(HardwareKeyClassManager.keyboardServiceClass_keyDownMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				if (HardwareKeyMethods.mHardwareShortcutsConfigured)
				{
					int keycode = (int)param.args[0];
					KeyEvent origEvent = (KeyEvent)param.args[1];
					KeyEvent event = origEvent;

					int scancode = 0;
					if (event != null)
						scancode = event.getScanCode();

					if (HardwareKeyMethods.handleHotkeyKeyDown(keycode,scancode))
					{
						param.setResult(true);
					}
				}
			}
		});
	}

	private static XC_MethodHook.Unhook hookHardKeyUp()
	{
		return XposedBridge.hookMethod(HardwareKeyClassManager.keyboardServiceClass_keyUpMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				//Log.i(LOGTAG,"KEY UP");
				int keycode = (int)param.args[0];
				KeyEvent event = (KeyEvent)param.args[1];

				int scancode = 0;
				if (event != null)
					scancode = event.getScanCode();

				HardwareKeyMethods.handleHotkeyUp(keycode,scancode);
			}
		});
	}


	public static boolean HookAll(final PackageTree lpparam)
	{
		try
		{
			HardwareKeyClassManager.doAllTheThings(lpparam);

			if (Hooks.hardwareKeys_base.isRequirementsMet())
			{

				Hooks.hardwareKeys_base.add(hookHardKeyDown());
				Hooks.hardwareKeys_base.add(hookHardKeyUp());


				Settings.addOnSettingsUpdatedListener( () -> HardwareKeyMethods.populateHardwareHotkeys() );

			}
		}
		catch(Throwable ex)
		{
			Hooks.hardwareKeys_base.invalidate(ex, "Failed to Hook");
			return false;
		}

		return true;
	}
}


