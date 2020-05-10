package it.dpg.gamecycleTests;

import it.dpg.controller.gamecycle.player.Player;
import it.dpg.controller.gamecycle.player.PlayerImpl;
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

import java.util.List;
import java.util.NoSuchElementException;
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
    private final Dice defaultDice = Dice.D6;
    private final List<Dice> rewardDice = List.of(Dice.D10, Dice.D8, Dice.D6);

    @BeforeEach
    void setup() {
        create();
        state.newTurn();
    }

    void create() {
        Player p1 = new PlayerImpl(new CharacterImpl(1, "Franco", gridMock), new HumanPlayerController(state, view));
        Player p2 = new PlayerImpl(new CharacterImpl(2, "Alberto", gridMock), new HumanPlayerController(state, view));
        Player p3 = new PlayerImpl(new CharacterImpl(3, "CPU1", gridMock), new CpuPlayerController(state, view, new CpuMock()));
        manager = new PlayerManagerImpl(defaultDice, rewardDice, 5, Set.of(p1, p2, p3));
    }

    @Test
    void startTest1() { //test that every player gets the same dice at the start
        for(Player player : manager.getPlayers()) {
            assertEquals(defaultDice, player.getCharacter().getDice());
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
        assertThrows(NoSuchElementException.class, () -> manager.nextPlayer());
    }

    @Test
    void basicTestGame() {
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
}
