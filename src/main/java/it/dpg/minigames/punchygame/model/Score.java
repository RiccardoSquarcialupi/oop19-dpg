package it.dpg.minigames.punchygame.model;

public interface Score {
    void incrementScore();
    int getPoints();
    int getMultiplier();
    void resetCombo();
}
