package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

public interface GameObject {
    int getHeight();
    int getWidth();
    Pair<Integer, Integer> getPosition();
    void setPosition(final Pair<Integer, Integer> position);
    int getSpeedX();
    int getSpeedY();
    void setSpeedX(final int speedX);
    void setSpeedY(final int speedY);

    void updatePosition();
}
