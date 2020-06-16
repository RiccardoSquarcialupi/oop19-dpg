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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;

public class GridViewImpl implements GridView {

    private final Grid grid;
    public Scene scene;

    public String currentPlayer;
    public int movesLeft;

    private BorderPane root = new BorderPane();
    private VBox upperGroup = new VBox(5);
    private Group gridGroup = new Group();

    private Label mainText = new Label();
    private Label movesText = new Label();
    Button diceButton = new Button("Dice");

    //this map keeps track of the various cells (by graphic representation) and the coordinates of the cells connected to the Key Cell
    private Map<Circle, Set<ImmutablePair<Integer, Integer>>> circlesList = new LinkedHashMap<>();

    //this map keeps track of the player'id and corresponding graphic representation
    private Map<Integer, Rectangle> playerList = new LinkedHashMap<>();

    private ViewNodesFactory nodes = new ViewNodesFactoryImpl();

    //this integer is a constant that modifies the position of a graphic element based on their coordinates
    private int Xmodifier = 130;
    private int Ymodifier = 90;


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

        Group circleGroup = new Group();

        for (var i : grid.getCellList().entrySet()) {

            Circle circle;

            if (i.getKey().getType().equals(CellType.START) || i.getKey().getType().equals(CellType.END)) {
                circle = nodes.generateCell(Color.LIGHTBLUE);
            } else if (i.getKey().getType().equals(CellType.NORMAL)) {
                circle = nodes.generateCell(Color.LIGHTGREEN);
            } else {
                circle = nodes.generateCell(Color.WHITE);
            }

            int left = i.getValue().getLeft()*Xmodifier;
            int right = i.getValue().getRight()*Ymodifier;
            circle.setLayoutX(left);
            circle.setLayoutY(right);

            Set<ImmutablePair<Integer, Integer>> next = new HashSet<>();

            for (var j : i.getKey().getNext()) {
                next.add(j.getCoordinates());
            }
            circlesList.put(circle, next);

            circleGroup.getChildren().add(circle);
        }
        gridGroup.getChildren().addAll(nodes.generateLines(circlesList, Xmodifier, Ymodifier), circleGroup);

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
        diceLayout.getChildren().addAll(diceBox, diceButton);

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

        this.currentPlayer = name;
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
    public void enableDirectionChoice(Set<ImmutablePair<Integer, Integer>> cells) {

        for (var i : cells) {
            Button button = new Button();
            button.setShape(new Circle(4));
            button.setMinSize(40, 40);
            button.setLayoutX(i.getLeft()*Xmodifier-20);
            button.setLayoutY(i.getRight()*Ymodifier-20);
            String arrow = "|\nV";
            button.setText(arrow);
            button.setTextAlignment(TextAlignment.CENTER);
            gridGroup.getChildren().add(button);
        }

    }

    @Override
    public void disableDirectionChoice() {
        gridGroup.getChildren().removeIf(i -> i instanceof Button);
    }

    @Override
    public void updatePlayers(Map<Integer, ImmutablePair<Integer, Integer>> players) {

        int playerMod = 0;
        if (playerList.isEmpty()) {
            for (var i : players.entrySet()) {
                Rectangle playerSquare =  nodes.generatePlayer(i.getKey());
                playerList.put(i.getKey(), playerSquare);
                gridGroup.getChildren().add(playerSquare);
            }
        }

        for (var j : players.entrySet()) {
            playerList.get(j.getKey()).setLayoutX(j.getValue().getLeft()*Xmodifier+playerMod);
            playerList.get(j.getKey()).setLayoutY(j.getValue().getRight()*Ymodifier);
            for (var k : players.entrySet()) {
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
        diceButton.setText("D"+dice.getFaces());
    }

    @Override
    public void disableDiceThrow() {
        diceButton.setDisable(true);
    }
}
