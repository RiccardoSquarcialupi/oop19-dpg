package it.dpg.minigames.base.view;

import it.dpg.maingame.view.View;

/**
 * Interface extension of view for minigames on a secondary window
 * @author Davide Picchiotti
 * */

public interface MinigameView extends View {

    /**
     * Close the minigame window
     * */
    void closeView();
}
