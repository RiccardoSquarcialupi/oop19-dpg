package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

public interface Platform {
    Pair<Integer, Integer> getPosition();
    int getHeight();
    int getWidth();
    int getX();
    int getY();
    int getId();
    void setSpeedY(final int speedY);
    void updatePosition();
}
