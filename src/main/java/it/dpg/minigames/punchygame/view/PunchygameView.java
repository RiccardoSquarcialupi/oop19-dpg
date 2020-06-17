package it.dpg.minigames.punchygame.view;

import it.dpg.minigames.base.view.MinigameView;
import it.dpg.minigames.punchygame.model.Direction;

import java.util.List;

public interface PunchygameView extends MinigameView {
    void updateSacks(List<Direction> sacks);
    void updateScore(int score);
    void updateTimer(int timer);
}
