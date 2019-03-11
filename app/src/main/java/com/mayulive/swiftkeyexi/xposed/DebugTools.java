package com.mayulive.swiftkeyexi.xposed;


import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.xposed.classhunter.CachedProfile;
import com.mayulive.xposed.classhunter.ProfileCache;
import com.mayulive.xposed.classhunter.profiles.Profile;

import java.lang.reflect.Field;
import java.util.Map;

public class DebugTools
{
	private static String LOGTAG = ExiModule.getLogTag(DebugTools.class);

	public static boolean DEBUG_PREDICTIONS = false;
	public static boolean DEBUG_POPUPS = false;
	public static boolean DEBUG_KEYS = false;
	public static boolean DEBUG_HITBOXES = false;
	public static boolean DEBUG_PROFILE_MATCHES = true;


	public static <T> void logIfProfileMismatch(T target, Class targetParentClass, Profile<T> profile, String name)
	{
		if (!DEBUG_PROFILE_MATCHES)
			return;

		if (target == null)
		{
			Log.i(LOGTAG, "Target mismatch: NULL");
		}

		float similarity = profile.getSimilarity(target, targetParentClass, 0);
		if (similarity < 1.0)
		{
			Log.i(LOGTAG, "Profile mismatch: "+similarity+" at "+name+", "+target.toString());
		}

	}

	public static void logIfCachedProfileMismatch()
	{
		if (!DEBUG_PROFILE_MATCHES)
			return;

		try
		{
			// I should update ClassHunter to expose this, but pushing an update is a bit to much effort.

			// private static Map<String, CachedProfile> mClassMap = new HashMap<>();

			Field cacheField = ProfileCache.class.getDeclaredField("mClassMap");
			cacheField.setAccessible(true);

			Map<String, CachedProfile> classMap = (Map<String, CachedProfile>) cacheField.get(null);

			for (Map.Entry<String, CachedProfile> entry : classMap.entrySet())
			{
				CachedProfile profile = entry.getValue();

				// Skip stale entries
				if ( !profile.getUsed() )
					continue;

				// These will occasionally be 0, bug in ClassHunter.
				// Does not happen when they're a perfect match though.
				if ( profile.getSimilarity() < 1.0 )
				{
					Log.i(LOGTAG, "Cache Profile mismatch: "+profile.getSimilarity()+". Target: "+ profile.getTargetPath()+", class: "+profile.getClassPath());
				}

			}
		}
		catch ( Throwable ex)
		{
			Log.e(LOGTAG, "Problem checking cached profile mismatches. This is fine.");
			ex.printStackTrace();
		}

	}
}
