package it.dpg.model.character;

import it.dpg.model.Cell;
import it.dpg.model.CellType;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

public interface Character {
    int getId();
    String getName();
    void setTurn(int turn);
    int getTurn();
    void setPosition(final ImmutablePair<Integer, Integer> coordinates);
    ImmutablePair<Integer, Integer> getPosition();
    Set<ImmutablePair<Integer, Integer>> getAdjacentPositions();
    CellType getCellType();
    boolean stepForward();
    boolean stepInDirection(final ImmutablePair<Integer, Integer> coordinates);
    void setDice(final Dice dice);
    Dice getDice();
    int throwDice();
    void setMinigameScore(final int score);
    int getMinigameScore();
}
