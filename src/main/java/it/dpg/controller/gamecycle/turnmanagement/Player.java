package it.dpg.controller.gamecycle.turnmanagement;

import it.dpg.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.model.character.Character;

/**
 * a player includes a controller, and the relative character
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
