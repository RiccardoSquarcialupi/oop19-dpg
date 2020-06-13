package it.dpg.maingame.controller;

import it.dpg.maingame.view.GridView;
import javafx.stage.Stage;

public interface GridViewGenerator {

    /**
     * generates a new Grid View and starts it
     * @return created Grid View
     */
    GridView generate(Stage stage);

    /**
     * returns the created grid View
     */
    GridView getView();
}
