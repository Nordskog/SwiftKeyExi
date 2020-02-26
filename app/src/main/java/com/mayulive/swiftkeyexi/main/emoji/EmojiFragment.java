package com.mayulive.swiftkeyexi.main.emoji;

import com.mayulive.swiftkeyexi.EmojiCache.EmojiCache;
import com.mayulive.swiftkeyexi.EmojiCache.EmojiResources;
import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.database.WrappedDatabase;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiModifiers;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.util.DimenUtils;
import com.mayulive.swiftkeyexi.util.TextUtils;
import com.mayulive.swiftkeyexi.util.view.FixedViewPager;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.emoji.data.EmojiPanelItem;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiPanelItem;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.main.commons.PopupLinearLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.Space;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

import static android.content.ContentValues.TAG;
import static com.mayulive.swiftkeyexi.main.emoji.EmojiFragment.EmojiPanelType.DEFAULT;

public class EmojiFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener
{

	private static String LOGTAG = ExiModule.getLogTag(EmojiFragment.class);

	WrappedDatabase mDbWrap = null;

	View mRootView = null;

	EmojiGarbageView mEmojiDropGarbage;

	EmojiDropIndicator mKeyboardDragCover;

	EmojiPanelPagerAdapter mKeyboardPagerAdapter = null;
	FixedViewPager mKeyboardPager = null;

	EmojiPanelPagerAdapter mDictionaryPagerAdapter = null;
	FixedViewPager mDictionaryPager = null;


	TableList<DB_EmojiPanelItem> mDictionaryPanels = null;
	TableList<DB_EmojiPanelItem> mKeyboardPanels = null;

	FloatingActionButton dictionaryPanelMenuButton = null;

	EmojiPanelTabLayout mDictionaryTabIndicator = null;
	EmojiPanelTabLayout mKeyboardTabIndicator = null;

	EmojiPanelInfoView mDictionaryInfoView;
	EmojiPanelInfoView mKeyboardInfoView;


