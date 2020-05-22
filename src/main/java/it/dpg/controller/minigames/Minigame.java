package it.dpg.controller.minigames;

import it.dpg.model.character.Difficulty;

public interface Minigame {
    int start();
    int randomizeScore(Difficulty difficulty);
}
