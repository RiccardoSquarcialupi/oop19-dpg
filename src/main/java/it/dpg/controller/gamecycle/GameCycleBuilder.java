package it.dpg.controller.gamecycle;

import it.dpg.model.character.Difficulty;

public interface GameCycleBuilder {

    GameCycleBuilder setDefaultDice();

    GameCycleBuilder setRewardDices();

    GameCycleBuilder addHumanPlayer(String name);

    GameCycleBuilder addCpu(String name, Difficulty difficulty);

    GameCycle build();
}
