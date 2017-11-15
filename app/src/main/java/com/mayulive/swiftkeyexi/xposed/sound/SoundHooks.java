package com.mayulive.swiftkeyexi.xposed.sound;

import android.content.SharedPreferences;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

/**
 * Created by Roughy on 11/13/2017.
 */

public class SoundHooks
{

	private static String LOGTAG = ExiModule.getLogTag(SoundHooks.class);

	public static XC_MethodHook.Unhook hookPlaySound(PackageTree param )
	{

		return XposedBridge.hookMethod(SoundClassManager.keySoundClass_playSoundMethod, new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				if (Settings.USE_CUSTOM_KEYPRESS_SOUND) //Custom sound
				{
					int soundId = (int)param.args[0];
					SoundMethods.playSound(soundId);
					param.setResult(null);
				}
			}
		});
	}

	public static void hookAll(final PackageTree param)
	{
		try
		{
			SoundClassManager.doAllTheThings(param);
			if (Hooks.soundHooks_base.isRequirementsMet())
			{

				Hooks.soundHooks_base.add( hookPlaySound(param) );

				KeyboardMethods.addKeyboardEventListener(new KeyboardMethods.KeyboardEventListener()
				{
					@Override public void afterKeyboardConfigurationChanged() {}
					@Override public void beforeKeyboardClosed() { }
					@Override public void keyboardInvalidated() {}

					@Override
					public void beforeKeyboardOpened()
					{
						SoundMethods.updateCustomSound();
					}
				});

				KeyboardMethods.addOnSwiftkeySharedPrefChangedListener(new SharedPreferences.OnSharedPreferenceChangeListener()
				{
					@Override
					public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
					{
						if (key.equals(SoundMethods.SOUND_FEEDBACK_SLIDER_PREF_KEY))
						{
							SoundMethods.updateKeypressVolume();
						}

					}
				});
			}
		}
		catch (Throwable ex)
		{
			Hooks.soundHooks_base.invalidate(ex, "Failed to hook");
		}




	}

}
