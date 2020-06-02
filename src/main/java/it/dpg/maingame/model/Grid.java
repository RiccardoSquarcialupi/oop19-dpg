package it.dpg.maingame.model;

public interface Grid {

    /**
     * return first Cell of the Grid
     */
    Cell getFirst();
    /**
     * returns last Cell of the Grid
     */
    Cell getLast();
    /**
     * returns a Cell based on the coordinates given
     */
    Cell getCellByCoordinates(Integer X, Integer Y);
}
