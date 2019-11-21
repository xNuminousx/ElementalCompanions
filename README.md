# ElementalCompanions - Beta Phases
Companions to follow you, empower bending, and defend you! Use the command `/ec` to get started!

This plugin is currently in **early** beta phases. Some features are not finished, others may malfunction. There is no chat formatting or permissions. All features (durations, cooldowns, ranges, etc) are hardcoded; there is no configuration yet. Use at your own risk!

I'm allowing public, testing use of it now because I'm proud of this and want to share it :)

## Requirements
**Minecraft / Spigot** - 1.13.2 (*should work, idk?*) & 1.14.4

**ProjectKorra** - 1.8.9 (*the unreleased 1.13 branch of the PK repo or if you have a 1.14.4 compatible one*)
> If you use the downloadable `ProjectKorra-1.8.8.jar` from the forums it'll likely either break or not perform correctly.

## Companions / Companion Abilities
When a companion is enabled, it'll not be reactive. "Not be reactive" just means it'll float around and do nothing. In order for your companion to start reacting to things, you must set it to be reactive. Do this by executing the command `/ec reactive [true/false]`.

**Fire** - This is the companion I'm using to do most of my testing so this will be the most developed companion. Others will have lesser features than this one (most likely). This companion, when set to reactive, will shoot blasts of fire at nearby entities.

**Earth** - This companion, when set to reactive, will launch grounded entities into the air. Both the entity and companion owner **must** be on the ground in order for the companion to react.

**Water** - Not developed *yet*. Right now I aim for this companion to be a healer of sorts.

**Air** - Not developed *yet*. This one may serve as a mobility booster.

## Player Abilities
I intend to give players certain abilities to be able to fight with Companions. In addition, I will also eventually allow Companion Abilities to interact with stock ProjectKorra abilities.

**Defuse** - Not developed *yet*. I intend for this ability to allow enemy players to "kill" a companion, but only if it's reactive. Passive companions should not be killed by this.
