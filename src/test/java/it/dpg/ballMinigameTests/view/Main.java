package it.dpg.ballMinigameTests.view;

import it.dpg.minigames.ballgame.controller.BallGamecycle;
import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import it.dpg.minigames.ballgame.controller.BallMinigameObserver;
import it.dpg.minigames.ballgame.view.BallMinigameView;
import it.dpg.minigames.ballgame.view.BallViewImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private final BallMinigameObserver o = new BallMinigameObserver() {
        @Override
        public void addGamecycle(BallGamecycle cycle) {}

        @Override
        public void handleUpButton(boolean isPressed) {
            System.out.println("Up button: " + isPressed);
        }

        @Override
        public void handleDownButton(boolean isPressed) {
            System.out.println("Down button: " + isPressed);
        }

        @Override
        public void handleLeftButton(boolean isPressed) {
            System.out.println("Left button: " + isPressed);
        }

        @Override
        public void handleRightButton(boolean isPressed) {
            System.out.println("Right button: " + isPressed);
        }
    };

    private final Thread worker = new Thread(() -> {
        BallMinigameView view = new BallViewImpl(850, o);

        Platform.runLater(() -> {
            view.setView();
            view.setupLevel(BallMinigameLevel.LEVEL1);
            view.setScore(1);
            view.setReady();
        });
        sleepMillis(2000);
        Platform.runLater(() -> {
            view.removeReady();
            view.setScore(2);
            view.setGo();
        });
        sleepMillis(1000);
        Platform.runLater(view::removeGo);
        sleepMillis(500);
        Platform.runLater(() -> {
            view.positionBall(50, 50);
            view.setScore(999);
        });
        Platform.runLater(view::removeGo);
        sleepMillis(500);
        Platform.runLater(() -> view.positionBall(50, 100 - 60));
        sleepMillis(500);
        Platform.runLater(() -> view.positionBall(50, 100 - 70));
        Platform.runLater(view::setVictory);
        sleepMillis(500);
        Platform.runLater(view::closeView);
    });

    private void sleepMillis(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        worker.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}