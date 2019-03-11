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

		newProfile.setFullPath("-$$Lambda$ezt$7UcBplaNkfHj9Ogr2-C38gi_CzE");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC | FINAL | SYNTHETIC );

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
						new FieldItem( PRIVATE | FINAL | SYNTHETIC | EXACT , 	new ClassItem(containerClass)),	//f$0
						new FieldItem( PRIVATE | FINAL | SYNTHETIC | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//f$1

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
								(		PUBLIC | SYNTHETIC | EXACT ,

										new ClassItem(containerClass),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}


	protected static ClassProfile _get_EXTRA_KEY_POPUP_RUNNABLE_CONTAINER_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("ezt");
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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(long.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//d
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

						//Method #1: f
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: lambda$7UcBplaNkfHj9Ogr2-C38gi_CzE
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.util.EnumSet.class)

								),

						//Method #6: a_
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #8: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #9: d
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

		newProfile.setFullPath("dzp");
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
						new ClassItem("" , STATIC | EXACT )

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

										new ClassItem(float.class),
										new ClassItem(int.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(float.class),

										new ClassItem(android.graphics.RectF.class),
										new ClassItem(int.class),
										new ClassItem(float.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(float.class),
										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(boolean.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(android.graphics.RectF.class),

										new ClassItem(float.class),
										new ClassItem(android.graphics.RectF.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(float.class),
										new ClassItem(int.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , STATIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.graphics.RectF.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(float.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(int.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem("" , STATIC | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem(float.class),
										new ClassItem(float.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #9: b
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(float.class),
										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(float.class)

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class),
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

		newProfile.setFullPath("eun");
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
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//B
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//C
						new FieldItem( FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//a
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( EXACT , 	new ClassItem(java.util.Locale.class)),	//c
						new FieldItem( FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//d
						new FieldItem( FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//n
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//o
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//p
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(float.class)),	//r
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//s
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//t
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//u
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//v
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//w
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//x
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//y
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | STATIC | EXACT )),	//z

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
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.lang.Float.class),
										new ClassItem(java.lang.Float.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.lang.Float.class),
										new ClassItem(java.lang.Float.class),
										new ClassItem(boolean.class)

								),

						//Method #9: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #10: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(float.class)

								),

						//Method #11: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #12: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #13: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(float.class)

								),

						//Method #16: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.util.List.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #17: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem(java.util.List.class),
										new ClassItem(java.lang.CharSequence.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #18: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #19: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #20: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #21: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem(java.util.List.class)

								),

						//Method #22: c
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #23: d
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #24: A
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #25: B
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #26: C
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #27: D
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #28: E
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #29: F
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #30: G
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #31: H
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #32: I
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #33: J
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #34: K
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #35: L
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #36: M
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #37: N
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #38: O
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #39: P
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #40: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #41: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #42: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.graphics.Typeface.class)

								),

						//Method #43: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #44: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #45: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #46: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.util.List.class)

								),

						//Method #47: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #48: a
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

						//Method #49: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #50: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #51: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #52: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #53: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #54: g
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #55: h
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #56: i
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #57: j
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #58: k
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #59: l
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #60: m
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #61: n
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #62: o
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #63: p
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #64: q
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #65: r
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #66: s
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #67: t
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #68: u
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #69: v
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #70: w
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #71: x
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #72: y
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #73: z
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
