package it.dpg.minigames.punchygame;

import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.base.view.MinigameView;
import it.dpg.minigames.punchygame.controller.PunchygameCycle;
import it.dpg.minigames.punchygame.view.PunchygameView;
import it.dpg.minigames.punchygame.view.PunchygameViewImpl;

public class PunchyMinigame extends AbstractMinigame {

    private PunchygameView view = new PunchygameViewImpl();

    @Override
    public int getMaxScore() {
        return 400;
    }

    @Override
    public MinigameView createView() {
        return view;
    }

    @Override
    public MinigameCycle createCycle() {
        return new PunchygameCycle(view);
    }
}
