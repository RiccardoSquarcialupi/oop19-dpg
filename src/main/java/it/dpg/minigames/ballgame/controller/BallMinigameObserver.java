package it.dpg.minigames.ballgame.controller;

public interface BallMinigameObserver {

    /**
     * adds the gamecycle witch needs to be signaled when events happen
     */
    void addGamecycle(BallGamecycle cycle);

    /**
     * handle the up button
     *
     * @param isPressed true if it's pressed, false if it's released
     */
    void handleUpButton(boolean isPressed);

    /**
     * handle the down button
     *
     * @param isPressed true if it's pressed, false if it's released
     */
    void handleDownButton(boolean isPressed);

    /**
     * handle the left button
     *
     * @param isPressed true if it's pressed, false if it's released
     */
    void handleLeftButton(boolean isPressed);

    /**
     * handle the right button
     *
     * @param isPressed true if it's pressed, false if it's released
     */
    void handleRightButton(boolean isPressed);
}
