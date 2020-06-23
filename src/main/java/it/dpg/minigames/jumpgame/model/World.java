package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public interface World {
    int getWidth();
    int getHeight();
    void update();
    boolean isGameOver();
    Pair<Integer, Integer> getPlayerPosition();
    int getPlayerSize();
    List<Platform> getPlatforms();
    void setPlayerSpeedX(final int speedX);
    int getScore();
}
