package me.numin.elementalcompanions.utils;

import java.util.Random;

public class RandomChance {

    private double percent;
    private int randomInt;

    /**
     * Used for allowing more randomization.
     * Using 1000 as a base rather than 100 allows for greater range.
     *
     * @param percent The percent chance you want chanceReached to return true.
     */
    public RandomChance(double percent) {
        this.percent = percent * 10;
        this.randomInt = new Random().nextInt(1000);
    }

    public boolean chanceReached() {
        return randomInt <= percent;
    }

}
