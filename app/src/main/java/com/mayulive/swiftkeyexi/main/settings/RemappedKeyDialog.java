package com.mayulive.swiftkeyexi.main.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.HardwareKeyboardInputDialog;
import com.mayulive.swiftkeyexi.main.commons.data.DB_RemappedKey;
import com.mayulive.swiftkeyexi.main.commons.data.HardKey;
import com.mayulive.swiftkeyexi.main.commons.data.HardKeyType;
import com.mayulive.swiftkeyexi.main.commons.data.RemappedKey;
import com.mayulive.swiftkeyexi.util.KeyUtils;

/**
 * Created by Roughy on 2/13/2017.
 */

public abstract class RemappedKeyDialog
{

	AlertDialog mDialog = null;

	DB_RemappedKey mItem;
	RemappedKey mConfigItem = new RemappedKey();

	Button mFromButton;
	Button mToButton;

	Context mContext;

	RemappedKeyDialog(Context context, DB_RemappedKey inputItem)
	{
		mItem = inputItem;
		mContext = context;

		//////////////////////////////////
		//Create new item if necessary
		//////////////////////////////////

		boolean existingItem = mItem != null;
		if (mItem == null)
		{
			mItem = new DB_RemappedKey();
		}

		mConfigItem = mItem.clone();

		///////////////////
		// Inflate view
		///////////////////

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		View configView = inflater.inflate(R.layout.remapped_key_dialog_layout, null,false);
		builder.setView(configView);

		mFromButton = configView.findViewById(R.id.remapped_key_from_button);
		mToButton = configView.findViewById(R.id.remapped_key_to_button);

		setFromButton(mConfigItem.getFrom_key());
		setToButton(mConfigItem.getTo_key());

		///////////////////////
		//Setup callbacks
		////////////////////////

		mFromButton.setOnClickListener(v ->
		{
			HardwareKeyboardInputDialog dialog = new HardwareKeyboardInputDialog(mContext)
			{
				@Override
				public void onKeyInput(int keycode, int scancode)
				{
					setFromButton(new HardKey(keycode, scancode));
				}
			};

			dialog.show();
		});

		mToButton.setOnClickListener(v ->
		{
			HardwareKeyboardInputDialog dialog = new HardwareKeyboardInputDialog(mContext)
			{
				@Override
				public void onKeyInput(int keycode, int scancode)
				{
					setToButton(new HardKey(keycode, scancode));
				}
			};

			dialog.show();
		});

		builder.setMessage(R.string.hotkeys_define_shortcut)
				.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						mItem.set(mConfigItem);
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

	private void setFromButton(HardKey key)
	{
		mConfigItem.setFrom_key(key);
		mFromButton.setText( KeyUtils.getKeyString(mContext, key) );
	}

	private void setToButton(HardKey key)
	{
		mConfigItem.setTo_key(key);
		mToButton.setText( KeyUtils.getKeyString(mContext, key) );
	}

	public abstract void onEntrySaved(DB_RemappedKey item);
	public abstract void onEntryDeleted(DB_RemappedKey item);


	public void show()
	{
		mDialog.show();
	}

}
