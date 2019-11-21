package me.numin.elementalcompanions.utils;

import java.util.Random;

public class TrueRandom {
    public static double getTrueRandom() {
        int x = new Random().nextInt(2);
        if (x == 1) return -Math.random();
        return Math.random();
    }
}
