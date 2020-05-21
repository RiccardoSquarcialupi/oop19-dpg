package it.dpg.view.minigames;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractMinigameView implements MinigameView {

    private Stage stage = new Stage();
    private Scene scene;

    @Override
    public void setView() {
        stage.setHeight(600d);
        stage.setWidth(600d);
        stage.setResizable(false);
        stage.setScene(scene);

        Platform.runLater(() -> stage.show());
    }

    @Override
    public void setScene(final Scene scene) {
        this.scene = scene;
    }

    @Override
    public void closeView() {
        Platform.runLater(() -> stage.close());
    }
}
