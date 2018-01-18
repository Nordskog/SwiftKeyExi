# Changelog
All notable changes to this project will be documented in this file.  
Dates are in the format dd/mm/yyyy
## [1.3.3] - 18-01-2018

### Fixed
 - Keys being input when attempting to trigger 2-finger gesture swipe selection
 - Emoji failing to load in Android Oreo (8.0) and above
 - Both fingers controlling the same cursor when starting selection from the end of a text block.
 
## [1.3.2] - 15-01-2018

### Fixed
 - More suggestions and removal of black bars surrounding candidates targeting >6.7.4.31
 - Fixed keys being input before swipe in hold-and-swipe modes
 - Fixed new emoji back button being too small ( >6.7.5.28)
 
### Added
 - Toggle to enable/disable vibration when inputting emoji (Exi panel only)
 - Keyboard size multiplier (Range 0.3 to 3.0)

### Changed
 - Recent Emoji panel can now be cleared like the other user-created panels

## [1.3.1] - 06-12-2017

### Changed
- Device will now vibrate when you input an emoji
- Shortcuts will no longer be ellipsized
- Translations updated, Russian/Polish now complete
- More file types allowed when selecting custom sounds

## [1.3.0] - 30-11-2017

### Fixed
- Cursor sometimes being to wrong position when inserting shortcut
- Popups not respecting case (Upper can also be set manually)
- Shortcuts being inserted into flow even when disabled

### Added
- Option to set custom keypress sounds
- Number-row swipe-selection mode
- RTL language support for swipe selection
- Option to disable cursor jumping to end of word
- Option to display NSFW Gifs
- Option to disable full-screen keyboard mode
- Polish translation (Thanks to xda user )
- French translation (Thanks to xda user )

## [1.2.1] - 02-11-2017

### Fixed
- Some emoji rendering as text instead of bitmap-emoji
- Emoji losing diverse modifiers when using "add-all emoji to panel"
- Cursor not jumping to end of word when tapping it on-screen

### Added
- Ability to choose between pre- and post-nougat stock emoji panels
- Option to remove existing popup keys
- Russian translation (Thanks to xda user alex_long)
- Brazilian Portuguese translation (Thanks to xda user X_hunter)

## [1.2.0] - 28-10-2017

### Fixed
- More suggestions
- Dark/Light theming in emoji panel
- Overlay hook sometimes not being called, breaking the quick-menu

### Added
- Diverse Emoji support
- Icons to all menus
- Ability to partially restore stock configuration

### Changed
- Emoji panels to match Nougat
- Stock Emoji panels to be unmodifiable
- Emoji menus to be more intuitive

### Broke
- Support for Swiftkey 6.6.7.24

## [1.1.1] - 02-10-2017
### Fixed
- Crash when flow shortcuts enabled

## [1.1.0] - 01-10-2017
### Fixed
- Disable auto-space after punctuation accidentally removing all punctuation rules
- Numerous style and theming issues
- Swipe-to-hotkey sometimes not working properly

### Added
- Ability to configure the Quick-Menu 
- Go-to-start text action

## [1.0.3] - 10-09-2017
### Fixed
- Compatibility targeting Swiftkey Beta 6.6.7.28

## [1.0.2] - 24-08-2017
### Fixed
- Quick Menu triggering with the wrong finger
- Custom actions with the Shift key not working in some langauges (e.g. Korean)
- ;-) emoticon having been incorrectly entered as ,-) ( Will not affect existing installs )
- Emoji replacing composing text instead of inserting cleanly

### Added
- Verical cursor movement when swiping
- Ability to disable auto-space after punctuation

## [1.0.1] - 08-08-2017
### Fixed
- Popups always being upper case. They are now input as-is
- Fontloader redundantly loading fonts on every launch

### Added
- Button to open keyboard in most sections of the app

### Changed
- Redesigned Swipe Speed slider
- Menu title and descriptions changed to be less confusing

## [1.0.0] - 02-08-2017
### Added
- Initial Release
