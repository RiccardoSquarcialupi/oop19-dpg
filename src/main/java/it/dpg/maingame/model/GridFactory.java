package it.dpg.maingame.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;

public interface GridFactory {

    void setJson();
    void makeGrid();
    Map<Cell, ImmutablePair<Integer, Integer>> getGrid();
    Cell getFirst();
    Cell getLast();

}
