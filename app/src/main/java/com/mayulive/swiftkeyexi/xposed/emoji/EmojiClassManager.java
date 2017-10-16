package com.mayulive.swiftkeyexi.xposed.emoji;

import android.util.Log;

import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.xposed.classhunter.ClassHunter;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

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



	/////////////////
	//Methods
	/////////////////

	protected static Method emojiPanel_staticConstructorMethod = null;

	protected static void loadKnownClasses( PackageTree param )
	{
		emojiPanelClass = ClassHunter.loadClass("com.touchtype.keyboard.view.fancy.emoji.EmojiPanel", param.getClassLoader());
	}


	protected static void loadMethods() throws NoSuchMethodException
	{
		if (emojiPanelClass != null)
		{
			emojiPanel_staticConstructorMethod = ProfileHelpers.findAllMethodsWithReturnType(EmojiClassManager.emojiPanelClass, EmojiClassManager.emojiPanelClass.getDeclaredMethods()).get(0);
			emojiPanel_staticConstructorMethod.setAccessible(true);
		}
	}

	protected static void doAllTheThings(PackageTree param) throws IOException, NoSuchFieldException, NoSuchMethodException
	{
		loadKnownClasses(param);
		loadMethods();

		updateDependencyState();
	}

	protected static void updateDependencyState()
	{
		Hooks.logSetRequirementFalseIfNull( Hooks.emojiHooks_base,	 "emojiPanelClass", 	emojiPanelClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.emojiHooks_base,	 "emojiPanel_staticConstructorMethod", 	emojiPanel_staticConstructorMethod );
	}

}
