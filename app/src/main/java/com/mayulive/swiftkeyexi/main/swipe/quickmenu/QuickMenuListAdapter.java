package com.mayulive.swiftkeyexi.main.swipe.quickmenu;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.keyboard.HotkeyPanel;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Roughy on 9/22/2017.
 */

public class QuickMenuListAdapter extends RecyclerView.Adapter<QuickMenuListAdapter.ItemViewHolder>
{

	List<? extends HotkeyPanel.HotkeyMenuItem> mItems = new ArrayList<>();

	//TODO make this a common resource
	private static final KeyboardInteraction.TextAction[] mDropdownOptions = Arrays.copyOfRange(KeyboardInteraction.TextAction.values(), 1, KeyboardInteraction.TextAction.values().length);
	private String[] sMenuItems = new String[mDropdownOptions.length];

	private OnHotkeyMenuItemChangedListener mChangedListener = null;
	private OnDragStartedListener mDragListener = null;


	public QuickMenuListAdapter(Context context, List<? extends HotkeyPanel.HotkeyMenuItem> items)
	{
		mItems = items;

		{
			for (int i = 0; i < mDropdownOptions.length; i++)
			{
				sMenuItems[i] = KeyboardInteraction.TextAction.getTextRepresentation(context,mDropdownOptions[i]);
			}
		}
	}

	public void setOnHotkeyMenuItemChangedListener(OnHotkeyMenuItemChangedListener listener)
	{
		mChangedListener = listener;
	}

	public void setOnDragStartedListener(OnDragStartedListener listener)
	{
		mDragListener = listener;
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotkeymenuitem_item, parent, false);

		Spinner spinner = (Spinner) view.findViewById(R.id.quick_menu_action_picker);

		final ViewGroup par = parent;

		ArrayAdapter<CharSequence> actionMenuAdapter = new ArrayAdapter<CharSequence>(parent.getContext(), android.R.layout.simple_spinner_dropdown_item, sMenuItems);
		spinner.setAdapter(actionMenuAdapter);

		final ItemViewHolder holder = new ItemViewHolder(view);

		holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if (mChangedListener != null)
				{
					mChangedListener.onHotkeyMenuItemChanged( holder.getItem(), indexToAction(position), holder.getItem().text );
				}
			}
			@Override public void onNothingSelected(AdapterView<?> parent) {}
		});

		holder.displayText.addTextChangedListener(new TextWatcher()
		{
			@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void afterTextChanged(Editable s)
			{
				if (mChangedListener != null)
				{
					mChangedListener.onHotkeyMenuItemChanged( holder.getItem(), holder.getItem().action, s.toString() );
				}
			}
		});

		return holder;



	}

	@Override
	public void onBindViewHolder(ItemViewHolder holder, int position)
	{
		holder.set(mItems.get(position));
		holder.setIndex(position);
	}

	@Override
	public int getItemCount()
	{
		return mItems.size();
	}

	public class ItemViewHolder extends RecyclerView.ViewHolder
	{
		HotkeyPanel.HotkeyMenuItem mItem = null;
		Spinner spinner = null;
		EditText displayText = null;
		TextView displayIndex = null;
		View handle = null;

		public ItemViewHolder(View itemView)
		{
			super(itemView);

			spinner = (Spinner) itemView.findViewById(R.id.quick_menu_action_picker);
			displayText =(EditText)itemView.findViewById(R.id.quick_menu_action_display_text);
			displayIndex = (TextView) itemView.findViewById(R.id.quick_menu_index);
			handle = itemView.findViewById(R.id.quick_menu_drag_handle);
		}

		public HotkeyPanel.HotkeyMenuItem getItem()
		{
			return mItem;
		}

		public void set(HotkeyPanel.HotkeyMenuItem item)
		{
			mItem = item;

			spinner.setSelection(actionToIndex( mItem.action));
			displayText.setText( mItem.text );
		}

		public void setIndex(int index)
		{
			mItem.position = index;
			displayIndex.setText(""+(index+1) );
		}
	}

	// ...
	private static int actionToIndex(KeyboardInteraction.TextAction action)
	{
		for (int i = 0; i < mDropdownOptions.length; i++)
		{
			if (mDropdownOptions[i] == action)
				return i;
		}

		return -1;
	}

	private static KeyboardInteraction.TextAction indexToAction(int index)
	{
		return mDropdownOptions[index];
	}

	public interface OnHotkeyMenuItemChangedListener
	{
		void onHotkeyMenuItemChanged(HotkeyPanel.HotkeyMenuItem item, KeyboardInteraction.TextAction action, String displayText);
	}

	public interface OnDragStartedListener
	{
		void onDragStarted(ItemViewHolder holder);
		void onSwipeStarted(ItemViewHolder holder);
	}


}
