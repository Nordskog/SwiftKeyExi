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

		newProfile.setFullPath("dpr");
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
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.view.inputmethod.CompletionInfo.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.KeyPress" , PUBLIC | ARRAY | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #10: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #12: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #13: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #16: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #17: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #18: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #19: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #20: b
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #21: b
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #22: c
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #23: c
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #24: i
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #25: lambda$1nzn8aCmIUafXykbse5qP1Kh4vA
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #26: lambda$4hGmifufUBYsNV1MBir6cDSDS5w
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #27: lambda$CDt006G0rSeGpsJeZNK-h84svlw
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #28: lambda$CxQo31-XnvARn824pIafIMtdWp8
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #29: lambda$M5Rzc6C7B9q3Qs_cdkjECGWD5Xk
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #30: lambda$N-KSVEkk87RlVNm2grPzjgCCxiA
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #31: lambda$TiVLrjbat2qSDTVWq1IXg0uqOMM
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #32: lambda$W9QQR3q2eP5Q040YtDG7z5yDOUI
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #33: lambda$WY8kIPXK13jVDywLIOmZz5tM9BI
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #34: lambda$Y4OquE4-GNmeIZWttm3NJANXUmU
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #35: lambda$ZI9O_83NzRaGbVTe3EuOVmlymTo
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.view.inputmethod.CompletionInfo.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #36: lambda$_keyrDidnrhRMDE2x9FFJGItjrc
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #37: lambda$_lqkGXwMOBR3GKpUjTtq5ZKlBhQ
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #38: lambda$dctjoxNYFC6zQQqIZgIDOpqCw2A
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #39: lambda$ec_8eDaJTZpLd2900FmMTLlQwQY
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #40: lambda$l0sXu_xd57ZxxZ7DmsDE7AziMZg
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #41: lambda$o6te2eAdIHrf47VHpb8N33X6oe0
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #42: lambda$oJFrvs6bltM7zbHa5vyEyK3EV4A
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #43: lambda$sJ_bEnesOnUkqVtS3vtBghC-xug
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #44: lambda$uPUd-iOeYnlBYxxqcWTv0P_sPwA
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

						//Method #45: lambda$ujRf7_kqzVsTSDDO-VbELfBV8_Y
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

						//Method #46: lambda$wwBDyIxwBqXMoWYh6LRs82zry9Y
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #47: lambda$xxLXopPxcXSyKOAT1vJbVIrDeog
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

						//Method #48: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #49: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #50: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.Point" , PUBLIC | EXACT ),
										new ClassItem(long.class)

								),

						//Method #51: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class)

								),

						//Method #52: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class)

								),

						//Method #53: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #54: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.view.inputmethod.CompletionInfo.class)

								),

						//Method #55: a
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

						//Method #56: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #57: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #58: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #59: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #60: a
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

						//Method #61: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #62: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #63: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #64: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #65: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #66: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #67: a
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

						//Method #68: a
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

						//Method #69: a
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

						//Method #70: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #71: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #72: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(boolean.class),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #73: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #74: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #75: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #76: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #77: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #78: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #79: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #80: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #81: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #82: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #83: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #84: g
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #85: h
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
