package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.view.GridView;

public abstract class AbstractPlayerController implements PlayerController {

    protected final TurnState turnState;
    protected final GridView view;

    public AbstractPlayerController(final TurnState turnState, final GridView view) {
        this.turnState = turnState;
        this.view = view;
    }

    @Override
    public void waitNextStep() {
        turnState.setTurnPause(true);
        view.showText("continue ►");
        synchronized (this.turnState) {
            try {
                while (turnState.isPaused()) {
                    turnState.wait();
                }
            } catch (InterruptedException e) {
                System.out.println("thread interrupted during turn step wait");
            }
        }
        view.removeText();
    }
}
