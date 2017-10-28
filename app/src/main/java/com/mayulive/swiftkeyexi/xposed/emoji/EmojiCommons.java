package com.mayulive.swiftkeyexi.xposed.emoji;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.SharedTheme;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.main.emoji.EmojiPanelView;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardClassManager;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem;
import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.providers.Provider;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.database.WrappedProvider;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiCache;
import com.mayulive.swiftkeyexi.main.emoji.EmojiPanelPagerAdapter;
import com.mayulive.swiftkeyexi.main.emoji.EmojiPanelTabLayout;
import com.mayulive.swiftkeyexi.util.view.FixedViewPager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import static com.mayulive.swiftkeyexi.util.ContextUtils.getHookContext;

/**
 * Created by Roughy on 12/26/2016.
 */

public class EmojiCommons
{
	private static String LOGTAG = ExiModule.getLogTag(EmojiCommons.class);

	protected static EmojiPanelTabLayout mEmojiPanelTabs;
	protected static EmojiPanelPagerAdapter mEmojiPanelAdapter;
	protected static int mEmojiPanelRecentsTabIndex = -1;
	protected static FixedViewPager mEmojiPanelPager;
	protected static FrameLayout mOuterTabsWrapper = null;
	protected static RelativeLayout mEmojiTopRelative = null;


	private final static int RECENTS_COUNT = 32;


	protected static ArrayList<DB_EmojiPanelItem> mPanelItems = new ArrayList<DB_EmojiPanelItem>();

	static long mLastUpdateTime = -1;
	static int mLastPanelIndex = 0;

	public static void inputText(String text)
	{
		if (KeyboardClassManager.keyboardServiceInstance != null)
		{
			try
			{
				InputConnection currentConnection = (InputConnection) KeyboardClassManager.getCurrentInputConnectionMethod.invoke( KeyboardClassManager.keyboardServiceInstance );

				if (currentConnection != null)
				{
					//If the cursor is after a letter or digit, swiftkey will insist on
					//putting us into composing mode. Committing text in this state will replace
					//the composing text. Finish composing to prevent. Might confuse swiftkey state?
					currentConnection.finishComposingText();
					currentConnection.commitText(text,1);
				}
			}
			catch (IllegalAccessException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void refreshEmojiTheme()
	{
		if (mOuterTabsWrapper != null)
		{
			mOuterTabsWrapper.setBackgroundColor(SharedTheme.getSwiftkeyThemeAccentColor());
		}
	}

	//Will usually use panel style rather than item style
	public static void handleEmojiClick(DB_EmojiItem item, int style, boolean sourceIsRecentView)
	{
		inputText(item.get_text());

		if (!sourceIsRecentView)
		{
			DB_EmojiItem newItem = new DB_EmojiItem(item);
			newItem.set_style(style);

			addRecentItem(item);
		}
	}

	public static void addRecentItem( DB_EmojiItem item)
	{
		if (mEmojiPanelRecentsTabIndex != -1)
		{
			EmojiPanelView recentsView = (EmojiPanelView)mEmojiPanelPager.getPageAtPosition(mEmojiPanelRecentsTabIndex);
			DB_EmojiPanelItem recentsItem = mPanelItems.get(mEmojiPanelRecentsTabIndex);

			if (recentsView == null)
			{
				if (recentsItem != null)
				{
					int existingItem = recentsItem.findExistingItemIndex(item);
					if (existingItem != -1)
					{
						recentsItem.get_items().remove(existingItem);
						recentsItem.get_items().add(0,item);
					}
					else
					{
						recentsItem.get_items().add(0,item);
						recentsItem.trimItems(RECENTS_COUNT);
					}
				}
				else
				{
					Log.e(LOGTAG, "Could not obtain either the recents view nor its item. Index was: "+mEmojiPanelRecentsTabIndex);
				}
			}
			else
			{
				int existingItem = recentsView.getPanelItem().findExistingItemIndex(item);

				if (existingItem != -1)
				{
					recentsView.moveItem(existingItem, 0);
				}
				else
				{
					recentsView.addItem(0,item);
					recentsView.trimItems(RECENTS_COUNT);
				}
			}
		}
	}

	public static void loadEmoji()
	{
		if (mLastUpdateTime < Settings.LAST_EMOJI_UPDATE || Settings.changed_EMOJI_TEXT_SIZE)
		{
			mLastUpdateTime = Settings.LAST_EMOJI_UPDATE;

			if (Settings.changed_EMOJI_TEXT_SIZE)
			{
				Context context = getHookContext();
				EmojiResources.setEmojiTextSize(context, Settings.EMOJI_TEXT_SIZE);

				//Forces everything to be reloaded, sizes don't update otherwise.
				//mEmojiPanelAdapter = null;
				//mEmojiPanelPager = null;
				//mEmojiPanelTabs = null;
				//mRecentEmoji = null;

			}

			//Should presist somewhere instead
			WrappedProvider dbWrap = Provider.getWrapped(getHookContext());

			ArrayList<DB_EmojiPanelItem> panels = (ArrayList<DB_EmojiPanelItem> ) DatabaseMethods.getAllItems(dbWrap, TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO);

			mPanelItems.clear();
			mPanelItems.addAll(panels);

			Collections.sort( mPanelItems, new EmojiPanelItem.EmojiPanelComparator() );

			mEmojiPanelRecentsTabIndex = -1;
			{
				int iterator = 0;
				for (DB_EmojiPanelItem item : mPanelItems)
				{
					if (item.get_source() == EmojiPanelItem.PANEL_SOURCE.RECENTS)
					{
						//Recents is the only panel we will ever update from swiftkey.
						//Normally this would be fast enough to do as the operation is performed,
						//but in swiftkey-space we're dealing with a cursor rather than a direct
						//database connection. Sync will manually be called on a separate thread
						//at an opportune time.
						item.get_items().setDatabaseMode(TableList.DatabaseMode.MANUAL);
						mEmojiPanelRecentsTabIndex = iterator;
						break;
					}

						iterator++;
				}
			}


			//Only clear if changed.
			EmojiCache.clearCache();
			com.mayulive.swiftkeyexi.main.emoji.EmojiCommons.preRenderPanels( getHookContext(), mPanelItems );

			if (mEmojiPanelAdapter != null)
				mEmojiPanelAdapter.notifyDataSetChanged();
		}
	}

/*
	//Now reundant
	public static void loadRecents()
	{
		if (mRecentEmoji == null)
		{
			WrappedProvider dbWrap = Provider.getWrapped(getHookContext());

			List<DB_EmojiItem> recents = (ArrayList<DB_EmojiItem> ) DatabaseMethods.getAllItems(dbWrap, TableInfoTemplates.RECENT_EMOJI_TABLE_INFO);
			mRecentEmoji = new DB_EmojiPanelItem(-1, 0, 0, "", "", 0, 0);
			mRecentEmoji.get_items().clear();
			mRecentEmoji.get_items().addAll(recents);
		}
	}
*/

	public static void saveRecents()
	{
		if (mEmojiPanelRecentsTabIndex != -1)
		{
			DB_EmojiPanelItem recentsItem = mPanelItems.get(mEmojiPanelRecentsTabIndex);
			if (recentsItem != null)
			{

				if (recentsItem.get_items().isConnected())
				{
					//recentsItem.get_items().processPendingOperations();

					//There are only 32 items, this is easier than keeping track of indices.
					recentsItem.get_items().replaceTableWithContents();
				}
				else
				{
					Log.e(LOGTAG, "Attempted to save recent emoji, but panel table was not connected to database");
				}

			}
		}
	}
}
