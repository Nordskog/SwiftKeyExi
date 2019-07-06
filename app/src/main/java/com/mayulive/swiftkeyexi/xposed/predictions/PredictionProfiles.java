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

		newProfile.setFullPath("eci");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//c
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//d

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
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: equals
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.Object.class)

								),

						//Method #2: hashCode
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

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.util.List.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}

	protected static ClassProfile get_CANDIDATES_VIEW_FACTORY_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("foi");
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
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common.base.Function" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.widget.LinearLayout.class),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.widget.LinearLayout.class),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.widget.LinearLayout.class),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PRIVATE | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #8: b
						new MethodProfile
								(
										PRIVATE | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.widget.LinearLayout.class),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #9: lambda$-g7h_tWCVRFf1sKx6nNZARuvOfg
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #10: lambda$q_L2ZfdCSC5T0E_5jki5gtfa3tw
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
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

				});

		return newProfile;
	}


	protected static ClassProfile get_CANDIDATE_VIEW_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("ehp");
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
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Nested Classes
/////////////////////////
		newProfile.setNestedClasses(new ClassItem[]
				{
						new ClassItem("" , PUBLIC | STATIC | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//e
						new FieldItem( PROTECTED | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.text.TextPaint.class)),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(android.graphics.Rect.class)),	//i
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(boolean.class)),	//j
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//k
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )),	//l
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//m

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class)

								),

						//Method #1: getContentDrawable
						new MethodProfile
								(
										PROTECTED | FINAL | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class)

								),

						//Method #2: onAttachedToWindow
						new MethodProfile
								(
										PROTECTED | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #3: onDetachedFromWindow
						new MethodProfile
								(
										PROTECTED | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #4: onMeasure
						new MethodProfile
								(
										PROTECTED | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #5: setCandidate
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #6: setMeasuredTextSize
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(float.class)

								),

						//Method #7: setShortcutText
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #8: setStyleId
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #9: t_
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
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(float.class)

								),


				});

		return newProfile;
	}

	protected static ClassProfile get_BU_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("eho");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC | INTERFACE | ABSTRACT );

		newProfile.setTypeParamCount(0);
/////////////////////////
//Interfaces
/////////////////////////
		newProfile.setInterfaces(new ClassItem[]
				{
						new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
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
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

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

										new ClassItem("com.touchtype_fluency.Punctuator" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.CompletionInfo.class)

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.Tokenizer" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.LanguageLoadState" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.FluencyCandidate" , PUBLIC | EXACT )

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT )

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.util.EnumSet.class)

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #27: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

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

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #31: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class)

								),

						//Method #32: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(android.view.KeyEvent.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

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

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #35: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #36: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #37: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #38: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #39: c
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #40: c
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #41: c
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #42: d
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #43: d
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #44: d
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #45: e
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #46: e
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #47: f
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #48: f
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #49: g
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #50: h
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #51: i
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #52: j
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #53: k
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #54: l
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #55: m
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #56: n
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #57: o
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #58: p
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



	public static ClassProfile get_CANDIDATE_SOURCE_TYPE_ENUN_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("elt");
		newProfile.setKnownPath("");

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
						"CANDIDATE_BAR",
						"SPACE",
						"SPACE_NO_CORRECTION",
						"PUNCTUATION",
						"PUNCTUATION_NO_CORRECTION",
						"EXPANDED_CANDIDATES_WINDOW",
						"HANDWRITING_PROVISIONAL",
						"HANDWRITING_AFTER_HANDWRITING",
						"TAP_AFTER_HANDWRITING",
						"CLIPBOARD_AFTER_HANDWRITING",
						"EMOJI_AFTER_HANDWRITING",
						"FLOW_AFTER_HANDWRITING",
						"RICH_TEXT_CONTENT_AFTER_HANDWRITING",
						"FLOW",
						"FLOW_PROVISIONAL",
						"FLOW_FAILED",
						"TAP_AFTER_FLOW",
						"EMOJI_AFTER_FLOW",
						"SHIFT_AFTER_FLOW",
						"FLOW_AFTER_FLOW",
						"RICH_TEXT_CONTENT_AFTER_FLOW",
						"CLIPBOARD_AFTER_FLOW",
						"HANDWRITING_AFTER_FLOW",
						"AUTO_COMMIT_STABILISED_TOKEN",
						"COMMIT_UNCOMMITTED_TEXT",
						"FLOW_AUTO_COMMIT",
						"SINGLE_LETTER_BEFORE_FLOW",
						"ENTER",
						"TAB",
						"PUNCTUATION_NOT_COMMITTING",
						"CURSOR_MOVE",
						"CURSOR_MOVE_FROM_BEGINNING",
						"SWITCH_TO_BUFFERED_LAYOUT",
						"SWITCH_TO_HANDWRITING_LAYOUT",
						"SWITCH_TO_JAPANESE_LAYOUT",
						"SHORTCUT",
						"BUFFER_FLUSH",
						"UNKNOWN",

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//A
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//B
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//C
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//D
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//E
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//F
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//G
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//H
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//I
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//J
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//K
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//L
						new FieldItem( PRIVATE | STATIC | FINAL | SYNTHETIC | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | ARRAY | EXACT )),	//M
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//a
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//b
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//c
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//d
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//e
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//f
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//g
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//h
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//i
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//j
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//k
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//l
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//m
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//n
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//o
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//p
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//q
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//r
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//s
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//t
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//u
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//v
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//w
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//x
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//y
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//z

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: valueOf
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #1: values
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | ARRAY | EXACT )

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


				});

		return newProfile;
	}



	protected static ClassProfile get_RESULT_TYPE_ENUM_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("eco");
		newProfile.setKnownPath("");

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
						new FieldItem( FINAL | EXACT , 	new ClassItem(int.class)),	//j
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//a
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//b
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//c
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//d
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//e
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//f
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//g
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//h
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//i
						new FieldItem( PRIVATE | STATIC | FINAL | SYNTHETIC | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | ARRAY | EXACT )),	//k

				});
