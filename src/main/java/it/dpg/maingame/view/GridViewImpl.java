package it.dpg.maingame.view;

import it.dpg.maingame.model.CellType;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.character.Dice;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;
import java.util.Set;

public class GridViewImpl implements GridView {

    private final Grid grid;
    public Scene scene;

    public VBox mainLayout = new VBox(10);
    public StackPane mainTextLayout = new StackPane();
    public StackPane diceLayout = new StackPane();
    public GridPane gridLayout = new GridPane();
    public StackPane movesLayout = new StackPane();


    public GridViewImpl (Grid grid) {
        this.grid = grid;
    }

    @Override
    public void startGeneration(Stage stage) {

        /*
         *  Grid layout
         */
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        gridLayout.setAlignment(Pos.CENTER);

        for (var i : grid.getCellList().entrySet()) {

            StackPane cellPane;

            if (i.getKey().getType().equals(CellType.START) || i.getKey().getType().equals(CellType.END)) {
                cellPane = generateCell("LIGHTBLUE");
            } else if (i.getKey().getType().equals(CellType.NORMAL)) {
                cellPane = generateCell("LIGHTGREEN");
            } else {
                cellPane = generateCell("WHITE");
            }

            gridLayout.add(cellPane, i.getValue().getLeft(), i.getValue().getRight());
        }

        /*
         * main Text Layout
         */
        Rectangle rectangle = new Rectangle(500, 60);
        rectangle.setFill(Color.WHITE);
        mainTextLayout.getChildren().addAll(rectangle, new Text("LET'S GET STARTED!"));

        /*
         * Dice Layout
         */

        Rectangle diceBox = new Rectangle(100, 100);
        diceBox.setFill(Color.WHITE);
        diceLayout.getChildren().addAll(diceBox);


        /*
         * remaining moves layout
         */
        Rectangle movesBox = new Rectangle(500, 60);
        movesBox.setFill(Color.WHITE);
        movesLayout.getChildren().addAll(movesBox, new Text("hewwo"));


        /*
         * Main layout
         */
        mainLayout.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        mainLayout.getChildren().addAll(mainTextLayout, movesLayout, diceLayout, gridLayout);
        scene = new Scene(mainLayout, 1000, 1000, Color.AQUAMARINE);

        /* TODO
         * Chiedi dove va settata la scene
         */

        stage.setScene(scene);

    }

    @Override
    public StackPane generateCell(String colour) {
        StackPane cellPane = new StackPane();
        Circle circle = new Circle(40);
        circle.setFill(Color.valueOf(colour));
        cellPane.getChildren().addAll(circle);

        return cellPane;
    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    public void setCurrentPlayerName(String name) {

        removeText();
        showText("Currently Playing: " + name);
    }

    @Override
    public void setRemainingMoves(int moves) {
        movesLayout.getChildren().remove(1);
        movesLayout.getChildren().add(new Text("remaining moves: " + moves));
    }

    @Override
    public void enableDiceThrow(Dice dice) {

    }

    @Override
    public void disableDiceThrow() {

    }

    @Override
    public void showText(String text) {
        mainTextLayout.getChildren().add(new Text(text));
    }

    @Override
    public void removeText() {
        mainTextLayout.getChildren().remove(1);

    }

    @Override
    public void enableDirectionChoice(Set<ImmutablePair<Integer, Integer>> cells) {

    }

    @Override
    public void disableDirectionChoice() {

    }

    @Override
    public void updatePlayers(Map<Integer, Integer> players) {

    }
}
