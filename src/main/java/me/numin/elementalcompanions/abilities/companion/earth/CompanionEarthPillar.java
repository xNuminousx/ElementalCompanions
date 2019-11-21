package me.numin.elementalcompanions.abilities.companion.earth;

import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.util.TempBlock;
import me.numin.elementalcompanions.abilities.companion.CompanionAbility;
import me.numin.elementalcompanions.companions.Companion;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class CompanionEarthPillar extends CompanionAbility {

    private Location currentLocation;

    private long revert;

    public CompanionEarthPillar(Companion companion, LivingEntity target) {
        super(companion, target);

        this.currentLocation = target.getLocation().clone();
        this.revert = 3000;
    }

    @Override
    public Companion getCompanion() {
        return companion;
    }

    @Override
    public LivingEntity getTarget() {
        return target;
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }

    @Override
    public void advanceAbility() {
        Vector targetVelocity = target.getVelocity().setY(1.2);
        target.setVelocity(targetVelocity);

        Block standingBlock = target.getLocation().getBlock();
        new TempBlock(standingBlock, standingBlock.getRelative(BlockFace.DOWN).getType()).setRevertTime(revert);

        EarthAbility.playEarthbendingSound(standingBlock.getLocation());

        removeAbility();
    }
}
