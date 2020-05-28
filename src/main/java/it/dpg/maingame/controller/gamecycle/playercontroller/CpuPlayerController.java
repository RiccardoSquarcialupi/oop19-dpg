package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.model.character.Cpu;
import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.view.GridView;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

public class CpuPlayerController extends AbstractPlayerController{

    private final Cpu cpu;

    public CpuPlayerController(final TurnState turnState, final GridView view, final Cpu cpu) {
        super(turnState, view);
        this.cpu = cpu;
    }

    @Override
    public void throwDice(final Dice dice) {
        turnState.setDiceThrown(true);
    }

    @Override
    public void chooseDirection(final Set<ImmutablePair<Integer, Integer>> possibleCells) {
        ImmutablePair<Integer, Integer> direction = cpu.getRandomDirection();
        turnState.setLastDirectionChoice(direction);
    }

    @Override
    public int playMinigame() {
        //TODO implement the method when minigames are implemented
        return 0;
    }
}
