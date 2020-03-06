package com.mayulive.swiftkeyexi.main.popupkeys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;
import com.mayulive.swiftkeyexi.R;

/**
 * Created by Roughy on 5/4/2017.
 */

public abstract class popupkeysParentEntryDialog
{

	AlertDialog mDialog = null;


	popupkeysParentEntryDialog(Context context, @Nullable DB_PopupParentKeyItem inputParentItem)
	{

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		View configView = inflater.inflate(R.layout.popup_key_parent_dialog, null,false);
		builder.setView(configView);


		final EditText parentKeyEditText = (EditText)  configView.findViewById(R.id.popupkey_parent_key_edittext);

		boolean existingItem = inputParentItem != null;
		if (!existingItem)
		{
			inputParentItem = new DB_PopupParentKeyItem();
		}



		final DB_PopupParentKeyItem parentItem = inputParentItem;

		final String oldKey = parentItem.get_parentKey();

				//Set existing values
		parentKeyEditText.setText( parentItem.get_parentKey());

		builder.setMessage(R.string.popups_set_popup_parent_key)
				.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						//outputItem.set_parentKey( parentEditText.getText().toString() );
						parentItem.set_parentKey( parentKeyEditText.getText().toString().trim() );

						onEntrySaved(parentItem, oldKey);
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
					onEntryDeleted(parentItem);
				}
			});
		}

		mDialog = builder.create();
	}



	public abstract void onEntrySaved(DB_PopupParentKeyItem item, String oldKey);
	public abstract void onEntryDeleted(DB_PopupParentKeyItem item);


	public void show()
	{
		mDialog.show();
	}

}
