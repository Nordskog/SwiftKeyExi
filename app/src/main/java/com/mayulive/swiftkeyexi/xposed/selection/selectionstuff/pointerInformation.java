package com.mayulive.swiftkeyexi.xposed.selection.selectionstuff;

import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;

/**
 * Created by Roughy on 6/7/2017.
 */
public class pointerInformation
{

	public PointerState state = PointerState.DEFAULT;

	public SwipeSpeedModifier modifier = SwipeSpeedModifier.DEFAULT;
	public pointerInformation partner = null;    //If double swiping

	public KeyDefinition key = new KeyDefinition();

	public float downX = 0;
	public float downY = 0;

	public float xDistance = 0;
	public float yDistance = 0;

	public float xDistanceLast = 0;
	public float yDistanceLast = 0;


	public float distance = 0;

	public float xDistanceChangeLast = 0;
	public float xDistanceChange = 0;
	public float cursorBank = 0;

	public float cursorDistanceChange = 0;
}
