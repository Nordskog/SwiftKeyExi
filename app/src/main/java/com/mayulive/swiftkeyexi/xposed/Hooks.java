package com.mayulive.swiftkeyexi.xposed;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.xposed.emoji.EmojiCommons;
import com.mayulive.swiftkeyexi.xposed.predictions.PredictionHooks;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.xposed.emoji.EmojiHooks;
import com.mayulive.swiftkeyexi.xposed.key.KeyCommons;
import com.mayulive.swiftkeyexi.xposed.key.KeyHooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardHooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysHooks;
import com.mayulive.swiftkeyexi.xposed.predictions.PredictionCommons;
import com.mayulive.swiftkeyexi.xposed.selection.SelectionHooks;
import com.mayulive.xposed.classhunter.packagetree.PackageTree;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;

/**
 * Created by Roughy on 6/22/2017.
 */

public class Hooks
{
	private static String LOGTAG = ExiModule.getLogTag(Hooks.class);

	//Predictions
	public static HookCategory predictionHooks_more = new HookCategory("PredictionHooks More");
	public static HookCategory predictionHooks_base = new HookCategory("PredictionHooks", predictionHooks_more);
	public static HookCategory predictionHooks_candidate = new HookCategory("PredictionHooks Candidate", predictionHooks_base);

	//Selection
	public static HookCategory selectionHooks_base = new HookCategory("SelectionHooks");

	//Popups
	public static HookCategory popupHooks_delay = new HookCategory("PopupHooks Delay");
	public static HookCategory popupHooks_cancel = new HookCategory("PopupHooks Cancel", selectionHooks_base);
	public static HookCategory popupHooks_modify = new HookCategory("PopupHooks Modify");
	public static HookCategory popupHooks_read = new HookCategory("PopupHooks Read", popupHooks_modify);

	//Emoji
	public static HookCategory emojiHooks_theme = new HookCategory("EmojiHooks Theme");
	public static HookCategory emojiHooks_base = new HookCategory("EmojiHooks Base", emojiHooks_theme);

	//Key
	public static HookCategory keyHooks_keyCancel = new HookCategory("KeyHooks Cancel");
	public static HookCategory keyHooks_keyDefinition = new HookCategory("KeyHooks Definition", keyHooks_keyCancel, selectionHooks_base);

	//Overlay (Doesn't contain any hooks, just dependencies)
	public static HookCategory overlayHooks_base = new HookCategory("overlayHooks base");

	//Keyboard
	public static HookCategory baseHooks_viewCreated= new HookCategory("KeyboardHooks ViewCreated", overlayHooks_base);
	public static HookCategory baseHooks_invalidateLayout = new HookCategory("KeyboardHooks InvalidateLayout", popupHooks_modify);
	public static HookCategory baseHooks_layoutChange = new HookCategory("KeyboardHooks LayoutChange", overlayHooks_base);



	//Base, unhook everything.
	public static HookCategory baseHooks_base = new HookCategory("KeyboardHooks base", 	baseHooks_layoutChange,
																						baseHooks_invalidateLayout,
																						keyHooks_keyDefinition,
																						emojiHooks_base,
																						predictionHooks_base );

	public static class HookCategory extends ArrayList<XC_MethodHook.Unhook>
	{

		private String mName = "NULL";
		private boolean mRequirementsMet = true;
		private HookCategory[] mDepenencies = new HookCategory[0];
		private boolean mLogMe = true;

		HookCategory(String name)
		{
			this(name, new HookCategory[0]);
		}

		HookCategory(String name,  HookCategory... dependencies)
		{
			super();
			this.mName = name;
			this.mDepenencies = dependencies;
		}


		//Returns true if change state as a result of this call
		public boolean setRequirementsMet(boolean depenencyMet)
		{

			//Only react if state changes from true to false
			if (mRequirementsMet && !depenencyMet)
			{
				//Propegate to depenencies
				invalidate(null, "Requirement not met");
				return false;
			}

			return true;
		}

		public void invalidate(Throwable ex, String reason)
		{
			if (mRequirementsMet)
			{
				mRequirementsMet = false;
				logRemoval(ex,reason);
				removeHooks();
				invalidateDepenencies();
			}
			else
			{
				Log.d(LOGTAG, "Attempted to invalidated already invalidated HookCategory");
			}
		}


