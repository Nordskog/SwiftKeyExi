package com.mayulive.swiftkeyexi.xposed.key;

import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ClassProfile;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;
import com.mayulive.xposed.classhunter.profiles.FieldItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import static com.mayulive.xposed.classhunter.Modifiers.ABSTRACT;
import static com.mayulive.xposed.classhunter.Modifiers.ARRAY;
import static com.mayulive.xposed.classhunter.Modifiers.BRIDGE;
import static com.mayulive.xposed.classhunter.Modifiers.ENUM;
import static com.mayulive.xposed.classhunter.Modifiers.EXACT;
import static com.mayulive.xposed.classhunter.Modifiers.FINAL;
import static com.mayulive.xposed.classhunter.Modifiers.INTERFACE;
import static com.mayulive.xposed.classhunter.Modifiers.PRIVATE;
import static com.mayulive.xposed.classhunter.Modifiers.PROTECTED;
import static com.mayulive.xposed.classhunter.Modifiers.PUBLIC;
import static com.mayulive.xposed.classhunter.Modifiers.STATIC;
import static com.mayulive.xposed.classhunter.Modifiers.SYNTHETIC;
import static com.mayulive.xposed.classhunter.Modifiers.THIS;

/**
 * Created by Roughy on 7/24/2017.
 */

public class KeyProfiles
{

	public static ClassProfile get_KEY_FIELDS_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.f.i$a");
		newProfile.setKnownPath("com.touchtype.keyboard");

