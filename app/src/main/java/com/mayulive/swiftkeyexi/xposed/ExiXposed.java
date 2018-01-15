package com.mayulive.swiftkeyexi.xposed;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.xposed.classhunter.ClassHunter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;


/**
 * Created by Roughy on 3/29/2017.
 */

public class ExiXposed
{

	private static String LOGTAG = ExiModule.getLogTag(ExiXposed.class);

	//Hook globals
	public static String HOOK_PACKAGE_NAME;

	public static boolean isBeta = false;

	public static String getPrefsPath()
	{
		if (isBeta)
		{
			return "com.touchtype.swiftkey.beta_preferences";
		}
		else
		{
			return "com.touchtype.swiftkey_preferences";
		}
	}

	public static void setPackage(String packageName)
	{
		isBeta = packageName.equals( ExiModule.SWIFTKEY_BETA_PACKAGE_NAME);
		HOOK_PACKAGE_NAME = packageName;
	}

	public static int loadCRC(String path)
	{
		File crcFile = new File(path);
		int crc = 0;

		FileReader reader = null;
		try
		{
			char[] buffer = new char[8];
			reader = new FileReader(crcFile);
			reader.read(buffer);
			reader.close();

			String loadedCRC = new String(buffer);
			Log.i(LOGTAG, "Loaded CRC: "+loadedCRC);

			crc = (int) Long.parseLong(loadedCRC, 16);

		}
		catch (FileNotFoundException e)
		{
			Log.i(LOGTAG, "No existing apk CRC stored, expected.");
		}
		catch (Exception e)
		{
			Log.e(LOGTAG, "Failed to load apk CRC");
			e.printStackTrace();
		}
		finally
		{

		}

		return crc;
	}

	public static void saveCRC(String path, int crc)
	{
		Log.i(LOGTAG, "Saving CRC: "+Integer.toHexString(crc));

		try
		{
			FileWriter ex = new FileWriter(path, false);
			ex.write(Integer.toHexString(crc));
			ex.close();
		}
		catch (Exception e)
		{
			Log.e(LOGTAG, "Failed to save apk CRC");
			e.printStackTrace();
		}
	}

	//apk path: lpparam.appInfo.sourceDir
	//crcSavePath: lpparam.appInfo.dataDir+"/files/EXI_LAST_APK_CRC"
	public static boolean apkCrcUnchanged(String apkPath, String crcSavePath)
	{
		//Only load cache if same apk
		int prevCRC = ExiXposed.loadCRC(crcSavePath);
		int currentCRC = ClassHunter.getApkCRC32(apkPath);

		Log.i(LOGTAG, "Comparing "+Integer.toHexString(prevCRC)+" to "+Integer.toHexString(currentCRC));

		ExiXposed.saveCRC(crcSavePath, currentCRC);

		return prevCRC == currentCRC;
	}

}
