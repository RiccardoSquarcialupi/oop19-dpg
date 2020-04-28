package it.dpg.model.minigames;

import it.dpg.model.Difficulty;

public abstract class AbstractMinigame implements Minigame{

    @Override
    //TODO implement start
    public int start() {
        return 0;
    }

    @Override
    public int randomizeScore(Difficulty difficulty) {
        return (int) (difficulty.getMultiplier() * getMaxScore());
    }

    public abstract int getMaxScore();
}
