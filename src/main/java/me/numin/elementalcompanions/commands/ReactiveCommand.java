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

        if (input == null) {
            boolean isReactive = companion.isReactive();
            if (isReactive) changeReactiveState(companion, false);
            else changeReactiveState(companion, true);
            return;
        }

        if (input.equalsIgnoreCase("true")) {
            changeReactiveState(companion, true);
        } else if (input.equalsIgnoreCase("false")) {
            changeReactiveState(companion, false);
        } else player.sendMessage("Not a valid input");
    }

    private void changeReactiveState(Companion companion, boolean state) {
        companion.setReactive(state);
        companion.getPlayer().sendMessage("Your companion's reactive state is now [" + state + "]");
    }
}
