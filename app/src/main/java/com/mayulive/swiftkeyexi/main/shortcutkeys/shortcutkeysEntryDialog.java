package com.mayulive.swiftkeyexi.main.shortcutkeys;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.data.ModifierKeyItem;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

/**
 * Created by Roughy on 2/13/2017.
 */

public abstract class shortcutkeysEntryDialog
{

	AlertDialog mDialog = null;
	private static final KeyboardInteraction.TextAction[] mDropdownOptions = KeyboardInteraction.TextAction.getUsableTextActions();

	HardKeyView mHardFrag = null;
	SoftKeyView mSoftFrag = null;

	HotkeyDialogAdapter mAdapter;
	ViewPager mPager;
	ModifierKeyItem mItem;

	//Store changes in intermediate item so we can cancel
	ModifierKeyItem mConfigItem;


	shortcutkeysEntryDialog(Context context, FragmentManager fm, DB_ModifierKeyItem inputItem)
	{
		mItem = inputItem;

		//////////////////////////////////
		//Create new item if necessary
		//////////////////////////////////

		boolean existingItem = mItem != null;
		if (mItem == null)
		{
			mItem = new DB_ModifierKeyItem(-1, "", KeyboardInteraction.TextAction.COPY);
		}

		mConfigItem = mItem.clone();

		///////////////////
		// Inflate view
		///////////////////

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		View configView = inflater.inflate(R.layout.hotkey_dialog_layout, null,false);
		builder.setView(configView);

		//Tabs
		mAdapter = new HotkeyDialogAdapter(context, mConfigItem);
		mPager = configView.findViewById(R.id.hotkey_softhard_viewpager);
		mPager.setAdapter(mAdapter);

		TabLayout titleIndicator = configView.findViewById(R.id.hotkey_softhard_tablayout);
		titleIndicator.setupWithViewPager(mPager);

		if (mConfigItem.get_hotkey_type() == ModifierKeyItem.HotkeyKeyType.SOFT)
			mPager.setCurrentItem(0);
		else
			mPager.setCurrentItem(1);

		mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{

			}

			@Override
			public void onPageSelected(int position)
			{
				if (position == 0)
				{
					mConfigItem.set_hotkey_type( ModifierKeyItem.HotkeyKeyType.SOFT );
				}
				else
				{
					mConfigItem.set_hotkey_type( ModifierKeyItem.HotkeyKeyType.HARD );
				}
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{

			}
		});

		///////////////////////
		//Setup callbacks
		////////////////////////


		builder.setMessage(R.string.hotkeys_define_shortcut)
				.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						shortcutkeysEntryDialog.this.saveActiveTab();
						onEntrySaved(mItem);
					}
				})
				.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{

					}
				});

		if (existingItem)
		{
			//Add delete button
			builder.setNeutralButton(R.string.button_delete, new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int id)
				{
					onEntryDeleted(mItem);
				}
			});
		}

		mDialog = builder.create();

	}

	//Save the info from the current active tab,
	private void saveActiveTab()
	{
		mItem.set(mConfigItem);
	}


	public abstract void onEntrySaved(ModifierKeyItem item);
	public abstract void onEntryDeleted(ModifierKeyItem item);


	public void show()
	{
		mDialog.show();
		//When you have an edittext that isn't initially visible in a dialog,
		//android will refuse to open the keyboard anymore.
		mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

	}

}
