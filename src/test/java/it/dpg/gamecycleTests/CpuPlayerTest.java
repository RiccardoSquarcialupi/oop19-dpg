package it.dpg.gamecycleTests;

import it.dpg.controller.gamecycle.TurnState;
import it.dpg.controller.gamecycle.TurnStateImpl;
import it.dpg.controller.gamecycle.playercontroller.CpuPlayerController;
import it.dpg.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.model.Cpu;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CpuPlayerTest {

    private class CpuMock implements Cpu{
        @Override
        public int randomizeDirectionChoice() {
            return 5;
        }
    }

    private final Cpu cpuMock = new CpuMock();
    private final TurnState state = new TurnStateImpl();
    private final PlayerController pc = new CpuPlayerController(state, new GridViewTestImpl(), cpuMock);

    @Test
    public void testDiceThrow() {
        state.newTurn();
        pc.throwDice(6);
        assertTrue(state.isDiceThrown());
    }

    @Test
    public void testDirectionChoice() {
        state.newTurn();
        assertEquals(5, pc.chooseDirection(Set.of(2,5,9)));
        assertTrue(state.getLastDirectionChoice().isPresent());
        assertEquals(5, state.getLastDirectionChoice().get());
        assertFalse(state.isChoosing());
    }
}
