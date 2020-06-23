package it.dpg.maingame.model.character;

import it.dpg.maingame.model.CellType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

public interface Character {

    /**
     * return character numeric id
     * */
    int getId();

    /**
     * return the name assigned to the character
     * */
    String getName();

    /**
     * set turn order for the character
     * */
    void setTurn(int turn);

    /**
     * get turn order for the character
     * */
    int getTurn();

    /**
     * set an absolute position for the character on the grid
     * */
    void setPosition(final Pair<Integer, Integer> coordinates);

    /**
     * get position of the character in the grid
     * */
    Pair<Integer, Integer> getPosition();

    /**
     * get positions on the grid adjacent to the character, excluding backwards
     * */
    Set<Pair<Integer, Integer>> getAdjacentPositions();

    /**
     * get the type of cell the player is on
     * */
    CellType getCellType();

    /**
     * if no fork is detected and there are any remaining moves, move the character one position forward on the grid
     * */
    boolean stepForward();

    /**
     * move the character one position backwards, without counting remaining moves
     * */
    void stepBackward();

    /**
     * move the character in the given adjacent direction
     * */
    boolean stepInDirection(final Pair<Integer, Integer> coordinates);

    /**
     * set the dice type used for dice throws
     * */
    void setDice(final Dice dice);

    /**
     * get the dice type used for dice throws
     * */
    Dice getDice();

    /**
     * throw the dice, returning the number
     * */
    int throwDice();

    /**
     * set the score obtained in the last played minigame
     * */
    void setMinigameScore(final int score);

    /**
     * get the score obtained in the last played minigame
     * */
    int getMinigameScore();
}
