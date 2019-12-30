package me.numin.elementalcompanions.companions;

import com.projectkorra.projectkorra.GeneralMethods;
import me.numin.elementalcompanions.abilities.companion.CompanionAbility;
import me.numin.elementalcompanions.utils.handlers.MovementHandler;
import me.numin.elementalcompanions.utils.randomizers.TrueRandom;
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
    private MovementHandler movementHandler;
    private Player player;

    private boolean isReactive;
    private boolean isSilenced;

    public Companion(Player player) {
        this.player = player;

        this.spawn = locationRelativeToPlayer();
        this.movementHandler = new MovementHandler(player, spawn);

        this.isReactive = false;
        this.isSilenced = false;

        companions.put(player, this);
    }

    public boolean canBeReactive() {
        return GeneralMethods.getEntitiesAroundPoint(getPlayer().getLocation(), 20).size() >= 1;
    }

    public static HashMap<Player, Companion> getCompanions() {
        return companions;
    }

    public MovementHandler getMovementHandler() {
        return movementHandler;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getSpawn() {
        return spawn;
    }

    public boolean hasPermission(Player player) {
        return player.hasPermission("elementalcompanions." +
                this.getElement().getName().toLowerCase() + "." +
                this.getName().toLowerCase());
    }

    @Override
    public boolean isReactive() {
        return isReactive && canBeReactive();
    }

    @Override
    public boolean isSilenced() {
        return isSilenced;
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

    public void setReactive(boolean bool) {
        isReactive = bool;
    }

    public void setSilent(boolean bool) {
        isSilenced = bool;
    }
}
