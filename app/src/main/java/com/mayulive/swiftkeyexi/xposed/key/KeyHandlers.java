package com.mayulive.swiftkeyexi.xposed.key;

import android.graphics.RectF;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.commons.data.KeyDefinition;
import com.mayulive.swiftkeyexi.main.commons.data.KeyType;
import com.mayulive.swiftkeyexi.xposed.DebugSettings;
import com.mayulive.swiftkeyexi.xposed.Hooks;
import com.mayulive.swiftkeyexi.xposed.keyboard.KeyboardMethods;

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
			return;

		//Since this was moved to hook a method that gets called multiple times for every key,
		//we started occasionally getting some extra bogus keys.
		//Filter any keys that are 0,0,0,0, or that span the entire screen.
		if (hitbox.top == 0 && hitbox.bottom == 1)
			return;
		if (hitbox.left == 0 && hitbox.right == 1)
			return;
		if (hitbox.left == 0 &&
				hitbox.top == 0 &&
				hitbox.right == 0 &&
				hitbox.bottom == 0)
			return;


		if (DebugSettings.DEBUG_KEYS)
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

		KeyCommons.mLastTemplateKey = null;
		//With the key created, clear recycled variables
		//KeyCommons.sLastSymbolDefined = null;
	}

}