/////////////////////////
//Declared Methods
/////////////////////////
		newProfile.setDeclaredMethods(new MethodProfile[]
				{
						//Method #0: valueOf
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #1: values
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | ARRAY | EXACT )

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

		newProfile.setFullPath("esl");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.report.TouchTypeStats" , PUBLIC | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//g

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
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #4: b
						new MethodProfile
								(
										PRIVATE | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #5: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #12: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

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
										new ClassItem("com.touchtype.report.TouchTypeStats" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}

	protected static ClassProfile get_CANDIDATE_UPDATER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("dqt");
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
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(java.util.concurrent.Executor.class)),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyProfilerWrapper" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.util.concurrent.Future.class)),	//i
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("" , PUBLIC | FINAL | EXACT )),	//j
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(boolean.class)),	//k
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Predicate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//l
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//m

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

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem(java.util.Set.class),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(java.util.Set.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.Predictor" , PUBLIC | EXACT )

								),

						//Method #1: lambda$WEZ0DtdyzqCPo6ZAvRGVo25MjK0
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | FINAL | THIS | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem(java.util.Set.class),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(java.util.Set.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.Predictor" , PUBLIC | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										FINAL | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | STATIC | EXACT ),
										new ClassItem(java.util.Set.class),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.layouts.LayoutData.Layout" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(java.util.Set.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class),
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

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyProfilerWrapper" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Predicate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}


	public static ClassProfile get_ELIPSIZE_CHECKER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("dzt");
		newProfile.setKnownPath("");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem("" , PUBLIC | EXACT ));
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(float.class)),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.graphics.RectF.class)),	//c
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(java.lang.String.class)),	//d
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int[].class)),	//j
						new FieldItem( STATIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.candidates.Candidate.Visitor" , PUBLIC | STATIC | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | STATIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.candidates.Candidate.Visitor" , PUBLIC | STATIC | ABSTRACT | EXACT )),	//f

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
										new ClassItem(android.graphics.RectF.class),

										new ClassItem(float.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | THIS | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | THIS | EXACT ),

										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | FINAL | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: b
						new MethodProfile
								(
										PUBLIC | FINAL | BRIDGE | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #8: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #9: c
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #10: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #11: e
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(int[].class)

								),

						//Method #12: equals
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.Object.class)

								),

						//Method #13: f
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #14: g
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(java.lang.Object.class)

								),

						//Method #15: h
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(android.graphics.RectF.class)

								),

						//Method #16: hashCode
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #17: i
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
								(		EXACT ,

										new ClassItem(float.class)

								),

						//Constructor #1
						new ConstructorProfile
								(		EXACT ,

										new ClassItem(android.graphics.RectF.class),
										new ClassItem(float.class)

								),

						//Constructor #2
						new ConstructorProfile
								(		PRIVATE | EXACT ,

										new ClassItem(android.graphics.RectF.class),
										new ClassItem(float.class),
										new ClassItem(int[].class)

								),


				});

		return newProfile;
	}


}


