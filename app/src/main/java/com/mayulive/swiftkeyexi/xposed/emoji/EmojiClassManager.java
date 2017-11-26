package com.mayulive.swiftkeyexi.xposed.emoji;

import android.util.Log;

import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.Modifiers;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;


public class EmojiClassManager 
{
	/////////////////
	//Known classes
	/////////////////

	protected static Class emojiPanelClass = null;

	/////////////////
	//Unknown classes
	/////////////////

	protected static Class gifUrlQueryClass = null;

	/////////////////
	//Methods
	/////////////////

	protected static Method gifUrlQueryClass_createQueryMethod = null;


	protected static Method emojiPanel_staticConstructorMethod = null;

	protected static void loadKnownClasses( PackageTree param )
	{
		emojiPanelClass = ClassHunter.loadClass("com.touchtype.keyboard.view.fancy.emoji.EmojiPanel", param.getClassLoader());
	}

	protected static void loadUnknownClasses(PackageTree param)
	{
		gifUrlQueryClass = ProfileHelpers.loadProfiledClass(EmojiProfiles.get_GIF_URL_QUERY_CLASS_PROFILE(), param);
	}


	protected static void loadMethods() throws NoSuchMethodException
	{
		if (emojiPanelClass != null)
		{
			emojiPanel_staticConstructorMethod = ProfileHelpers.findAllMethodsWithReturnType(EmojiClassManager.emojiPanelClass, EmojiClassManager.emojiPanelClass.getDeclaredMethods()).get(0);
			emojiPanel_staticConstructorMethod.setAccessible(true);
		}

		if (gifUrlQueryClass != null)
		{
			gifUrlQueryClass_createQueryMethod = ProfileHelpers.findMostSimilar(	new MethodProfile
					(
							Modifiers.PUBLIC | Modifiers.EXACT ,
							new ClassItem(java.lang.String.class),

							new ClassItem(java.lang.String.class),
							new ClassItem(java.lang.String.class),
							new ClassItem(java.lang.String.class),
							new ClassItem(java.lang.String.class),	// "Moderate"
							new ClassItem(java.lang.String.class),
							new ClassItem(int.class),
							new ClassItem(int.class),
							new ClassItem(int.class)

					), gifUrlQueryClass.getDeclaredMethods(), gifUrlQueryClass);
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
		Hooks.logSetRequirementFalseIfNull( Hooks.emojiHooks_base,	 "emojiPanelClass", 	emojiPanelClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.emojiHooks_base,	 "emojiPanel_staticConstructorMethod", 	emojiPanel_staticConstructorMethod );

		Hooks.logSetRequirementFalseIfNull( Hooks.gifHooksNSFW,	 "gifUrlQueryClass", 	gifUrlQueryClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.gifHooksNSFW,	 "gifUrlQueryClass_createQueryMethod", 	gifUrlQueryClass_createQueryMethod );
	}

}
