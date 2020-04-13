package it.dpg.controller.gamecycle;

/**
 * keeps track of the current state of a turn
 */
public interface TurnState {

    /**
     * starts a new turn, it needs to be called at least once before accessing other methods
     */
    void newTurn();

    /**
     * notify the dice has been thrown
     *
     * @exception IllegalStateException if newTurn was never called
     */
    void setDiceThrown();

    /**
     * @return true if the dice has been thrown, false otherwise
     *
     * @exception IllegalStateException if newTurn was never called
     * @exception IllegalStateException if used twice in the same turn
     */
    boolean wasDiceThrown();

    /**
     * set the state of the turn for when a player has to make a choice
     *
     * @param isChoosing true if the player has to choose, false otherwise
     *
     * @exception IllegalStateException if newTurn was never called
     */
    void setIsChoosing(boolean isChoosing);

    /**
     * @return true if the player is currently choosing, false otherwise
     *
     * @exception IllegalStateException if newTurn was never called
     */
    boolean isChoosing();
}
