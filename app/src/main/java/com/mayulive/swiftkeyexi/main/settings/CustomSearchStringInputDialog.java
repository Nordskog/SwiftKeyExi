package com.mayulive.swiftkeyexi.main.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mayulive.swiftkeyexi.R;

/**
 * Created by Roughy on 5/4/2017.
 */

public abstract class CustomSearchStringInputDialog
{

	private static final String DUCK_SEARCH_STRING = "https://duckduckgo.com/?q=$1";
	public static final String GOOGLE_SEARCH_STRING = "https://google.com/search?q=$1";
	private static final String WIKI_SEARCH_STRING = "https://en.wikipedia.org/wiki/$1";


	AlertDialog mDialog = null;

	CustomSearchStringInputDialog(Context context, String existingValue)
	{

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		View configView = inflater.inflate(R.layout.custom_search_string_preference_layout, null,false);
		builder.setView(configView);

		final EditText searchStringEditText = configView.findViewById(R.id.custom_search_string_input);

		Button googleButton = configView.findViewById(R.id.custom_search_string_google);
		Button duckButton = configView.findViewById(R.id.custom_search_string_duckduckgo);
		Button wikiButton = configView.findViewById(R.id.custom_search_string_wiki);

		googleButton.setOnClickListener(v -> searchStringEditText.setText( GOOGLE_SEARCH_STRING ));
		duckButton.setOnClickListener(v -> searchStringEditText.setText( DUCK_SEARCH_STRING ));
		wikiButton.setOnClickListener(v -> searchStringEditText.setText( WIKI_SEARCH_STRING ));

		searchStringEditText.setText( existingValue );

		builder.setMessage(R.string.pref_custom_search_string_title)
				.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{

						String searchString = searchStringEditText.getText().toString().trim();
						onEntrySaved( searchString );
					}
				})
				.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{

					}
				});

		mDialog = builder.create();
	}



	public abstract void onEntrySaved( String newString );


	public void show()
	{
		mDialog.show();
	}

}
