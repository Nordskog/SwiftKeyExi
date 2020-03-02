package com.mayulive.swiftkeyexi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Pref<T>
{
	private static final String TAG = "Pref";
	private static final String SHARED_PREFS_DEFAULT = "shared_prefs_default";

	protected String prefsName;
	private String prefKey;
	private int prefKeyStringResource;
	private boolean useStringResource = false;

	public Pref(String prefsName, String prefKey)
	{
		this.prefsName = prefsName;
		this.prefKey = prefKey;
	}

	public Pref(String prefKey)
	{
		this.prefsName = SHARED_PREFS_DEFAULT;
		this.prefKey = prefKey;
	}

	public Pref(String prefsName, int prefKeyStringRes)
	{
		this.prefsName = prefsName;
		this.prefKeyStringResource = prefKeyStringRes;
		this.useStringResource = true;
	}

	public Pref(int prefKeyStringRes)
	{
		this.prefsName = SHARED_PREFS_DEFAULT;
		this.prefKeyStringResource = prefKeyStringRes;
		this.useStringResource= true;
	}

	public String getKey(Context context)
	{
		if (useStringResource)
		{
			return context.getString( this.prefKeyStringResource);
		}
		else
		{
			return this.prefKey;
		}
	}

	////////////////
	// Boolean
	////////////////

	public static class BooleanPreference extends Pref
	{
		public BooleanPreference(String prefsName, String prefName)
		{
			super(prefsName, prefName);
		}

		public BooleanPreference(String prefName)
		{
			super(prefName);
		}

		public BooleanPreference(String prefsName, int prefKeyStringRes) { super(prefsName, prefKeyStringRes); }

		public BooleanPreference( int prefKeyStringRes) { super(prefKeyStringRes); }

		public boolean get(Context context, boolean def)
		{
			return context.getSharedPreferences( prefsName, Context.MODE_PRIVATE).getBoolean(this.getKey(context), def);
		}

		public void set(Context context, boolean value)
		{
			SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean(this.getKey(context), value);
			editor.apply();
		}

	}


	////////////////
	// Integer
	////////////////

	public static class IntegerPreference extends Pref
	{
		public IntegerPreference(String prefsName, String prefName)
		{
			super(prefsName, prefName);
		}

		public IntegerPreference(String prefName)
		{
			super(prefName);
		}


		public IntegerPreference(String prefsName, int prefKeyStringRes) { super(prefsName, prefKeyStringRes); }

		public IntegerPreference( int prefKeyStringRes) { super(prefKeyStringRes); }

		public int get(Context context, int def)
		{
			return context.getSharedPreferences( prefsName, Context.MODE_PRIVATE).getInt(this.getKey(context), def);
		}

		public void set(Context context, int value)
		{
			SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putInt(this.getKey(context), value);
			editor.apply();
		}
	}

	////////////////
	// Float
	////////////////

	public static class FloatPreference extends Pref
	{
		public FloatPreference(String prefsName, String prefName)
		{
			super(prefsName, prefName);
		}

		public FloatPreference(String prefName)
		{
			super(prefName);
		}

		public FloatPreference(String prefsName, int prefKeyStringRes) { super(prefsName, prefKeyStringRes); }

		public FloatPreference( int prefKeyStringRes) { super(prefKeyStringRes); }

		public float get(Context context, float def)
		{
			return context.getSharedPreferences( prefsName, Context.MODE_PRIVATE).getFloat(this.getKey(context), def);
		}

		public void set(Context context, float value)
		{
			SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putFloat(this.getKey(context), value);
			editor.apply();
		}
	}


	////////////////
	// String
	////////////////

	public static class StringPreference extends Pref
	{
		public StringPreference(String prefsName, String prefName)
		{
			super(prefsName, prefName);
		}

		public StringPreference(String prefName)
		{
			super(prefName);
		}

		public StringPreference(String prefsName, int prefKeyStringRes) { super(prefsName, prefKeyStringRes); }

		public StringPreference( int prefKeyStringRes) { super(prefKeyStringRes); }

		public String get(Context context, String def)
		{
			return context.getSharedPreferences( prefsName, Context.MODE_PRIVATE).getString(this.getKey(context), def);
		}

		public void set(Context context, String value)
		{
			SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString(this.getKey(context), value);
			editor.apply();
		}
	}

	////////////////
	// Long
	////////////////

	public static class LongPreference extends Pref
	{
		public LongPreference(String prefsName, String prefName)
		{
			super(prefsName, prefName);
		}

		public LongPreference(String prefName)
		{
			super(prefName);
		}

		public LongPreference(String prefsName, int prefKeyStringRes) { super(prefsName, prefKeyStringRes); }

		public LongPreference( int prefKeyStringRes) { super(prefKeyStringRes); }

		public long get(Context context, long def)
		{
			return context.getSharedPreferences( prefsName, Context.MODE_PRIVATE).getLong(this.getKey(context), def);
		}

		public void set(Context context, long value)
		{
			SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putLong(this.getKey(context), value);
			editor.apply();
		}
	}

	//////////////////
	// Enum
	//////////////////

	public static class EnumPreference<T extends Enum<T>> extends Pref
	{
		public EnumPreference(String prefsName, String prefName)
		{
			super(prefsName, prefName);
		}

		public EnumPreference(String prefName)
		{
			super(prefName);
		}

		public EnumPreference(String prefsName, int prefKeyStringRes) { super(prefsName, prefKeyStringRes); }

		public EnumPreference( int prefKeyStringRes) { super(prefKeyStringRes); }

		public T get(Context context, T def)
		{
			T val = def;

			try
			{
				String enumVal = context.getSharedPreferences( prefsName, Context.MODE_PRIVATE).getString(this.getKey(context), null);
				if (enumVal != null)
				{
					val = Enum.valueOf( def.getDeclaringClass(), enumVal );
				}

			}
			catch ( Exception ex )
			{
				Log.i(TAG, "Failed to load enum pref");
				ex.printStackTrace();
			}

			return val;

		}

		public void set(Context context, T value)
		{
			SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();

			editor.putString(this.getKey(context), value.toString());
			editor.apply();
		}
	}


}
