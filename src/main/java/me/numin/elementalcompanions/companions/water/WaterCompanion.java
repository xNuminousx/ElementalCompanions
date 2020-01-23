package me.numin.elementalcompanions.companions.water;

import com.projectkorra.projectkorra.Element;
import me.numin.elementalcompanions.abilities.companion.water.CompanionHeal;
import me.numin.elementalcompanions.abilities.companion.water.CompanionWaterBlast;
import me.numin.elementalcompanions.companions.Companion;
import me.numin.elementalcompanions.utils.randomizers.RandomChance;
import me.numin.elementalcompanions.utils.handlers.SoundHandler;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class WaterCompanion extends Companion {

    private Particle.DustOptions blue;
    private Location currentLocation;
    private SoundHandler soundHandler;

    private long currentTime;
    private long lastHealTime;
    private long reactBuffer;

    public WaterCompanion(Player player) {
        super(player);

        this.currentLocation = getSpawn();
        this.blue = new Particle.DustOptions(Color.fromRGB(50, 120, 255), 1);

        this.currentTime = System.currentTimeMillis();
        this.lastHealTime = System.currentTimeMillis();
        this.reactBuffer = 3000;
        this.soundHandler = new SoundHandler(this);
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
    public void animateMovement() {
        currentLocation = getMovementHandler().moveAimlessly();

        if (!isSilenced()) {
            soundHandler.playAmbientCompanionSound(6000, 1);
            soundHandler.playAmbientElementalCompanionSound(1000, 10);
        }

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
        if (System.currentTimeMillis() > currentTime + reactBuffer) {
            if (getPlayer().getHealth() < 5) {
                boolean canReact = new RandomChance(10).chanceReached();

                if (canReact && System.currentTimeMillis() - lastHealTime > 3000) {
                    lastHealTime = System.currentTimeMillis();
                    new CompanionHeal(this, getPlayer());
                    currentTime = System.currentTimeMillis();
                }
            } else {
                boolean canReact = new RandomChance(1).chanceReached();
                LivingEntity randomEntity = getRandomEntity(getLocation(), 20);

                if (canReact && randomEntity != null) {
                    new CompanionWaterBlast(this, randomEntity);
                    currentTime = System.currentTimeMillis();
                }
            }
        }
    }
}
