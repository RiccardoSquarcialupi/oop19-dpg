package it.dpg.minigames.base.view;

import it.dpg.maingame.view.View;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Abstract implementation of MinigameView
 * @author Davide Picchiotti
 * @see View
 * */

public abstract class AbstractMinigameView implements View {

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

    /**
     * Create the scene to set on the view
     * */
    public abstract Scene createScene();
}
