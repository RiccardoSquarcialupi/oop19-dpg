package it.dpg.maingame.model;

public interface Grid {
    Cell getFirst();
    Cell getLast();
    Cell getCellByCoordinates(Integer X, Integer Y);
}
