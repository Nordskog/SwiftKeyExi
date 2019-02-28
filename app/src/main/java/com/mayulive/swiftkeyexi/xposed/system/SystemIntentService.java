package com.mayulive.swiftkeyexi.xposed.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.system.input.InputMethods;

/**
 * Created by Roughy on 3/23/2018.
 */

public class SystemIntentService extends BroadcastReceiver
{

	private static final String LOGTAG = ExiModule.getLogTag(SystemIntentService.class);

	public static final String KEY_MAPPINGS_UPDATED_INTENT = "com.mayulive.swiftkey_SYSTEM_KEY_MAPPINGS_UPDATED";

	public static final String KEYBOARD_STATE_UPDATED_INTENT = "com.mayulive.swiftkey_KEYBOARD_STATE_UPDATED_INTENT";

	public static final String HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD_CHANGED_INTENT = "com.mayulive.swiftkey_HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD_CHANGED";


	/**
	 * True for open, false for closed
	 */
	public static final String EXTRA_KEYBOARD_STATE = "keyboard_state";

	public static final String EXTRA_HARDWARE_REMAP_ONLY_IN_KEYBOARD = "remap_only_in_keyboard";

	private static SystemIntentService mService;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		if ( intent == null )
			return;

		String action = intent.getAction();

		if (action == null)
			return;

		switch (action)
		{
			case Intent.ACTION_BOOT_COMPLETED:
			{
				// Get some settings on boot
				try
				{
					Log.i(LOGTAG, "Loading settings in android context");
					Settings.updateSettingsFromProviderSync(context.getApplicationContext());

					// Keyboard is closed at this point, set to opposite of pref value.
					InputMethods.mKeyRemappingAllowed = !Settings.HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD;

				}
				catch (Throwable ex)
				{
					Log.e(LOGTAG,"Problem loading settings in android context");
					ex.printStackTrace();
				}


				// PASS THROUGH
				// DO NOT BREAK HERE
			}
			case KEY_MAPPINGS_UPDATED_INTENT:
			{

				// Load key mappings on boot and on update

				try
				{
					Log.i(LOGTAG, "Loading remapped keys");
					InputMethods.populateRemappedKeys();
				}
				catch (Throwable ex)
				{
					Log.e(LOGTAG,"Problem loading remapped keys");
					ex.printStackTrace();
				}

				break;
			}

			case HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD_CHANGED_INTENT:
			{

				if ( intent.hasExtra(EXTRA_HARDWARE_REMAP_ONLY_IN_KEYBOARD) )
				{
					Settings.HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD = intent.getBooleanExtra(EXTRA_HARDWARE_REMAP_ONLY_IN_KEYBOARD, false );

					// Keyboard is closed at this point, set to opposite of pref value.
					InputMethods.mKeyRemappingAllowed = !Settings.HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD;
				}
				break;
			}

			case KEYBOARD_STATE_UPDATED_INTENT:
			{

				if ( Settings.HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD )
				{
					if ( intent.hasExtra(EXTRA_KEYBOARD_STATE) )
					{
						InputMethods.mKeyRemappingAllowed = intent.getBooleanExtra(EXTRA_KEYBOARD_STATE, false );
					}
				}


				break;
			}

		}



	}

	public static void startService( Context context )
	{
		IntentFilter filter = new IntentFilter(KEY_MAPPINGS_UPDATED_INTENT);
		filter.addAction(Intent.ACTION_BOOT_COMPLETED);
		filter.addAction(KEYBOARD_STATE_UPDATED_INTENT);
		filter.addAction(HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD_CHANGED_INTENT);

		mService = new SystemIntentService();
		context.registerReceiver(mService, filter);
	}

	public static void sendKeyMappingsUpdatedBroadcast(Context context)
	{
		try
		{
			Intent intent = new Intent();
			intent.setAction(KEY_MAPPINGS_UPDATED_INTENT);
			context.sendBroadcast(intent);
		}
		catch (Throwable ex)
		{
			//Oreo cand and will crash you for sending a global broadcast
			Log.e(LOGTAG, "Failed to request remapped key update");
			ex.printStackTrace();
		}
	}

	public static void sendKeyboardStateUpdatedBroadcast(Context context, boolean state)
	{
		try
		{
			Intent intent = new Intent();
			intent.setAction(KEYBOARD_STATE_UPDATED_INTENT);
			intent.putExtra( EXTRA_KEYBOARD_STATE,  state);
			context.sendBroadcast(intent);
		}
		catch (Throwable ex)
		{
			//Oreo cand and will crash you for sending a global broadcast
			Log.e(LOGTAG, "Failed to broadcast keyboard state update");
			ex.printStackTrace();
		}
	}

	public static void sendRemapOnlyInKeyboardChangedBroadcast(Context context, boolean state)
	{
		try
		{
			Intent intent = new Intent();
			intent.setAction(HARDWARE_KEY_REMAP_ONLY_IN_KEYBOARD_CHANGED_INTENT);
			intent.putExtra( EXTRA_HARDWARE_REMAP_ONLY_IN_KEYBOARD, state);
			context.sendBroadcast(intent);
		}
		catch (Throwable ex)
		{
			//Oreo cand and will crash you for sending a global broadcast
			Log.e(LOGTAG, "Failed to broadcast remap only in keyboard pref change");
			ex.printStackTrace();
		}
	}
}
