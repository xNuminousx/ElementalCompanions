package me.numin.elementalcompanions.companions.water;

import com.projectkorra.projectkorra.Element;
import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class WaterCompanion extends Companion {

    private Location currentLocation;

    public WaterCompanion(Player player) {
        super(player);

        this.currentLocation = getSpawn();
    }

    @Override
    public Element getElement() {
        return Element.WATER;
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }

    @Override
    public String getName() {
        return "WaterCompanion";
    }

    @Override
    public boolean isReactive() {
        return super.isReactive();
    }

    @Override
    public void animateMovement() {
        currentLocation = getMovement().moveNaturally();
        currentLocation.getWorld().spawnParticle(Particle.WATER_WAKE, currentLocation, 5, 0.1, 0.1, 0.1, 0);
    }

    @Override
    public void advanceReaction() {
    }
}
