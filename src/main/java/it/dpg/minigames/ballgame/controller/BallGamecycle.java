package it.dpg.minigames.ballgame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;

public interface BallGamecycle extends MinigameCycle {

    /**
     * signal the gamecycle the up input state
     *
     * @param isGoing true if it's pressed, false if it's released
     */
    void signalGoingUp(boolean isGoing);

    /**
     * signal the gamecycle the down input state
     *
     * @param isGoing true if it's pressed, false if it's released
     */
    void signalGoingDown(boolean isGoing);

    /**
     * signal the gamecycle the left input state
     *
     * @param isGoing true if it's pressed, false if it's released
     */
    void signalGoingleft(boolean isGoing);

    /**
     * signal the gamecycle the right input state
     *
     * @param isGoing true if it's pressed, false if it's released
     */
    void signalGoingRight(boolean isGoing);
}
