package com.mayulive.swiftkeyexi.xposed.sound;

import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.mayulive.xposed.classhunter.Modifiers.EXACT;
import static com.mayulive.xposed.classhunter.Modifiers.FINAL;
import static com.mayulive.xposed.classhunter.Modifiers.PUBLIC;

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
			MethodProfile profile = new MethodProfile
			(
					PUBLIC | EXACT ,
					new ClassItem(void.class),

					new ClassItem(int.class),
					new ClassItem(android.content.Context.class)

			);

			keySoundClass_playSoundMethod = ProfileHelpers.findMostSimilar(profile, keySoundClass.getDeclaredMethods(), keySoundClass);
			DebugTools.logIfMethodProfileMismatch(  keySoundClass_playSoundMethod, keySoundClass, profile, "keySoundClass_playSoundMethod");

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