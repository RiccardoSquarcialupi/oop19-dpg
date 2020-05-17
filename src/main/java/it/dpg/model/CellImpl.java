package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashSet;
import java.util.Set;

public class CellImpl implements Cell {

    private Set<Cell> nextCell = new HashSet<>();
    private final boolean isAFork;
    private final ImmutablePair<Integer, Integer> coordinates;
    private final CellType type;

    public CellImpl (final boolean isAFork, final ImmutablePair<Integer, Integer> coordinates, final CellType type) {
        this.isAFork = isAFork;
        this.coordinates = coordinates;
        this.type = type;
    }

    @Override
    public Boolean isAFork() {
        return this.isAFork;
    }

    @Override
    public void setNext(Set<Cell> next) {
        this.nextCell = next;
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
