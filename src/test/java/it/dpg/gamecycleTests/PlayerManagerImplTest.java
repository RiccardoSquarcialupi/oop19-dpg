package it.dpg.gamecycleTests;

import it.dpg.controller.gamecycle.playercontroller.CpuPlayerController;
import it.dpg.controller.gamecycle.playercontroller.HumanPlayerController;
import it.dpg.controller.gamecycle.turnmanagement.*;
import it.dpg.model.Cell;
import it.dpg.model.Grid;
import it.dpg.model.character.*;
import it.dpg.model.character.Character;
import it.dpg.view.GridView;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerManagerImplTest {

    private static class CpuMock implements Cpu {

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
            return new ImmutablePair<>(4, 8);
        }
    }

    public final Grid gridMock = new Grid() {
        @Override
        public Cell getFirst() {
            return null;
        }

        @Override
        public Cell getLast() {
            return null;
        }

        @Override
        public Cell getCellByCoordinates(Integer X, Integer Y) {
            return null;
        }
    };

    private final TurnState state = new TurnStateImpl();
    private final GridView view = new GridViewMock();
    private PlayerManager manager;

    @BeforeEach
    void setup() {
        initialize();
        addPlayers();
        state.newTurn();
    }

    void initialize() {
        manager = new PlayerManagerImpl();
    }

    void addPlayers() {
        Player p1 = new PlayerImpl(new CharacterImpl(1, "Franco", gridMock), new HumanPlayerController(state, view));
        Player p2 = new PlayerImpl(new CharacterImpl(2, "Alberto", gridMock), new HumanPlayerController(state, view));
        Player p3 = new PlayerImpl(new CharacterImpl(3, "CPU1", gridMock), new CpuPlayerController(state, view, new CpuMock()));
        manager.addPlayer(p1);
        manager.addPlayer(p2);
        manager.addPlayer(p3);
    }

    @Test
    void startTest1() { //test that every player gets the same dice at the start
        manager.startGame(5);
        Player p = manager.nextPlayer();
        Dice dice = p.getCharacter().getDice();
        for(Player player : manager.getPlayers()) {
            assertEquals(dice, player.getCharacter().getDice());
        }
    }

    void basicTestTurn(Player first, Player second, Player third) {
        assertTrue(manager.hasNextPlayer());
        assertEquals(first, manager.nextPlayer());
        assertTrue(manager.hasNextPlayer());
        assertEquals(second, manager.nextPlayer());
        assertTrue(manager.hasNextPlayer());
        assertEquals(third, manager.nextPlayer());
        assertFalse(manager.hasNextPlayer());
        assertThrows(IllegalStateException.class, () -> manager.hasNextPlayer());
    }

    @Test
    void basicTestGame() {
        manager.startGame(5);
        Set<Player> players = manager.getPlayers();
        Optional<Player> temp = players.stream().filter(p -> p.getCharacter().getTurn() == 0).findAny();
        assertTrue(temp.isPresent());
        Player first = temp.get();
        temp = players.stream().filter(p -> p.getCharacter().getTurn() == 1).findAny();
        assertTrue(temp.isPresent());
        Player second = temp.get();
        temp = players.stream().filter(p -> p.getCharacter().getTurn() == 2).findAny();
        assertTrue(temp.isPresent());
        Player third = temp.get();

        for(int i = 0; i < 4; i++) {
            basicTestTurn(first, second, third);
            assertTrue(manager.hasNextTurn());
            manager.nextTurn();
        }
        assertFalse(manager.hasNextTurn());
        assertThrows(IllegalStateException.class, () -> manager.nextTurn());
    }

    @Test
    void testExeption1() {
        assertThrows(IllegalStateException.class, () -> manager.hasNextPlayer());
        assertThrows(IllegalStateException.class, () -> manager.nextPlayer());
        assertThrows(IllegalStateException.class, () -> manager.hasNextTurn());
        assertThrows(IllegalStateException.class, () -> manager.nextTurn());
        manager.startGame(5);
        assertThrows(IllegalStateException.class, () -> new PlayerImpl(new CharacterImpl(4, "Albertino", gridMock), new HumanPlayerController(state, view)));
    }

    @Test
    void testExeption2() {
        assertThrows(IllegalArgumentException.class, () -> manager.addPlayer(
                new PlayerImpl(new CharacterImpl(1, "Albertino", gridMock), new HumanPlayerController(state, view))
        ));
    }
}
