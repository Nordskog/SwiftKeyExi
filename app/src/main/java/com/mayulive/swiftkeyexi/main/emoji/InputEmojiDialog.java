package com.mayulive.swiftkeyexi.main.emoji;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.mayulive.swiftkeyexi.R;

public abstract class InputEmojiDialog
{
	AlertDialog mDialog = null;

	InputEmojiDialog(Context context)
	{

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		View configView = inflater.inflate(R.layout.emoji_input_emoji_layout, null,false);
		builder.setView(configView);

		final EditText textValueBox = (EditText)configView.findViewById(R.id.textValueBox);

		builder.setMessage(R.string.dictionary_set_shortcut)
				.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						String textField = textValueBox.getText().toString().trim();

						if (!textField.isEmpty())
						{
							onEntrySaved(textField);
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


		// Create the AlertDialog object and return it
		mDialog = builder.create();
	}

	public abstract void onEntrySaved(String input );

	public void show()
	{
		mDialog.show();
	}

}

