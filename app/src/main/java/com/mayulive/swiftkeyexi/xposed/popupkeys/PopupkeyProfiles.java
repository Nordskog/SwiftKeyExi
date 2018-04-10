package com.mayulive.swiftkeyexi.xposed.popupkeys;

import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ClassProfile;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;
import com.mayulive.xposed.classhunter.profiles.FieldItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import static com.mayulive.xposed.classhunter.Modifiers.*;

public class PopupkeyProfiles
{



	
	protected static ClassProfile _get_EXTRA_KEY_POPUP_RUNNABLE_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.h.h.m$1");
		newProfile.setKnownPath("com.touchtype.keyboard");

		newProfile.setMinDepth(3);
		newProfile.setMaxDepth(3);
		newProfile.setModifiers(NO_MODIFIERS);

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem(java.lang.Object.class));
		/////////////////////////
		//Interfaces
		/////////////////////////
		newProfile.setInterfaces(new ClassItem[]
				{
						new ClassItem(java.lang.Runnable.class)

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
						new FieldItem( FINAL | SYNTHETIC | EXACT , 	new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( FINAL | SYNTHETIC | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//b

				});
		/////////////////////////
		//Declared Methods
		/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: run
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),


				});
		/////////////////////////
		//Declared Constructors
		/////////////////////////
		newProfile.setDeclaredConstructors(new ConstructorProfile[]
				{
						//Constructor #0
						new ConstructorProfile
								(		EXACT ,

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}


	public static ClassProfile get_KEY_LONGPRESS_POPUP_CONFIGURE_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.bf");
		newProfile.setKnownPath("com.touchtype.keyboard");

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

				});
/////////////////////////
//Nested Classes
/////////////////////////
		newProfile.setNestedClasses(new ClassItem[]
				{
						new ClassItem("com.touchtype.keyboard" , PRIVATE | STATIC | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(float.class),

										new ClassItem(android.graphics.RectF.class),
										new ClassItem(int.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(android.graphics.RectF.class),

										new ClassItem(float.class),
										new ClassItem(android.graphics.RectF.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PRIVATE | STATIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.graphics.RectF.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(float.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PRIVATE | STATIC | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #5: b
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(float.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class)

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.Locale.class)

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

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}




	protected static ClassProfile _get_POPUP_SCHEDULER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.b.c$1");
		newProfile.setKnownPath("com.touchtype.keyboard");

		newProfile.setMinDepth(2);
		newProfile.setMaxDepth(2);
		newProfile.setModifiers(STATIC );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem(java.lang.Object.class));
/////////////////////////
//Interfaces
/////////////////////////
		newProfile.setInterfaces(new ClassItem[]
				{
						new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

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
						new FieldItem( FINAL | SYNTHETIC | EXACT , 	new ClassItem(android.os.Handler.class)),	//a

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
										new ClassItem(void.class),

										new ClassItem(java.lang.Runnable.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.Runnable.class),
										new ClassItem(long.class),
										new ClassItem(java.util.concurrent.TimeUnit.class)

								),


				});
/////////////////////////
//Declared Constructors
/////////////////////////
		newProfile.setDeclaredConstructors(new ConstructorProfile[]
				{
						//Constructor #0
						new ConstructorProfile
								(		EXACT ,

										new ClassItem(android.os.Handler.class)

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEY_LONGPRESS_CONFIGURE_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.i.j");
		newProfile.setKnownPath("com.touchtype.keyboard");

		newProfile.setMinDepth(1);
		newProfile.setMaxDepth(1);
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
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//A
						new FieldItem( FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//a
						new FieldItem( EXACT , 	new ClassItem(java.util.Locale.class)),	//b
						new FieldItem( FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//c
						new FieldItem( FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.preferences" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//n
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//o
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(float.class)),	//p
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//q
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//r
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//s
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//t
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//u
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//v
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//w
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | EXACT )),	//x
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//y
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//z

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
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.lang.Float.class),
										new ClassItem(java.lang.Float.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.lang.Float.class),
										new ClassItem(java.lang.Float.class),
										new ClassItem(boolean.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(float.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(float.class)

								),

						//Method #12: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #13: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #14: c
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #15: A
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #16: B
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #17: C
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #18: D
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #19: E
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #20: F
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #21: G
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #22: H
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #23: I
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #24: J
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #25: K
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #26: L
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #27: M
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #28: N
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #29: O
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #30: P
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #31: a
						new MethodProfile
								(
										EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #32: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #33: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.graphics.Typeface.class)

								),

						//Method #34: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.common" , PUBLIC | FINAL | EXACT )

								),

						//Method #35: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #36: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #37: a
						new MethodProfile
								(
										EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #38: a
						new MethodProfile
								(
										EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem(boolean.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #39: a
						new MethodProfile
								(
										EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem(java.util.List.class),
										new ClassItem(java.lang.CharSequence.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #40: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.util.Set.class)

								),

						//Method #41: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.lang.Boolean.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | EXACT ),
										new ClassItem(java.util.Set.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #42: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #43: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #44: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #45: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #46: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #47: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #48: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #49: h
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #50: i
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #51: j
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #52: k
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #53: l
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #54: m
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #55: n
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #56: o
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #57: p
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #58: q
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #59: r
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #60: s
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #61: t
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #62: u
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #63: v
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #64: w
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #65: x
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #66: y
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #67: z
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

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

										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.preferences" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}
}
