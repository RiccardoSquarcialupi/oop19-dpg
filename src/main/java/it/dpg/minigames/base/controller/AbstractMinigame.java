package it.dpg.minigames.base.controller;

import it.dpg.maingame.model.character.Difficulty;
import it.dpg.minigames.base.view.MinigameView;

/**
 * Abstract implementation for a minigame
 * @author Davide Picchiotti
 * */

public abstract class AbstractMinigame implements Minigame {

    @Override
    public int start() {
        createView().setView();
        return createCycle().startCycle();
    }

    @Override
    public int randomizeScore(Difficulty difficulty) {
        return (int) (difficulty.getMultiplier() * getMaxScore());
    }

    /**
     * Get a pseudo max score to calculate cpu score
     * @return the max score
     * */
    public abstract int getMaxScore();

    /**
     * Create a MinigameView to set for the minigame
     * @return the view
     * @see MinigameView
     * */
    public abstract MinigameView createView();

    /**
     * Create a MinigameCycle to set for the minigame
     * @return the cycle
     * @see MinigameCycle
     * */
    public abstract MinigameCycle createCycle();
}
