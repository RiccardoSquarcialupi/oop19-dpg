package it.dpg.minigames.punchygame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.punchygame.controller.input.Input;
import it.dpg.minigames.punchygame.model.WorldImpl;
import it.dpg.minigames.punchygame.view.PunchygameView;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PunchygameCycle implements MinigameCycle, InputObserver {

    private static final int PERIOD = 20;

    private PunchygameView view;
    private WorldImpl world;
    private BlockingQueue<Input> inputBuffer;

    public PunchygameCycle(final PunchygameView view) {
        this.view = Objects.requireNonNull(view);
        world = new WorldImpl();
        inputBuffer = new ArrayBlockingQueue<>(10);
    }

    @Override
    public int startCycle() {
        setup();
        long lastTime = System.currentTimeMillis();
        long currentTime;
        long total = 0;

        while(world.getTimer().getTimeLeft() > 0 && !world.isGameOver()) {
            currentTime = System.currentTimeMillis();

            total += currentTime - lastTime;
            total = updateTimer(total);

            processInput();

            lastTime = currentTime;
        }

        return world.getScore().getPoints();
    }

    @Override
    public void notifyInput(final Input input) {
        inputBuffer.add(Objects.requireNonNull(input));
    }

    private long updateTimer(final long elapsed) {
        if(elapsed >= 1000) {
            world.getTimer().timerDecrease();
            view.updateTimer(world.getTimer().getTimeLeft());
            return 0;
        }
        return elapsed;
    }

    private void processInput() {
        Input i = inputBuffer.poll();
        if(i != null) {
            i.execute(world);
        }
    }

    private void setup() {
        view.setInputObserver(this);
        view.updateScore(world.getScore().getPoints());
        view.updateTimer(world.getTimer().getTimeLeft());
    }
}
