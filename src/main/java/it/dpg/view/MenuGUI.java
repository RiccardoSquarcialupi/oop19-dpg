package it.dpg.view;

import it.dpg.controller.MenuController;
import it.dpg.controller.MenuControllerImpl;
import it.dpg.model.character.Difficulty;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * The Menu when the application start
 *
 * @author Riccardo Squarcialupi
 */
public class MenuGUI extends Application implements MenuView {

    /**
     * startBtn is the Button for Start the Game
     * creditBtn is the Button for display the Credits
     * optionsBtm is the Button for display the options
     * exitBtn is the Button for exit the Application
     * optionPlayerMap, Map for keeping track the number of player selected in the options menu
     * optionAIMap, Map for keeping track the number of AI selected
     * optionController, use for set and get option
     */

    private final Button startBtn = new Button("Start");
    private final Button creditBtn = new Button("Credits");
    private final Button optionsBtn = new Button("Options");
    private final Button exitBtn = new Button("Exit");
    private MenuController optionController = new MenuControllerImpl();

    /**
     * Method for start the Application
     *
     * @param stage Represent the "case" for the all Graphics Stuff
     */
    @Override
    public void start(Stage stage) {
        initializeGUI(stage);
    }

    /**
     * Method for create the menu
     *
     * @param stage Represent the "case" for the all Graphics Stuff
     */
    private void initializeGUI(Stage stage) {

        startBtn.setPrefSize(100, 60);
        startBtn.setFont(Font.font(15));
        creditBtn.setPrefSize(100, 60);
        creditBtn.setFont(Font.font(15));
        optionsBtn.setPrefSize(100, 60);
        optionsBtn.setFont(Font.font(15));
        exitBtn.setPrefSize(100, 60);
        exitBtn.setFont(Font.font(15));

        VBox rootBox = new VBox();
        rootBox.setSpacing(7);
        rootBox.setAlignment(Pos.BASELINE_CENTER);
        Scene mainScene = new Scene(rootBox, 300, 350);
        rootBox.getChildren().addAll(startBtn, creditBtn, optionsBtn, exitBtn);

        startBtn.setOnAction((ActionEvent event) -> startGame());

        exitBtn.setOnAction((ActionEvent event) -> exitGUI());

        creditBtn.setOnAction((ActionEvent event) -> displayCredit());

        optionsBtn.setOnAction((ActionEvent event) -> displayOptions());

        stage.setTitle("Dope Game Party-Menu");
        stage.setScene(mainScene);
        stage.show();

    }

    /**
     * Method that create another window for Credits
     */
    @Override
    public void displayCredit() {
        Stage creditStage = new Stage();

        final TextArea creditText = new TextArea();
        Scene creditScene = new Scene(creditText, 300, 350);

        creditText.setText("Dope Game Party by \n\n" + "Riccardo Squarcialupi\n" +
                "Davide Picchiotti\n" +
                "Miriana Ascenzo\n" +
                "Davide Freddi");
        creditText.setEditable(false);

        creditStage.setScene(creditScene);
        creditStage.setTitle("Dope Game Party-Credits");
        creditStage.show();

    }

