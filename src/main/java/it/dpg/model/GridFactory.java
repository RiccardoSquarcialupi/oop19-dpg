package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;

public interface GridFactory {

    void setJson(GridType grid_type);
    void makeGrid();
    Map<Cell, ImmutablePair<Integer, Integer>> getGrid();
    void getFirst();
    void getLast();

}
