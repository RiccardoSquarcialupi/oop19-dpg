package it.dpg.minigames.ballgame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;

public interface BallGamecycle extends MinigameCycle {
    void signalUpButton(boolean isPressed);

    void signalDownButton(boolean isPressed);

    void signalLeftButton(boolean isPressed);

    void signalRightButton(boolean isPressed);
}