	boolean mIconPickMode = false;
	EmojiPanelType mIconPickModeTarget = DEFAULT;
	int mIconPickModePosition = -1;

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		if (key.endsWith(PreferenceConstants.pref_emoji_text_size_key))
		{
			updateTextResources();
		}
	}

	public enum EmojiPanelType
	{
		DEFAULT, KEYBOARD, DICTIONARY
	}

	private void setupReferences()
	{
		mDbWrap = DatabaseHolder.getWrapped(this.getContext());
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setupReferences();
		loadEmoij();
		updateTextResources();
	}

	private void loadEmoij()	{

		if (mDictionaryPanels == null || mKeyboardPanels == null)
		{
			mDictionaryPanels = new TableList<>(mDbWrap, TableInfoTemplates.EMOJI_DICTIONARY_PANEL_TABLE_INFO);
			mKeyboardPanels = new TableList<>(mDbWrap, TableInfoTemplates.EMOJI_KEYBOARD_PANEL_TABLE_INFO);

			sortAndValidatePanelIndices(mDictionaryPanels,true);
			sortAndValidatePanelIndices(mKeyboardPanels,true);

			///Find and sort the recents panel
			for (DB_EmojiPanelItem panel : mKeyboardPanels)
			{
				if (panel.get_source() == EmojiPanelItem.PANEL_SOURCE.RECENTS)
				{
					//Sort by last change time descending
					Collections.sort( panel.get_items(), (a, b) -> (int)( b.get_last_change() - a.get_last_change() ) );
				}
				else if ( panel.get_source() == EmojiPanelItem.PANEL_SOURCE.USER)
				{
					//Sort by last change time ascending. Reusing for general order.
					Collections.sort( panel.get_items(), (a, b) -> (int)( a.get_last_change() - b.get_last_change() ) );
				}
			}
		}

		boolean syncDict = mDictionaryPanels.sync();
		boolean syncKeyboard = mKeyboardPanels.sync();
		if (syncDict || syncKeyboard)
		{
			sortAndValidatePanelIndices(mDictionaryPanels,true);
			sortAndValidatePanelIndices(mKeyboardPanels,true);

			if (mKeyboardPagerAdapter != null)
				mKeyboardPagerAdapter.notifyDataSetChanged();
			if (mDictionaryPagerAdapter != null)
				mDictionaryPagerAdapter.notifyDataSetChanged();

		}
	}

	private void updateTextResources()
	{
		SharedPreferences prefs = SettingsCommons.getSharedPreferences(this.getContext(), SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);
		int emojiTextSize = prefs.getInt(PreferenceConstants.pref_emoji_text_size_key, 12);
		String defaultModifier = prefs.getString(PreferenceConstants.pref_emoji_default_diverse_modifier_key, "");
		if (EmojiResources.setEmojiTextSize(this.getContext(),emojiTextSize) || EmojiResources.setDefaultDiverseModifier(defaultModifier) )
		{
			EmojiCache.clearCache();

			//Invalidate panels
			if (mKeyboardPagerAdapter != null && mDictionaryPagerAdapter != null)
			{
				//Let's not do this anymore
				//EmojiCommons.preRenderPanels(getContext(), mDictionaryPanels, mKeyboardPanels);

				mKeyboardPagerAdapter.notifyDataSetChanged();
				mDictionaryPagerAdapter.notifyDataSetChanged();
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		mRootView = inflater.inflate(R.layout.emoji_fragment_layout, container, false);

		mEmojiDropGarbage = mRootView.findViewById( R.id.emoji_garbage_window );

		mKeyboardDragCover = mRootView.findViewById( R.id.emoji_keyboard_drag_cover );

		setupPanels(EmojiPanelType.KEYBOARD);
		setupPanels(EmojiPanelType.DICTIONARY);

		setupMenu();

		// Needs to happen after adapters have spit out the views, so wait a bit.
		new Handler(Looper.getMainLooper()).post( () -> setupCurrentPanelCompanions() );

		return mRootView;
	}


	///////////////
	//Setup
	///////////////

	private void setupCurrentPanelCompanions()
	{
		if (!panelsValid())
			return;

		EmojiPanelView dictionary = getCurrentPanel(EmojiPanelType.DICTIONARY);
		EmojiPanelView keyboard = getCurrentPanel(EmojiPanelType.KEYBOARD);

		// Is no-op if not compatible.
		dictionary.setCompanions( keyboard, mEmojiDropGarbage);
		keyboard.setCompanions(dictionary, mEmojiDropGarbage);

		dictionary.setOnDragEventListener( new EmojiPanelView.OnDragEventListener()
		{
			@Override
			public void onDragStarted()
			{


				if (dictionary.getPanelItem().get_source() == EmojiPanelItem.PANEL_SOURCE.USER)
				{
					mEmojiDropGarbage.setVisible(true);
				}


				if (keyboard.getPanelItem().get_source() == EmojiPanelItem.PANEL_SOURCE.USER)
				{
					mKeyboardDragCover.set( EmojiDropIndicator.CoverType.CAN_DROP, true, false );
				}
				else
				{
					mKeyboardDragCover.set( EmojiDropIndicator.CoverType.CANNOT_DROP, true, false );
				}
			}

			@Override
			public void onDragEnded()
			{
				mEmojiDropGarbage.setVisible(false);
				mKeyboardDragCover.setVisible( false );
			}

			@Override
			public void onCompanionHover(boolean entered)
			{
				mKeyboardDragCover.setActivated( entered );
			}

			@Override
			public void onGarbageHover(boolean entered)
			{
				mEmojiDropGarbage.setActivated(entered);
			}

		});




		keyboard.setOnDragEventListener( new EmojiPanelView.OnDragEventListener()
		{
			@Override
			public void onDragStarted()
			{
				if (keyboard.getPanelItem().get_source() == EmojiPanelItem.PANEL_SOURCE.USER)
				{
					mEmojiDropGarbage.setVisible(true);
				}
				else
				{
					mEmojiDropGarbage.setVisible(false);
				}


			}

			@Override
			public void onDragEnded()
			{
				mEmojiDropGarbage.setVisible(false);
			}

			@Override
			public void onCompanionHover(boolean entered)
			{
				// no-op
			}

			@Override
			public void onGarbageHover(boolean entered)
			{
				mEmojiDropGarbage.setActivated(entered);
			}

		});
	}

	private void setupPanels(final EmojiPanelType type)
	{
		EmojiPanelPagerAdapter pagerAdapter = null;
		FixedViewPager pager = null;
		EmojiPanelTabLayout tabs = null;



		if (type == EmojiPanelType.KEYBOARD)
		{
			mKeyboardInfoView = (EmojiPanelInfoView )mRootView.findViewById(R.id.keyboardPanelInfoView);
			mKeyboardPager = (FixedViewPager) mRootView.findViewById(R.id.keyboardPanelTabs);
			mKeyboardTabIndicator = (EmojiPanelTabLayout) mRootView.findViewById(R.id.keyboardPanelTabsTitles);

			mKeyboardPagerAdapter = new EmojiPanelPagerAdapter(mKeyboardPanels, type, true);

			pagerAdapter = mKeyboardPagerAdapter;
			pager = mKeyboardPager;
			tabs = mKeyboardTabIndicator;
		}
		else
		{
			mDictionaryInfoView = (EmojiPanelInfoView )mRootView.findViewById(R.id.dictionaryPanelInfoView);
			mDictionaryPager = (FixedViewPager) mRootView.findViewById(R.id.dictionaryPanelTabs);
			mDictionaryTabIndicator = (EmojiPanelTabLayout) mRootView.findViewById(R.id.dictionaryPanelTabsTitles);

			mDictionaryPagerAdapter = new EmojiPanelPagerAdapter(mDictionaryPanels, type, true);

			pagerAdapter = mDictionaryPagerAdapter;
			pager = mDictionaryPager;
			tabs = mDictionaryTabIndicator;
		}


		pager.setAdapter(pagerAdapter);

		pagerAdapter.setProvidePageTitles(false);
		pagerAdapter.setupWithFixedTabLayout(tabs);
		tabs.setupWithViewPager(pager);


		tabs.setOnTabLongPressedListener(new EmojiPanelTabLayout.OnTabLongPressedListener()
		{
			@Override
			public void OnTabLongPressed(int position, TabLayout.Tab tab)
			{
				showPanelInfoWidget(type, position, null);

			}
		});


		pagerAdapter.setOnItemClickListener(new EmojiPanelView.OnEmojiItemClickListener()
		{
			@Override
			public void onClick(DB_EmojiItem item, View view, EmojiPanelView panelView, DB_EmojiPanelItem panel, int position)
			{
				handleEmojiItemClick(type, panelView, view, item, position);
			}

			@Override
			public void onLongPress(DB_EmojiItem item, View view, EmojiPanelView panelView, DB_EmojiPanelItem panel, int position)
			{
				handleEmojiItemLongPress(type, panelView, view, item, position);
			}

		});

		pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}
			@Override

			public void onPageSelected(int position)
			{
				setupCurrentPanelCompanions();
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

	}

	private void setupMenu()
	{
		dictionaryPanelMenuButton = (FloatingActionButton) mRootView.findViewById(R.id.dictionaryPanelMenuButton);
		dictionaryPanelMenuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				//Dismiss anything already open
				mKeyboardInfoView.dismissSilently();
				mDictionaryInfoView.dismissSilently();


				EmojiPanelView dictionarView = getCurrentPanel(EmojiPanelType.DICTIONARY);
				EmojiPanelView keyboardView = getCurrentPanel(EmojiPanelType.KEYBOARD);

				///////////////////////////
				//Dictionary menu setup
				///////////////////////////

				final PopupLinearLayout dictionaryPopup = new PopupLinearLayout(dictionaryPanelMenuButton.getContext());
				NavigationView dictionaryMenuView = new NavigationView(dictionaryPanelMenuButton.getContext());
				dictionaryMenuView.inflateMenu(R.menu.emoji_dictionary_popup_menu);

				{
					//Add margin so there's space to display the shadow. PopupLinearLayout is supposed to be elevated, but doesn't work.
					//So the shadow needs to be displayed by the navigation view /inside/ the popup
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

					//Should be enough
					float shadowMargin = DimenUtils.calculatePixelFromDp(EmojiFragment.this.getContext(), 30);

					params.setMargins((int)shadowMargin,0,0, (int)shadowMargin);
					dictionaryPopup.addItem(dictionaryMenuView, params);

					//Compensate for margin of both menus
					dictionaryPopup.setOffset( 0, (int)(shadowMargin/2f) );
				}


				//A template item may not be edited, that means no adding items, deleting, or editing the icon
				//Also no deleting panels that don't exist

				if (dictionarView == null || !dictionarView.getPanelItem().get_source().isEditable())
				{
					dictionaryMenuView.getMenu().removeItem(R.id.column_add_single_emoji);
					dictionaryMenuView.getMenu().removeItem(R.id.delete_menu_item);
				}

				if (keyboardView == null || !keyboardView.getPanelItem().get_source().isEditable())
				{
					//Cannot edit current keyboard panel, remove relevant menu items
					dictionaryMenuView.getMenu().removeItem(R.id.copy_emoji_to_panel_menu_item);
				}

				Space space = new Space(getContext());
				space.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dictionaryPanelMenuButton.getMeasuredHeight() / 2 ));
				dictionaryPopup.addItem(space);

				/////////////////////////
				//Keyboard menu setup
				/////////////////////////

				NavigationView keyboardMenuView = new NavigationView(dictionaryPanelMenuButton.getContext());
				keyboardMenuView.inflateMenu(R.menu.emoji_keyboard_popup_menu);

				{
					//Add margin so there's space to display the shadow. PopupLinearLayout is supposed to be elevated, but doesn't work.
					//So the shadow needs to be displayed by the navigation view /inside/ the popup
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

					//Should be enough
					float shadowMargin = DimenUtils.calculatePixelFromDp(EmojiFragment.this.getContext(), 30);

					params.setMargins((int)shadowMargin,0,0, (int)shadowMargin);
					dictionaryPopup.addItem(keyboardMenuView, params);
				}

				//No deleting panels that don't exist, or recents
				if (keyboardView == null || !keyboardView.getPanelItem().get_source().isKeyboardDeleteable())
				{
					keyboardMenuView.getMenu().removeItem(R.id.delete_menu_item);
				}

				//and no clearing stock panels
				if ( keyboardView == null || !keyboardView.getPanelItem().get_source().isKeyboardClearable() )
				{
					keyboardMenuView.getMenu().removeItem(R.id.clear_menu_item);
				}

				//Recent has dynamic row width
				if ( keyboardView == null || keyboardView.getPanelItem().get_source() == EmojiPanelItem.PANEL_SOURCE.RECENTS)
				{
					keyboardMenuView.getMenu().removeItem(R.id.column_size_menu_item);
				}



				////////////////////
				//DICITIONARY menu
				////////////////////
				dictionaryMenuView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
				{
					@Override
					public boolean onNavigationItemSelected(@NonNull MenuItem item)
					{
						switch(item.getItemId())
						{
							default:
								handleCommonMenuItems(item,EmojiPanelType.DICTIONARY);
						}

						dictionaryPopup.dismiss();
						//keyboardPopup.dismiss();

						return true;

					}
				});


				////////////////////
				//KEYBOARD menu
				////////////////////
				keyboardMenuView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(@NonNull MenuItem item)
					{


						switch(item.getItemId())
						{
							default:
								handleCommonMenuItems(item,EmojiPanelType.KEYBOARD);
						}

						dictionaryPopup.dismiss();
						//keyboardPopup.dismiss();

						return true;

					}
				});

				dictionaryPopup.showCenteredOn(dictionaryPanelMenuButton,space);
			}
		});

	}

	///////////////
	//Dialogs, menus, and interactions
	//////////////

	void showPaneldeleteConfirmationDialog(final EmojiPanelType panelType)
	{
		if (panelValid(panelType))
		{
			new AlertDialog.Builder(getContext())

					.setTitle(R.string.emoji_delete_panel)
					.setMessage(R.string.action_warning_message)
					.setPositiveButton(R.string.button_delete, new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialogInterface, int i)
						{
							deletePanel(panelType);
							if (panelType == EmojiPanelType.KEYBOARD && panelValid(EmojiPanelType.DICTIONARY))
							{
								setLastUpdateTime();
							}
						}
					})
					.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialogInterface, int i)
						{

						}
					})
					.show();
		}
	}

	void showPanelClearConfirmationDialog(final EmojiPanelType panelType)
	{
		if (panelValid(panelType))
		{
			new AlertDialog.Builder(getContext())

					.setTitle(R.string.emoji_clear_panel)
					.setMessage(R.string.action_warning_message)
					.setPositiveButton(R.string.button_clear, new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialogInterface, int i)
						{
							getCurrentPanel(panelType).clear();
							if (panelType == EmojiPanelType.KEYBOARD && panelValid(EmojiPanelType.DICTIONARY))
							{
								setLastUpdateTime();
							}
						}
					})
					.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialogInterface, int i)
						{

						}
					})
					.show();
		}
	}

	private void handleCommonMenuItems(MenuItem item, EmojiPanelType panelType)
	{
		switch(item.getItemId())
		{
			case R.id.new_menu_item:
			{
				addPanel(panelType, null);
				break;
			}

			case R.id.copy_panel_to_keyboard_menu_item:
			{
				addPanel(EmojiPanelType.KEYBOARD, getCurrentPanel(EmojiPanelType.DICTIONARY).getPanelItem());
				break;
			}

			case R.id.column_size_menu_item:
			{

				showColumnWidthWidget(panelType);
				break;
			}

			case R.id.load_menu_item:
			{

				showLoadEmojiFromFileDialog();
				break;
			}

			case R.id.copy_emoji_to_panel_menu_item:
			{

				addAllDictionaryPanelToKeyboardPanel();
				break;
			}

			case R.id.clear_menu_item:
			{
				showPanelClearConfirmationDialog(panelType);
				break;
			}

			case R.id.delete_menu_item:
			{
				showPaneldeleteConfirmationDialog(panelType);
				break;
			}

			case R.id.column_add_single_emoji:
			{
				new InputEmojiDialog(this.getContext())
				{
					@Override
					public void onEntrySaved(String input)
					{
						addSingle(input, EmojiPanelType.DICTIONARY);
					}
				}.show();
				break;
			}
		}
	}


	private void handleEmojiItemLongPress(EmojiPanelType sourcePanelType, EmojiPanelView sourcePanelView, View view, DB_EmojiItem item, int position)
	{

		if(!mIconPickMode)
		{

			if ( sourcePanelView.getPanelItem().get_source().isEditable() )
			{
				sourcePanelView.removeItem(position);
			}
			else
			{
				EmojiDialogCommons.displayUneditableWarning(this.getContext());
			}

			setLastUpdateTime();
		}
		else
		{
			handleEmojiItemClick(sourcePanelType,sourcePanelView,view,item,position);
		}
	}


	private void handleEmojiItemClick(EmojiPanelType sourcePanelType, EmojiPanelView sourcePanelView, View clickedView, DB_EmojiItem item, int position)
	{

		if(!mIconPickMode)
		{
			switch(sourcePanelType)
			{
				case KEYBOARD:
				{
					if (item.get_modifiers_supported())
					{
						//Display default diverse modifier popup
						EmojiModifiersPopup popup = new EmojiModifiersPopup(clickedView.getContext(), item.get_text());
						AlertDialog dialog = popup.showInDialog(popup);
						popup.setOnEmojiClickedListener(new EmojiModifiersPopup.OnEmojiClickedListener()
						{
							@Override
							public void onEmojiClicked(String emoji, String modifier)
							{
								SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(EmojiFragment.this.getContext(), SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);
								editor.putString(PreferenceConstants.pref_emoji_default_diverse_modifier_key, modifier);
								editor.apply();

								updateTextResources();

								dialog.dismiss();
							}
						});
					}

					break;
				}
				case DICTIONARY:
				{
					if (panelsValid() )
					{
						EmojiPanelView currentKeyboardPanelView = getCurrentPanel(EmojiPanelType.KEYBOARD);

						if (currentKeyboardPanelView.getPanelItem().get_source().isEditable())
						{
							currentKeyboardPanelView.addItem(new DB_EmojiItem( item ) );
							currentKeyboardPanelView.scrollToEnd();
						}
						else
						{
							EmojiDialogCommons.displayUneditableWarning(this.getContext());
						}
					}

					break;
				}
			}
			setLastUpdateTime();

		}
		else
		{
			if (mIconPickModePosition != -1)
			{
				String icon = item.get_text();
				icon = icon.replace("\uFE0F", "");

				//showChangePanelIconDialog(mIconPickModeTarget, item.getContainer().getText());
				showPanelInfoWidget(mIconPickModeTarget, mIconPickModePosition, icon);
			}

			mIconPickMode = false;
			mIconPickModeTarget = DEFAULT;
			mIconPickModePosition = -1;
		}
	}



	private void showPanelInfoWidget(final EmojiPanelType panelType, int pos, @Nullable String newIcon)
	{

		//*sigh*
		//I miss primitive references. I'd even take pointers.
		class IntWrapper
		{
			int value = 0;

			public IntWrapper(int val)
			{
				value = val;
			}
		}

		final IntWrapper position = new IntWrapper(pos);

		//Dismiss anything already open
		mKeyboardInfoView.dismissSilently();
		mDictionaryInfoView.dismissSilently();


		final TableList<DB_EmojiPanelItem> items = 	panelType == EmojiPanelType.KEYBOARD ? mKeyboardPanels			: mDictionaryPanels;
		final EmojiPanelInfoView infoView = 		panelType == EmojiPanelType.KEYBOARD ? mKeyboardInfoView 		: mDictionaryInfoView;
		final EmojiPanelPagerAdapter adapter = 		panelType == EmojiPanelType.KEYBOARD ? mKeyboardPagerAdapter	: mDictionaryPagerAdapter;
		final EmojiPanelTabLayout tabLayout = 			panelType == EmojiPanelType.KEYBOARD ? mKeyboardTabIndicator 	: mDictionaryTabIndicator;


		//Select the tab
		TabLayout.Tab tab = tabLayout.getTabAt(position.value);
		if (tab == null)
			return;
		tab.select();

		DB_EmojiPanelItem item = items.get(position.value);

		if (newIcon != null)
			infoView.setIcon(newIcon, item.get_icon_style());
		else
			infoView.setIcon( item.get_icon(), item.get_icon_style() );

		infoView.setOnLeftShiftListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				if (position.value > 0 )
				{

					Collections.rotate(items.subList(position.value-1, position.value+1), -1);
					sortAndValidatePanelIndices(items, false);
					tabLayout.getTabAt(position.value-1).select();

					position.value--;

					adapter.notifyDataSetChanged();
					setLastUpdateTime();
				}
			}
		});

		infoView.setOnRightShiftListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (items.size() > 1 && position.value < items.size()-1 )
				{
					Collections.rotate(items.subList(position.value, position.value+2), -1);
					sortAndValidatePanelIndices(items, false);
					tabLayout.getTabAt(position.value+1).select();

					position.value++;

					adapter.notifyDataSetChanged();
					setLastUpdateTime();
				}
			}
		});

		infoView.setOnPickButtonClickedListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mIconPickModePosition = position.value;
				mIconPickMode = true;
				mIconPickModeTarget = panelType;

				infoView.dismissSilently();
			}
		});

		infoView.setOnDismissedListener(new EmojiPanelInfoView.OnEmojiPanelInfoViewDismissedListener()
		{
			@Override
			public void onDismiss(String newIcon, int style)
			{
				EmojiPanelView view = getCurrentPanel(panelType);

				view.setPanelIcon(newIcon);
				view.getPanelItem().set_icon_style(style);
				items.update(view.getPanelItem());
				adapter.notifyDataSetChanged();
				setLastUpdateTime();

			}
		});

		infoView.setIconEditability( item.get_source().isEditable() );

		infoView.show();
	}

	private void showColumnWidthWidget(final EmojiPanelType panelType)
	{

		if ( panelValid(panelType))
		{
			final EmojiPanelView panelView = getCurrentPanel(panelType);
			final EmojiColumnWidthConfigView configView = new EmojiColumnWidthConfigView(EmojiFragment.this.getActivity(), panelView.getColumnWidth() , 0,99);

			configView.getNumberPicker().setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
			{
				@Override
				public void onValueChange(NumberPicker numberPicker, int i, int i1)
				{
					panelView.setColumnWidth(i1);
				}
			});

			configView.getPopup().setOnDismissListener(new PopupWindow.OnDismissListener()
			{
				@Override
				public void onDismiss()
				{
					updatePanel(panelView, panelType);
				}
			});

			switch(panelType)
			{
				case KEYBOARD:
				{
					configView.showBelow(dictionaryPanelMenuButton);
					break;
				}
				case DICTIONARY:
				{
					configView.showAbove(dictionaryPanelMenuButton);
					break;
				}
			}
		}
	}

	/////////////
	//Misc
	/////////////

	private void setLastUpdateTime()
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext(), SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);
		editor.putLong(PreferenceConstants.pref_emoji_last_update_key, System.currentTimeMillis());
		editor.apply();
	}

	/////////////
	//Entry and item shenanigans
	/////////////

	private void sortAndValidatePanelIndices(TableList<DB_EmojiPanelItem> items, boolean sort)
	{
		if (sort)
			Collections.sort( items, new EmojiPanelItem.EmojiPanelComparator() );

		items.startBatchEdit();
		for (int i = 0; i < items.size(); i++)
		{
			items.get(i).set_index(i);
			items.performDbOperation(TableList.DatabaseOperation.UPDATE, i, false);
		}
		items.endBatchEdit();
	}

	private void updatePanel(EmojiPanelView panelView, EmojiPanelType panelType)
	{
		switch(panelType)
		{
			case KEYBOARD:
			{
				mKeyboardPanels.update(panelView.getPanelItem());
				break;
			}
			case DICTIONARY:
			{
				mDictionaryPanels.update(panelView.getPanelItem());
				break;
			}
		}

		setLastUpdateTime();
	}

	int getCurrentPanelIndex(EmojiPanelType panelType)
	{
		switch(panelType)
		{
			case KEYBOARD:
			{
				return  mKeyboardPager.getCurrentItem();
			}
			case DICTIONARY:
			{
				return mDictionaryPager.getCurrentItem();
			}
		}

		return -1;
	}

	void setPanelIcon(EmojiPanelType panelType, String icon)
	{
		if (panelValid(panelType))
		{
			switch(panelType)
			{
				case KEYBOARD:
				{
					mKeyboardPagerAdapter.getCurrentView().setPanelIcon(icon);
					updatePanel(mKeyboardPagerAdapter.getCurrentView(), EmojiPanelType.KEYBOARD);
					mKeyboardPagerAdapter.notifyDataSetChanged();
					break;

				}
				case DICTIONARY:
				{
					mDictionaryPagerAdapter.getCurrentView().setPanelIcon(icon);
					updatePanel(mDictionaryPagerAdapter.getCurrentView(), EmojiPanelType.DICTIONARY);
					mDictionaryPagerAdapter.notifyDataSetChanged();
					break;
				}
			}

			setLastUpdateTime();
		}


	}

	void addAllDictionaryPanelToKeyboardPanel()
	{
		EmojiPanelView sourcePanel = getCurrentPanel(EmojiPanelType.DICTIONARY);
		EmojiPanelView destionationPanel = getCurrentPanel(EmojiPanelType.KEYBOARD);

		if (panelsValid())
		{
			int destionationSize = destionationPanel.getPanelItem().get_items().size();

			destionationPanel.addAll( sourcePanel.getPanelItem().get_items() );
			//If the destionation panel was empty, let's also copy the icon, and width
			if (destionationSize == 0)
			{
				destionationPanel.getPanelItem().set_icon_style( sourcePanel.getPanelItem().get_icon_style() );
				destionationPanel.getPanelItem().set_column_width( sourcePanel.getPanelItem().get_column_width() );
				setPanelIcon(EmojiPanelType.KEYBOARD, sourcePanel.getPanelIcon());
			}

			//updatePanel(destionationPanel, EmojiPanelType.KEYBOARD);
			setLastUpdateTime();
		}

	}

	void addSingle(String string, EmojiPanelType type)
	{
		EmojiPanelView destionationPanel = getCurrentPanel(type);

		if (destionationPanel != null)
		{
			if (destionationPanel.getPanelItem().get_source().isEditable())
			{
				DB_EmojiItem newItem = new DB_EmojiItem(string);
				newItem.set_modifiers_supported( EmojiModifiers.supportsModifiers(newItem.get_text()) );

				destionationPanel.addItem( newItem );
				setLastUpdateTime();
			}
		}
	}

	EmojiPanelView getCurrentPanel(EmojiPanelType panelType)
	{
		switch(panelType)
		{
			case KEYBOARD:
			{
				return (EmojiPanelView) mKeyboardPager.getCurrentPage();
			}
			case DICTIONARY:
			{
				return (EmojiPanelView) mDictionaryPager.getCurrentPage();
			}
		}

		return null;
	}

	boolean panelValid(EmojiPanelType panelType)
	{
		return getCurrentPanel(panelType) != null;
	}

	boolean panelsValid()
	{
		return getCurrentPanel(EmojiPanelType.DICTIONARY) != null && getCurrentPanel(EmojiPanelType.KEYBOARD) != null;
	}

	DB_EmojiPanelItem addPanel(EmojiPanelType panelType, DB_EmojiPanelItem copyFrom)
	{
		//I changed my mind, all panels will now use the b/w icon style
		DB_EmojiPanelItem newPanel;
		if (copyFrom != null)
			newPanel = new DB_EmojiPanelItem(copyFrom);
		else
			newPanel = new DB_EmojiPanelItem( -1,-1,0,"◯", "◯", 0, 1 );

		switch(panelType)
		{
			case KEYBOARD:
			{
				newPanel.set_index( mKeyboardPanels.size() );
				mKeyboardPanels.add(newPanel);
				mKeyboardPagerAdapter.notifyDataSetChanged();
				mKeyboardPager.setCurrentItem(mKeyboardPanels.size()-1,true);
				break;
			}
			case DICTIONARY:
			{
				newPanel.set_index( mDictionaryPanels.size() );
				mDictionaryPanels.add(newPanel);
				mDictionaryPagerAdapter.notifyDataSetChanged();
				mDictionaryPager.setCurrentItem(mDictionaryPanels.size()-1,true);
				break;
			}
		}

		setLastUpdateTime();

		return newPanel;


	}

	void showLoadEmojiFromFileDialog()
	{
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

		// Filter to only show results that can be "opened", such as a
		// file (as opposed to a list of contacts or timezones)
		intent.addCategory(Intent.CATEGORY_OPENABLE);

		// Filter to show only images, using the image MIME data type.
		// If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
		// To search for all documents available via installed storage providers,
		// it would be "*/*".
		intent.setType("text/plain");

		startActivityForResult(intent, READ_REQUEST_CODE);
	}



	private static final int READ_REQUEST_CODE = 42;

	void deletePanel(EmojiPanelType panelType)
	{
		if (panelValid(panelType))
		{
			DB_EmojiPanelItem panelItem = getCurrentPanel(panelType).getPanelItem();
			int panelIndex = getCurrentPanelIndex(panelType);

			switch(panelType)
			{
				case KEYBOARD:
				{
					mKeyboardPanels.remove(panelIndex);
					mKeyboardPagerAdapter.notifyDataSetChanged();

					EmojiCache.clearCache(panelItem);

					break;
				}
				case DICTIONARY:
				{
					mDictionaryPanels.remove(panelIndex);
					mDictionaryPagerAdapter.notifyDataSetChanged();

					EmojiCache.clearCache(panelItem);

					break;
				}
			}
		}

		setLastUpdateTime();
	}


	void loadEmojiFromFile(Uri filePath)
	{
		DB_EmojiPanelItem newPanel = new DB_EmojiPanelItem( -1,-1,1,"◯", "◯", 0, 1 );

		try
		{

			BufferedReader in = new BufferedReader (new InputStreamReader( getContext().getContentResolver().openInputStream(filePath)) );

			String line = null;

			while ( (line = in.readLine() ) != null)
			{
				newPanel.get_items().add( new DB_EmojiItem( TextUtils.stripBom( line ) ));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		newPanel.updateModifierSupport();

		mDictionaryPanels.add(newPanel);
		mDictionaryPagerAdapter.notifyDataSetChanged();
		mDictionaryPager.setCurrentItem(mDictionaryPanels.size()-1,true);

		setLastUpdateTime();
	}



	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent resultData)
	{

		//this.onPause();	//Why the hell was this here

		// The ACTION_OPEN_DOCUMENT intent was sent with the request code
		// READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
		// response to some other intent, and the code below shouldn't run at all.

		//Resultcode is always -1 ...
		if (requestCode == READ_REQUEST_CODE) // && resultCode == Activity.RESULT_OK)
		{

			// The document selected by the user won't be returned in the intent.
			// Instead, a URI to that document will be contained in the return intent
			// provided to this method as a parameter.
			// Pull that URI using resultData.getData().
			Uri uri = null;
			if (resultData != null)
			{
				uri = resultData.getData();
				Log.i(TAG, "Uri: " + uri.toString());

				loadEmojiFromFile(uri);

			}
			else
			{
				Log.e(LOGTAG, "Could not import Emoji because data in ActivityResult was null");
			}
		}
		else
		{
			super.onActivityResult(requestCode, resultCode, resultData);
		}
	}

	@Override
	public void onPause()
	{
		super.onPause();
		SettingsCommons.getSharedPreferences(this.getContext(), SettingsCommons.MODULE_SHARED_PREFERENCES_KEY).unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onResume()
	{
		updateTextResources();
		loadEmoij();
		super.onResume();
		SettingsCommons.getSharedPreferences(this.getContext(), SettingsCommons.MODULE_SHARED_PREFERENCES_KEY).registerOnSharedPreferenceChangeListener(this);

	}

}