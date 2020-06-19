package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.model.character.Dice;
import it.dpg.minigames.MinigameType;
import it.dpg.maingame.model.character.Character;

/**
 * interface for handle behaviours of a specific entity, human or cpu
 * depending on the implementation.
 */
public interface PlayerController {

    /**
     * @return the character controller by the controller
     */
    Character getCharacter();

    /**
     * handles the throw of a dice for the player (human or cpu)
     * @param dice the type of dice throw
     */
    void throwDice(final Dice dice);

    /**
     * handles the choice of direction for the player (human or cpu)
     */
    void chooseDirection();

    /**
     * handles the pause of the turn, used between the steps of a turn (resumed by a button press)
     */
    void waitNextStep();

    /**
     * handles the phase of the turn in witch a player (human or cpu) has to play a minigame
     * @param type the minigame that has to be played
     */
    void playMinigame(MinigameType type);
}
