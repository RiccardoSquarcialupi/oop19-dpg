package it.dpg.controller.gamecycle;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public class TurnStateImpl implements TurnState {

    private boolean gameStarted = false; //true if new turn has been called at least once
    private volatile boolean diceThrown; //booleans are volatile to make parallel thread access easier
    private volatile boolean isChoosing;
    private ImmutablePair<Integer, Integer> lastDirectionChosen;
    private boolean hasChosenDirection = false;
    private boolean turnPaused = false;

    public TurnStateImpl() {}

    @Override
    public void newTurn() {
        gameStarted = true;
        diceThrown = false;
        isChoosing = false;
        turnPaused = false;
        hasChosenDirection = false;
    }

    @Override
    public void setDiceThrown(boolean wasThrown) {

    }

    @Override
    public boolean wasDiceThrown() {
        return false;
    }

    @Override
    public void setChoice(boolean isChoosing) {

    }

    @Override
    public boolean isChoosing() {
        return false;
    }

    @Override
    public void setLastDirectionChoice(ImmutablePair<Integer, Integer> direction) {

    }

    @Override
    public Optional<ImmutablePair<Integer, Integer>> getLastDirectionChoice() {
        return Optional.empty();
    }

    @Override
    public void setTurnPause(boolean isPaused) {

    }

    @Override
    public boolean isPaused() {
        return false;
    }

    private void checkGameStarted() {
        if(!gameStarted) {
            throw new IllegalStateException("new turn was never called");
        }
    }
}
