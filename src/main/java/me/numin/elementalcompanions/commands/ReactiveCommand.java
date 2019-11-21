package me.numin.elementalcompanions.commands;

import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.entity.Player;

class ReactiveCommand {

    ReactiveCommand(Player player, String input) {

        if (!Companion.companions.containsKey(player))
            return;

        Companion companion = Companion.companions.get(player);

        if (companion == null)
            return;

        if (input.equalsIgnoreCase("true")) {
            companion.setReactive(true);
            player.sendMessage("Your companion is now reactive");
        } else if (input.equalsIgnoreCase("false")) {
            companion.setReactive(false);
            player.sendMessage("Your companion is no longer reactive.");
        } else player.sendMessage("Not a valid input");
    }
}
