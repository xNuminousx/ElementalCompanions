package me.numin.elementalcompanions.companions.air;

import com.projectkorra.projectkorra.Element;
import me.numin.elementalcompanions.companions.Companion;
import me.numin.elementalcompanions.utils.SoundHandler;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class AirCompanion extends Companion {

    private Location currentLocation;
    private SoundHandler elementalSounds, genericSounds;

    public AirCompanion(Player player) {
        super(player);

        this.currentLocation = getSpawn();
        this.elementalSounds = new SoundHandler(1000, 10);
        this.genericSounds = new SoundHandler(6000, 1);
    }

    @Override
    public Element getElement() {
        return Element.AIR;
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }

    @Override
    public String getName() {
        return "AirCompanion";
    }

    @Override
    public void animateMovement() {
        currentLocation = getMovement().moveAimlessly();

        if (!isSilenced()) {
            genericSounds.playAmbientCompanionSound(this);
            elementalSounds.playAmbientElementalCompanionSound(this);
        }

        currentLocation
                .getWorld()
                .spawnParticle(Particle.CLOUD, currentLocation, 1, 0, 0, 0, 0);

        currentLocation
                .getWorld()
                .spawnParticle(Particle.SPELL_INSTANT, currentLocation,1, 0.1, 0.1, 0.1, 0);
    }

    @Override
    public void advanceReaction() {
    }
}
