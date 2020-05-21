package it.dpg.view.minigames;

import it.dpg.view.View;
import javafx.scene.Scene;

public interface MinigameView extends View {
    void setScene(final Scene scene);
    void closeView();
}
