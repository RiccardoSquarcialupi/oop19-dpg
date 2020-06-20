package it.dpg.maingame.controller;

import it.dpg.maingame.controller.gamecycle.GameCycle;
import it.dpg.maingame.model.*;
import it.dpg.maingame.view.GridView;
import it.dpg.maingame.view.GridViewImpl;
import it.dpg.maingame.view.GridViewPlat;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;

public class GridViewGeneratorImpl implements GridViewGenerator {

    public Map<Cell, ImmutablePair<Integer, Integer>> gridMap;
    public GridView view;
    private final GridType gridType;

    public GridViewGeneratorImpl (GridType type, GameCycle gameCycle) {
        this.gridType = type;
    }

    @Override
    public ImmutablePair<Grid, GridView> generate() {
        GridInitializer gridFact = new GridInitializerImpl();
        Grid grid = gridFact.makeGrid(gridType);
        this.gridMap = grid.getCellList();
        this.view = new GridViewPlat(gridMap);
        view.setView();
        return new ImmutablePair<>(grid, view);
    }
}
