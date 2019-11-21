package me.numin.elementalcompanions.companions.air;

import com.projectkorra.projectkorra.Element;
import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Random;

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
        currentLocation = getMovement().moveNaturally();
        currentLocation.getWorld().spawnParticle(Particle.CLOUD, currentLocation, 1, 0, 0, 0, 0);
    }

    @Override
    public void advanceReaction() {
        int random = new Random().nextInt(100);
        if (random < 10) {
            getLocation().getWorld().spawnParticle(Particle.CRIT_MAGIC, getLocation(), 5, 0.1, 0.1, 0.1, 0);
        }
    }
}
