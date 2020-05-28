package it.dpg.minigames.base.controller;

import it.dpg.maingame.model.character.Difficulty;

public interface Minigame {
    int start();
    int randomizeScore(Difficulty difficulty);
}
