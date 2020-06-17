package it.dpg.minigames.punchygame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.punchygame.model.WorldImpl;
import it.dpg.minigames.punchygame.view.PunchygameView;
import it.dpg.minigames.punchygame.view.PunchygameViewImpl;

public class PunchygameCycle implements MinigameCycle {

    private static final int PERIOD = 20;

    private PunchygameView view;
    private WorldImpl world;

    public PunchygameCycle(final PunchygameView view) {
        this.view = view;
        world = new WorldImpl();
    }

    @Override
    public int startCycle() {
        setup();
        while(world.getTimer().getTimeLeft() > 0) {
            world.getTimer().timerDecrease();
            view.updateTimer(world.getTimer().getTimeLeft());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private void setup() {
        view.updateScore(world.getScore().getScore());
        view.updateTimer(world.getTimer().getTimeLeft());
    }
}
