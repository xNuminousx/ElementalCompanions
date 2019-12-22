# ElementalCompanions - Beta Phases
Companions to follow you, empower bending, and defend you! Use the command `/ec` to get started!

This plugin is currently in **early** beta phases. Some features are not finished, others may malfunction. There is no chat formatting or permissions. All features (durations, cooldowns, ranges, etc) are hardcoded; there is no configuration yet. Use at your own risk!

I'm allowing public, testing use of it now because I'm proud of this and want to share it :)

## Requirements
**Minecraft / Spigot** - 1.13.2 (*should work, idk?*) & 1.14.4. May also maintain compatibility with 1.15 versions.

**ProjectKorra** - 1.8.9
> If you use the downloadable `ProjectKorra-1.8.8.jar` from the forums it'll likely either break or not perform correctly.

## Companions / Companion Abilities
When a companion is enabled, it'll not be reactive. "Not be reactive" just means it'll float around and do nothing. In order for your companion to start reacting to things, you must set it to be reactive. Do this by executing the command `/ec reactive [true/false]`.

**Fire** - This is the companion I'm using to do most of my testing so this will be the most developed companion. Others will have lesser features than this one (most likely). This companion, when set to reactive, will shoot blasts of fire at nearby entities.

**Earth** - This companion, when set to reactive, will launch grounded entities into the air. Both the entity and companion owner **must** be on the ground in order for the companion to react.

**Water** - When set to reactive, this companion will have a chance to either shoot a blast at an entity or heal you. Currently, it's very strong. Plan on working out the kinks later.

**Air** - No abilities *yet*. This one may serve as a mobility booster.

## Player Abilities
I intend to give players certain abilities to be able to fight with Companions. In addition, I will also eventually allow Companion Abilities to interact with stock ProjectKorra abilities.

**Defuse** - Not developed *yet*. I intend for this ability to allow enemy players to "kill" a companion, but only if it's reactive. Passive companions should not be killed by this.

## Plans / Goals
Here I'll occasionally update some goals I come up with. This will serve as something to hopefully look forward to in the future.

**Reactive Feature** - This is an idea I'd really like to expand upon. I figure Companions can turn on their reactive state automatically when they sense their host is in danger or they perceive a threat. This can be done in several ways such as detecting a large damage loss or detecting a monster approaching. Also would turn off after X amount of time has passed with no threat taking place and return to passive mode.

**Water Abilities** - I'd like for the Water Companion to "loose health" as it uses its abilities since the companion is using itself as a source. So when it heals you it loses 2 health and when it shoots a blast it loses 1 health all out of 10. When it loses all of its health, it dies. Just an example, but that's what I'm thinking.
