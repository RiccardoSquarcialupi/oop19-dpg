package it.dpg.minigames.punchygame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.punchygame.view.PunchygameView;
import it.dpg.minigames.punchygame.view.PunchygameViewImpl;

public class PunchygameCycle implements MinigameCycle {

    private PunchygameView view;

    public PunchygameCycle(final PunchygameView view) {
        this.view = view;
    }

    @Override
    public int startCycle() {
        return 0;
    }
}
