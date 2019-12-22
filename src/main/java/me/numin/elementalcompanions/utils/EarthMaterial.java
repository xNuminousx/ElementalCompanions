package me.numin.elementalcompanions.utils;

import org.bukkit.Color;
import org.bukkit.Material;

/**
 * An enumeration used to determine the type and color of earth particles
 * when used.
 */
public enum EarthMaterial {

    DIRT(Material.DIRT, Color.fromRGB(89, 49, 0)),
    GRASS(Material.GRASS, Color.fromRGB(89, 49, 0)),
    SAND(Material.SAND, Color.fromRGB(255, 228, 153)),
    STONE(Material.STONE, Color.fromRGB(161, 161, 161));

    private Color color;
    private Material material;

    /**
     * Creates the EarthMaterial
     *
     * @param material The block to be based off of. Often used to display block crack particles.
     * @param color The color of the block in Color format to be used with colored redstone particles.
     */
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
