import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.dpg.controller.gamecycle.TurnState;

public class TurnStateTest {

    TurnState state;

    @BeforeEach
    void Setup() {

    }

    @Test
    void testNewTurn() {
        state.newTurn();

        assertEquals(false, state.wasDiceThrown());
        assertEquals(false, state.isChoosing());
    }

    @Test
    void testExeption() {
        assertThrows(IllegalStateException.class, () -> {
            state.isChoosing();
        });
        assertThrows(IllegalStateException.class, () -> {
            state.setIsChoosing(true);
        });
        assertThrows(IllegalStateException.class, () -> {
            state.setDiceThrown();
        });
        assertThrows(IllegalStateException.class, () -> {
            state.wasDiceThrown();
        });
    }

    void testDiceThrown() {
        state.newTurn();
        assertEquals(false, state.wasDiceThrown());
        state.setDiceThrown();
        assertEquals(true, state.wasDiceThrown());
        state.newTurn();
        assertEquals(false, state.wasDiceThrown());
        state.setDiceThrown();
        assertThrows(IllegalStateException.class, () -> {
            state.setDiceThrown();
        });
    }

    void testChoice() {
        state.newTurn();
        assertEquals(false, state.isChoosing());
        state.setIsChoosing(true);
        assertEquals(true, state.isChoosing());
        state.setIsChoosing(false);
        assertEquals(false, state.isChoosing());
    }
}
