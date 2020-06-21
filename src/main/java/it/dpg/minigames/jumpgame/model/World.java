package it.dpg.minigames.jumpgame.model;

import java.util.List;

public interface World {
    void update();
    boolean isGameOver();
    Player getPlayer();
    List<Platform> getPlatforms();
    int getWidth();
    int getHeight();
}
