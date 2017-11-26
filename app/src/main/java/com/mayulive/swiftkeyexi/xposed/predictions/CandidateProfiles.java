package com.mayulive.swiftkeyexi.xposed.predictions;

import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ClassProfile;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;
import com.mayulive.xposed.classhunter.profiles.FieldItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import static com.mayulive.xposed.classhunter.Modifiers.*;

public class CandidateProfiles
{

	public static ClassProfile get_PREDICTION_TOKEN_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("com.touchtype.keyboard.f.g.w");
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
						new FieldItem( PRIVATE | EXACT , 	new ClassItem(java.lang.String.class)),	//a
						new FieldItem( PRIVATE | EXACT , 	new ClassItem("com.touchtype_fluency.Term" , PUBLIC | EXACT )),	//b
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(boolean.class)),	//c
						new FieldItem( PRIVATE | FINAL | EXACT , 	new ClassItem(boolean.class)),	//d

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
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | THIS | EXACT ),

										new ClassItem("com.touchtype_fluency.Term" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | THIS | EXACT ),

										new ClassItem(java.lang.String.class)

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | THIS | EXACT ),

										new ClassItem(java.lang.String.class),
										new ClassItem(boolean.class)

								),

						//Method #3: b
						new MethodProfile
								(
										PUBLIC | STATIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype.keyboard" , PUBLIC | FINAL | THIS | EXACT ),

										new ClassItem(boolean.class),
										new ClassItem(int.class)

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.String.class)

								),

						//Method #6: b
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class)

								),

						//Method #7: c
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem("com.touchtype_fluency.Term" , PUBLIC | EXACT )

								),

						//Method #8: d
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #9: e
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class)

								),

						//Method #10: equals
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(boolean.class),

										new ClassItem(java.lang.Object.class)

								),

						//Method #11: f
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.util.Set.class)

								),

						//Method #12: hashCode
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
								(		PRIVATE | EXACT ,

										new ClassItem(java.lang.String.class),
										new ClassItem("com.touchtype_fluency.Term" , PUBLIC | EXACT ),
										new ClassItem(boolean.class)

								),


				});

		return newProfile;
	}


}
