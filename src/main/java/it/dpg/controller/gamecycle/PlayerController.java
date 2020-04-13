package it.dpg.controller.gamecycle;

import java.util.Set;

public interface PlayerController {

    /**
     * handles the throw of a dice for the player (human or cpu)
     * @param dice the type of dice throw
     */
    void throwDice(int dice);

    /**
     * handles the choice of direction for the player (human or cpu)
     * @param
     */
    void chooseDirection(Set<Integer> possibleCells);

    /**
     * handles the phase of the turn in witch a player (human or cpu) has to play a minigame
     * @return the score
     */
    int playMinigame();
}
