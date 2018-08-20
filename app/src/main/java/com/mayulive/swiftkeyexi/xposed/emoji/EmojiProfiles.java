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

	public static ClassProfile get_GIF_URL_QUERY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("gbl");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC | FINAL );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem(java.lang.Object.class));
/////////////////////////
//Interfaces
/////////////////////////
		newProfile.setInterfaces(new ClassItem[]
				{
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Nested Classes
/////////////////////////
		newProfile.setNestedClasses(new ClassItem[]
				{
						new ClassItem("" , STATIC | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//a
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//c
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//d
						new FieldItem( EXACT , 	new ClassItem(int.class)),	//e
						new FieldItem( EXACT , 	new ClassItem(int.class)),	//f
						new FieldItem( EXACT , 	new ClassItem(int.class)),	//g
						new FieldItem( EXACT , 	new ClassItem(java.lang.String.class)),	//h
						new FieldItem( EXACT , 	new ClassItem(boolean.class)),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.concurrent.Executor.class)),	//m
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.lang.String.class)),	//n
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.lang.String.class)),	//o

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(long.class),

										new ClassItem("com.google.gson.JsonObject" , PUBLIC | FINAL | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem("" , STATIC | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.swiftkey.avro.telemetry.sk.android.GifResultStatus" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class),
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.GifResultSource" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , STATIC | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.GifResultSource" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , STATIC | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | BRIDGE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | FINAL | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , STATIC | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | FINAL | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.io.InputStream.class),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class)

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

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

}
