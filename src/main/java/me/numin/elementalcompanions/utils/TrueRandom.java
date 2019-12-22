package me.numin.elementalcompanions.utils;

import java.util.Random;

public class TrueRandom {
    /**
     * Used for being able to get a random decimal both on the positive
     * and negative side of the spectrum.
     *
     * @return A random decimal between -1 and 1.
     */
    public static double getTrueRandom() {
        int x = new Random().nextInt(2);
        if (x == 1) return -Math.random();
        return Math.random();
    }
}
