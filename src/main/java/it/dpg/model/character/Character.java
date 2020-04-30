package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

public interface Character {
    int getId();
    String getName();
    void setTurn(int turn);
    int getTurn();
    void setPosition(Cell position);
    ImmutablePair<Integer, Integer> getPosition();
    CellType getCellType();
    boolean stepForward();
    boolean stepInDirection(ImmutablePair<Integer, Integer> coordinates);
    void setDice(Dice dice);
    Dice getDice();
    int throwDice();
    void setMinigameScore(int score);
    int getMinigameScore();
}
