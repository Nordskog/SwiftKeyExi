package com.mayulive.swiftkeyexi.xposed.predictions;

import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ClassProfile;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;
import com.mayulive.xposed.classhunter.profiles.FieldItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import static com.mayulive.xposed.classhunter.Modifiers.*;

public class PredictionProfiles
{



	protected static ClassProfile get_UPDATE_CANDIDATE_DISPLAY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.candidates.a");
		newProfile.setKnownPath("com.touchtype.keyboard.candidates");

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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )),	//d

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
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #1: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #2: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT )

								),

						//Method #5: equals
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.Object.class)

								),

						//Method #6: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #7: hashCode
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

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(java.util.List.class),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}

	protected static ClassProfile get_CANDIDATES_VIEW_FACTORY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.view.c.d");
		newProfile.setKnownPath("com.touchtype.keyboard.view");

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

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.widget.LinearLayout.class),
										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.widget.LinearLayout.class),
										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT )

								),

						//Method #6: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

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


	protected static ClassProfile get_CANDIDATE_VIEW_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.candidates.view.l");
		newProfile.setKnownPath("com.touchtype.keyboard.candidates.view");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC | FINAL );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem("com.touchtype.keyboard.candidates.view" , PUBLIC | ABSTRACT | EXACT ));
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
						new ClassItem("com.touchtype.keyboard.candidates.view" , PUBLIC | STATIC | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates.view" , PUBLIC | FINAL | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.text.TextPaint.class)),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.graphics.Rect.class)),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(boolean.class)),	//j
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//k
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//l
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(float.class)),	//m
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//n

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
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #2: getContentDrawable
						new MethodProfile
								(
										PROTECTED | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class)

								),

						//Method #3: onAttachedToWindow
						new MethodProfile
								(
										PROTECTED | EXACT ,
										new ClassItem(void.class)

								),

						//Method #4: onDetachedFromWindow
						new MethodProfile
								(
										PROTECTED | EXACT ,
										new ClassItem(void.class)

								),

						//Method #5: onMeasure
						new MethodProfile
								(
										PROTECTED | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #6: setCandidate
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: setShortcutText
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #8: setStyleId
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

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
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),


				});

		return newProfile;
	}


	protected static ClassProfile get_COMMIT_TEXT_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.e.c");
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
						new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Nested Classes
