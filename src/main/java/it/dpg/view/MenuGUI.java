/**
 *@author Riccardo Squarcialupi
 */
package it.dpg.view;
import it.dpg.controller.MenuController;
import it.dpg.controller.MenuControllerImpl;
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

import java.util.HashMap;
import java.util.Map;

/** The Menu when the application start
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
    private Map<Integer, String> optionPlayerMap = new HashMap<>();
    private Map<String, Difficulty> optionAIMap = new HashMap<>();
    private MenuController optionController = new MenuControllerImpl();

    /**Method for start the Application
     * @param stage Represent the "case" for the all Graphics Stuff
     */
    @Override
    public void start(Stage stage) {
        initializeGUI(stage);
    }

    /** Method for create the menu
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

        var rootBox = new VBox();
        rootBox.setSpacing(7);
        rootBox.setAlignment(Pos.BASELINE_CENTER);
        var mainScene = new Scene(rootBox, 300, 350);
        rootBox.getChildren().addAll(startBtn, creditBtn, optionsBtn, exitBtn);

        startBtn.setOnAction((ActionEvent event) -> startGame());

        exitBtn.setOnAction((ActionEvent event) -> exitGUI());

        creditBtn.setOnAction((ActionEvent event) -> displayCredit());

        optionsBtn.setOnAction((ActionEvent event) -> displayOptions());

        stage.setTitle("Dope Game Party-Menu");
        stage.setScene(mainScene);
        stage.show();

    }

    /** Method that create another window for Credits
     */
    @Override
    public void displayCredit() {
        Stage creditStage = new Stage();

        final TextArea creditText = new TextArea();
        var creditScene = new Scene(creditText, 300, 350);

        creditText.setText("Dope Game Party by \n\n" + "Riccardo Squarcialupi\n" +
                "Davide Picchiotti\n" +
                "Miriana Ascenzo\n" +
                "Davide Freddi");
        creditText.setEditable(false);

        creditStage.setScene(creditScene);
        creditStage.setTitle("Dope Game Party-Credits");
        creditStage.show();

    }

    /**Method that create another window for Options
     */
    @Override
    public void displayOptions() {
        Stage optionStage = new Stage();
        ObservableList<Integer> listNumPlayer = FXCollections.observableArrayList(1, 2, 3, 4);
        ObservableList<Integer> listNumAI = FXCollections.observableArrayList(0, 1, 2, 3, 4);

        ComboBox<Integer> numPlayer = new ComboBox<>(listNumPlayer);
        numPlayer.setPromptText("Numero di Giocatori");

        numPlayer.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            optionPlayerMap.clear();
            for (int i = newValue; i >= 1; i--) {
                optionPlayerMap.put(i, "Giocatore" + i);
            }
            optionController.setOptionsPlayer(optionPlayerMap);

        });

        ComboBox<Integer> numAI = new ComboBox<>(listNumAI);
        numAI.setPromptText("Numero di AI");

        numAI.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            optionAIMap.clear();
            for (int i = newValue; i >= 1; i--) {
                optionAIMap.put("AI" + i, Difficulty.EASY);
            }
            optionController.setOptionsAI(optionAIMap);
        });

        var optionBox = new VBox();
        var optionScene = new Scene(optionBox, 300, 250);
        optionBox.setSpacing(10);
        optionBox.setAlignment(Pos.BASELINE_CENTER);
        optionBox.getChildren().addAll(numPlayer, numAI);

        optionStage.setScene(optionScene);
        optionStage.setTitle("Dope game Party-Options");
        optionStage.show();

    }

    /**Method for exit the application
     */
    @Override
    public void exitGUI() {
        System.exit(0);
    }

    /**Method that start the game
     * @param
     * @param
     * @param
     */
    @Override
    public void startGame() {
        //startgame with variable from input
    }
}
