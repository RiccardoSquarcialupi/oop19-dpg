package it.dpg.minigames.ballgame.controller;

import it.dpg.maingame.controller.gamecycle.GameCycle;
import it.dpg.maingame.view.View;
import it.dpg.minigames.ballgame.view.BallMinigameView;
import it.dpg.minigames.ballgame.view.BallViewImpl;
import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.Minigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.base.view.MinigameView;

import java.awt.*;

public class BallMinigame extends AbstractMinigame implements Minigame {
    private final int maxScore = 999;
    private final BallMinigameObserver observer = new BallObserverImpl();
    private final BallMinigameView view;
    private final BallGamecycle cycle;

    BallMinigame() {
        view = new BallViewImpl(Toolkit.getDefaultToolkit().getScreenSize().height * 0.7, observer);
        cycle = new BallGamecycleImpl(view, maxScore);
        observer.addGamecycle(cycle);
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
