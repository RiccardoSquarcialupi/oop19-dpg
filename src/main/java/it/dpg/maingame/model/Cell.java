package it.dpg.maingame.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

public interface Cell {

    /**
     * returns whether the Cell is connected to a fork
     */
    Boolean isAFork();

    /**
     * returns the connected Cells
     */
    Set<Cell> getNext();

    /**
     * sets the next Cells
     */
    void setNext(Set<Cell> next);

    /**
     * returns the Cell coordinates
     */
    ImmutablePair<Integer, Integer> getCoordinates();

    /**
     * returns the Cell Type
     */
    CellType getType();
}
