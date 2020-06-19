package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.model.character.Dice;
import it.dpg.minigames.MinigameType;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Set;

/**
 * interface for handle behaviours of a specific entity, human or cpu
 * depending on the implementation.
 */
public interface PlayerController {

    /**
     * handles the throw of a dice for the player (human or cpu)
     * @param dice the type of dice throw
     */
    void throwDice(final Dice dice);

    /**
     * handles the choice of direction for the player (human or cpu)
     * @param possibleCells IDs if the possible choices
     */
    void chooseDirection(final Set<ImmutablePair<Integer, Integer>> possibleCells);

    /**
     * handles the pause of the turn, used between the steps of a turn (resumed by a button press)
     */
    void waitNextStep();

    /**
     * handles the phase of the turn in witch a player (human or cpu) has to play a minigame
     * @return the score
     * @param type the minigame that has to be played
     */
    int playMinigame(MinigameType type);
}
