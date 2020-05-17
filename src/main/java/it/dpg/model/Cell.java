package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;
import java.util.Set;

public interface Cell {

    Boolean isAFork();
    Set<Cell> getNext();
    void setNext(Set<Cell> next);
    ImmutablePair<Integer, Integer> getCoordinates();
    CellType getType();
}
