package it.dpg.maingame.controller.gamecycle;

import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerFactory;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerFactoryImpl;
import it.dpg.maingame.controller.gamecycle.turnmanagement.*;
import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.model.character.Difficulty;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Set;


public class GameCycleImpl implements GameCycle {

    private final TurnState turnState;

    GameCycleImpl(int nTurns, Dice defaultDice, List<Dice> rewardDices, Set<String> humanPlayers, Set<ImmutablePair<String, Difficulty>> cpuPlayers) {
        this.turnState = new TurnStateImpl();
        //PlayerFactory playerFactory = new PlayerFactoryImpl();
        TurnManagerBuilder turnManagerBuilder = new TurnManagerBuilderImpl(nTurns);
    }

    @Override
    public void startGameCycle() {

    }

    @Override
    public void signalDiceThrown() {

    }

    @Override
    public void signalPathChosen(ImmutablePair<Integer, Integer> coordinates) {

    }

    @Override
    public void signalNextStep() {

    }
}
