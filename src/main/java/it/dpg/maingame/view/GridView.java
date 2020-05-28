package it.dpg.maingame.view;

import it.dpg.maingame.model.character.Dice;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;
import java.util.Set;

public interface GridView {
    void setCurrentPlayerName(String name);
    void setRemainingMoves(int moves);
    void enableDiceThrow(Dice dice);
    void disableDiceThrow();
    void showText(String text);
    void removeText();
    void enableDirectionChoice(Set<ImmutablePair<Integer, Integer>> cells);
    void disableDirectionChoice();
    void startGeneration();
    void generateCell(int x, int y, String name, String colour);
    void updatePlayers(Map<Integer, Integer> players);
}
