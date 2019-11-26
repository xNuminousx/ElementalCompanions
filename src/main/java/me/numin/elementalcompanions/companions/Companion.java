package me.numin.elementalcompanions.companions;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import me.numin.elementalcompanions.abilities.companion.CompanionAbility;
import me.numin.elementalcompanions.utils.Movement;
import me.numin.elementalcompanions.utils.RandomChance;
import me.numin.elementalcompanions.utils.TrueRandom;
import org.bukkit.Location;
import org.bukkit.Sound;
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
    private Movement movement;
    private Player player;

    private boolean isReactive;

    public Companion(Player player) {
        this.player = player;

        this.spawn = locationRelativeToPlayer();
        this.movement = new Movement(spawn, player);

        // Companions are not reactive by default, must be enabled manually.
        this.isReactive = false;

        companions.put(player, this);
    }

    boolean canBeReactive() {
        return GeneralMethods.getEntitiesAroundPoint(getPlayer().getLocation(), 20).size() >= 1;
    }

    static HashMap<Player, Companion> getCompanions() {
        return companions;
    }

    protected Movement getMovement() {
        return movement;
    }

    public Player getPlayer() {
        return player;
    }

    protected Location getSpawn() {
        return spawn;
    }

    boolean hasPermission(Player player) {
        return player.hasPermission("elementalcompanions." +
                this.getElement().getName().toLowerCase() + "." +
                this.getName().toLowerCase());
    }

    @Override
    public boolean isReactive() {
        return isReactive && canBeReactive();
    }

    protected LivingEntity getRandomEntity(Location location, double radius) {
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

    private Location locationRelativeToPlayer() {
        return player
                .getEyeLocation()
                .add(TrueRandom.getTrueRandom(), TrueRandom.getTrueRandom(), TrueRandom.getTrueRandom());
    }

    protected void playSound() {
        Element element = getElement();

        // elemental sounds
        if (new RandomChance(10).chanceReached()) {
            if (element == Element.FIRE)
                getLocation().getWorld().playSound(getLocation(), Sound.BLOCK_FIRE_AMBIENT, 0.2F, 1F);
            else if (element == Element.WATER)
                getLocation().getWorld().playSound(getLocation(), Sound.BLOCK_WATER_AMBIENT, 0.1F, 1.2F);
            else if (element == Element.EARTH)
                getLocation().getWorld().playSound(getLocation(), Sound.BLOCK_STONE_HIT, 0.1F, 0.2F);
        }

        // generic sound
        if (new RandomChance(1).chanceReached())
            getLocation().getWorld().playSound(getLocation(), Sound.ENTITY_GHAST_AMBIENT, 0.3F, 2F);
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
}
