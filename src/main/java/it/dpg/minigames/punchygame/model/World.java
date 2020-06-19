package it.dpg.minigames.punchygame.model;

import java.util.List;

public interface World {
    void checkSackHit(final Direction direction);
    List<Direction> getSacks();
    int getScore();
    int getScoreMultiplier();
    float getTimer();
    void updateTimer(final float elapsed);
    Direction getBoxerDirection();
    boolean isGameOver();
}
