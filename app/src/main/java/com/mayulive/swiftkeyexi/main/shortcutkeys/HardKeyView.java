package com.mayulive.swiftkeyexi.main.shortcutkeys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.main.commons.HardwareKeyboardInputDialog;
import com.mayulive.swiftkeyexi.main.commons.data.HardKey;
import com.mayulive.swiftkeyexi.main.commons.data.HardKeyType;
import com.mayulive.swiftkeyexi.main.commons.data.ModifierKeyItem;
import com.mayulive.swiftkeyexi.util.KeyUtils;

/**
 * Created by Roughy on 3/15/2018.
 */

public class HardKeyView extends SoftHardKeyDialogView
{
	private Button mKeyButton;
	private Button mModifierButton;
	private Button mAddModifierButton;		//Hidden after modifier added
	private Button mRemoveModifierButton;


	//Visible after modifier added
	private ViewGroup mModifierBlock;


	public HardKeyView(Context context, ModifierKeyItem item)
	{
		super(context, item);
	}

	@Override
	void inflateLayout(LayoutInflater inflater)
	{
		// Inflate the layout for this fragment
		inflater.inflate(R.layout.hotkey_dialog_hard_layout, this, true);

		mKeyButton = this.findViewById(R.id.hotkey_hard_key_button);
		mModifierButton = this.findViewById(R.id.hotkey_hard_modifier_button);

		mAddModifierButton = this.findViewById(R.id.hotkey_hard_add_modifier_button);
		mRemoveModifierButton = this.findViewById(R.id.hotkey_hard_remove_modifier_button);

		mModifierBlock = this.findViewById(R.id.hotkey_hard_modifier_block);

		mAddModifierButton.setOnClickListener(v ->
		{
			//Default to ctrl maybe?
			modifierAdded( new HardKey( -1, -1 ) );
		});

		mRemoveModifierButton.setOnClickListener( v ->
		{
			modifierRemoved();
		});

		mKeyButton.setOnClickListener(v ->
		{
			HardwareKeyboardInputDialog dialog = new HardwareKeyboardInputDialog(this.getContext())
			{
				@Override
				public void onKeyInput(int keycode, int scancode)
				{
					setKey(new HardKey(keycode, scancode));
				}
			};

			dialog.show();
		});

		mModifierButton.setOnClickListener(v ->
		{
			HardwareKeyboardInputDialog dialog = new HardwareKeyboardInputDialog(this.getContext())
			{
				@Override
				public void onKeyInput(int keycode, int scancode)
				{
					modifierAdded(new HardKey(keycode, scancode));
				}
			};

			dialog.show();
		});


		if (mItem.get_hard_modifier_key().getType() != HardKeyType.UNDEFINED)
		{

			modifierAdded( mItem.get_hard_modifier_key() );
		}
		else
		{
			modifierRemoved();
		}

		setKey( mItem.get_hard_key() );

	}

	private void setKey(HardKey key)
	{
		mItem.set_hard_key( key);
		mKeyButton.setText( KeyUtils.getKeyString(this.getContext(), key) );
	}

	private void modifierAdded(HardKey key)
	{
		mItem.set_hard_modifier_key(key);

		//Unhide modifier block, hide add modifier buton
		mModifierBlock.setVisibility(View.VISIBLE);
		mAddModifierButton.setVisibility(View.GONE);

		mModifierButton.setText( KeyUtils.getKeyString(this.getContext(), key) );
	}

	private void modifierRemoved()
	{
		mItem.set_hard_modifier_key( new HardKey(-1,-1) );
		mModifierBlock.setVisibility(View.GONE);
		mAddModifierButton.setVisibility(View.VISIBLE);
	}
}
