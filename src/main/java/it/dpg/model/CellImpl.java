package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

public class CellImpl implements Cell {

    private final Set<Cell> nextCell;
    private final boolean isAFork;
    private final ImmutablePair<Integer, Integer> coordinates;
    private final CellType type;

    public CellImpl (final Set<Cell> nextCell, final boolean isAFork, final ImmutablePair<Integer, Integer> coordinates, final CellType type) {
        this.nextCell = nextCell;
        this.isAFork = isAFork;
        this.coordinates = coordinates;
        this.type = type;
    }

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
