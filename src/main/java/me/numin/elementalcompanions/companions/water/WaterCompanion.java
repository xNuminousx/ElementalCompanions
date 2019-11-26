package me.numin.elementalcompanions.companions.water;

import com.projectkorra.projectkorra.Element;
import me.numin.elementalcompanions.companions.Companion;
import me.numin.elementalcompanions.utils.RandomChance;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class WaterCompanion extends Companion {

    private Particle.DustOptions blue;
    private Location currentLocation;

    public WaterCompanion(Player player) {
        super(player);

        this.currentLocation = getSpawn();
        this.blue = new Particle.DustOptions(Color.fromRGB(50, 120, 255), 1);
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
        currentLocation = getMovement().moveAimlessly();

        playSound();

        currentLocation
                .getWorld()
                .spawnParticle(Particle.WATER_WAKE, currentLocation, 5, 0.1, 0.1, 0.1, 0);

        if (new RandomChance(5).chanceReached())
            currentLocation
                    .getWorld()
                    .spawnParticle(Particle.DRIP_WATER, currentLocation, 1, 0.1, 0.1, 0.1, 0);

        currentLocation
                .getWorld()
                .spawnParticle(Particle.REDSTONE, currentLocation, 2, 0.1, 0.1, 0.1, 0, blue);
    }

    @Override
    public void advanceReaction() {
    }
}
