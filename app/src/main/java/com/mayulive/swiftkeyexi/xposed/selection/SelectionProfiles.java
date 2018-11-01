package com.mayulive.swiftkeyexi.xposed.selection;

import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ClassProfile;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;
import com.mayulive.xposed.classhunter.profiles.FieldItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import static com.mayulive.xposed.classhunter.Modifiers.*;

/**
 * Created by Roughy on 3/26/2017.
 */

public class SelectionProfiles
{


	public static ClassProfile get_SELECTION_CHANGED_METHOD_ARGUMENT_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("emq");
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
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Nested Classes
/////////////////////////
		newProfile.setNestedClasses(new ClassItem[]
				{
						new ClassItem("" , STATIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int.class)),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//f

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
										new ClassItem(boolean.class),

										new ClassItem("" , STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.Point" , PUBLIC | EXACT ),
										new ClassItem(long.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.view.inputmethod.CompletionInfo.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.KeyPress" , PUBLIC | ARRAY | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #27: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #28: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #29: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #30: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #31: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #32: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #33: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #34: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #35: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #36: f
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
								(		PUBLIC | EXACT ,

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.util.Set.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_SELECTION_CHANGED_INFO_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("eqp");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//c
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//d
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(boolean.class)),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//i

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

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #2: toString
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
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

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Constructor #1
						new ConstructorProfile
								(		PUBLIC | EXACT ,

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}


	protected static ClassProfile get_SWIPE_DELEGATE_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("fcr");
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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//a
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//b

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
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}


	protected static ClassProfile get_FLOW_DELEGATE_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("fcj");
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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(float.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.graphics.PointF.class)),	//c
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g

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
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.util.EnumSet.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #8: a_
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #9: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #10: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #12: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #13: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #14: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #15: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #16: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #17: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #18: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #19: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #20: g
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #21: h
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

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(float.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	protected static ClassProfile get_FRAME_HOLDER_FACTORY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("fvn");
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

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//A
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//B
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//C
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//D
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//E
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//F
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//G
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//H
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//I
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//J
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//K
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//L
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//M
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//N
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//O
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//P
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//Q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//R
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//S
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//T
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//U
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//V
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//W
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//X
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//Y
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//Z
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aa
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//ab
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(boolean.class)),	//ac
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT )),	//ad
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ae
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//af
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ag
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//ah
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ai
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//aj
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ak
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//al
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//am
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//an
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//d
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//g
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//i
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//k
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//l
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//n
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.SharedPreferences.class)),	//o
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//p
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//r
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//s
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//t
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//u
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//v
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//w
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//x
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
										PRIVATE | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(int.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class),
										new ClassItem(int.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #9: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #10: c
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #11: d
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #12: e
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #13: f
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #14: g
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #15: h
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #16: i
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.ModelTrackingFrame" , PUBLIC | EXACT )

								),

						//Method #18: apply
						new MethodProfile
								(
										PUBLIC | FINAL | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Object.class),

										new ClassItem(java.lang.Object.class)

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
										new ClassItem(android.content.SharedPreferences.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}




	public static ClassProfile 	get_SELECTION_CHANGED_INPUT_EVENT_PROFILE( Class emq)
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("eqz");
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

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//c

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PUBLIC | FINAL | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(emq),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

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

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}



}
