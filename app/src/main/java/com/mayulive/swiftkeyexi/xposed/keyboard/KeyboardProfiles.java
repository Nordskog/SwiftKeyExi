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

		newProfile.setFullPath("za3");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem(android.widget.FrameLayout.class));
/////////////////////////
//Interfaces
/////////////////////////
		newProfile.setInterfaces(new ClassItem[]
				{
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem(android.view.ViewTreeObserver.OnGlobalLayoutListener.class),
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//h
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//i
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//j
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//k
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//l
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//m
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Function" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//n
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//o
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//p
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//q
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//r
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(boolean.class)),	//s
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | ABSTRACT | EXACT )),	//t
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.touchtype.keyboard.toolbar.ToolbarButton" , PUBLIC | EXACT )),	//u

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: setUpRedDot
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.widget.ImageView.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | BRIDGE | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.Object.class),
										new ClassItem(int.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

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

						//Method #8: onAttachedToWindow
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #9: onDetachedFromWindow
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #10: onGlobalLayout
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #11: r
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #12: setVisibility
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #13: t
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

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Function" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEY_HEIGHT_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("cv5");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//i
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//j
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int.class)),	//k

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

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #2: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #3: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #4: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #5: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #6: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #7: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #8: onSharedPreferenceChanged
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

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),


				});

		return newProfile;
	}



	public static ClassProfile get_FULL_KEYBOARD_SERVICE_DELEGATE_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("oc1");
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
						new ClassItem("com.touchtype_fluency.service.receiver.SDCardListener" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("com.touchtype_fluency.service.HandwritingModelLoadStateListener" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("com.touchtype_fluency.service.LanguageLoadStateListener" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//A
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//A0
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//B
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//B0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//C
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//C0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//D
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//D0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//E
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//E0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//F
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | EXACT )),	//F0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | ABSTRACT | EXACT )),	//G
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//G0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//H
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//H0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//I
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//I0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )),	//J
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//J0
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//K
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//K0
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//L
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//L0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.languagepacks.LanguagePackListener" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//M
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//M0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//N
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//N0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//O
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//O0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.concurrent.ExecutorService.class)),	//P
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//P0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//Q
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(java.util.Locale.class)),	//Q0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//R
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//R0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//S
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//S0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//T
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//T0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//U
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//U0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//V
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.touchtype_fluency.service.handwriting.HandwritingModelLoadState" , PUBLIC | FINAL | ENUM | EXACT )),	//V0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//W
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//W0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//X
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//X0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//Y
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//Y0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//Z
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//Z0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//a0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//b0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//b1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.ClipboardManager.class)),	//c0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//c1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//d0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//d1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//e0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.FullLayoutProvider" , PUBLIC | EXACT )),	//e1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.Collection.class)),	//f0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//f1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.res.Resources.class)),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.Collection.class)),	//g0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT )),	//h
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//h0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//h1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//i
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//i0
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//i1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//j
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//j1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//k
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//k0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//k1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//l
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//l0
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(java.lang.Runnable.class)),	//l1
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//m
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//m0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//n
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//n0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//o
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//o0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//p
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//p0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//q
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//q0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//r
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//r0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//s
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//s0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//t
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int.class)),	//t0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//u
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.google.common.base.Function" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//u0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//v
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.touchtype.keyboard.service.LockScreenWatcher" , PUBLIC | EXACT )),	//v0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//w
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.receiver.SDCardReceiverWrapper" , PUBLIC | EXACT )),	//w0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//x
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.touchtype_fluency.service.LanguageLoadStateCombiner" , PUBLIC | EXACT )),	//x0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//y
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//y0
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//z
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//z0

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
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.os.IBinder.class),

										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.util.concurrent.ScheduledExecutorService.class),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.io.InputStream.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Iterable.class),

										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.concurrent.ScheduledExecutorService.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #21: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class)

								),

						//Method #22: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Float.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #23: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT )

								),

						//Method #24: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #25: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #26: c
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.os.IBinder.class),

										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT )

								),

						//Method #27: c
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #28: c
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #29: c
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #30: d
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #31: d
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #32: e
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.util.DisplayMetrics.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #33: e
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Float.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #34: f
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #35: f
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #36: g
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Float.class),

										new ClassItem(android.content.res.Resources.class)

								),

						//Method #37: g
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.util.Locale.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #38: h
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #39: i
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #40: j
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.transition.Transition.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #41: k
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #42: l
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #43: m
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class)

								),

						//Method #44: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #45: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #46: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #47: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #48: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.inputmethodservice.InputMethodService.Insets.class)

								),

						//Method #49: a
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View.class)

								),

						//Method #50: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.Window.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #51: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.inputmethod.CursorAnchorInfo.class)

								),

						//Method #52: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class)

								),

						//Method #53: a
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.widget.FrameLayout.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class)

								),

						//Method #54: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #55: a
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #56: a
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #57: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #58: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #59: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class),
										new ClassItem(java.util.Locale.class)

								),

						//Method #60: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #61: a
						new MethodProfile
								(
										PUBLIC | FINAL | VARARGS | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.View[].class)

								),

						//Method #62: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.inputmethod.CompletionInfo[].class)

								),

						//Method #63: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #64: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #65: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class)

								),

						//Method #66: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #67: b
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #68: c
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #69: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #70: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #71: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(boolean.class)

								),

						//Method #72: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #73: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #74: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #75: h
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #76: i
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #77: j
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #78: k
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #79: l
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #80: m
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #81: n
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(android.view.View.class)

								),

						//Method #82: o
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #83: onConfigurationChanged
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.res.Configuration.class)

								),

						//Method #84: onHandwritingModelLoadStateChanged
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.handwriting.HandwritingModelLoadState" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #85: onKeyDown
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class)

								),

						//Method #86: onKeyUp
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class)

								),

						//Method #87: onLanguageLoadStateChanged
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.LanguageLoadState" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #88: onMediaMounted
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #89: onMediaUnmounted
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #90: onTrimMemory
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #91: p
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT )

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

										new ClassItem("com.touchtype.KeyboardService" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.content.res.Resources.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.util.concurrent.ExecutorService.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.ClipboardManager.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.Collection.class),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.Locale.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.util.Collection.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.receiver.SDCardReceiverWrapper" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.keyboard.service.LockScreenWatcher" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.FullLayoutProvider" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	protected static ClassProfile _get_LOCATION_MANAGER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("f13");
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
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Nested Classes
/////////////////////////
		newProfile.setNestedClasses(new ClassItem[]
				{
						new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.Context.class)),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//c
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.net.wifi.WifiManager.class)),	//d
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//h
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//i
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//k
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.IntentFilter.class)),	//l
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.content.BroadcastReceiver.class)),	//m
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.concurrent.ExecutorService.class)),	//n
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//o
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(java.util.UUID.class)),	//p
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(android.location.Location.class)),	//q
						new FieldItem( PUBLIC | STATIC | FINAL | EXACT , 	new ClassItem("com.google.common.collect.ImmutableSet" , PUBLIC | ABSTRACT | EXACT )),	//r

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
										new ClassItem(int.class),

										new ClassItem(android.net.wifi.ScanResult.class),
										new ClassItem(android.net.wifi.ScanResult.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(int.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.swiftkey.avro.telemetry.sk.android.LocationGpsResultStatus" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.UUID.class)

								),

						//Method #6: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.util.List.class)

								),

						//Method #7: b
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #8: c
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #9: d
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
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
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.net.wifi.WifiManager.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
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

		newProfile.setFullPath("Dsa");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//b
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int.class)),	//c
						new FieldItem( PUBLIC | STATIC | EXACT , 	new ClassItem("" , PUBLIC | THIS | EXACT )),	//a

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
										new ClassItem(boolean.class),

										new ClassItem(int.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #3: b
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

		newProfile.setFullPath("Zha");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//b
						new FieldItem( PUBLIC | STATIC | FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//a

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: b
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
										PUBLIC | EXACT ,
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class),
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

		newProfile.setFullPath("hs2");
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

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//A
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//B
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//C
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(java.util.Set.class)),	//D
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(java.util.Set.class)),	//E
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(boolean.class)),	//F
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(boolean.class)),	//G
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//h
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//i
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//k
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype.report.TouchTypeStats" , PUBLIC | EXACT )),	//l
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//m
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//n
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//o
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//p
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//q
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//r
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//s
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//t
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//u
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//v
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//w
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.candidates.ForgetCandidateVisitor" , PUBLIC | EXACT )),	//x
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | EXACT )),	//y
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//z

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
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.net.Uri.class),
										new ClassItem(android.net.Uri.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.Punctuator" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.KeyPress" , PUBLIC | ARRAY | EXACT )

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.util.List.class)

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class)

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.CompletionInfo.class)

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.Tokenizer" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #27: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #28: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.FluencyCandidate" , PUBLIC | EXACT )

								),

						//Method #29: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #30: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #31: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #32: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #33: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #34: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #35: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #36: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.util.EnumSet.class)

								),

						//Method #37: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #38: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #39: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #40: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #41: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #42: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #43: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #44: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #45: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #46: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #47: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #48: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #49: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #50: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #51: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #52: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class)

								),

						//Method #53: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #54: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #55: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #56: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #57: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #58: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #59: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #60: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #61: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #62: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #63: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #64: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #65: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #66: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #67: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #68: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #69: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #70: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #71: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #72: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #73: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #74: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #75: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #76: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #77: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #78: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #79: h
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #80: i
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #81: j
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #82: k
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #83: l
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #84: m
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #85: n
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #86: o
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #87: onDestroy
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #88: onLanguageLoadStateChanged
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.LanguageLoadState" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #89: p
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #90: q
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #91: r
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #92: s
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
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.report.TouchTypeStats" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.ForgetCandidateVisitor" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	public static ClassProfile get_KEYBOARD_THEME_SETTER_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("tb3");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//h
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//i
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.util.concurrent.ListeningExecutorService" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//j
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.concurrent.Executor.class)),	//k
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.Set.class)),	//l
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//m
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//n
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//o
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//p
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.concurrent.atomic.AtomicBoolean.class)),	//q
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//r
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )),	//s
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int.class)),	//t

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
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | THIS | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.util.concurrent.FutureCallback" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class)

								),

						//Method #3: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | THIS | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem("com.google.common.util.concurrent.FutureCallback" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.lang.Throwable.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.Throwable.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.Object.class),
										new ClassItem(int.class)

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | VARARGS | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String[].class)

								),

						//Method #16: b
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.lang.Throwable.class)

								),

						//Method #17: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #18: b
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.Throwable.class)

								),

						//Method #19: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #20: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #21: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #22: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("com.google.common.util.concurrent.ListenableFuture" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #23: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #24: e
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

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.util.concurrent.ListeningExecutorService" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),


				});

		return newProfile;
	}






	public static ClassProfile get_INSERT_GIF_TEXT_CLASS_RPFOILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("pva");
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
//Public fields
/////////////////////////
		newProfile.setPublicFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(boolean.class)),	//c
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//a

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.lang.String.class)),	//b
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
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #2: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #3: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #4: equals
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.Object.class)

								),

						//Method #5: getClass
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.Class.class)

								),

						//Method #6: hashCode
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #7: notify
						new MethodProfile
								(
										PUBLIC | FINAL | NATIVE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #8: notifyAll
						new MethodProfile
								(
										PUBLIC | FINAL | NATIVE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #9: toString
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #10: wait
						new MethodProfile
								(
										PUBLIC | FINAL | NATIVE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #11: wait
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(long.class)

								),

						//Method #12: wait
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
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: toString
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
										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class)

								),


				});

		return newProfile;
	}


}
