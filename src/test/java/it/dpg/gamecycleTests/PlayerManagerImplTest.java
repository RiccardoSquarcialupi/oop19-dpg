package it.dpg.gamecycleTests;

import it.dpg.maingame.controller.gamecycle.playercontroller.CpuPlayerController;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.controller.gamecycle.turnmanagement.*;
import it.dpg.maingame.model.Cell;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.character.*;
import it.dpg.maingame.model.character.Character;
import it.dpg.maingame.view.GridView;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerManagerImplTest {

    private static class CpuMock implements Cpu {

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

        @Override
        public Map<Cell, ImmutablePair<Integer, Integer>> getCellList() {
            return null;
        }
    };

    private final TurnState state = new TurnStateImpl();
    private final GridView view = new GridViewMock();
    private TurnManager manager;
    private final Dice defaultDice = Dice.D6;
    private final List<Dice> rewardDice = List.of(Dice.D10, Dice.D8, Dice.D6);

    @BeforeEach
    void setup() {
        create();
        state.newTurn();
    }

    void create() {
        PlayerController p1 = new CpuPlayerController(state, view, new CharacterImpl(1, "Franco", gridMock), Difficulty.HARD);
        PlayerController p2 = new CpuPlayerController(state, view, new CharacterImpl(2, "Alberto", gridMock), Difficulty.NORMAL);
        PlayerController p3 = new CpuPlayerController(state, view, new CharacterImpl(3, "CPU1", gridMock), Difficulty.EASY);
        manager = new TurnManagerImpl(defaultDice, rewardDice, 5, Set.of(p1, p2, p3));
    }

    @Test
    void startTest1() { //test that every player gets the same dice at the start
        for(PlayerController player : manager.getPlayers()) {
            assertEquals(defaultDice, player.getCharacter().getDice());
        }
    }

    void basicTestTurn(PlayerController first, PlayerController second, PlayerController third) {
        assertTrue(manager.hasNextPlayer());
        assertEquals(first, manager.nextPlayer());
        assertTrue(manager.hasNextPlayer());
        assertEquals(second, manager.nextPlayer());
        assertTrue(manager.hasNextPlayer());
        assertEquals(third, manager.nextPlayer());
        assertFalse(manager.hasNextPlayer());
        assertThrows(NoSuchElementException.class, () -> manager.nextPlayer());
    }

    @Test
    void basicTestGame() {
        List<PlayerController> players = manager.getPlayers();
        Optional<PlayerController> temp = players.stream().filter(p -> p.getCharacter().getTurn() == 0).findAny();
        assertTrue(temp.isPresent());
        PlayerController first = temp.get();
        temp = players.stream().filter(p -> p.getCharacter().getTurn() == 1).findAny();
        assertTrue(temp.isPresent());
        PlayerController second = temp.get();
        temp = players.stream().filter(p -> p.getCharacter().getTurn() == 2).findAny();
        assertTrue(temp.isPresent());
        PlayerController third = temp.get();

        for(int i = 0; i < 4; i++) {
            basicTestTurn(first, second, third);
            assertTrue(manager.hasNextTurn());
            manager.nextTurn();
        }
        assertFalse(manager.hasNextTurn());
        assertThrows(IllegalStateException.class, () -> manager.nextTurn());
    }
}
