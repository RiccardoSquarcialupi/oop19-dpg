package it.dpg.maingame.view.menu;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Method for start the Application
     *
     * @param stage Represent the "case" for the all Graphics Stuff
     */
    @Override
    public void start(Stage stage) throws Exception {
        MenuGUI m = new MenuGUI();
        m.initializeGUI(stage);
    }
}
