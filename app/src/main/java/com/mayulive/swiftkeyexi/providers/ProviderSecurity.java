package com.mayulive.swiftkeyexi.providers;

import android.content.Context;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.MainActivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Roughy on 7/28/2017.
 */

public class ProviderSecurity
{
	private static String LOGTAG = ExiModule.getLogTag(ProviderSecurity.class);

	static Map<Integer,Boolean> mAllowedUids = new HashMap<>();

	static Set<String> mAllowedPackages = new HashSet<>();

	static
	{
		mAllowedPackages.add(ExiModule.SWIFTKEY_PACKAGE_NAME);
		mAllowedPackages.add(ExiModule.SWIFTKEY_BETA_PACKAGE_NAME);
	}

	public static boolean isAllowed(Context context, int uid)
	{
		Boolean allowed = mAllowedUids.get(uid);
		if (allowed != null)
			return allowed;
		else
		{
			String packageName = context.getPackageManager().getNameForUid(uid);
			allowed = mAllowedPackages.contains(packageName);
			mAllowedUids.put(uid, allowed);

			if (!allowed)
			{
				Log.i(LOGTAG, "Not allowing "+packageName+":"+uid);
			}
			else
			{
				Log.i(LOGTAG, "Allowing "+packageName+":"+uid);
			}

			return allowed;
		}
	}


}
