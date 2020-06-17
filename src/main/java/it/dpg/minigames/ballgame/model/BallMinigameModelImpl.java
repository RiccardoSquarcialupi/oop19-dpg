package it.dpg.minigames.ballgame.model;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class BallMinigameModelImpl implements BallMinigameModel{
    private final BallEnvironmentFactory factory;
    private BallEnvironment env;
    private boolean levelIsReady = false;
    private boolean isGoingLeft = false;
    private boolean isGoingRight = false;
    private boolean isGoingUp = false;
    private boolean isGoingDown = false;

    BallMinigameModelImpl(int expectedFPS) {
        this.factory = new BallEnvironmentFactoryImpl(expectedFPS);
    }

    private void checkLevelSetup() {
        if(!levelIsReady) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void setupLevel(BallMinigameLevel level) {
        env = factory.createEnvironment(level);
        levelIsReady = true;
    }

    @Override
    public void setGoingLeft(boolean isGoingLeft) {
        this.isGoingLeft = isGoingLeft;
    }

    @Override
    public void setGoingRight(boolean isGoingRight) {
        this.isGoingRight = isGoingRight;
    }

    @Override
    public void setGoingUp(boolean isGoingUp) {
        this.isGoingUp = isGoingUp;
    }

    @Override
    public void setGoingDown(boolean isGoingDown) {
        this.isGoingDown = isGoingDown;
    }


    @Override
    public ImmutablePair<Double, Double> calculateNextFrame() {
        checkLevelSetup();
        env.nextFrame(isGoingUp, isGoingDown, isGoingLeft, isGoingRight);
        return new ImmutablePair<>(env.getX(), env.getY());
    }

    @Override
    public int getScore() {
        checkLevelSetup();
        return env.getScore();
    }

    @Override
    public boolean isOver() {
        checkLevelSetup();
        return env.goalReached();
    }
}
