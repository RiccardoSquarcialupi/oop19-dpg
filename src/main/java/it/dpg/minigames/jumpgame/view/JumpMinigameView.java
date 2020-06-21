package it.dpg.minigames.jumpgame.view;

import it.dpg.minigames.base.view.MinigameView;

public interface JumpMinigameView extends MinigameView {
    void setGameSize(final int width, final int height);
    void createPlayer(final int x, final int y, final int size);
    void createPlatform(final int x, final int y, final int width, final int height);
    void updatePlayer(final int x, final int y);
}
