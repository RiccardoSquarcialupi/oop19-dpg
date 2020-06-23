package it.dpg.minigames.molegame;

import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.base.view.MinigameView;
import it.dpg.minigames.molegame.controller.HitTheMoleCycle;
import it.dpg.minigames.molegame.controller.HitTheMoleCycleImpl;
import it.dpg.minigames.molegame.view.HitTheMoleView;
import it.dpg.minigames.molegame.view.HitTheMoleViewImpl;

public class MoleMiniGame extends AbstractMinigame {

    private final int maxScore = 27;
    private HitTheMoleCycle cycle = new HitTheMoleCycleImpl();
    private HitTheMoleView view = new HitTheMoleViewImpl(cycle);


    public MoleMiniGame() {
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
