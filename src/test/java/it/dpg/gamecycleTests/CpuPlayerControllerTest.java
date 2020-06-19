package it.dpg.gamecycleTests;

import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnStateImpl;
import it.dpg.maingame.controller.gamecycle.playercontroller.CpuPlayerController;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.model.character.Character;
import it.dpg.maingame.model.character.Cpu;
import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.model.character.Difficulty;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CpuPlayerControllerTest {

    private static class CpuMock implements Cpu{

        @Override
        public Character getControlledCharacter() {
            return null;
        }

        @Override
        public Difficulty getDifficulty() {
            return null;
        }

        @Override
        public ImmutablePair<Integer, Integer> getRandomDirection() {
            return new ImmutablePair<>(4, 8);
        }
    }

    private final Cpu cpuMock = new CpuMock();
    private final TurnState state = new TurnStateImpl();
    private final PlayerController pc = new CpuPlayerController(state, new GridViewMock(), cpuMock);

    @Test
    public void testDiceThrow() {
        state.newTurn();
        pc.throwDice(Dice.D6);
        assertTrue(state.wasDiceThrown());
    }

    @Test
    public void testDirectionChoice() {
        state.newTurn();
        pc.chooseDirection(Set.of(
            new ImmutablePair<>(4, 8),
            new ImmutablePair<>(3, 9),
            new ImmutablePair<>(4, 9)));
        assertTrue(state.getLastDirectionChoice().isPresent());
        assertEquals(new ImmutablePair<>(4, 8), state.getLastDirectionChoice().get());
        assertFalse(state.isChoosing());
    }

    @Test
    public void testMinigame() {
        state.newTurn();

    }
}
