package me.numin.elementalcompanions.abilities.companion.fire;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.FireAbility;
import me.numin.elementalcompanions.abilities.companion.CompanionAbility;
import me.numin.elementalcompanions.companions.Companion;
import me.numin.elementalcompanions.utils.randomizers.RandomChance;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class CompanionFireBlast extends CompanionAbility {

    private Location currentLocation;
    private Location origin;
    private Location targetOrigin;
    private Vector direction;

    private double damage;
    private double speed;

    public CompanionFireBlast(Companion companion, LivingEntity target) {
        super(companion, target);

        this.currentLocation = companion.getLocation().clone();
        this.origin = currentLocation.clone();
        this.targetOrigin = target.getLocation();
        this.direction = new Vector(1, 0, 0);
        this.damage = 2;
        this.speed = 0.5;

        currentLocation.getWorld().playSound(currentLocation, Sound.ENTITY_GHAST_SHOOT, 0.1F, 0.5F);
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
        return currentLocation;
    }

    @Override
    public void advanceAbility() {
        direction.add(targetOrigin.toVector().subtract(currentLocation.toVector()).multiply(speed)).normalize();
        if (currentLocation.distance(origin) <= 20) {
            currentLocation.add(direction.clone().multiply(speed));
            currentLocation.getWorld().spawnParticle(Particle.FLAME, currentLocation, 2, 0.1, 0.1, 0.1, 0.009);

            if (new RandomChance(10).chanceReached())
                FireAbility.playFirebendingSound(currentLocation);

            if (currentLocation.distance(targetOrigin) < 0.1 ||
                    getLocation().getBlock().isLiquid() ||
                    GeneralMethods.isSolid(getLocation().getBlock())) {
                removeAbility();
                return;
            }

            for (Entity entity : GeneralMethods.getEntitiesAroundPoint(currentLocation, 1)) {
                if (entity instanceof LivingEntity &&
                        !(entity instanceof ArmorStand) &&
                        !entity.getUniqueId().equals(getCompanion().getPlayer().getUniqueId())) {
                    ((LivingEntity) entity).damage(damage);
                    removeAbility();
                }
            }
        } else removeAbility();
    }
}
