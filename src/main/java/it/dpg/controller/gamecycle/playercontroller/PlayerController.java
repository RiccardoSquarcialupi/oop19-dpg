package it.dpg.controller.gamecycle.playercontroller;

import jdk.jshell.spi.ExecutionControl;
import org.apache.commons.lang3.tuple.ImmutablePair;

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
    ImmutablePair<Integer, Integer> chooseDirection(Set<Integer> possibleCells);

    /**
     * handles the phase of the turn in witch a player (human or cpu) has to play a minigame
     * @return the score
     */
    int playMinigame();
}
