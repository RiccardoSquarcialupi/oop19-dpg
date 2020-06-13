package it.dpg.maingame.view;

import it.dpg.maingame.model.CellType;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.character.Dice;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class GridViewImpl implements GridView {

    private final Grid grid;
    public Scene scene;

    public VBox mainLayout = new VBox(10);
    public StackPane mainTextLayout = new StackPane();
    public StackPane diceLayout = new StackPane();
    public GridPane gridLayout = new GridPane();

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
            /*
            qua ci va Generate Cell
             */

            StackPane cellPane = new StackPane();
            Circle circle = new Circle(40);

            if (i.getKey().getType().equals(CellType.START) || i.getKey().getType().equals(CellType.END)) {
                circle.setFill(Color.LIGHTBLUE);
            } else if (i.getKey().getType().equals(CellType.NORMAL)) {
                circle.setFill(Color.LIGHTGREEN);
            }
            cellPane.getChildren().addAll(circle);

            gridLayout.add(cellPane, i.getValue().getLeft(), i.getValue().getRight());
        }

        /*
         * main Text Layout
         */
        Rectangle rectangle = new Rectangle(500, 100);
        rectangle.setFill(Color.WHITE);
        Text mainText = new Text("Welcome!");
        mainText.autosize();
        mainTextLayout.getChildren().addAll(rectangle, mainText);

        /*
         * Dice Layout
         */

        Rectangle diceBox = new Rectangle(100, 100);
        diceBox.setFill(Color.WHITE);
        diceLayout.getChildren().addAll(diceBox);

        /*
         * Main layout
         */

        mainLayout.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        mainLayout.getChildren().addAll(mainTextLayout, diceLayout, gridLayout);
        scene = new Scene(mainLayout, 1000, 1000, Color.AQUAMARINE);

        /* TODO
         * Chiedi dove va settata la scene
         */

        stage.setScene(scene);

    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    public void setCurrentPlayerName(String name) {

    }

    @Override
    public void setRemainingMoves(int moves) {

    }

    @Override
    public void enableDiceThrow(Dice dice) {

    }

    @Override
    public void disableDiceThrow() {

    }

    @Override
    public void showText(String text) {

    }

    @Override
    public void removeText() {

    }

    @Override
    public void enableDirectionChoice(Set<ImmutablePair<Integer, Integer>> cells) {

    }

    @Override
    public void disableDirectionChoice() {

    }

    @Override
    public void generateCell(int x, int y, String name, String colour) {
        //dovrebbe ritornare qualcosa, un bottone? o una struttura dati con tutte le informazioni?
    }

    @Override
    public void updatePlayers(Map<Integer, Integer> players) {

    }
}
