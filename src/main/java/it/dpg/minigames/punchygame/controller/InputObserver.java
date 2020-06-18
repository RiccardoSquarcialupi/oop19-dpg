package it.dpg.minigames.punchygame.controller;

import it.dpg.minigames.punchygame.controller.input.Input;

public interface InputObserver {
    void notifyInput(final Input input);
}
