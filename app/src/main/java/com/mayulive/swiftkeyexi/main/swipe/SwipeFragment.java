package com.mayulive.swiftkeyexi.main.swipe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.settings.PreferenceConstants;
import com.mayulive.swiftkeyexi.util.MathUtils;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.CursorBehavior;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SpaceModifierBehavior;
import com.mayulive.swiftkeyexi.R;
import com.mayulive.swiftkeyexi.settings.SettingsCommons;
import com.mayulive.swiftkeyexi.xposed.selection.selectionstuff.SelectionBehavior;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;
import static com.mayulive.swiftkeyexi.settings.SettingsCommons.MODULE_SHARED_PREFERENCES_KEY;

/**
 * Created by Roughy on 1/10/2017.
 */

public class SwipeFragment extends Fragment
{

	private static String LOGTAG = ExiModule.getLogTag(SwipeFragment.class);

	private static final int SPEED_SEEKBAR_MAX = 1000;
	private static final float SPEED_PIXEL_MAX = 500;
	private static final float SPEED_PIXEL_MIN = 10;
	private static final int SPEED_SEEKBAR_EXPONENT_MULTIPLIER = 2;

	View mRootView = null;

	ArrayList<View> mSwipeModeViews = new ArrayList<>();
	ArrayList<View> mSelectModeViews = new ArrayList<>();
	ArrayList<View> mSpaceModifierViews = new ArrayList<>();

	UnitCharDisplayView mSpeedTextDisplay;
	UnitBarDisplayView mSpeedDisplay;
	SeekBar mSpeedSeekBar;

	UnitBarDisplayView mThresholdDisplay;
	SeekBar mThresholdSeekBar;

	private static float mLastPixelSpeed = 100;
	private static float mLastPixelThreshold = 100;


	//Must match layout below
	CursorBehavior[] mSwipeModes = new CursorBehavior[]
	{
			CursorBehavior.SWIPE,
			CursorBehavior.HOLD_ANY_SWIPE,
			CursorBehavior.SPACE_SWIPE,
			CursorBehavior.HOLD_SHIFT_SWIPE,
	};

	int[] mViewIds = new int[]
	{
			R.id.swipe_mode_anywhere,
			R.id.swipe_mode_hold_any,
			R.id.swipe_mode_space,
			R.id.swipe_mode_hold_shift,
			//R.id.swipe_mode_disable
	};

	int[] mDrawableIds = new int[]
	{
			R.drawable.ic_swipe_mode_anywhere,
			R.drawable.ic_swipe_mode_hold_any,
			R.drawable.ic_swipe_mode_space,
			R.drawable.ic_swipe_mode_hold_shift,
			//R.drawable.ic_swipe_mode_disabled,
	};

	int[] mTextIds = new int[]
			{
					R.string.swipe_mode_anywhere,
					R.string.swipe_mode_hold_any,
					R.string.swipe_mode_space,
					R.string.swipe_mode_hold_shift,
					//R.string.swipe_mode_disabled
			};


	//Mistakes were made
	CursorBehavior mCurrentSwipeMode = CursorBehavior.SWIPE;
	SpaceModifierBehavior mCurrentSpaceModBehavior = SpaceModifierBehavior.MENU ;
	boolean mSelectionShiftDeleteState = true;
	boolean mSelectionTwoFingerState = true;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		// Inflate the layout for this fragment
		mRootView = inflater.inflate(R.layout.swipe_fragment_layout, container, false);



		//////////////////////////
		//Swipe and select modes
		//////////////////////////

		mSwipeModeViews.clear();
		mSelectModeViews.clear();
		mSpaceModifierViews.clear();

		for (int i = 0; i < mSwipeModes.length; i++)
		{
			View view = mRootView.findViewById(mViewIds[i]);
			setSwipeButton(view, mDrawableIds[i],mTextIds[i]);
			setSwipeClickListener(view, mSwipeModes[i]);
			mSwipeModeViews.add(view);
		}


		View selectTwoFinger = mRootView.findViewById(R.id.swipe_select_mode_two_finger);
		View selectShiftDelete = mRootView.findViewById(R.id.swipe_select_mode_shift_delete);

