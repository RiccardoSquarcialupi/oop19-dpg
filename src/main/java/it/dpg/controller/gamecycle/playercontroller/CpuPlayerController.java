package it.dpg.controller.gamecycle.playercontroller;

import it.dpg.controller.gamecycle.TurnState;
import it.dpg.model.Cpu;
import it.dpg.view.GridView;

import java.util.Set;

public class CpuPlayerController extends AbstractPlayerController{

    public CpuPlayerController(TurnState turnState, GridView view, Cpu cpu) {
        super(turnState, view);
    }

    @Override
    public void throwDice(int dice) {
        turnState.setDiceThrown();
    }

    @Override
    public int chooseDirection(Set<Integer> possibleCells) {
        return 0;
    }

    @Override
    public int playMinigame() {
        //TODO implement the method when minigames are implemented
        return 0;
    }
}
