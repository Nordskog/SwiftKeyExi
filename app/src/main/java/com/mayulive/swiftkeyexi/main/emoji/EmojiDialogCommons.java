package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.mayulive.swiftkeyexi.R;

public class EmojiDialogCommons
{
	static void displayUneditableWarning(Context context)
	{
		AlertDialog dialog = new AlertDialog.Builder(context)
				.setTitle(R.string.emoji_uneditable_title)
				.setMessage(R.string.emoji_uneditable_message)
				.setPositiveButton(R.string.button_ok, null)
				.create();
		dialog.show();
	}
}
