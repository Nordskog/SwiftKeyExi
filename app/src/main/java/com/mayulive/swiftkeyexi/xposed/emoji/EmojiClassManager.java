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
import java.util.List;

import static com.mayulive.xposed.classhunter.Modifiers.PUBLIC;


public class EmojiClassManager 
{
	/////////////////
	/////////////////

	protected static Class emojiPanelClass = null;
	protected static Method emojiPanel_staticConstructorMethod = null;
	protected static Method emojiPanel_onAttachedToWindowMethod = null;

	/////////////////
	/////////////////

	protected static Class gifPanelClass = null;
	protected static Method gifPanel_StaticConstructorClass = null;
	protected static Method gifPanel_onAttachedToWindowMethod = null;

	protected static Class  stickerPanelClass = null;
	protected static Method stickerPanel_StaticConstructorClass = null;
	protected static Method stickerPanel_onAttachedToWindowMethod = null;

	/////////////////
	/////////////////

	protected static Method uriBuilderAppendParameterMethod = null;
	protected static Method uriBuilderSetAuthorityMethod = null;


	protected static void loadKnownClasses( PackageTree param )
	{
		emojiPanelClass = ClassHunter.loadClass("com.touchtype.keyboard.view.fancy.emoji.EmojiPanel", param.getClassLoader());
		gifPanelClass = ClassHunter.loadClass("com.touchtype.keyboard.view.fancy.richcontent.gifs.GifPanel", param.getClassLoader());
		stickerPanelClass = ClassHunter.loadClass("com.touchtype.keyboard.view.fancy.richcontent.stickers.StickerGalleryPanel", param.getClassLoader());
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

		if (gifPanelClass != null)
		{
			List<Method> methods =ProfileHelpers.findAllMethodsWithReturnType(EmojiClassManager.gifPanelClass, EmojiClassManager.gifPanelClass.getDeclaredMethods());
			if (!methods.isEmpty())
				gifPanel_StaticConstructorClass = methods.get(0);

			gifPanel_onAttachedToWindowMethod = ProfileHelpers.findFirstMethodByName( EmojiClassManager.gifPanelClass.getDeclaredMethods(), "onAttachedToWindow");

		}

		if (stickerPanelClass != null)
		{
			List<Method> methods =ProfileHelpers.findAllMethodsWithReturnType(EmojiClassManager.stickerPanelClass, EmojiClassManager.stickerPanelClass.getDeclaredMethods());
			if (!methods.isEmpty())
				stickerPanel_StaticConstructorClass = methods.get(0);

			stickerPanel_onAttachedToWindowMethod = ProfileHelpers.findFirstMethodByName( EmojiClassManager.stickerPanelClass.getDeclaredMethods(), "onAttachedToWindow");

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
