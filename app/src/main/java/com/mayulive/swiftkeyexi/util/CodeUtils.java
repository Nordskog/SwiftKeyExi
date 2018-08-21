package com.mayulive.swiftkeyexi.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roughy on 5/20/2017.
 */

public class CodeUtils
{

	private static String LOGTAG = "Exi/"+CodeUtils.class.getSimpleName();

	public static void printStackTrace()
	{
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();

		for (StackTraceElement currentElement : stack)
		{
			Log.i(LOGTAG, currentElement.toString());
		}

	}

	public static void printArguments(String indent, Object[] args)
	{
		//Log.e("###", "Arg count: "+args.length);

		StringBuilder builder = new StringBuilder();
		builder.append("-> ");

		if (args == null)
		{
			Log.i(LOGTAG, "NULL");
			return;
		}


		for (Object arg : args)
		{
			if (arg == null)
				builder.append(", \"NULL\"");
			else
				builder.append("\""+arg.toString()+"\", ");
		}

		Log.i(LOGTAG, indent+builder.toString());

	}

	public static void printViewInfo(View view, int depth)
	{
		String depthIndent = "";
		for (int i = 0; i < depth; i++)
		{
			depthIndent = depthIndent + "|  ";
		}



		String className = view.getClass().getName();

		int viewID = view.getId();

		String viewIdName = null;
		try
		{
			viewIdName = view.getResources().getResourceName(viewID);
		}
		catch(Exception ex)
		{

		}

		if (viewIdName == null && viewID != View.NO_ID)
		{
			viewIdName = "#"+viewID;
		}


		Log.i(LOGTAG,depthIndent + className+", ID: "+viewIdName+", Viz: "+getVisibility(view) );



	}

	public static View getTopParent(View view)
	{
		View finalParent = view;
		Object currentParent = (Object)view;

		while (currentParent != null)
		{
			finalParent = (View)currentParent;

			currentParent = (Object) ((View)currentParent).getParent();

			if (!(currentParent instanceof View))
				break;


		}

		return finalParent;



	}

	public static String getParamValues(Object[] params)
	{
		StringBuilder builder = new StringBuilder();

		for (Object currentParam : params)
		{
			if (currentParam != null)
			{
				try
				{
					builder.append(currentParam.toString()+", ");
				}
				catch (Exception ex)
				{
					builder.append("EXCEPTION"+", ");
				}


			}
			else
			{
				builder.append("NULL"+", ");
			}



		}

		return builder.toString();


	}

	public static String getVisibility(View view)
	{
		if (view == null)
			return "null";

		switch(view.getVisibility())
		{
			case View.VISIBLE:
			{
				return "VISIBLE";
			}
			case View.INVISIBLE:
			{
				return "IN-INVISIBLE";
			}
			case View.GONE:
			{
				return "GONE";
			}
		};

		return "null";
	}

	public static void traverseLayout(View rootView, int depth)
	{
		if (rootView != null)
			printViewInfo(rootView, depth);

		if ( rootView != null && rootView instanceof ViewGroup)
		{
			int childCount = ((ViewGroup) rootView).getChildCount();

			for (int i = 0; i < childCount; i++)
			{
				traverseLayout(((ViewGroup) rootView).getChildAt(i), depth + 1);
			}
		}
	}

	public static Enum findEnumByName(Enum[] enums, String searchString)
	{
		for (Enum currentEnum : enums)
		{
			if (currentEnum.name().equals(searchString))
				return currentEnum;
		}

		//Default
		return enums[0];
	}

	public static int findViewPosition(ViewGroup root, View target)
	{
		int childCount = root.getChildCount();

		for (int i = 0; i < childCount; i++)
		{
			if (root.getChildAt(i) == target)
				return i;
		}

		return -1;
	}

	private List<View> getAllChildren(View view)
	{
		ArrayList<View> views = new ArrayList<>();

		if ( !ViewGroup.class.isAssignableFrom( view.getClass() ) )
			return views;

		ViewGroup viewGroup = (ViewGroup)view;

		for (int i = 0; i < viewGroup.getChildCount(); i++)
		{
			views.addAll( getAllChildren( (viewGroup.getChildAt(i)) ) );
		}

		return views;
	}

	public static boolean isPrimitive(Class clazz)
	{
		if (clazz == int.class)
			return true;
		if (clazz == float.class)
			return true;
		if (clazz == String.class)
			return true;
		if (clazz == double.class)
			return true;
		if (clazz == long.class)
			return true;
		if (clazz == char.class)
			return true;
		if (clazz == boolean.class)
			return true;
		if (clazz == void.class)
			return true;
		if (clazz == short.class)
			return true;
		if (clazz == byte.class)
			return true;
		if (clazz == Integer.class)
			return true;
		if (clazz == Float.class)
			return true;
		if (clazz == Double.class)
			return true;
		if (clazz == Long.class)
			return true;
		if (clazz == Boolean.class)
			return true;
		if (clazz == Void.class)
			return true;
		if (clazz == Short.class)
			return true;
		if (clazz == Byte.class)
			return true;

		if (clazz == MotionEvent.class)
			return true;
		if (clazz.isEnum())
			return true;
		if (clazz == Activity.class)
			return true;
		if (clazz == Fragment.class)
			return true;
		if (clazz == Context.class)
			return true;
		if (clazz == Matrix.class)
			return true;

		if (clazz == AccessibilityManager.AccessibilityStateChangeListener.class)
			return true;

		{
			if (clazz.getName().startsWith("android."))
				return true;
		}

		return false;

	}

	public static void printSingleFieldValues(int depth, Object object, int maxDepth)
	{
		String indent = "";
		for (int i = 0; i < depth; i++)
		{
			indent = indent+"|    ";
		}


		if (depth > maxDepth)
		{
			Log.i(LOGTAG, indent+"####MAX DEPTH###");
			return;
		}


		if (object != null)
		{

			Class objectClass = object.getClass();
			Field[] fields = objectClass.getDeclaredFields();

			if (depth == 0)
				Log.i(LOGTAG, "Printing fields for Object of type "+objectClass.toString());

			try
			{
				int fieldCounter = 0;

				for (Field currentField : fields)
				{

					//if (fieldCounter > 10)
					//	break;

					currentField.setAccessible(true);

					if (currentField.get(object) != null)
					{
						Log.i(LOGTAG, indent+ "" + currentField.get(object).toString());
						if (  !isPrimitive(currentField.get(object).getClass()) )
							printSingleFieldValues(depth+1, currentField.get(object), maxDepth);

					}
					else
					{
						Log.i(LOGTAG, indent+ "NULL@" + currentField.getType().toString());
					}

					fieldCounter++;
				}

			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}

		}
	}

	public static void printFieldValues(Class clazz, Object instance)
	{
		Log.i(LOGTAG, "Printing fields for: "+clazz.getName());

		try
		{
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields)
			{
				field.setAccessible(true);
				Log.i(LOGTAG, "Field: "+field.getName()+", Value: "+field.get(instance));
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
