package it.dpg.maingame.view;

import it.dpg.maingame.controller.GridObserver;
import it.dpg.maingame.controller.GridObserverImpl;
import it.dpg.maingame.controller.gamecycle.GameCycle;
import it.dpg.maingame.launcher.Main;
import it.dpg.maingame.model.character.Dice;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class GridViewImpl implements GridView {

    private static Stage pStage;
    public Scene scene;
    private GridObserver obs;

    public int movesLeft;
    public String currentPlayer;

    private BorderPane root = new BorderPane();
    private VBox upperGroup = new VBox(5);
    private Group gridGroup = new Group();

    private Label mainText = new Label();
    private Label movesText = new Label();
    private Label playerText = new Label();
    Button diceButton = new Button("Dice");

    //this map keeps track of the various cells (by graphic representation) and the coordinates of the cells connected to the Key Cell
    private Map<Circle, Set<Pair<Integer, Integer>>> circlesList = new LinkedHashMap<>();

    //this map keeps track of the player'id and corresponding graphic representation
    private Map<Integer, Rectangle> playerList = new LinkedHashMap<>();

    private ViewNodesFactory nodes = new ViewNodesFactoryImpl();

    //these integers are constants that modify the position of a graphic element based on their coordinates
    private int Xmodifier = 130;
    private int Ymodifier = 90;

    public GridViewImpl(GameCycle gameCycle) {
        this.obs = new GridObserverImpl(gameCycle);
    }

    /**
     * this method creates the CirclesList filled with Circles and next Cell coordinates related to a Cell
     */
    public void makeCellList(Pair<Integer, Integer> coordinates, String type, Set<Pair<Integer, Integer>> nextCells) {

        Circle circle;

        //the color is dictated by the Cell Type
        if (type.equals("START") || type.equals("END")) {
            circle = nodes.generateCell(Color.LIGHTBLUE);
        } else if (type.equals("NORMAL")) {
            circle = nodes.generateCell(Color.LIGHTGREEN);
        } else {
            circle = nodes.generateCell(Color.WHITE);
        }

        int left = coordinates.getLeft() * Xmodifier;
        int right = coordinates.getRight() * Ymodifier;
        circle.setLayoutX(left);
        circle.setLayoutY(right);

        circlesList.put(circle, nextCells);

    }

    public void startGeneration() {

        StackPane mainTextLayout = new StackPane();
        StackPane diceLayout = new StackPane();
        Group movesLayout = new Group();

        /*
         Grid Group; everything is added to a Scroll Pane
         */
        ScrollPane sp = new ScrollPane();
        Group circleGroup = new Group();

        for (var i : circlesList.entrySet()) {       //every circle is added to the Group
            circleGroup.getChildren().add(i.getKey());
        }
        gridGroup.getChildren().addAll(nodes.generateLines(circlesList, Xmodifier, Ymodifier), circleGroup);
        sp.setContent(gridGroup);

        /*
         * upper Group
         */
        Rectangle rectangle = new Rectangle(500, 60);
        rectangle.setFill(Color.WHITE);
        mainTextLayout.getChildren().addAll(rectangle, mainText);

        Rectangle diceBox = new Rectangle(100, 100);
        diceBox.setFill(Color.WHITE);
        diceButton.setDisable(true);
        diceButton.setShape(new Rectangle(1, 1));
        diceButton.setMinSize(60, 60);

        /* action handler */
        diceButton.setOnMousePressed(actionEvent -> obs.throwDiceHandler());

        diceLayout.getChildren().addAll(diceBox, diceButton);

        Rectangle LabelBox = new Rectangle(500, 60);
        LabelBox.setFill(Color.WHITE);
        VBox labels = new VBox();
        labels.getChildren().addAll(movesText, playerText);
        labels.setAlignment(Pos.CENTER);
        movesLayout.getChildren().addAll(LabelBox, labels);

        upperGroup.getChildren().addAll(mainTextLayout, diceLayout, movesLayout);
        upperGroup.setAlignment(Pos.CENTER);

        /*
        root layout
         */

        root.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));

        root.setTop(upperGroup);
        root.setCenter(sp);

        scene = new Scene(root, 1500, 1000, Color.AQUAMARINE);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                obs.KeyPressHandler();
            }
        });

    }

    @Override
    public void setView() {
        pStage = Main.getPrimaryStage();
        pStage.setScene(this.scene);
    }

    @Override
    public void setCurrentPlayerName(String name) {
        this.currentPlayer = name;
        this.playerText.setText("Currently playing: " +currentPlayer);
    }

    @Override
    public void setRemainingMoves(int moves) {
        this.movesLeft = moves;
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
    public void enableDirectionChoice(Set<Pair<Integer, Integer>> cells) {

        //the method searches for the wanted fork inside the map, and creates and places buttons to the corresponding fork cells inside the Grid
        for (var i : cells) {
            Button button = new Button();
            button.setShape(new Circle(4));
            button.setMinSize(40, 40);
            button.setLayoutX(i.getLeft() * Xmodifier - 20);
            button.setLayoutY(i.getRight() * Ymodifier - 20);
            String arrow = "|\nV";
            button.setText(arrow);
            button.setTextAlignment(TextAlignment.CENTER);

            button.setOnMousePressed(actionEvent -> obs.choosePathHandler(i));

            gridGroup.getChildren().add(button);
        }

    }

    @Override
    public void disableDirectionChoice() {
        //the method removes every instance of buttons.
        gridGroup.getChildren().removeIf(i -> i instanceof Button);
    }

    @Override
    public void updatePlayers(Map<Integer, Pair<Integer, Integer>> players) {

        int playerMod = 0;  //player modifier is a modifier that changes every time more than one player sit on the same Cell
        if (playerList.isEmpty()) {
            //if there's still no players, they are generated
            for (var i : players.entrySet()) {
                Rectangle playerSquare = nodes.generatePlayer(i.getKey());
                playerList.put(i.getKey(), playerSquare);
                gridGroup.getChildren().add(playerSquare);
            }
        }

        //the players are placed in the grid by coordinates, which are the ones passed through @param; they are modified to fit nicely and avoid overlapping
        for (var j : players.entrySet()) {
            playerList.get(j.getKey()).setLayoutX(j.getValue().getLeft() * Xmodifier + playerMod);
            playerList.get(j.getKey()).setLayoutY(j.getValue().getRight() * Ymodifier);
            for (var k : players.entrySet()) {
                //this cycle counts how many player sit on the same cell, to apply a modifier accordingly
                if (j.getValue().equals(k.getValue())) {
                    playerMod = j.getKey() * 35;
                    break;
                }
            }
        }

    }

    @Override
    public void enableDiceThrow(Dice dice) {
        diceButton.setDisable(false);
        diceButton.setText("D" + dice.getFaces());
    }

    @Override
    public void disableDiceThrow() {
        diceButton.setDisable(true);
    }

    @Override
    public void closeView() {
        pStage.close();
    }
}
