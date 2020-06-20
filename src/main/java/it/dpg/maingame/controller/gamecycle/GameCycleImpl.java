package it.dpg.maingame.controller.gamecycle;

import it.dpg.maingame.controller.GridViewGenerator;
import it.dpg.maingame.controller.GridViewGeneratorImpl;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerFactory;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerFactoryImpl;
import it.dpg.maingame.controller.gamecycle.turnmanagement.*;
import it.dpg.maingame.model.GridType;
import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.model.character.Difficulty;
import it.dpg.maingame.view.GridView;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Set;


public class GameCycleImpl implements GameCycle {

    private final TurnState turnState;
    private final GridView view;
    private final TurnManager turnManager;

    GameCycleImpl(int nTurns, Dice defaultDice, List<Dice> rewardDices, Set<String> humanPlayers, Set<ImmutablePair<String, Difficulty>> cpuPlayers) {
        this.turnState = new TurnStateImpl();
        GridType level = GridType.GRID_ONE;//randomize when multiple levels are present
        var pair = new GridViewGeneratorImpl(level, this).generate();
        this.view = pair.right;
        PlayerFactory playerFactory = new PlayerFactoryImpl(turnState, view, pair.left);
        TurnManagerBuilder turnManagerBuilder = new TurnManagerBuilderImpl(nTurns);
        turnManagerBuilder
                .setDefaultDice(defaultDice)
                .setRewardDices(rewardDices);
        for(String humanName : humanPlayers) {
            turnManagerBuilder.addPlayer(playerFactory.createHumanPlayer(humanName));
        }
        for(var nameDiff : cpuPlayers) {
            turnManagerBuilder.addPlayer(playerFactory.createCpu(nameDiff.left, nameDiff.right));
        }
        this.turnManager = turnManagerBuilder.build();
    }

    @Override
    public void startGameCycle() {
        throw new NotImplementedException("class not finished");
    }

    @Override
    public void signalDiceThrown() {
        throw new NotImplementedException("class not finished");
    }

    @Override
    public void signalPathChosen(ImmutablePair<Integer, Integer> coordinates) {
        throw new NotImplementedException("class not finished");
    }

    @Override
    public void signalNextStep() {
        throw new NotImplementedException("class not finished");
    }
}
