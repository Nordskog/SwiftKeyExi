package com.mayulive.swiftkeyexi.xposed.popupkeys;

import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ClassProfile;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;
import com.mayulive.xposed.classhunter.profiles.FieldItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import static com.mayulive.xposed.classhunter.Modifiers.*;

public class PopupkeyProfiles
{

	public static ClassProfile _get_EXTRA_KEY_POPUP_RUNNABLE_PROFILE( Class containerClass )
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("fcm");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(FINAL );

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
						new FieldItem( FINAL | SYNTHETIC | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( FINAL | SYNTHETIC | EXACT , 	new ClassItem(containerClass)),	//b

				});
		/////////////////////////
		//Declared Methods
		/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: run
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
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

										new ClassItem(containerClass),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}


	protected static ClassProfile _get_EXTRA_KEY_POPUP_RUNNABLE_CONTAINER_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("fcl");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC | FINAL );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem("" , PUBLIC | ABSTRACT | EXACT ));
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
						new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( EXACT , 	new ClassItem(boolean.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(long.class)),	//d
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//e
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.lang.Runnable.class)),	//h

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
										new ClassItem(void.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.util.EnumSet.class)

								),

						//Method #4: a_
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

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

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(long.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}


	public static ClassProfile get_KEY_LONGPRESS_POPUP_CONFIGURE_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("dxy");
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

				});
/////////////////////////
//Nested Classes
/////////////////////////
		newProfile.setNestedClasses(new ClassItem[]
				{
						new ClassItem("" , PUBLIC | STATIC | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e

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
										new ClassItem(int.class),
										new ClassItem(float.class),
										new ClassItem("" , PUBLIC | STATIC | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(float.class),
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
										new ClassItem(int.class),
										new ClassItem(float.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | STATIC | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem(float.class),
										new ClassItem(float.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | STATIC | EXACT )

								),

						//Method #4: b
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(float.class),
										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.graphics.RectF.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(float.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(int.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
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

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}




	public static ClassProfile get_KEY_LONGPRESS_CONFIGURE_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("ews");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//d
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//i
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//j
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(float.class)),	//k
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//m
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//n
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//o
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//p
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | STATIC | EXACT )),	//q
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(boolean.class)),	//r
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(java.util.Locale.class)),	//s
						new FieldItem( FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//t
						new FieldItem( FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//u
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//v
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//w
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//x
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//y
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
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.lang.Float.class),
										new ClassItem(java.lang.Float.class),
										new ClassItem(boolean.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(float.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem(java.util.List.class),
										new ClassItem(java.lang.CharSequence.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #9: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #10: A
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #11: B
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.graphics.Typeface.class)

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | EXACT )

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.util.List.class)

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.lang.Float.class),
										new ClassItem(java.lang.Float.class)

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(float.class)

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.util.Set.class)

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.Boolean.class),
										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem(java.util.Set.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #27: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #28: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #29: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #30: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #31: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #32: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #33: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #34: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #35: g
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #36: h
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #37: i
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #38: j
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #39: k
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #40: l
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #41: m
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #42: n
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #43: o
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #44: p
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #45: q
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #46: r
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #47: s
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #48: t
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #49: u
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #50: v
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #51: w
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #52: x
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #53: y
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #54: z
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

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
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}
}
