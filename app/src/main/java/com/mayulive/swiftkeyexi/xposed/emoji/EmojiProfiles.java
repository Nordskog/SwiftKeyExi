package com.mayulive.swiftkeyexi.xposed.emoji;

import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ClassProfile;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;
import com.mayulive.xposed.classhunter.profiles.FieldItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import static com.mayulive.xposed.classhunter.Modifiers.*;

/**
 * Created by Roughy on 5/8/2017.
 */

public class EmojiProfiles
{

	public static ClassProfile get_EMOJI_PANEL_THEME_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.w.a.k");
		newProfile.setKnownPath("com.touchtype");

		newProfile.setMinDepth(2);
		newProfile.setMaxDepth(2);
		newProfile.setModifiers(PUBLIC | FINAL );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem(java.lang.Object.class));
/////////////////////////
//Interfaces
/////////////////////////
		newProfile.setInterfaces(new ClassItem[]
				{

				});
/////////////////////////
//Nested Classes
/////////////////////////
		newProfile.setNestedClasses(new ClassItem[]
				{

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//c

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.Integer.class)

								),

						//Method #1: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #2: equals
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.Object.class)

								),

						//Method #3: hashCode
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),


				});
/////////////////////////
//Declared Constructors
/////////////////////////
		newProfile.setDeclaredConstructors(new ConstructorProfile[]
				{
						//Constructor #0
						new ConstructorProfile
								(		PUBLIC | EXACT ,

										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}

}
