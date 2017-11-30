package com.mayulive.swiftkeyexi.util;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

/**
 * Created by Roughy on 2/20/2017.
 */

public class SteppedHookLog
{
	//Hooks all declared methods of a class, printing a stepped
	//view of how they are called

	private static String LOGTAG = ExiModule.getLogTag(SteppedHookLog.class);

	private Class[] mClass;

	private static void logMethod(Method method, Object[] args, String indent)
	{
		Log.i(LOGTAG, indent+method.toString());
		CodeUtils.printArguments(indent,args);
	}

	public SteppedHookLog(Class... clazz)
	{
		mClass = clazz;
	}


	private int mDepth = 0;

	private static String mIndent = "|    ";

	private static String getIndent(int depth)
	{
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < depth; i++)
		{
			builder.append(mIndent);
		}

		return builder.toString();

	}

	public void hookOne(Class clazz)
	{
		Method[] methods = clazz.getDeclaredMethods();

		for (Method method : methods)
		{
			try
			{
				XposedBridge.hookMethod(method, new XC_MethodHook()
				{

					private boolean wasFirst = false;

					@Override
					protected void beforeHookedMethod(MethodHookParam param) throws Throwable
					{
						if (mDepth == 0)
						{
							wasFirst = true;
							Log.i(LOGTAG, "############# ENTERED #############");
						}

						logMethod((Method)param.method, param.args, getIndent(mDepth));

						mDepth++;
					}

					@Override
					protected void afterHookedMethod(MethodHookParam param) throws Throwable
					{

						mDepth--;

						if (mDepth == 0 || wasFirst)
						{
							wasFirst = false;
							mDepth = 0;
							Log.i(LOGTAG, "############# EXITED #############");
						}
					}
				});
			}
			catch (Exception ex)
			{

			}


		}
	}

	public void hookAll()
	{
		for (Class clazz : mClass)
		{
			hookOne(clazz);
		}

	}









}
