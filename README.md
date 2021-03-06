# Exi for Swiftkey

### This project is mostly abandoned, and only supports older versions of Swiftkey and Swiftkey Beta, namely between 7.5.7.4 and 7.6.7.4

Exi for Swiftkey is an Xposed Framework module that adds a number of features to the Swiftkey keyboard.

* [XDA Support Thread](https://forum.xda-developers.com/xposed/modules/mod-exi-swiftkey-t3650598)

* [Xposed Module Repository page](http://repo.xposed.info/module/com.mayulive.swiftkeyexi)

A short list of features:

* Swipe selection
  * Cursor movement (Horizontal and vertical)
  * Selection (Gesture or swipe-from-key)
  * Multiple modes (Anywhere, spacebar, hold-and-swipe)
* More Suggestions
  * Suggestions bar can be scrolled to view all suggestions
* Custom shortcuts
  * Multiple shortcuts for same word
  * Trigger on verbatim input, suggestions, or flow
  * Dictionary importable from file (tab or space delimited)
* Hotkeys (Ctrl + key )
  * Define any key as CTRL
  * Supports Select all / Cut / Copy / Paste / Go to end / Undo / Redo / Toggle lots of stuff
* Quick Actions
  * Display circular quick-menu by swiping up from spacebar
  * Trigger corresponding hotkey by swiping from spacebar to key
* Popup-keys
  * Customize popup alt-characters
* Emoji
  * Panels are fully customizable (Icon, position, emoji, row count)
  * Supports importing from file (line-break delimited)
  * Configurable font size
* Gifs
  * Remove Bing url redirect
  * Gifs from more sources
  * Disable safe-search
* Intents ( Used via tasker )
  * Set theme
  * Toggle incognito mode
  * Toggle vibrate
* Other
  * Remove empty space next to suggestions
  * Prevent period key from triggering on tap
  * Cursor stays where you place it, rather than moving to end word
  * Ability to remove auto-space after punctuation
  * Set keyboard transparency
  * Remap hardware keys
  * Customizable search
  * Customize size ( landscape, portrait, keyboard/emoji/gif panels separately )
  * Text selection with arrow keys ( mod + arrow key )
  
  
## Libraries used

* Rovo89's Xposed Framework

* QuadFlask's [Color Picker](https://github.com/QuadFlask/colorpicker)

* [FreeType](https://www.freetype.org/)

* My own [Class Hunter](https://github.com/Nordskog/ClassHunter)

## Building

The app module loads its signing config from keystore/signing.gradle. 
The config and keystore are not provided, and you will need to create them yourself before building.
