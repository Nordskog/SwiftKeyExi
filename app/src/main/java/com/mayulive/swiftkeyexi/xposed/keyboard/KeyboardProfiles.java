package com.mayulive.swiftkeyexi.xposed.keyboard;

import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ClassProfile;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;
import com.mayulive.xposed.classhunter.profiles.FieldItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import static com.mayulive.xposed.classhunter.Modifiers.*;


public class KeyboardProfiles
{

	public static ClassProfile get_TOOLBAR_OPEN_BUTTON_OVERLAY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("eld");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC | FINAL );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem(android.widget.FrameLayout.class));
/////////////////////////
//Interfaces
/////////////////////////
		newProfile.setInterfaces(new ClassItem[]
				{
						new ClassItem(android.view.ViewTreeObserver.OnGlobalLayoutListener.class),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Function" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//m
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//n
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//o
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | ABSTRACT | EXACT )),	//p
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard.toolbar.ToolbarButton" , PUBLIC | EXACT )),	//q

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #1: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #2: c
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #3: d
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #4: lambda$QZwAKT-Yd5UbC93YWX8uGXbMQ-s
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #5: setUpRedDot
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.widget.ImageView.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #7: onAttachedToWindow
						new MethodProfile
								(
										PROTECTED | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #8: onDetachedFromWindow
						new MethodProfile
								(
										PROTECTED | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #9: onGlobalLayout
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #10: onKeyHeightUpdated
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #11: onModelUpdated
						new MethodProfile
								(
										PUBLIC | FINAL | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.Object.class),
										new ClassItem(int.class)

								),

						//Method #12: onVisibilityChanged
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #13: setVisibility
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

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

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Function" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEY_HEIGHT_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("gnx");
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
						new ClassItem(android.content.SharedPreferences.OnSharedPreferenceChangeListener.class)

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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.res.Resources.class)),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//f
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//g

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
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(int.class)

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
										new ClassItem(int.class)

								),

						//Method #6: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(int.class)

								),

						//Method #7: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #8: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(int.class)

								),

						//Method #9: onSharedPreferenceChanged
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

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.content.res.Resources.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}



	public static ClassProfile get_FULL_KEYBOARD_SERVICE_DELEGATE_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("crv");
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
						new ClassItem("com.touchtype_fluency.service.HandwritingModelLoadStateListener" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("com.touchtype_fluency.service.LanguageLoadStateListener" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("com.touchtype_fluency.service.receiver.SDCardListener" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
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
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//A
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//B
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//C
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | ABSTRACT | EXACT )),	//D
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//E
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//F
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//G
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//H
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//I
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.languagepacks.LanguagePackListener" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//J
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//K
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//L
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.concurrent.ExecutorService.class)),	//M
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//N
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//O
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//P
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//Q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//R
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//S
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//T
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//U
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//V
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//W
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//X
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//Y
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.ClipboardManager.class)),	//Z
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//aA
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aB
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | EXACT )),	//aC
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//aD
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aE
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aF
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aG
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aH
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aI
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aJ
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aK
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aL
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//aM
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.util.Locale.class)),	//aN
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aO
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aP
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aQ
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//aR
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype_fluency.service.handwriting.HandwritingModelLoadState" , PUBLIC | FINAL | ENUM | EXACT )),	//aS
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aT
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aU
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//aV
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aW
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//aX
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aY
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aZ
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aa
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ab
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.Collection.class)),	//ac
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ad
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//ae
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//af
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ag
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//ah
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ai
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aj
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//ak
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//al
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//am
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//an
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ao
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//ap
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//aq
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.google.common.base.Function" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//ar
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype.keyboard.service.LockScreenWatcher" , PUBLIC | EXACT )),	//as
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.receiver.SDCardReceiverWrapper" , PUBLIC | EXACT )),	//at
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype_fluency.service.LanguageLoadStateCombiner" , PUBLIC | EXACT )),	//au
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//av
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//aw
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//ax
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ay
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//az
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//ba
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//bb
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//bc
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//bd
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//be
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.lang.Runnable.class)),	//bf
						new FieldItem( FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )),	//c
						new FieldItem( FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.FullLayoutProvider" , PUBLIC | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.res.Resources.class)),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//n
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//o
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//p
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//r
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//s
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//t
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//u
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//v
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//w
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//x
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
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.os.IBinder.class),

										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.content.Context.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #10: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.util.concurrent.ExecutorService.class),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #12: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #13: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #15: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Iterable.class),

										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )

								),

						//Method #16: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #17: a
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #18: a
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.widget.FrameLayout.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class)

								),

						//Method #19: a
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #20: a
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #21: a
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #22: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.concurrent.ExecutorService.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #23: a
						new MethodProfile
								(
										PRIVATE | VARARGS | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View[].class)

								),

						//Method #24: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #25: b
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #26: b
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #27: b
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #28: b
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #29: b
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.content.Context.class)

								),

						//Method #30: b
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #31: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #32: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(boolean.class)

								),

						//Method #33: c
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.transition.Transition.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #34: c
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #35: c
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #36: c
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Float.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #37: c
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #38: d
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #39: d
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #40: d
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #41: d
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #42: e
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #43: e
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #44: f
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #45: f
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Float.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #46: g
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.util.DisplayMetrics.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #47: g
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #48: h
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #49: h
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #50: i
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #51: j
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #52: k
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #53: l
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #54: l
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #55: lambda$01xmCpaUOasp7yjQ2xpk1McOpHA
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #56: lambda$1H3VhlZaiArHtQOIX5pM2N7xS9I
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #57: lambda$1XeBEoHUMANVIyOX12_BMxKFmoA
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #58: lambda$3txlVaiPgQpb8LysyJvhYfZ-QSQ
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #59: lambda$4DygcVvW9yBqW9qqNmBZlKVxskc
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #60: lambda$5exCt5ldGT8ou4ARO-JhE5TMxto
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #61: lambda$5xlGnFoGZ2XtugHhFXQGKiRWO08
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #62: lambda$6UX7PUNH9QtxzUP1Bx4xw59gzYU
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #63: lambda$7fcywbC3PwHv0IBCGxYJ1aXs5ZU
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #64: lambda$9kgwi3qpvYHU8mY5c7hEzZk1-PM
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.os.IBinder.class),

										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT )

								),

						//Method #65: lambda$9zNYfAlef1VNSm2AnW92G5VfXUc
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #66: lambda$AoO_O0bbWVXbmnWZT83Mj28lQRM
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.util.concurrent.ExecutorService.class),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #67: lambda$BKemF0i_3aZDe4dfjJiuXPe3cfQ
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #68: lambda$DPjVj64xC9lL8xQmKSU2ZzIO2Xw
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #69: lambda$EAcusA6UPo8BP_WPsFuag3iJNME
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #70: lambda$GsuOWJi8IpUH7r0q7jDnIVvgsTQ
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Float.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #71: lambda$Gz8oGLRwcg5pjps3ZmmUJZv71og
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.concurrent.ExecutorService.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #72: lambda$HC4jJIaEdZM1RxTtlKUfuJdpTak
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.content.Context.class)

								),

						//Method #73: lambda$KJy3RElKA06ZaEyNnK4b8NPxzZw
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.content.Context.class)

								),

						//Method #74: lambda$KrWUKtYUBFvlOfenQ-iIIKE79d0
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #75: lambda$L25J1NJFEtOKBkB9B32O8wn0DwE
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #76: lambda$LLnzIKEmhGcdk0UofmqVFIgnq94
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #77: lambda$MhNN2AuV8REh3kv0SD7ZHl6qGeQ
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #78: lambda$MlpfoFc7ME2Hv9A-QtgmxPuywTE
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #79: lambda$N8Po6O_Pl5NNb1xB88K23YAJdv4
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #80: lambda$P3KDWH2Ael_AnmN5lhp6_mURrXM
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #81: lambda$Q0EKcqyld3j5zDV6XtAWPurRFX8
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(android.widget.FrameLayout.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class)

								),

						//Method #82: lambda$SDP_EkuSfO777w67iz-vjm1rlms
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Iterable.class),

										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )

								),

						//Method #83: lambda$VPIiaMJ5RFA7AJZMgy6Ea2AlEc0
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #84: lambda$V_kkDlS1z1FKj0mp9dAqblQYmVc
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #85: lambda$WU8zLPn3ZQMICJLaS48LL_PjBh8
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #86: lambda$WXL3Gt3TjBXhCCwCZAaf28Wm24A
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #87: lambda$ZfSqieTe60fovZkM9STUUEzmvpo
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.util.DisplayMetrics.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #88: lambda$b98bK7TmgOQOdWqmzdlePZzMLGc
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #89: lambda$cwwi5APaIfEgdYVIB_IGBtrtsNc
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Float.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #90: lambda$d46ZbwAS3CWQgH3HThNo1CKnwSE
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class)

								),

						//Method #91: lambda$diszj5kOAUSORVY-xrgpmmFT-2w
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #92: lambda$fjJpqJ-pM4fGDDuB1hUOrjIm0Hc
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #93: lambda$fvKIFbD7O89bfpioKvvT7ng4w-s
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #94: lambda$gsWEMbvEBsU0B2cenZ45UqUbwvQ
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #95: lambda$hfT-Um3IZWGF_T3O964Xwk9QQEU
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #96: lambda$jVUAzbQjXEcpWuj5Fvgd5r02Wss
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #97: lambda$kJouaVI9QLJuAlQyDsBezV6Clvw
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #98: lambda$opbfL6jmYHST_KY5F5AzYeVhn94
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.transition.Transition.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #99: lambda$pibRE7Ez-v9duyMmgCeSj2NTVeM
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #100: lambda$ro_hv8u6P9fgiaRGtPBrBipl4B0
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #101: lambda$vr1dFWAlzKZ7zHtdgaSjgK4-e4w
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #102: lambda$wsp4vHHLzSI5zSbLA0BEjPGpX2o
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #103: lambda$x37Hf-KKKwTmbmADyLKIzpJ2_Ns
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #104: lambda$xk3PyyVoiHZS9Ki9C1Jlv8bOuuc
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #105: m
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #106: n
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #107: o
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #108: p
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #109: q
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class)

								),

						//Method #110: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #111: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #112: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #113: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #114: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.res.Configuration.class)

								),

						//Method #115: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.inputmethodservice.InputMethodService.Insets.class)

								),

						//Method #116: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.Window.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #117: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.inputmethod.CursorAnchorInfo.class)

								),

						//Method #118: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class)

								),

						//Method #119: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #120: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.CompletionInfo.class)

								),

						//Method #121: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #122: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.inputmethod.CompletionInfo[].class)

								),

						//Method #123: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class)

								),

						//Method #124: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #125: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class)

								),

						//Method #126: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #127: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class)

								),

						//Method #128: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #129: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #130: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #131: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(int.class)

								),

						//Method #132: g
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #133: h
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #134: i
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #135: j
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #136: k
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #137: onHandwritingModelLoadStateChanged
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.handwriting.HandwritingModelLoadState" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #138: onLanguageLoadStateChanged
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.LanguageLoadState" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #139: onMediaMounted
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #140: onMediaUnmounted
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
								(		PRIVATE | EXACT ,

										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem(android.content.res.Resources.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.util.concurrent.ExecutorService.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.content.ClipboardManager.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.Locale.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.util.Collection.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.receiver.SDCardReceiverWrapper" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard.service.LockScreenWatcher" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.FullLayoutProvider" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	protected static ClassProfile _get_LOCATION_MANAGER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("edu");
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
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Nested Classes
/////////////////////////
		newProfile.setNestedClasses(new ClassItem[]
				{
						new ClassItem("" , STATIC | FINAL | ENUM | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//c
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.BroadcastReceiver.class)),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.concurrent.ExecutorService.class)),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.net.wifi.WifiManager.class)),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//n
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.content.IntentFilter.class)),	//o
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//p
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.util.UUID.class)),	//q
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(android.location.Location.class)),	//r
						new FieldItem( PUBLIC | STATIC | FINAL | EXACT , 	new ClassItem("com.google.common.collect.ImmutableSet" , PUBLIC | ABSTRACT | EXACT )),	//a

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(android.net.wifi.ScanResult.class),
										new ClassItem(android.net.wifi.ScanResult.class)

								),

						//Method #1: a
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.content.Context.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(int.class)

								),

						//Method #3: a
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(java.util.UUID.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.UUID.class)

								),

						//Method #5: b
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #6: b
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.util.List.class)

								),

						//Method #7: c
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.util.UUID.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #8: c
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #9: d
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #10: e
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #11: f
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #12: g
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #13: h
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #14: i
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #15: lambda$RRR-i-tog12fLWub1PCH3jBp3tQ
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #16: lambda$SCypdTlNDFoiUUX_mogfFZEB7SE
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(java.util.List.class)

								),

						//Method #17: lambda$_pDAIXGHFCY6NX8HWmTIrPSkb8U
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(java.util.List.class)

								),

						//Method #18: lambda$sOAQmnCqJblziLiwmLqpumYRq1g
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #19: lambda$uifN2kw8ovMkvIKJp-8J3L3zEVI
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(android.net.wifi.ScanResult.class),
										new ClassItem(android.net.wifi.ScanResult.class)

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(java.util.UUID.class)

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.location.Location.class)

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.swiftkey.avro.telemetry.sk.android.LocationGpsResultStatus" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #26: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #27: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #28: d
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

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.net.wifi.WifiManager.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.content.IntentFilter.class),
										new ClassItem(java.util.concurrent.ExecutorService.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}


	public static ClassProfile _get_INCOG_CONTROL_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("ejw");
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
						new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int.class)),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//b
						new FieldItem( PRIVATE | STATIC | EXACT , 	new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )),	//c

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
										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class)

								),

						//Method #2: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
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
								(		PRIVATE | EXACT ,

										new ClassItem[0]

								),


				});

		return newProfile;
	}

	public static ClassProfile _get_QUICK_SETTINGS_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("fdo");
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
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.widget.FrameLayout.class)),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.widget.FrameLayout.class)),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j

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
										new ClassItem(java.util.List.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: b
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: lambda$OjhQLFSDN7YUV7u9v1K_Ve1EIwY
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #6: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #7: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #8: d
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
								(		PUBLIC | EXACT ,

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.widget.FrameLayout.class),
										new ClassItem(android.widget.FrameLayout.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}


	public static ClassProfile _get_QUICK_SETTINGS_PREF_REFERENCE_CLASS_PROFILE(Class dyhClass)
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("hmq");
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
						new FieldItem( FINAL | SYNTHETIC | EXACT , 	new ClassItem(dyhClass)),	//a

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: get
						new MethodProfile
								(
										PUBLIC | FINAL | SYNTHETIC | EXACT ,
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

										new ClassItem(dyhClass )

								),


				});

		return newProfile;
	}


	public static ClassProfile get_SEARCH_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("drv");
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
						new ClassItem("" , INTERFACE | ABSTRACT | EXACT )

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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PRIVATE | STATIC | FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//a

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
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

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

	public static ClassProfile get_INSERT_GIF_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("dwe");
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
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.util.Set.class)),	//A
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.util.Set.class)),	//B
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//C
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//D
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.report.TouchTypeStats" , PUBLIC | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//n
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//o
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//p
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//q
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//r
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//s
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.candidates.ForgetCandidateVisitor" , PUBLIC | EXACT )),	//t
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | EXACT )),	//u
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//v
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//w
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//x
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//y
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//z

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
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #10: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #11: c
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #12: g
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #13: r
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #14: s
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.net.Uri.class),
										new ClassItem(android.net.Uri.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.Punctuator" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.KeyPress" , PUBLIC | ARRAY | EXACT )

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #27: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #28: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #29: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #30: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #31: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #32: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #33: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #34: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #35: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #36: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #37: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.CompletionInfo.class)

								),

						//Method #38: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #39: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #40: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #41: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.Tokenizer" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #42: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.LanguageLoadState" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #43: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #44: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #45: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.FluencyCandidate" , PUBLIC | EXACT )

								),

						//Method #46: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #47: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #48: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #49: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.util.EnumSet.class)

								),

						//Method #50: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #51: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #52: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #53: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #54: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #55: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #56: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #57: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #58: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #59: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #60: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #61: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class)

								),

						//Method #62: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #63: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #64: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #65: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #66: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #67: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #68: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #69: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #70: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #71: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #72: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #73: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #74: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #75: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #76: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #77: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #78: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #79: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #80: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #81: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #82: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #83: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #84: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #85: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #86: g
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #87: h
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #88: i
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #89: j
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #90: k
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #91: l
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #92: m
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #93: n
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #94: o
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #95: p
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #96: q
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

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
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype.report.TouchTypeStats" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.ForgetCandidateVisitor" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEYBOARD_THEME_SETTER_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("err");
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
						new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
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
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//a
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//c
						new FieldItem( FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( FINAL | EXACT , 	new ClassItem(java.util.concurrent.atomic.AtomicBoolean.class)),	//e
						new FieldItem( EXACT , 	new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.util.concurrent.ListeningExecutorService" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.concurrent.Executor.class)),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//j
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//m
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , FINAL | EXACT )),	//n
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//o
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//p

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.Throwable.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , FINAL | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , FINAL | EXACT ),
										new ClassItem("" , FINAL | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , FINAL | EXACT ),
										new ClassItem(java.lang.Throwable.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.lang.Throwable.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , FINAL | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.util.concurrent.FutureCallback" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class)

								),

						//Method #8: b
						new MethodProfile
								(
										STATIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #9: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , FINAL | EXACT )

								),

						//Method #10: b
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , FINAL | EXACT ),
										new ClassItem(java.lang.Throwable.class)

								),

						//Method #11: b
						new MethodProfile
								(
										PRIVATE | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.lang.Throwable.class)

								),

						//Method #12: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #13: c
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #14: f
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem("" , FINAL | EXACT )

								),

						//Method #15: lambda$NVEsGiafZ6TGlVv9CV-ecca5gPw
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem("" , FINAL | EXACT ),
										new ClassItem("" , FINAL | EXACT )

								),

						//Method #16: lambda$UkdUqEESSSLV19FVr8z2dLqQfJ0
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem("" , FINAL | EXACT ),
										new ClassItem(java.lang.Throwable.class)

								),

						//Method #17: lambda$dycp_IJzGOpKfKjUaLXARydQtjo
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.Throwable.class)

								),

						//Method #18: lambda$fRxKcNZZ81vI3Bipxl6Gndh1eCQ
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(java.lang.Throwable.class)

								),

						//Method #19: lambda$nVE8YP8oUrSKjqK9IcGoUHl51_U
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem("" , FINAL | EXACT ),
										new ClassItem(java.lang.Throwable.class)

								),

						//Method #20: lambda$p2gEeCZPSH755LomdzExGAN3t9I
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(java.lang.Throwable.class)

								),

						//Method #21: lambda$rTO0j9u4-ofCoOIowEqh9lmWR7g
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem("com.google.common.util.concurrent.FutureCallback" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class)

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #27: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #28: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #29: c
						new MethodProfile
								(
										PUBLIC | FINAL | VARARGS | EXACT ,
										new ClassItem(void.class)

								),

						//Method #30: d
						new MethodProfile
								(
										FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #31: e
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

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.util.concurrent.ListeningExecutorService" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}






	public static ClassProfile get_INSERT_GIF_TEXT_CLASS_RPFOILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("epv");
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
//Public fields
/////////////////////////
		newProfile.setPublicFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(boolean.class)),	//c

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(boolean.class)),	//c

				});
/////////////////////////
//Public Methods
/////////////////////////
		newProfile.setPublicMethods(new MethodProfile[]
				{
						//Method #0: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #3: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #4: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #5: equals
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.Object.class)

								),

						//Method #6: getClass
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.Class.class)

								),

						//Method #7: hashCode
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #8: notify
						new MethodProfile
								(
										PUBLIC | FINAL | NATIVE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #9: notifyAll
						new MethodProfile
								(
										PUBLIC | FINAL | NATIVE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #10: toString
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #11: wait
						new MethodProfile
								(
										PUBLIC | FINAL | NATIVE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #12: wait
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(long.class)

								),

						//Method #13: wait
						new MethodProfile
								(
										PUBLIC | FINAL | NATIVE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(long.class),
										new ClassItem(int.class)

								),


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

						//Method #1: toString
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
										new ClassItem(java.lang.String.class)

								),


				});

		return newProfile;
	}


}
