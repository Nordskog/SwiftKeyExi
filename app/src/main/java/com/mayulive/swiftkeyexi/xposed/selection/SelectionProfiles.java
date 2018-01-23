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


	protected static ClassProfile get_SWIPE_DELEGATE_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.view.v");
		newProfile.setKnownPath("com.touchtype.keyboard.view");

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
						new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//c
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//d
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(android.graphics.PointF.class)),	//e
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(float.class)),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(float.class)),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(float.class)),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(float.class)),	//j

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
										new ClassItem(void.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PROTECTED | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(float.class),
										new ClassItem(float.class),
										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #4: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
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

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(float.class),
										new ClassItem(float.class),
										new ClassItem(float.class),
										new ClassItem(float.class)

								),


				});

		return newProfile;
	}


	protected static ClassProfile get_FLOW_DELEGATE_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.h.h.k");
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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(float.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.graphics.PointF.class)),	//c
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//f

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

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | ENUM | EXACT )

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
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(int.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #9: a_
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #10: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #11: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #12: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #13: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #14: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #15: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #16: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #17: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #18: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #19: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #20: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #21: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #22: h
						new MethodProfile
								(
										PUBLIC | EXACT ,
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

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(float.class),
										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}

	protected static ClassProfile get_FRAME_HOLDER_FACTORY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.view.t");
		newProfile.setKnownPath("com.touchtype.keyboard.view");

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
						new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//A
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.consent" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//B
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.storage" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//C
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )),	//D
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//E
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | FINAL | EXACT )),	//F
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//G
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.clipboard" , PUBLIC | FINAL | EXACT )),	//H
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )),	//I
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//J
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//K
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//L
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT )),	//M
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//N
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//O
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//P
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )),	//Q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.preferences" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//R
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.preferences" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//S
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//T
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.consent" , PUBLIC | FINAL | EXACT )),	//U
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//V
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//W
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT )),	//X
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//Y
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT )),	//Z
						new FieldItem( FINAL | EXACT , 	new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.consent" , PUBLIC | FINAL | EXACT )),	//aa
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//ab
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )),	//ac
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//ad
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//ae
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//af
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.richcontent" , PUBLIC | FINAL | EXACT )),	//ag
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.superribbontextbox" , PUBLIC | FINAL | EXACT )),	//ah
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.richcontent.gifs" , PUBLIC | FINAL | EXACT )),	//ai
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//aj
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//ak
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//al
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.richcontent.gifs" , PUBLIC | FINAL | EXACT )),	//am
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT )),	//an
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT )),	//ao
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.toolbar" , PUBLIC | FINAL | EXACT )),	//ap
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.preferences" , PUBLIC | FINAL | EXACT )),	//aq
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//ar
						new FieldItem( FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.service" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("android.content.SharedPreferences" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | EXACT )),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//n
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//o
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.toolbar" , PUBLIC | FINAL | EXACT )),	//p
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.toolbar" , PUBLIC | FINAL | EXACT )),	//q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//r
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//s
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//t
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//u
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.richcontent.gifs" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//v
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy.richcontent.stickers" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//w
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard.view.fancy" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//x
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//y
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//z

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

										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(boolean.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard.expandedcandidate" , PUBLIC | FINAL | EXACT ),

										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard.candidates.view" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.touchtype.keyboard.toolbar.ToolbarFrame" , PUBLIC | EXACT ),

										new ClassItem(android.view.View.class),
										new ClassItem(int.class)

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
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.expandedcandidate" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.expandedcandidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("com.touchtype.keyboard.candidates.view" , PUBLIC | FINAL | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class),
										new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.toolbar.ToolbarFrame" , PUBLIC | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class),
										new ClassItem(boolean.class)

								),

						//Method #9: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #11: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | EXACT )

								),

						//Method #12: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #13: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(boolean.class)

								),

						//Method #14: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #15: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class),
										new ClassItem(int.class)

								),

						//Method #16: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #17: c
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #18: d
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #19: e
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #22: apply
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
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
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.telemetry" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.richcontent.gifs" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.richcontent.stickers" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("android.content.SharedPreferences" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.preferences" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.preferences" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.consent" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.storage" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.toolbar" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.toolbar" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.clipboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.emoji" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.frames" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.consent" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.quicksettings" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.consent" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.richcontent" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.superribbontextbox" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.richcontent.gifs" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.fancy.richcontent.gifs" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.candidates" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.service" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.view" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard.toolbar" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.preferences" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}




	public static ClassProfile get_SELECTION_CHANGED_INPUT_EVENT_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.g.a.z");
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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(boolean.class)),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )),	//i

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

										new ClassItem("com.touchtype.keyboard" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #2: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #3: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #4: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #5: h
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #6: i
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #7: j
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #8: toString
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

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Constructor #1
						new ConstructorProfile
								(		PUBLIC | EXACT ,

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(boolean.class),
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}



}
