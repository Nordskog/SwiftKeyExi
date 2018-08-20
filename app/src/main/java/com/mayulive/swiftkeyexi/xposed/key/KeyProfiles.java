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

		newProfile.setFullPath("keyfieldscontainingclass.keyfieldssubclass");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(1);
		newProfile.setMaxDepth(1);
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
						new FieldItem( EXACT , 	new ClassItem( PUBLIC | FINAL | ENUM | EXACT )),	//f
						new FieldItem( EXACT , 	new ClassItem( PUBLIC | FINAL | ENUM | EXACT )),	//g
						new FieldItem( EXACT , 	new ClassItem( PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//h
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
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(int.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(android.graphics.RectF.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.Float.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.Integer.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(boolean.class)

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | FINAL | EXACT )

								),

						//Method #10: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #11: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #12: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #13: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(boolean.class)

								),

						//Method #14: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #15: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #16: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #17: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.util.List.class)

								),

						//Method #18: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #19: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(PUBLIC | STATIC | THIS | EXACT ),

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


	public static ClassProfile get_KEY_RAW_DEFINITION_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("ewx");
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
						new ClassItem("" , PUBLIC | STATIC | EXACT ),
						new ClassItem("" , PUBLIC | STATIC | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//c
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )),	//d
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//f
						new FieldItem( FINAL | EXACT , 	new ClassItem(java.lang.Float.class)),	//g
						new FieldItem( FINAL | EXACT , 	new ClassItem(int.class)),	//h
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(float.class)),	//i
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(float.class)),	//j
						new FieldItem( FINAL | EXACT , 	new ClassItem(android.graphics.RectF.class)),	//k
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//l
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//m
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(boolean.class)),	//n
						new FieldItem( FINAL | EXACT , 	new ClassItem(boolean.class)),	//o
						new FieldItem( FINAL | EXACT , 	new ClassItem(int.class)),	//p
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//r
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//s
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//t
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//u
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.lang.Integer.class)),	//v

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
										new ClassItem("" , PUBLIC | STATIC | EXACT ),

										new ClassItem(android.util.TypedValue.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),

										new ClassItem(android.content.res.TypedArray.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(java.lang.Float.class),

										new ClassItem(java.util.List.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.List.class)

								),

						//Method #7: b
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(java.util.List.class)

								),

						//Method #8: c
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(float.class),

										new ClassItem(java.util.List.class)

								),

						//Method #9: d
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(float.class),

										new ClassItem(java.util.List.class)

								),

						//Method #10: e
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(java.util.List.class)

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),

										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #12: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #13: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #14: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #15: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #16: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #17: g
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #18: h
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #19: i
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #20: j
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #21: k
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #22: l
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #23: m
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
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

										new ClassItem("" , PUBLIC | STATIC | EXACT )

								),

						//Constructor #1
						new ConstructorProfile
								(		SYNTHETIC | EXACT ,

										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem(byte.class)

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEY_DEFINITION_KEY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("exn");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC );

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

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//d
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
										PUBLIC | FINAL | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #5: a_
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

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
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #9: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #10: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

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

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEYBOARD_LOADER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("dvr");
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
						new ClassItem(android.content.SharedPreferences.OnSharedPreferenceChangeListener.class),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//A
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//B
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | ABSTRACT | EXACT )),	//C
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//D
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//E
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//F
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//G
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//H
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//I
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//J
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//K
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//L
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//M
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//N
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//O
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.SharedPreferences.class)),	//P
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//Q
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//b
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | ABSTRACT | EXACT )),	//c
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(boolean.class)),	//d
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | ABSTRACT | ARRAY | EXACT )),	//e
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int.class)),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.SymbolsNumberDisplay" , PUBLIC | FINAL | ENUM | EXACT )),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//n
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//o
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//p
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ARRAY | EXACT )),	//q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ARRAY | EXACT )),	//r
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )),	//s
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//t
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int[].class)),	//u
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//v
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )),	//w
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//x
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//y
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//z

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

										new ClassItem("" , PUBLIC | ABSTRACT | ARRAY | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | ABSTRACT | ARRAY | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ARRAY | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem(int[].class),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: c
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #5: d
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #6: e
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(int.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.LanguageLayoutChangeSource" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(int.class)

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(int.class)

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | FINAL | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.Object.class),
										new ClassItem(int.class)

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #18: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #19: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #20: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #21: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #22: m
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #23: onSharedPreferenceChanged
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
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
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.content.SharedPreferences.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_POINTER_LOCATION_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("goj$c");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(1);
		newProfile.setMaxDepth(1);
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
										new ClassItem("" , PUBLIC | FINAL | EXACT )

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

		newProfile.setFullPath("fbz");
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

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//a
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.util.EnumSet.class)

								),

						//Method #3: a_
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: d
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

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}
}
