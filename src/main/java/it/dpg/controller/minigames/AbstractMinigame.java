package it.dpg.controller.minigames;

import it.dpg.model.character.Difficulty;
import it.dpg.view.minigames.MinigameView;

public abstract class AbstractMinigame implements Minigame{

    private MinigameView view = this.createView();
    private MinigameCycle cycle = this.createCycle();

    @Override
    public int start() {
        view.setView();
        return cycle.startCycle(view);
    }

    @Override
    public int randomizeScore(Difficulty difficulty) {
        return (int) (difficulty.getMultiplier() * getMaxScore());
    }

    public abstract int getMaxScore();
    public abstract MinigameView createView();
    public abstract MinigameCycle createCycle();
}
