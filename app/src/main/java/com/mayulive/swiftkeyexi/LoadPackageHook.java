package com.mayulive.swiftkeyexi;

import android.util.Log;

import com.mayulive.swiftkeyexi.main.emoji.data.EmojiModifiers;
import com.mayulive.swiftkeyexi.shared.SharedStyles;
import com.mayulive.swiftkeyexi.xposed.ExiXposed;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.AndroidHooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileCache;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class LoadPackageHook implements IXposedHookLoadPackage
{
	private static String LOGTAG = ExiModule.getLogTag(LoadPackageHook.class);

	@Override
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable
	{
		//Swiftkey
		if ( lpparam.packageName.equals( ExiModule.SWIFTKEY_BETA_PACKAGE_NAME ) || lpparam.packageName.equals( ExiModule.SWIFTKEY_PACKAGE_NAME ) )
		{
			ExiXposed.setPackage( lpparam.packageName );

			//Some assets as shared between the app and xposed, requirement some management
			SharedStyles.setStyleContext(SharedStyles.StyleContext.HOOK);	//Defaults to app

			int CRC32 = ClassHunter.getApkCRC32(lpparam.appInfo.sourceDir);
			XposedBridge.log(LOGTAG+", "+"Module loaded in "+lpparam.packageName);
			Log.i(
					LOGTAG,
					"Module loaded in "+ExiXposed.HOOK_PACKAGE_NAME+
							", CRC: "+Integer.toHexString(CRC32)+
							", Module version: "+BuildConfig.VERSION_NAME);

			PackageTree classTree = new PackageTree(lpparam.appInfo.sourceDir, lpparam.classLoader);

			ProfileCache.setSaveLocation(lpparam.appInfo.dataDir+"/files/EXI_CLASS_CACHE_"+ ExiXposed.HOOK_PACKAGE_NAME);

			//Automatically reset class cache on update of module or swiftkey
			ClassHunter.MODULE_SIGNATURE = BuildConfig.VERSION_CODE;
			ClassHunter.HOOK_SIGNATURE = CRC32;

			ProfileCache.loadCache();

			Hooks.hookAll(classTree);

			//Cache is saved once all async work has finished, so add a callback.
			//Callbacks are called on the main thread, so no worrying about race conditions.
			Hooks.addOnWorkFinishedListener( () -> ProfileCache.saveCache() );

			XposedBridge.log(LOGTAG+", "+"Finished priority work in"+lpparam.packageName);
			Log.i(LOGTAG, "Finished priority work in "+ExiXposed.HOOK_PACKAGE_NAME);

			//Bonus: While it's convenient to load classes only when they are needed,
			//they cause a bit of a hiccup if they have a long initialization process.
			//Force load stuff here
			EmojiModifiers.forceInit();
		}
		else if (lpparam.packageName.equals( AndroidHooks.SYSTEM_SERVER_PACKAGE ))
		{
			Log.i(LOGTAG, "In system server context");
			AndroidHooks.hookAll(lpparam.classLoader);
		}
	}
}



