package it.dpg.maingame.view;

import it.dpg.maingame.controller.GridObserver;
import it.dpg.maingame.model.Cell;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.character.Dice;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;
import java.util.Set;

public  class GridViewPlat implements GridView {

    private GridViewImpl view;

    public GridViewPlat (GridViewImpl view) {
        this.view = view;
    }

    @Override
    public void setView() {
        Platform.runLater(() -> view.setView());
    }

    @Override
    public void setCurrentPlayerName(String name) {

        Platform.runLater(() -> view.setCurrentPlayerName(name));

    }

    @Override
    public void setRemainingMoves(int moves) {
        Platform.runLater(() -> view.setRemainingMoves(moves));
    }

    @Override
    public void showText(String text) {
        Platform.runLater(() ->
                view.showText(text));
    }

    @Override
    public void removeText() {
        Platform.runLater(() -> view.removeText());
    }

    @Override
    public void enableDirectionChoice(Set<ImmutablePair<Integer, Integer>> cells) {
        Platform.runLater(() -> view.enableDirectionChoice(cells));
    }

    @Override
    public void disableDirectionChoice() {
        Platform.runLater(() -> view.disableDirectionChoice());
    }

    public void startGeneration() {
        Platform.runLater(() -> view.startGeneration());
    }

    @Override
    public void updatePlayers(Map<Integer, ImmutablePair<Integer, Integer>> players) {
        Platform.runLater(() -> view.updatePlayers(players));
    }

    @Override
    public void enableDiceThrow(Dice dice) {
        Platform.runLater(() -> view.enableDiceThrow(dice));
    }

    @Override
    public void disableDiceThrow() {
        Platform.runLater(() -> view.disableDiceThrow());
    }

    @Override
    public void closeView() {
        Platform.runLater(() -> view.closeView());
    }

    public void makeCellList(ImmutablePair<Integer, Integer> coordinates, String type, Set<ImmutablePair<Integer, Integer>> nextCells) {
        Platform.runLater(()->view.makeCellList(coordinates, type, nextCells));
    }
}
