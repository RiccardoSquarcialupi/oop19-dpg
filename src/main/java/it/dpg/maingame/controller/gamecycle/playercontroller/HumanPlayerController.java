package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.model.character.Character;
import it.dpg.maingame.view.grid.GridView;
import it.dpg.minigames.MinigameType;
import it.dpg.minigames.base.controller.Minigame;

import java.util.concurrent.TimeUnit;

public class HumanPlayerController extends AbstractPlayerController {

    public HumanPlayerController(final TurnState turnState, final GridView view, final Character character) {
        super(turnState, view, character);
    }

    @Override
    public int throwDice() {
        view.enableDiceThrow(character.getDice());
        view.showText("throw the dice!");
        synchronized (this.turnState) {
            try {
                while (!turnState.wasDiceThrown()) {
                    turnState.wait();
                }
            } catch (InterruptedException e) {
                System.out.println("thread interrupted during dice throw wait");
            }
        }
        view.disableDiceThrow();
        view.removeText();
        return character.throwDice();
    }

    @Override
    public void chooseDirection() {
        view.enableDirectionChoice(getCharacter().getAdjacentPositions());
        view.showText("choose the direction on the map");
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
        view.removeText();
        view.disableDirectionChoice();
    }

    @Override
    public void playMinigame(MinigameType type) {
        Minigame minigame = type.getMinigame();
        view.showText("it's " + character.getName() + "'s turn to play the minigame");
        try {
            TimeUnit.MILLISECONDS.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        view.removeText();
        handleMinigameResult(minigame.start());
    }
}
