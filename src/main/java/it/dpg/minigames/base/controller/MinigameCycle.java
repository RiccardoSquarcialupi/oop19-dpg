package it.dpg.minigames.base.controller;

import it.dpg.minigames.base.view.MinigameView;

public interface MinigameCycle {
    <T extends MinigameView> int startCycle(final T view);
}
