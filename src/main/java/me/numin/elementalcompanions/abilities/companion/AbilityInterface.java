package me.numin.elementalcompanions.abilities.companion;

import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public interface AbilityInterface {
    Companion getCompanion();
    LivingEntity getTarget();
    Location getLocation();
    void advanceAbility();
}
