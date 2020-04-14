package gamecycleTests;

import static org.junit.jupiter.api.Assertions.*;

import it.dpg.controller.gamecycle.TurnStateImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.dpg.controller.gamecycle.TurnState;

import java.util.Optional;

public class TurnStateTest {

    private TurnState state;

    @BeforeEach
    void Setup() {
        state = new TurnStateImpl();
    }

    @Test
    void testNewTurn() {
        state.newTurn();

        assertFalse(state.isDiceThrown());
        assertFalse(state.isChoosing());
    }

    @Test
    void testExeption() {
        assertThrows(IllegalStateException.class, () -> state.isChoosing());
        assertThrows(IllegalStateException.class, () -> state.choiceStarted());
        assertThrows(IllegalStateException.class, () -> state.setDiceThrown());
        assertThrows(IllegalStateException.class, () -> state.isDiceThrown());
        assertThrows(IllegalStateException.class, () -> state.setLastDirectionChoice(1));
        assertThrows(IllegalStateException.class, () -> state.getLastDirectionChoice());
    }

    @Test
    void testDiceThrown() {
        state.newTurn();
        assertFalse(state.isDiceThrown());
        state.setDiceThrown();
        assertTrue(state.isDiceThrown());
        state.newTurn();
        assertFalse(state.isDiceThrown());
        state.setDiceThrown();
        assertThrows(IllegalStateException.class, () -> state.setDiceThrown());
    }

    @Test
    void testChoice() {
        state.newTurn();
        assertFalse(state.isChoosing());
        state.choiceStarted();
        assertTrue(state.isChoosing());
        state.choiceCompleted();
        assertFalse(state.isChoosing());
    }

    @Test
    void testDirectionChoice() {
        state.newTurn();
        assertTrue(state.getLastDirectionChoice().isEmpty());
        state.setLastDirectionChoice(1);
        assertTrue(state.getLastDirectionChoice().isPresent());
        assertEquals(1, state.getLastDirectionChoice().get());
        state.newTurn();
        assertTrue(state.getLastDirectionChoice().isEmpty());
    }
}
