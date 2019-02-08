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
	private static String LOGTAG = ExiModule.getLogTag(SwiftkeyBroadcastListener.class);

	public static final String INCOG_ENABLE_INTENT = "com.mayulive.swiftkeyexi.INCOGNITO_ON";
	public static final String INCOG_DISABLE_INTENT = "com.mayulive.swiftkeyexi.INCOGNITO_OFF";

	public static final String SET_THEME_INTENT = "com.mayulive.swiftkeyexi.SET_THEME";

	public static final String SET_THEME_EXTRA_THEME_HASH = "THEME_HASH";

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

					case SET_THEME_INTENT:
					{
						String hash = intent.getStringExtra(SET_THEME_EXTRA_THEME_HASH);
						if (hash != null)
						{
							KeyboardMethods.setThemeByHash(hash);
						}
						else
						{
							Log.e(LOGTAG, "Set theme intent received but hash was null");
						}

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
			filter.addAction(SET_THEME_INTENT);

			mService = new SwiftkeyBroadcastListener();
			context.registerReceiver(mService, filter);
		}

	}




}
