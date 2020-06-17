package it.dpg.minigames.base.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractMinigameView implements MinigameView {

    private Stage stage = new Stage();

    @Override
    public void setView() {
        stage.setScene(this.createScene());

        Platform.runLater(() -> stage.show());
    }

    @Override
    public void closeView() {
        Platform.runLater(() -> stage.close());
    }

    public abstract Scene createScene();
}
