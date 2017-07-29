package com.mayulive.swiftkeyexi;

import android.util.Log;

import com.mayulive.swiftkeyexi.xposed.ExiXposed;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ProfileCache;
import com.mayulive.xposed.classhunter.ProfileServer;
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
		if ( !(lpparam.packageName.equals( ExiModule.SWIFTKEY_BETA_PACKAGE_NAME ) || lpparam.packageName.equals( ExiModule.SWIFTKEY_PACKAGE_NAME )) )
            return;

		ExiXposed.HOOK_PACKAGE_NAME = lpparam.packageName;

		XposedBridge.log(LOGTAG+", "+"Module loaded in "+lpparam.packageName);
		Log.i(LOGTAG, "Module loaded in "+ExiXposed.HOOK_PACKAGE_NAME);

		PackageTree classTree = new PackageTree(lpparam.appInfo.sourceDir, lpparam.classLoader);
		ProfileCache.setSaveLocation(lpparam.appInfo.dataDir+"/files/EXI_CLASS_CACHE_"+ ExiXposed.HOOK_PACKAGE_NAME);
		ProfileCache.loadCache();

		Hooks.hookAll(classTree);
		ProfileCache.saveCache();

		XposedBridge.log(LOGTAG+", "+"Finished hooking work in"+lpparam.packageName);
		Log.i(LOGTAG, "Finished hooking work in "+ExiXposed.HOOK_PACKAGE_NAME);
	}
}



