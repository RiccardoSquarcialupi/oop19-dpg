package it.dpg.minigames.ballgame.controller;

public class BallObserverImpl implements BallObserver {
    private BallGamecycle cycle;
    private boolean isGamecycleSet = false;

    @Override
    public void addGamecycle(BallGamecycle cycle) {
        this.cycle = cycle;
        isGamecycleSet = true;
    }

    private void checkGamecycle() {
        if (!isGamecycleSet) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void handleUpButton(boolean isPressed) {
        checkGamecycle();
        cycle.signalGoingUp(isPressed);
    }

    @Override
    public void handleDownButton(boolean isPressed) {
        checkGamecycle();
        cycle.signalGoingDown(isPressed);
    }

    @Override
    public void handleLeftButton(boolean isPressed) {
        checkGamecycle();
        cycle.signalGoingleft(isPressed);
    }

    @Override
    public void handleRightButton(boolean isPressed) {
        checkGamecycle();
        cycle.signalGoingRight(isPressed);
    }
}
