package it.dpg.model;

public interface Character {
    int getId();
    String getName();
    void setTurn(int turn);
    int getTurn();
    void setPosition(Cell position);
    Cell getPosition();
    boolean stepForward();
    boolean stepInDirection();
    void setDice(Dice dice);
    Dice getDice();
    int throwDice();
    void setMinigameScore(int score);
    int getMinigameScore();
}
