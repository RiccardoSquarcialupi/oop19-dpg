package it.dpg.minigames.ballgame.controller;

import it.dpg.maingame.controller.gamecycle.GameCycle;
import it.dpg.maingame.view.View;
import it.dpg.minigames.ballgame.view.BallMinigameView;
import it.dpg.minigames.ballgame.view.BallViewImpl;
import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.Minigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.base.view.MinigameView;
import javafx.application.Platform;

import java.awt.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class BallMinigame extends AbstractMinigame implements Minigame {
    private final int maxScore = 999;
    private BallMinigameView view;
    private BallGamecycle cycle;

    public BallMinigame() {
    }

    private void createComponents() {
        if (view == null || cycle == null) {
            BallMinigameObserver observer = new BallObserverImpl();
            this.view = new BallViewImpl(Toolkit.getDefaultToolkit().getScreenSize().height * 0.7, observer);
            this.cycle = new BallGamecycleImpl(view, maxScore);
            observer.addGamecycle(cycle);
        }
    }

    @Override
    public int getMaxScore() {
        return maxScore;
    }

    @Override
    public MinigameView createView() {
        createComponents();
        return view;
    }

    @Override
    public MinigameCycle createCycle() {
        createComponents();
        return cycle;
    }
}
