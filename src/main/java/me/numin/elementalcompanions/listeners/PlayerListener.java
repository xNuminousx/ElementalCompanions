package me.numin.elementalcompanions.listeners;

import com.projectkorra.projectkorra.BendingPlayer;
import me.numin.elementalcompanions.abilities.player.Defuse;
import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

        if (bPlayer == null)
            return;

        if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Defuse") && event.isSneaking()) {
            new Defuse(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (!Companion.companions.containsKey(player))
            return;

        Companion.companions.get(player).removeCompanion();
    }
}
