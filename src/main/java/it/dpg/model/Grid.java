package it.dpg.model;

public interface Grid {
    Cell getFirst();
    Cell getLast();
    Cell getCellByID(int id);
}
