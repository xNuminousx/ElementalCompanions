package me.numin.elementalcompanions.companions.air;

import com.projectkorra.projectkorra.Element;
import me.numin.elementalcompanions.companions.Companion;
import me.numin.elementalcompanions.utils.handlers.SoundHandler;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class AirCompanion extends Companion {

    private Location currentLocation;
    private SoundHandler soundHandler;

    public AirCompanion(Player player) {
        super(player);

        this.currentLocation = getSpawn();
        this.soundHandler = new SoundHandler(this);
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
        currentLocation = getMovementHandler().moveAimlessly();

        if (!isSilenced()) {
            soundHandler.playAmbientCompanionSound(6000, 1);
            soundHandler.playAmbientElementalCompanionSound(1000, 10);
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
