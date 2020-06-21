package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.model.character.Character;
import it.dpg.maingame.model.character.Cpu;
import it.dpg.maingame.model.character.CpuImpl;
import it.dpg.maingame.model.character.Difficulty;
import it.dpg.maingame.view.GridView;
import it.dpg.minigames.MinigameType;
import it.dpg.minigames.base.controller.Minigame;
import org.apache.commons.lang3.tuple.Pair;

public class CpuPlayerController extends AbstractPlayerController {

    private final Cpu cpu;

    public CpuPlayerController(final TurnState turnState, final GridView view, final Character character, Difficulty difficulty) {
        super(turnState, view, character);
        this.cpu = new CpuImpl(character, difficulty);
    }

    @Override
    public int throwDice() {
        turnState.setDiceThrown(true);
        return character.throwDice();
    }

    @Override
    public void chooseDirection() {
        Pair<Integer, Integer> direction = cpu.getRandomDirection();
        turnState.setLastDirectionChoice(direction);
    }

    @Override
    public void playMinigame(MinigameType type) {
        Minigame minigame = type.getMinigame();
        handleMinigameResult(minigame.randomizeScore(cpu.getDifficulty()));
    }
}
