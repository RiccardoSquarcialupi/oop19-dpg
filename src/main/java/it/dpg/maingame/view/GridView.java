package it.dpg.maingame.view;

import it.dpg.maingame.model.character.Dice;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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

    /**
     * generates the View for the Grid
     */
    void startGeneration(Stage stage);

    /**
     * Generates a new Cell in the Grid based on position, name and colour
     */
    StackPane generateCell(String colour);

    /**
     * Updates the Players positions in the Grid
     */
    void updatePlayers(Map<Integer, Integer> players);

    /**
     * returns the Grid Scene
     */
    Scene getScene();
}
