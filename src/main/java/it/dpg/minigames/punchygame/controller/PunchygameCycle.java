package it.dpg.minigames.punchygame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.punchygame.controller.input.Input;
import it.dpg.minigames.punchygame.model.WorldImpl;
import it.dpg.minigames.punchygame.view.PunchygameView;
import it.dpg.minigames.punchygame.view.PunchygameViewImpl;

public class PunchygameCycle implements MinigameCycle, InputObserver {

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
        boolean gameover = false;
        long lastTime = System.currentTimeMillis();
        long currentTime;
        long total = 0;

        while(world.getTimer().getTimeLeft() > 0 && !gameover) {
            currentTime = System.currentTimeMillis();
            total += currentTime - lastTime;
            if(total >= 1000) {
                world.getTimer().timerDecrease();
                view.updateTimer(world.getTimer().getTimeLeft());
                total = 0;
            }
            lastTime = currentTime;
        }
        return 0;
    }

    @Override
    public void notifyInput(final Input input) {

    }

    private void setup() {
        view.updateScore(world.getScore().getScore());
        view.updateTimer(world.getTimer().getTimeLeft());
    }
}
