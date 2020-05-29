package it.dpg.minigames.punchygame.controller;

import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.base.view.MinigameView;

public class MainController extends AbstractMinigame {

    @Override
    public int getMaxScore() {
        return 0;
    }

    @Override
    public MinigameView createView() {
        return null;
    }

    @Override
    public MinigameCycle createCycle() {
        return null;
    }
}
