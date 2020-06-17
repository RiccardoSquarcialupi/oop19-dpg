package it.dpg.minigames.ballgame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;

public interface BallGamecycle extends MinigameCycle {

    /**
     * signal the gamecycle the up input state
     * @param isPressed true if it's pressed, false if it's released
     */
    void signalUpButton(boolean isPressed);

    /**
     * signal the gamecycle the down input state
     * @param isPressed true if it's pressed, false if it's released
     */
    void signalDownButton(boolean isPressed);

    /**
     * signal the gamecycle the left input state
     * @param isPressed true if it's pressed, false if it's released
     */
    void signalLeftButton(boolean isPressed);

    /**
     * signal the gamecycle the right input state
     * @param isPressed true if it's pressed, false if it's released
     */
    void signalRightButton(boolean isPressed);
}
