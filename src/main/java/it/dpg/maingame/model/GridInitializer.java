package it.dpg.maingame.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;

public interface GridInitializer {

    /**
     * sets Json file based on the grid type
     */
    void setJson(GridType gridType);
    /**
     * creates a grid based on the grid type
     */
    Grid makeGrid(GridType gridType);
    /**
     * returns the created grid
     */
    Grid getGrid();
    /**
     * returns the first Cell of the Grid
     */
    Cell getFirst();
    /**
     * returns the last Cell of the Grid
     */
    Cell getLast();

}
