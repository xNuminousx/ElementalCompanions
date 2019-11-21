package me.numin.elementalcompanions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CommandRegistry implements CommandExecutor {

    private static String[] aliases = {"elementalcompanions", "ec"};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Arrays.asList(aliases).contains(label.toLowerCase())) {

            if (args.length == 0)
                new HelpCommand(sender);

            else {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("You must be a player.");
                    return true;
                }
                Player player = (Player)sender;

                if (args[0].equalsIgnoreCase("spawn")) {
                    new SpawnCommand(player, args[1]);
                    return true;
                } else if (args[0].equalsIgnoreCase("remove")) {
                    new RemoveCommand(player);
                    return true;
                } else if (args[0].equalsIgnoreCase("reactive")) {
                    new ReactiveCommand(player, args[1]);
                    return true;
                }
            }
        }
        return true;
    }
}
