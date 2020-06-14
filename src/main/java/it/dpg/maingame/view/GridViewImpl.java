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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.w3c.dom.css.Rect;

import java.util.*;

public class GridViewImpl implements GridView {

    private final Grid grid;
    public Scene scene;

    private VBox mainLayout = new VBox(10);
    private StackPane mainTextLayout = new StackPane();
    private StackPane diceLayout = new StackPane();
    private GridPane gridLayout = new GridPane();
    private StackPane movesLayout = new StackPane();

    private Map<ImmutablePair<Integer, Integer>, StackPane> cellPanes = new LinkedHashMap<>();
    private Map<Rectangle, ImmutablePair<Integer, Integer>> playerList = new LinkedHashMap<>();
    private Map<StackPane, HBox> playerPocketList = new LinkedHashMap<>();


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

            cellPanes.put(new ImmutablePair<>(i.getValue().getLeft(), i.getValue().getRight()), cellPane);
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

        HBox playerPocket = new HBox();

        cellPane.getChildren().addAll(circle, playerPocket);
        playerPocketList.put(cellPane, playerPocket);

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
    public void showText(String text) {
        if (mainTextLayout.getChildren().size()>1) {
            removeText();
        }
        mainTextLayout.getChildren().add(new Text(text));
    }

    @Override
    public void removeText() {
        mainTextLayout.getChildren().remove(1);

    }

    @Override
    public void enableDirectionChoice(Set<ImmutablePair<Integer, Integer>> cells) {

        removeText();
        showText("Choose a direction!");

        for (var i : cells) {
            for (var j : cellPanes.entrySet()) {
                if (i.equals(j.getKey())) {
                    Button button = new Button();
                    Circle circleB = new Circle(30);
                    button.setShape(circleB);
                    button.setMinSize(30, 30);
                    cellPanes.get(i).getChildren().add(button);
                }
            }
        }

    }

    @Override
    public void disableDirectionChoice() {

        removeText();

        for (var i : cellPanes.entrySet()) {
            if (i.getValue().getChildren().size()>2) {  //checks if there's a button
                i.getValue().getChildren().remove(2);
            }
        }
    }

    @Override
    public void updatePlayers(Map<Integer, Integer> players) {

        int counter = 0;

        if (!playerList.isEmpty()) {
            playerList.clear();
        }

        for (var i : players.entrySet()) {
            playerList.put(generatePlayer(counter), new ImmutablePair<>(i.getKey(), i.getValue()));
            counter++;
        }

        this.placePlayers();

    }

    /*
    creates a square representing the player
     */
    private Rectangle generatePlayer(Integer player){
        Rectangle square = new Rectangle(30, 30);
        if (player == 0) {
            square.setFill(Color.RED);
        } else if (player == 1) {
            square.setFill(Color.BLUE);
        } else {
            square.setFill(Color.YELLOW);
        }
        return square;
    }

    /*
    places square players in the grid
     */
    private void placePlayers(){

        //rimuove le precedenti pedine se ci sono
        for (var k : playerPocketList.entrySet()) {
            if (k.getValue().getChildren().size()>0) {
                k.getValue().getChildren().remove(0);
            }
        }

        //aggiunge nuove pedine in base alle nuove posizioni
        for (var i : playerList.entrySet()) {
            for (var j : cellPanes.entrySet()) {
                if (j.getKey().equals(i.getValue())) {  //cerca la cella di coordinate del player
                    playerPocketList.get(j.getValue()).getChildren().add(i.getKey());   //mette il quadrato al posto giusto
                }
            }
        }

    }

    @Override
    public void enableDiceThrow(Dice dice) {

    }

    @Override
    public void disableDiceThrow() {


    }
}
