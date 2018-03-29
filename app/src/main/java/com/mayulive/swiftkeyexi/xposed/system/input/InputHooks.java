package com.mayulive.swiftkeyexi.xposed.system.input;

import android.util.Log;
import android.view.KeyEvent;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.AndroidHooks;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

/**
 * Created by Roughy on 3/18/2018.
 */

public class InputHooks
{
	private static String LOGTAG = ExiModule.getLogTag(InputHooks.class);

	private static final int INPUT_EVENT_INJECTION_SYNC_NONE = 0;

	private static XC_MethodHook.Unhook hookKeyEvent()
	{
		Log.i(LOGTAG, "Hooking system server");

		return XposedBridge.hookMethod(InputClassManager.InputManagerServiceClass_interceptKeyBeforeDispatching, new XC_MethodHook()
		{
			KeyEvent mLastInjectedEvent = null;

			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				if (InputMethods.mKeysRemapped)
				{
					try
					{
						KeyEvent event = (KeyEvent) param.args[1];
						KeyEvent origEvent = event;

						//Don't touch injected events
						if ( InputMethods.isInjected(origEvent) )
							return;

						event = InputMethods.handleKeyRemap(event);

						event = InputMethods.applyModifiers(event);

						if (event != origEvent)
						{
							//cancel existing event
							param.setResult(-1);

							mLastInjectedEvent = event;

							//Inject replacement
							Object[] args = new Object[2];
							args[0] = event;
							args[1] = INPUT_EVENT_INJECTION_SYNC_NONE;

							InputClassManager.InputManagerServiceClass_injectInputEvent.invoke(param.thisObject, args);
						}

						//Only needs to be considered if key not remapped
						if (InputMethods.mVirtualLockKeyExists)
							InputMethods.syncLockFlag(event);
					}
					catch ( Throwable ex )
					{
						AndroidHooks.InputManagerHooks_base.invalidate(ex, "Unexpected problem remapping key");
					}
				}
			}
		});
	}


	public static boolean HookAll(final ClassLoader loader)
	{
		try
		{
			InputClassManager.doAllTheThings(loader);

			if (AndroidHooks.InputManagerHooks_base.isRequirementsMet())
			{
				hookKeyEvent();
			}
		}
		catch(Throwable ex)
		{
			AndroidHooks.InputManagerHooks_base.invalidate(ex, "Failed to Hook");
			return false;
		}

		return true;
	}
}


