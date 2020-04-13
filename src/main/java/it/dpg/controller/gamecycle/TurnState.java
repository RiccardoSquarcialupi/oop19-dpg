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
    boolean isDiceThrown();

    /**
     * notify a choice has to be done
     *
     * @exception IllegalStateException if newTurn was never called
     */
    void choiceStarted();

    /**
     * notify a choice has been completed
     *
     * @exception IllegalStateException if newTurn was never called
     */
    void choiceCompleted();

    /**
     * @return true if the player is currently choosing, false otherwise
     *
     * @exception IllegalStateException if newTurn was never called
     */
    boolean isChoosing();
}
