package com.mayulive.swiftkeyexi.main.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.mayulive.swiftkeyexi.main.commons.data.DB_KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.R;

/**
 * Created by Roughy on 2/13/2017.
 */

public abstract class KeyDefinitionEntryDialog
{

	AlertDialog mDialog = null;

	//Skip the first default value, second is symbol
	private static final KeyType[] mDropdownOptions = new KeyType[]
			{
					KeyType.SYMBOL,
					KeyType.SHIFT,
					KeyType.DELETE,
					KeyType.SWITCH_LAYOUT,
					KeyType.ENTER,
					KeyType.PERIOD,
					KeyType.CLEAR_BUFFER_KEY,
					KeyType.TAB,
					KeyType.SPACE,
					KeyType.EMOJI,
			};

	KeyDefinitionEntryDialog(Context context, DB_KeyDefinition inputItem)
	{

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		View configView = inflater.inflate(R.layout.key_definition_list_dialog, null,false);
		builder.setView(configView);

		final Spinner actionMenu = (Spinner) configView.findViewById(R.id.key_definition_type_picker);
		final EditText keyEditText = (EditText)  configView.findViewById(R.id.key_definition_content);

		String[] menuItems = new String[mDropdownOptions.length];
		{
			for (int i = 0; i < mDropdownOptions.length; i++)
			{
				menuItems[i] = KeyType.getKeyDefinitionDisplayString(context,mDropdownOptions[i]);
			}
		}


		ArrayAdapter<CharSequence> actionMenuAdapter = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_dropdown_item, menuItems);

		actionMenu.setAdapter(actionMenuAdapter);
		actionMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				KeyType type = indexToAction( position );
				keyEditText.setEnabled( type == KeyType.SYMBOL );
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});


		boolean existingItem = inputItem != null;
		if (!existingItem)
		{
			inputItem = new DB_KeyDefinition("", KeyType.SYMBOL);
		}
		else
		{

			//Load existing values into view
			actionMenu.setSelection(actionToIndex( inputItem.getType() ));
			keyEditText.setText( inputItem.getContent() );

		}

		final DB_KeyDefinition outputItem = inputItem;


		builder.setMessage(R.string.key_definition_define_key)
				.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						outputItem.setType( indexToAction( actionMenu.getSelectedItemPosition() ) );
						outputItem.setContent( keyEditText.getText().toString() );

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



	private static KeyType indexToAction(int index)
	{
		return mDropdownOptions[index];
	}

	// ...
	private static int actionToIndex(KeyType action)
	{
		for (int i = 0; i < mDropdownOptions.length; i++)
		{
			if (mDropdownOptions[i] == action)
				return i;
		}

		return -1;
	}

	public abstract void onEntrySaved(DB_KeyDefinition item);
	public abstract void onEntryDeleted(DB_KeyDefinition item);


	public void show()
	{
		mDialog.show();
	}

}
