package com.mayulive.swiftkeyexi.xposed.selection;

import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.key.KeyClassManager;
import com.mayulive.xposed.classhunter.ProfileHelpers;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;
import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.mayulive.xposed.classhunter.Modifiers.ABSTRACT;
import static com.mayulive.xposed.classhunter.Modifiers.EXACT;
import static com.mayulive.xposed.classhunter.Modifiers.FINAL;
import static com.mayulive.xposed.classhunter.Modifiers.INTERFACE;
import static com.mayulive.xposed.classhunter.Modifiers.PRIVATE;
import static com.mayulive.xposed.classhunter.Modifiers.PUBLIC;


public class PrioritySelectionClassManager
{

	private static String LOGTAG = ExiModule.getLogTag(PrioritySelectionClassManager.class);


	protected static Method frameHolderFactoryClass_frameHolderInflaterMethod = null;
	protected static Class frameHolderFactoryClass = null;

	public static void loadKnownClasses(PackageTree param)
	{

	}

	public static void loadUnknownClasses(PackageTree param)
	{
		PrioritySelectionClassManager.frameHolderFactoryClass = ProfileHelpers.loadProfiledClass( SelectionProfiles.get_FRAME_HOLDER_FACTORY_CLASS_PROFILE(), param );
	}

	public static void loadMethods() throws NoSuchMethodException
	{
		if (PrioritySelectionClassManager.frameHolderFactoryClass != null)
		{
			PrioritySelectionClassManager.frameHolderFactoryClass_frameHolderInflaterMethod = ProfileHelpers.findMostSimilar(new MethodProfile
							(
									PRIVATE | EXACT ,
									new ClassItem(void.class),

									new ClassItem(android.view.View.class),
									new ClassItem("" , PUBLIC | FINAL | EXACT ),
									new ClassItem("" , PUBLIC | FINAL | EXACT ),
									new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
									new ClassItem(int.class),
									new ClassItem("" , PUBLIC | FINAL | EXACT ),
									new ClassItem("" , PUBLIC | FINAL | EXACT )

							),
					PrioritySelectionClassManager.frameHolderFactoryClass.getDeclaredMethods(), PrioritySelectionClassManager.frameHolderFactoryClass);
		}
	}

	public static void loadFields()
	{

	}


	public static void doAllTheThings(PackageTree param) throws IOException, NoSuchFieldException, NoSuchMethodException
	{

		loadKnownClasses(param);
		loadUnknownClasses(param);
		loadMethods();
		loadFields();

		updateDependencyState();
	}


	protected static void updateDependencyState()
	{
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "frameHolderFactoryClass", 	PrioritySelectionClassManager.frameHolderFactoryClass );
		Hooks.logSetRequirementFalseIfNull( Hooks.selectionHooks_base,	 "frameHolderFactoryClass_frameHolderInflaterMethod", 	PrioritySelectionClassManager.frameHolderFactoryClass_frameHolderInflaterMethod );
	}

}
