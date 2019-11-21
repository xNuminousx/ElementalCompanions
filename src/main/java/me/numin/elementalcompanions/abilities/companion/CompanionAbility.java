package me.numin.elementalcompanions.abilities.companion;

import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;

public abstract class CompanionAbility implements AbilityInterface {

    public static HashMap<Companion, CompanionAbility> activeAbilities = new HashMap<>();

    public Companion companion;
    protected LivingEntity target;

    public CompanionAbility(Companion companion, LivingEntity target) {
        this.companion = companion;
        this.target = target;

        activeAbilities.put(companion, this);
    }

    public void removeAbility() {
        activeAbilities.remove(this.getCompanion());
    }
}
