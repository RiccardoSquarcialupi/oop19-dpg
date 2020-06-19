package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.view.GridView;
import it.dpg.minigames.MinigameType;
import it.dpg.minigames.base.controller.Minigame;
import it.dpg.maingame.model.character.Character;

public class HumanPlayerController extends AbstractPlayerController{

    public HumanPlayerController(final TurnState turnState, final GridView view, final Character character) {
        super(turnState, view, character);
    }

    @Override
    public void throwDice(final Dice dice) {
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
    public void chooseDirection()  {
        view.enableDirectionChoice(getCharacter().getAdjacentPositions());
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
    public int playMinigame(MinigameType type) {
        Minigame minigame = type.getMinigame();
        return minigame.start();
    }
}
