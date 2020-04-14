package it.dpg.controller.gamecycle;

public class TurnStateImpl implements TurnState {

    private boolean gameStarted = false;
    private volatile boolean diceThrown;//booleans are volatile to allow parallel threads access
    private volatile boolean isChoosing;

    public TurnStateImpl() {}

    @Override
    public void newTurn() {
        gameStarted = true;
        diceThrown = false;
        isChoosing = false;
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

    private void checkGameStarted() {
        if(!gameStarted) {
            throw new IllegalStateException("new turn was never called");
        }
    }
}
