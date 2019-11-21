package me.numin.elementalcompanions.commands;

import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.entity.Player;

class RemoveCommand {

    RemoveCommand(Player player) {
        if (player == null)
            return;

        if (Companion.companions.containsKey(player)) {
            player.sendMessage("Removed the companion [" + Companion.companions.get(player).getName() + "]");
            Companion.companions.get(player).removeCompanion();
        }
    }
}
