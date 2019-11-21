package me.numin.elementalcompanions.abilities.player;

import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.AvatarAbility;
import me.numin.elementalcompanions.ElementalCompanions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Defuse extends AvatarAbility implements AddonAbility {

    private long cooldown;

    public Defuse(Player player) {
        super(player);

        if (!bPlayer.canBend(this))
            return;

        this.cooldown = 0;

        start();
    }

    @Override
    public void progress() {
        if (player.isSneaking()) {
        }
    }

    @Override
    public boolean isSneakAbility() {
        return true;
    }

    @Override
    public boolean isHarmlessAbility() {
        return false;
    }

    @Override
    public long getCooldown() {
        return cooldown;
    }

    @Override
    public String getName() {
        return "Defuse";
    }

    @Override
    public String getDescription() {
        return "Product of ElementalCompanions - Used to defuse reactive companions! Fight them back!";
    }

    @Override
    public String getInstructions() {
        return "Hold shift while looking near a companion.";
    }

    @Override
    public Location getLocation() {
        return player.getLocation();
    }

    @Override
    public void load() {}
    @Override
    public void stop() {}

    @Override
    public String getAuthor() {
        return "Numin";
    }

    @Override
    public String getVersion() {
        return ElementalCompanions.plugin.getDescription().getVersion();
    }
}
