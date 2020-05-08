package it.dpg.controller.gamecycle;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public class TurnStateImpl implements TurnState {

    private boolean gameStarted = false;
    private volatile boolean diceThrown;//booleans are volatile to make parallel thread access easier
    private volatile boolean isChoosing;
    private ImmutablePair<Integer, Integer> lastDirectionChosen;
    private boolean hasChosenDirection;

    public TurnStateImpl() {}

    @Override
    public void newTurn() {
        gameStarted = true;
        diceThrown = false;
        isChoosing = false;
        hasChosenDirection = false;
    }

    @Override
    public void setDiceThrown() {
        checkGameStarted();
        if(diceThrown) {
            throw new IllegalStateException("can't throw the dice twice in a turn");
        }

        diceThrown = true;
    }

    @Override
    public boolean isDiceThrown() {
        checkGameStarted();

        return diceThrown;
    }

    @Override
    public void choiceStarted() {
        checkGameStarted();

        this.isChoosing = true;
    }

    @Override
    public void choiceCompleted() {
        checkGameStarted();

        this.isChoosing = false;
    }

    @Override
    public boolean isChoosing() {
        checkGameStarted();

        return isChoosing;
    }

    @Override
    public synchronized void setLastDirectionChoice(ImmutablePair<Integer, Integer> direction) {
        checkGameStarted();

        hasChosenDirection = true;
        lastDirectionChosen = direction;
    }

    @Override
    public synchronized Optional<ImmutablePair<Integer, Integer>> getLastDirectionChoice() {
        checkGameStarted();

        if(hasChosenDirection) {
            return Optional.of(lastDirectionChosen);
        }
        return Optional.empty();
    }

    private void checkGameStarted() {
        if(!gameStarted) {
            throw new IllegalStateException("new turn was never called");
        }
    }
}
