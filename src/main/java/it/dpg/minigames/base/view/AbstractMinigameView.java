package it.dpg.minigames.base.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractMinigameView implements MinigameView {

    private Stage stage;

    @Override
    public void setView() {
        setViewUsingAppThread();
    }

    @Override
    public void closeView() {
        Platform.runLater(() -> stage.close());
    }

    private void setViewUsingAppThread() {
        Scene scene = createScene();
        Platform.runLater(() -> {
            stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
    }

    public abstract Scene createScene();
}
