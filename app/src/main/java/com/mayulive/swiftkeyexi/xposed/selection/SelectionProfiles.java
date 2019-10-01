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

		newProfile.setFullPath("gua");
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
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Nested Classes
/////////////////////////
		newProfile.setNestedClasses(new ClassItem[]
				{
						new ClassItem("" , PRIVATE | STATIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//c
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//d
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(boolean.class)),	//e
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int.class)),	//f

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.KeyPress" , PUBLIC | ARRAY | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | EXACT )

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

										new ClassItem(int.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.Point" , PUBLIC | EXACT ),
										new ClassItem(long.class)

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.KeyPress" , PUBLIC | ARRAY | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("hua.a" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PRIVATE | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #27: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #28: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #29: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(boolean.class),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #30: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #31: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #32: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #33: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #34: clearMetaKeyStates
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class)

								),

						//Method #35: commitCompletion
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.view.inputmethod.CompletionInfo.class)

								),

						//Method #36: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #37: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #38: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #39: finishComposingText
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #40: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #41: setComposingRegion
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #42: setSelection
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
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

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.util.Set.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_SELECTION_CHANGED_INFO_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("qva");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC );

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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//c
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//d
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(boolean.class)),	//h
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//i

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

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #2: toString
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

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | EXACT )

								),


				});

		return newProfile;
	}


	protected static ClassProfile get_SWIPE_DELEGATE_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("ZAa");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC );

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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//a
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(boolean.class)),	//b

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
										new ClassItem(void.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.util.EnumSet.class)

								),

						//Method #4: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

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
										new ClassItem("" , PUBLIC | EXACT )

								),


				});

		return newProfile;
	}


	protected static ClassProfile get_FLOW_DELEGATE_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("TAa");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(float.class)),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.graphics.PointF.class)),	//c
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(boolean.class)),	//d
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g

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

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.util.EnumSet.class)

								),

						//Method #8: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #9: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #10: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #11: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #12: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #13: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #14: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #15: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #16: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #17: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #18: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #19: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #20: h
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #21: i
						new MethodProfile
								(
										PUBLIC | EXACT ,
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
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(float.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}


	public static ClassProfile 	get_SELECTION_CHANGED_INPUT_EVENT_PROFILE( Class emq)
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("Hva");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//c

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

										new ClassItem("" , PUBLIC | EXACT ),
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
										new ClassItem("" , PUBLIC | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEYBOARD_FRAME_HOLDER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.view.KeyboardFrameHolderFrame");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(4);
		newProfile.setMaxDepth(4);
		newProfile.setModifiers(PUBLIC );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem(android.widget.FrameLayout.class));
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//f
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(android.view.View.class)),	//g
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//h
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//i
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(android.view.View.class)),	//j

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

										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | BRIDGE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.Object.class),
										new ClassItem(int.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #4: get
						new MethodProfile
								(
										PUBLIC | BRIDGE | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Object.class)

								),

						//Method #5: get
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | STATIC | FINAL | EXACT )

								),

						//Method #6: getEcwView
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #7: onAttachedToWindow
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #8: onDetachedFromWindow
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #9: onMeasure
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
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

										new ClassItem(android.content.Context.class)

								),

						//Constructor #1
						new ConstructorProfile
								(		PUBLIC | EXACT ,

										new ClassItem(android.content.Context.class),
										new ClassItem(android.util.AttributeSet.class)

								),


				});

		return newProfile;
	}


}
