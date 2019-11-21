package me.numin.elementalcompanions.commands;

import me.numin.elementalcompanions.companions.Companion;
import me.numin.elementalcompanions.companions.air.AirCompanion;
import me.numin.elementalcompanions.companions.earth.EarthCompanion;
import me.numin.elementalcompanions.companions.fire.FireCompanion;
import me.numin.elementalcompanions.companions.water.WaterCompanion;
import org.bukkit.entity.Player;

class SpawnCommand {

    private Companion companion;
    private Player player;
    private String companionType;

    SpawnCommand(Player player, String companionType) {
        this.player = player;
        this.companionType = companionType;

        Companion.companions.remove(player);

        giveCompanion();
    }

    private void giveCompanion() {
        try {
            if (companionType.equalsIgnoreCase("fire")) {
                companion = new FireCompanion(player);
            } else if (companionType.equalsIgnoreCase("Earth")) {
                companion = new EarthCompanion(player);
            } else if (companionType.equalsIgnoreCase("Water")) {
                companion = new WaterCompanion(player);
            } else if (companionType.equalsIgnoreCase("Air")) {
                companion = new AirCompanion(player);
            }
            player.sendMessage("You now have the companion [" + companion.getName() + "]");
        } catch(Exception e) {
            player.sendMessage("Failed to give you the companion [" + companion.getName() + "]");
            e.printStackTrace();
        }
    }
}
