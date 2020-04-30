package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

public interface Grid {
    Cell getFirst();
    Cell getLast();
    Cell getCellByCoordinates(int X, int Y);
    ImmutablePair<Integer, Integer> getCoordinatesByCell(Cell cell);
}
