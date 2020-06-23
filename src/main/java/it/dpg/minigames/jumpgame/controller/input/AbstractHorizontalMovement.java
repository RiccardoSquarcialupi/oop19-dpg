package it.dpg.minigames.jumpgame.controller.input;

import it.dpg.minigames.jumpgame.model.World;

/**
 * Implementation of Input that modifies the player speed on X axis
 * @author Davide Picchiotti
 * */

public abstract class AbstractHorizontalMovement implements Input {
    @Override
    public void execute(World world) {
        world.setPlayerSpeedX(0);
    }

    /**
     * @return the X speed to assign to the player
     * */
    public abstract int getHorizontalMovementSpeed();
}
