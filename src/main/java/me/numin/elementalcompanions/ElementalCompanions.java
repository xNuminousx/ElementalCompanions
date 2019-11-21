package me.numin.elementalcompanions;

import com.projectkorra.projectkorra.ability.CoreAbility;
import me.numin.elementalcompanions.abilities.companion.CompanionAbilityManager;
import me.numin.elementalcompanions.commands.CommandRegistry;
import me.numin.elementalcompanions.companions.Companion;
import me.numin.elementalcompanions.companions.CompanionManager;
import me.numin.elementalcompanions.utils.LogType;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ElementalCompanions extends JavaPlugin {

    public static ElementalCompanions plugin;

    @Override
    public void onEnable() {
        plugin = this;

        registerAbilities();
        registerCommands();
        registerSchedulers();

        log(LogType.INFO, "Successfully enabled " + plugin.getName());
    }

    @Override
    public void onDisable() {
        Companion.removeAllCompanions();
        log(LogType.INFO, "Successfully disabled " + plugin.getName());
    }

    private void log(LogType type, String message) {
        if (type == LogType.INFO)
            getLogger().info(message);
        else getLogger().warning(message);
    }

    public void logException(LogType type, Companion companion) {
        if (type == null || companion == null || companion.getPlayer() == null)
            return;

        log(type, "Failed to execute a companion action.");
        log(type, "Companion: " + companion.getName());
        log(type, "Player: " + companion.getPlayer().getName());
        log(type, "World: " + Objects.requireNonNull(companion.getLocation().getWorld()).getName());
    }

    private void registerAbilities() {
        CoreAbility.registerPluginAbilities(plugin, "me.numin.elementalcompanions.abilities.player");
    }

    private void registerCommands() {
        getCommand("elementalcompanions").setExecutor(new CommandRegistry());
        getCommand("ec").setExecutor(new CommandRegistry());
    }

    private void registerSchedulers() {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this, new CompanionManager(this), 20, 1);
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this, new CompanionAbilityManager(), 20, 1);
    }
}