/////////////////////////
		newProfile.setNestedClasses(new ClassItem[]
				{
						new ClassItem("com.touchtype.keyboard" , PRIVATE | STATIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( EXACT , 	new ClassItem(int.class)),	//b
						new FieldItem( EXACT , 	new ClassItem(android.view.inputmethod.InputConnection.class)),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy" , PUBLIC | FINAL | EXACT )),	//n
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//o

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard" , PRIVATE | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(android.view.inputmethod.InputConnection.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class)

								),

						//Method #4: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(char.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class)

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

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.net.Uri.class),
										new ClassItem(android.net.Uri.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.view.inputmethod.CompletionInfo.class)

								),

						//Method #13: a
						new MethodProfile
								(
										EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.view.inputmethod.InputConnection.class)

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.common" , PUBLIC | FINAL | EXACT )

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class)

								),

						//Method #27: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.List.class)

								),

						//Method #28: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #29: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #30: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(boolean.class),
										new ClassItem("com.google.common" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #31: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(boolean.class),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #32: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #33: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #34: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #35: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #36: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #37: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #38: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #39: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #40: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #41: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #42: f
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
								(		PUBLIC | EXACT ,

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard.view.fancy" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}


	protected static ClassProfile get_CANDIDATE_CLICK_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.candidates.view.l$a");
		newProfile.setKnownPath("com.touchtype.keyboard.candidates.view");

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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//d

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

										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
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

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	protected static ClassProfile get_BU_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.h.ab");
		newProfile.setKnownPath("com.touchtype.keyboard");

		newProfile.setMinDepth(1);
		newProfile.setMaxDepth(1);
		newProfile.setModifiers(PUBLIC | INTERFACE | ABSTRACT );

		newProfile.setTypeParamCount(0);
/////////////////////////
//Interfaces
/////////////////////////
		newProfile.setInterfaces(new ClassItem[]
				{
						new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
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
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.net.Uri.class),
										new ClassItem(android.net.Uri.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.CompletionInfo.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.common" , PUBLIC | FINAL | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.util.EnumSet.class)

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.Tokenizer" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.LanguageLoadState" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.FluencyCandidate" , PUBLIC | EXACT )

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #27: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.Punctuator" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #28: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #29: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #30: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class)

								),

						//Method #31: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #32: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #33: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #34: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #35: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #36: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #37: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #38: c
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #39: c
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #40: c
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #41: d
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #42: d
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #43: d
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #44: e
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #45: e
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #46: f
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #47: f
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #48: g
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #49: h
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #50: i
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #51: j
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #52: k
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #53: l
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #54: m
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #55: n
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #56: o
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #57: p
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

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


	protected static ClassProfile get_RESULT_TYPE_ENUM_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.candidates.g");
		newProfile.setKnownPath("com.touchtype.keyboard.candidates");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC | FINAL | ENUM );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem(java.lang.Enum.class));
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
//Enum Values
/////////////////////////
		newProfile.setEnumValues(new String[]
				{
						"DEFAULT",
						"TAP",
						"HARD",
						"FLOW",
						"LAST_USED",
						"FLOW_FAILED",
						"FLOW_SUCCEEDED",
						"EXPANDED",
						"FLOW_LIFT_OFF",

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//j
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//a
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//b
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//c
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//d
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//e
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//f
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//g
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//h
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//i
						new FieldItem( PRIVATE | STATIC | FINAL | SYNTHETIC | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | ARRAY | EXACT )),	//k

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
										new ClassItem(int.class)

								),

						//Method #1: valueOf
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #2: values
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | THIS | ARRAY | EXACT )

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

										new ClassItem(java.lang.String.class),
										new ClassItem(int.class)

								),

						//Constructor #1
						new ConstructorProfile
								(		PRIVATE | EXACT ,

										new ClassItem(java.lang.String.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),


				});

		return newProfile;
	}



	protected static ClassProfile get_HANDLE_CANDIDATE_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.h.c.a");
		newProfile.setKnownPath("com.touchtype.keyboard");

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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.report.TouchTypeStats" , PUBLIC | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//g

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
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #3: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.List.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.FlowAutoCommitCandidate" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #16: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

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
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.report.TouchTypeStats" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}



	protected static ClassProfile get_CANDIDATE_REMOVE_DIALOG_FACTORY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.candidates.view.i");
		newProfile.setKnownPath("com.touchtype.keyboard.candidates.view");

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
										new ClassItem(android.app.Dialog.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

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



	protected static ClassProfile get_CANDIDATE_UPDATER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.candidates.k");
		newProfile.setKnownPath("com.touchtype.keyboard.candidates");

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
						new FieldItem( FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.concurrent.Executor.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyProfilerWrapper" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.util.concurrent.Future.class)),	//g
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//k

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
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | STATIC | EXACT ),
										new ClassItem(java.util.Set.class),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(java.util.Set.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: b
						new MethodProfile
								(
										EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT )

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
										new ClassItem("com.touchtype_fluency.service.FluencyProfilerWrapper" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}


	protected static ClassProfile get_KEYBOARD_UX_OPTIONS_INTERFACE_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.ba");
		newProfile.setKnownPath("com.touchtype.keyboard");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC | INTERFACE | ABSTRACT );

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
						//Method #0: A
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #1: B
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #2: C
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #3: D
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #4: E
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #5: F
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #6: G
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #7: H
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #8: I
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #9: J
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

								),

						//Method #10: K
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #11: L
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #12: M
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

								),

						//Method #13: N
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #14: O
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #15: P
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #16: Q
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #17: R
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #18: S
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #19: T
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

								),

						//Method #20: U
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

								),

						//Method #21: V
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #22: W
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #23: X
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #24: Y
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #25: Z
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(java.util.Set.class)

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #27: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #28: aa
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #29: ab
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #30: ac
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #31: ad
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #32: ae
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #33: af
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #34: ag
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #35: ah
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #36: h
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #37: i
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #38: j
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #39: k
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #40: m
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #41: n
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #42: o
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #43: p
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #44: q
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #45: r
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #46: s
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #47: t
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #48: u
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #49: v
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

								),

						//Method #50: w
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

								),

						//Method #51: x
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

								),

						//Method #52: y
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

								),

						//Method #53: z
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(int.class)

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

}


