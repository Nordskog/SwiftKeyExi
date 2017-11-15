package com.mayulive.swiftkeyexi.xposed.sound;

import android.content.Context;

import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.emoji.EmojiClassManager;
import com.mayulive.swiftkeyexi.xposed.emoji.EmojiProfiles;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.Modifiers;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;
import com.mayulive.xposed.classhunter.profiles.Profile;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Roughy on 11/15/2017.
 */

public class SoundClassManager
{

	protected static Class keySoundClass = null;
	protected static Method keySoundClass_playSoundMethod = null;

	protected static void loadKnownClasses( PackageTree param )
	{

	}

	protected static void loadUnknownClasses(PackageTree param)
	{
		keySoundClass = ProfileHelpers.loadProfiledClass(SoundProfiles.get_KEY_SOUND_CLASS_PROFILE(), param);
	}


	protected static void loadMethods() throws NoSuchMethodException
	{

		if (keySoundClass != null)
		{
			keySoundClass_playSoundMethod = ProfileHelpers.findMostSimilar(new MethodProfile(

					Modifiers.PUBLIC,
					new ClassItem(void.class),
					new ClassItem(int.class),
					new ClassItem(Context.class)

			), keySoundClass.getDeclaredMethods(), keySoundClass);

		}
	}

	protected static void doAllTheThings(PackageTree param) throws IOException, NoSuchFieldException, NoSuchMethodException
	{
		loadKnownClasses(param);
		loadUnknownClasses(param);
		loadMethods();

		updateDependencyState();
	}

	protected static void updateDependencyState()
	{
		Hooks.logSetRequirementFalseIfNull( Hooks.soundHooks_base,	 "keySoundClass", 	keySoundClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.soundHooks_base,	 "keySoundClass_playSoundMethod", 	keySoundClass_playSoundMethod );
	}

}