package com.mayulive.swiftkeyexi.main.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mayulive.swiftkeyexi.main.dictionary.DictionaryFragment;
import com.mayulive.swiftkeyexi.main.emoji.EmojiFragment;
import com.mayulive.swiftkeyexi.main.shortcutkeys.ShortcutkeysFragment;
import com.mayulive.swiftkeyexi.main.swipe.SwipeFragment;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.popupkeys.PopupkeysFragment;

/**
 * Created by Roughy on 12/4/2016.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter
{

	Context mContext = null;


	public MainViewPagerAdapter(Context context, FragmentManager fm)
	{
		super(fm);
		mContext = context;

	}

	@Override
	public int getCount()
	{
		return 5;
	}

	@Override
	public String getPageTitle(int position)
	{

		switch(position)
		{
			case 0:
				return mContext.getResources().getText(R.string.tab_name_swipe).toString();
			case 1:
				return mContext.getResources().getText(R.string.tab_name_emoji).toString();
			case 2:
				return mContext.getResources().getText(R.string.tab_name_dictionary).toString();
			case 3:
				return mContext.getResources().getText(R.string.tab_name_hotkeys).toString();
			case 4:
				return mContext.getResources().getText(R.string.tab_name_popups).toString();
			default:
				return "Null";
		}
	}

	@Override
	public Fragment getItem(int position)
	{
		switch(position)
		{
			case 0:
				return new SwipeFragment();
			case 1:
				return new EmojiFragment();
			case 2:
				return new DictionaryFragment();
			case 3:
				return new ShortcutkeysFragment();
			case 4:
				return new PopupkeysFragment();
				//return new TestFragment();
			default:
				return null;
		}

	}
}
