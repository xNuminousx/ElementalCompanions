package me.numin.elementalcompanions.utils;

import java.util.Random;

public class RandomChance {

    private double percent;

    /**
     * Used for allowing more randomization.
     * Using 1000 as a base rather than 100 allows for greater range.
     *
     * @param percent The percent chance you want chanceReached to return true.
     */
    public RandomChance(double percent) {
        this.percent = percent * 10;
    }

    public boolean chanceReached() {
        int randomInt = new Random().nextInt(1000);
        return randomInt <= percent;
    }

}
