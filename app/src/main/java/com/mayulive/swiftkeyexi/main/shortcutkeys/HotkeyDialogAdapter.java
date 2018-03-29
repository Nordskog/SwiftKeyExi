package com.mayulive.swiftkeyexi.main.shortcutkeys;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.data.ModifierKeyItem;

/**
 * Created by Roughy on 3/15/2018.
 */

public class HotkeyDialogAdapter extends PagerAdapter
{

	Context mContext = null;
	ModifierKeyItem mItem = null;


	public HotkeyDialogAdapter(Context context, ModifierKeyItem item)
	{
		super();
		mContext = context;
		mItem = item;

	}

	@Override
	public int getCount()
	{
		return 2;
	}

	@Override
	public boolean isViewFromObject(View view, Object object)
	{
		return view == object;
	}

	@Override
	public String getPageTitle(int position)
	{

		switch(position)
		{
			case 0:
				return mContext.getResources().getText(R.string.hotkeys_software_key_short).toString();
			case 1:
				return mContext.getResources().getText(R.string.hotkeys_hardware_key_short).toString();
		}

		return "null";
	}

	@Override
	public Object instantiateItem(ViewGroup collection, int position)
	{
		SoftHardKeyDialogView view;
		if (position == 0)
			view = new SoftKeyView(collection.getContext(), mItem);
		else
			view = new HardKeyView(collection.getContext(), mItem);

		collection.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup collection, int position, Object view)
	{
		collection.removeView((View) view);
	}



}