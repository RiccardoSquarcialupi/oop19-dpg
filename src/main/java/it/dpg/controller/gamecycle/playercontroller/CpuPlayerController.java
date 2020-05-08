package it.dpg.controller.gamecycle.playercontroller;

import it.dpg.controller.gamecycle.TurnState;
import it.dpg.model.character.Cpu;
import it.dpg.view.GridView;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

public class CpuPlayerController extends AbstractPlayerController{

    private final Cpu cpu;

    public CpuPlayerController(TurnState turnState, GridView view, Cpu cpu) {
        super(turnState, view);
        this.cpu = cpu;
    }

    @Override
    public void throwDice(int dice) {
        turnState.setDiceThrown();
    }

    @Override
    public void chooseDirection(Set<Integer> possibleCells) {
        ImmutablePair<Integer, Integer> direction = cpu.getRandomDirection();
        turnState.setLastDirectionChoice(direction);
    }

    @Override
    public int playMinigame() {
        //TODO implement the method when minigames are implemented
        return 0;
    }
}
