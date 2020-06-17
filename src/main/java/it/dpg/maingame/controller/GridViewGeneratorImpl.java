package it.dpg.maingame.controller;

import it.dpg.maingame.model.Cell;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.view.GridView;
import it.dpg.maingame.view.GridViewImpl;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;

public class GridViewGeneratorImpl implements GridViewGenerator {

    private final Map<Cell, ImmutablePair<Integer, Integer>> grid;
    public GridView view;

    public GridViewGeneratorImpl (Map<Cell, ImmutablePair<Integer, Integer>> grid) {
        this.grid = grid;
    }

    @Override
    public GridView generate(Stage stage) {
        this.view = new GridViewImpl(grid);
        view.setView(stage);
        return this.view;
    }

    public GridView getView () {
        return this.view;
    }
}
