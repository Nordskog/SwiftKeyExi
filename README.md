# Exi for Swiftkey

Exi for Swiftkey is an Xposed Framework module that adds a number of features to the Swiftkey keyboard.

//TODO Xposed remove link
//TODO XDA support thread link

A short list of features:

* Swipe selection
  * Cursor movement
  * Selection (Gesture or swipe-from-key)
  * Multiple modes (Anywhere, spacebar, hold-and-swipe)
* Custom shortcuts
  * Multiple words for each shortcut
  * Can be inserted after primary suggestion
  * Can trigger on suggestions, not just verbatrim input
  * Can be inserted into flow suggestions
  * Supports importing from file (Tab or space delimited)
* Hotkeys (Ctrl + key )
  * Define any key as CTRL
  * Supports Select all / Cut / Copy / Paste / Go to end
* Quick Actions
  * Display circular quick-menu by swiping up from spacebar
  * Trigger corresponding hotkey by swiping from spacebar to key
* Popup-keys
  * Alternate characters can be added and positioned anywhere
* Emoji
  * Panels are fully customizable (Icon, position, emoji, row count)
  * Supports importing from file (line-break delimited)
  * Emoji size can be configured
* Other
  * Remove empty space next to suggestions
  * Prevent period key from triggering on tap
  * Cursor stays where you place it, rather than moving to end word
  
## Libraries used

* Rovo89's Xposed Framework

* kizitonwose's [ColorPreference](https://github.com/kizitonwose/colorpreference)

* [FreeType](https://www.freetype.org/)

* My own [Class Hunter](https://github.com/Nordskog/ClassHunter)

## Building

The app module loads its signing config from keystore/signing.gradle. 
The config and keystore are not provided, and you will need to create them yourself before building.
