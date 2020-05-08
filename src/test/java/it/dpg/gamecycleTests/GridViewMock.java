package it.dpg.gamecycleTests;

import it.dpg.view.GridView;

import java.util.Map;
import java.util.Set;

public class GridViewMock implements GridView {
    @Override
    public void setCurrentPlayerName(String name) {
        System.out.println("Current player: " + name);
    }

    @Override
    public void setRemainingMoves(int moves) {
        System.out.println("moves remaining: " + moves);
    }

    @Override
    public void enableDiceThrow(int diceFaces) {
        System.out.println("dice throw enabled");
    }

    @Override
    public void disableDiceThrow() {
        System.out.println("dice throw disabled");
    }

    @Override
    public void showText(String text) {
        System.out.println("text: " + text);
    }

    @Override
    public void removeText() {
        System.out.println("text removed");
    }

    @Override
    public void enableDirectionChoice(Set<Integer> cellsId) {
        System.out.println("direction choice enabled between cells " + cellsId.toString());
    }

    @Override
    public void disableDirectionChoice() {
        System.out.println("direction choice disabled");
    }

    @Override
    public void startGeneration() {

    }

    @Override
    public void generateCell(int x, int y, String name, String colour) {

    }

    @Override
    public void updatePlayers(Map<Integer, Integer> players) {
        System.out.println("players are at the following positions " + players);
    }
}
