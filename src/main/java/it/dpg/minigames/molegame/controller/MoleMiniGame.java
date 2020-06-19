package it.dpg.minigames.molegame.controller;

import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.Minigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.base.view.MinigameView;

public class MoleMiniGame extends AbstractMinigame implements Minigame {
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
