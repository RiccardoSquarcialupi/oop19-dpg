package it.dpg.controller.gamecycle.turnmanagement;

import it.dpg.controller.gamecycle.playercontroller.PlayerController;

/**
 * a player includes a PlayerController, and a character
 */
public interface Player {

    /**
     * @return the controller relative to the player
     */
    PlayerController getPlayerController();

    /**
     * @return the character controlled by the player
     */
    Character getCharacter();
}
