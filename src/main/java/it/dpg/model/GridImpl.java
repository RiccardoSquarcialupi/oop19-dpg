package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;

public class GridImpl implements Grid {

    Cell first;
    Cell last;
    Map<Cell, ImmutablePair<Integer, Integer>> grid;

    @Override
    public Cell getFirst() {
        return this.first;
    }

    @Override
    public Cell getLast() {
        return this.last;
    }

    @Override
    public Cell getCellByCoordinates(Integer X, Integer Y) {
        for(var i : grid.entrySet()) {
            if (i.getValue().getLeft().equals(X) && i.getValue().getRight().equals(Y)) {
                return i.getKey();
            }
        }
        return null;
    }
}
