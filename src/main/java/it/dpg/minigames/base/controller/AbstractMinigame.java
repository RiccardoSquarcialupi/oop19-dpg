package it.dpg.minigames.base.controller;

import it.dpg.maingame.model.character.Difficulty;
import it.dpg.minigames.base.view.MinigameView;

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

    public abstract int getMaxScore();
    public abstract MinigameView createView();
    public abstract MinigameCycle createCycle();
}
