package it.dpg.maingame.controller.gamecycle;

import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.model.character.Difficulty;

import java.util.List;

public interface GameCycleBuilder {

    GameCycleBuilder setNTurns(final int nTurns);

    GameCycleBuilder setDefaultDice(final Dice defaultDice);

    GameCycleBuilder addRewardDice(final Dice rewardDice);

    GameCycleBuilder addHumanPlayer(final String name);

    GameCycleBuilder addCpu(final String name, final Difficulty difficulty);

    GameCycle build();
}
