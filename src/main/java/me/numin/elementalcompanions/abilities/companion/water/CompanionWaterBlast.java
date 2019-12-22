package me.numin.elementalcompanions.abilities.companion.water;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.TempBlock;
import me.numin.elementalcompanions.abilities.companion.CompanionAbility;
import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class CompanionWaterBlast extends CompanionAbility {

    private Location currentLocation;
    private Location origin;
    private Location targetOrigin;
    private Vector direction;

    private double damage;
    private double speed;

    public CompanionWaterBlast(Companion companion, LivingEntity target) {
        super(companion, target);

        this.currentLocation = companion.getLocation().clone();
        this.origin = currentLocation.clone();
        this.targetOrigin = target.getLocation().clone();
        this.direction = new Vector(1, 0, 0);

        this.damage = 2;
        this.speed = 0.5;
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

            new TempBlock(currentLocation.getBlock(), Material.WATER).setRevertTime(200);

            if (currentLocation.distance(targetOrigin) < 0.1 ||
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
