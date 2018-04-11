package com.mayulive.swiftkeyexi.main.commons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.data.HardKeyType;
import com.mayulive.swiftkeyexi.util.KeyUtils;


public abstract class HardwareKeyboardInputDialog
{
	private static String LOGTAG = ExiModule.getLogTag(HardwareKeyboardInputDialog.class);

	AlertDialog mDialog = null;
	TextView mTextView;
	FrameLayout mContainer;
	Button mListButton;

	ListView mList;

	public HardwareKeyboardInputDialog(Context context)
	{
		///////////////////
		// Inflate view
		///////////////////

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		mContainer = (FrameLayout) inflater.inflate(R.layout.hardware_key_input_dialog_layout, null,false);
		builder.setView(mContainer);

		mTextView = mContainer.findViewById(R.id.hardware_key_input_text_view);

		mList = mContainer.findViewById(R.id.hardware_key_list);

		mListButton = mContainer.findViewById(R.id.hardware_key_select_from_list_button);

		/////////////////
		//Fix list sizing
		////////////////////

		//A scrollable list will push the dialog buttons off-screen
		//Dirty dirty hacks to the rescue

		{
			DisplayMetrics metrics = context.getResources().getDisplayMetrics();
			ViewGroup.LayoutParams params = mList.getLayoutParams();
			params.height = (int) (metrics.heightPixels * 0.6);
			mList.setLayoutParams(params);
		}


		///////////////////////
		// Populate key list
		///////////////////////

		KeycodeListAdapter adapter = new KeycodeListAdapter(context, KeyUtils.getValidKeycodes());
		mList.setAdapter(adapter);

		///////////////////////
		//Setup callbacks
		////////////////////////

		//Message just for the spacing
		builder.setMessage("")
				.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						//Cancel
					}
				});

		mListButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				int visibility = mList.getVisibility();
				if (visibility == View.VISIBLE)
				{
					mList.setVisibility(View.GONE);
				}
				else
				{
					mList.setVisibility(View.VISIBLE);
				}
			}
		});


		mDialog = builder.create();

		mDialog.setOnKeyListener((dialog, keyCode, event) ->
		{
			onKeyInput(event.getKeyCode(), event.getScanCode());

			mDialog.dismiss();

			return true;
		});

		mList.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				onKeyInput(position+1, 0);
				mDialog.dismiss();
			}
		});

	}

	public abstract void onKeyInput(int keycode, int scancode);




	public void show()
	{
		mDialog.show();
		mContainer.requestFocus();
	}

}
