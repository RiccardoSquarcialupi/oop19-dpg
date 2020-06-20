package it.dpg.maingame.controller;

import org.apache.commons.lang3.tuple.ImmutablePair;

public interface GridObserver {

    /**
     * choosen Path handler
     */
    void choosePathHandler(ImmutablePair<Integer, Integer> path);

    /**
     * dice throw handler
     */
    void throwDiceHandler();

}
