package com.mayulive.swiftkeyexi.main.popupkeys;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mayulive.swiftkeyexi.main.popupkeys.data.PopupKeyItem;
import com.mayulive.swiftkeyexi.main.popupkeys.data.DB_PopupKeyItem;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.popupkeys.data.PopupParentKeyItem;
import com.mayulive.swiftkeyexi.util.ThemeUtils;

import java.util.ArrayList;


/**
 * Created by Roughy on 2/18/2017.
 */

public class PopupkeysPositionWidget extends LinearLayout
{

	LinearLayout mKeyLayout = null;
	Button mAddKeyButton = null;
	Button mCenterButton = null;

	ArrayList<Button> mRowKeys = new ArrayList<>();

	private int mButtonCount = 0;	//Row buttons

	LayoutInflater mInflater = null;

	private float mRowButtonWidth = 0;

	private float mDefaultTextSize = 0;
	private Button mMeasureButton = null;


	private String mSelectionText = "test";
	private Button mSelectionButton = null;

	private int mAccentColor = 0;

	private OnKeyClickedListener mClickListener = null;

	private OnKeyClickedListener mAddClickListener = null;

	private WidgetMode mWidgetMode = WidgetMode.CONFIG;

	public enum WidgetMode
	{
		CONFIG, DISPLAY
	}

