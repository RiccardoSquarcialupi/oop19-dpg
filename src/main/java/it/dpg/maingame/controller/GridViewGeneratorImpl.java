package it.dpg.maingame.controller;

import it.dpg.maingame.model.Grid;
import it.dpg.maingame.view.GridView;
import it.dpg.maingame.view.GridViewImpl;
import javafx.stage.Stage;

public class GridViewGeneratorImpl implements GridViewGenerator {

    private final Grid grid;
    public GridView view;

    public GridViewGeneratorImpl (Grid grid) {
        this.grid = grid;
    }

    @Override
    public GridView generate(Stage stage) {
        this.view = new GridViewImpl(grid);
        view.startGeneration(stage);
        return this.view;
    }

    public GridView getView () {
        return this.view;
    }
}
