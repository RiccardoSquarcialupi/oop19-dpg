package it.dpg.maingame.controller;

import it.dpg.maingame.controller.gamecycle.GameCycle;
import it.dpg.maingame.model.*;
import it.dpg.maingame.view.GridView;
import it.dpg.maingame.view.GridViewImpl;
import it.dpg.maingame.view.GridViewPlat;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GridViewGeneratorImpl implements GridViewGenerator {

    public Map<Cell, Pair<Integer, Integer>> gridMap;
    public GridViewPlat view;
    private final GridType gridType;
    private final GameCycle gameCycle;

    public GridViewGeneratorImpl(GridType type, GameCycle gameCycle) {
        this.gridType = type;
        this.gameCycle = gameCycle;
    }

    @Override
    public Pair<Grid, GridView> generate() {

        /* The grid is initialized */
        GridInitializer gridFact = new GridInitializerImpl();
        Grid grid = gridFact.makeGrid(gridType);

        /* I get the Cells List by Cell and Coordinates to create a List inside View */
        this.gridMap = grid.getCellList();

        /* The View is initialized and passed to View Platform*/
        GridViewImpl viewImpl = new GridViewImpl(gameCycle);
        this.view = new GridViewPlat(viewImpl);

        for (var i : gridMap.entrySet()) {
            /* I save the coordinates of the next cells in a new set */
            Set<Pair<Integer, Integer>> nextCell = new HashSet<>();
            for (var j : i.getKey().getNext()) {
                nextCell.add(j.getCoordinates());
            }
            view.makeCellList(i.getValue(), i.getKey().getType().toString(), nextCell);
        }

        view.startGeneration();
        view.setView();
        return new ImmutablePair<>(grid, view);
    }
}
