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




}
