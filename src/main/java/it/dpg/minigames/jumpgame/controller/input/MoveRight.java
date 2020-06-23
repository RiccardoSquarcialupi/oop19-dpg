package it.dpg.minigames.jumpgame.controller.input;

/**
 * Implementation of Input that moves the player to the right
 * @author Davide Picchiotti
 * */

public class MoveRight extends AbstractHorizontalMovement {
    @Override
    public int getHorizontalMovementSpeed() {
        return 12;
    }
}
