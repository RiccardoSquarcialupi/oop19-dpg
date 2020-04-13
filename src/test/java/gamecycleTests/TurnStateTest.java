package gamecycleTests;

import static org.junit.jupiter.api.Assertions.*;

import it.dpg.controller.gamecycle.TurnStateImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.dpg.controller.gamecycle.TurnState;

public class TurnStateTest {

    TurnState state;

    @BeforeEach
    void Setup() {
        state = new TurnStateImpl();
    }

    @Test
    void testNewTurn() {
        state.newTurn();

        assertFalse(state.wasDiceThrown());
        assertFalse(state.isChoosing());
    }

    @Test
    void testExeption() {
        assertThrows(IllegalStateException.class, () -> state.isChoosing());
        assertThrows(IllegalStateException.class, () -> state.setIsChoosing(true));
        assertThrows(IllegalStateException.class, () -> state.setDiceThrown());
        assertThrows(IllegalStateException.class, () -> state.wasDiceThrown());
    }

    @Test
    void testDiceThrown() {
        state.newTurn();
        assertFalse(state.wasDiceThrown());
        state.setDiceThrown();
        assertTrue(state.wasDiceThrown());
        state.newTurn();
        assertFalse(state.wasDiceThrown());
        state.setDiceThrown();
        assertThrows(IllegalStateException.class, () -> state.setDiceThrown());
    }

    @Test
    void testChoice() {
        state.newTurn();
        assertFalse(state.isChoosing());
        state.setIsChoosing(true);
        assertTrue(state.isChoosing());
        state.setIsChoosing(false);
        assertFalse(state.isChoosing());
    }
}
