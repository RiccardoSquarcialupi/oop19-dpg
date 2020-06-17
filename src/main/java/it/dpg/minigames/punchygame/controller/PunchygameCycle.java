package it.dpg.minigames.punchygame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.punchygame.model.WorldImpl;
import it.dpg.minigames.punchygame.view.PunchygameView;
import it.dpg.minigames.punchygame.view.PunchygameViewImpl;

public class PunchygameCycle implements MinigameCycle {

    private PunchygameView view;
    private WorldImpl world;

    public PunchygameCycle(final PunchygameView view) {
        this.view = view;
        world = new WorldImpl();
    }

    @Override
    public int startCycle() {
        return 0;
    }
}
