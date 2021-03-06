package me.numin.elementalcompanions.companions.fire;

import com.projectkorra.projectkorra.Element;
import me.numin.elementalcompanions.abilities.companion.fire.CompanionFireBlast;
import me.numin.elementalcompanions.companions.Companion;
import me.numin.elementalcompanions.utils.randomizers.RandomChance;
import me.numin.elementalcompanions.utils.handlers.SoundHandler;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;

public class FireCompanion extends Companion {

    private Particle.DustOptions red;
    private Location currentLocation;
    private SoundHandler soundHandler;

    private long currentTime;
    private long reactBuffer;

    public FireCompanion(Player player) {
        super(player);
        this.currentLocation = getSpawn();
        this.currentTime = System.currentTimeMillis();
        this.reactBuffer = 3000;
        this.red = new Particle.DustOptions(Color.fromRGB(255, 0, 0), 1);
        this.soundHandler = new SoundHandler(this);
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
    public void animateMovement() {
        currentLocation = getMovementHandler().moveAimlessly();

        if (!isSilenced()) {
            soundHandler.playAmbientCompanionSound(6000, 10);
            soundHandler.playAmbientElementalCompanionSound(1000, 10);
        }

        currentLocation
                .getWorld()
                .spawnParticle(Particle.FLAME, currentLocation, 3, 0.15, 0.15, 0.15, 0.009);
        currentLocation
                .getWorld()
                .spawnParticle(Particle.SMOKE_NORMAL, currentLocation, 1, 0.1, 0.1, 0.1, 0);

        if (isReactive())
            currentLocation
                    .getWorld()
                    .spawnParticle(Particle.REDSTONE, currentLocation, 2, 0.1, 0.1, 0.1, 0, red);
    }

    @Override
    public void advanceReaction() {
        boolean canReact = new RandomChance(1).chanceReached();
        LivingEntity randomEntity = getRandomEntity(getLocation(), 20);

        if (canReact && randomEntity != null && System.currentTimeMillis() > currentTime + reactBuffer) {
            new CompanionFireBlast(this, randomEntity);
            currentTime = System.currentTimeMillis();
        }
    }
}
