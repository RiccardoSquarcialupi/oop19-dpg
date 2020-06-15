package it.dpg.maingame.view;

import it.dpg.maingame.model.CellType;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.ViewNodesFactory;
import it.dpg.maingame.model.ViewNodesFactoryImpl;
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

    private Map<Circle, ImmutablePair<Integer, Integer>> circlesList = new LinkedHashMap<>();
    private Map<Integer, Rectangle> playerList = new LinkedHashMap<>();

    private ViewNodesFactory nodes = new ViewNodesFactoryImpl();

    private int modifier = 90;


    public GridViewImpl (Grid grid) {
        this.grid = grid;
    }

    @Override
    public void startGeneration(Stage stage) {

        StackPane mainTextLayout = new StackPane();
        StackPane diceLayout = new StackPane();
        StackPane movesLayout = new StackPane();

        /*
         Grid Group
         */

        //gridGroup.getChildren().add(nodes.generateLines(circlesList));

        for (var i : grid.getCellList().entrySet()) {

            Circle circle;

            if (i.getKey().getType().equals(CellType.START) || i.getKey().getType().equals(CellType.END)) {
                circle = nodes.generateCell(Color.LIGHTBLUE);
            } else if (i.getKey().getType().equals(CellType.NORMAL)) {
                circle = nodes.generateCell(Color.LIGHTGREEN);
            } else {
                circle = nodes.generateCell(Color.WHITE);
            }

            int left = i.getValue().getLeft()*modifier;
            int right = i.getValue().getRight()*modifier;
            circle.setLayoutX(left);
            circle.setLayoutY(right);

            circlesList.put(circle, new ImmutablePair<>(left, right));

            gridGroup.getChildren().add(circle);
        }

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
        /*
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
        }*/

    }

    @Override
    public void disableDirectionChoice() {
/*
        removeText();

        for (var i : cellPanes.entrySet()) {
            if (i.getValue().getChildren().size()>2) {  //checks if there's a button
                i.getValue().getChildren().remove(2);
            }
        }*/
    }

    @Override
    public void updatePlayers(Map<Integer, ImmutablePair<Integer, Integer>> players) {

        if (playerList.isEmpty()) {
            for (var i : players.entrySet()) {
                Rectangle playerSquare =  nodes.generatePlayer(i.getKey());
                playerList.put(i.getKey(), playerSquare);
                gridGroup.getChildren().add(playerSquare);
                playerSquare.setLayoutX(0);
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
