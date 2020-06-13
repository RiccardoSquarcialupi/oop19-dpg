package it.dpg.minigames.ballgame.controller;

public interface BallMinigameObserver {
    void handleUpButton(boolean isPressed);

    void handleDownButton(boolean isPressed);

    void handleLeftButton(boolean isPressed);

    void handleRightButton(boolean isPressed);
}
