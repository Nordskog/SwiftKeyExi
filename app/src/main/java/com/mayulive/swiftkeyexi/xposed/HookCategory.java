package com.mayulive.swiftkeyexi.xposed;

import android.support.annotation.Nullable;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

/**
 * Created by Roughy on 3/22/2018.
 */
public class HookCategory extends ArrayList<XC_MethodHook.Unhook>
{
	private static String LOGTAG = ExiModule.getLogTag(HookCategory.class);

	private String mName = "NULL";
	private boolean mRequirementsMet = true;
	private HookCategory[] mDepenencies = new HookCategory[0];
	private boolean mLogMe = true;

	HookCategory(String name)
	{
		this(name, new HookCategory[0]);
	}

	HookCategory(String name, HookCategory... dependencies)
	{
		super();
		this.mName = name;
		this.mDepenencies = dependencies;
	}


	//Returns true if change state as a result of this call
	public boolean setRequirementsMet(boolean depenencyMet)
	{

		//Only react if state changes from true to false
		if (mRequirementsMet && !depenencyMet)
		{
			//Propegate to depenencies
			invalidate(null, "Requirement not met");
			return false;
		}

		return true;
	}

	public void invalidate(Throwable ex, String reason)
	{
		if (mRequirementsMet)
		{
			mRequirementsMet = false;
			logRemoval(ex, reason);
			removeHooks();
			invalidateDepenencies();
		} else
		{
			Log.d(LOGTAG, "Attempted to invalidated already invalidated HookCategory");
		}
	}


	private void logRemoval(@Nullable Throwable ex, @Nullable String reason)
	{
		if (mLogMe)
		{
			if (reason == null)
				reason = "NULL";

			Log.e(LOGTAG, "Removed Hooks: " + mName + ", " + reason);
			XposedBridge.log("Removed hooks: " + mName + ", " + reason);
			if (ex != null)
			{
				ex.printStackTrace();
				XposedBridge.log(ex);
			}

		}
	}

	private void invalidateDepenencies()
	{
		//Set the state of any depenencies to false, and remove their hooks
		for (HookCategory hookCategory : mDepenencies)
		{
			//hookCategory.mLogMe = false;
			hookCategory.invalidate(null, "Dependency invalidated");
		}
	}

	public boolean isRequirementsMet()
	{
		return mRequirementsMet;
	}

	private void removeHooks()
	{
		for (XC_MethodHook.Unhook hook : this)
		{
			hook.unhook();
		}
		this.clear();
	}
}
