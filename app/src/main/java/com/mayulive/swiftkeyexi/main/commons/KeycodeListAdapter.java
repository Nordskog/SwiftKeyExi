package com.mayulive.swiftkeyexi.main.commons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.data.HardKeyType;
import com.mayulive.swiftkeyexi.util.KeyUtils;

/**
 * Created by Roughy on 3/19/2018.
 */

public class KeycodeListAdapter extends BaseAdapter
{
	private int[] mItems;
	private LayoutInflater mInflater = null;

	public KeycodeListAdapter(Context context, int[] items)
	{
		mItems = items;
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount()
	{
		return mItems.length;
	}

	@Override
	public Object getItem(int position)
	{
		return mItems[position];
	}

	@Override
	public long getItemId(int position)
	{
		return mItems[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.hardware_key_list_adapter_item, parent,false);
		}

		//Match_parent does not work for listview items when the listview is inside a dialog.
		ViewGroup.LayoutParams params = convertView.getLayoutParams();
		params.width = parent.getMeasuredWidth();
		convertView.setLayoutParams(params);

		TextView name = convertView.findViewById(R.id.hardware_key_key_name);
		TextView code = convertView.findViewById(R.id.hardware_key_key_code);

		code.setText( Integer.toString(mItems[position]) );
		name.setText(KeyUtils.getKeyString(parent.getContext(), mItems[position], HardKeyType.KEY_CODE) );


		return convertView;
	}
}
