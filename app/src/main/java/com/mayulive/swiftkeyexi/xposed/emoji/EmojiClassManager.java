package com.mayulive.swiftkeyexi.xposed.emoji;

import android.net.Uri;

import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.mayulive.xposed.classhunter.Modifiers.PUBLIC;


public class EmojiClassManager 
{
	/////////////////
	//Known classes
	/////////////////

	protected static Class emojiPanelClass = null;

	protected static Method emojiPanel_onAttachedToWindowMethod = null;

	/////////////////
	//Unknown classes
	/////////////////


	/////////////////
	//Methods
	/////////////////

	protected static Method uriBuilderAppendParameterMethod = null;

	protected static Method uriBuilderSetAuthorityMethod = null;



	protected static Method emojiPanel_staticConstructorMethod = null;

	protected static void loadKnownClasses( PackageTree param )
	{
		emojiPanelClass = ClassHunter.loadClass("com.touchtype.keyboard.view.fancy.emoji.EmojiPanel", param.getClassLoader());
	}

	protected static void loadUnknownClasses(PackageTree param)
	{

	}


	protected static void loadMethods() throws NoSuchMethodException
	{
		if (emojiPanelClass != null)
		{
			emojiPanel_staticConstructorMethod = ProfileHelpers.findAllMethodsWithReturnType(EmojiClassManager.emojiPanelClass, EmojiClassManager.emojiPanelClass.getDeclaredMethods()).get(0);
			emojiPanel_staticConstructorMethod.setAccessible(true);

			// Only used to resizing top bar when modifying size, not a requirement
			emojiPanel_onAttachedToWindowMethod = ProfileHelpers.findFirstMethodByName( EmojiClassManager.emojiPanelClass.getDeclaredMethods(), "onAttachedToWindow");
		}


		uriBuilderAppendParameterMethod = ProfileHelpers.firstMethodByName(android.net.Uri.Builder.class.getDeclaredMethods(), "appendQueryParameter");


		uriBuilderSetAuthorityMethod  = ProfileHelpers.findFirstMatchingMethodWithName(new MethodProfile(
						PUBLIC,
						new ClassItem(Uri.Builder.class),
						new ClassItem(String.class)
				),
				Uri.Builder.class.getDeclaredMethods(), Uri.Builder.class, "authority");


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

		Hooks.logSetRequirementFalseIfNull( Hooks.gifHooksNSFW,	 "uriBuilderAppendParameterMethod", uriBuilderAppendParameterMethod);
		Hooks.logSetRequirementFalseIfNull( Hooks.gifHooksNSFW,	 "uriBuilderAppendParameterMethod", uriBuilderSetAuthorityMethod);
	}

}
