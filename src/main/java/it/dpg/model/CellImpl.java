package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

public class CellImpl implements Cell {

    Set<Cell> nextCell;
    boolean isAFork;
    ImmutablePair<Integer, Integer> coordinates;
    CellType type;

    @Override
    public Boolean isAFork() {
        return this.isAFork;
    }

    @Override
    public Set<Cell> getNext() {
        return this.nextCell;
    }

    @Override
    public ImmutablePair<Integer, Integer> getCoordinates() {
        return this.coordinates;
    }

    @Override
    public CellType getType() {
        return this.type;
    }
}
