package me.numin.elementalcompanions.utils;

import com.projectkorra.projectkorra.Element;
import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundHandler {

    private boolean isPlayingSound;
    private double percentChance;
    private long increment;
    private long timeHolder;

    /**
     * Used to localize sounds. Allows for specific, stylized sounds to play randomly after every interval.
     * Also restricts the amount of sounds playing per instance at any one time to 1 so there's no overlap.
     *
     * @param increment The amount of time required in between each sound execution.
     * @param percentChance The chance a sound has to play after a full increment has passed.
     */
    public SoundHandler(long increment, double percentChance) {
        this.increment = increment;
        this.percentChance = percentChance;
        this.timeHolder = System.currentTimeMillis();
        this.isPlayingSound = false;
    }

    public boolean canPlaySound() {
        return (System.currentTimeMillis() - timeHolder > increment) &&
                new RandomChance(percentChance).chanceReached() &&
                !isPlayingSound;
    }

    public void playAmbientElementalCompanionSound(Companion companion) {
        Element element = companion.getElement();
        CompanionSounds sound;

        if (element.equals(Element.AIR)) sound = CompanionSounds.AIR;
        else if (element.equals(Element.EARTH)) sound = CompanionSounds.EARTH;
        else if (element.equals(Element.FIRE)) sound = CompanionSounds.FIRE;
        else if (element.equals(Element.WATER)) sound = CompanionSounds.WATER;
        else return;

        if (canPlaySound()) {
            companion.getLocation().getWorld().playSound(companion.getLocation(), sound.getSound(), sound.getVolume(), sound.getPitch());
            this.isPlayingSound = true;
            this.timeHolder = System.currentTimeMillis();
        } else isPlayingSound = false;
    }

    public void playAmbientCompanionSound(Companion companion) {
        if (canPlaySound()) {
            companion.getLocation().getWorld().playSound(companion.getLocation(), Sound.ENTITY_GHAST_AMBIENT, 0.1F, 2F);
            this.isPlayingSound = true;
            this.timeHolder = System.currentTimeMillis();
        } else isPlayingSound = false;
    }

    public enum CompanionSounds {

        AIR(Sound.ENTITY_HORSE_BREATHE, 0.1F, 1),
        EARTH(Sound.BLOCK_STONE_HIT, 0.1F, 0.2F),
        FIRE(Sound.BLOCK_FIRE_AMBIENT, 0.2F, 1),
        WATER(Sound.BLOCK_WATER_AMBIENT, 0.1F, 1.2F);

        private Sound sound;
        private float volume, pitch;

        CompanionSounds(Sound sound, float volume, float pitch) {
            this.sound = sound;
            this.volume = volume;
            this.pitch = pitch;
        }

        public Sound getSound() {
            return sound;
        }

        public float getVolume() {
            return volume;
        }

        public float getPitch() {
            return pitch;
        }
    }
}
