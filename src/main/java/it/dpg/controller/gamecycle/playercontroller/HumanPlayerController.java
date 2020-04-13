package it.dpg.controller.gamecycle.playercontroller;

import it.dpg.controller.gamecycle.TurnState;
import it.dpg.view.GridView;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.util.Set;

public class HumanPlayerController extends AbstractPlayerController{
    public HumanPlayerController(TurnState turnState, GridView view) {
        super(turnState, view);
    }

    @Override
    public void throwDice(int dice) {

    }

    @Override
    public void chooseDirection(Set<Integer> possibleCells)  {

    }

    @Override
    public int playMinigame() {
        //TODO implement the method
        return 0;
    }
}
