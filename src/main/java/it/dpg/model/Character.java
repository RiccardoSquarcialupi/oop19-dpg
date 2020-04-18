package it.dpg.model;

public interface Character {
    void setName(String name);
    String getName();
    void setTurn(Integer turn);
    Integer getTurn();
    Cell getPosition();
    boolean stepInDirection(Direction direction);
    void setDice(Dice dice);
    Dice getDice();
    Integer getMinigameScore();
}
