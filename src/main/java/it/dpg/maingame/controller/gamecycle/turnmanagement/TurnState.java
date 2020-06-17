package it.dpg.maingame.controller.gamecycle.turnmanagement;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * keeps track of the current state of a turn
 */
public interface TurnState {

    /**
     * starts a new turn, it needs to be called at least once before accessing other methods
     */
    void newTurn();

    /**
     * notify if the dice has been thrown or not
     *
     * @exception IllegalStateException if newTurn was never called
     */
    void setDiceThrown(final boolean wasThrown);

    /**
     * @return true if the dice has been thrown, false otherwise
     *
     * @exception IllegalStateException if newTurn was never called
     */
    boolean wasDiceThrown();

    /**
     * notify if a choice has to be done or not
     *
     * @exception IllegalStateException if newTurn was never called
     */
    void setChoice(final boolean isChoosing);

    /**
     * @return true if the player is currently choosing, false otherwise
     *
     * @exception IllegalStateException if newTurn was never called
     */
    boolean isChoosing();

    /**
     * save the last direction choice of the player/cpu currently playing the turn
     * @param direction id of the chosen cell
     *
     * @exception IllegalStateException if newTurn was never called
     */
    void setLastDirectionChoice(final ImmutablePair<Integer, Integer> direction);

    /**
     * get the last direction choice made by a player/cpu
     * @return Optional.empty if no choice has been made in the turn, or the chosen cell otherwise
     */
    Optional<ImmutablePair<Integer, Integer>> getLastDirectionChoice();

    /**
     * notify if the turn is currently paused or not
     *
     * @exception IllegalStateException if newTurn was never called
     */
    void setTurnPause(final boolean isPaused);

    /**
     * @return true if the turn is paused, false otherwise
     *
     * @exception IllegalStateException if newTurn was never called
     */
    boolean isPaused();
}