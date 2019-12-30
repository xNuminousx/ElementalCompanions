package me.numin.elementalcompanions.utils.handlers;

import com.projectkorra.projectkorra.GeneralMethods;
import me.numin.elementalcompanions.utils.randomizers.TrueRandom;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovementHandler {

    private Location currentLocation;
    private Location destination;
    private Player anchor;
    private Vector direction;

    private double speed;
    private long newGoalTime;
    private long newGoalBuffer;

    /**
     * A class used to manipulate a {@link Location} variable in different ways.
     *
     * @param anchor The {@link Player} which the movement revolves around.
     * @param spawn The initial {@link Location} used for first calculations.
     */
    public MovementHandler(Player anchor, Location spawn) {
        this.anchor = anchor;

        this.currentLocation = spawn.clone();
        this.destination = generateRandomPoint(anchor.getEyeLocation());
        this.direction = new Vector(1, 0, 0);

        this.newGoalTime = System.currentTimeMillis();
        this.newGoalBuffer = 2500;
        this.speed = 0.1;
    }

    public Location moveAimlessly() {
        double anchorDistance = currentLocation.distance(anchor.getEyeLocation());

        if (anchorDistance > 20)
            currentLocation = anchor.getEyeLocation().clone();
        else if (anchorDistance > 4)
            speed = Math.max(0.1, anchorDistance * 0.05);
        else
            speed = Math.max(0.05, Math.random() > 0.9 ? anchorDistance * (Math.random() * 0.175) : 0.15);

        if (System.currentTimeMillis() - newGoalTime > newGoalBuffer) {
            destination = generateRandomPoint(anchor.getEyeLocation());
            newGoalTime = System.currentTimeMillis();
        }
        double targetDistance = currentLocation.distance(destination);

        direction.add(destination.toVector().subtract(currentLocation.toVector()).multiply(speed)).normalize();

        if (targetDistance > 0.15)
            currentLocation.add(direction.clone().multiply(speed));

        return currentLocation;
    }

    public Location generateRandomPoint(Location base) {
        Location newPoint = base.clone().add(TrueRandom.getTrueRandom() * 1.3, TrueRandom.getTrueRandom() * 1.3, TrueRandom.getTrueRandom() * 1.3);

        if (!newPoint.getBlock().getType().equals(Material.AIR)) {
            List<Block> alternatePoints = new ArrayList<>();

            for (Block block : GeneralMethods.getBlocksAroundPoint(newPoint, 3)) {
                if (block.getType().equals(Material.AIR))
                    alternatePoints.add(block);
            }

            if (alternatePoints.size() < 1)
                newPoint = anchor.getEyeLocation().clone().add(TrueRandom.getTrueRandom() * 1.3, 1.5, TrueRandom.getTrueRandom() * 1.3);
            else {
                newPoint = alternatePoints.get(new Random().nextInt(alternatePoints.size())).getLocation();
            }
        }

        return newPoint;
    }
}
