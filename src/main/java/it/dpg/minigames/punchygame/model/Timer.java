package it.dpg.minigames.punchygame.model;

public interface Timer {
    void timerDecrease(final float elapsed);
    void timerIncrease();
    float getTimeLeft();
}