		private void logRemoval(@Nullable Throwable ex, @Nullable String reason)
		{
			if (mLogMe)
			{
				if (reason == null)
					reason = "NULL";

				Log.e(LOGTAG, "Removed Hooks: "+mName+", "+reason);
				if (ex != null)
					ex.printStackTrace();
			}
		}

		private void invalidateDepenencies()
		{
			//Set the state of any depenencies to false, and remove their hooks
			for (HookCategory hookCategory : mDepenencies)
			{
				//hookCategory.mLogMe = false;
				hookCategory.invalidate(null, "Dependency invalidated");
			}
		}

		public boolean isRequirementsMet()
		{
			return mRequirementsMet;
		}

		private void removeHooks()
		{
			for (XC_MethodHook.Unhook hook : this)
			{
				hook.unhook();
			}
			this.clear();
		}
	}

	//Convenience method for checking requirement and logging on failure
	public static void logSetRequirement( HookCategory category, String name, boolean newValue)
	{
		if ( !category.setRequirementsMet( newValue ))
		{
			Log.e(LOGTAG, "Pre-Hook failure: "+name+": Unexpected Value");
		}
	}

	//Convenience method for checking requirement and logging on failure
	//Sets to false if input value is null
	public static void logSetRequirementFalseIfNull(HookCategory category, String name, @Nullable Object value)
	{
		if ( !category.setRequirementsMet( value != null ))
		{
			Log.e(LOGTAG, "Pre-Hook failure: "+name+": Unexpected Value");
		}
	}


	public static void hookAll(PackageTree classTree)
	{

		//If this fails we are screwed
		KeyboardHooks.HookAll(classTree);

		if (Hooks.baseHooks_base.isRequirementsMet())
		{

			//No a hook, just sets a listener
			preventPeriodHook();

			//Emojis do not depend on anything else
			EmojiHooks.HookAll(classTree);

			KeyHooks.HookAll(classTree);

			//Predictions also fairly independent
			PredictionHooks.HookAll(classTree);


			//Selection requires popups to keep track of
			//keys with multiple popups.
			PopupkeysHooks.hookAll(classTree);


			SelectionHooks.hookAll(classTree);


			//While loading needs to be done /immediatel/ and is thus handled on the main
			//thread by each individual hook, we don't want to be too wasteful about saving
			//things that need to saved, so that is handled with a single thread call here.

			//The other ones could of course be handled with futures and all that, but they are
			//only updated when the user changes something, so it's hardly worth the effort.
			KeyboardMethods.addKeyboardEventListener(new KeyboardMethods.KeyboardEventListener()
			{
				@Override
				public void beforeKeyboardOpened() {}

				@Override
				public void beforeKeyboardClosed()
				{
					AsyncTask.execute(new Runnable()
					{
						@Override
						public void run()
						{
							try
							{

								//These will run every time the keyboard closest, so very often.
								//They shouldn't fail, but if they do it won't be a disaster.
								//What would be a disaster is them crashing swiftkey in the process.
								PredictionCommons.savePriority();
								EmojiCommons.saveRecents();
							}
							catch (Exception ex)
							{
								ex.printStackTrace();
								Log.e(LOGTAG, "Something went wrong performing keyboardc-close duties. Not fatal, but please report.");
							}


						}
					});
				}

				@Override
				public void keyboardInvalidated()
				{

				}

				@Override
				public void afterKeyboardConfigurationChanged() {}
			});
		}
	}



	private static void preventPeriodHook()
	{
		KeyCommons.addOnKeyDownListener(new KeyCommons.OnKeyDownListener()
		{
			@Override
			public void onKeyDown(KeyDefinition key)
			{
				if (key.is(KeyCommons.KeyType.PERIOD) && Settings.DISABLE_PERIOD_CLICK)
				{

					KeyCommons.requestCancelNextKey();

					//Unfortunately, period key does not abide by the popup to trigger
					//PopupkeysCommons.requestPopupDelay();
				}
			}
		});
	}
}
