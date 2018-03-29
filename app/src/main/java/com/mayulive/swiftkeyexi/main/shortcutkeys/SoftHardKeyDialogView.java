package com.mayulive.swiftkeyexi.main.shortcutkeys;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.data.ModifierKeyItem;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

/**
 * Created by Roughy on 3/15/2018.
 */

public abstract class SoftHardKeyDialogView extends LinearLayout
{
	ModifierKeyItem mItem;
	private static final KeyboardInteraction.TextAction[] mDropdownOptions = KeyboardInteraction.TextAction.getUsableTextActions();

	private Spinner mActionMenu;

	public SoftHardKeyDialogView(Context context, ModifierKeyItem item)
	{
		super(context);

		mItem = item;

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflateLayout(inflater);


		/////////////////////////////////////////
		//Action menu for both soft and hard
		/////////////////////////////////////////

		mActionMenu = this.findViewById(R.id.shortcutkey_action_picker);
		String[] menuItems = new String[mDropdownOptions.length];
		{
			for (int i = 0; i < mDropdownOptions.length; i++)
			{
				menuItems[i] = KeyboardInteraction.TextAction.getTextRepresentation(getContext(),mDropdownOptions[i]);
			}
		}
		ArrayAdapter<CharSequence> actionMenuAdapter = new ArrayAdapter<CharSequence>(getContext(), android.R.layout.simple_spinner_dropdown_item, menuItems);
		mActionMenu.setAdapter(actionMenuAdapter);
		mActionMenu.setSelection(actionToIndex( mItem.get_textAction() ));

		mActionMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				mItem.set_textAction( indexToAction(position) );
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
	}

	public ModifierKeyItem getItem()
	{
		return mItem;
	}

	public KeyboardInteraction.TextAction getAction()
	{
		return indexToAction(mActionMenu.getSelectedItemPosition());
	}

	abstract void inflateLayout(LayoutInflater inflater);


	private static KeyboardInteraction.TextAction indexToAction(int index)
	{
		return mDropdownOptions[index];
	}

	private static int actionToIndex(KeyboardInteraction.TextAction action)
	{
		for (int i = 0; i < mDropdownOptions.length; i++)
		{
			if (mDropdownOptions[i] == action)
				return i;
		}

		return -1;
	}


}
