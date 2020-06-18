package it.dpg.minigames.punchygame.model;

import java.util.List;

public interface World {
    Direction getNextSack();
    List<Direction> getSacks();
    Score getScore();
    Timer getTimer();
    Boxer getBoxer();
    void triggerGameOver();
    boolean isGameOver();
}
