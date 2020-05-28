package it.dpg.maingame.controller.gamecycle.turnmanagement;

import it.dpg.maingame.controller.gamecycle.player.Player;

import java.util.Set;

/**
 * PlayerManager handles the operations on the players relative to the turn order and dices, already starts in the first turn when created
 */
public interface PlayerManager {

    /**
     * @return the next player in order of turn
     * @exception java.util.NoSuchElementException if no players have to play in this turn
     */
    Player nextPlayer();

    /**
     * @return true if there are other players who have to do their turns, false otherwise
     */
    boolean hasNextPlayer();

    /**
     * go to the next turn making everyone play a minigame, and calculating the dices for the players of the next turn basing on the scores
     * @exception IllegalStateException if no more turns have to be done
     */
    void nextTurn();

    /**
     * @return true if another turn has to be played, false otherwise
     */
    boolean hasNextTurn();

    /**
     * @return the set of players saved
     */
    Set<Player> getPlayers();
}
