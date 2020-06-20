package it.dpg.maingame.controller.gamecycle.turnmanagement;

import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;

import java.util.List;

/**
 * PlayerManager handles the operations on the players relative to the turn order and dices, already starts in the first turn when created
 */
public interface TurnManager {

    /**
     * @return the next player in order of turn
     * @throws java.util.NoSuchElementException if no players have to play in this turn
     */
    PlayerController nextPlayer();

    /**
     * @return true if there are other players who have to do their turns, false otherwise
     */
    boolean hasNextPlayer();

    /**
     * go to the next turn making everyone play a minigame, and calculating the dices for the players of the next turn basing on the scores
     *
     * @throws IllegalStateException if no more turns have to be done
     */
    void nextTurn();

    /**
     * @return true if another turn has to be played, false otherwise
     */
    boolean hasNextTurn();

    /**
     * @return the list of players saved ordered by turn
     */
    List<PlayerController> getPlayers();
}
