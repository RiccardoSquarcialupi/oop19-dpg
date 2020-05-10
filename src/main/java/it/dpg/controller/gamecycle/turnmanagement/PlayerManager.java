package it.dpg.controller.gamecycle.turnmanagement;

import java.util.Set;

/**
 * PlayerManager handles the operations on the players relative to the turn order and dices
 */
public interface PlayerManager {

    /**
     * adds a player
     * @param player to handle
     * @exception IllegalStateException if the method is called after startGame is called
     * @exception IllegalArgumentException if a player with the same character id is already inserted
     */
    void addPlayer(final Player player);

    /**
     * start the game and the first turn, assigns equal dices to all players, randomize turn order
     * @exception IllegalStateException if called twice
     * @param nTurns number of turns of the game
     */
    void startGame(int nTurns);

    /**
     * @return the next player in order of turn
     * @exception IllegalStateException if called before the game starts
     * @exception IllegalStateException if no players have to play in this turn
     */
    Player nextPlayer();

    /**
     * @exception IllegalStateException if called before the game starts
     * @return true if there are other players who have to do their turns, false otherwise
     */
    boolean hasNextPlayer();

    /**
     * go to the next turn making everyone play a minigame, and calculating the dices for the players of the next turn basing on the scores
     * @exception IllegalStateException if called before the game starts
     * @exception IllegalStateException if no more turns have to be done
     */
    void nextTurn();

    /**
     * @exception IllegalStateException if called before the game starts
     * @return true if another turn has to be played, false otherwise
     */
    boolean hasNextTurn();

    /**
     * @return the set of players saved
     */
    Set<Player> getPlayers();
}
