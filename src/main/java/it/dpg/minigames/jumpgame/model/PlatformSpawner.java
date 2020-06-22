package it.dpg.minigames.jumpgame.model;

import java.util.List;

public interface PlatformSpawner {
    List<Platform> getPlatforms();
    void updatePlatformsGeneration();
}
