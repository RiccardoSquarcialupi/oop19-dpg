package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

public interface Player {
    Pair<Integer, Integer> getPosition();
    void setPosition(final int x, final int y);
    int getSize();
    int getSpeedX();
    int getSpeedY();
    void setSpeedX(final int speedX);
    void setSpeedY(final int speedY);
    void updatePosition();
    void checkCollisionWithPlatform(final int platformX, final int platformY, final int platformLength);
}