	public void setDisplayMode(WidgetMode mode)
	{

		mWidgetMode = mode;


		mAddKeyButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (mWidgetMode == WidgetMode.CONFIG)
				{
					addButton();
				}
				else
				{
					if (mAddClickListener != null)
					{
						mAddClickListener.onKeyClicked(-1, null);
					}
				}

			}
		});

		mCenterButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				setButtonSelected((Button)v, 0,false);
			}
		});


		//Make occupied views semi-transparent
		if (mWidgetMode == WidgetMode.CONFIG)
		{
			if (mCenterButton.length() > 0)
				setButtonTransparent(mCenterButton,true);

			for (Button button : mRowKeys)
			{
				if (button.length() > 0)
					setButtonTransparent(button,true);
			}

			if (mSelectionButton != null)
			{
				setButtonTransparent(mSelectionButton,false);
			}

		}
		else
		{
			setButtonTransparent(mCenterButton,false);

			for (Button button : mRowKeys)
			{
				setButtonTransparent(button,false);
			}
		}
	}

	public void selectFirstEmpty()
	{
		int index = 0;
		Button selectMe = null;

		if (mCenterButton.getText().length() < 1)
		{
			selectMe = mCenterButton;
		}
		else
		{
			for (Button button : mRowKeys)
			{
				index++;
				if (button.length() < 1)
				{
					selectMe = button;
					break;
				}

			}
		}

		if (selectMe == null)
		{
			index = mButtonCount;
			selectMe = addButton();
		}


		setButtonSelected(selectMe,index,true);

	}

	public void setSelectedKeyText(String text)
	{
		mSelectionText = text;

		if (mSelectionButton != null)
		{

			fitText(mSelectionButton, text, getWidth( mSelectionButton != mCenterButton ) );
		}
	}

	public int getSelectedPosition()
	{
		if (mSelectionButton == null)
			return -1;
		else
		{
			if (mSelectionButton == mCenterButton)
				return 0;
			else
			{
				for (int i = 0; i < mRowKeys.size(); i++)
				{
					if (mRowKeys.get(i) == mSelectionButton)
						return i+1;
				}

			}
		}
		return -1;
	}



	private void setButtonSelected(Button button, int position, boolean forceSelect)
	{
		if (mWidgetMode == WidgetMode.CONFIG)
		{
			if (mSelectionText != null)
			{
				//No selecting already-occupied slots
				if (button != null && (button.getText().length() < 1) || forceSelect)
				{

					//Deselect existing
					if (mSelectionButton != null)
					{
						mSelectionButton.setSelected(false);
						mSelectionButton.setText("");
						//setButtonTransparent(mSelectionButton,true);

					}

					mSelectionButton = button;
					mSelectionButton.setSelected(true);

					setButtonTransparent(mSelectionButton,false);

					fitText(mSelectionButton, mSelectionText, getWidth( position != 0));
				}
			}
		}
		else
		{
			if (mClickListener != null)
			{
				mClickListener.onKeyClicked(position, null);
			}
		}
	}

	public PopupkeysPositionWidget(Context context)
	{
		super(context);
		init(context);
	}

	public PopupkeysPositionWidget(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}

	public PopupkeysPositionWidget(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public void setFromParent(PopupParentKeyItem parentKey)
	{

		this.setDisplayMode(WidgetMode.DISPLAY);

		int maxIndex = 0;
		clearText();

		for (DB_PopupKeyItem item : parentKey.get_items())
		{
			setKey(item.get_popupLower(), item.get_insertIndex());
			if (item.get_insertIndex() > maxIndex)
				maxIndex = item.get_insertIndex();
		}

		trim(maxIndex);
	}

	public void setFromKey(PopupKeyItem key, PopupParentKeyItem parentKey)
	{
		setFromParent(parentKey);

		this.setDisplayMode(WidgetMode.CONFIG);

		mSelectionText = key.get_popupLower();

		int index = key.get_insertIndex();

		if (index < 0)
		{
			selectFirstEmpty();
		}
		else if (index == 0)
		{
			setButtonSelected(mCenterButton, 0, true);
		}
		else
		{
			setButtonSelected(mRowKeys.get(index-1), index, true);
		}
	}



	private void init(Context context)
	{
		mRowButtonWidth = context.getResources().getDimensionPixelSize(R.dimen.popup_key_row_button);

		mAccentColor = ThemeUtils.getThemeAccentColor(context);

		mInflater = LayoutInflater.from(context);

		mMeasureButton =  getMeasureButton();//getRowButton();

		View.inflate(context, R.layout.popup_key_position_widget_layout, this);



		mKeyLayout = (LinearLayout) findViewById(R.id.popupkey_key_row);

		mAddKeyButton = (Button) findViewById(R.id.popupkey_add_row_key);

		//We're lazy and use a + sign as the add button. Remove padding to center it properly.
		mAddKeyButton.setIncludeFontPadding(false);

		setButtonBackground(mAddKeyButton,true);


		mCenterButton = (Button) findViewById(R.id.popupkey_center_key);

		setDisplayMode(mWidgetMode);

		mDefaultTextSize = mCenterButton.getTextSize();


	}


	private void clearText()
	{
		mCenterButton.setText("");

		for (Button button : mRowKeys)
		{
			button.setText("");
		}
	}


	public void trim(int buttonCount)
	{
		if (buttonCount < 0)
			buttonCount = 0;

		while (mButtonCount > buttonCount)
		{
			Button view = mRowKeys.remove( mRowKeys.size() -1 );
			mKeyLayout.removeView( view);

			mButtonCount--;
		}
	}

	private float getSingleWidth()
	{
		float viewWidth = this.getMeasuredWidth();
		if (viewWidth == 0)
			return mRowButtonWidth;

		int padding = ( mMeasureButton.getPaddingLeft() + mMeasureButton.getPaddingRight() ) * mButtonCount;
		float buttonWidth = (viewWidth - padding) / (float) mButtonCount;

		if (buttonWidth > mRowButtonWidth)
			buttonWidth = mRowButtonWidth;

		return buttonWidth;
	}

	public void fitText(Button button, String text, int buttonWidth)
	{
		float sizeCounter = mDefaultTextSize;

		if (mMeasureButton != null)
		{
			buttonWidth *= 0.75;

			mMeasureButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeCounter);
			mMeasureButton.setText(text);

			mMeasureButton.measure(  MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			while(mMeasureButton.getMeasuredWidth() > buttonWidth)
			{
				sizeCounter--;
				if (sizeCounter <= 1)
					break;

				mMeasureButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeCounter);
				mMeasureButton.measure(  MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			}
		}

		button.setText(text);
		button.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeCounter);
	}


	public void setKey(String key, int index)
	{
		int iteration = 0;
		while (index > mButtonCount)
		{
			addButton();

			iteration++;

			if (iteration > 20)
				break;
		}

		Button setButton = null;
		if (index == 0)
		{
			setButton = mCenterButton;
		}
		else
		{
			setButton = mRowKeys.get(index-1);
		}


		fitText(setButton, key, getWidth(index != 0));
	}

	private int getWidth(boolean isVariable)
	{
			if (isVariable)
				return (int)getSingleWidth();
			else
				return  (int)mRowButtonWidth;
	}


	private Button getMeasureButton()
	{
		Button newButton = (Button)mInflater.inflate(R.layout.popup_key_list_dialog_key_button, null);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.weight = 1;

		newButton.setLayoutParams(params);
		//newButton.setMaxWidth((int) mRowButtonWidth);

		return newButton;
	}

	private Button getRowButton()
	{
		Button newButton = (Button)mInflater.inflate(R.layout.popup_key_list_dialog_key_button, null);



		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)mRowButtonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.weight = 1;

		newButton.setLayoutParams(params);
		newButton.setMaxWidth((int) mRowButtonWidth);

		return newButton;
	}



	private void setButtonBackground(Button button, boolean selected)
	{
		if (selected)
		{
			button.getBackground().setColorFilter(mAccentColor, PorterDuff.Mode.MULTIPLY);
		}
		else
			button.getBackground().clearColorFilter();
	}

	private void setButtonTransparent(Button button, boolean trans)
	{
		if (trans)
		{
			button.setAlpha(0.3f);
		}
		else
			button.setAlpha(1);
	}



	private Button addButton()
	{
		mButtonCount++;

		//The index of the view we are about to create.
		//Note that this will be offset by 1, as we include
		//the center button in this count
		final int localButtonCount = mButtonCount;

		Button newButton = getRowButton();


		//newButton.setHint(""+(mButtonCount));
		mKeyLayout.addView(newButton, mKeyLayout.getChildCount()-1 );
		mRowKeys.add(newButton);

		//if (mWidgetMode == WidgetMode.CONFIG)
		{
			newButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					setButtonSelected((Button)v,localButtonCount,false);
					//setSelected(mKey, localButtonCount);
				}
			});

		}
		//else
		{
		//	newButton.setClickable(false);
		}
		//Refit text

		int	buttonWidth = getWidth(true);
		for (Button button : mRowKeys)
		{
			if (button.getText().length() > 0)
			{
				fitText(button, button.getText().toString(), buttonWidth);
			}

		}

		return newButton;

		//this.requestLayout();

	}

	public void setOnKeyClickedListener(OnKeyClickedListener listener)
	{
		mClickListener = listener;
	}

	public void setOnAddKeyClickedListener(OnKeyClickedListener listener)
	{
		mAddClickListener = listener;
	}

	public interface OnKeyClickedListener
	{
		void onKeyClicked(int position, @Nullable PopupParentKeyItem parentItem);
	}



}
