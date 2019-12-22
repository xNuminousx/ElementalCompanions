package me.numin.elementalcompanions.commands;

import org.bukkit.command.CommandSender;

class HelpCommand {

    HelpCommand(CommandSender sender) {
        sender.sendMessage("/ec spawn [element] - Spawns a new companion for the desired element!");
        sender.sendMessage("/ec remove - Remove your companion :(");
        sender.sendMessage("/ec reactive [true/false] - Set your companion to be reactive or not!");
        sender.sendMessage("/ec silence [true/false] - Silence your companion or hear them roar!");
    }
}
