package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.view.GridView;
import it.dpg.minigames.MinigameType;
import it.dpg.minigames.base.controller.Minigame;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

public class HumanPlayerController extends AbstractPlayerController{

    public HumanPlayerController(final TurnState turnState, final GridView view) {
        super(turnState, view);
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
    public void chooseDirection(final Set<ImmutablePair<Integer, Integer>> possibleCells)  {
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
    public int playMinigame(MinigameType type) {
        Minigame minigame = type.getMinigame();
        return minigame.start();
    }
}
