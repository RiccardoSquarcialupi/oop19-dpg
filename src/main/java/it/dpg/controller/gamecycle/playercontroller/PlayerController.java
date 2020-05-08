package it.dpg.controller.gamecycle.playercontroller;

import java.util.Set;

/**
 * interface for handle behaviours of a specific entity, human or cpu
 * depending on the implementation.
 */
public interface PlayerController {

    /**
     * handles the throw of a dice for the player (human or cpu)
     * @param dice the type of dice throw
     */
    void throwDice(int dice);

    /**
     * handles the choice of direction for the player (human or cpu)
     * @param possibleCells IDs if the possible choices
     */
    void chooseDirection(Set<Integer> possibleCells);

    /**
     * handles the pause of the turn, used petween the steps of a turn (resumed by a button press)
     */
    void waitNextStep();

    /**
     * handles the phase of the turn in witch a player (human or cpu) has to play a minigame
     * @return the score
     */
    int playMinigame();
}
