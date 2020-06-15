package it.dpg.maingame.view;

import it.dpg.maingame.model.CellType;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.character.Dice;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;

public class GridViewImpl implements GridView {

    private final Grid grid;
    public Scene scene;

    private BorderPane root = new BorderPane();
    private VBox upperGroup = new VBox(5);
    private Group gridGroup = new Group();

    private Label mainText = new Label();
    private Label movesText = new Label();

    private Map<ImmutablePair<Integer, Integer>, StackPane> cellPanes = new LinkedHashMap<>();
    private Map<Integer, Rectangle> playerList = new LinkedHashMap<>();


    public GridViewImpl (Grid grid) {
        this.grid = grid;
    }

    @Override
    public void startGeneration(Stage stage) {

        StackPane mainTextLayout = new StackPane();
        StackPane diceLayout = new StackPane();
        GridPane gridLayout = new GridPane();
        StackPane movesLayout = new StackPane();

        /*
         Grid Group
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

        gridGroup.getChildren().addAll(generateLines(), gridLayout);

        /*
         * upper Group
         */
        Rectangle rectangle = new Rectangle(500, 60);
        rectangle.setFill(Color.WHITE);
        mainTextLayout.getChildren().addAll(rectangle, mainText);

        Rectangle diceBox = new Rectangle(100, 100);
        diceBox.setFill(Color.WHITE);
        diceLayout.getChildren().addAll(diceBox);

        Rectangle movesBox = new Rectangle(500, 60);
        movesBox.setFill(Color.WHITE);
        movesLayout.getChildren().addAll(movesBox, movesText);

        upperGroup.getChildren().addAll(mainTextLayout, diceLayout, movesLayout);


        /*
        root layout
         */

        root.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));

        root.setTop(upperGroup);
        root.setCenter(gridGroup);

        scene = new Scene(root, 1000, 1000, Color.AQUAMARINE);

    }

    private Group generateLines(){
        Group linesGroup = new Group();
        Line line1 = new Line();
        line1.setStartX(129.0f);
        line1.setStartY(10.0f);
        line1.setEndX(129.0f);
        line1.setEndY(700.0f);
        line1.setStrokeWidth(5);
        line1.setStroke(Color.FORESTGREEN);
        Line line2 = new Line();
        line2.setStartX(219.0f);
        line2.setStartY(400.0f);
        line2.setEndX(219.0f);
        line2.setEndY(600.0f);
        line2.setStrokeWidth(5);
        line2.setStroke(Color.FORESTGREEN);
        linesGroup.getChildren().addAll(line1, line2);

        return linesGroup;
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
        movesText.setText("remaining moves: " + moves);
    }

    @Override
    public void showText(String text) {
        mainText.setText(text);
    }

    @Override
    public void removeText() {
        mainText.setText("");

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
    public void updatePlayers(Map<Integer, ImmutablePair<Integer, Integer>> players) {

        if (playerList.isEmpty()) {
            for (var i : players.entrySet()) {
                Rectangle playerSquare = generatePlayer(i.getKey());
                playerList.put(i.getKey(), playerSquare);
                gridGroup.getChildren().add(playerSquare);
                playerSquare.setLayoutX(90);
                playerSquare.setLayoutY(0);
            }
        }

        /*
        for (var j : players.entrySet()) {

        }

        /*int counter = 0;

        if (!playerList.isEmpty()) {
            playerList.clear();
        }

        for (var i : players.entrySet()) {
            playerList.put(generatePlayer(counter), new ImmutablePair<>(i.getKey(), i.getValue()));
            counter++;
        }

        this.placePlayers();

         */

    }

    //creates a square representing the player

    private Rectangle generatePlayer(Integer player){
        Rectangle square = new Rectangle(30, 30);

        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        Color color = new Color(r, g, b, 1);
        square.setFill(color);
        return square;
    }

    /*
    //places square players in the grid

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
    */

    @Override
    public void enableDiceThrow(Dice dice) {

    }

    @Override
    public void disableDiceThrow() {


    }
}
