package it.dpg.maingame.view;

import it.dpg.maingame.model.character.Dice;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;
import java.util.Set;

public interface GridView {

    /**
     * this methods sets the view
     */
    void setView(Stage stage);

    /**
     * Sets the name of who's currently playing in the main text
     */
    void setCurrentPlayerName(String name);

    /**
     * Sets the remaining moves in a specific text bubble
     */
    void setRemainingMoves(int moves);

    /**
     * Changes the main Text in the game
     */
    void showText(String text);

    /**
     * removes main Text in the game
     */
    void removeText();

    /**
     * enables direction choice by enabling two buttons corresponding to the possible choices
     */
    void enableDirectionChoice(Set<ImmutablePair<Integer, Integer>> cells);

    /**
     * disables the direction choice buttons and removes them
     */
    void disableDirectionChoice();

    /**
     * generates the View for the Grid
     */
    void startGeneration(Stage stage);

    /**
     * Updates the Players positions in the Grid
     */
    void updatePlayers(Map<Integer, ImmutablePair<Integer,Integer>> players);

    /**
     * enables the dice button
     */
    void enableDiceThrow(Dice dice);

    /**
     * disables the dice button
     */
    void disableDiceThrow();
}
