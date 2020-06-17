package it.dpg.minigames.ballgame.controller;

public class BallObserverImpl implements BallMinigameObserver{
    private final BallGamecycle cycle;

    BallObserverImpl(BallGamecycle cycle) {
        this.cycle = cycle;
    }

    @Override
    public void handleUpButton(boolean isPressed) {
        cycle.signalUpButton(isPressed);
    }

    @Override
    public void handleDownButton(boolean isPressed) {
        cycle.signalDownButton(isPressed);
    }

    @Override
    public void handleLeftButton(boolean isPressed) {
        cycle.signalLeftButton(isPressed);
    }

    @Override
    public void handleRightButton(boolean isPressed) {
        cycle.signalRightButton(isPressed);
    }
}
