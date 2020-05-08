package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.File;
import java.util.Map;

public interface GridFactory {

    void setJson(File jsonFile);
    void makeGrid();
    Map<Cell, ImmutablePair<Integer, Integer>> getGrid();
    void getFirst();
    void getLast();

}
