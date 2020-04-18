package it.dpg.model;

import java.util.Optional;

public interface Cell {


    Optional<Cell> getLeft();
    Optional<Cell> getRight();
    Optional<Cell> getForward();
    Optional<Cell> getPrevious();
    Boolean isAFork();

    Integer getId();
    CellType getType();
}
