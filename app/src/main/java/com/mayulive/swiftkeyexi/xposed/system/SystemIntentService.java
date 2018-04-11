package com.mayulive.swiftkeyexi.xposed.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.system.input.InputMethods;

/**
 * Created by Roughy on 3/23/2018.
 */

public class SystemIntentService extends BroadcastReceiver
{

	private static String LOGTAG = ExiModule.getLogTag(SystemIntentService.class);

	public static String KEY_MAPPINGS_UPDATED_INTENT = "com.mayulive.swiftkey_SYSTEM_KEY_MAPPINGS_UPDATED";

	private static SystemIntentService mService;

	@Override
	public void onReceive(Context context, Intent intent)
	{
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

	}

	public static void startService( Context context )
	{
		IntentFilter filter = new IntentFilter(KEY_MAPPINGS_UPDATED_INTENT);
		filter.addAction(Intent.ACTION_BOOT_COMPLETED);

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
}
