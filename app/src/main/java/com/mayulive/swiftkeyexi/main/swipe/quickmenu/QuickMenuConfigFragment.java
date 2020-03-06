package com.mayulive.swiftkeyexi.main.swipe.quickmenu;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.database.DatabaseHolder;
import com.mayulive.swiftkeyexi.database.DatabaseMethods;
import com.mayulive.swiftkeyexi.database.DatabaseWrapper;
import com.mayulive.swiftkeyexi.database.TableList;
import com.mayulive.swiftkeyexi.main.commons.data.DB_HotkeyMenuItem;
import com.mayulive.swiftkeyexi.main.commons.data.TableInfoTemplates;
import com.mayulive.swiftkeyexi.main.keyboard.HotkeyPanel;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.settings.Settings;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.util.ThemeUtils;
import com.mayulive.swiftkeyexi.util.view.DisableableLinearLayoutManager;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

import java.util.Collections;

/**
 * Created by Roughy on 9/21/2017.
 */

public class QuickMenuConfigFragment extends Fragment
{
	private static String LOGTAG = ExiModule.getLogTag(QuickMenuConfigFragment.class);

	private static float TEXT_SIZE_MIN = 0.01f;
	private static float TEXT_SIZE_MAX = 0.75f;
	private static float mTextSize = 0.15f;
	private static int mHighlightColor = 0xFF2d5bc6;

	DatabaseWrapper mDbWrap = null;

	ViewGroup mRootView = null;
	HotkeyPanel mHotkeyMenuPanel = null;
	RecyclerView mList = null;
	QuickMenuListHelper mHelper = null;
	DisableableLinearLayoutManager mLayoutManager;
	SeekBar mSizeSeekbar;

	QuickMenuListAdapter mAdapter;

	TableList<DB_HotkeyMenuItem> mItems = null;

	private void setupReferences()
	{
		mDbWrap = DatabaseHolder.getWrapped(this.getContext());
	}

	private void loadTextSizePref()
	{
		SharedPreferences prefs = SettingsCommons.getSharedPreferences(this.getContext());
		mTextSize = prefs.getFloat(PreferenceConstants.pref_hotkey_menu_text_size_key, 0.15f);
	}

