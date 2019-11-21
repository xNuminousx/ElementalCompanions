package me.numin.elementalcompanions.companions;

import com.projectkorra.projectkorra.GeneralMethods;
import me.numin.elementalcompanions.abilities.companion.CompanionAbility;
import me.numin.elementalcompanions.utils.NaturalMovement;
import me.numin.elementalcompanions.utils.TrueRandom;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public abstract class Companion implements Companionable {

    public static HashMap<Player, Companion> companions = new HashMap<>();

    private Location spawn;
    private NaturalMovement movement;
    private Player player;

    private boolean isReactive;

    public Companion(Player player) {
        this.player = player;

        this.spawn = locationRelativeToPlayer();
        this.movement = new NaturalMovement(spawn, player);

        // Companions are not reactive by default, must be enabled manually.
        this.isReactive = false;

        companions.put(player, this);
    }

    static HashMap<Player, Companion> getCompanions() {
        return companions;
    }

    protected NaturalMovement getMovement() {
        return movement;
    }

    public Player getPlayer() {
        return player;
    }

    protected Location getSpawn() {
        return spawn;
    }

    public boolean canBeReactive() {
        return GeneralMethods.getEntitiesAroundPoint(getPlayer().getLocation(), 20).size() >= 1;
    }

    @Override
    public boolean isReactive() {
        return isReactive && canBeReactive();
    }

    public void setReactive(boolean bool) {
        isReactive = bool;
    }

    public boolean hasPermission(Player player) {
        return player.hasPermission("elementalcompanions." +
                this.getElement().getName().toLowerCase() + "." +
                this.getName().toLowerCase());
    }

    public LivingEntity getRandomEntity(Location location, double radius) {
        List<LivingEntity> validEntities = new ArrayList<>();
        for (Entity entity : GeneralMethods.getEntitiesAroundPoint(location, radius)) {
            if (entity instanceof LivingEntity &&
                    !(entity instanceof ArmorStand) &&
                    !entity.getUniqueId().equals(getPlayer().getUniqueId()) &&
                    !CompanionAbility.activeAbilities.containsKey(this)) {
                validEntities.add((LivingEntity)entity);
            }
        }

        if (validEntities.isEmpty())
            return null;
        else
            return validEntities.get(new Random().nextInt(validEntities.size()));
    }

    public Location locationRelativeToPlayer() {
        return player
                .getEyeLocation()
                .add(TrueRandom.getTrueRandom(), TrueRandom.getTrueRandom(), TrueRandom.getTrueRandom());
    }

    public void removeCompanion() {
        getCompanions().remove(player);
    }

    public static void removeAllCompanions() {
        for (Companion companion : companions.values()) {
            companion.removeCompanion();
        }
    }
}
