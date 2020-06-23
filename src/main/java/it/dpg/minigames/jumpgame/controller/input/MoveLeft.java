package it.dpg.minigames.jumpgame.controller.input;

/**
 * Implementation of Input that moves the player to the left
 * @author Davide Picchiotti
 * */

public class MoveLeft extends AbstractHorizontalMovement {
    @Override
    public int getHorizontalMovementSpeed() {
        return -12;
    }
}
