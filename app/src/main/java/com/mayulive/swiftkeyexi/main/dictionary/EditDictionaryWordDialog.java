package com.mayulive.swiftkeyexi.main.dictionary;

import com.mayulive.swiftkeyexi.main.dictionary.data.DB_DictionaryWordItem;
import com.mayulive.swiftkeyexi.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public abstract class EditDictionaryWordDialog
{

	AlertDialog mDialog = null;

	EditDictionaryWordDialog(Context context, DB_DictionaryWordItem inputItem, final String key)
	{

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		View configView = inflater.inflate(R.layout.edit_dictionary_word_dialog_layout, null,false);
		builder.setView(configView);

		final boolean isExistingEntry = inputItem != null;
		if (inputItem == null)
		{
			inputItem = new DB_DictionaryWordItem();

		}
		final DB_DictionaryWordItem outputItem = inputItem;

		final EditText textValueBox = (EditText)configView.findViewById(R.id.textValueBox);
		final EditText keyValueBox = (EditText)configView.findViewById(R.id.keyValueBox);

		textValueBox.setText(outputItem.get_text());
		if (key != null)
			keyValueBox.setText(key);

		builder.setMessage(R.string.dictionary_set_shortcut)
				.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						String textField = textValueBox.getText().toString().trim();
						String keyField = keyValueBox.getText().toString().trim().toLowerCase();

						if (textField.isEmpty() && keyField.isEmpty())
						{
							if (isExistingEntry)
							{
								onEntryDeleted(outputItem,keyField);
							}
						}
						else
						{
							outputItem.set_text( textField );
							onEntrySaved(outputItem, keyField, key );
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

		if (isExistingEntry)
		{
			//Add delete button
			builder.setNeutralButton(R.string.button_delete, new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int id)
				{
					onEntryDeleted(outputItem, key);
				}
			});
		}



		// Create the AlertDialog object and return it
		mDialog = builder.create();
	}

	public abstract void onEntrySaved(DB_DictionaryWordItem item, String newKey, String oldKey);
	public abstract void onEntryDeleted(DB_DictionaryWordItem item, String key);

	public void show()
	{
		mDialog.show();
	}

}

