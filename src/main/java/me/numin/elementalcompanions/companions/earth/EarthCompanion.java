package me.numin.elementalcompanions.companions.earth;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.ability.EarthAbility;
import me.numin.elementalcompanions.abilities.companion.CompanionAbility;
import me.numin.elementalcompanions.abilities.companion.earth.CompanionEarthPillar;
import me.numin.elementalcompanions.companions.Companion;
import me.numin.elementalcompanions.utils.enumerations.EarthMaterial;
import me.numin.elementalcompanions.utils.randomizers.RandomChance;
import me.numin.elementalcompanions.utils.handlers.SoundHandler;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Random;

public class EarthCompanion extends Companion {

    private BlockData earthBlock;
    private Particle.DustOptions blockColor;
    private Location currentLocation;
    private SoundHandler soundHandler;

    private long currentTime;
    private long reactBuffer;

    public EarthCompanion(Player player) {
        super(player);

        EarthMaterial[] materials = EarthMaterial.values();
        EarthMaterial material = materials[new Random().nextInt(materials.length)];
        earthBlock = material.getMaterial().createBlockData();
        blockColor = new Particle.DustOptions(material.getColor(), 1);

        this.currentLocation = getSpawn();
        this.currentTime = System.currentTimeMillis();
        this.reactBuffer = 3000;
        this.soundHandler = new SoundHandler(this);
    }

    @Override
    public Element getElement() {
        return Element.EARTH;
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }

    @Override
    public String getName() {
        return "EarthCompanion";
    }

    @Override
    public void animateMovement() {
        currentLocation = getMovementHandler().moveAimlessly();

        if (!isSilenced()) {
            soundHandler.playAmbientCompanionSound(6000, 1);
            soundHandler.playAmbientElementalCompanionSound(1000, 10);
        }

        currentLocation
                .getWorld()
                .spawnParticle(Particle.BLOCK_CRACK, currentLocation, 1, 0, 0, 0, 0, earthBlock);
        currentLocation
                .getWorld()
                .spawnParticle(Particle.REDSTONE, currentLocation, 2, 0.1, 0.1, 0.1, 0, blockColor);
    }

    @Override
    public void advanceReaction() {
        LivingEntity randomEntity = getRandomEntity(getLocation(), 20);

        if (randomEntity == null)
            return;

        Block entityStandingBlock = randomEntity.getLocation().getBlock().getRelative(BlockFace.DOWN);
        Block playerStandingBlock = getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);

        boolean canReact = new RandomChance(1).chanceReached() &&
                EarthAbility.isEarth(playerStandingBlock) && EarthAbility.isEarth(entityStandingBlock);

        if (canReact && System.currentTimeMillis() > currentTime + reactBuffer) {
            new CompanionEarthPillar(this, randomEntity);
            currentTime = System.currentTimeMillis();
        }
    }
}
