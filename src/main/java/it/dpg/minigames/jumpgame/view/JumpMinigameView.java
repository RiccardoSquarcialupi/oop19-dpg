package it.dpg.minigames.jumpgame.view;

import it.dpg.minigames.base.view.MinigameView;
import it.dpg.minigames.jumpgame.controller.input.InputObserver;

public interface JumpMinigameView extends MinigameView {
    void setGameSize(final int width, final int height);
    void createPlayer(final int x, final int y, final int size);
    void updatePlayer(final int x, final int y);
    void updatePlatform(final int x, final int y, int width, int height, final int id, final boolean exist);
    void updateScore(final int score);
    void setInputObserver(final InputObserver observer);
}
