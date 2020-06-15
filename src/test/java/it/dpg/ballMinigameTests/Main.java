package it.dpg.ballMinigameTests;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import it.dpg.minigames.ballgame.view.BallMinigameView;
import it.dpg.minigames.ballgame.view.BallViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        BallMinigameView view = new BallViewImpl(850);
        view.setView();
        view.setupLevel(BallMinigameLevel.LEVEL1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
