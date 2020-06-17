package it.dpg.minigames.punchygame.view;

import it.dpg.minigames.base.view.AbstractMinigameView;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class MinigameViewImpl extends AbstractMinigameView {

    private Scene scene;

    @Override
    public Scene createScene() {
        scene = new Scene(new HBox());
        return scene;
    }
}
