package it.dpg.minigames.molegame.view;

import it.dpg.minigames.base.view.MinigameView;

import java.util.List;

public interface HitTheMoleView extends MinigameView {

    /**
     * update the score of the view
     */
    void updateScore();

    /**
     * decrease the timer
     */
    void updateTimer();

    /**
     * update the view of out moles
     * @param moleOut list of the out mole
     */
    void updateMole(List<Integer> moleOut);

}
