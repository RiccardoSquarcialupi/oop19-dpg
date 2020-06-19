package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.view.GridView;

import it.dpg.maingame.model.character.Character;

import java.util.Objects;

public abstract class AbstractPlayerController implements PlayerController {

    protected final TurnState turnState;
    protected final GridView view;
    protected final Character character;

    public AbstractPlayerController(final TurnState turnState, final GridView view, final Character character) {
        this.character = character;
        this.turnState = turnState;
        this.view = view;
    }

    @Override
    public Character getCharacter() {
        return character;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPlayerController)) return false;
        AbstractPlayerController that = (AbstractPlayerController) o;
        return getCharacter().getId() == that.getCharacter().getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacter().getId());
    }
}
