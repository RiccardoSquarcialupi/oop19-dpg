package it.dpg.view.minigames;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractMinigameView implements MinigameView {

    private Stage stage = new Stage();

    @Override
    public void setView() {
        stage.setHeight(600d);
        stage.setWidth(600d);
        stage.setResizable(false);

        stage.setScene(this.createScene());

        Platform.runLater(() -> stage.show());
    }

    public abstract Scene createScene();

    @Override
    public void closeView() {
        Platform.runLater(() -> stage.close());
    }
}
