package com.mayulive.swiftkeyexi.xposed.emoji;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.main.emoji.EmojiPanelView;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiModifiers;
import com.mayulive.swiftkeyexi.util.ContextUtils;
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
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.style.StyleCommons;

import java.util.ArrayList;
import java.util.Collections;

import static com.mayulive.swiftkeyexi.util.ContextUtils.getHookContext;

/**
 * Created by Roughy on 12/26/2016.
 */

public class EmojiHookCommons
{
	private static String LOGTAG = ExiModule.getLogTag(EmojiHookCommons.class);

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

	//Will usually use panel style rather than item style
	@SuppressLint("MissingPermission")
	public static void handleEmojiClick(DB_EmojiItem item, int style, boolean sourceIsRecentView)
	{
		String text = item.get_text();
		if (item.get_modifiers_supported())
		{
			text = EmojiModifiers.applyModifier( text, EmojiResources.getDefaultDiverseModifier() );
		}

		KeyboardMethods.inputText(text);

		// In other instances we respect swiftkey's key press vibrate option.
		// We already have a setting for this though, so we'll just leave it.
		if (Settings.EMOJI_TAP_VIBRATE)
		{
			Vibrator v = (Vibrator) ContextUtils.getHookContext().getSystemService(Context.VIBRATOR_SERVICE);
			v.vibrate(25);
		}


		if (!sourceIsRecentView)
		{
			DB_EmojiItem newItem = new DB_EmojiItem(item);
			newItem.set_last_change( System.currentTimeMillis()  );
			newItem.set_style(style);

			try
			{
				//TableLists are now in immediate mode, let's make sure we don't take down everything.
				addRecentItem(newItem);
			}
			catch ( Throwable ex)
			{
				Log.e(LOGTAG, "Problem modifying recent emoji");
				ex.printStackTrace();
			}

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
					recentsView.getPanelItem().get_items().get(existingItem).set_last_change( item.get_last_change() );
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
		if (mLastUpdateTime < Settings.LAST_EMOJI_UPDATE || Settings.changed_EMOJI_TEXT_RESOURCE)
		{
			mLastUpdateTime = Settings.LAST_EMOJI_UPDATE;

			Context context = getHookContext();
			EmojiResources.setEmojiTextSize(context, Settings.EMOJI_TEXT_SIZE);
			EmojiResources.setDefaultDiverseModifier(Settings.EMOJI_DEFAULT_DIVERSE_MODIFIER);


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
						//Used to be manual update but are now immediate.
						//Reconsider depending on performance.
						item.get_items().setDatabaseMode(TableList.DatabaseMode.IMMEDIATE);
						mEmojiPanelRecentsTabIndex = iterator;

						//Sort by last change time descending
						Collections.sort( item.get_items(), (a, b) -> (int)( b.get_last_change() - a.get_last_change() ) );

					}
					else if ( item.get_source() == EmojiPanelItem.PANEL_SOURCE.USER)
					{
						//Sort by last change time ascending. Reusing for general order.
						Collections.sort( item.get_items(), (a, b) -> (int)( a.get_last_change() - b.get_last_change() ) );
					}

						iterator++;
				}
			}


			//Only clear if changed.
			EmojiCache.clearCache();

			//Let's not do this anymore
			//com.mayulive.swiftkeyexi.main.emoji.EmojiCommons.preRenderPanels( getHookContext(), mPanelItems );

			if (mEmojiPanelAdapter != null)
				mEmojiPanelAdapter.notifyDataSetChanged();
		}
	}

}
