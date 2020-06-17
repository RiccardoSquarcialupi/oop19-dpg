package it.dpg.minigames.ballgame.controller;

public class BallObserverImpl implements BallMinigameObserver{
    private BallGamecycle cycle;
    private boolean isGamecycleSet = false;

    @Override
    public void addGamecycle(BallGamecycle cycle) {
        this.cycle = cycle;
        isGamecycleSet = true;
    }

    private void checkGamecycle() {
        if(!isGamecycleSet) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void handleUpButton(boolean isPressed) {
        checkGamecycle();
        cycle.signalUpButton(isPressed);
    }

    @Override
    public void handleDownButton(boolean isPressed) {
        checkGamecycle();
        cycle.signalDownButton(isPressed);
    }

    @Override
    public void handleLeftButton(boolean isPressed) {
        checkGamecycle();
        cycle.signalLeftButton(isPressed);
    }

    @Override
    public void handleRightButton(boolean isPressed) {
        checkGamecycle();
        cycle.signalRightButton(isPressed);
    }
}
