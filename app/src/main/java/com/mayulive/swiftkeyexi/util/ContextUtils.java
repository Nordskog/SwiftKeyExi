package com.mayulive.swiftkeyexi.util;

import android.app.AndroidAppHelper;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;

public class ContextUtils
{
	public static Context mHookContext = null;
	public static  Context mModuleContext = null;	
	
	public static Context getHookContext()
	{
		if (mHookContext != null)
			return mHookContext;
		
		mHookContext = AndroidAppHelper.currentApplication();
		
		return mHookContext;
			
	}
	
	public static Context getModuleContext()
	{
		if (mModuleContext != null)
			return mModuleContext;

		try
		{
			mModuleContext = AndroidAppHelper.currentApplication().createPackageContext(ExiModule.PACKAGE,  Context.CONTEXT_IGNORE_SECURITY);
		}
		catch (NameNotFoundException e)
		{
			Log.e("SwiftkeyExi", "Failed to get module context. Very strange.");
			e.printStackTrace();
		}

		return mModuleContext;		
	}
}
