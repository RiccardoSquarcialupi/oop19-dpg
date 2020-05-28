package it.dpg.maingame.controller.gamecycle.player;

import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.model.character.Character;

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
