package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

public interface Grid {
    Cell getFirst();
    Cell getLast();
    Cell getCellByCoordinates(Integer X, Integer Y);
}