		newProfile.setMinDepth(2);
		newProfile.setMaxDepth(2);
		newProfile.setModifiers(PUBLIC | STATIC );

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
						new FieldItem( EXACT , 	new ClassItem(java.lang.String.class)),	//a
						new FieldItem( EXACT , 	new ClassItem(java.lang.String.class)),	//b
						new FieldItem( EXACT , 	new ClassItem(java.lang.String.class)),	//c
						new FieldItem( EXACT , 	new ClassItem(java.util.List.class)),	//d
						new FieldItem( EXACT , 	new ClassItem(java.util.List.class)),	//e
						new FieldItem( EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )),	//f
						new FieldItem( EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )),	//g
						new FieldItem( EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//h
						new FieldItem( EXACT , 	new ClassItem(java.util.List.class)),	//i
						new FieldItem( EXACT , 	new ClassItem(java.lang.Float.class)),	//j
						new FieldItem( EXACT , 	new ClassItem(int.class)),	//k
						new FieldItem( EXACT , 	new ClassItem(java.lang.String.class)),	//l
						new FieldItem( EXACT , 	new ClassItem(java.lang.String.class)),	//m
						new FieldItem( EXACT , 	new ClassItem(android.graphics.RectF.class)),	//n
						new FieldItem( EXACT , 	new ClassItem(java.util.List.class)),	//o
						new FieldItem( EXACT , 	new ClassItem(boolean.class)),	//p
						new FieldItem( EXACT , 	new ClassItem(java.lang.Integer.class)),	//q
						new FieldItem( EXACT , 	new ClassItem(java.util.List.class)),	//r
						new FieldItem( EXACT , 	new ClassItem(boolean.class)),	//s

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
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(int.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(android.graphics.RectF.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.Float.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.Integer.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(boolean.class)

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #10: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #11: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #12: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #13: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(boolean.class)

								),

						//Method #14: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #15: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #16: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #17: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #18: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #19: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.util.List.class)

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

										new ClassItem[0]

								),


				});

		return newProfile;
	}

	public static ClassProfile get_SIMPLE_KEY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.f.w");
		newProfile.setKnownPath("com.touchtype.keyboard");

		newProfile.setMinDepth(1);
		newProfile.setMaxDepth(1);
		newProfile.setModifiers(PUBLIC );

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
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e

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
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #4: a_
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #6: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #7: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #8: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #9: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #10: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #12: toString
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

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
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEY_RAW_DEFINITION_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.h.k");
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
						new ClassItem("com.touchtype.keyboard" , PRIVATE | STATIC | EXACT ),
						new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.Float.class)),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.graphics.RectF.class)),	//n
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//o
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//p
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(boolean.class)),	//q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(boolean.class)),	//r
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.Integer.class)),	//s

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PRIVATE | STATIC | EXACT ),

										new ClassItem(android.util.TypedValue.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | THIS | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | THIS | EXACT ),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(java.lang.Float.class),

										new ClassItem(java.util.List.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.List.class)

								),

						//Method #6: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(java.util.List.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #9: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #10: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #11: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #12: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #13: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #14: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #15: h
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #16: i
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #17: j
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.graphics.RectF.class)

								),

						//Method #18: k
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #19: l
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.Float.class)

								),

						//Method #20: m
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #21: n
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #22: o
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #23: p
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #24: q
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #25: r
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
								(		PRIVATE | EXACT ,

										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | EXACT )

								),

						//Constructor #1
						new ConstructorProfile
								(		SYNTHETIC | EXACT ,

										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , STATIC | SYNTHETIC | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEY_DEFINITION_KEY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.h.ac");
		newProfile.setKnownPath("com.touchtype.keyboard");

		newProfile.setMinDepth(1);
		newProfile.setMaxDepth(1);
		newProfile.setModifiers(PUBLIC );

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
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e

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
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #4: a_
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #6: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #7: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #8: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #9: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #10: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #12: toString
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

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
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEYBOARD_LOADER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.t");
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
						new ClassItem(android.content.SharedPreferences.OnSharedPreferenceChangeListener.class),
						new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT )),	//A
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//B
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//C
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//D
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT )),	//E
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//F
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//G
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//H
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//I
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//J
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//K
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//L
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//f
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ARRAY | EXACT )),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ARRAY | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )),	//k
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int[].class)),	//m
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//n
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//o
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.google.common" , PUBLIC | ABSTRACT | EXACT )),	//p
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//q
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | ABSTRACT | ARRAY | EXACT )),	//r
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | ENUM | EXACT )),	//s
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//t
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//u
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//v
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//w
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//x
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//y
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//z

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
										new ClassItem(java.util.Set.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | ABSTRACT | ARRAY | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(int.class)

								),

						//Method #2: c
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #3: c
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #4: d
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #5: e
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(int.class)

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.LanguageLayoutChangeSource" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.util.Map.class)

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(java.util.Map.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #20: a_
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.Object.class),
										new ClassItem(int.class)

								),

						//Method #21: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #22: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #23: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #24: k
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #25: onSharedPreferenceChanged
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.SharedPreferences.class),
										new ClassItem(java.lang.String.class)

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
										new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_POINTER_LOCATION_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.view.c.i$c");
		newProfile.setKnownPath("com.touchtype.keyboard.view");

		newProfile.setMinDepth(2);
		newProfile.setMaxDepth(2);
		newProfile.setModifiers(PUBLIC | STATIC | INTERFACE | ABSTRACT );

		newProfile.setTypeParamCount(0);
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

				});
		/////////////////////////
		//Declared Methods
		/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(android.graphics.PointF.class)

								),

						//Method #1: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(android.graphics.PointF.class)

								),

						//Method #2: c
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(float.class)

								),

						//Method #3: d
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(float.class)

								),

						//Method #4: e
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(long.class)

								),

						//Method #5: f
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(long.class)

								),

						//Method #6: g
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

								),

						//Method #7: h
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

								),

						//Method #8: i
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )

								),

						//Method #9: j
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(android.graphics.PointF.class)

								),

						//Method #10: k
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(android.graphics.PointF.class)

								),


				});
		/////////////////////////
		//Declared Constructors
		/////////////////////////
		newProfile.setDeclaredConstructors(new ConstructorProfile[]
				{

				});

		return newProfile;
	}

	public static ClassProfile get_NORMAL_BUTTON_CLICK_ITEM_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.h.h.c");
		newProfile.setKnownPath("com.touchtype.keyboard");

		newProfile.setMinDepth(2);
		newProfile.setMaxDepth(2);
		newProfile.setModifiers(PUBLIC | FINAL );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem("com.touchtype.keyboard" , PUBLIC | ABSTRACT | EXACT ));
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
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//a
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c

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

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.util.EnumSet.class)

								),

						//Method #2: a_
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: f
						new MethodProfile
								(
										PROTECTED | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

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

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}
}
