package it.dpg.minigames.molegame;

import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.base.view.MinigameView;
import it.dpg.minigames.molegame.controller.HitTheMoleCycle;
import it.dpg.minigames.molegame.controller.HitTheMoleCycleImpl;
import it.dpg.minigames.molegame.view.HitTheMoleView;
import it.dpg.minigames.molegame.view.HitTheMoleViewImpl;

public class MoleMiniGame extends AbstractMinigame {

    private final int maxScore = 60;
    private HitTheMoleView view;
    private HitTheMoleCycle cycle;

    public MoleMiniGame() {
        cycle = new HitTheMoleCycleImpl();
        view = new HitTheMoleViewImpl(cycle);
    }

    @Override
    public int getMaxScore() {
        return maxScore;
    }

    @Override
    public MinigameView createView() {

        return view;
    }

    @Override
    public MinigameCycle createCycle() {

        return cycle;
    }
}
