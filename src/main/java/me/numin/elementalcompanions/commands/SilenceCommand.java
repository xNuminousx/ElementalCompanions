package me.numin.elementalcompanions.commands;

import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.entity.Player;

public class SilenceCommand {

    SilenceCommand(Player player, String input) {
        if (!Companion.companions.containsKey(player))
            return;

        Companion companion = Companion.companions.get(player);

        if (companion == null)
            return;

        if (input == null) {
            boolean isSilent = companion.isSilenced();
            if (isSilent) changeSilentState(companion, false);
            else changeSilentState(companion, true);
            return;
        }

        if (input.equalsIgnoreCase("true")) {
            changeSilentState(companion, true);
        } else if (input.equalsIgnoreCase("false")) {
            changeSilentState(companion, false);
        } else player.sendMessage("Not a valid input");
    }

    private void changeSilentState(Companion companion, boolean state) {
        companion.setSilent(state);
        companion.getPlayer().sendMessage("Your companion's silence state is now [" + state + "]");
    }
}
