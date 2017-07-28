package com.mayulive.swiftkeyexi.main.popupkeys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupKeyItem;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

/**
 * Created by Roughy on 2/13/2017.
 */

public abstract class popupkeysEntryDialog
{

	AlertDialog mDialog = null;

	private void setButtonListeners(final PopupkeysPositionWidget widget, final EditText lowerEditText)
	{
		lowerEditText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				widget.setSelectedKeyText(s.toString().trim());
			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});

	}

	popupkeysEntryDialog(Context context, DB_PopupParentKeyItem parentItem, @Nullable DB_PopupKeyItem keyItemInput)
	{
		this(context, parentItem, keyItemInput, -1);
	}

	popupkeysEntryDialog(Context context, DB_PopupParentKeyItem parentItem, @Nullable  DB_PopupKeyItem keyItemInput, int index)
	{

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		View configView = inflater.inflate(R.layout.popup_key_list_dialog, null,false);
		builder.setView(configView);


		final EditText lowerEditText = (EditText)  configView.findViewById(R.id.popupkey_lowercaseEditText);

		TextView headerTitle = (TextView)configView.findViewById(R.id.popupkey_parent_key);
		headerTitle.setText( parentItem.get_parentKey() );

		final PopupkeysPositionWidget widget = (PopupkeysPositionWidget) configView.findViewById(R.id.popupkey_position_widget);

		boolean existingItem = keyItemInput != null;
		if (!existingItem)
		{
			keyItemInput = new DB_PopupKeyItem(-1,index,"","", KeyboardInteraction.TextAction.DEFAULT,false);
		}

		final DB_PopupKeyItem keyItem = keyItemInput;

		widget.setFromKey(keyItem, parentItem);

		//updating the position widget for us
		setButtonListeners(widget,lowerEditText);

		//Set existing values
		//parentEditText.setText( outputItem.get_parentKey() );
		lowerEditText.setText( keyItem.get_popupLower());


		builder.setMessage(R.string.popups_set_popup_key)
				.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						//outputItem.set_parentKey( parentEditText.getText().toString() );
						keyItem.set_popupLower( lowerEditText.getText().toString().trim() );
						keyItem.set_insertIndex( widget.getSelectedPosition() );

						//outputItem.set_insertIndex(widget.getSelected());

						if (!keyItem.get_popupLower().isEmpty())
							onEntrySaved(keyItem);
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
					onEntryDeleted(keyItem);
				}
			});
		}

		mDialog = builder.create();
	}



	public abstract void onEntrySaved(DB_PopupKeyItem item);
	public abstract void onEntryDeleted(DB_PopupKeyItem item);


	public void show()
	{
		mDialog.show();
	}

}
