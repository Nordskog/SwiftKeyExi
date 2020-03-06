package com.mayulive.swiftkeyexi.main.popupkeys;

import android.content.Context;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.popupkeys.data.PopupParentKeyItem;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupParentKeyItem;

import java.util.ArrayList;

public class PopupkeysAdapter extends BaseAdapter
{
	private ArrayList<DB_PopupParentKeyItem> mItems = null;
	private LayoutInflater mInflater = null;

	PopupkeysPositionWidget.OnKeyClickedListener mListener = null;
	PopupkeysPositionWidget.OnKeyClickedListener mParentListener = null;
	PopupkeysPositionWidget.OnKeyClickedListener mAddClickedListener = null;

	//Input array must always be sorted.
	public PopupkeysAdapter(Context context, ArrayList<DB_PopupParentKeyItem> sortedItems)
	{
		mItems = sortedItems;
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		if ( position < mItems.size())
			return mItems.get(position).get_id();
		else
			return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		{
			final DB_PopupParentKeyItem entry = mItems.get(position);

			if (convertView == null)
			{
				convertView = mInflater.inflate(R.layout.popup_key_list_item, parent,false);
			}

			Button headerTitle = (Button)convertView.findViewById(R.id.popupkey_parent_key);
			TextView deleteExistingText = (TextView)convertView.findViewById(R.id.delete_existing_textview);

			headerTitle.setText( entry.get_parentKey() );

			if (entry.get_delete_existing())
			{
				deleteExistingText.setText(R.string.popups_parent_delete_existing_short);
			}
			else
			{
				deleteExistingText.setText(R.string.popups_parent_keep_existing_short);
			}

			headerTitle.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (mParentListener != null)
					{
						mParentListener.onKeyClicked(-1, entry);
					}
				}
			});

			PopupkeysPositionWidget widget = (PopupkeysPositionWidget)convertView.findViewById(R.id.popupkey_position_widget);
			//widget.setDisplayMode(PopupkeysPositionWidget.WidgetMode.DISPLAY);

			widget.setFromParent(entry);

			widget.setOnKeyClickedListener(new PopupkeysPositionWidget.OnKeyClickedListener()
			{
				@Override
				public void onKeyClicked(int position, PopupParentKeyItem item)
				{
					if (mListener != null)
					{
						mListener.onKeyClicked(position, entry);
					}
				}
			});

			widget.setOnAddKeyClickedListener(new PopupkeysPositionWidget.OnKeyClickedListener()
			{
				@Override
				public void onKeyClicked(int position, @Nullable PopupParentKeyItem parentItem)
				{
					if (mAddClickedListener != null)
					{
						mAddClickedListener.onKeyClicked(-1, entry);
					}
				}
			});

			return convertView;
		}

	}

	public void setOnKeyClickedListener(PopupkeysPositionWidget.OnKeyClickedListener listener )
	{
		mListener = listener;
	}

	public void setOnParentKeyClickedListener(PopupkeysPositionWidget.OnKeyClickedListener listener )
	{
		mParentListener = listener;
	}

	public void setOnAddKeyClickedListener(PopupkeysPositionWidget.OnKeyClickedListener listener )
	{
		mAddClickedListener = listener;
	}

}
