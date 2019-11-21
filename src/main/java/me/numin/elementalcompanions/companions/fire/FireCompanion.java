package me.numin.elementalcompanions.companions.fire;

import com.projectkorra.projectkorra.Element;
import me.numin.elementalcompanions.abilities.companion.CompanionAbility;
import me.numin.elementalcompanions.abilities.companion.fire.CompanionFireBlast;
import me.numin.elementalcompanions.companions.Companion;
import me.numin.elementalcompanions.utils.RandomChance;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;

public class FireCompanion extends Companion {

    private Particle.DustOptions red;
    private Location currentLocation;

    private long currentTime;
    private long reactBuffer;

    public FireCompanion(Player player) {
        super(player);
        this.currentLocation = getSpawn();
        this.currentTime = System.currentTimeMillis();
        this.reactBuffer = 3000;
        this.red = new Particle.DustOptions(Color.fromRGB(255, 0, 0), 1);
    }

    @Override
    public Element getElement() {
        return Element.FIRE;
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }

    @Override
    public String getName() {
        return "FireCompanion";
    }

    @Override
    public boolean isReactive() {
        //TODO: Implement configurable hostile mode (can be turned on or off through configuration)
        //TODO: Maybe hostility can be environmental too?
        return super.isReactive();
    }

    @Override
    public void animateMovement() {
        currentLocation = getMovement().moveNaturally();
        currentLocation
                .getWorld()
                .spawnParticle(Particle.FLAME, currentLocation, 2, 0.2, 0.2, 0.2, 0.009);
        currentLocation
                .getWorld()
                .spawnParticle(Particle.SMOKE_NORMAL, currentLocation, 1, 0.1, 0.1, 0.1, 0);

        if (isReactive())
            currentLocation
                    .getWorld()
                    .spawnParticle(Particle.REDSTONE, currentLocation, 2, 0, 0, 0, 0, red);
    }

    @Override
    public void advanceReaction() {
        // Skeleton for the random chance to shoot an enemy
        if (CompanionAbility.activeAbilities.containsKey(this))
            return;

        boolean canReact = new RandomChance(1).chanceReached();
        LivingEntity randomEntity = getRandomEntity(getLocation(), 20);

        if (canReact && randomEntity != null && System.currentTimeMillis() > currentTime + reactBuffer) {
            new CompanionFireBlast(this, randomEntity);
            currentTime = System.currentTimeMillis();
        }
    }
}
