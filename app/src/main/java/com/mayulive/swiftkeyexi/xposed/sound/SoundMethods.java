package com.mayulive.swiftkeyexi.xposed.sound;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.providers.SoundProvider;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.util.ContextUtils;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;

import java.io.FileDescriptor;
import java.io.FileInputStream;

/**
 * Created by Roughy on 11/14/2017.
 */

public class SoundMethods
{

	private static String LOGTAG = ExiModule.getLogTag(SoundMethods.class);

	protected static final String SOUND_FEEDBACK_SLIDER_PREF_KEY = "pref_sound_feedback_slider_key";

	//These appear to be constant, not sure what they're based on.
	//They are /not/ the soundpool ids
	private static final int SPACEBAR_KEY_ID = 32;
	private static final int BACKSPACE_KEY_ID = -5;

	private static final int MAX_VOLUME_VALUE = 9;	//What swiftkey uses


	protected static int mKeypressVolumeSetting = -1;		//1 - 9 set by swiftkey,
	protected static float mKeypressVolumeCalculated = -1;	//actual volume calculated using fancy numbers and stuff
	protected static long mLastSoundUpdate = -1;
	protected static SoundPool mKeypressSoundPool = null;

	protected static int mKeypressSoundId = -1;
	protected static int mSpacebarSoundId = -1;
	protected static int mBackspaceSoundId = -1;


	protected static void updateKeypressVolume()
	{
		SharedPreferences prefs = KeyboardMethods.getSwiftkeySharedPrefs();
		mKeypressVolumeSetting = prefs.getInt(SOUND_FEEDBACK_SLIDER_PREF_KEY,3);
		SoundMethods.calculcateKeypressVolume();
	}

	private static float getKeypressVolume()
	{
		if (mKeypressVolumeSetting == -1)
		{
			updateKeypressVolume();
		}

		return mKeypressVolumeCalculated;
	}

	protected static void calculcateKeypressVolume()
	{
		//Copypasta from swiftkey source.
		mKeypressVolumeCalculated= (float) Math.pow(0.8899999856948853d, (double) ((float) ((MAX_VOLUME_VALUE - mKeypressVolumeSetting) * 4)));
	}

	protected static void updateCustomSound()
	{
		if (mLastSoundUpdate < Settings.LAST_KEYPRESS_SOUND_UPDATE)
		{
			mLastSoundUpdate = Settings.LAST_KEYPRESS_SOUND_UPDATE;

			//Create a new soundpool
			if (mKeypressSoundPool != null)
			{
				mKeypressSoundPool.release();
				mKeypressSoundPool = null;
			}
			mKeypressSoundPool = new SoundPool(3, AudioManager.STREAM_SYSTEM, 0);

			mKeypressSoundId = loadSoundIntoSoundPool(SoundProvider.KEYPRESS_SOUND_FILENAME);
			mSpacebarSoundId = loadSoundIntoSoundPool(SoundProvider.SPACEBAR_SOUND_FILENAME);
			mBackspaceSoundId = loadSoundIntoSoundPool(SoundProvider.BACKSPACE_SOUND_FILENAME);
		}
	}

	private static int loadSoundIntoSoundPool(String soundName)
	{
		ParcelFileDescriptor file = null;

		int id = -1;

		try
		{
			file = SoundProvider.getSoundUri(ContextUtils.getHookContext(), soundName);

			if (file != null)
			{
				FileDescriptor descriptor = file.getFileDescriptor();
				long fileSize = getSize(descriptor);

				id = mKeypressSoundPool.load(descriptor, 0, fileSize, 1);
				file.close();
			}
		}
		catch (Exception ex)
		{
			Log.d(LOGTAG, "Failed to load custom keypress sound");
		}
		finally
		{
			try
			{
				file.close();
			}
			catch(Exception ex)
			{

			}
		}

		return id;

	}

	public static void playSound(int keyID)
	{
		if (mKeypressSoundPool != null)
		{
			float volume = getKeypressVolume();
			int id = -1;

			switch(keyID)
			{
				case SPACEBAR_KEY_ID:
				{
					id = mSpacebarSoundId;
					break;
				}

				case BACKSPACE_KEY_ID:
				{
					id = mBackspaceSoundId;
					break;
				}

				default:
				{
					id = mKeypressSoundId;
					break;
				}
			}

			if (id != -1)
			{
				mKeypressSoundPool.play(id, volume,volume,1,0,1);
			}
		}
	}

	private static long getSize(FileDescriptor fd)
	{
		try (FileInputStream fis = new FileInputStream(fd))
		{
			return fis.getChannel().size();
		}
		catch (Exception ex)
		{
			return 0;
		}
	}









}
