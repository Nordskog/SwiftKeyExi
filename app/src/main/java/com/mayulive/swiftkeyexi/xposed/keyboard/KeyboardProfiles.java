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


	public static ClassProfile get_EMOJI_THEME_LOADER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.v.a.t");
		newProfile.setKnownPath("com.touchtype");

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
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(int.class)),	//d
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )),	//e
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )),	//f
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )),	//g
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )),	//h
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )),	//i

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
										new ClassItem(java.lang.Integer.class)

								),

						//Method #1: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #2: equals
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.Object.class)

								),

						//Method #3: hashCode
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

										new ClassItem("com.touchtype" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("com.touchtype" , PUBLIC | FINAL | EXACT )

								),


				});

		return newProfile;
	}




}
