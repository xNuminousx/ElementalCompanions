package me.numin.elementalcompanions.abilities.companion;

public class CompanionAbilityManager implements Runnable {
    @Override
    public void run() {
        for (CompanionAbility ability : CompanionAbility.activeAbilities.values()) {
            if (ability.getTarget().isDead() ||
                    !ability.getTarget().getWorld().equals(ability.getCompanion().getLocation().getWorld()))
                ability.removeAbility();

            ability.advanceAbility();
        }
    }
}
