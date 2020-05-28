package it.dpg.maingame.controller.gamecycle;

import it.dpg.maingame.model.character.Difficulty;

public interface GameCycleBuilder {

    GameCycleBuilder setDefaultDice();

    GameCycleBuilder setRewardDices();

    GameCycleBuilder addHumanPlayer(String name);

    GameCycleBuilder addCpu(String name, Difficulty difficulty);

    GameCycle build();
}
