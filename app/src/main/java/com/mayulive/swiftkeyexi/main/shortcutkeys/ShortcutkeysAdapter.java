package com.mayulive.swiftkeyexi.main.shortcutkeys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.main.commons.data.DB_ModifierKeyItem;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.data.HardKeyType;
import com.mayulive.swiftkeyexi.main.commons.data.ModifierKeyItem;
import com.mayulive.swiftkeyexi.util.KeyUtils;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

import java.util.List;

/**
 * Created by Roughy on 2/13/2017.
 */

public class ShortcutkeysAdapter extends BaseAdapter
{
	List<DB_ModifierKeyItem> mItems;
	LayoutInflater mInflater;

	ShortcutkeysAdapter(Context context, List<DB_ModifierKeyItem> items)
	{
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mItems = items;
	}


	@Override
	public int getCount()
	{
		return mItems.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		//May not be intialized though. Hmmm
		return mItems.get(position).get_id();
	}

	public int getViewTypeCount()
	{
		return 2;
	}

	public int getItemViewType(int position)
	{
		switch(mItems.get(position).get_hotkey_type())
		{
			case SOFT:
			{
				return 0;
			}

			case HARD:
			{
				return 1;
			}

			default:
				return -1;
		}
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		DB_ModifierKeyItem item = mItems.get(position);

		if (convertView == null)
		{
			if (item.get_hotkey_type() == ModifierKeyItem.HotkeyKeyType.SOFT)
				convertView = mInflater.inflate(R.layout.shortcut_key_list_item, parent,false);
			else
				convertView = mInflater.inflate(R.layout.shortcut_key_hard_list_item, parent,false);
		}


		Button modifierView = (Button)convertView.findViewById(R.id.shortcutkey_modifier);
		Button keyView = (Button)convertView.findViewById(R.id.shortcutkey_text);
		TextView actionView = (TextView)convertView.findViewById(R.id.shortcutkey_action);
		TextView plusSign = convertView.findViewById(R.id.plusSign);

		actionView.setText(KeyboardInteraction.TextAction.getTextRepresentation(convertView.getContext(), item.get_textAction() ));


		if (item.get_hotkey_type() == ModifierKeyItem.HotkeyKeyType.SOFT)
		{
			keyView.setText( item.get_key());
		}
		else
		{
			modifierView.setText( KeyUtils.getKeyString( parent.getContext(), item.get_hard_modifier_key() ));

			//If modifier is undefined, hide modifier button and plus sign
			if ( item.get_hard_modifier_key().getType() == HardKeyType.UNDEFINED )
			{
				modifierView.setVisibility(View.GONE);
				plusSign.setVisibility(View.GONE);
			}
			else
			{
				modifierView.setVisibility(View.VISIBLE);
				plusSign.setVisibility(View.VISIBLE);
			}

			keyView.setText( KeyUtils.getKeyString( parent.getContext(), item.get_hard_key() ));
		}


		return convertView;

	}
}
