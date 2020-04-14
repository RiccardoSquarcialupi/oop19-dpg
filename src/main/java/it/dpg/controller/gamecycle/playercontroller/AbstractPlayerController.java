package it.dpg.controller.gamecycle.playercontroller;

import it.dpg.controller.gamecycle.TurnState;
import it.dpg.view.GridView;

public abstract class AbstractPlayerController implements PlayerController {

    protected final TurnState turnState;
    protected final GridView view;

    public AbstractPlayerController(TurnState turnState, GridView view) {
        this.turnState = turnState;
        this.view = view;
    }
}
