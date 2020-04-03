package com.mayulive.swiftkeyexi.xposed;

import android.content.Context;
import android.os.Build;

import com.mayulive.swiftkeyexi.R;

import java.util.ArrayList;

/**
 * Created by Roughy on 2/23/2017.
 */
public class KeyboardInteraction
{
	public enum TextAction
	{
		DEFAULT,
		UNDO,
		REDO,
		COPY,
		CUT,
		PASTE,
		PASTE_PLAIN,
		SELECT_ALL,
		GO_TO_END,
		GO_TO_START,
		INSERT,
		NEWLINE,
		TOGGLE_AUTOCORRECT,
		TOGGLE_INCOGNITO,
		TOGGLE_AUTO_INCOGNITO,
		TOGGLE_NUMBER_ROW,
		TOGGLE_TOOLBAR;

		public static String getTextRepresentation(Context context, TextAction action)
		{
			try
			{
				switch(action)
				{
					case DEFAULT:
						return "DEFAULT";
					case UNDO:
						return context.getResources().getString(R.string.textaction_undo);
					case REDO:
						return context.getResources().getString(R.string.textaction_redo);
					case COPY:
						return context.getResources().getString(R.string.textaction_copy);
					case CUT:
						return context.getResources().getString(R.string.textaction_cut);
					case PASTE:
						return context.getResources().getString(R.string.textaction_paste);
					case PASTE_PLAIN:
						return context.getResources().getString(R.string.textaction_paste_plain);
					case SELECT_ALL:
						return context.getResources().getString(R.string.textaction_selectall);
					case GO_TO_END:
						return context.getResources().getString(R.string.textaction_gotoend);
					case GO_TO_START:
						return context.getResources().getString(R.string.textaction_gotostart);
					case INSERT:
						return context.getResources().getString(R.string.textaction_insert);
					case TOGGLE_AUTOCORRECT:
						return context.getResources().getString(R.string.textaction_toggle_auto);
					case TOGGLE_INCOGNITO:
						return context.getResources().getString(R.string.textaction_toggle_incog);
					case TOGGLE_AUTO_INCOGNITO:
						return context.getResources().getString(R.string.textaction_toggle_auto_incog);
					case NEWLINE:
						return context.getResources().getString(R.string.textaction_insert_newline);
					case TOGGLE_NUMBER_ROW:
						return context.getResources().getString(R.string.textaction_toggle_number_row);
					case TOGGLE_TOOLBAR:
						return context.getResources().getString(R.string.textaction_toggle_toolbar);

					default:
						return "Null";
				}
			}
			catch (Exception ex)
			{
				//When we update the app the resource ids usually change,
				//but the loaded xposed side is still using the old ids, causing a crash.
				return "Null";
			}
		}

		public static String getShortTextRepresentation(Context context, TextAction action)
		{
			try
			{
				switch(action)
				{
					case DEFAULT:
						return "DEFAULT";
					case UNDO:
						return context.getResources().getString(R.string.textaction_short_undo);
					case REDO:
						return context.getResources().getString(R.string.textaction_short_redo);
					case COPY:
						return context.getResources().getString(R.string.textaction_short_copy);
					case CUT:
						return context.getResources().getString(R.string.textaction_short_cut);
					case PASTE:
						return context.getResources().getString(R.string.textaction_short_paste);
					case PASTE_PLAIN:
						return context.getResources().getString(R.string.textaction_short_paste_plain);
					case SELECT_ALL:
						return context.getResources().getString(R.string.textaction_short_selectall);
					case GO_TO_END:
						return context.getResources().getString(R.string.textaction_short_gotoend);
					case GO_TO_START:
						return context.getResources().getString(R.string.textaction_short_gotostart);
					case INSERT:
						return context.getResources().getString(R.string.textaction_short_insert);
					case TOGGLE_AUTOCORRECT:
						return context.getResources().getString(R.string.textaction_short_toggle_auto);
					case TOGGLE_INCOGNITO:
						return context.getResources().getString(R.string.textaction_short_toggle_incog);
					case TOGGLE_AUTO_INCOGNITO:
						return context.getResources().getString(R.string.textaction_short_toggle_auto_incog);
					case NEWLINE:
						return context.getResources().getString(R.string.textaction_short_insert_newline);
					case TOGGLE_NUMBER_ROW:
						return context.getResources().getString(R.string.textaction_short_toggle_number_row);
					case TOGGLE_TOOLBAR:
						return context.getResources().getString(R.string.textaction_short_toggle_toolbar);

					default:
						return "Null";
				}
			}
			catch (Exception ex)
			{
				//When we update the app the resource ids usually change,
				//but the loaded xposed side is still using the old ids, causing a crash.
				return "Null";
			}
		}

		public static TextAction[] getUsableTextActions()
		{
			return getUsableTextActions(false);
		}

		public static TextAction[] getUsableTextActions(boolean includeSpecial)
		{

			ArrayList<TextAction> values = new ArrayList<>();

			//Supports everything
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
			{

				values.add( TextAction.UNDO);
				values.add( TextAction.REDO);
			}

			values.add( TextAction.COPY);
			values.add( TextAction.CUT);
			values.add( TextAction.PASTE);

			//Supports everything
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
			{
				values.add( TextAction.PASTE_PLAIN );
			}

			if (includeSpecial)
			{
				values.add( TextAction.INSERT );
			}

			values.add( TextAction.NEWLINE);

			values.add( TextAction.SELECT_ALL);
			values.add( TextAction.GO_TO_END);
			values.add( TextAction.GO_TO_START);

			values.add( TextAction.TOGGLE_NUMBER_ROW);
			values.add( TextAction.TOGGLE_TOOLBAR);
			values.add( TextAction.TOGGLE_AUTOCORRECT);
			values.add( TextAction.TOGGLE_INCOGNITO);
			values.add( TextAction.TOGGLE_AUTO_INCOGNITO);

			return values.toArray( new TextAction[ values.size() ] );
		}

	}


}
