package me.numin.elementalcompanions.abilities.companion.water;

import me.numin.elementalcompanions.abilities.companion.CompanionAbility;
import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class CompanionHeal extends CompanionAbility {

    private Location companionLocation;
    private Location currentLocation;
    private Vector direction;

    private double speed;

    public CompanionHeal(Companion companion, LivingEntity target) {
        super(companion, target);

        this.companionLocation = companion.getLocation();
        this.currentLocation = companionLocation.clone();
        this.direction = new Vector(1, 0, 0);

        this.speed = 0.2;
    }

    @Override
    public Companion getCompanion() {
        return companion;
    }

    @Override
    public LivingEntity getTarget() {
        return target;
    }

    @Override
    public Location getLocation() {
        return companionLocation;
    }

    @Override
    public void advanceAbility() {
        Location hostLocation = getCompanion().getPlayer().getEyeLocation();
        direction.add(hostLocation.toVector().subtract(currentLocation.toVector()).multiply(speed)).normalize();
        double hostDistance = currentLocation.distance(hostLocation);

        if (hostDistance > 0.5) {
            currentLocation.add(direction.clone().multiply(speed));
            currentLocation
                    .getWorld()
                    .spawnParticle(Particle.WATER_WAKE, currentLocation, 1, 0, 0, 0, 0);
        } else {
            if (getTarget() instanceof Player) {
                Player player = (Player)getTarget();
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1500, 1));
            }
            removeAbility();
        }
    }
}
