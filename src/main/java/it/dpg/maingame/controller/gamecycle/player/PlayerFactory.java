package it.dpg.maingame.controller.gamecycle.player;

import it.dpg.maingame.model.character.Difficulty;


/**
 * used for creating players, each one with a different id
 */
public interface PlayerFactory {

    /**
     * @param name name of the character
     * @return the human player with the correct implementation
     */
    Player createHumanPlayer(String name);

    /**
     * @param name name of the character
     * @param difficulty of the cpu
     * @return the cpu player with the correct implementation
     */
    Player createCpu(String name, Difficulty difficulty);
}
