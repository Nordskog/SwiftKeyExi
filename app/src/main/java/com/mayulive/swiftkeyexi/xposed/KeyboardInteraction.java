package com.mayulive.swiftkeyexi.xposed;

import android.content.Context;

import com.mayulive.swiftkeyexi.R;

/**
 * Created by Roughy on 2/23/2017.
 */
public class KeyboardInteraction
{
	public enum TextAction
	{
		DEFAULT,
		COPY,
		CUT,
		PASTE,
		SELECT_ALL,
		GO_TO_END
		;

		public static String getTextRepresentation(Context context, TextAction action)
		{

			switch(action)
			{
				case DEFAULT:
					return "DEFAULT";
				case COPY:
					return context.getResources().getString(R.string.textaction_copy);
				case CUT:
					return context.getResources().getString(R.string.textaction_cut);
				case PASTE:
					return context.getResources().getString(R.string.textaction_paste);
				case SELECT_ALL:
					return context.getResources().getString(R.string.textaction_selectall);
				case GO_TO_END:
					return context.getResources().getString(R.string.textaction_gotoend);

				default:
					return "DEFAULT";
			}
		}
	}


}
