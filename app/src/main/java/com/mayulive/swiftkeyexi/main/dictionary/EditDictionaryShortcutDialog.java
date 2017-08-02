package com.mayulive.swiftkeyexi.main.dictionary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryShortcutItem;
import com.mayulive.swiftkeyexi.R;

public abstract class EditDictionaryShortcutDialog
{

	AlertDialog mDialog = null;

	EditDictionaryShortcutDialog(Context context, final DB_DictionaryShortcutItem outputItem)
	{
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		View configView = inflater.inflate(R.layout.edit_dictionary_shortcut_dialog_layout, null,false);
		builder.setView(configView);

		final EditText keyValueBox = (EditText)configView.findViewById(R.id.keyValueBox);
		final CheckBox secondaryCheckbox = (CheckBox)configView.findViewById(R.id.secondaryCheckbox);

		keyValueBox.setText(outputItem.get_key());
		secondaryCheckbox.setChecked(outputItem.getInsertSecondary());

		final String oldKey = outputItem.get_key();

		builder.setMessage(R.string.dictionary_set_shortcut)
				.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						String keyField = keyValueBox.getText().toString().trim().toLowerCase();
						Boolean makeSecondaryValue = secondaryCheckbox.isChecked();

						outputItem.setInsertSecondary(makeSecondaryValue);

						if (keyField.isEmpty())
						{

							onEntryDeleted(outputItem,keyField);

						}
						else
						{
							outputItem.set_key( keyField );
							onEntrySaved(outputItem, oldKey );
						}

					}
				})
				.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						// User cancelled the dialog
					}
				});


		{
			//Add delete button
			builder.setNeutralButton(R.string.button_delete, new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int id)
				{
					onEntryDeleted(outputItem, oldKey);
				}
			});
		}

		// Create the AlertDialog object and return it
		mDialog = builder.create();
	}

	public abstract void onEntrySaved(DB_DictionaryShortcutItem item, String oldKey);
	public abstract void onEntryDeleted(DB_DictionaryShortcutItem item, String oldKey);


	public void show()
	{
		mDialog.show();
	}

}

