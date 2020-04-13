package it.dpg.controller.gamecycle;

public class TurnStateImpl implements TurnState {

    private boolean gameStarted = false;
    private boolean diceThrown;
    private boolean isChoosing;

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
    public boolean wasDiceThrown() {
        checkGameStarted();

        return diceThrown;
    }

    @Override
    public void setIsChoosing(boolean isChoosing) {
        checkGameStarted();

        this.isChoosing = isChoosing;
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
