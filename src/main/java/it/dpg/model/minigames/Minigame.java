package it.dpg.model.minigames;

import it.dpg.model.Difficulty;

public interface Minigame {
    int start();
    int randomizeScore(Difficulty difficulty);
}
