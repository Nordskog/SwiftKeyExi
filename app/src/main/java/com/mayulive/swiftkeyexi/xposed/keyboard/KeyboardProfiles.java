package com.mayulive.swiftkeyexi.xposed.keyboard;

import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ClassProfile;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;
import com.mayulive.xposed.classhunter.profiles.FieldItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import static com.mayulive.xposed.classhunter.Modifiers.*;


public class KeyboardProfiles
{
	public static ClassProfile get_BREADCRUMB_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.telemetry.b");
		newProfile.setKnownPath("com.touchtype.telemetry");

		newProfile.setMinDepth(0);
		newProfile.setMaxDepth(0);
		newProfile.setModifiers(PUBLIC );

		newProfile.setTypeParamCount(0);
		newProfile.setSuperClass(	new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ));
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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )),	//b
						new FieldItem( PUBLIC | STATIC | FINAL | EXACT , 	new ClassItem(android.os.Parcelable.Creator.class)),	//CREATOR

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
										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT ),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | STATIC | FINAL | ENUM | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("com.touchtype.telemetry" , PUBLIC | EXACT )

								),

						//Method #3: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.google.common.collect" , PUBLIC | ABSTRACT | EXACT )

								),

						//Method #4: describeContents
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #5: writeToParcel
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(android.os.Parcel.class),
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

										new ClassItem[0]

								),

						//Constructor #1
						new ConstructorProfile
								(		PRIVATE | EXACT ,

										new ClassItem(android.os.Parcel.class)

								),

						//Constructor #2
						new ConstructorProfile
								(		SYNTHETIC | EXACT ,

										new ClassItem(android.os.Parcel.class),
										new ClassItem("com.touchtype.telemetry" , STATIC | EXACT )

								),


				});

		return newProfile;
	}


	public static ClassProfile get_THEME_LOADER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.l.k");
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
										new ClassItem(int.class),

										new ClassItem(boolean.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(boolean.class),
										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | EXACT )

								),

						//Method #4: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(boolean.class)

								),

						//Method #5: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #6: c
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #7: d
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #8: e
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

								),

						//Method #9: f
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem(boolean.class),
										new ClassItem(android.content.res.Resources.class)

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

	public static ClassProfile get_KEYBOARD_SIZER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.j");
		newProfile.setKnownPath("com.touchtype");

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
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(int.class)),	//a

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
										new ClassItem(android.view.View.class),

										new ClassItem(android.content.Context.class),
										new ClassItem(android.view.View.class),
										new ClassItem(int.class),
										new ClassItem(int.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem(int.class)

								),

						//Method #2: drawChild
						new MethodProfile
								(
										PROTECTED | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(android.graphics.Canvas.class),
										new ClassItem(android.view.View.class),
										new ClassItem(long.class)

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


				});

		return newProfile;
	}




}
