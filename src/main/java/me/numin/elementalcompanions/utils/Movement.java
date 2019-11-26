package me.numin.elementalcompanions.utils;

import com.projectkorra.projectkorra.GeneralMethods;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Movement {

    private Location currentLocation;
    private Location destination;
    private Player source;
    private Vector direction;

    private double range;
    private double speed;
    private long currentTime;
    private long refreshRate;

    public Movement(Location origin, Player source) {
        this.source = source;

        this.currentLocation = origin.clone();
        this.currentTime = System.currentTimeMillis();
        this.destination = generateDestination(currentLocation);
        this.direction = new Vector(1, 0, 0);

        this.refreshRate = 5000;
        this.range = 3;
        this.speed = 0.1;
    }

    public Location moveAimlessly() {
        World currentWorld = currentLocation.getWorld();
        World sourceWorld = source.getWorld();

        double targetDistance = currentLocation.distance(destination);

        if (targetDistance > 1 || refreshDestination() || tooLow(currentLocation))
            destination = generateDestination(currentLocation);
        if (outOfReach()) {
            destination = generateDestination(source.getEyeLocation());
            speed = 0.4;
        } else speed = 0.1;

        assert currentWorld != null;
        if (extremelyOutOfReach() || !currentWorld.equals(sourceWorld)) {
            currentLocation = source.getLocation().clone();
            destination = generateDestination(source.getEyeLocation());
        }

        direction.add(destination.toVector().subtract(currentLocation.toVector()).multiply(speed)).normalize();

        if (currentLocation.distance(destination) > 0.1)
            currentLocation.add(direction.clone().multiply(speed));

        return currentLocation;
    }

    private boolean outOfReach() {
        return source.getLocation().distance(currentLocation) > range + 2;
    }

    private boolean extremelyOutOfReach() {
        return source.getLocation().distance(currentLocation) > range + 20;
    }

    private boolean refreshDestination() {
        if (System.currentTimeMillis() > currentTime + refreshRate) {
            currentTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    private boolean tooLow(Location location) {
        return location.getY() < source.getLocation().getY();
    }

    private Location generateDestination(Location base) {
        return base.clone().add(TrueRandom.getTrueRandom() * 1.3, TrueRandom.getTrueRandom() * 1.5, TrueRandom.getTrueRandom() * 1.3);
    }
}
