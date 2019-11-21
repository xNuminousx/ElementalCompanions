package me.numin.elementalcompanions.companions;

import com.projectkorra.projectkorra.Element;
import org.bukkit.Location;

public interface Companionable {
    Element getElement();
    Location getLocation();
    String getName();
    //TODO: Implement reactive features
    boolean isReactive();
    void animateMovement();
    void advanceReaction();
}