	private void saveTextSizePref()
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext());
		editor.putFloat(PreferenceConstants.pref_hotkey_menu_text_size_key, mTextSize);
		editor.apply();
	}

	private void loadHighlightColorPref()
	{
		SharedPreferences prefs = SettingsCommons.getSharedPreferences(this.getContext());
		mHighlightColor = prefs.getInt(PreferenceConstants.pref_quick_menu_color_key, 0xFF2d5bc6);
	}

	private void saveHighlightColorPref()
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext());
		editor.putInt(PreferenceConstants.pref_quick_menu_color_key, mHighlightColor);
		editor.apply();
	}

	private void setLastUpdateTime()
	{
		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext(), SettingsCommons.MODULE_SHARED_PREFERENCES_KEY);
		editor.putLong(PreferenceConstants.pref_quickmenu_last_update_key, System.currentTimeMillis());
		editor.apply();
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setupReferences();
		loadTextSizePref();
		loadHighlightColorPref();
		loadItems();
	}

	private void loadItems()
	{
		if (mItems == null)
		{
			mItems = new TableList<>(mDbWrap, TableInfoTemplates.HOTKEY_MENU_ITEMS_TABLE_INFO);
			Collections.sort(mItems, new HotkeyPanel.HotkeyMenuItem.HotkeyMenuItemComparator() );
		}

		if (mItems.sync())
		{
			Collections.sort(mItems, new HotkeyPanel.HotkeyMenuItem.HotkeyMenuItemComparator() );
			if (mAdapter != null)
				mAdapter.notifyDataSetChanged();
			if (mHotkeyMenuPanel != null)
				mHotkeyMenuPanel.refresh();
		}

	}

	private void setupList()
	{

		/////////////////
		//Basic setup
		/////////////////
		mList = (RecyclerView)mRootView.findViewById(R.id.configHolder);

		mLayoutManager = new DisableableLinearLayoutManager(this.getContext());
		mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		mList.setLayoutManager(mLayoutManager);
		mAdapter = new QuickMenuListAdapter(this.getContext(), mItems);
		mList.setAdapter(mAdapter);

		/////////////////
		//Interaction
		/////////////////

		// This listener is responsible for letting us select a view by its handle without long-pressing.
		// We intercept touch events but never take control, as doing so messes up ItemTouchHelper normal behavior.
		// We only handle the drag aspect, as swipe triggers correctly, and doesn't let us start dragging once triggered.
		mList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener()
		{
			float downY = 0;
			boolean eventTriggered = false;
			boolean intercepted = false;
			float yTrigger = 40;
			ViewGroup touchedView = null;

			@Override
			public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
			{
				switch(e.getActionMasked())
				{
					case MotionEvent.ACTION_DOWN:
					{
						//Enable just incase we somehow managed to miss the down event
						mLayoutManager.setScrollEnabled(true);

						{
							View view = rv.findChildViewUnder(e.getX(),e.getY());
							if (view != null && ViewGroup.class.isAssignableFrom( view.getClass() ))
							{
								touchedView = (ViewGroup)view;
							}
						}

						if (touchedView != null)
						{
							//Check if we hit the handle
							//Our handle is on the left side with no padding, so we can just compare the x pos and width
							//Could we just set a clickListener on every item? sure. This is uglier but simpler.
							View handle = touchedView.findViewById(R.id.quick_menu_drag_handle);
							if (handle != null && e.getX() < handle.getMeasuredWidth())
							{
								downY = e.getY();
								intercepted  = true;
								eventTriggered = false;	//For some reason we sometimes get a down event before a move event ...
								mLayoutManager.setScrollEnabled(false);

								//Let's just use a fraction of the view height as the drag trigger threshold
								yTrigger = ( ( (float)touchedView.getMeasuredHeight() ) * 0.1f);
							}
						}

						break;
					}

					case MotionEvent.ACTION_MOVE:
					{
						if (!eventTriggered && intercepted && touchedView != null)
						{
							float yDistance = Math.abs( e.getY() - downY);

							if (yDistance > yTrigger )//|| xDistance > xTrigger)
							{
								RecyclerView.ViewHolder holder = rv.findContainingViewHolder(touchedView);
								eventTriggered = true;
								boolean dragTriggered = yDistance > yTrigger;

								if (dragTriggered && holder != null)
								{
									mHelper.startDrag(holder);
								}
							}

						}

						break;
					}

					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_CANCEL:
					{
						touchedView = null;
						mLayoutManager.setScrollEnabled(true);
						intercepted = false;
						eventTriggered = false;
						break;
					}

				}

				return false;
			}

			@Override
			public void onTouchEvent(RecyclerView rv, MotionEvent e)
			{

			}

			@Override
			public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept)
			{

			}
		});

		////////////////
		//Callbacks
		////////////////


		//Action / Display text changed in list
		mAdapter.setOnHotkeyMenuItemChangedListener(new QuickMenuListAdapter.OnHotkeyMenuItemChangedListener()
		{
			@Override
			public void onHotkeyMenuItemChanged(HotkeyPanel.HotkeyMenuItem item, KeyboardInteraction.TextAction action, String displayText)
			{

				boolean actionChanged = item.action != action;

				item.action = action;
				item.text = displayText;

				if (actionChanged)
				{
					//Changed display text to match action if action changed
					item.text = KeyboardInteraction.TextAction.getShortTextRepresentation(getContext(), item.action);
					mAdapter.notifyItemChanged( mItems.indexOf(item) );
				}

				mHotkeyMenuPanel.refresh();
				mItems.update((DB_HotkeyMenuItem) item);

				setLastUpdateTime();
			}
		});

		//Item moved/removed in list
		ItemTouchHelper.SimpleCallback mHelperCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN , ItemTouchHelper.END)
		{
			@Override
			public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
			{
				int startPos = viewHolder.getAdapterPosition();
				int endPos = target.getAdapterPosition();

				( ( QuickMenuListAdapter.ItemViewHolder ) viewHolder).setIndex(endPos);
				( ( QuickMenuListAdapter.ItemViewHolder ) target).setIndex(startPos);

				Collections.swap(mItems, startPos, endPos);
				mAdapter.notifyItemMoved(startPos, endPos);
				mHotkeyMenuPanel.refresh();

				return true;
			}


			@Override
			public boolean isLongPressDragEnabled() {
				return false;	//Only want to drag by handle
			}

			@Override
			public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
			{
				mItems.remove( ( ( QuickMenuListAdapter.ItemViewHolder )viewHolder ).getItem() );
				mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
				mHotkeyMenuPanel.refresh();

			}

			@Override
			public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
			{
				super.clearView(recyclerView, viewHolder);
				//This can apparently be used to catch drag-ended
				DatabaseMethods.updateAllItems(mDbWrap, mItems, mItems.getTableInfo(),false);
				setLastUpdateTime();
			}

		};
		mHelper = new QuickMenuListHelper(mHelperCallback);
		mHelper.attachToRecyclerView(mList);
	}


	private void setupHotkeyWidget()
	{
		mHotkeyMenuPanel = new HotkeyPanel( mRootView.getContext(), Settings.QUICK_MENU_HIGHLIGHT_COLOR );

		//Defaults to white
		mHotkeyMenuPanel.setPrimaryColor( ThemeUtils.getThemeAttribute( this.getContext(), R.attr.textColor ) );
		mHotkeyMenuPanel.enableConfigMode(true);
		mHotkeyMenuPanel.setItems(mItems);

		mHotkeyMenuPanel.setTextSizeRatio(mTextSize);

		mHotkeyMenuPanel.setOnInteractionEndedListener(new HotkeyPanel.OnInteractionEndedListener()
		{
			@Override
			public void onInteractionEnded()
			{
				mItems.updateAll();
				setLastUpdateTime();
			}
		});

		RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		mHotkeyMenuPanel.setLayoutParams(params);
		ViewGroup widgetHolder = (ViewGroup)mRootView.findViewById(R.id.widgetHolder);
		widgetHolder.addView(mHotkeyMenuPanel);
	}

	private void setupWidgets()
	{
		/////////////////////
		//Textsize seekbar
		//////////////////////

		mSizeSeekbar = (SeekBar)mRootView.findViewById(R.id.textSizeSeekBar);
		int progress =  (int) ( ( (mTextSize - TEXT_SIZE_MIN) / TEXT_SIZE_MAX) * mSizeSeekbar.getMax() );
		mSizeSeekbar.setProgress( progress);
		mSizeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				if (fromUser)
				{
					mTextSize = ( (float)progress / (float)(seekBar.getMax() ) * (TEXT_SIZE_MAX-TEXT_SIZE_MIN) ) + TEXT_SIZE_MIN;
					mHotkeyMenuPanel.setTextSizeRatio(mTextSize);

				}

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				saveTextSizePref();
			}
		});

	}

	private void setupColorPicker()
	{
		final ImageView view = (ImageView)mRootView.findViewById(R.id.hotkeymenu_highlight_color_button);
		view.setColorFilter(mHighlightColor);

		view.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ColorPickerDialogBuilder
						.with(QuickMenuConfigFragment.this.getContext())
						.setTitle(R.string.action_choose_color)
						.initialColor(mHighlightColor)
						.wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
						.density(12)
						.noSliders()
						.setPositiveButton(R.string.button_ok, new ColorPickerClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors)
							{
								mHighlightColor = selectedColor;
								view.setColorFilter(mHighlightColor);
								saveHighlightColorPref();
							}
						})
						.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
							}
						})
						.build()
						.show();
			}
		});
	}

	private void setupButtons()
	{
		View keyboardButton = mRootView.findViewById(R.id.testkeyboardbutton);
		keyboardButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				((QuickMenuActivity)getActivity()).displayInputTest();
			}
		});

		FloatingActionButton addButton = (FloatingActionButton)mRootView.findViewById(R.id.addHotkeyMenuItemButton);
		addButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//Completely arbitrary limit
				if (mItems.size() < 10)
				{
					mItems.add(new DB_HotkeyMenuItem("COPY", KeyboardInteraction.TextAction.COPY, 0.3f, mItems.size()));
					mHotkeyMenuPanel.refresh();
					mAdapter.notifyItemInserted(mItems.size()-1);
				}
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		mRootView = (ViewGroup)inflater.inflate(R.layout.hotkeymenu_config_fragment, container, false);

		setupList();
		setupButtons();
		setupWidgets();
		setupHotkeyWidget();
		setupColorPicker();

		return mRootView;
	}

	public void onResume()
	{
		loadItems();
		super.onResume();
	}
}