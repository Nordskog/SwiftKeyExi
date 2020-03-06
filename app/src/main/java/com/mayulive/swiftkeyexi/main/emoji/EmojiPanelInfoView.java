package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.Nullable;

import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.mayulive.swiftkeyexi.util.ThemeUtils;
import com.mayulive.swiftkeyexi.util.view.BoundedFrameLayout;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.util.KeyboardUtil;

/**
 * Created by Roughy on 5/7/2017.
 */

public class EmojiPanelInfoView extends BoundedFrameLayout
{

	LinearLayout mContent = null;

	ImageButton mLeftButton;
	ImageButton mRightButton;

	ImageButton mPickButton;
	Button mDoneButton;

	CheckBox mUseColorEmojiCheckbox;

	EditText mIconText;


	OnEmojiPanelInfoViewDismissedListener mDismissListener;

	public EmojiPanelInfoView(Context context)
	{
		super(context);
		init(context,null);
	}

	public EmojiPanelInfoView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		init(context,attrs);
	}

	public EmojiPanelInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context,attrs);
	}

	public void show()
	{
		this.setVisibility(View.VISIBLE);
	}

	public void hide()
	{
		this.setVisibility(View.GONE);
	}

	public void setIconEditability(boolean editable)
	{
		if (editable)
		{
			mPickButton.setEnabled(true);

			mIconText.setInputType(InputType.TYPE_CLASS_TEXT);
			mIconText.setEnabled(true);
		}
		else
		{
			mPickButton.setEnabled(false);
			mIconText.setInputType(InputType.TYPE_NULL);
			mIconText.setEnabled(false);
		}
	}


	/**
	 * Dismiss without calling dismiss listeners
	 */
	public void dismissSilently()
	{
		this.hide();
		clearListeners();
		KeyboardUtil.hideKeyboard(mIconText);

	}
	public void setIcon(String icon, int style)
	{
		mIconText.setText(icon);

		//Panels were originally meant to support different emoji sets,
		//but for the time being we only have simple and color. 0 is color, 1 is simple.
		mUseColorEmojiCheckbox.setChecked( style == 0);
	}

	private void clearListeners()
	{

		mLeftButton.setOnClickListener(null);
		mRightButton.setOnClickListener(null);
		mPickButton.setOnClickListener(null);

	}

	public void dismiss()
	{
		this.hide();
		if (mDismissListener != null)
		{
			mDismissListener.onDismiss(mIconText.getText().toString().trim(), mUseColorEmojiCheckbox.isChecked() ? 0 : 1);
		}
		clearListeners();
		KeyboardUtil.hideKeyboard(mIconText);
	}


	private void init(Context context, AttributeSet attrs)
	{

		LayoutInflater inflater = ( (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) );
		mContent = (LinearLayout) inflater.inflate(R.layout.emoji_set_icon_dialog, this,false);

		mLeftButton = (ImageButton)mContent.findViewById(R.id.leftButton);
		mRightButton = (ImageButton)mContent.findViewById(R.id.rightButton);

		mPickButton = (ImageButton)mContent.findViewById(R.id.pickButton);

		mDoneButton = (Button)mContent.findViewById(R.id.doneButton);

		mIconText = (EditText)mContent.findViewById(R.id.set_icon_edittext);

		mUseColorEmojiCheckbox = (CheckBox)mContent.findViewById(R.id.emoji_color_checkbox);

		mDoneButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dismiss();
			}
		});


		this.addView(mContent);

		int backgroundColor = ThemeUtils.getThemeAttribute(context, R.attr.colorPrimary);
		ColorDrawable background = new ColorDrawable();
		background.setColor(backgroundColor);
		background.setAlpha(240);


		this.setBackground(background);

		hide();



		this.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//Keep hitting it by accident
				//dismissSilently();


			}
		});

	}

	public void setOnLeftShiftListener(OnClickListener listener)
	{
		mLeftButton.setOnClickListener(listener);
	}

	public void setOnRightShiftListener(OnClickListener listener)
	{
		mRightButton.setOnClickListener(listener);
	}

	public void setOnPickButtonClickedListener(OnClickListener listener)
	{
		mPickButton.setOnClickListener(listener);
	}

	//Just returns null though
	public void setOnDismissedListener(OnEmojiPanelInfoViewDismissedListener listener)
	{
		mDismissListener = listener;
	}

	public interface OnEmojiPanelInfoViewDismissedListener
	{
		void onDismiss(String newIcon, int style);
	}


}
