package it.dpg.model;

public interface Character {
    String getName();
    void setTurn(Integer turn);
    Integer getTurn();
    Cell getPosition();
    boolean stepInDirection(Direction direction);
    void setDice(Dice dice);
    Dice getDice();
    Integer throwDice();
    Integer getMinigameScore();
}
