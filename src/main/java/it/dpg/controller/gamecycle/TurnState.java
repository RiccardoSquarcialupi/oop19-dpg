package it.dpg.controller.gamecycle;

/**
 * keeps track of the current state of a turn
 */
public interface TurnState {

    /**
     * reset the states to star a new turn
     */
    void newTurn();

    /**
     * notify the dice has been thrown
     */
    void setDiceThrown();

    /**
     * @return true if the dice has been thrown, false otherwise
     */
    boolean wasDiceThrown();

    /**
     * set the state of the turn for when a player has to make a choice
     * @param isChoosing true if the player has to choose, false otherwise
     */
    void setIsChoosing(boolean isChoosing);

    /**
     * @return true if the player is currently choosing, false otherwise
     */
    boolean isChoosing();
}
