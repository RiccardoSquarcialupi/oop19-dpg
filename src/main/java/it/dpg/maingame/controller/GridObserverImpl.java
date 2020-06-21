package it.dpg.maingame.controller;

import it.dpg.maingame.controller.gamecycle.GameCycle;
import org.apache.commons.lang3.tuple.Pair;

public class GridObserverImpl implements GridObserver {

    private final GameCycle gameCycle;

    public GridObserverImpl(GameCycle gameCycle) {
        this.gameCycle = gameCycle;
    }

    @Override
    public void choosePathHandler(Pair<Integer, Integer> path) {
        gameCycle.signalPathChosen(path);
    }

    @Override
    public void throwDiceHandler() {
        gameCycle.signalDiceThrown();
    }

    public void KeyPressHandler() { gameCycle.signalNextStep(); }
}
