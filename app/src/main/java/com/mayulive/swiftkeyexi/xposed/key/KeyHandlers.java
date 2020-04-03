package com.mayulive.swiftkeyexi.xposed.key;

import android.graphics.RectF;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.xposed.DebugTools;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;
import com.mayulive.swiftkeyexi.xposed.popupkeys.PopupkeysCommons;

import java.util.List;

/**
 * Created by Roughy on 9/9/2017.
 */

public class KeyHandlers
{

	private static String LOGTAG = ExiModule.getLogTag(KeyHandlers.class);

	protected static void handleKeyConstructed(Object returnKey, KeyCommons.TemplateKey template) throws Throwable
	{
		RectF hitbox = KeyCommons.getKeyArea(returnKey);

		if (hitbox == null)	//No hitbox, no key we care about
		{
			return;
		}

		boolean isPhysicalKey = true;

		// This method does not work for weird layouts, as the spacebar is part of a separate layout
		if ( !KeyboardMethods.isLayoutWeird() )
		{
			if ( hitbox.top == 0f & hitbox.bottom == 1.0f )
			{
				// Key fills space. Only popups do this.
				isPhysicalKey = false;
			}

		}


		// Upper and lower case popups are delimited by a empty, 0-size key
		// If we have iterated the popup counter and it has not been reset yet, then we've already handled lower case.
		if ( hitbox.width() == 0 && hitbox.height() == 0)
		{
			KeyCommons.mLastKeyPopupLowerCaseKeyDefinitionsProcessed = KeyCommons.mLastKeyPopupKeyIndexCounter != 0;
			KeyCommons.mLastKeyPopupKeyIndexCounter = 0;
			return;
		}


		//Since this was moved to hook a method that gets called multiple times for every key,
		//we started occasionally getting some extra bogus keys.
		//Filter any keys that are 0,0,0,0, or that span the entire screen.
		//if (hitbox.top == 0 && hitbox.bottom == 1)	// This happens in weird layouts and popups, so allow
		//	return;
		if (hitbox.left == 0 && hitbox.right == 1)	// This should never happen, I think
			return;
		if (	hitbox.left == 0 &&		// Popps have an empty spacer key like this, but that is handled above, so skip.
				hitbox.top == 0 &&
				hitbox.right == 0 &&
				hitbox.bottom == 0)
			return;


		if (DebugTools.DEBUG_KEYS)
		{
			Log.i(LOGTAG, "Key defined. Tag: "+template.tag);
			Log.i(LOGTAG, "Key defined. Type: "+template.type);
			Log.i(LOGTAG, "Key defined. Content: "+template.content);

			Log.i(LOGTAG, "Hitbox. Tag: "+hitbox);
		}

		if (KeyType.SPACE == template.type)
		{

			if (KeyboardMethods.isLayoutWeird())
			{
				// Figure out the real positions of the keys in weird layouts
				// would be entirely too much work, so they will not support
				// swipe-from-space-to-key hotkeys. They should still support
				// the hotkey menu though, meaning we at the very least need to
				// figure out where the spacebar is.

				// In weird layouts, the bottom row of keys is in its own window,
				// and hitbox coordinates are for positions inside it. If arrow
				// keys are enabled they go in the same box below this row,
				// so spacebar will take up half the vertical space, rather than all of it).
				// In horizontal mode the arrows go on the right side instead.
				// The height of the spacebar is the same regardless, only being squished
				// if the user also enabled the number row. Maybe take that into consideration later.



				//If it fills its box it's at the bottom, if not there are two rows.
				boolean spacebarAtBottom = hitbox.height() > 0.75f;

				if (spacebarAtBottom)
				{
					//Space key at the very bottom in a normal layout, without number row
					//Area: RectF(0.25250053, 0.75625, 0.74750054, 0.99375) }

					hitbox = new RectF(hitbox.left, 0.75625f, hitbox.right, 0.99375f);


				}
				else
				{	//With arrow keys row
					//Hitbox: RectF(0.25250053, 0.6368421, 0.74750054, 0.8368421)
					hitbox = new RectF(hitbox.left, 0.6368421f, hitbox.right, 0.8368421f);
				}
			}
		}


		{

			KeyDefinition newKey = new KeyDefinition(template.content, template.type, hitbox);

			if (template.tag != null)
				newKey.tag = template.tag;

			if (!isPhysicalKey)
			{
				// This is a popup key, but the template belongs to the parent, physical key.
				// Change the type and tag accordingly
				newKey.type = KeyType.POPUP;
				newKey.tag = "POPUP";

				// Get the actual contents of these popup keys, which we stored when messing with popups.

				List<String> popupStrings = KeyCommons.mLastKeyPopupLowerCaseKeyDefinitionsProcessed ? PopupkeysCommons.mLastOrderedUpperasepopups : PopupkeysCommons.mLastOrderedLowerCasepopups;
				if ( popupStrings != null && !popupStrings.isEmpty() )
				{
					if ( KeyCommons.mLastKeyPopupKeyIndexCounter < popupStrings.size() )
					{
						newKey.content = popupStrings.get( KeyCommons.mLastKeyPopupKeyIndexCounter );
					}
				}

				// Keep track of which popup key we are on. Reset aboe and on new key.
				KeyCommons.mLastKeyPopupKeyIndexCounter++;
			}

			KeyCommons.addKeyDefinition(returnKey, newKey);
			KeyCommons.mLastKeyDefined = newKey;

			//If we can't track layout changes, we shouldn't bother with this.
			if (Hooks.baseHooks_layoutChange.isRequirementsMet())
			{
				KeyCommons.HitboxMap map = KeyCommons.getHitboxMap();

				//Don't bother adding anything but space for weird layouts
				if (map != null && ( !KeyboardMethods.isLayoutWeird() || template.type == KeyType.SPACE ) )
				{
					String key = "";
					if (template.content != null)
						key = template.content;
					if (key.isEmpty())
					{
						key = template.tag+System.identityHashCode(returnKey);
					}

					map.put(key, newKey);
				}
			}
		}

		// Seems we're okay without clearing now, and in fact rely on not clearing for popups to be processed.
		// KeyCommons.mLastTemplateKey = null;
	}

}
