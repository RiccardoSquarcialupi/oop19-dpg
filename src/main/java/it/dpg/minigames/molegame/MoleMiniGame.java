package it.dpg.minigames.molegame;

import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.Minigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.base.view.MinigameView;
import it.dpg.minigames.molegame.controller.HitTheMoleCycle;
import it.dpg.minigames.molegame.controller.HitTheMoleCycleImpl;
import it.dpg.minigames.molegame.view.HitTheMoleView;
import it.dpg.minigames.molegame.view.HitTheMoleViewImpl;

public class MoleMiniGame extends AbstractMinigame implements Minigame {

    private final int maxScore = 60;
    private HitTheMoleView view;
    private HitTheMoleCycle cycle;

    public MoleMiniGame(){}

    @Override
    public int getMaxScore() {
        return 0;
    }

    @Override
    public MinigameView createView() {
        view = new HitTheMoleViewImpl();
        return view;
    }

    @Override
    public MinigameCycle createCycle() {
        cycle = new HitTheMoleCycleImpl();
        return cycle;
    }
}
