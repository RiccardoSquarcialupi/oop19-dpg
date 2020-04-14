package it.dpg.controller.gamecycle;

import java.util.Optional;

public class TurnStateImpl implements TurnState {

    private boolean gameStarted = false;
    private volatile boolean diceThrown;//booleans are volatile to allow parallel threads access
    private volatile boolean isChoosing;
    private Integer lastDirectionChosen;
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
    public synchronized void setLastDirectionChoice(int cellId) {
        checkGameStarted();

        hasChosenDirection = true;
        lastDirectionChosen = cellId;
    }

    @Override
    public synchronized Optional<Integer> getLastDirectionChoice() {
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