    /**
     * Method that create another window for Options
     */
    @Override
    public void displayOptions() {
        Stage optionStage = new Stage();
        ObservableList<Integer> listNumPlayer = FXCollections.observableArrayList(1, 2, 3, 4);
        ObservableList<Integer> listNumAI = FXCollections.observableArrayList(0, 1, 2, 3, 4);
        ObservableList<Difficulty> listDifficultyAI = FXCollections.observableArrayList(Difficulty.EASY, Difficulty.NORMAL, Difficulty.HARD, Difficulty.CRAZY);

        List<ComboBox<Difficulty>> listDifficulty = new ArrayList<>();
        listDifficulty.add(0, new ComboBox<>(listDifficultyAI));
        listDifficulty.add(1, new ComboBox<>(listDifficultyAI));
        listDifficulty.add(2, new ComboBox<>(listDifficultyAI));
        listDifficulty.add(3, new ComboBox<>(listDifficultyAI));

        listDifficulty.get(0).setDisable(true);
        listDifficulty.get(0).setPromptText("Difficolta' AI1");
        listDifficulty.get(0).valueProperty().addListener((observableValue, oldValue, newValue) -> optionController.setAIDifficulty(0, newValue));

        listDifficulty.get(1).setDisable(true);
        listDifficulty.get(1).setPromptText("Difficolta' AI2");
        listDifficulty.get(1).valueProperty().addListener((observableValue, oldValue, newValue) -> optionController.setAIDifficulty(1, newValue));

        listDifficulty.get(2).setDisable(true);
        listDifficulty.get(2).setPromptText("Difficolta' AI3");
        listDifficulty.get(2).valueProperty().addListener((observableValue, oldValue, newValue) -> optionController.setAIDifficulty(2, newValue));

        listDifficulty.get(3).setDisable(true);
        listDifficulty.get(3).setPromptText("Difficolta' AI4");
        listDifficulty.get(3).valueProperty().addListener((observableValue, oldValue, newValue) -> optionController.setAIDifficulty(3, newValue));

        ComboBox<Integer> numPlayer = new ComboBox<>(listNumPlayer);
        numPlayer.setPromptText("Numero di Giocatori");

        numPlayer.valueProperty().addListener((observableValue, oldValue, newValue) -> optionController.setOptionsPlayer(newValue));

        ComboBox<Integer> numAI = new ComboBox<>(listNumAI);
        numAI.setPromptText("Numero di AI");

        numAI.valueProperty().addListener((observableValue, oldValue, newValue) -> {

            optionController.setOptionsAI(newValue);
            listDifficulty.get(0).valueProperty().set(Difficulty.EASY);
            listDifficulty.get(1).valueProperty().set(Difficulty.EASY);
            listDifficulty.get(2).valueProperty().set(Difficulty.EASY);
            listDifficulty.get(3).valueProperty().set(Difficulty.EASY);

            switch (newValue) {
                case 0:
                    listDifficulty.get(0).setDisable(true);
                    listDifficulty.get(1).setDisable(true);
                    listDifficulty.get(2).setDisable(true);
                    listDifficulty.get(3).setDisable(true);
                    break;
                case 1:
                    listDifficulty.get(0).setDisable(false);
                    listDifficulty.get(1).setDisable(true);
                    listDifficulty.get(2).setDisable(true);
                    listDifficulty.get(3).setDisable(true);
                    break;
                case 2:
                    listDifficulty.get(0).setDisable(false);
                    listDifficulty.get(1).setDisable(false);
                    listDifficulty.get(2).setDisable(true);
                    listDifficulty.get(3).setDisable(true);
                    break;
                case 3:
                    listDifficulty.get(0).setDisable(false);
                    listDifficulty.get(1).setDisable(false);
                    listDifficulty.get(2).setDisable(false);
                    listDifficulty.get(3).setDisable(true);
                    break;
                case 4:
                    listDifficulty.get(0).setDisable(false);
                    listDifficulty.get(1).setDisable(false);
                    listDifficulty.get(2).setDisable(false);
                    listDifficulty.get(3).setDisable(false);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + newValue);
            }
        });

        VBox optionBox = new VBox();
        Scene optionScene = new Scene(optionBox, 300, 250);
        optionBox.setSpacing(10);
        optionBox.setAlignment(Pos.BASELINE_CENTER);
        optionBox.getChildren().addAll(numPlayer, numAI, listDifficulty.get(0), listDifficulty.get(1), listDifficulty.get(2), listDifficulty.get(3));

        optionStage.setScene(optionScene);
        optionStage.setTitle("Dope game Party-Options");
        optionStage.show();
    }

    /**
     * Method for exit the application
     */
    @Override
    public void exitGUI() {
        System.exit(0);
    }

    /**
     * Method that start the game
     *
     * @param
     * @param
     * @param
     */
    @Override
    public void startGame() {
        //startgame with variable from input
    }
}
