package com.mayulive.swiftkeyexi.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;

public class SwiftkeyBroadcastListener extends BroadcastReceiver
{
	public static final String INCOG_ENABLE_INTENT = "com.mayulive.swiftkeyexi.INCOGNITO_ON";
	public static final String INCOG_DISABLE_INTENT = "com.mayulive.swiftkeyexi.INCOGNITO_OFF";
	private static String LOGTAG = ExiModule.getLogTag(SwiftkeyBroadcastListener.class);

	private static SwiftkeyBroadcastListener mService;

	@Override
	public void onReceive(final Context context, final Intent intent)
	{
		if (intent != null)
		{
			if (intent.getAction() != null)
			{
				Log.i(LOGTAG, "Swiftkey intent received: "+intent.getAction());

				switch( intent.getAction() )
				{
					case INCOG_ENABLE_INTENT:
					case INCOG_DISABLE_INTENT:
					{

						KeyboardMethods.setIncogState( intent.getAction() == INCOG_ENABLE_INTENT );
						break;
					}

					default:
					{
						break;
					}
				}
			}
		}
	}


	// Do this from Swiftkey
	public static void startService( Context context )
	{
		if (mService == null)
		{
			Log.i(LOGTAG, "Starting service");

			IntentFilter filter = new IntentFilter(INCOG_ENABLE_INTENT);
			filter.addAction(INCOG_DISABLE_INTENT);

			mService = new SwiftkeyBroadcastListener();
			context.registerReceiver(mService, filter);
		}

	}




}
