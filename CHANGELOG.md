# Changelog Infinity Buttons

## 4.0.4

- Partially added translations for:
  - Korean ([gisellevonbingen](https://github.com/TeamDiopside/InfinityButtons/pull/33))
  - Japanese ([Furikorifuto](https://github.com/TeamDiopside/InfinityButtons/pull/35))
  - German ([DerCoderJo](https://github.com/TeamDiopside/InfinityButtons/pull/39))

Fabric:
- Fixed a crash using Jade ([Snownee](https://github.com/TeamDiopside/InfinityButtons/pull/40))

## 4.0.3

Forge:
- Removed Secret Buttons of Upgrade Aquatic's Kelpy Brick variants as they have been removed from the original mod.

## 4.0.2

Forge:
- Bookshelf Secret Buttons made from Abnormals wood types are now able to use Woodworks.
- Fixed Hoglin Mount Button missing model when turned off. (1.20.1)

## 4.0.1

- Fixed missing textures on Purpur Block Secret Button.

Fabric:
- Fixed an incompatibility with Lanterns Belong on Walls.
- Fixed a Mixin warning occuring on servers. (1.19.2)

Forge:
- Added Arid Sand Buttons for Atmospheric.
- Fixed a possible crash on startup. (Big thanks to murao.kun for testing my jars on this issue!)
- Fixed Ender Torch Buttons not showing up in the creative tab. (1.16.5)
- Fixed Lantern Button click sounds. (1.19.3+)

## 4.0.0

- Ported to 1.20.1

- Fixed weird pitch on Emergency Button sounds when using a resource pack.
- Emergency Buttons can now be configured to have a custom range or be heard globally.
- Fixed Prismarine Buttons not being able to be waterlogged properly.
- When using Jade, Secret Buttons will now show up as their original block.
- Added Console Buttons & Toggle Buttons.
- Renamed Lamp Lever to Lamp Toggle Button
- Emergency Buttons will now make Villagers run towards their bed like bells do. (Configurable)
- Fixed Safety Buttons not being able to open when using a shield.
- Fixed some blocks not working when using 'Every Compat (Wood Good)'.
- Made some small changes to language values.
- Added Lantern Buttons & Levers.
- Added Letter Button & Toggle Button.
- Added Purpur Block Secret Button.
- Added Create compat. (1.18.2, 1.19.x & Forge 1.20.1)
- Overhauled Copper Buttons. (1.18.2+)
- Fancy Emergency Buttons are now included in their corresponding tags.
- Added Advancements.
- Metallic Buttons now require the correct Tool Tier to drop.
- Added more Buttons to Vanilla Tabs. (1.19.3+)
- Fixed Small Brick Textures being rotated incorrectly.
- Changed Diamond Button Particle.
- Fixed Buttons having wrong Piston Behaviour.
- Shifted Lamp Button inventory model a bit.
- Alarm and doorbell sounds are now actually directional (stereo) and decrease in volume when standing further away.
- Amplified the alarm feel by adding some reverb to the alarm.
- Fixed typo in Dutch localization.
- Added Cherry Large Button (1.20)
- Added Bamboo Large Button (1.20)
- Added Cherry Plank Secret Button (1.20)
- Change tags to match Vanilla (1.20)
- (Mostly) Added Spanish translations by SoyTutta!
- The Creative Tab has been sorted correctly. (1.16.5-1.19.2)

Fabric Only:
- Added Nether's Delight compat for 1.19.x
- Fixed not being able to use the latest version of Cloth Config API (1.18.2)

Forge Only:
- Added assets for EMI. (1.18.2+)
- Fixed a weird symbol appearing in the mod description.
- Changed background in config menu.
- Added integration for Copperized JEI Categories. (1.18.2-1.19.2)
- Added compat for:
  - Atmospheric 1.18.2 & 1.19.x
  - Autumnity 1.18.2 & 1.19.x
  - Buzzier Bees 1.18.2 & 1.19.x
  - Clayworks 1.18.2 & 1.19.x
  - Environmental 1.18.2 & 1.19.x
  - Neapolitan 1.18.2 & 1.19.x
  - Savage & Ravage 1.18.2 & 1.19.x
  - Upgrade Aquatic 1.18.2 & 1.19.x

## 3.1.0

- Fixed the Fabric version crashing when loaded on its own.
- Bookshelf Secret Buttons top faces are now also fixed.
- Secret Buttons now only work when you click on the correct side.
- You are now able to place blocks on Secret Buttons without having to sneak.
- Concrete Powder Buttons now group together in the recipe book.
- Added Concrete Powder Buttons tags.

Forge Only:
- Fixed some language issues with the Diamond Particle Config.

## 3.0.0

- Ported to 1.19.3.
- Added Deepslate, Granite, Diorite, Andesite, Calcite, Tuff and Dripstone Buttons.
- Added Concrete Powder Buttons, which act the same as Sand Buttons.
- Added Lamp Button and Lever.
- Added Carpenter compat. (1.18.2)
- Added Stonecutter recipes.
- Added an actionbar message to Safety Buttons when you don't sneak.
- Added WoodGood compat.
- Fixed texture rotation on Secret Buttons.
- Changed the order of colored buttons, to match with 1.19.3.
- Changed Prismarine Button Recipes.
- Sorted tags to improve recipe compatibility.
- Bookshelf Secret Buttons now provide Enchanting Power.

1.19.3:
- Added vanilla buttons to the beginning of the Infinity Buttons tab.
- Added Large Buttons to the Building Blocks tab.
- Added Redstone Torch lever and Copper Buttons to the Redstone tab.
- Added all colored buttons to the Colored Blocks tab.
- Crimson and Warped Buttons now use Nether wood sounds.

Fabric Only:
- Ported to 1.18.2.
- Added tag models for EMI.
- Fixed the Emergency Button tooltip mentioning Safety Buttons.
- Added compat for Nether's Delight Fabric. (1.18.2)

Forge Only:
- Added Mangrove buttons. (1.19.x)
- Added Woodworks compat. (1.18.2 and 1.19.x)
- Added compat for Abundance, Atmospheric, Bamboo Blocks, Bayou Blues, Enhanced Mushrooms, Upgrade Aquatic, Buzzier Bees, Neapolitan and Savage & Ravage. (1.16.5)
- Expanded Quark compat.
- Changed things in the way compat works. All loot table errors have been fixed! More info on compat soon! Be sure to back up your world! (Special Thanks to MehVahdJukaar for helping out!)
- Fixed an issue causing compat blocks to have no tags. (1.18.2 and 1.19.x)
- Fixed a black outline on Torch buttons when mod resources are not set correctly. (1.19.x)
- Merged Creative Tabs

## 2.2.1

- Fixed an issue causing Safety Buttons being unable to function on Dedicated Servers.

## 2.2.0

- Added Sand, Red Sand and Gravel Buttons. They can only be pressed once.
- Added Safety Buttons in all colors. To unlock it, shift-click to open the glass casing.
- Fixed Emergency Button breaking particles being black.
- Fixed Torch Buttons and Levers on the wall floating and breaking when not supposed to.

Forge only:
- Ported to 1.19.x!
- Fixed an issue causing blocks to be unmineable. (1.18.2)
- Expanded Quark Compat.

## 2.1.0

- Added Fancy Emergency Button. Taste the rainbow!
- Added Prismarine Buttons, which can be waterlogged.
- Added shiny Diamond Buttons, just to flex.
- The particles of the Diamond Buttons can be disabled in the config.
- Torch Buttons and Levers can now be placed on the floor.
- Piglins now get angry when you break Gold Buttons.
- Fixed several bugs!

Forge only:
- Ported to 1.18.2!
- Added Autumnity compat (1.16.5 only).
- Added Nether's Delight compat.
- Expanded Quark compat.
- Fixed Endergetic recipes trying to load when not supposed to (1.16.5 only).

## 2.0.0

- Ported to Forge! From now on, every version will have its Forge counterpart as well.
- Added more Secret Buttons: Bookshelf, Planks and more!
- Added Torch Buttons and Torch Levers. Also in soul and redstone form.
- Added Doorbell and Doorbell Button.
- Edited the vanilla Button recipe to produce 4 Buttons instead of 1.
- Edited the small Button recipe to produce 2 Buttons instead of 1.
- Fixed the side textures of the item model of Large Buttons.

Forge only:
- Added compatibility for blocks from Quark, Environmental and The Endergetic Expansion.

## 1.5.0

- Added Secret Buttons, which blend into walls.
  - Currently contains all Brick variants, like Stone Bricks and Quartz Bricks.

## 1.4.1

- Added a config to disable tooltips.

## 1.4.0

- Added Iron and Gold buttons, which can only be pressed by shooting an arrow at it.
- Added tooltips to the iron, gold, emerald and sticky copper buttons.
- Increased the volume of some sounds.

## 1.3.1

- Added a config.
- Added an alarm sound to the Emergency Buttons, which is toggleable in the config.
- The mod has a new dependency: oωo lib.

## 1.3.0

- Added regular sized Copper and Emerald Buttons.

## 1.2.2

- Fixed Copper Large Buttons recipes conflicting with vanilla recipes.
- Fixed Black Emergency Buttons breaking particles containing red pixels.

## 1.2.1

- Fixed Emergency Buttons breaking particles only being the red variant.
- Added Chinese and Dutch translations.

## 1.2.0

- Added Emergency Buttons in every color.
- Added Emerald Button, which has a random pulse length between 0.5 and 5 seconds.
- Changed the Blackstone Large Button texture.

## 1.1.0

- Added Sticky Copper Large Buttons.
- Fixed recipe advancements not working.
- Changed the Copper Large Button texture.

## 1.0.0

- The initial release.
