package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public interface World {
    int getWidth();
    int getHeight();
    void update();
    boolean isGameOver();
    Pair<Integer, Integer> getPlayerPosition();
    int getPlayerSize();
    Map<Integer, Pair<Integer, Integer>> getPlatformsPositions();
    Map<Integer, Integer> getPlatformsWidth();
    Map<Integer, Integer> getPlatformsHeight();
    void setPlayerSpeedX(final int speedX);
}
