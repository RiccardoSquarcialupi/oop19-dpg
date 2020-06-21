package it.dpg.minigames.jumpgame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.jumpgame.view.JumpMinigameView;

public class JumpMinigameCycle implements MinigameCycle {

    public JumpMinigameView view;

    public JumpMinigameCycle(final JumpMinigameView view) {
        this.view = view;
    }

    @Override
    public int startCycle() {
        return 0;
    }
}
