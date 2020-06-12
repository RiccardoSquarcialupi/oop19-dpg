package it.dpg.maingame.launcher;

import it.dpg.maingame.view.menu.MenuGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MenuGUI m = new MenuGUI();
        m.initializeGUI(stage);
    }
}
