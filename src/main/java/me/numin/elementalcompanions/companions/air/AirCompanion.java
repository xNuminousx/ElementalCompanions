package me.numin.elementalcompanions.companions.air;

import com.projectkorra.projectkorra.Element;
import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class AirCompanion extends Companion {

    private Location currentLocation;

    public AirCompanion(Player player) {
        super(player);

        this.currentLocation = getSpawn();
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
    public boolean isReactive() {
        return super.isReactive();
    }

    @Override
    public void animateMovement() {
        currentLocation = getMovement().moveAimlessly();

        playSound();

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
