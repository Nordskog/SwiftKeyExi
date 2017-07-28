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

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.shortcut_key_list_item, parent,false);
		}


		Button keyView = (Button)convertView.findViewById(R.id.shortcutkey_text);
		TextView actionView = (TextView)convertView.findViewById(R.id.shortcutkey_action);

		DB_ModifierKeyItem item = mItems.get(position);

		keyView.setText( item.get_key());

		actionView.setText(KeyboardInteraction.TextAction.getTextRepresentation(convertView.getContext(), item.get_textAction() ));

		return convertView;

	}
}
