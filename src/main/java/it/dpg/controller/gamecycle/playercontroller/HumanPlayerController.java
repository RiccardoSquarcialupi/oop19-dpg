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
                while (!turnState.isDiceThrown()) {
                    turnState.wait();
                }
            } catch(InterruptedException e) {
                System.out.println("thread interrupted during dice throw wait");
            }
        }
        view.disableDiceThrow();
    }

    @Override
    public int chooseDirection(Set<Integer> possibleCells)  {
        view.enableDirectionChoice(possibleCells);
        turnState.choiceStarted();
        synchronized (this.turnState) {
            try {
                while (turnState.isChoosing()) {
                    turnState.wait();
                }
            } catch (InterruptedException e) {
                System.out.println("thread interrupted during dice throw wait");
            }
        }
        view.disableDirectionChoice();
        return 0;
    }

    @Override
    public int playMinigame() {
        //TODO implement the method when minigames are implemented
        return 0;
    }
}
