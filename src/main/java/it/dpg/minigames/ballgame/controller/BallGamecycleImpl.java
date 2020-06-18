package it.dpg.minigames.ballgame.controller;

import it.dpg.minigames.ballgame.model.BallMinigameModel;
import it.dpg.minigames.ballgame.model.BallMinigameModelImpl;
import it.dpg.minigames.ballgame.view.BallMinigameView;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.concurrent.TimeUnit;

public class BallGamecycleImpl implements BallGamecycle {
    private final BallMinigameModel model;
    private final BallMinigameView view;

    BallGamecycleImpl(BallMinigameView view, int maxScore) {
        this.view = view;
        this.model = new BallMinigameModelImpl(30, maxScore);
    }

    @Override
    public void signalUpButton(boolean isPressed) {
        model.setGoingUp(isPressed);
    }

    @Override
    public void signalDownButton(boolean isPressed) {
        model.setGoingDown(isPressed);
    }

    @Override
    public void signalLeftButton(boolean isPressed) {
        model.setGoingLeft(isPressed);
    }

    @Override
    public void signalRightButton(boolean isPressed) {
        model.setGoingRight(isPressed);
    }

    private void sleepMillis(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int startCycle() {
        boolean exitCycle = false;
        BallMinigameLevel level = BallMinigameLevel.LEVEL1;
        setup(level);
        startSequence();
        while(!exitCycle) {
            ImmutablePair<Double, Double> coords = model.calculateNextFrame();
            this.view.positionBall(coords.left, 100 - coords.right);
            this.view.setScore(model.getScore());
            if(model.isOver()) {
                this.view.setVictory();
                sleepMillis(1500);
                exitCycle = true;
            }
            sleepMillis(30);
        }
        this.view.closeView();
        return model.getScore();
    }

    private void setup(BallMinigameLevel level) {
        this.view.setupLevel(level);
        this.model.setupLevel(level);
        this.view.setScore(model.getScore());
    }

    private void startSequence() {
        view.setReady();
        sleepMillis(2000);
        view.removeReady();
        view.setGo();
        sleepMillis(1000);
        view.removeGo();
    }
}
