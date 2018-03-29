package com.mayulive.swiftkeyexi.main.shortcutkeys;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.data.ModifierKeyItem;

/**
 * Created by Roughy on 3/15/2018.
 */

public class SoftKeyView extends SoftHardKeyDialogView
{
	EditText mEditText;

	public SoftKeyView(Context context, ModifierKeyItem item)
	{
		super(context, item);
	}

	@Override
	void inflateLayout(LayoutInflater inflater)
	{
		// Inflate the layout for this fragment
		 inflater.inflate(R.layout.hotkey_dialog_soft_layout, this, true);

		final EditText mEditText = this.findViewById(R.id.shortcutkey_text);

		mEditText.setText( mItem.get_key() );

		mEditText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				mItem.set_key(s.toString().trim());
			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});
	}
}