		setSwipeButton(selectTwoFinger, R.drawable.ic_selection_mode_two_finger,R.string.selection_mode_two_finger);
		setSwipeButton(selectShiftDelete, R.drawable.ic_selection_mode_shift_delete,R.string.selection_mode_shift_delete);

		mSelectModeViews.add(selectTwoFinger);
		mSelectModeViews.add(selectShiftDelete);

		selectTwoFinger.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mSelectionTwoFingerState = !mSelectionTwoFingerState;
				setSelectionSelectedFromState();
			}
		});

		selectShiftDelete.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mSelectionShiftDeleteState = !mSelectionShiftDeleteState;
				setSelectionSelectedFromState();
			}
		});

		////////////////////////////
		// Space modifier
		////////////////////////////

		View selectMenu = mRootView.findViewById(R.id.space_modifier_select_mode_menu);
		View selectKey = mRootView.findViewById(R.id.space_modifier_select_mode_key);

		setSwipeButton(selectMenu, R.drawable.ic_space_modifier_menu,R.string.space_modifier_menu);
		setSwipeButton(selectKey, R.drawable.ic_space_modifier_key,R.string.space_modifier_key);


		mSpaceModifierViews.add(selectMenu);
		mSpaceModifierViews.add(selectKey);

		selectMenu.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (mCurrentSpaceModBehavior == SpaceModifierBehavior.MENU)
				{
					setSpaceModBehavior(SpaceModifierBehavior.DISABLED);
				}
				else
				{
					setSpaceModBehavior(SpaceModifierBehavior.MENU);
				}
			}
		});

		selectKey.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (mCurrentSpaceModBehavior == SpaceModifierBehavior.KEY)
				{
					setSpaceModBehavior(SpaceModifierBehavior.DISABLED);
				}
				else
				{
					setSpaceModBehavior(SpaceModifierBehavior.KEY);
				}
			}
		});




		////////////////////////////
		//Swipe speed / threshold
		////////////////////////////


		mSpeedTextDisplay = (UnitCharDisplayView) mRootView.findViewById(R.id.swipe_speed_text_indicator);
		mSpeedDisplay = (UnitBarDisplayView) mRootView.findViewById(R.id.swipe_speed_indicator);

		//This is static
		mSpeedDisplay.setPixelCount( mSpeedTextDisplay.getComparisonBarLenght() );


		mSpeedSeekBar = (SeekBar)mRootView.findViewById(R.id.swipe_speed_seekbar);
		mSpeedSeekBar.setMax(SPEED_SEEKBAR_MAX);	//Arbitrary units

		mSpeedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				setSwipeSpeed( speedUnitsToPixelUits( progress ), !fromUser);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				saveSettings();
			}
		});

		/////////////////
		//Threshold
		////////////////



		mThresholdDisplay = (UnitBarDisplayView)mRootView.findViewById(R.id.swipe_threshold_indicator);
		mThresholdDisplay.setMaxPixelCount(SPEED_PIXEL_MAX);

		mThresholdSeekBar = (SeekBar)mRootView.findViewById(R.id.swipe_threshold_seekbar);
		mThresholdSeekBar.setMax((int) SPEED_PIXEL_MAX);


		mThresholdSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				setSwipeThreshold( (float) progress, !fromUser);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{



				saveSettings();
			}
		});

		loadSettings();


		return mRootView;
	}


	///////////
	//Setup
	///////////


	////////////////
	//Settings
	////////////////

	private void loadSettings()
	{

		SharedPreferences prefs = SettingsCommons.getSharedPreferences(this.getContext(), MODULE_SHARED_PREFERENCES_KEY);

		mCurrentSwipeMode =
				CursorBehavior.valueOf
						(
								prefs.getString(PreferenceConstants.pref_cursor_behavior_key,
										CursorBehavior.SWIPE.toString()
								)
						);


		setSelectionStateFromMode(
				SelectionBehavior.valueOf
						(
								prefs.getString(PreferenceConstants.pref_selection_behavior_key,
										SelectionBehavior.HYBRID.toString()
								)
						));


		mCurrentSpaceModBehavior =
				SpaceModifierBehavior.valueOf
						(
								prefs.getString(PreferenceConstants.pref_space_swipe_modifier_mode_key,
										SpaceModifierBehavior.MENU.toString()
								)
						);



		mLastPixelSpeed = prefs.getFloat(PreferenceConstants.pref_cursor_speed_key, 100);

		mLastPixelThreshold = prefs.getFloat(PreferenceConstants.pref_swipe_threshold_key, 100);

		setSwipeSpeed(mLastPixelSpeed, true);

		setSwipeThreshold(mLastPixelThreshold, true);
		setSelectionSelectedFromState();
		setSpaceModifierFromState();
		setSwipeSelected(mCurrentSwipeMode);
	}

	private void saveSettings()
	{

		SharedPreferences.Editor editor = SettingsCommons.getSharedPreferencesEditor(this.getContext(), MODULE_SHARED_PREFERENCES_KEY);

		editor.putString(PreferenceConstants.pref_cursor_behavior_key, mCurrentSwipeMode.toString());
		editor.putString(PreferenceConstants.pref_selection_behavior_key, getSelectionModeFromState().toString());

		editor.putString(PreferenceConstants.pref_space_swipe_modifier_mode_key, mCurrentSpaceModBehavior.toString());
		editor.putFloat(PreferenceConstants.pref_cursor_speed_key, mLastPixelSpeed );
		editor.putFloat(PreferenceConstants.pref_swipe_threshold_key, mLastPixelThreshold);


		editor.apply();
	}


	///////////////////
	//Swipe settings
	///////////////////

	private float speedUnitsToPixelUits(float progress)
	{
		progress = MathUtils.scaleExponential( progress, SPEED_SEEKBAR_MAX, SPEED_SEEKBAR_EXPONENT_MULTIPLIER);

		//As ratio
		progress /= SPEED_SEEKBAR_MAX;

		//Reverse
		progress = 1f - progress;

		//Get as pixel value
		progress *= SPEED_PIXEL_MAX - SPEED_PIXEL_MIN;
		progress += SPEED_PIXEL_MIN;

		return progress;

	}

	private int pixelUnitstoSpeedUnits(float pixelValue)
	{
		//Get as speed units
		pixelValue = SPEED_SEEKBAR_MAX * ( (pixelValue - SPEED_PIXEL_MIN) / (SPEED_PIXEL_MAX - SPEED_PIXEL_MIN));


		//Reverse
		pixelValue = SPEED_SEEKBAR_MAX - pixelValue;

		pixelValue = MathUtils.unscaleExponential( pixelValue, SPEED_SEEKBAR_MAX, SPEED_SEEKBAR_EXPONENT_MULTIPLIER);

		return (int) pixelValue;
	}

	private boolean mDoNotUpdateSeekBar = false;

	//Remember that 0 is inifinity, 1 is ultrafast, 500 is slow.
	private void setSwipeSpeed(float newValue, boolean updateSeekbar)
	{
		mLastPixelSpeed = newValue;
		//No loops here

		if (!mDoNotUpdateSeekBar && updateSeekbar)
		{
			mDoNotUpdateSeekBar = true;
			mSpeedSeekBar.setProgress( pixelUnitstoSpeedUnits(mLastPixelSpeed) );
			mDoNotUpdateSeekBar = false;
		}

		mSpeedTextDisplay.setPixelCount( newValue );
	}

	private void setSwipeThreshold(float newValue, boolean updateSeekbar)
	{
		newValue = newValue < 1f ? 1f : newValue;

		mLastPixelThreshold = newValue;


		//No loops here
		if (updateSeekbar)
		{
			mThresholdSeekBar.setProgress((int) (newValue) );
		}

		mThresholdDisplay.setPixelCount( newValue );
	}

	/////////////////
	//Swipe buttons
	/////////////////
	private void setSwipeButton(View view, int drawableResource, int textResource)
	{
		ImageView imageView = (ImageView) ((ViewGroup)view).getChildAt(0);
		TextView textView = (TextView) ((ViewGroup)view).getChildAt(1);

		//Drawable img = this.getContext().getResources().getDrawable(drawableResource);
		imageView.setImageResource(drawableResource);



		//imageView.setBackgroundResource(drawableResource);

		String text = this.getContext().getResources().getString(textResource);
		textView.setText(text);


		//imageView.setImageDrawable(img);
	}

	private void setSelected(View view)
	{
		view.setAlpha(1);
	}

	private void setUnselected(View view)
	{
		view.setAlpha(0.25f);
	}

	//////////////////
	//Space modifier
	//////////////////

	private void setSpaceModBehavior (SpaceModifierBehavior behavior)
	{
		mCurrentSpaceModBehavior = behavior;
		setSpaceModifierFromState();
	}


	private void setSpaceModifierFromState()
	{
		switch(mCurrentSpaceModBehavior)
		{

			case KEY:
			{
				setUnselected(mSpaceModifierViews.get(0));
				setSelected(mSpaceModifierViews.get(1));
				break;
			}
			case MENU:
			{
				setUnselected(mSpaceModifierViews.get(1));
				setSelected(mSpaceModifierViews.get(0));
				break;
			}
			case DISABLED:
			{
				setUnselected(mSpaceModifierViews.get(1));
				setUnselected(mSpaceModifierViews.get(0));
				break;
			}
		}

		saveSettings();
	}


	//////////
	//Swipe
	//////////

	private void setSwipeSelected(CursorBehavior behavior)
	{
		mCurrentSwipeMode = behavior;

		for (View view : mSwipeModeViews)
			setUnselected(view);

		if (behavior != CursorBehavior.DISABLED)
		{
			View selectedView = mSwipeModeViews.get( getSwipeIndexFromMode(behavior) );
			setSelected(selectedView);
		}

		saveSettings();
	}

	private int getSwipeIndexFromMode(CursorBehavior inMode)
	{
		int counter = 0;
		for (CursorBehavior mode : mSwipeModes)
		{
			if (mode == inMode)
				return counter;
			counter++;
		}
		return -1;
	}

	private void setSwipeClickListener(View view, final CursorBehavior behavior)
	{
		view.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				int selectedIndex = mSwipeModeViews.lastIndexOf(v);

				if (behavior == mCurrentSwipeMode)
				{
					setSwipeSelected(CursorBehavior.DISABLED );
				}
				else
				{
					setSwipeSelected(mSwipeModes[selectedIndex]);
				}

			}
		});
	}

	////////////
	//Selection
	////////////



	private void setSelectionSelectedFromState()
	{
		if (mSelectionTwoFingerState)
			setSelected(mSelectModeViews.get(0));
		else
			setUnselected(mSelectModeViews.get(0));

		if (mSelectionShiftDeleteState)
			setSelected(mSelectModeViews.get(1));
		else
			setUnselected(mSelectModeViews.get(1));

		saveSettings();
	}

	private SelectionBehavior getSelectionModeFromState()
	{
		if (mSelectionTwoFingerState && mSelectionShiftDeleteState)
			return SelectionBehavior.HYBRID;

		if (mSelectionTwoFingerState)
			return SelectionBehavior.HOLD_AND_DRAG_SWIPE;

		if (mSelectionShiftDeleteState)
			return SelectionBehavior.SHIFT_DELETE_DRAG_SWIPE;

		return SelectionBehavior.DISABLED;
	}

	private void setSelectionStateFromMode(SelectionBehavior behavior)
	{
		switch(behavior)
		{
			case DISABLED:
			{
				mSelectionTwoFingerState = false;
				mSelectionShiftDeleteState = false;
				break;
			}
			case SHIFT_DELETE_DRAG_SWIPE:
			{
				mSelectionTwoFingerState = false;
				mSelectionShiftDeleteState = true;
				break;
			}
			case HOLD_AND_DRAG_SWIPE:
			{
				mSelectionTwoFingerState = true;
				mSelectionShiftDeleteState = false;
				break;
			}
			case HYBRID:
			{
				mSelectionTwoFingerState = true;
				mSelectionShiftDeleteState = true;
				break;
			}
		}

	}
}
