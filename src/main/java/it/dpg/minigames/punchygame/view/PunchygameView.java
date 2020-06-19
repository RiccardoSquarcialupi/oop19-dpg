package it.dpg.minigames.punchygame.view;

import it.dpg.minigames.base.view.MinigameView;
import it.dpg.minigames.punchygame.controller.input.InputObserver;
import it.dpg.minigames.punchygame.model.Direction;

import java.util.List;

public interface PunchygameView extends MinigameView {
    void updateSacks(final List<Direction> sacks);
    void updateScore(final int score, final int combo);
    void updateTimer(final float timer);
    void updateBoxer(final Direction boxerDirection);
    void setInputObserver(final InputObserver observer);
}
