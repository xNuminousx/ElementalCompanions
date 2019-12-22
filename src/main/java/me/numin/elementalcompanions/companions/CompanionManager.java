package me.numin.elementalcompanions.companions;

import com.projectkorra.projectkorra.BendingPlayer;
import me.numin.elementalcompanions.ElementalCompanions;
import me.numin.elementalcompanions.utils.LogType;
import org.bukkit.entity.Player;

public class CompanionManager implements Runnable {

    private ElementalCompanions plugin;

    public CompanionManager(ElementalCompanions plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Companion companion : Companion.getCompanions().values()) {
            Player player = companion.getPlayer();

            if (!isValidPlayer(player) || !companion.hasPermission(player)) {
                companion.removeCompanion();
                return;
            }

            if (player.isDead())
                continue;

            try {
                companion.animateMovement();
            } catch(Exception e) {
                plugin.logException(LogType.WARN, companion);
                e.printStackTrace();
            }

            if (companion.isReactive()) {
                try {
                    companion.advanceReaction();
                } catch(Exception e) {
                    plugin.logException(LogType.WARN, companion);
                    e.printStackTrace();
                }
            }
        }
    }

    // Might want to remove the BendingPlayer check, undecided
    private boolean isValidPlayer(Player player) {
        return player.isOnline() || BendingPlayer.getBendingPlayer(player) == null;
    }
}
