package me.numin.elementalcompanions.utils;

import com.projectkorra.projectkorra.GeneralMethods;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Movement {

    private Location currentLocation;
    private Location destination;
    private Player anchor;
    private Vector direction;

    private double speed;
    private long newGoalTime;
    private long stopTime;
    private long newGoalBuffer;
    private long movementBuffer;
    private long time;

    /**
     * A class used to manipulate a Location variable in different ways.
     *
     * @param anchor The player entity which the movement revolves around.
     * @param spawn The initial spawn location used for first calculations.
     */
    public Movement(Player anchor, Location spawn) {
        this.anchor = anchor;
        this.currentLocation = spawn.clone();
        this.destination = generateRandomPoint(anchor.getEyeLocation());
        this.direction = new Vector(1, 0, 0);
        this.newGoalBuffer = 1500;
        this.movementBuffer = 500;
        this.speed = 0.1;
        this.time = System.currentTimeMillis();
        this.newGoalTime = time;
        this.stopTime = time;
    }

    public Location moveAimlessly() {
        double anchorDistance = currentLocation.distance(anchor.getEyeLocation());

        if (anchorDistance > 20)
            currentLocation = anchor.getEyeLocation().clone();
        else if (anchorDistance > 4)
            speed = Math.max(0.1, anchorDistance * 0.05);
        else speed = 0.1;

        if (System.currentTimeMillis() > newGoalTime + newGoalBuffer) {
            destination = generateRandomPoint(anchor.getEyeLocation());
            newGoalTime = System.currentTimeMillis();
        }
        double targetDistance = currentLocation.distance(destination);

        direction.add(destination.toVector().subtract(currentLocation.toVector()).multiply(speed)).normalize();

        if (targetDistance > 0.15)
            currentLocation.add(direction.clone().multiply(speed));

        return currentLocation;
    }

    public boolean shouldFreeze() {
        if (System.currentTimeMillis() > time + 1000) {
            if (System.currentTimeMillis() - time > 2000)
                time = System.currentTimeMillis();
            return new RandomChance(30).chanceReached();
        }
        return false;
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
