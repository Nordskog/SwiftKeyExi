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

		newProfile.setFullPath("Ena");
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

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.List.class)),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//c
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
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: equals
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.Object.class)

								),

						//Method #2: hashCode
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

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.util.List.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),


				});

		return newProfile;
	}


	protected static ClassProfile get_CANDIDATES_VIEW_FACTORY_CLASS_PROFILE_NEW()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("qh2");
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
										new ClassItem(float.class),

										new ClassItem(float.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(float.class),

										new ClassItem(android.graphics.Rect.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(float.class),
										new ClassItem(float.class),
										new ClassItem(android.graphics.Paint.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(int.class),
										new ClassItem(float.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(float.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(android.content.res.Resources.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(java.lang.Iterable.class)

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | STATIC | FINAL | EXACT ,
										new ClassItem(int.class),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.res.Resources.class),
										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(boolean.class)

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.animation.Animator.class),

										new ClassItem(android.view.View.class),
										new ClassItem(float.class),
										new ClassItem(java.lang.Runnable.class)

								),

						//Method #13: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.Bitmap.class),

										new ClassItem(android.graphics.Bitmap.class),
										new ClassItem(int.class)

								),

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.Bitmap.class),

										new ClassItem(android.graphics.Bitmap.class),
										new ClassItem(android.net.Uri.class),
										new ClassItem(android.content.Context.class)

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.PointF.class),

										new ClassItem(android.graphics.PointF.class),
										new ClassItem(android.graphics.Matrix.class)

								),

						//Method #16: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.Rect.class),

										new ClassItem(android.graphics.Rect.class),
										new ClassItem(android.graphics.Rect.class)

								),

						//Method #17: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.Rect.class),

										new ClassItem(android.graphics.RectF.class)

								),

						//Method #18: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.Rect.class),

										new ClassItem(android.graphics.drawable.Drawable.class)

								),

						//Method #19: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.Rect.class),

										new ClassItem(android.graphics.drawable.Drawable.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.graphics.RectF.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.graphics.PointF.class)

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.RectF.class),

										new ClassItem(android.graphics.RectF.class),
										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.text.Spannable.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("com.touchtype_fluency.service.languagepacks.AndroidLanguagePackManager" , PUBLIC | EXACT )

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.text.SpannableString.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.text.SpannableString.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.ConsentId" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #27: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT )

								),

						//Method #28: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #29: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT )

								),

						//Method #30: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #31: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.jobs.FluencyDebugLogSaver" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #32: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #33: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.view.View.class)

								),

						//Method #34: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.view.View.class),

										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #35: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.view.animation.TranslateAnimation.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #36: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.EmojiLocation" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.TextOrigin" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #37: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.view.View.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #38: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | EXACT ),

										new ClassItem(android.view.View.class),
										new ClassItem(java.lang.CharSequence.class),
										new ClassItem(int.class)

								),

						//Method #39: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Function" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.ModelTrackingFrame" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.ModelTrackingFrame" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #40: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common.collect.ImmutableCollection" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #41: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.CapHint" , PUBLIC | FINAL | ENUM | EXACT ),

										new ClassItem("com.touchtype_fluency.ResultsFilter.CapitalizationHint" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #42: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.CapHint" , PUBLIC | FINAL | ENUM | EXACT ),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #43: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.CapHint" , PUBLIC | FINAL | ENUM | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #44: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem(java.util.Map.class),
										new ClassItem(java.lang.Object.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #45: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),

										new ClassItem(java.util.Set.class)

								),

						//Method #46: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class)

								),

						//Method #47: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #48: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | STATIC | FINAL | ENUM | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #49: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #50: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #51: a
						new MethodProfile
								(
										PUBLIC | STATIC | VARARGS | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(android.content.res.Resources.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(java.lang.Object[].class)

								),

						//Method #52: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem("com.google.gson.JsonElement" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #53: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem("com.google.gson.JsonObject" , PUBLIC | FINAL | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #54: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #55: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem("com.google.common.collect.ImmutableList" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #56: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #57: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #58: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(long.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #59: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #60: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("org.json.JSONObject" , PUBLIC | EXACT ),

										new ClassItem(java.io.File.class)

								),

						//Method #61: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.util.Locale.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.util.Set.class)

								),

						//Method #62: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("com.google.common.base.Function" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #63: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #64: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.net.Uri.class),
										new ClassItem(android.content.Context.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #65: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.graphics.RectF.class),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #66: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #67: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(boolean.class)

								),

						//Method #68: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.content.DialogInterface.class),
										new ClassItem(int.class)

								),

						//Method #69: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class),
										new ClassItem(int.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(android.view.View.OnClickListener.class),
										new ClassItem(boolean.class)

								),

						//Method #70: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class),
										new ClassItem(android.text.Spannable.class)

								),

						//Method #71: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.widget.LinearLayout.class),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #72: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.widget.LinearLayout.class),
										new ClassItem(android.view.View.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #73: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(int.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(int.class),
										new ClassItem(android.view.View.class)

								),

						//Method #74: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #75: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("androidx.preference.CheckBoxPreference" , PUBLIC | EXACT ),
										new ClassItem("androidx.preference.CheckBoxPreference" , PUBLIC | EXACT )

								),

						//Method #76: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("androidx.preference.CheckBoxPreference" , PUBLIC | EXACT ),
										new ClassItem("androidx.preference.CheckBoxPreference" , PUBLIC | EXACT ),
										new ClassItem("androidx.preference.CheckBoxPreference" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("com.touchtype.materialsettings.SwiftKeyPreferenceFragment" , PUBLIC | EXACT )

								),

						//Method #77: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.content.DialogInterface.class),
										new ClassItem(int.class)

								),

						//Method #78: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.io.Closeable.class)

								),

						//Method #79: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.io.File.class),
										new ClassItem(java.lang.String.class),
										new ClassItem(int.class)

								),

						//Method #80: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.swiftkey.avro.telemetry.sk.android.SettingStateEventOrigin" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #81: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class)

								),

						//Method #82: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.AndroidLanguagePackManager" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #83: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.languagepacks.AndroidLanguagePackManager" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class)

								),

						//Method #84: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class)

								),

						//Method #85: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #86: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #87: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #88: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #89: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #90: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #91: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #92: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #93: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class)

								),

						//Method #94: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #95: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.languagepacks.AndroidLanguagePackManager" , PUBLIC | EXACT )

								),

						//Method #96: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #97: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #98: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.graphics.RectF.class)

								),

						//Method #99: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #100: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #101: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("androidx.preference.CheckBoxPreference" , PUBLIC | EXACT ),
										new ClassItem("androidx.preference.CheckBoxPreference" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.materialsettings.SwiftKeyPreferenceFragment" , PUBLIC | EXACT ),
										new ClassItem("androidx.preference.CheckBoxPreference" , PUBLIC | EXACT ),
										new ClassItem("androidx.preference.Preference" , PUBLIC | EXACT )

								),

						//Method #102: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("androidx.preference.CheckBoxPreference" , PUBLIC | EXACT ),
										new ClassItem("androidx.preference.CheckBoxPreference" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.materialsettings.SwiftKeyPreferenceFragment" , PUBLIC | EXACT ),
										new ClassItem("androidx.preference.Preference" , PUBLIC | EXACT )

								),

						//Method #103: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int[].class)

								),

						//Method #104: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int[].class),
										new ClassItem(int.class)

								),

						//Method #105: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String[].class),
										new ClassItem(java.lang.String[].class)

								),

						//Method #106: a
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.String[].class),

										new ClassItem(java.util.List.class)

								),

						//Method #107: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(int.class),
										new ClassItem(float.class)

								),

						//Method #108: b
						new MethodProfile
								(
										PUBLIC | STATIC | FINAL | EXACT ,
										new ClassItem(int.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #109: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #110: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.text.Spannable.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #111: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common.collect.ImmutableCollection" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #112: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("com.touchtype.keyboard.view.ModelTrackingFrame" , PUBLIC | EXACT ),

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.google.common.base.Function" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.ModelTrackingFrame" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype.keyboard.view.ModelTrackingFrame" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #113: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.touchtype_fluency.Point" , PUBLIC | EXACT ),

										new ClassItem(android.graphics.PointF.class),
										new ClassItem(android.graphics.Matrix.class)

								),

						//Method #114: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class)

								),

						//Method #115: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #116: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(java.lang.String.class),

										new ClassItem(android.content.res.Resources.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #117: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class)

								),

						//Method #118: b
						new MethodProfile
								(
										PUBLIC | STATIC | FINAL | EXACT ,
										new ClassItem(java.util.List.class),

										new ClassItem(java.util.List.class)

								),

						//Method #119: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem(android.graphics.RectF.class),
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #120: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class),
										new ClassItem(android.text.Spannable.class)

								),

						//Method #121: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class)

								),

						//Method #122: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #123: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #124: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class)

								),

						//Method #125: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #126: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.languagepacks.AndroidLanguagePackManager" , PUBLIC | EXACT )

								),

						//Method #127: b
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #128: c
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(int.class),
										new ClassItem(float.class)

								),

						//Method #129: c
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #130: c
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #131: c
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #132: c
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class)

								),

						//Method #133: c
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #134: c
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class)

								),

						//Method #135: c
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #136: d
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #137: d
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common.collect.ImmutableCollection" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #138: d
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class)

								),

						//Method #139: d
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #140: e
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #141: e
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #142: e
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #143: f
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class)

								),

						//Method #144: f
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #145: f
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #146: g
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(android.content.Context.class)

								),

						//Method #147: g
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(java.lang.Boolean.class)

								),

						//Method #148: g
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #149: h
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common.collect.ImmutableCollection" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #150: h
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #151: i
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common.collect.ImmutableCollection" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #152: i
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #153: j
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common.collect.ImmutableCollection" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #154: j
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #155: k
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common.collect.ImmutableCollection" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #156: k
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #157: l
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.google.common.collect.ImmutableCollection" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #158: l
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #159: m
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #160: n
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

								),

						//Method #161: o
						new MethodProfile
								(
										PUBLIC | STATIC | SYNTHETIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.View.class)

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

		newProfile.setFullPath("yc2");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//i
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//j
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//k
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.text.TextPaint.class)),	//l
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.graphics.Rect.class)),	//m
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(boolean.class)),	//n
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int.class)),	//o
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )),	//p
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//q

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

						//Method #1: d
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class)

								),

						//Method #2: getContentDrawable
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class)

								),

						//Method #3: onAttachedToWindow
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #4: onDetachedFromWindow
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class)

								),

						//Method #5: onMeasure
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #6: setCandidate
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: setMeasuredTextSize
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(float.class)

								),

						//Method #8: setShortcutText
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #9: setStyleId
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

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

										new ClassItem(android.content.Context.class),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
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

		newProfile.setFullPath("ml2");
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
										new ClassItem(void.class)

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

										new ClassItem(java.util.List.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

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

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #9: a
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

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(int.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.CompletionInfo.class)

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(android.view.inputmethod.EditorInfo.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #13: a
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

						//Method #14: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #15: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.Tokenizer" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

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
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #20: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.util.EnumSet.class)

								),

						//Method #21: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #22: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #23: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem(java.lang.String.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(boolean.class)

								),

						//Method #24: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #25: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #26: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #27: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #28: a
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(boolean.class),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

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
										new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #37: b
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #38: c
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #39: c
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #40: c
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #41: d
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #42: d
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
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

										new ClassItem("" , PUBLIC | EXACT )

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

										new ClassItem("" , PUBLIC | EXACT )

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
										new ClassItem(void.class)

								),

						//Method #51: j
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #52: k
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #53: l
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

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
										new ClassItem(boolean.class)

								),

						//Method #57: onDestroy
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #58: onLanguageLoadStateChanged
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.LanguageLoadState" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #59: p
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(void.class)

								),

						//Method #60: q
						new MethodProfile
								(
										PUBLIC | ABSTRACT | EXACT ,
										new ClassItem(boolean.class)

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

		newProfile.setFullPath("Sta");
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
						new FieldItem( PUBLIC | STATIC | FINAL | SYNTHETIC | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | ARRAY | EXACT )),	//M
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
								(		PUBLIC | EXACT ,

										new ClassItem(java.lang.String.class),
										new ClassItem(int.class)

								),


				});

		return newProfile;
	}



	protected static ClassProfile get_RESULT_TYPE_ENUM_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("Kna");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(int.class)),	//k
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//a
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//b
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//c
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//d
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//e
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//f
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//g
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//h
						new FieldItem( PUBLIC | STATIC | FINAL | ENUM | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | EXACT )),	//i
						new FieldItem( PUBLIC | STATIC | FINAL | SYNTHETIC | EXACT , 	new ClassItem("" , PUBLIC | FINAL | ENUM | THIS | ARRAY | EXACT )),	//j

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
								(		PUBLIC | EXACT ,

										new ClassItem(java.lang.String.class),
										new ClassItem(int.class)

								),

						//Constructor #1
						new ConstructorProfile
								(		PUBLIC | EXACT ,

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

		newProfile.setFullPath("np2");
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

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//c
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype.report.TouchTypeStats" , PUBLIC | EXACT )),	//d
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//g
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | STATIC | EXACT )),	//h

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
										new ClassItem(java.lang.String.class),

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(int.class)

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem(java.lang.String.class)

								),

						//Method #10: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #11: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #12: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
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

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype.report.TouchTypeStats" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | STATIC | EXACT )

								),


				});

		return newProfile;
	}

	protected static ClassProfile get_CANDIDATE_UPDATER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("Pna");
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

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.concurrent.Executor.class)),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT )),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//c
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//d
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.FluencyProfilerWrapper" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//e
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//f
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//g
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(java.util.concurrent.Future.class)),	//h
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//i
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(boolean.class)),	//j
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Predicate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//k
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//l
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//m

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
										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem(int.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | SYNTHETIC | EXACT ,
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
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.Predictor" , PUBLIC | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(boolean.class),
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

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyProfilerWrapper" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype_fluency.service.FluencyServiceProxy" , PUBLIC | EXACT ),
										new ClassItem(java.util.concurrent.Executor.class),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Predicate" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),


				});

		return newProfile;
	}


	public static ClassProfile get_ELIPSIZE_CHECKER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("bx2");
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
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(float.class)),	//h
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(android.graphics.RectF.class)),	//i
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int[].class)),	//j
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(java.lang.String.class)),	//k
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//l
						new FieldItem( PUBLIC | STATIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.candidates.Candidate.Visitor" , PUBLIC | STATIC | ABSTRACT | EXACT )),	//m
						new FieldItem( PUBLIC | STATIC | FINAL | EXACT , 	new ClassItem("com.touchtype_fluency.service.candidates.Candidate.Visitor" , PUBLIC | STATIC | ABSTRACT | EXACT )),	//n

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
										new ClassItem("" , PUBLIC | THIS | EXACT ),

										new ClassItem(float.class),
										new ClassItem(float.class)

								),

						//Method #2: i
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("" , PUBLIC | THIS | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | BRIDGE | SYNTHETIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | EXACT ),

										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("" , PUBLIC | ABSTRACT | EXACT ),

										new ClassItem("" , PUBLIC | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT ),
										new ClassItem("" , PUBLIC | FINAL | ENUM | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype_fluency.service.candidates.Candidate" , PUBLIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int[].class)

								),

						//Method #9: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.Object.class)

								),

						//Method #10: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #11: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

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
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #14: g
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.graphics.RectF.class)

								),

						//Method #15: h
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #16: hashCode
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

										new ClassItem(android.graphics.RectF.class),
										new ClassItem(float.class),
										new ClassItem(int[].class)

								),


				});

		return newProfile;
	}


}


