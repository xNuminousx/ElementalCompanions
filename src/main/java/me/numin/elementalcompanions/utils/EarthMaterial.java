package me.numin.elementalcompanions.utils;

import org.bukkit.Color;
import org.bukkit.Material;

public enum EarthMaterial {

    DIRT(Material.DIRT, Color.fromRGB(89, 49, 0)),
    GRASS(Material.GRASS, Color.fromRGB(89, 49, 0)),
    SAND(Material.SAND, Color.fromRGB(255, 228, 153)),
    STONE(Material.STONE, Color.fromRGB(161, 161, 161));

    private Color color;
    private Material material;

    EarthMaterial(Material material, Color color) {
        this.color = color;
        this.material = material;
    }

    public Color getColor() {
        return color;
    }

    public Material getMaterial() {
        return material;
    }
}
