package it.dpg.controller.gamecycle.playercontroller;

import it.dpg.controller.gamecycle.TurnState;
import it.dpg.view.GridView;

import java.util.Set;

public class HumanPlayerController extends AbstractPlayerController{

    public HumanPlayerController(TurnState turnState, GridView view) {
        super(turnState, view);
    }

    @Override
    public void throwDice(int dice) {
        view.enableDiceThrow(dice);
        synchronized (this.turnState) {
            try {
                while (!turnState.wasDiceThrown()) {
                    turnState.wait();
                }
            } catch(InterruptedException e) {
                System.out.println("thread interrupted during dice throw wait");
            }
        }
        view.disableDiceThrow();
    }

    @Override
    public void chooseDirection(Set<Integer> possibleCells)  {
        view.enableDirectionChoice(possibleCells);
        turnState.setChoice(true);
        synchronized (this.turnState) {
            try {
                while (turnState.isChoosing()) {
                    turnState.wait();
                }
            } catch (InterruptedException e) {
                System.out.println("thread interrupted during direction choice wait");
            }
        }
        view.disableDirectionChoice();
        if(turnState.getLastDirectionChoice().isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    public int playMinigame() {
        //TODO implement the method when minigames are implemented
        return 0;
    }
}
