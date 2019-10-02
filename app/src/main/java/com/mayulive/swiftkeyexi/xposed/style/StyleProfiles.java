package com.mayulive.swiftkeyexi.xposed.style;

import com.mayulive.xposed.classhunter.profiles.ClassItem;
import com.mayulive.xposed.classhunter.profiles.ClassProfile;
import com.mayulive.xposed.classhunter.profiles.ConstructorProfile;
import com.mayulive.xposed.classhunter.profiles.FieldItem;
import com.mayulive.xposed.classhunter.profiles.MethodProfile;

import static com.mayulive.xposed.classhunter.Modifiers.*;

public class StyleProfiles
{

	public static ClassProfile get_DRAWABLE_LOADER_CLASS_PROFILE()
	{
		ClassProfile newProfile = new ClassProfile();

		newProfile.setFullPath("je5");
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
						new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

				});
/////////////////////////
//Declared fields
/////////////////////////
		newProfile.setDeclaredFields(new FieldItem[]
				{
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT )),	//a
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem("" , PUBLIC | EXACT )),	//b
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.Map.class)),	//c
						new FieldItem( PUBLIC | EXACT , 	new ClassItem(int.class)),	//d
						new FieldItem( PUBLIC | FINAL | EXACT , 	new ClassItem(java.util.Map.class)),	//e
						new FieldItem( PUBLIC | EXACT , 	new ClassItem("com.google.common.base.Optional" , PUBLIC | ABSTRACT | EXACT )),	//f

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
										new ClassItem(float.class),

										new ClassItem(double.class)

								),

						//Method #1: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(int.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #2: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.graphics.RectF.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #3: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #4: a
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem(java.lang.Object.class),
										new ClassItem("" , PUBLIC | STATIC | INTERFACE | ABSTRACT | EXACT )

								),

						//Method #5: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #6: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #7: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #8: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #9: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #10: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(android.text.TextPaint.class),

										new ClassItem(java.lang.String.class)

								),

						//Method #11: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(java.lang.Integer.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #12: a
						new MethodProfile
								(
										PUBLIC | EXACT ,
										new ClassItem(void.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #13: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

										new ClassItem("" , PUBLIC | EXACT )

								),

						//Method #14: b
						new MethodProfile
								(
										PUBLIC | FINAL | EXACT ,
										new ClassItem(android.graphics.drawable.Drawable.class),

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

										new ClassItem("com.google.common.base.Supplier" , PUBLIC | INTERFACE | ABSTRACT | EXACT ),
										new ClassItem("" , PUBLIC | EXACT )

								),


				});

		return newProfile;
	}

}
