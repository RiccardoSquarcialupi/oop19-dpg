package it.dpg.controller.gamecycle;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

/**
 * coordinates the elements of the game
 */
public interface GameCycle {
    /**
     * start the game cycle with a new thread
     * @param nCharacters number of characters in the game
     * @param playerNames names of the players, witch have to be less than the number of characters
     * @throws IllegalArgumentException id playerNames.size() > nCharacters
     */
    void startGameCycle(int nCharacters, Set<String> playerNames) throws IllegalArgumentException;

    /**
     * method used to notify the game cycle the dice has been thrown by a player
     */
    void signalDiceThrown();

    /**
     * method used to notify the path has been chosen by a player
     */
    void signalPathChosen(ImmutablePair<Integer, Integer> coordinates);

    /**
     * signals the botton associated to the next step of the turn is pressed (ex. Enter)
     */
    void signalNextStep();
}
