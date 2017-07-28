package com.mayulive.swiftkeyexi.main.shortcutkeys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

import java.util.Arrays;

/**
 * Created by Roughy on 2/13/2017.
 */

public abstract class shortcutkeysEntryDialog
{

	AlertDialog mDialog = null;
	private static final KeyboardInteraction.TextAction[] mDropdownOptions = Arrays.copyOfRange(KeyboardInteraction.TextAction.values(), 1, KeyboardInteraction.TextAction.values().length);

	shortcutkeysEntryDialog(Context context, DB_ModifierKeyItem inputItem)
	{

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		View configView = inflater.inflate(R.layout.shortcut_key_list_dialog, null,false);
		builder.setView(configView);

		final Spinner actionMenu = (Spinner) configView.findViewById(R.id.shortcutkey_action_picker);
		final EditText keyEditText = (EditText)  configView.findViewById(R.id.shortcutkey_text);

		String[] menuItems = new String[mDropdownOptions.length];
		{
			for (int i = 0; i < mDropdownOptions.length; i++)
			{
				menuItems[i] = KeyboardInteraction.TextAction.getTextRepresentation(context,mDropdownOptions[i]);
			}
		}

		ArrayAdapter<CharSequence> actionMenuAdapter = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_dropdown_item, menuItems);

		actionMenu.setAdapter(actionMenuAdapter);

		boolean existingItem = inputItem != null;
		if (!existingItem)
		{
			inputItem = new DB_ModifierKeyItem(-1, "", KeyboardInteraction.TextAction.COPY);
		}
		else
		{
			//Load existing values into view
			actionMenu.setSelection(actionToIndex( inputItem.get_textAction() ));
			keyEditText.setText( inputItem.get_key() );

		}

		final DB_ModifierKeyItem outputItem = inputItem;


		builder.setMessage(R.string.hotkeys_define_shortcut)
				.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						outputItem.set_textAction( indexToAction( actionMenu.getSelectedItemPosition() ) );
						outputItem.set_key( keyEditText.getText().toString() );

						onEntrySaved(outputItem);
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
					onEntryDeleted(outputItem);
				}
			});
		}

		mDialog = builder.create();
	}

	private static KeyboardInteraction.TextAction indexToAction(int index)
	{
		return mDropdownOptions[index];
	}

	// ...
	private static int actionToIndex(KeyboardInteraction.TextAction action)
	{
		for (int i = 0; i < mDropdownOptions.length; i++)
		{
			if (mDropdownOptions[i] == action)
				return i;
		}

		return -1;
	}

	public abstract void onEntrySaved(DB_ModifierKeyItem item);
	public abstract void onEntryDeleted(DB_ModifierKeyItem item);


	public void show()
	{
		mDialog.show();
	}

}
