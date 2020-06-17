package it.dpg.minigames.base.controller;

import it.dpg.maingame.model.character.Difficulty;
import it.dpg.minigames.base.view.MinigameView;

public abstract class AbstractMinigame implements Minigame {

    private MinigameView view = this.createView();
    private MinigameCycle cycle = this.createCycle();

    @Override
    public int start() {
        view = this.createView();
        cycle = this.createCycle();

        view.setView();
        return cycle.startCycle();
    }

    @Override
    public int randomizeScore(Difficulty difficulty) {
        return (int) (difficulty.getMultiplier() * getMaxScore());
    }

    public abstract int getMaxScore();
    public abstract MinigameView createView();
    public abstract MinigameCycle createCycle();
}
