package it.dpg.model;

public interface Cell {
    Cell getLeft();
    Cell getRight();
    Cell getForward();
    Cell getPrevious();

    Integer getId();
    CellType getType();
}
