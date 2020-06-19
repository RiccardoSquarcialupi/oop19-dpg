package it.dpg.maingame.controller;

import it.dpg.maingame.model.*;
import it.dpg.maingame.view.GridView;
import it.dpg.maingame.view.GridViewImpl;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;

public class GridViewGeneratorImpl implements GridViewGenerator {

    public GridView view;
    private final GridType gridType;

    public GridViewGeneratorImpl (GridType type) {
        this.gridType = type;
    }

    @Override
    public ImmutablePair<Grid, GridView> generate(Stage stage) {

        Grid grid = new GridInitializerImpl().makeGrid(gridType);
        this.view = new GridViewImpl(grid.getCellList());
        this.view.setView(stage);
        return new ImmutablePair<>(grid, this.view);
    }
}
