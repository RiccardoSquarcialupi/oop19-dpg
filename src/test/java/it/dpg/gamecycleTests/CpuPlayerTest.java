package it.dpg.gamecycleTests;

import it.dpg.controller.gamecycle.TurnState;
import it.dpg.controller.gamecycle.TurnStateImpl;
import it.dpg.controller.gamecycle.playercontroller.CpuPlayerController;
import it.dpg.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.model.character.Character;
import it.dpg.model.character.Cpu;
import it.dpg.model.character.Difficulty;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CpuPlayerTest {

    private class CpuMock implements Cpu{

        @Override
        public Character getControlledCharacter() {
            return null;
        }

        @Override
        public void setDifficulty(Difficulty difficulty) {

        }

        @Override
        public Difficulty getDifficulty() {
            return null;
        }

        @Override
        public ImmutablePair<Integer, Integer> getRandomDirection() {
            return null;
        }
    }

    private final Cpu cpuMock = new CpuMock();
    private final TurnState state = new TurnStateImpl();
    private final PlayerController pc = new CpuPlayerController(state, new GridViewMock(), cpuMock);

    @Test
    public void testDiceThrow() {
        state.newTurn();
        pc.throwDice(6);
        assertTrue(state.wasDiceThrown());
    }

    /*@Test
    public void testDirectionChoice() {
        state.newTurn();
        assertEquals(5, pc.chooseDirection(Set.of(2,5,9)));
        assertTrue(state.getLastDirectionChoice().isPresent());
        assertEquals(5, state.getLastDirectionChoice().get());
        assertFalse(state.isChoosing());
    }*/
}
