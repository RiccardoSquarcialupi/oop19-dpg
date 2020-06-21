package it.dpg.minigames.jumpgame;

import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.base.view.MinigameView;
import it.dpg.minigames.jumpgame.controller.JumpMinigameCycle;
import it.dpg.minigames.jumpgame.view.JumpMinigameView;
import it.dpg.minigames.jumpgame.view.JumpMinigameViewImpl;

public class JumpMinigame extends AbstractMinigame {

    private JumpMinigameView view = new JumpMinigameViewImpl();

    @Override
    public int getMaxScore() {
        return 0;
    }

    @Override
    public MinigameView createView() {
        return view;
    }

    @Override
    public MinigameCycle createCycle() {
        return new JumpMinigameCycle(view);
    }
}
